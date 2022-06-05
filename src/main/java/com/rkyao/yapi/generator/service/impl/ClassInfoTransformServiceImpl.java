package com.rkyao.yapi.generator.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rkyao.yapi.generator.config.YapiGeneratorConfig;
import com.rkyao.yapi.generator.entity.template.ApiInfo;
import com.rkyao.yapi.generator.entity.template.EntityInfo;
import com.rkyao.yapi.generator.entity.template.FieldInfo;
import com.rkyao.yapi.generator.entity.template.ServiceInfo;
import com.rkyao.yapi.generator.entity.yapi.YapiInterfaceInfoDTO;
import com.rkyao.yapi.generator.entity.yapi.YapiPropertiesDTO;
import com.rkyao.yapi.generator.enums.FieldType;
import com.rkyao.yapi.generator.enums.GeneratingPatterns;
import com.rkyao.yapi.generator.service.ClassInfoTransformService;
import com.rkyao.yapi.generator.service.YapiOpenapiService;
import com.rkyao.yapi.generator.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 类信息转换接口
 *
 * @author yaorongke
 * @date 2022/5/22
 */
@Service
public class ClassInfoTransformServiceImpl implements ClassInfoTransformService {

    private static final Logger logger = LoggerFactory.getLogger(ClassGeneratorServiceImpl.class);

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @Autowired
    private YapiOpenapiService yapiOpenapiService;

    @Override
    public List<ServiceInfo> getServiceInfoList(List<String> idList, GeneratingPatterns pattern) {
        List<ApiInfo> apiInfoList = getApiInfoList(idList);
        if (CollectionUtils.isEmpty(apiInfoList)) {
            return Collections.emptyList();
        }

        switch (pattern) {
            case SINGLE:
                ServiceInfo serviceInfo = new ServiceInfo();
                serviceInfo.setServiceName(yapiGeneratorConfig.getClassDefaultName());
                serviceInfo.setBasePackage(yapiGeneratorConfig.getBasePackage());
                serviceInfo.setApiList(apiInfoList);
                return Collections.singletonList(serviceInfo);
            case MULTIPLE:
                List<ServiceInfo> serviceInfoList = new ArrayList<>();
                for (ApiInfo apiInfo : apiInfoList) {
                    ServiceInfo info = new ServiceInfo();
                    info.setServiceName(StringUtil.capitalizeFirstLetter(apiInfo.getMethodName()));
                    info.setBasePackage(yapiGeneratorConfig.getBasePackage());
                    info.setApiList(Collections.singletonList(apiInfo));
                    info.setServiceDesc(apiInfo.getDesc());
                    serviceInfoList.add(info);
                }
                return serviceInfoList;
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public List<ApiInfo> getApiInfoList(List<String> idList) {
        List<ApiInfo> apiInfoList = new ArrayList<>();
        for (String id : idList) {
            YapiInterfaceInfoDTO interfaceInfoDTO = yapiOpenapiService.getInterfaceInfo(id);
            if (interfaceInfoDTO.getErrcode() != 0) {
                logger.error("获取接口信息失败, interfaceInfoDTO: {}", JSONObject.toJSONString(interfaceInfoDTO));
                continue;
            }

            YapiInterfaceInfoDTO.DataDTO dataDTO = interfaceInfoDTO.getData();

            String[] pathArr = dataDTO.getPath().split("/");
            String method = pathArr[pathArr.length - 1];
            String methodName = StringUtil.buildHumpName(method.split("_"));

            // 参数信息字符串 body中的参数 只支持json格式
            // todo 支持body中的 form、file、raw格式
            YapiPropertiesDTO reqPropertiesDTO = JSONObject.parseObject(interfaceInfoDTO.getData().getReqBodyOther(), YapiPropertiesDTO.class);
            // 参数信息解析
            List<EntityInfo> reqEntityInfoList = analysisEntityInfo(reqPropertiesDTO, "RootParam", new ArrayList<>());

            // 返回信息字符串
            YapiPropertiesDTO respPropertiesDTO = JSONObject.parseObject(interfaceInfoDTO.getData().getResBody(),
                    YapiPropertiesDTO.class);
            // 返回信息解析
            List<EntityInfo> respEntityInfoList = analysisEntityInfo(respPropertiesDTO, "RootDTO", new ArrayList<>());

            List<EntityInfo> allEntityInfoList = new ArrayList<>();
            allEntityInfoList.addAll(reqEntityInfoList);
            allEntityInfoList.addAll(respEntityInfoList);

            // 如果接口数大于1，实体类名前加上接口名防止重复
            if (idList.size() > 1) {
                handeleEntityName(allEntityInfoList, methodName);
            }

            ApiInfo apiInfo = new ApiInfo();
            apiInfo.setHttpMethod(dataDTO.getMethod());
            apiInfo.setPath(dataDTO.getPath());
            apiInfo.setDesc(StringUtils.isEmpty(dataDTO.getMarkdown()) ? "这是注释" : dataDTO.getMarkdown());
            apiInfo.setMethodName(methodName);
            apiInfo.setParamList(analysisParamList(interfaceInfoDTO.getData(), reqEntityInfoList));
            apiInfo.setResponseType(analysisResponseType(respPropertiesDTO, respEntityInfoList));
            apiInfo.setEntityInfoList(allEntityInfoList);

            apiInfoList.add(apiInfo);
        }

        return apiInfoList;
    }

    private void handeleEntityName(List<EntityInfo> entityInfoList, String methodName) {
        if (CollectionUtils.isEmpty(entityInfoList)) {
            return;
        }
        for (EntityInfo entityInfo : entityInfoList) {
            entityInfo.setClassName(StringUtil.capitalizeFirstLetter(methodName) + entityInfo.getClassName());
        }
    }

    // 解析参数 get post的json格式
    private List<FieldInfo> analysisParamList(YapiInterfaceInfoDTO.DataDTO dataDTO, List<EntityInfo> reqEntityInfoList) {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        // query中的参数
        if (!CollectionUtils.isEmpty(dataDTO.getReqQuery())) {
            for (YapiInterfaceInfoDTO.DataDTO.ReqQueryDTO reqQueryDTO : dataDTO.getReqQuery()) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setType("String");
                fieldInfo.setName(reqQueryDTO.getName());
                fieldInfo.setDesc(reqQueryDTO.getDesc());
                fieldInfo.setRequired("1".equals(reqQueryDTO.getRequired()));
                fieldInfoList.add(fieldInfo);
            }
        }
        // body中的参数 只支持json格式
        if (!CollectionUtils.isEmpty(reqEntityInfoList)) {
            EntityInfo entityInfo = reqEntityInfoList.get(0);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setType(entityInfo.getClassName());
            fieldInfo.setName(StringUtil.lowercaseFirstLetter(entityInfo.getClassName()));
            fieldInfo.setDesc(entityInfo.getDesc());
            fieldInfo.setRequired(true);
            fieldInfo.setAnnotation("@RequestBody");
            fieldInfoList.add(fieldInfo);
        }

        return fieldInfoList;
    }

    private String analysisResponseType(YapiPropertiesDTO respPropertiesDTO, List<EntityInfo> respEntityInfoList) {
        // fixme respPropertiesDTO 空指针
        if (respPropertiesDTO == null || StringUtils.isEmpty(respPropertiesDTO.getType()) || CollectionUtils.isEmpty(respEntityInfoList)) {
            return "void";
        }
        if (!FieldType.OBJECT.getSource().equals(respPropertiesDTO.getType())) {
            return FieldType.getFieldType(respPropertiesDTO.getType()).getTarget();
        }
        return respEntityInfoList.get(0).getClassName();
    }

    public static void main(String[] args) {
        String resp = "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "        \"code\": {\n" +
                "            \"type\": \"number\",\n" +
                "            \"description\": \"状态码\"\n" +
                "        },\n" +
                "        \"msg\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"description\": \"状态信息\"\n" +
                "        },\n" +
                "        \"data\": {\n" +
                "            \"type\": \"object\",\n" +
                "            \"properties\": {\n" +
                "                \"id\": {\n" +
                "                    \"type\": \"number\",\n" +
                "                    \"description\": \"用户id\"\n" +
                "                },\n" +
                "                \"name\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"description\": \"用户名\"\n" +
                "                },\n" +
                "                \"address\": {\n" +
                "                    \"type\": \"object\",\n" +
                "                    \"properties\": {\n" +
                "                        \"country\": {\n" +
                "                            \"type\": \"string\",\n" +
                "                            \"description\": \"国家\"\n" +
                "                        },\n" +
                "                        \"street\": {\n" +
                "                            \"type\": \"string\",\n" +
                "                            \"description\": \"街道\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"required\": [\"country\", \"street\"]\n" +
                "                }\n" +
                "            },\n" +
                "            \"description\": \"数据\",\n" +
                "            \"required\": [\"address\"]\n" +
                "        }\n" +
                "    },\n" +
                "    \"description\": \"根节点\"\n" +
                "}";

        YapiPropertiesDTO propertiesDTO = JSONObject.parseObject(resp, YapiPropertiesDTO.class);

//        List<EntityInfo> entityInfoList = analysisEntityInfo(propertiesDTO, "RootDTO", null);
    }

    private List<EntityInfo> analysisEntityInfo(YapiPropertiesDTO propertiesDTO, String className, List<EntityInfo> entityInfoList) {
        if (propertiesDTO == null || !FieldType.OBJECT.getSource().equals(propertiesDTO.getType())) {
            return entityInfoList;
        }

        if (CollectionUtils.isEmpty(entityInfoList)) {
            entityInfoList = new ArrayList<>();
        }

        List<FieldInfo> fieldInfoList = new ArrayList<>();
        Map<String, YapiPropertiesDTO> propertiesMap = propertiesDTO.getProperties();
        for (Map.Entry<String, YapiPropertiesDTO> entry : propertiesMap.entrySet()) {
            String fieldName = entry.getKey();
            YapiPropertiesDTO dto = entry.getValue();

            FieldType fieldType = FieldType.getFieldType(dto.getType());
            String fieldTypeStr;
            if (fieldType == null || FieldType.OBJECT.equals(fieldType)) {
                fieldTypeStr = getFieldType(fieldName);
            } else if (FieldType.ARRAY.equals(fieldType)) {
                FieldType subFieldType = FieldType.getFieldType(dto.getItems().getType());
                if (dto.getItems() != null && FieldType.OBJECT.equals(subFieldType)) {
                    fieldTypeStr = fieldType.getTarget() + "<" + StringUtil.capitalizeFirstLetter(fieldName) + "DTO" + ">";
                } else {
                    fieldTypeStr = subFieldType == null ? fieldType.getTarget() : fieldType.getTarget() + "<" + subFieldType.getTarget() + ">";
                }
            } else {
                fieldTypeStr = fieldType.getTarget();
            }

            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setType(fieldTypeStr);
//            fieldInfo.setType(fieldType == null || FieldType.OBJECT.equals(fieldType) || FieldType.ARRAY.equals(fieldType) ? getFieldType(fieldName) : fieldType.getTarget());
            fieldInfo.setName(fieldName);
            fieldInfo.setDesc(StringUtils.isEmpty(dto.getDescription()) ? fieldName : dto.getDescription());
            fieldInfo.setRequired(false);

            fieldInfoList.add(fieldInfo);
        }

        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setClassName(className);
        entityInfo.setBasePackage("");
        entityInfo.setDesc(StringUtils.isEmpty(propertiesDTO.getDescription()) ? className : propertiesDTO.getDescription());
        entityInfo.setBasePackage(yapiGeneratorConfig.getBasePackage());
        entityInfo.setFieldList(fieldInfoList);

        entityInfoList.add(entityInfo);

        for (Map.Entry<String, YapiPropertiesDTO> entry : propertiesMap.entrySet()) {
            String fieldName = entry.getKey();
            YapiPropertiesDTO dto = entry.getValue();
            if (FieldType.OBJECT.getSource().equals(dto.getType())) {
                analysisEntityInfo(dto, getFieldType(fieldName), entityInfoList);
            }
            if (FieldType.ARRAY.getSource().equals(dto.getType())) {
                if (dto.getItems() != null && FieldType.OBJECT.getSource().equals(dto.getItems().getType())) {
                    analysisEntityInfo(dto.getItems(), getFieldType(fieldName), entityInfoList);
                }
            }
        }
        return entityInfoList;
    }

    private static String getFieldType(String fieldName) {
        return StringUtil.capitalizeFirstLetter(fieldName) + "DTO";
    }

    private static String getFieldType(String type, String fieldName) {
        FieldType fieldType = FieldType.getFieldType(type);
        if (fieldType== null || FieldType.OBJECT.equals(fieldType)) {
            return StringUtil.capitalizeFirstLetter(fieldName) + "DTO";
        }
        if (FieldType.ARRAY.equals(fieldType)) {
            return fieldType.getTarget() + "<" + StringUtil.capitalizeFirstLetter(fieldName) + "DTO" + ">";
        }
        return fieldType.getTarget();
    }

}

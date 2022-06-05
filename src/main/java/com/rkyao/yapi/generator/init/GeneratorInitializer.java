package com.rkyao.yapi.generator.init;

import com.rkyao.yapi.generator.config.YapiGeneratorConfig;
import com.rkyao.yapi.generator.entity.template.ServiceInfo;
import com.rkyao.yapi.generator.enums.GeneratingPatterns;
import com.rkyao.yapi.generator.service.ClassGeneratorService;
import com.rkyao.yapi.generator.service.ClassInfoTransformService;
import com.rkyao.yapi.generator.service.YapiOpenapiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 服务启动时执行
 *
 * @author yaorongke
 * @date 2022/5/29
 */
@Component
@Slf4j
public class GeneratorInitializer {

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @Autowired
    private YapiOpenapiService yapiOpenapiService;

    @Autowired
    private ClassGeneratorService classGeneratorService;

    @Autowired
    private ClassInfoTransformService classInfoTransformService;

    @PostConstruct
    public void init() {
        log.info("开始生成接口代码...");

        // 获取接口id列表
        List<String> interfaceIds = getInterfaceIds();

        // 类信息
        List<ServiceInfo> serviceInfoList = classInfoTransformService.getServiceInfoList(interfaceIds, GeneratingPatterns.valueOf(yapiGeneratorConfig.getClassPatterns()));

        // 生成类文件
        classGeneratorService.createClassFile(serviceInfoList);

        log.info("接口代码生成成功.");
    }

    private List<String> getInterfaceIds() {
        Set<String> interfaceIdSet = new HashSet<>();

        String catId = yapiGeneratorConfig.getCatId();
        if (!StringUtils.isEmpty(catId)) {
            List<String> ids = yapiOpenapiService.listCatIds(catId);
            if (!CollectionUtils.isEmpty(ids)) {
                interfaceIdSet.addAll(ids);
            }
        }

        String idsStr = yapiGeneratorConfig.getIntefaceIds();
        if (!StringUtils.isEmpty(idsStr)) {
            List<String> ids = Arrays.asList(idsStr.split(","));
            interfaceIdSet.addAll(ids);
        }

        if (CollectionUtils.isEmpty(interfaceIdSet)) {
            log.error("获取接口id失败, 请检查配置文件 [yapi-generator.properties], [yapi.api.interface.ids] 和 [yapi.api.cat.id] 至少配置一个");
            throw new RuntimeException("获取接口id失败, 请检查配置!");
        }
        return new ArrayList<>(interfaceIdSet);
    }

}

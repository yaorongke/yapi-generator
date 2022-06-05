package com.rkyao.yapi.generator.entity.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接口信息
 *
 * @author yaorongke
 * @date 2022/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {

    /**
     * http请求类型
     * @see com.rkyao.yapi.generator.enums.HttpMethod
     */
    private String httpMethod;

    /**
     * 请求地址
     */
    private String path;

    /**
     * 请求描述信息
     */
    private String desc = "ApiDesc";

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数列表
     */
    private List<FieldInfo> paramList;

    /**
     * 接口返回类型
     */
    private String responseType = "void";

    /**
     * 实体类信息
     */
    private List<EntityInfo> entityInfoList;

}

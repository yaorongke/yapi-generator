package com.rkyao.yapi.generator.entity.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * 服务信息
 *
 * @author yaorongke
 * @date 2022/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 服务描述信息
     */
    private String serviceDesc = "ClassDesc";

    /**
     * 接口类基础路径
     */
    private String basePackage;

    /**
     * 接口列表
     */
    private List<ApiInfo> apiList;

}

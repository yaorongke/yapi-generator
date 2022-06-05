package com.rkyao.yapi.generator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 读取application.properties中的配置
 *
 * @author yaorongke
 * @date 2021/12/12
 */
@Configuration
@Data
public class YapiGeneratorConfig {

    @Value("${yapi.api.interface.path}")
    private String intefacePath;

    @Value("${yapi.api.interface.ids}")
    private String intefaceIds;

    @Value("${yapi.api.cat.path}")
    private String catPath;

    @Value("${yapi.api.cat.id}")
    private String catId;

    @Value("${yapi.generator.base.package:com.rkyao.yapi.generator}")
    private String basePackage;

    @Value("${yapi.generator.class.patterns:SINGLE}")
    private String classPatterns;

    @Value("${yapi.generator.class.author:yaorongke}")
    private String classAuthor;

    @Value("${yapi.generator.class.defaultName:My}")
    private String classDefaultName;

}

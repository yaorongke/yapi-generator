package com.rkyao.yapi.generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker配置类
 *
 * @author yaorongke
 * @date 2022/5/26
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @PostConstruct
    public void setConfigure() throws Exception {
        // class的作者名 从匹配文件中读取，放到全局变量里，ftl里可直接使用
        configuration.setSharedVariable("classAuthor", yapiGeneratorConfig.getClassAuthor());
    }

}

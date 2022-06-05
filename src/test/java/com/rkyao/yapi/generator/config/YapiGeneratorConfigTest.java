package com.rkyao.yapi.generator.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * class
 *
 * @author yaorongke
 * @date 2021/12/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class YapiGeneratorConfigTest {

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @Test
    void testGet() {
        String interfacePath = yapiGeneratorConfig.getIntefacePath();
        System.out.println(interfacePath);
    }

}

package com.rkyao.yapi.generator.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * class
 *
 * @author yaorongke
 * @date 2022/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkerGeneratorTest {

    @Autowired
    private FreemarkerGenerator freemarkerGenerator;

    @Test
    public void testCreateFile() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "rkyao");

        freemarkerGenerator.createFile("/freemarker/test.ftl", "test.txt", dataMap);
    }

    @Test
    public void testCreateEntity() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "rkyao");

        freemarkerGenerator.createFile("/freemarker/entity.ftl", "entity.txt", dataMap);
    }

}

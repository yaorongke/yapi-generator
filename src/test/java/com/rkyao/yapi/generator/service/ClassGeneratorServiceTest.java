package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.config.YapiGeneratorConfig;
import com.rkyao.yapi.generator.entity.template.ServiceInfo;
import com.rkyao.yapi.generator.enums.GeneratingPatterns;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * class
 *
 * @author yaorongke
 * @date 2022/5/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassGeneratorServiceTest {

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @Autowired
    private ClassGeneratorService classGeneratorService;

    @Autowired
    private ClassInfoTransformService classInfoTransformService;

    @Test
    public void testCreateClassFile() {
        String ids = yapiGeneratorConfig.getIntefaceIds();
        if (StringUtils.isEmpty(ids)) {
            throw new RuntimeException("接口id未配置!");
        }
        List<String> idList = Arrays.asList(ids.split(","));

        // 类信息
        List<ServiceInfo> serviceInfoList = classInfoTransformService.getServiceInfoList(idList, GeneratingPatterns.valueOf(yapiGeneratorConfig.getClassPatterns()));

        // 生成类文件
        classGeneratorService.createClassFile(serviceInfoList);
    }

}


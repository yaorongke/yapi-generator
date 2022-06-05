package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.entity.template.ServiceInfo;
import com.rkyao.yapi.generator.enums.GeneratingPatterns;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class ClassInfoTransformServiceTest {

    @Autowired
    private ClassInfoTransformService classInfoTransformService;

    @Test
    public void testGetServiceInfoList() {
        List<String> idList = Arrays.asList("11", "17");
        List<ServiceInfo> serviceInfoList = classInfoTransformService.getServiceInfoList(idList, GeneratingPatterns.SINGLE);
        System.out.println(serviceInfoList);
    }

}

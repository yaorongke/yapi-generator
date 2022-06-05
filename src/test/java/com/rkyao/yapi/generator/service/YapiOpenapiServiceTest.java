package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.entity.yapi.YapiInterfaceInfoDTO;
import org.junit.Test;
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
public class YapiOpenapiServiceTest {

    @Autowired
    private YapiOpenapiService yapiOpenapiService;

    @Test
    public void testGetInterfaceInfo() {
        YapiInterfaceInfoDTO interfaceInfoDTO = yapiOpenapiService.getInterfaceInfo("11");
        System.out.println(interfaceInfoDTO);
    }

}

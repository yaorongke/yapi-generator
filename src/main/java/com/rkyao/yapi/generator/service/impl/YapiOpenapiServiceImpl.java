package com.rkyao.yapi.generator.service.impl;

import com.rkyao.yapi.generator.config.YapiGeneratorConfig;
import com.rkyao.yapi.generator.entity.yapi.YapiCatDTO;
import com.rkyao.yapi.generator.entity.yapi.YapiInterfaceInfoDTO;
import com.rkyao.yapi.generator.service.YapiOpenapiService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * yapi对外开放接口
 *
 * @author yaorongke
 * @date 2021/12/12
 */
@Service
@Slf4j
public class YapiOpenapiServiceImpl implements YapiOpenapiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private YapiGeneratorConfig yapiGeneratorConfig;

    @Override
    public YapiInterfaceInfoDTO getInterfaceInfo(String id) {
        String interfacePath = yapiGeneratorConfig.getIntefacePath() + "&id=" + id;
        YapiInterfaceInfoDTO interfaceInfoDTO;
        try {
            interfaceInfoDTO = restTemplate.getForObject(interfacePath, YapiInterfaceInfoDTO.class);
        } catch (Exception e) {
            log.error("获取接口 {} 详情失败, 请检查配置文件 [yapi-generator.properties], [yapi.url] 和 [yapi.project.token] 配置是否正确!", id);
            throw new RuntimeException("获取接口详情失败!");
        }
        if (interfaceInfoDTO == null || interfaceInfoDTO.getErrcode() != 0) {
            log.error("获取接口 {} 详情失败, 接口返回信息: {}", id, interfaceInfoDTO);
            throw new RuntimeException("获取接口详情失败!");
        }
        log.info("获取接口 {} 详情成功", id);
        return interfaceInfoDTO;
    }

    @Override
    public YapiCatDTO listCat(String catId) {
        String catPath = yapiGeneratorConfig.getCatPath() + "&catid=" + catId;
        YapiCatDTO yapiCatDTO;
        try {
            yapiCatDTO = restTemplate.getForObject(catPath, YapiCatDTO.class);
        } catch (Exception e) {
            log.error("获取分类 {} 下接口列表失败, 请检查配置文件 [yapi-generator.properties], [yapi.url] 和 [yapi.project.token] 配置是否正确!", catId);
            throw new RuntimeException("获取分类下接口列表失败!");
        }
        if (yapiCatDTO == null || yapiCatDTO.getErrcode() != 0) {
            log.error("获取分类 {} 下接口列表失败, 接口返回信息: {}", catId, yapiCatDTO);
            throw new RuntimeException("获取分类下接口列表失败!");
        }
        log.info("获取分类 {} 下接口列表成功.", catId);
        return yapiCatDTO;
    }

    @Override
    public List<String> listCatIds(String catId) {
        YapiCatDTO yapiCatDTO = listCat(catId);
        List<String> ids = yapiCatDTO.getData().getList().stream().map(YapiCatDTO.DataDTO.ListDTO::getId).collect(Collectors.toList());
        log.info("分类 {} 下的接口包括: {}", catId, ids);
        return ids;
    }

}

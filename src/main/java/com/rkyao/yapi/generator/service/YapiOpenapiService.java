package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.entity.yapi.YapiCatDTO;
import com.rkyao.yapi.generator.entity.yapi.YapiInterfaceInfoDTO;

import java.util.List;

/**
 * yapi对外开放接口
 *
 * @author yaorongke
 * @date 2021/12/12
 */
public interface YapiOpenapiService {

    /**
     * 获取接口数据（有详细接口数据定义文档）
     *
     * @return 接口详细数据
     */
    YapiInterfaceInfoDTO getInterfaceInfo(String id);

    /**
     * 获取某个分类下接口信息列表
     *
     * @return 接口信息列表
     */
    YapiCatDTO listCat(String catId);

    /**
     * 获取某个分类下接口id列表
     *
     * @return 接口id列表
     */
    List<String> listCatIds(String catId);

}

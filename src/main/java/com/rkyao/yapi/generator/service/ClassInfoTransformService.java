package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.entity.template.ApiInfo;
import com.rkyao.yapi.generator.entity.template.ServiceInfo;
import com.rkyao.yapi.generator.enums.GeneratingPatterns;

import java.util.List;

/**
 * 类信息转换接口
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public interface ClassInfoTransformService {

    List<ServiceInfo> getServiceInfoList(List<String> idList, GeneratingPatterns pattern);

    List<ApiInfo> getApiInfoList(List<String> idList);

}

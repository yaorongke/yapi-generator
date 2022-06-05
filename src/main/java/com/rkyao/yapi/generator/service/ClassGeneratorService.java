package com.rkyao.yapi.generator.service;

import com.rkyao.yapi.generator.entity.template.ServiceInfo;

import java.util.List;

/**
 * 生成接口类文件
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public interface ClassGeneratorService {

    void createClassFile(List<ServiceInfo> serviceInfoList);

}

package com.rkyao.yapi.generator.entity.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * entity实体类信息
 *
 * @author yaorongke
 * @date 2022/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityInfo {

    /**
     * 类名
     */
    private String className;

    /**
     * 描述信息
     */
    private String desc;

    /**
     * 接口类基础路径
     */
    private String basePackage;

    /**
     * 字段信息列表
     */
    private List<FieldInfo> fieldList;

}

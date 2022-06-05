package com.rkyao.yapi.generator.entity.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段信息
 *
 * @author yaorongke
 * @date 2022/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldInfo {

    /**
     * 字段类型
     * @see com.rkyao.yapi.generator.enums.FieldType
     */
    private String type;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段描述信息
     */
    private String desc;

    /**
     * 是否必须
     */
    private Boolean required;

    /**
     * 参数注解
     */
    private String annotation;

}

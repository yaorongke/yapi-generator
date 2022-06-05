package com.rkyao.yapi.generator.enums;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

/**
 * 字段类型枚举 yapi类型和java类型的映射关系
 *
 * @author yaorongke
 * @date 2022/5/21
 */
public enum FieldType {

    STRING("string", "String"),
    NUMBER("number", "Double"),
    ARRAY("array", "List"),
    OBJECT("object", ""),
    BOOLEAN("boolean", "Boolean"),
    INTEGER("integer", "Integer")
    ;

    /**
     * yapi的字段类型
     */
    private String source;

    /**
     * java的字段类型
     */
    private String target;

    FieldType(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
    
    public static FieldType getFieldType(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        for (FieldType type : FieldType.values()) {
            if (type.getSource().equals(source)) {
                return type;
            }
        }
        return null;
    }

}

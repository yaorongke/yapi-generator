package com.rkyao.yapi.generator.enums;

import org.springframework.util.StringUtils;

/**
 * http method
 *
 * @author yaorongke
 * @date 2022/5/21
 */
public enum HttpMethod {

    GET("GET", "Get"),
    POST("POST", "Post"),
    PUT("PUT", "Put"),
    DELETE("DELETE", "Delete")
    ;

    /**
     * yapi的字段类型
     */
    private String source;

    /**
     * java的字段类型
     */
    private String target;

    HttpMethod(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public static HttpMethod getHttpMethod(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        for (HttpMethod method : HttpMethod.values()) {
            if (method.getSource().equals(source)) {
                return method;
            }
        }
        return null;
    }

}

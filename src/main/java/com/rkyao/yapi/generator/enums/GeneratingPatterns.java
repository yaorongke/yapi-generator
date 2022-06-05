package com.rkyao.yapi.generator.enums;

import org.springframework.util.StringUtils;

/**
 * 接口类文件生成模式
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public enum GeneratingPatterns {

    /**
     * 所有接口生成到一个文件中
     */
    SINGLE,

    /**
     * 每个接口一个文件
     */
    MULTIPLE
    ;

}

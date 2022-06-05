package com.rkyao.yapi.generator.util;

import com.rkyao.yapi.generator.constant.GeneratorConstant;

import java.io.File;

/**
 * 字符工具类
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public class StringUtil {

    /**
     * 首字母大写
     *
     * @param str 原始字符
     * @return 首字母大写字符
     */
    public static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 原始字符
     * @return 首字母小写字符
     */
    public static String lowercaseFirstLetter(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 构建驼峰式名称
     *
     * @param arr 字符串列表
     * @return 驼峰式名称 eg: getName
     */
    public static String buildHumpName(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        if (arr.length == 1) {
            return arr[0];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                builder.append(arr[i]);
            } else {
                builder.append(capitalizeFirstLetter(arr[i]));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(capitalizeFirstLetter("get"));
        System.out.println(buildHumpName(new String[]{"get", "name"}));
    }

}

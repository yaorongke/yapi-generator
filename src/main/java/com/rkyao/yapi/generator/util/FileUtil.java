package com.rkyao.yapi.generator.util;

import com.rkyao.yapi.generator.constant.GeneratorConstant;
import org.junit.Test;

import java.io.File;

/**
 * 文件操作工具类
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public class FileUtil {

    /**
     * 删除文件及文件夹方法
     *
     * @param index
     */
    public static void delFile(File index) {
        if (index.isDirectory()) {
            File[] files = index.listFiles();
            for (File in : files) {
                delFile(in);
            }
        }
        index.delete();
    }

    public static void main(String[] args) {
//        delFile(new File("output"));

        String directories = "com.rkyao.test3";
        String path = GeneratorConstant.OUTPUT + "\\" + directories.replaceAll("\\.", "\\\\");

        File file = new File(path);
        boolean result = file.mkdirs();
    }

}

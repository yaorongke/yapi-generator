package com.rkyao.yapi.generator.util;

import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * 使用Freemarker模板引擎生成文件
 */
@Component
public class FreemarkerGenerator {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 根据ftl模板生成文件
     *
     * @param ftlName ftl模板路径
     * @param fileName 生成的文件保存路径
     * @param data 数据
     * @param <T> 类型
     * @return File
     */
    public <T> File createFile(String ftlName, String fileName, T data) {
        File f = new File(fileName);

        try {
            Template t = freeMarkerConfigurer.getConfiguration().getTemplate(ftlName);
            Writer w = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
            t.process(data, w, ObjectWrapper.BEANS_WRAPPER);
            w.flush();
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

}



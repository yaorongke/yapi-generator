package com.rkyao.yapi.generator.constant;

/**
 * 常量类
 *
 * @author yaorongke
 * @date 2022/5/22
 */
public class GeneratorConstant {

    /**
     * 路径分隔符
     */
    public static final String SEPARATOR = "\\";

    /**
     * Java类文件后缀
     */
    public static final String JAVA_SUFFIX = ".java";

    /**
     * 文件保存目录
     */
    public static final String OUTPUT = "output";

    /**
     * controller class文件保存目录
     */
    public static final String CONTROLLER_PATH = OUTPUT + SEPARATOR + "%s" + SEPARATOR + "controller";

    /**
     * service class文件保存目录
     */
    public static final String SERVICE_PATH = OUTPUT + SEPARATOR + "%s" + SEPARATOR + "service";

    /**
     * impl class文件保存目录
     */
    public static final String IMPL_PATH = OUTPUT + SEPARATOR + "%s" + SEPARATOR + "service" + SEPARATOR + "impl";

    /**
     * entity class文件保存目录
     */
    public static final String ENTITY_PATH = OUTPUT + SEPARATOR + "%s" + SEPARATOR + "entity";

    /**
     * controller ftl文件路径
     */
    public static final String CONTROLLER_FTL = "/freemarker/controller.ftl";

    /**
     * service ftl文件路径
     */
    public static final String SERVICE_FTL = "/freemarker/service.ftl";

    /**
     * controllimpler ftl文件路径
     */
    public static final String IMPL_FTL = "/freemarker/impl.ftl";

    /**
     * entity ftl文件路径
     */
    public static final String ENTITY_FTL = "/freemarker/entity.ftl";

}

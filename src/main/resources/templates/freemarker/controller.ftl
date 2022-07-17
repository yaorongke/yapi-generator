package ${basePackage}.controller;

import ${basePackage}.entity.*;
import ${basePackage}.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ${serviceDesc}
 *
 * @author ${classAuthor}
 * @date ${.now?string("yyyy/MM/dd")}
 */
@RestController
@RequestMapping("/")
public class ${serviceName}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${serviceName}Controller.class);

    @Autowired
    private ${serviceName}Service ${serviceName?uncap_first}Service;

    <#list apiList as api>
    /**
     * ${api.desc}
     *
    <#list api.paramList as param>
     * @param ${param.name} ${param.desc}
    </#list>
    <#if api.responseType != "void">
     * @return ${api.responseType?uncap_first}
    </#if>
     */
    @RequestMapping(value = "${api.path}", method = RequestMethod.${api.httpMethod})
    public ${api.responseType} ${api.methodName}(<#list api.paramList as param><#if param.annotation??>${param.annotation} </#if>${param.type} ${param.name}<#if param_has_next>, </#if></#list>) {
    <#if api.responseType != "void">
        return ${serviceName?uncap_first}Service.${api.methodName}(<#list api.paramList as param>${param.name}<#if param_has_next>, </#if></#list>);
    </#if>
    }

    </#list>
}
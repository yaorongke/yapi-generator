package ${basePackage}.service.impl;

import ${basePackage}.entity.*;
import ${basePackage}.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${serviceDesc}
 *
 * @author ${classAuthor}
 * @date ${.now?string("yyyy/MM/dd")}
 */
@Service
public class ${serviceName}ServiceImpl implements ${serviceName}Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(${serviceName}ServiceImpl.class);

   <#list apiList as api>
    @Override
    public ${api.responseType} ${api.methodName}(<#list api.paramList as param>${param.type} ${param.name}<#if param_has_next>, </#if></#list>) {
        <#if api.responseType != "void">
        return null;
        </#if>
    }

   </#list>
}
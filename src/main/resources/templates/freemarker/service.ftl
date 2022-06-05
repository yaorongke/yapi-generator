package ${basePackage}.service;

import ${basePackage}.entity.*;

import java.util.List;

/**
 * ${serviceDesc}
 *
 * @author ${classAuthor}
 * @date ${.now?string("yyyy/MM/dd")}
 */
public interface ${serviceName}Service {

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
    ${api.responseType} ${api.methodName}(<#list api.paramList as param>${param.type} ${param.name}<#if param_has_next>, </#if></#list>);

  </#list>
}

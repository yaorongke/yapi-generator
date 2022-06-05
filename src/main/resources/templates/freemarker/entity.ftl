package ${basePackage}.entity;

import ${basePackage}.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ${desc}
 *
 * @author ${classAuthor}
 * @date ${.now?string("yyyy/MM/dd")}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className} {

    <#list fieldList as field>
    /**
     * ${field.desc}
     */
    private ${field.type} ${field.name};

    </#list>
}

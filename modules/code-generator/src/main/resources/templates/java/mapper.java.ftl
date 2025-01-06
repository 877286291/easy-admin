package ${package.Mapper};

import ${package.Entity}.${table.entityName};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;

/**
<#if table.comment!?length gt 0>
 * <p>
 * ${table.comment!}
 * </p>
 *
</#if>
* @author ${author}
* @since ${date}
*/


@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${table.entityName}> {

}

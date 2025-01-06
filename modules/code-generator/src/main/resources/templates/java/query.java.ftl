package ${package.Query};

import ${superQueryClassPackage};
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
 <#if queryChainModel??>
import lombok.experimental.Accessors;
 </#if>
</#if>
/**
<#if table.comment!?length gt 0>
 * <p>
 * ${table.comment!} 查询类
 * </p>
 *
</#if>
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Getter
@Setter
 <#if queryChainModel??>
@Accessors(chain = true)
 </#if>
</#if>
<#if springdoc??>
@Schema(name = "${table.queryName}", description = "${table.comment!}")
<#elseif swagger??>
@ApiModel(value = "${table.queryName}对象", description = "${table.comment!}")
</#if>
<#if superQueryClass??>
public class ${table.queryName} extends ${superQueryClass}<#if activeRecord??><${table.queryName}></#if> {
<#elseif activeRecord>
public class ${table.queryName} extends Model<${table.queryName}> {
<#elseif querySerialVersionUID>
public class ${table.queryName} implements Serializable {
<#else>
public class ${table.queryName} {
</#if>
<#if querySerialVersionUID>
   private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
 <#if field.comment!?length gt 0>
  <#if springdoc>
   @Schema(description = "${field.comment}")
  <#elseif swagger>
   @ApiModelProperty("${field.comment}")
  <#else>
   /**
   * ${field.comment}
   */
  </#if>
 </#if>
   private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
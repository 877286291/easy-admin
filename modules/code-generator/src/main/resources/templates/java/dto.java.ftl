package ${package.Dto};

import ${superDtoClassPackage};
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
 <#if dtoChainModel??>
import lombok.experimental.Accessors;
 </#if>
</#if>
/**
<#if table.comment!?length gt 0>
 * <p>
 * ${table.comment!} 传输类
 * </p>
 *
</#if>
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Getter
@Setter
 <#if dtoChainModel??>
@Accessors(chain = true)
 </#if>
</#if>
<#if springdoc??>
@Schema(name = "${table.dtoName}", description = "${table.comment!}")
<#elseif swagger??>
@ApiModel(value = "${table.dtoName}对象", description = "${table.comment!}")
</#if>
<#if superDtoClass??>
public class ${table.dtoName} extends ${superDtoClass}<#if activeRecord??><${table.dtoName}></#if> {
<#elseif activeRecord>
public class ${table.dtoName} extends Model<${table.dtoName}> {
<#elseif dtoSerialVersionUID>
public class ${table.dtoName} implements Serializable {
<#else>
public class ${table.dtoName} {
</#if>
<#if dtoSerialVersionUID>
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
package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
<#if swagger??>
import io.swagger.v3.oas.annotations.tags.Tag;
</#if>

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
<#if table.comment!?length gt 0>
 * <p>
 * ${table.comment!} api接口
 * </p>
 *
</#if>
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/${entityPath}")
<#if swagger??>
@Tag(name = "")
</#if>
@RequiredArgsConstructor
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

}
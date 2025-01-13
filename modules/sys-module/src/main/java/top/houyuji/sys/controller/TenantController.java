package top.houyuji.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.sys.domain.dto.TenantBasicConfigDTO;
import top.houyuji.sys.domain.dto.TenantDTO;
import top.houyuji.sys.domain.dto.TenantSmallDTO;
import top.houyuji.sys.domain.query.TenantQuery;
import top.houyuji.sys.service.SysTenantService;

import java.util.List;


@RestController
@RequestMapping("/sys/tenant/org")
@RequiredArgsConstructor
@Tag(name = "管理端：商户管理")
public class TenantController {
    private final SysTenantService tenantService;


    /**
     * 商户识别码是否存在
     *
     * @param sysCode .
     * @param id      需要排除的ID
     * @return .
     */
    @GetMapping("/existsBySysCode")
    @Operation(summary = "商户识别码是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "sysCode", description = "商户识别码", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的ID")
    })
    public R<Boolean> existsBySysCode(String sysCode, @RequestParam(name = "id", required = false) String id) {
        Boolean res = tenantService.existsCode(sysCode, id);
        return R.OK(res);
    }

    /**
     * 租户机构分页
     *
     * @param query 查询条件
     * @return 机构分页
     */
    @GetMapping()
    @Operation(summary = "租户机构分页")
    public R<JsfPage<TenantDTO>> tenantPage(TenantQuery query) {
        JsfPage<TenantDTO> res = tenantService.page(query);
        return R.OK(res);
    }

    /**
     * 租户机构列表
     *
     * @param query 查询条件
     * @return 机构列表
     */
    @GetMapping("/list")
    @Operation(summary = "租户机构列表")
    public R<List<TenantDTO>> list(TenantQuery query) {
        List<TenantDTO> res = tenantService.list(query);
        return R.OK(res);
    }

    /**
     * 保存租户
     *
     * @param dto 租户机构
     * @return 保存结果
     */
    @PostMapping()
    @Operation(summary = "保存租户")
    public R<String> save(@Valid @RequestBody TenantSmallDTO dto) {
        tenantService.save(dto);
        return R.OK("保存成功");
    }

    /**
     * 更新租户
     *
     * @param dto 租户机构
     * @return 更新结果
     */
    @PutMapping()
    @Operation(summary = "更新租户机构")
    public R<String> updateTenantOrganization(@RequestBody @Valid TenantSmallDTO dto) {
        if (StrUtil.isBlank(dto.getId())) {
            return R.NG("id不能为空");
        }
        tenantService.updateById(dto);
        return R.OK("更新成功");
    }

    /**
     * 重置密码
     *
     * @param id .
     * @return .
     */
    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码")
    @Parameters({@io.swagger.v3.oas.annotations.Parameter(name = "id", description = "租户ID", required = true)})
    public R<String> resetPassword(@RequestParam String id) {
        tenantService.resetPassword(id);
        return R.OK("重置成功");
    }

    /**
     * 获取租户机构基础配置
     *
     * @param id .
     * @return .
     */
    @GetMapping("/basic/config")
    @Operation(summary = "获取租户机构基础配置")
    @Parameters({@io.swagger.v3.oas.annotations.Parameter(name = "id", description = "租户ID", required = true)})
    public R<TenantBasicConfigDTO> getTenantOrgConfig(@RequestParam String id) {
        TenantBasicConfigDTO res = tenantService.getTenantBasicConfig(id);
        return R.OK(res);
    }

    /**
     * 更新租户机构基础配置
     *
     * @param dto .
     * @return .
     */
    @PutMapping("/basic/config")
    @Operation(summary = "更新租户机构基础配置")
    public R<String> updateTenantOrgConfig(@Valid @RequestBody TenantBasicConfigDTO dto) {
        tenantService.updateTenantOrgBasicConfig(dto);
        return R.OK("更新成功");
    }

}

package top.houyuji.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.sys.domain.dto.PermissionDTO;
import top.houyuji.sys.domain.dto.PermissionSaveDTO;
import top.houyuji.sys.domain.query.PermissionQuery;
import top.houyuji.sys.domain.vo.MenuTreeVO;
import top.houyuji.sys.service.SysTenantPermissionService;
import top.houyuji.utils.MenuUtil;

import java.util.List;


@RestController
@RequestMapping("/sys/tenant/permission")
@Tag(name = "管理端：商户权限管理")
@RequiredArgsConstructor
public class TenantPermissionController {
    private final SysTenantPermissionService sysTenantPermissionService;

    /**
     * 权限列表【管理端】
     *
     * @return 权限列表
     */
    @GetMapping
    @Operation(summary = "权限列表")
    public R<List<PermissionDTO>> listPermission(PermissionQuery query) {
        List<PermissionDTO> list = sysTenantPermissionService.listByQuery(query);
        return R.OK(list);
    }

    /**
     * 菜单树
     *
     * @return .
     */
    @GetMapping("/menu/tree")
    @Operation(summary = "菜单树")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "filterButton", description = "是否过滤按钮", required = false)
    })
    public R<List<MenuTreeVO>> menuTree(@RequestParam(required = false, defaultValue = "false",
            name = "filterButton") Boolean filterButton) {
        List<PermissionDTO> allEnabled = sysTenantPermissionService.findAllEnabled();
        List<MenuTreeVO> res = MenuUtil.buildMenuTree(allEnabled, filterButton);
        return R.OK(res);
    }

    /**
     * 保存权限
     *
     * @param PermissionDTO 权限
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存权限")
    public R<String> savePermission(@Valid @RequestBody PermissionSaveDTO PermissionDTO) {
        PermissionDTO.setId(null);
        if (StrUtil.isNotBlank(PermissionDTO.getParentId())
                && "0".equals(PermissionDTO.getParentId())) {
            PermissionDTO.setParentId(null);
        }
        if ("".equals(PermissionDTO.getParentId())) {
            PermissionDTO.setIcon(null);
        }
        sysTenantPermissionService.save(PermissionDTO);
        return R.OK("保存成功");
    }

    /**
     * 更新权限
     *
     * @param PermissionDTO 权限
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新权限")
    public R<String> updatePermission(@RequestBody @Valid PermissionSaveDTO PermissionDTO) {
        if (PermissionDTO.getId() == null) {
            return R.NG("id不能为空");
        }
        if (StrUtil.isNotBlank(PermissionDTO.getParentId())
                && "0".equals(PermissionDTO.getParentId())) {
            PermissionDTO.setParentId(null);
        }
        if ("".equals(PermissionDTO.getParentId())) {
            PermissionDTO.setIcon(null);
        }
        sysTenantPermissionService.updateById(PermissionDTO);
        return R.OK("更新成功");
    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除权限")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "权限id", required = true, example = "1", schema = @io.swagger.v3.oas.annotations.media.Schema(type = "number"))
    })
    public R<String> deletePermission(String id) {
        sysTenantPermissionService.deleteById(id);
        return R.OK("删除成功");
    }
}

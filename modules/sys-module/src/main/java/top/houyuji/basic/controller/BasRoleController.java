package top.houyuji.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.houyuji.basic.domain.dto.BasRoleDTO;
import top.houyuji.basic.domain.query.BasRoleQuery;
import top.houyuji.basic.service.BasRoleService;
import top.houyuji.common.api.BaseQuery;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.common.satoken.utils.SaTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/bas/role")
@Tag(name = "履约端：角色管理")
@RequiredArgsConstructor
@Slf4j
public class BasRoleController {
    private final BasRoleService service;

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "分页查询")
    public R<JsfPage<BasRoleDTO>> page(@Validated({BaseQuery.PageGroup.class}) BasRoleQuery query) {
        String sysCode = SaTokenUtil.getSysCode();
        query.setSysCode(sysCode);
        JsfPage<BasRoleDTO> res = service.page(query);
        return R.OK(res);
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping("/list")
    @Operation(summary = "查询")
    public R<List<BasRoleDTO>> list(BasRoleQuery query) {
        String sysCode = SaTokenUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasRoleDTO> res = service.list(query);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "保存")
    public R<String> save(@Valid @RequestBody BasRoleDTO dto) {
        String sysCode = SaTokenUtil.getSysCode();
        dto.setSysCode(sysCode);
        service.save(dto);
        return R.OK();
    }

    /**
     * 修改
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改")
    public R<String> update(@Valid @RequestBody BasRoleDTO dto) {
        if (dto.getId() == null) {
            return R.NG("id不能为空");
        }
        String sysCode = SaTokenUtil.getSysCode();
        dto.setSysCode(sysCode);
        service.updateById(dto);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam String id) {
        service.deleteById(id);
        return R.OK();
    }

    /**
     * code是否存在
     *
     * @param code 角色标识
     * @param id   需要排除的id
     * @return .
     */
    @GetMapping("/existsByCode")
    @Operation(summary = "code是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "角色标识", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id")
    })
    public R<Boolean> existsByCode(@RequestParam String code, @RequestParam(required = false) String id) {
        String sysCode = SaTokenUtil.getSysCode();
        Boolean res = service.existsCode(code, sysCode, id);
        return R.OK(res);
    }

    /**
     * 获取角色权限
     *
     * @param id 角色id
     * @return .
     */
    @GetMapping("/permission")
    @Operation(summary = "获取角色权限")
    public R<List<String>> getPermission(String id) {
        List<String> res = service.getPermissionIdsByRoleId(id);
        return R.OK(res);
    }

    /**
     * 授权
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return .
     */
    @PutMapping("/grant")
    @Operation(summary = "授权")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "roleId", description = "角色id", required = true),
    })
    public R<String> grant(@RequestParam String id, @RequestBody List<String> permissionIds) {
        service.grantPermission(id, permissionIds);
        return R.OK();
    }
}

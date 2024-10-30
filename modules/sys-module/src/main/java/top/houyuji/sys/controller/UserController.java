package top.houyuji.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.api.BaseQuery;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.CollectionUtil;
import top.houyuji.common.base.utils.PasswordUtil;
import top.houyuji.satoken.utils.SaTokenUtil;
import top.houyuji.sys.domain.dto.RoleSmallDTO;
import top.houyuji.sys.domain.dto.UserDTO;
import top.houyuji.sys.domain.dto.UserRestPwdDTO;
import top.houyuji.sys.domain.dto.UserSaveDTO;
import top.houyuji.sys.domain.query.UserQuery;
import top.houyuji.sys.service.SysUserService;

import java.util.List;


@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Tag(name = "管理端：用户管理")
public class UserController {
    private final SysUserService sysUserService;

    /**
     * 分页查询用户
     *
     * @param query 查询条件
     * @return 用户分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询用户")
    public R<JsfPage<UserDTO>> page(@Validated({BaseQuery.PageGroup.class}) UserQuery query) {
        JsfPage<UserDTO> res = sysUserService.page(query);
        // 忽略密码
        List<UserDTO> records = res.getRecords();
        if (CollectionUtil.isNotEmpty(records)) {
            records.forEach(UserDTO -> UserDTO.setPassword(null));
        }
        return R.OK(res);
    }

    /**
     * 查询用户列表
     *
     * @param query 查询条件
     * @return 用户列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户列表")
    public R<List<UserDTO>> list(@Validated UserQuery query) {
        List<UserDTO> res = sysUserService.list(query);
        // 忽略密码
        res.forEach(UserDTO -> UserDTO.setPassword(null));
        return R.OK(res);
    }

    /**
     * 用户名是否存在
     *
     * @param username .
     * @param id       .
     * @return .
     */
    @GetMapping("/existsByUsername")
    @Operation(summary = "用户名是否存在")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true, example = "admin"),
            @Parameter(name = "id", description = "需要排除的用户ID", example = "1")
    })
    public R<Boolean> existsByUsernameAndIdNot(@RequestParam("username") String username,
                                               @RequestParam(name = "id", required = false) String id) {
        Boolean res = sysUserService.existsByUsername(username, id);
        return R.OK(res);
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    @GetMapping("/roles")
    @Operation(summary = "根据用户id查询角色")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true, example = "1")
    })
    public R<List<RoleSmallDTO>> getRolesByUserId(String userId) {
        List<RoleSmallDTO> res = sysUserService.getRoles(userId);
        return R.OK(res);
    }

    /**
     * 保存用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存用户")
    public R<String> save(@Validated({UserDTO.AddGroup.class}) @RequestBody UserSaveDTO dto) {
        dto.setPassword(PasswordUtil.encoder(dto.getPassword()));
        sysUserService.save(dto);
        return R.OK();
    }

    /**
     * 更新用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新用户")
    public R<String> update(@Validated({UserDTO.UpdateGroup.class}) @RequestBody UserSaveDTO dto) {
        dto.setPassword(null);
        sysUserService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除用户")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true, example = "1")
    })
    public R<String> delete(@RequestParam("id") String id) {
        sysUserService.deleteById(id);
        return R.OK();
    }

    /**
     * 重置密码
     *
     * @param dto 数据
     * @return 是否成功
     */
    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码")
    public R<String> resetPassword(@Validated({UserRestPwdDTO.RestPwdGroup.class}) @RequestBody UserRestPwdDTO dto) {
        String password = dto.getPassword();
        String pwd = PasswordUtil.encoder(password);
        dto.setPassword(pwd);
        String username = SaTokenUtil.getUsername();
        dto.setOperator(username);
        sysUserService.resetPassword(dto);
        return R.OK();
    }
}

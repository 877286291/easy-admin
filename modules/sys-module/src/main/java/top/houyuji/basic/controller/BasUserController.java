package top.houyuji.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.basic.domain.dto.BasUserDTO;
import top.houyuji.basic.domain.dto.BasUserRestPasswordDTO;
import top.houyuji.basic.domain.dto.BasUserSaveDTO;
import top.houyuji.basic.domain.query.BasUserQuery;
import top.houyuji.basic.service.BasUserService;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.PasswordUtil;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.satoken.utils.SaTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/bas/user")
@Tag(name = "履约端：用户管理")
@RequiredArgsConstructor
public class BasUserController {
    private final BasUserService service;

    /**
     * 账号是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return .
     */
    @GetMapping("/existsByUsername")
    @Operation(summary = "账号是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "用户名", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id")
    })
    public R<Boolean> existsByUsername(String username, @RequestParam(required = false) String id) {
        String sysCode = SaTokenUtil.getSysCode();
        Boolean res = service.existsUsername(username, sysCode, id);
        return R.OK(res);
    }

    @GetMapping
    @Operation(summary = "分页查询")
    public R<JsfPage<BasUserDTO>> page(BasUserQuery query) {
        String sysCode = SaTokenUtil.getSysCode();
        query.setSysCode(sysCode);
        JsfPage<BasUserDTO> res = service.page(query);
        return R.OK(res);
    }

    /**
     * 获取用户角色
     *
     * @param id .
     * @return .
     */
    @GetMapping("/roles")
    public R<List<String>> getRoleIds(String id) {
        List<String> res = service.getRoleIds(id);
        return R.OK(res);
    }

    @PostMapping
    @Operation(summary = "保存")
    public R<String> save(@Valid @RequestBody BasUserSaveDTO dto) {
        if (StrUtil.isBlank(dto.getPassword())) {
            return R.NG("密码不能为空");
        }
        String sysCode = SaTokenUtil.getSysCode();
        dto.setSysCode(sysCode);
        dto.setPassword(PasswordUtil.encoder(dto.getPassword()));
        service.save(dto);
        return R.OK();
    }

    @PutMapping
    @Operation(summary = "更新")
    public R<String> update(@Valid @RequestBody BasUserSaveDTO dto) {
        String id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        dto.setSysCode(SaTokenUtil.getSysCode());
        dto.setPassword(null);
        service.updateById(dto);
        return R.OK();
    }

    /**
     * 重置密码
     *
     * @param dto .
     * @return .
     */
    @PutMapping("/restPassword")
    @Operation(summary = "重置密码")
    public R<String> restPassword(@Valid @RequestBody BasUserRestPasswordDTO dto) {
        String username = SaTokenUtil.getUsername();
        dto.setOperator(username);
        dto.setPassword(PasswordUtil.encoder(dto.getPassword()));
        service.restPassword(dto);
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
    public R<String> delete(@RequestParam String id) {
        service.removeById(id);
        return R.OK();
    }
}

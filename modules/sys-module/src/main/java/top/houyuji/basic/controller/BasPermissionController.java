package top.houyuji.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.houyuji.basic.domain.dto.BasPermissionDTO;
import top.houyuji.basic.service.BasPermissionService;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.TreeUtil;
import top.houyuji.common.satoken.utils.SaTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/bas/permission")
@Tag(name = "履约端：权限管理")
@RequiredArgsConstructor
public class BasPermissionController {
    private final BasPermissionService basPermissionService;

    /**
     * 菜单权限树
     *
     * @return 权限列表
     */
    @GetMapping("/tree")
    @Operation(summary = "菜单权限树")
    public R<List<BasPermissionDTO>> tree() {
        String sysCode = SaTokenUtil.getSysCode();
        List<BasPermissionDTO> res = basPermissionService.list(sysCode);
        res = TreeUtil.buildTree(res);
        return R.OK(res);
    }
}

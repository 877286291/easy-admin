package top.houyuji.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.basic.domain.dto.BasOrgDTO;
import top.houyuji.basic.domain.query.BasOrgQuery;
import top.houyuji.basic.domain.query.BasOrgTreeQuery;
import top.houyuji.basic.service.BasOrgService;
import top.houyuji.common.base.R;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.base.utils.TreeUtil;
import top.houyuji.satoken.utils.SaTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/bas/org")
@RequiredArgsConstructor
@Tag(name = "履约端：机构管理")
public class BasOrgController {
    private final BasOrgService basOrgService;

    /**
     * 机构树
     *
     * @return .
     */
    @GetMapping("/tree")
    @Operation(summary = "机构树")
    public R<List<BasOrgDTO>> tree(BasOrgTreeQuery query) {
        String sysCode = SaTokenUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasOrgDTO> res = basOrgService.list(query);
        res = TreeUtil.buildTree(res);
        return R.OK(res);
    }

    /**
     * 机构信息
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "机构列表查询")
    public R<List<BasOrgDTO>> list(BasOrgQuery query) {
        String sysCode = SaTokenUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasOrgDTO> res = basOrgService.list(query);
        return R.OK(res);
    }


    /**
     * 新增机构
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "新增机构")
    public R<String> save(@Valid @RequestBody BasOrgDTO dto) {
        String parentId = dto.getParentId();
        if (StrUtil.isBlank(parentId)) {
            return R.NG("父类ID不能为空");
        }
        String sysCode = SaTokenUtil.getSysCode();
        dto.setSysCode(sysCode);
        basOrgService.save(dto);
        return R.OK();
    }

    /**
     * 修改机构
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改机构")
    public R<String> update(@Valid @RequestBody BasOrgDTO dto) {
        String id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        String sysCode = SaTokenUtil.getSysCode();
        dto.setSysCode(sysCode);
        basOrgService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除机构
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除机构")
    public R<String> delete(String id) {
        basOrgService.deleteById(id);
        return R.OK();
    }
}

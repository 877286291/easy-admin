package top.houyuji.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.sys.domain.dto.ProductDTO;
import top.houyuji.sys.domain.query.ProductQuery;
import top.houyuji.sys.service.SysProductService;

import java.util.List;


@Tag(name = "管理端：产品管理")
@RestController
@RequestMapping("/sys/product")
@RequiredArgsConstructor
public class ProductController {
    private final SysProductService productService;

    /**
     * 根据code查询
     *
     * @param code code
     * @param id   需要排除的id
     * @return .
     */
    @Operation(summary = "根据code查询")
    @GetMapping("/existsByCode")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "code", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam String code, @RequestParam(required = false) String id) {
        Boolean res = productService.existsCode(code, id);
        return R.OK(res);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return .
     */
    @Operation(summary = "分页查询")
    @GetMapping
    public R<JsfPage<ProductDTO>> page(ProductQuery query) {
        JsfPage<ProductDTO> res = productService.page(query);
        return R.OK(res);
    }

    /**
     * 列表查询
     *
     * @param query 查询条件
     * @return .
     */
    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public R<List<ProductDTO>> list(ProductQuery query) {
        List<ProductDTO> res = productService.list(query);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param dto 产品信息
     * @return .
     */
    @Operation(summary = "保存")
    @PostMapping
    public R<String> save(@Valid @RequestBody ProductDTO dto) {
        productService.save(dto);
        return R.OK("保存成功");
    }

    /**
     * 更新
     *
     * @param dto 产品信息
     * @return .
     */
    @Operation(summary = "更新")
    @PutMapping
    public R<String> update(@Valid @RequestBody ProductDTO dto) {
        if (dto.getId() == null) {
            return R.NG("id不能为空");
        }
        productService.updateById(dto);
        return R.OK("更新成功");
    }

    /**
     * 删除
     *
     * @param id 产品ID
     * @return .
     */
    @Operation(summary = "删除")
    @DeleteMapping
    public R<String> delete(String id) {
        productService.deleteById(id);
        return R.OK("删除成功");
    }

    /**
     * 获取权限
     *
     * @param id 产品ID
     * @return .
     */
    @GetMapping("/permission")
    @Operation(summary = "获取权限")
    public R<List<String>> getPermission(String id) {
        return R.OK(productService.getPermissionIds(id));
    }

    /**
     * 授权
     *
     * @param id      产品ID
     * @param menuIds 菜单ID集合
     * @return .
     */
    @Operation(summary = "授权")
    @PutMapping("/grant")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "产品ID", required = true),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "菜单ID集合", required = true)
    public R<String> grant(@RequestParam(value = "id") String id, @RequestBody List<String> menuIds) {
        productService.grantMenus(id, menuIds);
        return R.OK("授权成功");
    }
}

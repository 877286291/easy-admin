package top.houyuji.basic.domain.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.base.enums.MenuTypeEnums;
import top.houyuji.common.base.utils.TreeUtil;
import top.houyuji.common.mybatis.core.domain.BaseEntity;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BasPermissionDTO extends BaseEntity implements TreeUtil.Node<BasPermissionDTO, String> {
    @Schema(description = "权限ID")
    private String id;
    @Schema(description = "父类ID")
    private String parentId;
    /**
     * 子类
     */
    @ArraySchema(schema = @Schema(description = "子类", implementation = Object.class, allOf = BasPermissionDTO.class))
    private List<BasPermissionDTO> children;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 路由名称（必须保持唯一）
     */
    @Schema(description = "路由名称（必须保持唯一）")
    private String routeName;
    /**
     * 路由重定向
     */
    @Schema(description = "路由重定向")
    private String redirect;

    /*==========================路由信息==========================*/
    /**
     * 路由组件
     */
    @Schema(description = "路由组件")
    private String component;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String title;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 是否展示
     */
    @Schema(description = "是否展示")
    private Boolean showLink;
    /*==========================meta==========================*/
    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    @Schema(description = "菜单排序，值越高排的越后（只针对顶级路由）")
    private Integer rank;
    /**
     * 是否显示父菜单
     */
    @Schema(description = "是否显示父菜单")
    private Boolean showParent;
    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存")
    private Boolean keepAlive;
    /**
     * 需要内嵌的iframe链接地址
     */
    @Schema(description = "需要内嵌的iframe链接地址")
    private String frameSrc;
    /**
     * 菜单类型 1 菜单 2 iframe 3 外链 4 按钮
     */
    @Schema(description = "菜单类型 1 菜单 2 iframe 3 外链 4 按钮")
    private Integer menuType;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permission;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<BasPermissionDTO> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<BasPermissionDTO> children) {
        this.children = children;
    }

    /**
     * 菜单类型名称
     */
    @Schema(description = "菜单类型名称")
    public String getMenuTypeName() {
        MenuTypeEnums typeEnums = MenuTypeEnums.of(menuType);
        return typeEnums == null ? "" : typeEnums.getName();
    }

}

package top.houyuji.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.mybatis.core.domain.BaseEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bas_permission")
public class SysTenantPermission extends BaseEntity {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 父级id
     */
    private String parentId;
    /*==========================路由信息==========================*/
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由名称（必须保持唯一）
     */
    private String routeName;
    /**
     * 路由重定向
     */
    private String redirect;
    /**
     * 路由组件
     */
    private String component;
    /*==========================meta==========================*/
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否展示
     */
    private Boolean showLink = true;
    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    @TableField("`rank`")
    private Integer rank = 99;
    /**
     * 是否显示父菜单
     */
    private Boolean showParent = true;
    /**
     * 是否缓存
     */
    private Boolean keepAlive = false;
    /**
     * 需要内嵌的iframe链接地址
     */
    private String frameSrc;
    /**
     * 菜单类型 0 菜单 1 iframe 2 外链 3 按钮
     */
    private Integer menuType;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 是否启用
     */
    @TableField("`is_enabled`")
    private Boolean enabled;
}

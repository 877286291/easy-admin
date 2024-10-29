package top.houyuji.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import top.houyuji.common.mybatis.tenant.core.BaseTenantEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName("bas_role")
public class BasRole extends BaseTenantEntity {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 机构id
     */
    private String orgId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否系统
     */
    private Boolean isSystem = false;

    @TableField(exist = false)
    private Boolean system = isSystem;
    /**
     * 是否启用
     */
    @TableField("`is_enabled`")
    private Boolean enabled = true;

    public void setSystem(Boolean system) {
        this.system = system;
        this.isSystem = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        this.system = isSystem;
    }
}

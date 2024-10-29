package top.houyuji.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@TableName("bas_role_permission")
@AllArgsConstructor
@NoArgsConstructor
public class BasRolePermission implements Serializable {
    @TableId
    private String roleId;
    @TableId
    private String permissionId;
}

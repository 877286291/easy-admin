package top.houyuji.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("bas_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class BasUserRole implements Serializable {
    @TableId
    private String userId;
    @TableId
    private String roleId;
}

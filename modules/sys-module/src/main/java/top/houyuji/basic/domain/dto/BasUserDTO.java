package top.houyuji.basic.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.houyuji.common.mybatis.tenant.core.BaseTenantDTO;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasUserDTO extends BaseTenantDTO {
    @Schema(description = "id")
    private String id;
    /**
     * 机构
     */
    @Schema(description = "所属机构")
    private OrgSmallDTO org;
//    /**
//     * 角色
//     */
//    @Schema(description = "角色")
//    private List<RoleSmallDTO> roles;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码", hidden = true)
    @JsonIgnore
    private String password;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
    /**
     * 性别,0 保密,1 男,2 女
     */
    @Schema(description = "性别,0 保密,1 男,2 女")
    private Integer gender = 0;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    /**
     * 密码重置时间
     */
    @Schema(description = "密码重置时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwdResetTime;
    /**
     * 是否系统用户
     */
    @Schema(description = "是否系统用户")
    private Boolean system;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean enabled;
}

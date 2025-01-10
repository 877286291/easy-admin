package top.houyuji.basic.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.houyuji.common.base.annotation.ValidNumber;
import top.houyuji.common.mybatis.core.domain.BaseEntity;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasUserSaveDTO extends BaseEntity {
    @Schema(description = "用户id")
    private String id;
    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "不能为空", groups = {AddGroup.class})
    private String password;
    @Schema(description = "昵称")
    @NotBlank(message = "不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String nickname;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "邮箱", example = "example@example.com")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "性别,0 保密,1 男,2 女", example = "0")
    @ValidNumber(values = {0, 1, 2}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer gender;
    @Schema(description = "状态")
    private Boolean enabled;
    @Schema(description = "机构id")
    private String orgId;
    @Schema(description = "角色id")
    private List<String> roleIds;
    @Schema(description = "系统编码", hidden = true)
    private String sysCode;

    public interface AddGroup {
    }

    public interface UpdateGroup {
    }
}

package top.houyuji.basic.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.houyuji.common.api.BaseQuery;
import top.houyuji.common.query.annotation.Equals;
import top.houyuji.common.query.annotation.Like;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasUserQuery extends BaseQuery {
    /**
     * 商户
     */
    @Schema(description = "商户", hidden = true)
    @Equals
    private String sysCode;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Equals
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @Like
    private String nickname;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Equals
    private String phone;
    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;

    /**
     * 机构ID
     */
    @Schema(description = "机构id")
    @Equals
    private String orgId;
}

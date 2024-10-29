package top.houyuji.sys.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.api.BaseQuery;
import top.houyuji.common.query.annotation.Equals;
import top.houyuji.common.query.annotation.Like;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantQuery extends BaseQuery {
    /**
     * 是否启用
     */
    @Equals
    @Schema(description = "是否启用")
    public Boolean enabled;
    /**
     * 厂商名称
     */
    @Like
    @Schema(description = "厂商名称")
    private String name;
    /**
     * 厂商代码
     */
    @Equals
    @Schema(description = "厂商代码")
    private String sysCode;
    /**
     * 机构类型
     * 1 厂商
     */
    @Schema(hidden = true)
    @Equals
    private Integer type = 1;
}

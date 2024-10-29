package top.houyuji.basic.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.api.BaseQuery;
import top.houyuji.common.query.annotation.Equals;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BasNoticeQuery extends BaseQuery {
    @Schema(hidden = true)
    private String userId;
    /**
     * 商户ID
     */
    @Equals(allowNull = true)
    @Schema(hidden = true)
    private String orgId;


    @Equals
    @Schema(hidden = true)
    private Boolean enabled;
}

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
public class QuartzJobQuery extends BaseQuery {
    @Like
    @Schema(description = "任务名称")
    private String jobName;

    @Equals
    @Schema(description = "任务分组/商户识别码")
    private String sysCode;
    @Equals
    @Schema(description = "是否启用")
    private Boolean enabled;
}

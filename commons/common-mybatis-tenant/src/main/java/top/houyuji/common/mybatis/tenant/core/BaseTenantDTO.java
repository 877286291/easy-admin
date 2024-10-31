package top.houyuji.common.mybatis.tenant.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.mybatis.core.domain.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
public class BaseTenantDTO extends BaseEntity {

    @Schema(description = "租户ID")
    private String sysCode;
}

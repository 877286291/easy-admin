package top.houyuji.common.mybatis.tenant.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.api.domain.BaseDTO;

@Getter
@Setter
@Accessors(chain = true)
public class BaseTenantDTO extends BaseDTO {

    @Schema(description = "租户ID")
    private String sysCode;
}

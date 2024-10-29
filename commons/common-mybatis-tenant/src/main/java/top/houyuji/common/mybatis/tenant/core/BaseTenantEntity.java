package top.houyuji.common.mybatis.tenant.core;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.houyuji.common.mybatis.core.domain.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
public class BaseTenantEntity extends BaseEntity {

    /**
     * 租户ID
     */
    @NotBlank(message = "租户代码不能为空")
    private String sysCode;
}

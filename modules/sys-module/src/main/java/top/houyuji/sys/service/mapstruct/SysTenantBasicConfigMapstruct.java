package top.houyuji.sys.service.mapstruct;

import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.TenantBasicConfigDTO;
import top.houyuji.sys.domain.entity.SysTenant;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysTenantBasicConfigMapstruct extends BaseMapstruct<TenantBasicConfigDTO, SysTenant> {
}

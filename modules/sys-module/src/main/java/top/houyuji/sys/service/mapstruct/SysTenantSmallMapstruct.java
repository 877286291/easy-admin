package top.houyuji.sys.service.mapstruct;

import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.TenantSmallDTO;
import top.houyuji.sys.domain.entity.SysTenant;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysTenantSmallMapstruct extends BaseMapstruct<TenantSmallDTO, SysTenant> {
}

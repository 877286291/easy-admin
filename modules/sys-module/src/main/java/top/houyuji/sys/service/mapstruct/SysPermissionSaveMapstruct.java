package top.houyuji.sys.service.mapstruct;

import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.PermissionSaveDTO;
import top.houyuji.sys.domain.entity.SysPermission;
import top.houyuji.sys.domain.entity.SysTenantPermission;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysPermissionSaveMapstruct extends BaseMapstruct<PermissionSaveDTO, SysPermission> {

    /**
     * 转换
     *
     * @param dto .
     * @return .
     */
    SysTenantPermission toTenant(PermissionSaveDTO dto);
}

package top.houyuji.sys.service.mapstruct;

import org.mapstruct.Mapper;
import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.PermissionDTO;
import top.houyuji.sys.domain.entity.SysPermission;
import top.houyuji.sys.domain.entity.SysTenantPermission;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysPermissionMapstruct extends BaseMapstruct<PermissionDTO, SysPermission> {

    List<PermissionDTO> tenantToDTOList(List<SysTenantPermission> permissions);
}

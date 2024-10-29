package top.houyuji.basic.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import top.houyuji.basic.domain.dto.BasRoleDTO;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.common.base.mapstruct.BaseMapstruct;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasRoleMapstruct extends BaseMapstruct<BasRoleDTO, BasRole> {
}

package top.houyuji.basic.service.mapstruct;

import top.houyuji.basic.domain.dto.BasOrgDTO;
import top.houyuji.basic.domain.entity.BasOrg;
import top.houyuji.common.base.mapstruct.BaseMapstruct;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasOrgMapstruct extends BaseMapstruct<BasOrgDTO, BasOrg> {
}

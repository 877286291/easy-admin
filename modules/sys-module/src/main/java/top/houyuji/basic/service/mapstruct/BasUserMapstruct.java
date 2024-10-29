package top.houyuji.basic.service.mapstruct;

import top.houyuji.basic.domain.dto.BasUserDTO;
import top.houyuji.basic.domain.dto.BasUserSaveDTO;
import top.houyuji.basic.domain.entity.BasUser;
import top.houyuji.common.base.mapstruct.BaseMapstruct;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasUserMapstruct extends BaseMapstruct<BasUserDTO, BasUser> {

    BasUser toEntity(BasUserSaveDTO dto);
}

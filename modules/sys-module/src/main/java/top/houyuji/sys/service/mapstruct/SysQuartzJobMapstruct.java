package top.houyuji.sys.service.mapstruct;

import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.QuartzJobDTO;
import top.houyuji.sys.domain.entity.SysQuartzJob;


@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysQuartzJobMapstruct extends BaseMapstruct<QuartzJobDTO, SysQuartzJob> {
}

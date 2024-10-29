package top.houyuji.basic.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import top.houyuji.basic.domain.dto.BasNoticeDTO;
import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.entity.SysNotice;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasNoticeMapstruct extends BaseMapstruct<BasNoticeDTO, SysNotice> {
}

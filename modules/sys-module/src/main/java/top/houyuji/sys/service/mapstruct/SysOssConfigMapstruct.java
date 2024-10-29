package top.houyuji.sys.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.OssConfigDTO;
import top.houyuji.sys.domain.entity.SysOssConfig;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysOssConfigMapstruct extends BaseMapstruct<OssConfigDTO, SysOssConfig> {
}

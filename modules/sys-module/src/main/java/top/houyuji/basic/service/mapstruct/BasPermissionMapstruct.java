package top.houyuji.basic.service.mapstruct;

import org.mapstruct.Mapper;
import top.houyuji.basic.domain.dto.BasPermissionDTO;
import top.houyuji.basic.domain.entity.BasPermission;
import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.PermissionDTO;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasPermissionMapstruct extends BaseMapstruct<BasPermissionDTO, BasPermission> {
    /**
     * 转换
     *
     * @param permissions .
     * @return .
     */
    List<PermissionDTO> toPermissionDTOList(List<BasPermission> permissions);
}

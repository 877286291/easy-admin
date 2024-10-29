package top.houyuji.satoken.controller.mapstruct;

import org.mapstruct.Mapper;
import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.satoken.domain.dto.UserInfoDTO;
import top.houyuji.satoken.domain.vo.LoginInfoVo;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LoginInfoMapstruct extends BaseMapstruct<LoginInfoVo, UserInfoDTO> {

}
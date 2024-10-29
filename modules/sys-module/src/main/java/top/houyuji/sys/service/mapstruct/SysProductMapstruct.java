package top.houyuji.sys.service.mapstruct;

import top.houyuji.common.base.mapstruct.BaseMapstruct;
import top.houyuji.sys.domain.dto.ProductDTO;
import top.houyuji.sys.domain.entity.SysProduct;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysProductMapstruct extends BaseMapstruct<ProductDTO, SysProduct> {
}

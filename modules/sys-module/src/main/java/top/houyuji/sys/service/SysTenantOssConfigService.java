package top.houyuji.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.mybatis.core.service.BaseService;
import top.houyuji.sys.domain.dto.OssConfigDTO;
import top.houyuji.sys.domain.entity.SysOssConfig;
import top.houyuji.sys.mapper.SysOssConfigMapper;
import top.houyuji.sys.service.mapstruct.SysOssConfigMapstruct;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysTenantOssConfigService extends BaseService<SysOssConfigMapper, SysOssConfig> {
    private final SysOssConfigMapstruct mapstruct;

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return SysOssConfig
     */
    public OssConfigDTO findBySysCode(String sysCode) {
        SysOssConfig sysOssConfig = baseMapper.findBySysCode(sysCode);
        return mapstruct.toDTO(sysOssConfig);
    }

    /**
     * 保存
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(OssConfigDTO dto) {
        SysOssConfig sysOssConfig = mapstruct.toEntity(dto);
        SysOssConfig entity = getById(dto.getId());
        if (StrUtil.isBlank(dto.getId())) {
            entity = sysOssConfig;
        } else {
            BeanUtil.copyProperties(sysOssConfig, entity, CopyOptions.create().setIgnoreNullValue(true));
        }
        saveOrUpdate(entity);
    }
}

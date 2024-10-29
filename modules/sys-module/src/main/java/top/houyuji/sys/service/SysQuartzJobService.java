package top.houyuji.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.mybatis.core.service.BaseService;
import top.houyuji.common.query.mybatis.plus.QueryHelper;
import top.houyuji.sys.domain.dto.QuartzJobDTO;
import top.houyuji.sys.domain.entity.SysQuartzJob;
import top.houyuji.sys.domain.query.QuartzJobQuery;
import top.houyuji.sys.mapper.SysQuartzJobMapper;
import top.houyuji.sys.service.mapstruct.SysQuartzJobMapstruct;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysQuartzJobService extends BaseService<SysQuartzJobMapper, SysQuartzJob> {
    private final SysQuartzJobMapstruct mapstruct;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<QuartzJobDTO> page(QuartzJobQuery query) {
        QueryWrapper<SysQuartzJob> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysQuartzJob> page = QueryHelper.toPage(query);
        page = baseMapper.selectPage(page, queryWrapper);
        List<QuartzJobDTO> res = mapstruct.toDTOList(page.getRecords());
        return QueryHelper.toJsfPage(page, res);
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 数据
     */
    public QuartzJobDTO getById(String id) {
        SysQuartzJob sysQuartzJob = baseMapper.selectById(id);
        return mapstruct.toDTO(sysQuartzJob);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(QuartzJobDTO dto) {
        SysQuartzJob sysQuartzJob = mapstruct.toEntity(dto);
        baseMapper.insert(sysQuartzJob);
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJobDTO dto) {
        SysQuartzJob entity = baseMapper.selectById(dto.getId());
        SysQuartzJob sysQuartzJob = mapstruct.toEntity(dto);
        BeanUtil.copyProperties(sysQuartzJob, entity, CopyOptions.create().setIgnoreNullValue(true));
        baseMapper.updateById(entity);
    }

    /**
     * 删除
     *
     * @param ids id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        baseMapper.deleteByIds(ids);
    }
}

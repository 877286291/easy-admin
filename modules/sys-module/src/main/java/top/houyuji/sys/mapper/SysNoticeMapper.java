package top.houyuji.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.houyuji.sys.domain.entity.SysNotice;

@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
}

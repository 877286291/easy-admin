package top.houyuji.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.houyuji.sys.domain.entity.SysOssConfig;

@Mapper
public interface SysOssConfigMapper extends BaseMapper<SysOssConfig> {

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return SysOssConfig
     */
    @Select("select * from sys_oss_config where sys_code = #{sysCode} limit 1")
    SysOssConfig findBySysCode(String sysCode);
}

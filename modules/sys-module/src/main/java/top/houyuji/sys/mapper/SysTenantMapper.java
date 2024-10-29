package top.houyuji.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.houyuji.sys.domain.dto.TenantDTO;
import top.houyuji.sys.domain.entity.SysTenant;
import top.houyuji.sys.domain.query.TenantQuery;

import java.util.List;

@Mapper
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的id
     * @return 是否存在
     */
    @Select("select count(1) from `bas_organization` where sys_code = #{code} and id != #{id} and is_system=true")
    boolean existsCodeByIdNot(String code, String id);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @return 是否存在
     */
    @Select("select count(1) from `bas_organization` where sys_code = #{code} and is_system=true")
    boolean existsCode(String code);


    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<TenantDTO> list(IPage<TenantDTO> page, @Param("query") TenantQuery query);

}

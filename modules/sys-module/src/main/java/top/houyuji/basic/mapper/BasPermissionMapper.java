package top.houyuji.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.houyuji.basic.domain.entity.BasPermission;

import java.util.List;

@Mapper
public interface BasPermissionMapper extends BaseMapper<BasPermission> {

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    List<BasPermission> listByRoleIds(List<String> roleIds);

    /**
     * 根据产品ID获取权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    @Select("SELECT p.* from bas_permission p,sys_product_permission pp WHERE p.id=pp.permission_id AND pp" +
            ".product_id=#{productId} ORDER BY p.`rank`")
    List<BasPermission> findByProductId(String productId);

}

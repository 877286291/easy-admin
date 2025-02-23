package top.houyuji.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.houyuji.sys.domain.entity.SysPermission;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    List<SysPermission> listByRoleIds(List<String> roleIds);


    /**
     * 是否有子节点
     *
     * @param id id
     * @return true/false
     */
    @Select("select count(1) from sys_permission where parent_id = #{id}")
    boolean hasChild(String id);

    /**
     * 是否有角色权限
     *
     * @param permissionId 权限id
     * @return true/false
     */
    @Select("select count(1) from sys_role_permission where permission_id = #{permissionId}")
    boolean existsRolePermission(String permissionId);
}

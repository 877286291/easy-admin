package top.houyuji.basic.service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.houyuji.basic.domain.dto.BasPermissionDTO;
import top.houyuji.basic.domain.entity.BasOrg;
import top.houyuji.basic.domain.entity.BasPermission;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.basic.mapper.BasPermissionMapper;
import top.houyuji.basic.service.mapstruct.BasPermissionMapstruct;
import top.houyuji.common.base.utils.CollectionUtil;
import top.houyuji.common.mybatis.core.service.BaseService;
import top.houyuji.sys.domain.dto.PermissionDTO;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasPermissionService extends BaseService<BasPermissionMapper, BasPermission> {
    private final BasPermissionMapstruct mapstruct;
    @Lazy
    @Resource
    private BasRoleService basRoleService;
    @Lazy
    @Resource
    private BasOrgService basOrgService;

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasPermission> findByRoleIds(List<String> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

    /**
     * 用户路由
     *
     * @param userId .
     * @return .
     */
    public List<PermissionDTO> userRoutes(String userId) {
        List<BasRole> roles = basRoleService.findByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, BasRole::getId);
        List<BasPermission> basPermissions = findByRoleIds(roleIds);
        return mapstruct.toPermissionDTOList(basPermissions);
    }

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasPermission> listByRoleIds(List<String> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return 权限
     */
    public List<BasPermission> listByUserId(String userId) {
        List<BasRole> roles = basRoleService.findByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, BasRole::getId);
        return findByRoleIds(roleIds);
    }

    /**
     * 根据产品ID获取权限
     *
     * @param productId 产品ID
     * @return 权限
     */
    public List<BasPermission> findByProductId(String productId) {
        return baseMapper.findByProductId(productId);
    }


    /**
     * 根据产品ID获取权限
     *
     * @param sysCode 产品ID
     * @return 权限
     */
    public List<BasPermissionDTO> list(String sysCode) {
        BasOrg org = basOrgService.getTopOrg(sysCode);
        if (org == null) {
            return null;
        }
        List<BasPermission> res = baseMapper.findByProductId(org.getProductId());
        return mapstruct.toDTOList(res);
    }


}

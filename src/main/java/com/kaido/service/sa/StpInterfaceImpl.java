package com.kaido.service.sa;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import com.kaido.constant.ResourceType;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.handler.base.SysResourceHandler;
import com.kaido.repository.db.handler.base.SysRoleHandler;
import com.kaido.repository.db.handler.base.SysRoleResourceHandler;
import com.kaido.repository.db.handler.base.SysUserRoleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/9 22:06
 * @desc
 */
@Service
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final SysRoleResourceHandler sysRoleResourceHandler;
    private final SysResourceHandler sysResourceHandler;
    private final SysUserRoleHandler sysUserRoleHandler;
    private final SysRoleHandler sysRoleHandler;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Integer userId = Integer.parseInt(loginId.toString());
        List<Integer> resourceIds = sysRoleResourceHandler.getUserRoleResourceIds(userId);
        return sysResourceHandler.getResource(resourceIds).stream().filter(resource -> StrUtil.equals(resource.getResourceType(), ResourceType.ROUTER.name()))
                .map(SysResource::getResourceCode).collect(Collectors.toList());
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Integer userId = Integer.parseInt(loginId.toString());
        List<Integer> roleIds = sysUserRoleHandler.selectRoleIdByUserId(userId);
        return sysRoleHandler.selectByRoleIds(roleIds).stream().map(SysRole::getRoleCode).collect(Collectors.toList());
    }

}

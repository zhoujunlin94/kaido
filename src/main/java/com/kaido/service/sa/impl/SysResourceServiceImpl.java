package com.kaido.service.sa.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.handler.base.SysResourceHandler;
import com.kaido.repository.db.handler.base.SysRoleResourceHandler;
import com.kaido.repository.db.handler.base.SysUserHandler;
import com.kaido.service.sa.SysResourceService;
import com.kaido.vo.sa.ResourceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/8
 * @desc
 */
@Service
@RequiredArgsConstructor
public class SysResourceServiceImpl implements SysResourceService {

    private final SysUserHandler sysUserHandler;
    private final SysRoleResourceHandler sysRoleResourceHandler;
    private final SysResourceHandler sysResourceHandler;

    @Override
    public List<ResourceVO> getUserRoleResources(Integer userId, String resourceType) {
        SysUser sysUser = sysUserHandler.selectByPrimaryKey(userId);
        if (Objects.isNull(sysUser) || !sysUser.getUserStatus()) {
            return Lists.newArrayList();
        }
        List<Integer> resourceIds = sysRoleResourceHandler.getUserRoleResourceIds(userId);
        List<SysResource> resources = sysResourceHandler.getResource(resourceIds).stream()
                .filter(resource -> StrUtil.equals(resource.getResourceType(), resourceType)).collect(Collectors.toList());
        // 目前只支持两级
        List<SysResource> rootResources = resources.stream().filter(resource -> resource.getResourceParent() == 0).collect(Collectors.toList());

        List<ResourceVO> resultRootResources = BeanUtil.copyToList(rootResources, ResourceVO.class);
        resultRootResources.forEach(root -> {
            List<SysResource> childrenResources = resources.stream().filter(resource -> resource.getResourceParent().equals(root.getId())).collect(Collectors.toList());
            root.setChildren(BeanUtil.copyToList(childrenResources, ResourceVO.class));
        });
        return resultRootResources;
    }

}

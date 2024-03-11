package com.kaido.service.sa.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.kaido.constant.ResourceType;
import com.kaido.dto.sa.SysResourceDTO;
import com.kaido.dto.sa.SysResourcePageParamDTO;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.handler.base.SysResourceHandler;
import com.kaido.repository.db.handler.base.SysRoleResourceHandler;
import com.kaido.repository.db.handler.base.SysUserHandler;
import com.kaido.service.sa.SysResourceService;
import com.kaido.vo.sa.ResourceVO;
import com.you.meet.nice.tk_mybatis.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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

    private final SysUserHandler userHandler;
    private final SysRoleResourceHandler roleResourceHandler;
    private final SysResourceHandler resourceHandler;

    @Override
    public List<ResourceVO> getUserRoleResources(Integer userId, ResourceType resourceType) {
        // 获取当前用户数据
        SysUser sysUser = userHandler.selectByPrimaryKey(userId);
        if (Objects.isNull(sysUser) || !sysUser.getUserStatus()) {
            return Lists.newArrayList();
        }
        // 获取用户角色下的所有资源
        List<Integer> resourceIds = roleResourceHandler.getUserRoleResourceIds(userId);
        if (CollUtil.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        List<SysResource> relateResources = resourceHandler.getResource(resourceIds);

        List<SysResource> allResources = new ArrayList<>();
        List<Integer> parentResourceIds = new ArrayList<>();
        // 非父资源
        for (SysResource relateResource : relateResources) {
            if (relateResource.getResourceType() == resourceType) {
                if (relateResource.getResourceParent() > 0) {
                    allResources.add(relateResource);
                    parentResourceIds.add(relateResource.getResourceParent());
                } else {
                    parentResourceIds.add(relateResource.getId());
                }
            }
        }

        // 获取所有父资源
        List<SysResource> parentResources = resourceHandler.getResource(parentResourceIds)
                .stream().filter(resource -> resource.getResourceType() == resourceType).collect(Collectors.toList());
        CollUtil.addAll(allResources, parentResources);

        List<ResourceVO> retResources = BeanUtil.copyToList(allResources, ResourceVO.class);
        return dealLevelRelation(retResources.stream().filter(item -> item.getResourceParent() == 0).collect(Collectors.toList()), retResources);
    }

    @Override
    public List<ResourceVO> getAllResources(ResourceType resourceType) {
        List<SysResource> resources = resourceHandler.selectAll().stream()
                .filter(resource -> resource.getResourceType() == resourceType).collect(Collectors.toList());
        List<ResourceVO> allResources = BeanUtil.copyToList(resources, ResourceVO.class);
        return dealLevelRelation(allResources.stream().filter(item -> item.getResourceParent() == 0).collect(Collectors.toList()), allResources);
    }

    private static List<ResourceVO> dealLevelRelation(List<ResourceVO> parentResources, List<ResourceVO> allResources) {
        parentResources.sort(Comparator.comparing(ResourceVO::getResourceOrder));
        for (ResourceVO parent : parentResources) {
            List<ResourceVO> children = allResources.stream().filter(item -> item.getResourceParent().equals(parent.getId()))
                    .sorted(Comparator.comparing(ResourceVO::getResourceOrder)).collect(Collectors.toList());
            parent.setChildren(children);
            if (CollUtil.isNotEmpty(children)) {
                dealLevelRelation(children, allResources);
            }
        }
        return parentResources;
    }

    // ====================== CRUD ====================

    @Override
    public boolean create(SysResourceDTO resourceDTO, Integer loginUserId) {
        SysResource entity = BeanUtil.toBean(resourceDTO, SysResource.class);
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        return resourceHandler.insertSelective(entity) == 1;
    }

    @Override
    public boolean updateResourceStatus(SysResourceDTO resourceDTO, Integer loginUserId) {
        SysResource entity = SysResource.builder().id(resourceDTO.getId()).resourceStatus(resourceDTO.getResourceStatus()).updatedBy(loginUserId).build();
        return resourceHandler.updateByPrimaryKeySelective(entity) == 1;
    }

    @Override
    public boolean update(SysResourceDTO resourceDTO, Integer loginUserId) {
        SysResource entity = BeanUtil.toBean(resourceDTO, SysResource.class);
        entity.setUpdatedBy(loginUserId);
        return resourceHandler.updateByPrimaryKeySelective(entity) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer resourceId) {
        if (resourceHandler.deleteByPrimaryKey(resourceId) == 1) {
            roleResourceHandler.deleteByResourceId(resourceId);
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<SysResourceDTO> page(SysResourcePageParamDTO paramDTO) {
        PageInfo<SysResource> entityPageInfo = PageHelper.startPage(paramDTO.getPageNo(), paramDTO.getPageSize())
                .doSelectPageInfo(() -> resourceHandler.selectByParam(paramDTO));
        return PageUtil.copy(entityPageInfo, () -> BeanUtil.copyToList(entityPageInfo.getList(), SysResourceDTO.class));
    }

    // ====================== CRUD ====================

}

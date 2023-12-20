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
    public List<ResourceVO> getUserRoleResources(Integer userId, ResourceType resourceType) {
        SysUser sysUser = sysUserHandler.selectByPrimaryKey(userId);
        if (Objects.isNull(sysUser) || !sysUser.getUserStatus()) {
            return Lists.newArrayList();
        }
        List<Integer> resourceIds = sysRoleResourceHandler.getUserRoleResourceIds(userId);
        List<SysResource> resources = sysResourceHandler.getResource(resourceIds).stream()
                .filter(resource -> resource.getResourceType() == resourceType).collect(Collectors.toList());
        List<ResourceVO> allResources = BeanUtil.copyToList(resources, ResourceVO.class);
        return dealLevelRelation(allResources.stream().filter(item -> item.getResourceParent() == 0).collect(Collectors.toList()), allResources);
    }

    @Override
    public List<ResourceVO> getAllResources(ResourceType resourceType) {
        List<SysResource> resources = sysResourceHandler.selectAll().stream()
                .filter(resource -> resource.getResourceType() == resourceType).collect(Collectors.toList());
        List<ResourceVO> allResources = BeanUtil.copyToList(resources, ResourceVO.class);
        return dealLevelRelation(allResources.stream().filter(item -> item.getResourceParent() == 0).collect(Collectors.toList()), allResources);
    }

    private static List<ResourceVO> dealLevelRelation(List<ResourceVO> parentResources, List<ResourceVO> allResources) {
        for (ResourceVO parent : parentResources) {
            List<ResourceVO> children = allResources.stream().filter(item -> item.getResourceParent().equals(parent.getId())).collect(Collectors.toList());
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
        return sysResourceHandler.insertSelective(entity) > 0;
    }

    @Override
    public boolean updateResourceStatus(SysResourceDTO resourceDTO, Integer loginUserId) {
        SysResource entity = SysResource.builder().id(resourceDTO.getId()).resourceStatus(resourceDTO.getResourceStatus()).updatedBy(loginUserId).build();
        return sysResourceHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public boolean update(SysResourceDTO resourceDTO, Integer loginUserId) {
        SysResource entity = BeanUtil.toBean(resourceDTO, SysResource.class);
        entity.setUpdatedBy(loginUserId);
        return sysResourceHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public boolean delete(Integer resourceId) {
        return sysResourceHandler.deleteByPrimaryKey(resourceId) == 1;
    }

    @Override
    public PageInfo<SysResourceDTO> page(SysResourcePageParamDTO paramDTO) {
        PageInfo<SysResource> entityPageInfo = PageHelper.startPage(paramDTO.getPageNo(), paramDTO.getPageSize())
                .doSelectPageInfo(() -> sysResourceHandler.selectByParam(paramDTO));
        PageInfo<SysResourceDTO> retPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(entityPageInfo, retPageInfo);
        retPageInfo.setList(entityPageInfo.getList().stream().map(entity -> BeanUtil.toBean(entity, SysResourceDTO.class)).collect(Collectors.toList()));
        return retPageInfo;
    }

    // ====================== CRUD ====================

}

package com.kaido.service.sa.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.kaido.dto.sa.SysRoleDTO;
import com.kaido.dto.sa.SysRolePageParamDTO;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.entity.base.SysRoleResource;
import com.kaido.repository.db.handler.base.SysResourceHandler;
import com.kaido.repository.db.handler.base.SysRoleHandler;
import com.kaido.repository.db.handler.base.SysRoleResourceHandler;
import com.kaido.service.sa.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:59
 * @desc
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleHandler roleHandler;
    private final SysResourceHandler resourceHandler;
    private final SysRoleResourceHandler roleResourceHandler;

    // ================== CRUD =======================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = BeanUtil.toBean(roleDTO, SysRole.class);
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        roleHandler.insertSelective(entity);

        List<SysRoleResource> roleResourceEntityList = roleDTO.getRoleResources().stream().map(resourceId -> SysRoleResource.builder()
                .roleId(entity.getId()).resourceId(resourceId).createdBy(loginUserId).updatedBy(loginUserId).build()).collect(Collectors.toList());
        roleResourceHandler.batchInsert(roleResourceEntityList);
    }

    @Override
    public boolean updateRoleStatus(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = SysRole.builder().id(roleDTO.getId()).roleStatus(roleDTO.getRoleStatus()).updatedBy(loginUserId).build();
        return roleHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = BeanUtil.toBean(roleDTO, SysRole.class);
        entity.setUpdatedBy(loginUserId);
        roleHandler.updateByPrimaryKeySelective(entity);

        List<SysRoleResource> paramRoleResourceList = roleDTO.getRoleResources().stream().map(resourceId -> SysRoleResource.builder().roleId(entity.getId()).resourceId(resourceId)
                .createdBy(loginUserId).updatedBy(loginUserId).build()).collect(Collectors.toList());
        List<SysRoleResource> dbRoleResourceList = roleResourceHandler.getRoleResourceByRoleId(entity.getId());

        // 数据库存在 入参不存在  删除
        List<Integer> deleteIds = dbRoleResourceList.stream().filter(dbEntity -> !paramRoleResourceList.contains(dbEntity)).map(SysRoleResource::getId).collect(Collectors.toList());
        roleResourceHandler.deleteByIds(deleteIds);

        // 入参中存在  数据不出来  新增
        List<SysRoleResource> insertList = paramRoleResourceList.stream().filter(paramEntity -> !dbRoleResourceList.contains(paramEntity)).collect(Collectors.toList());
        roleResourceHandler.batchInsert(insertList);

    }

    @Override
    public boolean delete(Integer roleId) {
        return roleHandler.deleteByPrimaryKey(roleId) == 1;
    }

    @Override
    public PageInfo<SysRoleDTO> page(SysRolePageParamDTO pageParamDTO) {
        // 分页获取角色
        PageInfo<SysRole> entityPageInfo = PageHelper.startPage(pageParamDTO.getPageNo(), pageParamDTO.getPageSize())
                .doSelectPageInfo(() -> roleHandler.selectByParam(pageParamDTO));
        PageInfo<SysRoleDTO> retPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(entityPageInfo, retPageInfo);

        // 获取角色对应资源
        List<Integer> roleIds = entityPageInfo.getList().stream().map(SysRole::getId).collect(Collectors.toList());
        List<SysRoleResource> roleResources = roleResourceHandler.getRoleResourceByRoleId(roleIds);
        Map<Integer, List<Integer>> roleResourceIdMap = roleResources.stream().collect(Collectors.groupingBy(SysRoleResource::getRoleId,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList()))));
        List<Integer> resourceIds = roleResources.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList());
        Map<Integer, SysResource> resourceMap = resourceHandler.getResource(resourceIds).stream().collect(Collectors.toMap(SysResource::getId, Function.identity()));

        retPageInfo.setList(entityPageInfo.getList().stream().map(role -> {
            SysRoleDTO ret = BeanUtil.toBean(role, SysRoleDTO.class);
            List<Integer> thisResourceIds = roleResourceIdMap.getOrDefault(role.getId(), Lists.newArrayList());
            List<Integer> retResourceIds = new ArrayList<>();
            for (Integer thisResourceId : thisResourceIds) {
                if (resourceMap.containsKey(thisResourceId)) {
                    retResourceIds.add(thisResourceId);
                }
            }
            ret.setRoleResources(retResourceIds);
            return ret;
        }).collect(Collectors.toList()));
        return retPageInfo;
    }

    // ================== CRUD =======================

}

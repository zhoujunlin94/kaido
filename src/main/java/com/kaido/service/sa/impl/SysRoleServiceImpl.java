package com.kaido.service.sa.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaido.dto.sa.SysRoleDTO;
import com.kaido.dto.sa.SysRolePageParamDTO;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.handler.base.SysRoleHandler;
import com.kaido.service.sa.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:59
 * @desc
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleHandler sysRoleHandler;

    // ================== CRUD =======================

    public boolean create(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = BeanUtil.toBean(roleDTO, SysRole.class);
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        return sysRoleHandler.insertSelective(entity) > 0;
    }

    public boolean updateRoleStatus(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = SysRole.builder().id(roleDTO.getId()).roleStatus(roleDTO.getRoleStatus()).updatedBy(loginUserId).build();
        return sysRoleHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    public boolean update(SysRoleDTO roleDTO, Integer loginUserId) {
        SysRole entity = BeanUtil.toBean(roleDTO, SysRole.class);
        entity.setUpdatedBy(loginUserId);
        return sysRoleHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    public PageInfo<SysRoleDTO> page(SysRolePageParamDTO pageParamDTO) {
        PageInfo<SysRole> entityPageInfo = PageHelper.startPage(pageParamDTO.getPageNo(), pageParamDTO.getPageSize())
                .doSelectPageInfo(() -> sysRoleHandler.selectByParam(pageParamDTO));
        PageInfo<SysRoleDTO> retPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(entityPageInfo, retPageInfo);
        retPageInfo.setList(entityPageInfo.getList().stream().map(entity -> BeanUtil.toBean(entity, SysRoleDTO.class)).collect(Collectors.toList()));
        return retPageInfo;
    }

    // ================== CRUD =======================

}

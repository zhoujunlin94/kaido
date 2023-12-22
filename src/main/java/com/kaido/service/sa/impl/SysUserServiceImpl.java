package com.kaido.service.sa.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.kaido.dto.sa.LoginParamDTO;
import com.kaido.dto.sa.SysUserDTO;
import com.kaido.dto.sa.SysUserPageParamDTO;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.entity.base.SysUserRole;
import com.kaido.repository.db.handler.base.SysRoleHandler;
import com.kaido.repository.db.handler.base.SysUserHandler;
import com.kaido.repository.db.handler.base.SysUserRoleHandler;
import com.kaido.service.sa.SysUserService;
import com.you.meet.nice.common.exception.MeetException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/9 21:41
 * @desc
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserHandler userHandler;
    private final SysRoleHandler roleHandler;
    private final SysUserRoleHandler userRoleHandler;

    @Override
    public SaTokenInfo login(LoginParamDTO loginParam) {
        SysUser sysUser = userHandler.selectByAccountName(loginParam.getAccountName());
        if (Objects.isNull(sysUser)) {
            throw MeetException.meet("用户名或密码错误");
        }
        String password = DigestUtil.md5Hex(loginParam.getUserPassword());
        if (!StrUtil.equals(password, sysUser.getUserPassword())) {
            throw MeetException.meet("用户名或密码错误");
        }
        StpUtil.login(sysUser.getId());
        return StpUtil.getTokenInfo();
    }

    @Override
    public SysUserDTO getLoginUser(Integer loginUserId) {
        SysUser entity = userHandler.selectByPrimaryKey(loginUserId);
        SysUserDTO sysUserDTO = BeanUtil.toBean(entity, SysUserDTO.class);
        sysUserDTO.setUserPassword(StrUtil.EMPTY);
        return sysUserDTO;
    }

    // =================== CRUD ===================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = BeanUtil.toBean(userDTO, SysUser.class);
        entity.setUserPassword(DigestUtil.md5Hex(userDTO.getUserPassword()));
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        if (userHandler.insertSelective(entity) == 1) {
            List<SysUserRole> userRoleList = userDTO.getUserRoles().stream().map(roleId -> SysUserRole.builder().userId(entity.getId()).roleId(roleId)
                    .createdBy(loginUserId).updatedBy(loginUserId).build()).collect(Collectors.toList());
            userRoleHandler.batchInsert(userRoleList);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserStatus(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = SysUser.builder().id(userDTO.getId()).userStatus(userDTO.getUserStatus()).updatedBy(loginUserId).build();
        return userHandler.updateByPrimaryKeySelective(entity) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = BeanUtil.toBean(userDTO, SysUser.class);
        if (StrUtil.isNotBlank(userDTO.getUserPassword())) {
            entity.setUserPassword(DigestUtil.md5Hex(userDTO.getUserPassword()));
        }
        entity.setUpdatedBy(loginUserId);
        userHandler.updateByPrimaryKeySelective(entity);

        List<SysUserRole> paramUserRoleList = userDTO.getUserRoles().stream().map(roleId -> SysUserRole.builder().userId(entity.getId()).roleId(roleId)
                .createdBy(loginUserId).updatedBy(loginUserId).build()).collect(Collectors.toList());
        List<SysUserRole> dbUserRoleList = userRoleHandler.selectUserRoles(entity.getId());

        // 数据库存在  入参不存在  删除
        List<Integer> deleteIds = dbUserRoleList.stream().filter(dbEntity -> !paramUserRoleList.contains(dbEntity)).map(SysUserRole::getId).collect(Collectors.toList());
        userRoleHandler.deleteByIds(deleteIds);

        // 入参存在  数据库不存在  新增
        List<SysUserRole> insertList = paramUserRoleList.stream().filter(paramEntity -> !dbUserRoleList.contains(paramEntity)).collect(Collectors.toList());
        userRoleHandler.batchInsert(insertList);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer userId) {
        if (userHandler.deleteByPrimaryKey(userId) == 1) {
            userRoleHandler.deleteByUserId(userId);
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<SysUserDTO> page(SysUserPageParamDTO paramDTO) {
        // 分页
        PageInfo<SysUser> entityPageInfo = PageHelper.startPage(paramDTO.getPageNo(), paramDTO.getPageSize())
                .doSelectPageInfo(() -> userHandler.selectByParam(paramDTO));
        PageInfo<SysUserDTO> retPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(entityPageInfo, retPageInfo);

        // 获取用户下的角色
        List<Integer> userIds = entityPageInfo.getList().stream().map(SysUser::getId).collect(Collectors.toList());
        List<SysUserRole> userRoles = userRoleHandler.selectUserRoles(userIds);
        Map<Integer, List<Integer>> userRoleIdsMap = userRoles.stream().collect(Collectors.groupingBy(SysUserRole::getUserId, Collectors.collectingAndThen(Collectors.toList(),
                list -> list.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()))));
        List<Integer> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        Map<Integer, SysRole> roleMap = roleHandler.selectByRoleIds(roleIds).stream().collect(Collectors.toMap(SysRole::getId, Function.identity()));

        retPageInfo.setList(entityPageInfo.getList().stream().map(user -> {
            SysUserDTO ret = BeanUtil.toBean(user, SysUserDTO.class);
            List<Integer> userRoleIds = userRoleIdsMap.getOrDefault(user.getId(), Lists.newArrayList());

            List<Integer> thisUserRoleIds = new ArrayList<>();
            for (Integer thisRoleId : userRoleIds) {
                if (roleMap.containsKey(thisRoleId)) {
                    thisUserRoleIds.add(thisRoleId);
                }
            }
            ret.setUserRoles(thisUserRoleIds);

            return ret;
        }).collect(Collectors.toList()));
        return retPageInfo;
    }

    // =================== CRUD ===================


}

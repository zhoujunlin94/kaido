package com.kaido.service.sa.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaido.dto.sa.LoginParamDTO;
import com.kaido.dto.sa.SysUserDTO;
import com.kaido.dto.sa.SysUserPageParamDTO;
import com.kaido.internal.exception.MeetException;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.handler.base.SysUserHandler;
import com.kaido.service.sa.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023/11/9 21:41
 * @desc
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserHandler sysUserHandler;

    @Override
    public SaTokenInfo login(LoginParamDTO loginParam) {
        SysUser sysUser = sysUserHandler.selectByAccountName(loginParam.getAccountName());
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

    // =================== CRUD ===================

    @Override
    public boolean create(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = BeanUtil.toBean(userDTO, SysUser.class);
        entity.setUserPassword(DigestUtil.md5Hex(userDTO.getUserPassword()));
        entity.setCreatedBy(loginUserId);
        entity.setUpdatedBy(loginUserId);
        return sysUserHandler.insertSelective(entity) > 0;
    }

    @Override
    public boolean updateUserStatus(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = SysUser.builder().id(userDTO.getId()).userStatus(userDTO.getUserStatus()).updatedBy(loginUserId).build();
        return sysUserHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public boolean update(SysUserDTO userDTO, Integer loginUserId) {
        SysUser entity = BeanUtil.toBean(userDTO, SysUser.class);
        entity.setUserPassword(DigestUtil.md5Hex(userDTO.getUserPassword()));
        entity.setUpdatedBy(loginUserId);
        return sysUserHandler.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public PageInfo<SysUserDTO> page(SysUserPageParamDTO paramDTO) {
        PageInfo<SysUser> entityPageInfo = PageHelper.startPage(paramDTO.getPageNo(), paramDTO.getPageSize())
                .doSelectPageInfo(() -> sysUserHandler.selectByParam(paramDTO));
        PageInfo<SysUserDTO> retPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(entityPageInfo, retPageInfo);
        retPageInfo.setList(entityPageInfo.getList().stream().map(entity -> BeanUtil.toBean(entity, SysUserDTO.class)).collect(Collectors.toList()));
        return retPageInfo;
    }

    // =================== CRUD ===================


}

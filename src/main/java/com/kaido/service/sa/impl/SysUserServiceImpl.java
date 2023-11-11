package com.kaido.service.sa.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.kaido.dto.sa.LoginParamDTO;
import com.kaido.internal.exception.MeetException;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.handler.base.SysUserHandler;
import com.kaido.service.sa.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public void logout() {
        StpUtil.logout();
    }

}

package com.kaido.service.sa;

import com.kaido.dto.sa.LoginParamDTO;

/**
 * @author zhoujunlin
 * @date 2023/11/9
 * @desc
 */
public interface SysUserService {

    boolean login(LoginParamDTO loginParam);

    void logout();

}

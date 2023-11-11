package com.kaido.service.sa;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.kaido.dto.sa.LoginParamDTO;

/**
 * @author zhoujunlin
 * @date 2023/11/9
 * @desc
 */
public interface SysUserService {

    SaTokenInfo login(LoginParamDTO loginParam);

    void logout();

}

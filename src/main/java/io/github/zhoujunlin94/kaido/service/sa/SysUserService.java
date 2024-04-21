package io.github.zhoujunlin94.kaido.service.sa;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.sa.LoginParamDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysUserDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysUserPageParamDTO;

/**
 * @author zhoujunlin
 * @date 2023/11/9
 * @desc
 */
public interface SysUserService {

    SaTokenInfo login(LoginParamDTO loginParam);

    SysUserDTO getLoginUser(Integer loginUserId);

    boolean create(SysUserDTO userDTO, Integer loginUserId);

    boolean updateUserStatus(SysUserDTO userDTO, Integer loginUserId);

    boolean update(SysUserDTO userDTO, Integer loginUserId);

    boolean delete(Integer userId);

    PageInfo<SysUserDTO> page(SysUserPageParamDTO paramDTO);

}

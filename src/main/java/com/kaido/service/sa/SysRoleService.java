package com.kaido.service.sa;

import com.github.pagehelper.PageInfo;
import com.kaido.dto.sa.SysRoleDTO;
import com.kaido.dto.sa.SysRolePageParamDTO;

/**
 * @author zhoujunlin
 * @date 2023/11/13
 * @desc
 */
public interface SysRoleService {

    void create(SysRoleDTO roleDTO, Integer loginUserId);

    boolean updateRoleStatus(SysRoleDTO roleDTO, Integer loginUserId);

    void update(SysRoleDTO roleDTO, Integer loginUserId);

    PageInfo<SysRoleDTO> page(SysRolePageParamDTO pageParamDTO);

}

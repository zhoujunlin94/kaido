package io.github.zhoujunlin94.kaido.service.sa;

import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.sa.SysRoleDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysRolePageParamDTO;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/13
 * @desc
 */
public interface SysRoleService {

    boolean create(SysRoleDTO roleDTO, Integer loginUserId);

    boolean updateRoleStatus(SysRoleDTO roleDTO, Integer loginUserId);

    boolean update(SysRoleDTO roleDTO, Integer loginUserId);

    boolean delete(Integer roleId);

    PageInfo<SysRoleDTO> page(SysRolePageParamDTO pageParamDTO);

    List<SysRoleDTO> getAllRole();

}

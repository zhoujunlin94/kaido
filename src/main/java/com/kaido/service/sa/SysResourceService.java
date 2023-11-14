package com.kaido.service.sa;

import com.github.pagehelper.PageInfo;
import com.kaido.constant.ResourceType;
import com.kaido.dto.sa.SysResourceDTO;
import com.kaido.dto.sa.SysResourcePageParamDTO;
import com.kaido.vo.sa.ResourceVO;

import java.util.List;

/**
 * @author zhoujunlin
 */
public interface SysResourceService {

    List<ResourceVO> getUserRoleResources(Integer userId, ResourceType resourceType);

    boolean create(SysResourceDTO resourceDTO, Integer loginUserId);

    boolean updateResourceStatus(SysResourceDTO resourceDTO, Integer loginUserId);

    boolean update(SysResourceDTO resourceDTO, Integer loginUserId);

    PageInfo<SysResourceDTO> page(SysResourcePageParamDTO paramDTO);

}

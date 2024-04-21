package io.github.zhoujunlin94.kaido.service.sa;

import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.github.zhoujunlin94.kaido.dto.sa.SysResourceDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysResourcePageParamDTO;
import io.github.zhoujunlin94.kaido.vo.sa.ResourceVO;

import java.util.List;

/**
 * @author zhoujunlin
 */
public interface SysResourceService {

    List<ResourceVO> getUserRoleResources(Integer userId, ResourceType resourceType);

    List<ResourceVO> getAllResources(ResourceType resourceType);

    boolean create(SysResourceDTO resourceDTO, Integer loginUserId);

    boolean updateResourceStatus(SysResourceDTO resourceDTO, Integer loginUserId);

    boolean update(SysResourceDTO resourceDTO, Integer loginUserId);

    boolean delete(Integer resourceId);

    PageInfo<SysResourceDTO> page(SysResourcePageParamDTO paramDTO);

}

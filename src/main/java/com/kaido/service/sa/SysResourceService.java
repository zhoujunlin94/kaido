package com.kaido.service.sa;

import com.kaido.vo.sa.ResourceVO;

import java.util.List;

/**
 * @author zhoujunlin
 */
public interface SysResourceService {

    List<ResourceVO> getUserRoleResources(Integer userId, String resourceType);

}

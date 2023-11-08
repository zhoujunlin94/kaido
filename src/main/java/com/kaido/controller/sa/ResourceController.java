package com.kaido.controller.sa;

import com.kaido.service.sa.SysResourceService;
import com.kaido.vo.sa.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/8 22:03
 * @desc
 */
@Validated
@RestController
@Api(tags = {"B-sa资源相关"})
@RequiredArgsConstructor
@RequestMapping("/api/sa/resource")
public class ResourceController {

    private final SysResourceService sysResourceService;

    @GetMapping("/getUserRoleResources")
    @ApiOperation(value = "获取用户角色下的所有资源")
    public List<ResourceVO> getUserRoleResources(String resourceType) {
        return sysResourceService.getUserRoleResources(1, resourceType);
    }

}

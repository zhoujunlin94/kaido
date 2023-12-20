package com.kaido.controller.sa;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.kaido.constant.ResourceType;
import com.kaido.dto.sa.SysResourceDTO;
import com.kaido.dto.sa.SysResourcePageParamDTO;
import com.kaido.service.sa.SysResourceService;
import com.kaido.vo.sa.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public List<ResourceVO> getUserRoleResources(@RequestParam @NotNull(message = "资源类型不可为空") ResourceType resourceType) {
        return sysResourceService.getUserRoleResources(StpUtil.getLoginIdAsInt(), resourceType);
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建")
    public boolean create(@RequestBody @Valid SysResourceDTO resourceDTO) {
        resourceDTO.initCreate();
        return sysResourceService.create(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/updateResourceStatus")
    @ApiOperation(value = "修改资源状态")
    public boolean updateResourceStatus(@RequestBody SysResourceDTO resourceDTO) {
        Assert.notNull(resourceDTO.getId(), "主键不可为空");
        Assert.notNull(resourceDTO.getResourceStatus(), "资源状态不可为空");
        return sysResourceService.updateResourceStatus(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public boolean update(@RequestBody @Valid SysResourceDTO resourceDTO) {
        resourceDTO.initAndCheck4Update();
        return sysResourceService.update(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer resourceId) {
        return sysResourceService.delete(resourceId);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页")
    public PageInfo<SysResourceDTO> page(@RequestBody SysResourcePageParamDTO paramDTO) {
        return sysResourceService.page(paramDTO);
    }

}

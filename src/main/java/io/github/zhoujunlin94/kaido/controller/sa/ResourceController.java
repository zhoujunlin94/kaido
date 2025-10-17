package io.github.zhoujunlin94.kaido.controller.sa;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.github.zhoujunlin94.kaido.dto.sa.SysResourceDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysResourcePageParamDTO;
import io.github.zhoujunlin94.kaido.service.sa.SysResourceService;
import io.github.zhoujunlin94.kaido.vo.sa.ResourceVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/8 22:03
 * @desc
 */
@Validated
@RestController
@Tag(name = "B-sa资源相关")
@RequiredArgsConstructor
@RequestMapping("/api/sa/resource")
public class ResourceController {

    private final SysResourceService sysResourceService;

    @GetMapping("/getUserRoleResources")
    @Operation(summary = "获取用户角色下的所有资源")
    public List<ResourceVO> getUserRoleResources(@RequestParam @NotNull(message = "资源类型不可为空") ResourceType resourceType) {
        return sysResourceService.getUserRoleResources(StpUtil.getLoginIdAsInt(), resourceType);
    }

    @GetMapping("/getAllResources")
    @Operation(summary = "获取所有资源")
    public List<ResourceVO> getAllResources(@RequestParam @NotNull(message = "资源类型不可为空") ResourceType resourceType) {
        return sysResourceService.getAllResources(resourceType);
    }

    @PostMapping("/create")
    @Operation(summary = "创建")
    public boolean create(@RequestBody @Valid SysResourceDTO resourceDTO) {
        resourceDTO.initCreate();
        return sysResourceService.create(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/updateResourceStatus")
    @Operation(summary = "修改资源状态")
    public boolean updateResourceStatus(@RequestBody SysResourceDTO resourceDTO) {
        Assert.notNull(resourceDTO.getId(), "主键不可为空");
        Assert.notNull(resourceDTO.getResourceStatus(), "资源状态不可为空");
        return sysResourceService.updateResourceStatus(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    public boolean update(@RequestBody @Valid SysResourceDTO resourceDTO) {
        resourceDTO.initAndCheck4Update();
        return sysResourceService.update(resourceDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer resourceId) {
        return sysResourceService.delete(resourceId);
    }

    @PostMapping("/page")
    @Operation(summary = "分页")
    public PageInfo<SysResourceDTO> page(@RequestBody SysResourcePageParamDTO paramDTO) {
        return sysResourceService.page(paramDTO);
    }

}

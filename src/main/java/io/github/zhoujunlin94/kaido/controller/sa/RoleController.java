package io.github.zhoujunlin94.kaido.controller.sa;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.sa.SysRoleDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysRolePageParamDTO;
import io.github.zhoujunlin94.kaido.service.sa.SysRoleService;
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
 * @date 2023/11/9 21:40
 * @desc
 */
@Validated
@RestController
@Api(tags = {"B-sa角色相关"})
@RequiredArgsConstructor
@RequestMapping("/api/sa/role")
public class RoleController {

    private final SysRoleService sysRoleService;

    @PostMapping("/create")
    @ApiOperation(value = "创建")
    public boolean create(@RequestBody @Valid SysRoleDTO roleDTO) {
        roleDTO.initCreate();
        return sysRoleService.create(roleDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/updateRoleStatus")
    @ApiOperation(value = "修改角色状态")
    public boolean updateRoleStatus(@RequestBody SysRoleDTO roleDTO) {
        Assert.notNull(roleDTO.getId(), "主键不可为空");
        Assert.notNull(roleDTO.getRoleStatus(), "角色状态不可为空");
        return sysRoleService.updateRoleStatus(roleDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public boolean update(@RequestBody @Valid SysRoleDTO roleDTO) {
        roleDTO.initAndCheck4Update();
        return sysRoleService.update(roleDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer roleId) {
        return sysRoleService.delete(roleId);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页")
    public PageInfo<SysRoleDTO> page(@RequestBody SysRolePageParamDTO paramDTO) {
        return sysRoleService.page(paramDTO);
    }

    @GetMapping("/getAllRoles")
    @ApiOperation(value = "获取所有角色")
    public List<SysRoleDTO> getAllRole() {
        return sysRoleService.getAllRole();
    }

}

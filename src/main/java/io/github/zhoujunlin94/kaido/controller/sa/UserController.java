package io.github.zhoujunlin94.kaido.controller.sa;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.sa.LoginParamDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysUserDTO;
import io.github.zhoujunlin94.kaido.dto.sa.SysUserPageParamDTO;
import io.github.zhoujunlin94.kaido.service.sa.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author zhoujunlin
 * @date 2023/11/9 21:40
 * @desc
 */
@Validated
@RestController
@Tag(name = "B-sa用户相关")
@RequiredArgsConstructor
@RequestMapping("/api/sa/user")
public class UserController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public SaTokenInfo login(@RequestBody @Valid LoginParamDTO paramDTO) {
        return sysUserService.login(paramDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "注销")
    public void logout() {
        StpUtil.logout();
    }

    @GetMapping("/getLoginUser")
    @Operation(summary = "获取登录用户信息")
    public SysUserDTO getLoginUser() {
        return sysUserService.getLoginUser(StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/create")
    @Operation(summary = "创建")
    public boolean create(@RequestBody @Valid SysUserDTO userDTO) {
        userDTO.initAndCheck4Create();
        return sysUserService.create(userDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/updateUserStatus")
    @Operation(summary = "修改用户状态")
    public boolean updateUserStatus(@RequestBody SysUserDTO userDTO) {
        Assert.notNull(userDTO.getId(), "主键不可为空");
        Assert.notNull(userDTO.getUserStatus(), "用户状态不可为空");
        return sysUserService.updateUserStatus(userDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    public boolean update(@RequestBody @Valid SysUserDTO userDTO) {
        userDTO.initAndCheck4Update();
        return sysUserService.update(userDTO, StpUtil.getLoginIdAsInt());
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer userId) {
        return sysUserService.delete(userId);
    }

    @PostMapping("/page")
    @Operation(summary = "分页")
    public PageInfo<SysUserDTO> page(@RequestBody SysUserPageParamDTO paramDTO) {
        return sysUserService.page(paramDTO);
    }

}

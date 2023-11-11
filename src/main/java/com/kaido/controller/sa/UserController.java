package com.kaido.controller.sa;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.kaido.dto.sa.LoginParamDTO;
import com.kaido.service.sa.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhoujunlin
 * @date 2023/11/9 21:40
 * @desc
 */
@Validated
@RestController
@Api(tags = {"B-sa用户相关"})
@RequiredArgsConstructor
@RequestMapping("/api/sa/user")
public class UserController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public SaTokenInfo login(@RequestBody @Valid LoginParamDTO paramDTO) {
        return sysUserService.login(paramDTO);
    }

}

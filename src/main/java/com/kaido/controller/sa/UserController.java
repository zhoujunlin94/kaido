package com.kaido.controller.sa;

import com.kaido.service.sa.SysUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

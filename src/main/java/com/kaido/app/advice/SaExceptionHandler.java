package com.kaido.app.advice;

import cn.dev33.satoken.exception.*;
import com.you.meet.nice.common.pojo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhoujunlin
 * @date 2023/11/9 22:28
 * @desc
 */
@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class SaExceptionHandler {

    /**
     * 拦截：未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public JsonResponse handlerException(NotLoginException e) {
        return JsonResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 拦截：缺少权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public JsonResponse handlerException(NotPermissionException e) {
        return JsonResponse.fail(e.getCode(), "缺少权限：" + e.getPermission());
    }

    /**
     * 拦截：缺少角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    public JsonResponse handlerException(NotRoleException e) {
        return JsonResponse.fail(e.getCode(), "缺少角色：" + e.getRole());
    }

    /**
     * 拦截：二级认证校验失败异常
     */
    @ExceptionHandler(NotSafeException.class)
    public JsonResponse handlerException(NotSafeException e) {
        return JsonResponse.fail(e.getCode(), "二级认证校验失败：" + e.getService());
    }

    /**
     * 拦截：服务封禁异常
     */
    @ExceptionHandler(DisableServiceException.class)
    public JsonResponse handlerException(DisableServiceException e) {
        return JsonResponse.fail(e.getCode(), "当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
    }

    /**
     * 拦截：Http Basic 校验失败异常
     */
    @ExceptionHandler(NotBasicAuthException.class)
    public JsonResponse handlerException(NotBasicAuthException e) {
        return JsonResponse.fail(e.getCode(), e.getMessage());
    }


}

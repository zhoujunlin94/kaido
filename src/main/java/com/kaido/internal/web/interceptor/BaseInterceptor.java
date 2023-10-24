package com.kaido.internal.web.interceptor;

import com.kaido.internal.exception.CommonErrorCode;
import com.kaido.internal.util.ServletUtils;
import com.kaido.internal.web.dto.JSONResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhoujl
 * <p>
 * 拦截器的基类
 */
public abstract class BaseInterceptor implements HandlerInterceptor {

    protected void failed(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        JSONResponse jsonResponse = JSONResponse.builder().code(CommonErrorCode.S_SYSTEM_BUSY.getCode()).msg(message).build();
        ServletUtils.writeJSON(response, jsonResponse);
    }

    protected void fail(HttpServletResponse response, String redirectUrl) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        JSONResponse jsonResponse = JSONResponse.builder().code(CommonErrorCode.S_SYSTEM_BUSY.getCode()).data(redirectUrl).build();
        ServletUtils.writeJSON(response, jsonResponse);
    }
}

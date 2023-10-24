package com.kaido.internal.util;

import com.kaido.internal.web.dto.RequestContext;

/**
 * @author zhoujunlin
 * @date 2023年03月14日 17:28
 * @desc
 */
public final class RequestContextUtil {

    private final static ThreadLocal<RequestContext> THREAD_LOCAL = new ThreadLocal<>();

    public static RequestContext get() {
        return THREAD_LOCAL.get();
    }

    public static void set(RequestContext value) {
        THREAD_LOCAL.set(value);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}


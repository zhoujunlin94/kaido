package com.kaido.internal.feign.interceptor;

import com.kaido.internal.util.RequestIdUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2023年10月24日 15:22
 * @desc
 */
@Component
public class FeignRequestIdInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(RequestIdUtil.REQUEST_ID, RequestIdUtil.getRequestId());
    }

}

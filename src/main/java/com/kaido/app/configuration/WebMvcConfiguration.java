package com.kaido.app.configuration;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.you.meet.nice.web.interceptor.HttpBaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujl
 * @date 2021/4/22 18:18
 * @desc
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private HttpBaseInterceptor httpBaseInterceptor;
    @Resource
    private HttpMessageConverter<Object> fastJsonHttpMessageConverter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 基础http请求拦截器
        registry.addInterceptor(httpBaseInterceptor)
                .excludePathPatterns("/favicon.ico", "/assets/**/*", "/**/*.js", "/**/*.html", "/**/*.css")
                .excludePathPatterns("/swagger-resources", "/v2/api-docs")
        ;

        // sa-token拦截器
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 登录权限判断
                    SaRouter
                            .match("/api/**/*")
                            .notMatch("/api/sa/user/login")
                            .check(r -> StpUtil.checkLogin());

                    // 模块权限判断
                    SaRouter.match("/api/cache-cfg/**/*").check(r -> StpUtil.checkRoleOr("admin", "cache-cfg"));
                    SaRouter.match("/api/sa/user/**/*").notMatch("/api/sa/user/login").check(r -> StpUtil.checkRoleOr("admin", "user"));
                    SaRouter.match("/api/sa/role/**/*").check(r -> StpUtil.checkRoleOr("admin", "role"));
                    SaRouter.match("/api/sa/resource/**/*").notMatch("/api/sa/resource/getUserRoleResources").check(r -> StpUtil.checkRoleOr("admin", "resource"));

                }).isAnnotation(false)
        );

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, fastJsonHttpMessageConverter);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/doc.html");
    }

}


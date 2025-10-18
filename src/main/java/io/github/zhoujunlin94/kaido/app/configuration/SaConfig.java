package io.github.zhoujunlin94.kaido.app.configuration;

import cn.dev33.satoken.spring.pathmatch.SaPatternsRequestConditionHolder;
import cn.dev33.satoken.strategy.SaStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoujunlin
 * @date 2025/10/18 21:23
 */
@Configuration
public class SaConfig {

    /**
     * 重写路由匹配算法，切换为 ant_path_matcher 模式，使之可以支持 `**` 之后再出现内容
     */
    @PostConstruct
    public void customRouteMatcher() {
        SaStrategy.instance.routeMatcher = SaPatternsRequestConditionHolder::match;
    }

}

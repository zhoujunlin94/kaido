package com.kaido;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

/**
 * @author zhoujunlin
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {
        /*不要mybatis自动注入 改用手动注入数据源*/
        MapperAutoConfiguration.class,
        MybatisAutoConfiguration.class,

        /*不要redis自动注入*/
        RedisAutoConfiguration.class,
        RedissonAutoConfiguration.class
})
public class KaidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaidoApplication.class, args);
    }

}
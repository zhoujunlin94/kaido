package com.kaido;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {
        MapperAutoConfiguration.class,
        MybatisAutoConfiguration.class,

        /*不注入redis*/
        /*RedisAutoConfiguration.class,
        RedissonAutoConfiguration.class*/
})
public class KaidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaidoApplication.class, args);
    }

}
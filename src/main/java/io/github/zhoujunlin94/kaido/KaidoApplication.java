package io.github.zhoujunlin94.kaido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {
        /*不要redis自动注入  后续使用redis功能需要注释这两行*/
//        RedisAutoConfiguration.class,
//        RedissonAutoConfiguration.class
})
public class KaidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaidoApplication.class, args);
    }

}
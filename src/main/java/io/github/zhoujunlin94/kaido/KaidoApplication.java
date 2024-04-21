package io.github.zhoujunlin94.kaido;

import io.github.zhoujunlin94.meet.tk_mybatis.MeetTKMybatisAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author zhoujunlin
 */
@Import(MeetTKMybatisAutoConfiguration.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {
        /*不要redis自动注入  后续使用redis功能需要删除这两行*/
        RedisAutoConfiguration.class,
        RedissonAutoConfiguration.class
})
public class KaidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaidoApplication.class, args);
    }

}
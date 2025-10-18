package io.github.zhoujunlin94.kaido.app.configuration;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoujunlin
 * @date 2023年07月09日 18:46
 * @desc
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI kaidoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("项目接口文档")
                        .version("1.0.0")
                        .description("这是一个基于 Spring Boot 的 RESTful API 文档"))
                .addServersItem(new Server().url("/"))
                .components(new Components()
                        .addParameters("token", new Parameter().in(ParameterIn.HEADER.toString()).name("Authorization").required(false).description("Token").schema(new StringSchema()))
                );
    }

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("默认")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi saApi() {
        return GroupedOpenApi.builder()
                .group("sa权限")
                .pathsToMatch("/api/sa/**")
                .build();
    }

}

package io.github.zhoujunlin94.kaido.app.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.github.zhoujunlin94.kaido.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhoujunlin
 * @date 2023年07月09日 18:46
 * @desc
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true).select()
                //这里指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage(Constant.BIZ_CONTROLLER_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("kaido")
                .description("kaido by springboot 2.6.11")
                .contact(new Contact("zhoujunlin", "https://github.com/zhoujunlin94/kaido", "zhoujunlin.work@outlook.com"))
                .version("1.0.0")
                .build();
    }

}

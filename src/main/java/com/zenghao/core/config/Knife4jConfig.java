package com.zenghao.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Profile("!prod")
public class Knife4jConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(apiInfo())
                .groupName("1.0版本")
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.zenghao"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("健身预约管理文档")
                .description("健身预约管理文档")
                .version("1.0.0")
                .termsOfServiceUrl("http://localhost:8084/doc.html")
                .contact(new Contact("曾豪","","2283134870@qq.com"))
                .build();
    }
}

package com.fline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("api文档")
                        .termsOfServiceUrl("http://172.16.2.8:5607/")
                        .version("1.0")
                        .build())
                .groupName("访客管理系统")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fline.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
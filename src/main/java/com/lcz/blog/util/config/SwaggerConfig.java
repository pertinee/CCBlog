package com.lcz.blog.util.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在
@EnableWebMvc //必须存在
@ComponentScan(basePackages = {"com.lcz.blog.controller"}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class SwaggerConfig extends WebMvcConfigurationSupport{

    /**
     * 通用API接口文档
     * @return
     */
    @Bean
    public Docket commonDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用API接口文档")
                .apiInfo(apiInfo("提供通用接口"))
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lcz.blog.controller.api.common"))
                .paths(PathSelectors.any())//过滤的接口
                .build();
    }

    /**
     * 普通用户API文档
     * @return
     */
    @Bean
    public Docket normalUserDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("普通用户API文档")
                .apiInfo(apiInfo("提供普通用户接口"))
                .protocols(Sets.newHashSet("https","http"))
                .produces(Sets.newHashSet("html/text"))
                .pathMapping("/")
                .select()
                //设置生成的Docket对应的Controller为candidate下的所有Controller
                .apis(RequestHandlerSelectors.basePackage("com.lcz.blog.controller.api.normal"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 企业用户API接口文档
     * @return
     */
    @Bean
    public Docket companyUserDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("企业用户API接口文档")
                .apiInfo(apiInfo("提供企业用户接口"))
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lcz.blog.controller.api.company"))
//                .paths(or(regex("/api/.*")))
                .paths(PathSelectors.any())
                .build();
    }
    //设置文档信息
    private ApiInfo apiInfo(String desc) {
        Contact contact = new Contact("luchunzhou", "https://www.luchunzhou.cn/", "1090061055@qq.com");
        return new ApiInfoBuilder()
                .title("CCBlog API接口文档")
//                .termsOfServiceUrl("https://www.luchunzhou.cn/")
                .description(desc)
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
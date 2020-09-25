package com.wang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//开启Swagger2
@EnableSwagger2
public class SwaggerConfig {

    //配置了Swagger的Docket的Bean实例
    @Bean
    public Docket docket(Environment environment) {
        //RequestHandlerSelectors 配置要扫描接口的方式
        /*
        basePackage     指定要扫描的包
        any             扫描全部
        none            都不扫描
        withClassAnnotation 扫描类上的注解,参数是一个注解的反射对象, 比如 withClassAnnotation(RestController.class)
        withMethodAnnotation    扫描方法上的注解
         */
        //paths 过滤的路径, 需要传入PathSelectors.xxx 选择过滤的方式

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");

        //通过environment.acceptsProfiles, 判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wang.controller"))
//                .paths(PathSelectors.ant("/wang/**"))
                .build()
                .enable(flag)
                .groupName("我的API");
    }

    //配置Swagger信息 apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("wang", "https://www.cnblogs.com/wang-sky/", "715180879@qq.com");

        return new ApiInfo(
                "我的Swagger接口文档",
                "这是一个Swagger接口文档",
                "V 1.0",
                "https://www.cnblogs.com/wang-sky/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }


}

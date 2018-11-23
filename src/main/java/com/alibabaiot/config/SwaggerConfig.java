package com.alibabaiot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 *
 * @author linyibeng
 * @date 2018-10-22
 */
@Configuration
@EnableSwagger2
//@ConditionalOnProperty(prefix = "com", name = "swagger-open", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket systemRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("IOT屏显接口")
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         //这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.basePackage("com.alibabaiot.controller"))    //这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("IOT 网关")
                //创建人
                .contact(new Contact("cherryBlossomss", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("客户端与服务端测试")
                .build();
    }

}

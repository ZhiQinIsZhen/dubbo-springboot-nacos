package com.liyz.dubbo.common.api.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Desc:swagger base config
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 10:36
 */
public class SwaggerConfig {

    private static final String IN_HEADER = "header";

    protected final OpenApiExtensionResolver openApiExtensionResolver;
    protected final static Set<String> PROTOCOL = Sets.newHashSet("https", "http");

    public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("统一认证中心-接口文档")
                .description("这是一个Apache Dubbo项目，基于SpringBoot、Mybatis、Nacos、Seata等框架")
                .termsOfServiceUrl("http://127.0.0.1:7070/")
                .contact(new Contact("liyz", "https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos", "liyangzhen0114@foxmail.com"))
                .version("1.0.0")
                .build();
    }

    protected Docket docket(final String basePackage, final String groupName, final Predicate<String> selector) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(PROTOCOL)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .securitySchemes(Lists.newArrayList(new ApiKey("JWT认证(Bearer )", HttpHeaders.AUTHORIZATION, IN_HEADER)))
                .securityContexts(Lists.newArrayList(SecurityContext.builder()
                        .securityReferences(Lists.newArrayList(new SecurityReference("JWT认证(Bearer )",
                                Lists.newArrayList(new AuthorizationScope("global", HttpHeaders.AUTHORIZATION))
                                        .toArray(new AuthorizationScope[0]))))
                        .forPaths(selector)
                        .build()))
                .groupName(groupName);
    }

//    return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("xxx")
//                .apiInfo(new ApiInfoBuilder()
//                        .title("xxx")
//                        .description("xxx")
//                        .version("xxx")
//                        .build())
//            .select()
//                .apis(input -> {
//        Optional<ApiVersion> apiVersion = input.findAnnotation(ApiVersion.class);
//        return apiVersion.isPresent() && Arrays.asList(apiVersion.get().group()).contains(GROUP_CENTER_V1_2_6);
//    })
//            .paths(PathSelectors.any())
//            .build();
}

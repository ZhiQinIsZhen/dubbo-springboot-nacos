package com.liyz.dubbo.api.openapi3.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/29 10:09
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(info()).externalDocs(documentation());
    }

    public Info info() {
        return new Info()
                .title("网关接口文档")
                .version("V1.0.0")
                .description("Spring doc(基于openapi3)")
                .license(new License().name("MIT License").url("https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/License"))
                .contact(new Contact().name("ZhiQinIsZhen").url("https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos"))
                .summary("概要");
    }

    public ExternalDocumentation documentation() {
        return new ExternalDocumentation().description("Test网关接口文档").url("http://127.0.0.1:7050/doc.html");
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder()
                .displayName("测试接口")
                .group("test")
                .packagesToScan("com.liyz.dubbo.api.openapi3.controller.test")
                .addOpenApiCustomiser(openApiCustomiser())
                .addOperationCustomizer(operationCustomizer())
                .build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .displayName("认证接口")
                .group("auth")
                .packagesToScan("com.liyz.dubbo.api.openapi3.controller.auth")
                .addOpenApiCustomiser(openApiCustomiser())
                .addOperationCustomizer(operationCustomizer())
                .build();
    }

    /**
     * 这个会在每个分组的全局上配置auth
     *
     * @return
     */
    public OpenApiCustomiser openApiCustomiser() {
        return api -> api.components(new Components()
                .addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                        .name(HttpHeaders.AUTHORIZATION)
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .description("JWT认证")
                        .scheme("bearer ")));
    }

    /**
     * 这个配置会在每个接口的调试上增加auth
     *
     * @return
     */
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION, "bearer "));
            return operation;
        };
    }
}

package com.liyz.dubbo.common.api.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.collect.Sets;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.Set;

/**
 * Desc:swagger base config
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 10:36
 */
public class SwaggerConfig {

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
}

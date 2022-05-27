package com.liyz.dubbo.api.open.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.collect.Sets;
import com.liyz.dubbo.common.controller.config.SwaggerBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 注释:swagger配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/7/16 16:30
 */
@EnableKnife4j
@EnableSwagger2WebMvc
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends SwaggerBaseConfig {

    public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        super(openApiExtensionResolver);
    }

    @Bean
    public Docket createAuthApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.open.controller.auth"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .groupName("鉴权认证-API");
    }

    @Bean
    public Docket createStaffApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.open.controller.staff"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .groupName("员工-API");
    }

    @Bean
    public Docket createSmsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.open.controller.sms"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .groupName("验证类-API");
    }

    @Bean
    public Docket createExcelApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.open.controller.excel"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .groupName("Excel-API");
    }
}

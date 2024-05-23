package com.liyz.dubbo.api.user.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.liyz.dubbo.common.api.annotation.ApiVersion;
import com.liyz.dubbo.common.api.config.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.Optional;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/12 16:51
 */
@EnableKnife4j
@EnableSwagger2WebMvc
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class ApiSwaggerConfig extends SwaggerConfig {

    public static final String GROUP_TEST = "测试swagger分组";

    public ApiSwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        super(openApiExtensionResolver);
    }

    @Bean
    public Docket allApi() {
        return docket("com.liyz.dubbo.api.user.controller", "ALL-API",
                PathSelectors.none());
    }

    @Bean
    public Docket testApi() {
        return docket("com.liyz.dubbo.api.user.controller.test", "接口测试-API",
                PathSelectors.none());
    }

    @Bean
    public Docket authApi() {
        return docket("com.liyz.dubbo.api.user.controller.auth", "认证鉴权-API",
                PathSelectors.none());
    }

    @Bean
    public Docket userApi() {
        return docket("com.liyz.dubbo.api.user.controller.user", "客户信息-API",
                PathSelectors.any());
    }

    @Bean
    public Docket testGroupApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_TEST)
                .apiInfo(new ApiInfoBuilder()
                        .title(GROUP_TEST)
                        .description(GROUP_TEST)
                        .version(GROUP_TEST)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiVersion.class))
                .paths(PathSelectors.any())
                .build();
    }
}

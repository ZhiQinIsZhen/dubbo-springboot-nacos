package com.liyz.dubbo.api.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.liyz.dubbo.common.api.config.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

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

    public ApiSwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        super(openApiExtensionResolver);
    }

    @Bean
    public Docket testApi() {
        return docket("com.liyz.dubbo.api.admin.controller.test", "接口测试-API", PathSelectors.none());
    }

    @Bean
    public Docket authApi() {
        return docket("com.liyz.dubbo.api.admin.controller.authenticate", "认证鉴权-API", PathSelectors.none());
    }

    @Bean
    public Docket authorityApi() {
        return docket("com.liyz.dubbo.api.admin.controller.authority", "权限信息-API", PathSelectors.any());
    }

    @Bean
    public Docket staffApi() {
        return docket("com.liyz.dubbo.api.admin.controller.staff", "员工信息-API", PathSelectors.any());
    }

    @Bean
    public Docket algorithmApi() {
        return docket("com.liyz.dubbo.api.admin.controller.algorithm", "算法-API", PathSelectors.none());
    }
}

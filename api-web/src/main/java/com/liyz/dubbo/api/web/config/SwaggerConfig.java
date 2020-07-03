package com.liyz.dubbo.api.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 注释:swagger配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/7/16 16:30
 */
@EnableKnife4j
@EnableSwagger2
@Configuration
//@ConditionalOnExpression("${swagger.doc}")
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends SwaggerBaseConfig {

    @Bean
    public Docket createAuthApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.web.controller.auth"))
                .paths(PathSelectors.any())
                .build().groupName("鉴权认证-API");
    }

    @Bean
    public Docket createBusinessApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.web.controller.member"))
                .paths(PathSelectors.any())
                .build().groupName("用户-API");
    }

    @Bean
    public Docket createFileApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liyz.dubbo.api.web.controller.file"))
                .paths(PathSelectors.any())
                .build().groupName("文件服务-API");
    }
}

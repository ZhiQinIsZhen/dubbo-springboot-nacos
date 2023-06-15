package com.liyz.dubbo.security.client.constant;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 14:28
 */
public interface SecurityClientConstant {

    /**
     * 免登录资源
     */
    String[] SECURITY_IGNORE_RESOURCES = new String[] {
            "/",
            "/instances/**",
            "/actuator/**",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};

    String OPTIONS_PATTERNS = "/**";

    String DEFAULT_TOKEN_HEADER_KEY = "Authorization";

    String DUBBO_APPLICATION_NAME_PROPERTY = "dubbo.application.name";

    String DUBBO_AUTH_GROUP = "auth";

    String AUTH_SERVICE_BEAN_NAME = "remoteAuthService-auth";

    String JWT_SERVICE_BEAN_NAME = "remoteJwtParseService-auth";

    String AUTH_MANAGER_BEAN_NAME = "authenticationManager";
}

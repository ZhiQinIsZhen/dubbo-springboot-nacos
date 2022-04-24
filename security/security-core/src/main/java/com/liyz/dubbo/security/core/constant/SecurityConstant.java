package com.liyz.dubbo.security.core.constant;

/**
 * 注释:security常量池
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 17:49
 */
public interface SecurityConstant {

    /**
     * 免登陆资源
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

    String ALL_METHOD = "ALL";

    String BACKSTAGE_ROLE_ADMIN = "admin";
    String ANONYMOUS_USER = "anonymousUser";

    String GRANTED_AUTHORITY_SPLIT = ";";

    Integer VALIDATE_TOKEN_SUCCESS = 0;
    Integer VALIDATE_TOKEN_FAIL_USERNAME = 1;
    Integer VALIDATE_TOKEN_FAIL_EXPIRED = 2;
    Integer VALIDATE_TOKEN_FAIL_OTHER_LOGIN = 3;

    String DEFAULT_VERSION = "1.0.0";
}

package com.liyz.dubbo.common.security.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 14:33
 */
public interface SecurityConstant {

    String BACKSTAGE_ROLE_ADMIN = "admin";
    String ANONYMOUS_USER = "anonymousUser";

    String ALL_METHOD = "ALL";

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

    Integer VALIDATE_TOKEN_SUCCESS = 0;
    Integer VALIDATE_TOKEN_FAIL_USERNAME = 1;
    Integer VALIDATE_TOKEN_FAIL_EXPIRED = 2;
    Integer VALIDATE_TOKEN_FAIL_OTHER_LOGIN = 3;
}

package com.liyz.dubbo.common.security.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 13:28
 */
@Slf4j
public class RequestedMatcherImpl implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        log.info("!!!!!!!!!!!!!!!");
        return true;
    }
}

package com.liyz.dubbo.security.client.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 注释:认证事件监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:21
 */
@Slf4j
@Component
public class AuthenticationEvents {

    private AtomicInteger successAtomic = new AtomicInteger(0);

    private AtomicInteger failAtomic = new AtomicInteger(0);

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        log.info("auth success; count:{}", successAtomic.addAndGet(1));
    }

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent failures) throws ServletException, IOException {
        log.error("auth fail; count:{}", failAtomic.addAndGet(1));
    }
}

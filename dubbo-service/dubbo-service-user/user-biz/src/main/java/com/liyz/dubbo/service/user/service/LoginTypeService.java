package com.liyz.dubbo.service.user.service;

import com.google.common.collect.Maps;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.user.model.base.UserAuthBaseDO;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/14 9:19
 */
public interface LoginTypeService extends ApplicationListener<ContextRefreshedEvent> {

    //容器
    Map<LoginType, LoginTypeService> LOGIN_TYPE_MAP = Maps.newEnumMap(LoginType.class);

    @Override
    default void onApplicationEvent(ContextRefreshedEvent event) {
        LOGIN_TYPE_MAP.put(loginType(), event.getApplicationContext().getBean(this.getClass()));
    }

    /**
     * 获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    UserAuthBaseDO getByUsername(String username);

    /**
     * 获取登录方式
     *
     * @return 登录方式
     */
    LoginType loginType();
}

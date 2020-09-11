package com.liyz.dubbo.common.controller.resolver.impl;

import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import org.springframework.stereotype.Service;

/**
 * 注释: 重token获取 userInfoBO 安全类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:54
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService {

    private ThreadLocal<JwtUserBO> userBOContainer = new ThreadLocal<>();

    /**
     * 获取登录user
     *
     * @return JwtUserBO
     */
    @Override
    public JwtUserBO getUser() {
        return userBOContainer.get();
    }

    /**
     * 设置登录user
     *
     * @param user JwtUserBO
     */
    @Override
    public void setUser(JwtUserBO user) {
        this.userBOContainer.set(user);
    }

    /**
     * 移除
     */
    @Override
    public void remove() {
        userBOContainer.remove();
    }
}

package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.user.dao.UserAuthMobileMapper;
import com.liyz.dubbo.service.user.model.UserAuthMobileDO;
import com.liyz.dubbo.service.user.model.base.UserAuthBaseDO;
import com.liyz.dubbo.service.user.service.UserAuthMobileService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 9:45
 */
@Service
public class UserAuthMobileServiceImpl extends ServiceImpl<UserAuthMobileMapper, UserAuthMobileDO> implements UserAuthMobileService {

    /**
     * 获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    @Override
    public UserAuthBaseDO getByUsername(String username) {
        return getOne(Wrappers.lambdaQuery(UserAuthMobileDO.builder().mobile(username).build()));
    }

    /**
     * 获取登录方式
     *
     * @return 登录方式
     */
    @Override
    public LoginType loginType() {
        return LoginType.MOBILE;
    }
}

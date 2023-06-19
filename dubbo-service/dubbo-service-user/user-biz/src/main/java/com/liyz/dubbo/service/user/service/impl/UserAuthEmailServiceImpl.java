package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.user.dao.UserAuthEmailMapper;
import com.liyz.dubbo.service.user.model.UserAuthEmailDO;
import com.liyz.dubbo.service.user.model.base.UserAuthBaseDO;
import com.liyz.dubbo.service.user.service.UserAuthEmailService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 9:46
 */
@Service
public class UserAuthEmailServiceImpl extends ServiceImpl<UserAuthEmailMapper, UserAuthEmailDO> implements UserAuthEmailService {

    /**
     * 获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    @Override
    public UserAuthBaseDO getByUsername(String username) {
        return getOne(Wrappers.lambdaQuery(UserAuthEmailDO.builder().email(username).build()));
    }

    /**
     * 获取登录方式
     *
     * @return 登录方式
     */
    @Override
    public LoginType loginType() {
        return LoginType.EMAIL;
    }
}

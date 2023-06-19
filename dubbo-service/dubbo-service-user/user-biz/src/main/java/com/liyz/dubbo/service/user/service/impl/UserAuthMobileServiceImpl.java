package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.user.dao.UserAuthMobileMapper;
import com.liyz.dubbo.service.user.model.UserAuthMobileDO;
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
}

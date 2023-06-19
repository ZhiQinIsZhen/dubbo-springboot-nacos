package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.user.dao.UserAuthEmailMapper;
import com.liyz.dubbo.service.user.model.UserAuthEmailDO;
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
}

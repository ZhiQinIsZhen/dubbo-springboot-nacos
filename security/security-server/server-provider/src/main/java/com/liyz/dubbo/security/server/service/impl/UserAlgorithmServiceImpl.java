package com.liyz.dubbo.security.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.security.server.dao.UserAlgorithmMapper;
import com.liyz.dubbo.security.server.model.UserAlgorithmDO;
import com.liyz.dubbo.security.server.service.IUserAlgorithmService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/16 19:12
 */
@Service
public class UserAlgorithmServiceImpl extends ServiceImpl<UserAlgorithmMapper, UserAlgorithmDO> implements IUserAlgorithmService {

    @Override
    public UserAlgorithmDO getByUserId(Long userId) {
        UserAlgorithmDO userAlgorithmDO = new UserAlgorithmDO();
        userAlgorithmDO.setUserId(userId);
        return getOne(Wrappers.lambdaQuery(userAlgorithmDO));
    }
}

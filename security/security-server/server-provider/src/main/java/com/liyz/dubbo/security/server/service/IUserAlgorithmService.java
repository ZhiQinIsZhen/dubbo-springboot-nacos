package com.liyz.dubbo.security.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyz.dubbo.security.server.model.UserAlgorithmDO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/16 19:11
 */
public interface IUserAlgorithmService extends IService<UserAlgorithmDO> {

    UserAlgorithmDO getByUserId(Long userId);
}

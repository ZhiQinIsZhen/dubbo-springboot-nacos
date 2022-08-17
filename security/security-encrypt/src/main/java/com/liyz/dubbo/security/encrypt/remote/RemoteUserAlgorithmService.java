package com.liyz.dubbo.security.encrypt.remote;

import com.liyz.dubbo.security.encrypt.vo.UserAlgorithmVO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 11:22
 */
public interface RemoteUserAlgorithmService {

    Boolean create(UserAlgorithmVO userAlgorithmVO);
}

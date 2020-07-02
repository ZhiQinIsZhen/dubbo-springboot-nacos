package com.liyz.dubbo.common.remote.service;

import com.liyz.dubbo.common.remote.bo.JwtUserBO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/11/20 9:52
 */
public interface RemoteJwtUserService {

    JwtUserBO getByLoginName(String loginName);
}

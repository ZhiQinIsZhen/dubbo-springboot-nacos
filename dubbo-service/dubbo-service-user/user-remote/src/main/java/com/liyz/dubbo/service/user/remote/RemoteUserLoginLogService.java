package com.liyz.dubbo.service.user.remote;

import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.user.bo.UserLoginLogBO;

import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:19
 */
public interface RemoteUserLoginLogService {

    /**
     * 根据userId分页查询登录日志
     *
     * @param userId 用户ID
     * @param pageBO 分页参数
     * @return 用户登录日志
     */
    RemotePage<UserLoginLogBO> page(@NotNull Long userId, @NotNull PageBO pageBO);
}

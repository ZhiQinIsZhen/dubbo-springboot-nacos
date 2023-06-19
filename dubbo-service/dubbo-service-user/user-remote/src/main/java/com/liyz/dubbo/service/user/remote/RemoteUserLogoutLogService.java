package com.liyz.dubbo.service.user.remote;

import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.user.bo.UserLogoutLogBO;

import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:21
 */
public interface RemoteUserLogoutLogService {

    /**
     * 根据userId分页查询登出日志
     *
     * @param userId 用户ID
     * @param pageBO 分页参数
     * @return 用户登出日志
     */
    RemotePage<UserLogoutLogBO> page(@NotNull Long userId, @NotNull PageBO pageBO);
}

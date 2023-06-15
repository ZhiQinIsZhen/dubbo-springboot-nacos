package com.liyz.dubbo.service.staff.remote;


import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.staff.bo.StaffLoginLogBO;

import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:19
 */
public interface RemoteStaffLoginLogService {

    /**
     * 根据staffId分页查询登录日志
     *
     * @param staffId 员工ID
     * @param pageBO 分页信息
     * @return 员工登录日志
     */
    RemotePage<StaffLoginLogBO> page(@NotNull Long staffId, @NotNull PageBO pageBO);
}

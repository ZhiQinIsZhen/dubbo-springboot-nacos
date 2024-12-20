package com.liyz.dubbo.service.user.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.user.bo.UserLogoutLogBO;
import com.liyz.dubbo.service.user.model.UserLogoutLogDO;
import com.liyz.dubbo.service.user.remote.RemoteUserLogoutLogService;
import com.liyz.dubbo.service.user.service.UserLogoutLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 11:05
 */
@DubboService
public class RemoteUserLogoutLogServiceImpl implements RemoteUserLogoutLogService {

    @Resource
    private UserLogoutLogService userLogoutLogService;

    /**
     * 根据userId分页查询登出日志
     *
     * @param userId
     * @param pageBO
     * @return
     */
    @Override
    public RemotePage<UserLogoutLogBO> page(Long userId, PageBO pageBO) {
        Page page = userLogoutLogService.page(
                Page.of(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(UserLogoutLogDO.builder().userId(userId).build()).orderByDesc(UserLogoutLogDO::getId)
        );
        return BeanUtil.copyProperties(page, UserLogoutLogBO.class);
    }
}

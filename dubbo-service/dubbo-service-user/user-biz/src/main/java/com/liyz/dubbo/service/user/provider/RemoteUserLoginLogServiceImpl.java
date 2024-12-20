package com.liyz.dubbo.service.user.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.user.bo.UserLoginLogBO;
import com.liyz.dubbo.service.user.model.UserLoginLogDO;
import com.liyz.dubbo.service.user.remote.RemoteUserLoginLogService;
import com.liyz.dubbo.service.user.service.UserLoginLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:50
 */
@DubboService
public class RemoteUserLoginLogServiceImpl implements RemoteUserLoginLogService {

    @Resource
    private UserLoginLogService userLoginLogService;

    /**
     * 根据userId分页查询登录日志
     *
     * @param userId 用户ID
     * @param pageBO 分页参数
     * @return 用户登录日志
     */
    @Override
    public RemotePage<UserLoginLogBO> page(Long userId, PageBO pageBO) {
        Page page = userLoginLogService.page(
                Page.of(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(UserLoginLogDO.builder().userId(userId).build()).orderByDesc(UserLoginLogDO::getId)
        );
        return BeanUtil.copyProperties(page, UserLoginLogBO.class);
    }
}

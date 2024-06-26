package com.liyz.dubbo.service.user.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.user.bo.UserInfoBO;
import com.liyz.dubbo.service.user.remote.RemoteUserInfoService;
import com.liyz.dubbo.service.user.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheEvict;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:48
 */
@DubboService
public class RemoteUserInfoServiceImpl implements RemoteUserInfoService {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 根据userId获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public UserInfoBO getByUserId(Long userId) {
        return BeanUtil.copyProperties(userInfoService.getById(userId), UserInfoBO.class);
    }

    /**
     * 分页查询客户信息
     *
     * @param pageBO 分页参数
     * @return 客户信息
     */
    @Override
    public RemotePage<UserInfoBO> page(PageBO pageBO) {
        Page page = userInfoService.page(Page.of(pageBO.getPageNum(), pageBO.getPageSize()));
        return BeanUtil.copyProperties(page, UserInfoBO.class);
    }

    @Override
    @CacheEvict(cacheNames = {"userInfo"}, key = "'*'")
    public void test() {

    }
}

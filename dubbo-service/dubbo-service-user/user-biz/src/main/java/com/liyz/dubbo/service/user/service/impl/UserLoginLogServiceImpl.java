package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.user.dao.UserLoginLogMapper;
import com.liyz.dubbo.service.user.model.UserLoginLogDO;
import com.liyz.dubbo.service.user.service.UserLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 9:44
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLogDO> implements UserLoginLogService {

    /**
     * 获取上次登录时间
     *
     * @param userId 员工ID
     * @param device 设备类型
     * @return 上次登录时间
     */
    @Override
    public Date lastLoginTime(Long userId, Device device) {
        Page<UserLoginLogDO> page = page(
                new Page<>(1, 1),
                Wrappers.lambdaQuery(UserLoginLogDO.builder().userId(userId).device(device.getType()).build()).orderByDesc(UserLoginLogDO::getId)
        );
        Date lastLoginTime = null;
        if (Objects.nonNull(page) && !CollectionUtils.isEmpty(page.getRecords())) {
            lastLoginTime = page.getRecords().get(0).getLoginTime();
        }
        return lastLoginTime;
    }
}

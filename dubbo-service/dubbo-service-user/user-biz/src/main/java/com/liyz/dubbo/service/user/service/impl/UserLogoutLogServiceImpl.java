package com.liyz.dubbo.service.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.user.dao.UserLogoutLogMapper;
import com.liyz.dubbo.service.user.model.UserLogoutLogDO;
import com.liyz.dubbo.service.user.service.UserLogoutLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 9:43
 */
@Service
public class UserLogoutLogServiceImpl extends ServiceImpl<UserLogoutLogMapper, UserLogoutLogDO> implements UserLogoutLogService {


    /**
     * 获取上次登出时间
     *
     * @param userId 员工ID
     * @return 上次登出时间
     */
    @Override
    public Date lastLogoutTime(Long userId, Device device) {
        Page<UserLogoutLogDO> page = page(
                new Page<>(1, 1),
                Wrappers.lambdaQuery(UserLogoutLogDO.builder().userId(userId).device(device.getType()).build()).orderByDesc(UserLogoutLogDO::getId)
        );
        Date lastLogoutTime = null;
        if (Objects.nonNull(page) && !CollectionUtils.isEmpty(page.getRecords())) {
            lastLogoutTime = page.getRecords().get(0).getLogoutTime();
        }
        return lastLogoutTime;
    }
}

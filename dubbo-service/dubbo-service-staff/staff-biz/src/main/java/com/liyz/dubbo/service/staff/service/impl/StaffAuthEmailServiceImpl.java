package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.staff.dao.StaffAuthEmailMapper;
import com.liyz.dubbo.service.staff.model.StaffAuthEmailDO;
import com.liyz.dubbo.service.staff.model.base.StaffAuthBaseDO;
import com.liyz.dubbo.service.staff.service.StaffAuthEmailService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/15 14:06
 */
@Service
public class StaffAuthEmailServiceImpl extends ServiceImpl<StaffAuthEmailMapper, StaffAuthEmailDO> implements StaffAuthEmailService {

    @Override
    public StaffAuthBaseDO getByUsername(String username) {
        return getOne(Wrappers.lambdaQuery(StaffAuthEmailDO.builder().email(username).build()));
    }

    @Override
    public LoginType loginType() {
        return LoginType.EMAIL;
    }
}

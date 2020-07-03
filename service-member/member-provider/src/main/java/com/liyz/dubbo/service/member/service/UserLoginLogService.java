package com.liyz.dubbo.service.member.service;

import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.service.member.model.UserLoginLogDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/11 15:27
 */
@Slf4j
@Service
public class UserLoginLogService extends AbstractService<UserLoginLogDO> {

    public int save(@NotNull Long userId, @NotBlank String ip, @NotNull Integer type, @NotBlank String device) {
        UserLoginLogDO userLoginLogDO = new UserLoginLogDO();
        userLoginLogDO.setUserId(userId);
        userLoginLogDO.setIp(ip);
        userLoginLogDO.setType(type);
        userLoginLogDO.setDevice(device);
        userLoginLogDO.setCreateTime(new Date());
        return mapper.insert(userLoginLogDO);
    }
}

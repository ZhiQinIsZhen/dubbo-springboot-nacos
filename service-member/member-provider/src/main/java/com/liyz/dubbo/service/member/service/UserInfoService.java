package com.liyz.dubbo.service.member.service;

import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.common.redisson.RedissonService;
import com.liyz.dubbo.service.member.constant.RedisKeyConstant;
import com.liyz.dubbo.service.member.model.UserInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 15:44
 */
@Slf4j
@Service
public class UserInfoService extends AbstractService<UserInfoDO> {

    @Autowired
    RedissonService redissonService;

    @Override
    public UserInfoDO getById(Object id) {
        String key = RedisKeyConstant.getUserIdInfoKey(id.toString());
        UserInfoDO userInfoDO = redissonService.getValue(key, UserInfoDO.class);
        if (Objects.isNull(userInfoDO)) {
            userInfoDO = super.getById(id);
            redissonService.setValueExpire(key, JsonMapperUtil.toJSONString(userInfoDO), 30L, TimeUnit.MINUTES);
        }
        return userInfoDO;
    }

    @Override
    @Cacheable(cacheNames = {"memberService"}, key = "#model.loginName")
    public UserInfoDO getOne(UserInfoDO model) {
        return super.getOne(model);
    }

    @Cacheable(cacheNames = {"userCache"}, cacheManager = "userCacheManager", key = "#loginName")
    public UserInfoDO getByLoginName(String loginName) {
        String key = RedisKeyConstant.getLoginNameInfoKey(loginName);
        UserInfoDO userInfoDO = redissonService.getValue(key, UserInfoDO.class);
        if (Objects.isNull(userInfoDO)) {
            UserInfoDO param = new UserInfoDO();
            param.setLoginName(loginName);
            userInfoDO = super.getOne(param);
            redissonService.setValueExpire(key, JsonMapperUtil.toJSONString(userInfoDO), 30L, TimeUnit.MINUTES);
        }
        return userInfoDO;
    }



    @Override
    public int updateById(UserInfoDO model) {
        String key = RedisKeyConstant.getUserIdInfoKey(model.getUserId().toString());
        UserInfoDO userInfoDO = getById(model.getUserId());
        if (Objects.nonNull(userInfoDO)) {
            String loginNameKey = RedisKeyConstant.getLoginNameInfoKey(userInfoDO.getLoginName());
            redissonService.delete(loginNameKey);
        }
        redissonService.delete(key);
        return super.updateById(model);
    }

    public int selectCount(UserInfoDO userInfoDO) {
        return mapper.selectCount(userInfoDO);
    }

    public int loginNameCount(String loginName) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setLoginName(loginName);
        return mapper.selectCount(userInfoDO);
    }
}

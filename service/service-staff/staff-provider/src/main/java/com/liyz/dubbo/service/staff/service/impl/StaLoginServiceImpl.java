package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.staff.dao.StaLoginMapper;
import com.liyz.dubbo.service.staff.model.StaLoginDO;
import com.liyz.dubbo.service.staff.service.IStaLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/6 9:48
 */
@Service
public class StaLoginServiceImpl extends ServiceImpl<StaLoginMapper, StaLoginDO> implements IStaLoginService {

    /**
     * 查询
     *
     * @param staLoginDO
     * @return
     */
    @Override
    public StaLoginDO getOne(StaLoginDO staLoginDO) {
        return super.getOne(Wrappers.lambdaQuery(staLoginDO));
    }

    /**
     * 更新登陆时间
     *
     * @param staLoginDO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLoginTime(StaLoginDO staLoginDO) {
        return super.update(Wrappers.<StaLoginDO>lambdaUpdate()
                .set(StaLoginDO::getLoginTime, staLoginDO.getLoginTime())
                .eq(StaLoginDO::getCustomerId, staLoginDO.getCustomerId())
                .eq(StaLoginDO::getDevice, staLoginDO.getDevice())
        );
    }
}

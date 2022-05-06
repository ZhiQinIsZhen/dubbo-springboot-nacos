package com.liyz.dubbo.service.staff.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyz.dubbo.service.staff.model.StaLoginDO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/6 9:47
 */
public interface IStaLoginService extends IService<StaLoginDO> {

    /**
     * 查询
     *
     * @param staLoginDO
     * @return
     */
    StaLoginDO getOne(StaLoginDO staLoginDO);

    /**
     * 更新登陆时间
     *
     * @param staLoginDO
     * @return
     */
    boolean updateLoginTime(StaLoginDO staLoginDO);
}

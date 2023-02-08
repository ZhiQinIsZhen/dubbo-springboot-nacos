package com.liyz.dubbo.service.staff.service.impl;

import com.liyz.dubbo.common.dao.service.impl.LyzServiceImpl;
import com.liyz.dubbo.service.staff.dao.StaLoginLogMapper;
import com.liyz.dubbo.service.staff.model.StaLoginLogDO;
import com.liyz.dubbo.service.staff.service.IStaLoginLogService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/6 9:48
 */
@Service
public class StaLoginLogServiceImpl extends LyzServiceImpl<StaLoginLogMapper, StaLoginLogDO> implements IStaLoginLogService {
}

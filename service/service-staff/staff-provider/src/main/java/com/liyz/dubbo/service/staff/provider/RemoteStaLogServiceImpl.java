package com.liyz.dubbo.service.staff.provider;

import com.liyz.dubbo.service.staff.remote.RemoteStaLogService;
import com.liyz.dubbo.service.staff.service.IStaLoginLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/8 14:47
 */
@DubboService
public class RemoteStaLogServiceImpl implements RemoteStaLogService {

    @Resource
    private IStaLoginLogService staLoginLogService;

    @Override
    public int deleteById(Long id) {
        return staLoginLogService.deleteById(id);
    }

    @Override
    public int removeById(Long id) {
        return staLoginLogService.removeById(id) ? 1 : 0;
    }
}

package com.liyz.dubbo.service.staff.provider;

import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.service.staff.bo.CustomerBO;
import com.liyz.dubbo.service.staff.model.CustomerDO;
import com.liyz.dubbo.service.staff.remote.RemoteCustomerService;
import com.liyz.dubbo.service.staff.service.ICustomerService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 注释:客户信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/7 11:14
 */
@DubboService
public class RemoteCustomerServiceImpl implements RemoteCustomerService {

    @Resource
    private ICustomerService customerService;

    @Override
    public CustomerBO getByUsername(@NotBlank String username) {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(username);
        customerDO.setIsInactive(0);
        customerDO = customerService.getOne(customerDO);
        return CommonCloneUtil.objectClone(customerDO, CustomerBO.class);
    }
}
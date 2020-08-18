package com.liyz.dubbo.service.customer.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.service.customer.bo.CustomerBO;
import com.liyz.dubbo.service.customer.model.CustomerDO;
import com.liyz.dubbo.service.customer.remote.RemoteCustomerService;
import com.liyz.dubbo.service.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 14:02
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteCustomerServiceImpl implements RemoteCustomerService {

    @Autowired
    CustomerService customerService;

    @Override
    public CustomerBO getByUsername(@NotBlank String username) {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(username);
        customerDO.setIsInactive(0);
        customerDO = customerService.getOne(customerDO);
        return CommonConverterUtil.beanCopy(customerDO, CustomerBO.class);
    }
}

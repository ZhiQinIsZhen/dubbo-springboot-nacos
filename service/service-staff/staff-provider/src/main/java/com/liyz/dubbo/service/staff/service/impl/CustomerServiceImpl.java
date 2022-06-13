package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.staff.dao.CustomerMapper;
import com.liyz.dubbo.service.staff.model.CustomerDO;
import com.liyz.dubbo.service.staff.service.ICustomerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/7 11:07
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerDO> implements ICustomerService {

    @Override
    @Cacheable(cacheNames = {"staff"}, key = "'customerName:' + #customerDO.customerName", unless = "#result == null")
    public CustomerDO getOne(CustomerDO customerDO) {
        return super.getOne(Wrappers.lambdaQuery(customerDO));
    }
}

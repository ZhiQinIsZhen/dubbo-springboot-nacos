package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.staff.dao.CustomerRoleMapper;
import com.liyz.dubbo.service.staff.model.CustomerRoleDO;
import com.liyz.dubbo.service.staff.service.ICustomerRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/8/30 13:28
 */
@Slf4j
@Service
public class CustomerRoleServiceImpl extends ServiceImpl<CustomerRoleMapper, CustomerRoleDO> implements ICustomerRoleService {
}

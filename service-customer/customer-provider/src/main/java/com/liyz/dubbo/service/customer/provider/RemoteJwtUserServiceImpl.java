package com.liyz.dubbo.service.customer.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.remote.service.RemoteJwtUserService;
import com.liyz.dubbo.service.customer.bo.CustomerBO;
import com.liyz.dubbo.service.customer.model.CustomerDO;
import com.liyz.dubbo.service.customer.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 15:14
 */
@DubboService(version = "1.0.0", group = "customer")
public class RemoteJwtUserServiceImpl implements RemoteJwtUserService {

    @Autowired
    CustomerService customerService;

    @Override
    public JwtUserBO getByLoginName(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            return null;
        }
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(loginName);
        customerDO.setIsInactive(0);
        customerDO = customerService.getOne(customerDO);
        JwtUserBO jwtUserBO = CommonConverterUtil.beanCopy(customerDO, JwtUserBO.class);
        if (Objects.nonNull(customerDO)) {
            jwtUserBO.setLoginName(customerDO.getCustomerName());
            jwtUserBO.setLoginPwd(customerDO.getPassword());
        }
        return jwtUserBO;
    }
}

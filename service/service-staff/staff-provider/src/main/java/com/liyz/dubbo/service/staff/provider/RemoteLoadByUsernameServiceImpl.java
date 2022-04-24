package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.security.remote.RemoteLoadByUsernameService;
import com.liyz.dubbo.security.remote.bo.AuthUserBO;
import com.liyz.dubbo.service.staff.model.CustomerDO;
import com.liyz.dubbo.service.staff.model.CustomerRoleDO;
import com.liyz.dubbo.service.staff.service.ICustomerRoleService;
import com.liyz.dubbo.service.staff.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/7 11:27
 */
@Slf4j
@DubboService(group = "staff")
public class RemoteLoadByUsernameServiceImpl implements RemoteLoadByUsernameService {

    @Resource
    private ICustomerService customerService;
    @Resource
    private ICustomerRoleService customerRoleService;

    @Override
    public AuthUserBO login(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        boolean count = customerService.updateByUsername(username);
        if (!count) {
            log.error("userName : {}, login fail");
        }
        return loadByUsername(username);
    }

    @Override
    public AuthUserBO loadByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(username);
        customerDO.setIsInactive(0);
        customerDO = customerService.getOne(customerDO);
        AuthUserBO authUserBO = CommonCloneUtil.objectClone(customerDO, AuthUserBO.class);
        if (Objects.nonNull(customerDO)) {
            authUserBO.setUserName(customerDO.getCustomerName());
            authUserBO.setLoginName(customerDO.getCustomerName());
            authUserBO.setLoginPwd(customerDO.getPassword());
            authUserBO.setUserId(customerDO.getCustomerId());
            //查询角色信息
            CustomerRoleDO customerRoleDO = new CustomerRoleDO();
            customerRoleDO.setCustomerId(customerDO.getCustomerId());
            List<CustomerRoleDO> roleDOList = customerRoleService.list(Wrappers.lambdaQuery(customerRoleDO));
            authUserBO.setRoleIds(CollectionUtils.isEmpty(roleDOList) ? Lists.newArrayList() : roleDOList.stream().map(CustomerRoleDO::getRoleId).collect(Collectors.toList()));
        }
        return authUserBO;
    }
}

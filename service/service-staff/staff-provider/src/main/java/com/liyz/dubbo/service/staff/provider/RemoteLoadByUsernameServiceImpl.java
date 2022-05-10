package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.security.remote.RemoteLoadByUsernameService;
import com.liyz.dubbo.security.remote.bo.AuthUserBO;
import com.liyz.dubbo.service.staff.model.CustomerDO;
import com.liyz.dubbo.service.staff.model.CustomerRoleDO;
import com.liyz.dubbo.service.staff.model.StaLoginDO;
import com.liyz.dubbo.service.staff.model.StaLoginLogDO;
import com.liyz.dubbo.service.staff.service.ICustomerRoleService;
import com.liyz.dubbo.service.staff.service.ICustomerService;
import com.liyz.dubbo.service.staff.service.IStaLoginLogService;
import com.liyz.dubbo.service.staff.service.IStaLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private IStaLoginService staLoginService;
    @Resource
    private IStaLoginLogService staLoginLogService;

    /**
     * 修改登陆时间
     *
     * @param userId
     * @param device
     * @param isLogin
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Date updateLoginTime(final Long userId, final Integer device, final Boolean isLogin) {
        if (Objects.isNull(userId) || Objects.isNull(device)) {
            return null;
        }
        Date loginTime = DateUtil.currentDate();
        StaLoginDO staLoginDO = new StaLoginDO();
        staLoginDO.setCustomerId(userId);
        staLoginDO.setDevice(device);
        staLoginDO.setLoginTime(loginTime);
        boolean success = staLoginService.updateLoginTime(staLoginDO);
        if (!success) {
            //没有成功说明该数据没有，则新增一条数据
            staLoginService.save(staLoginDO);
        }
        //如果是登陆时修改时间，则需要记录日志
        if (isLogin) {
            StaLoginLogDO staLoginLogDO = new StaLoginLogDO();
            staLoginLogDO.setCustomerId(userId);
            staLoginLogDO.setDevice(device);
            staLoginLogDO.setLoginTime(loginTime);
            staLoginLogService.save(staLoginLogDO);
        }
        staLoginDO = new StaLoginDO();
        staLoginDO.setCustomerId(userId);
        staLoginDO.setDevice(device);
        return staLoginService.getOne(staLoginDO).getLoginTime();
    }

    /**
     * 通过username查询认证信息
     *
     * @param username
     * @param device
     * @return
     */
    @Override
    public AuthUserBO loadByUsername(final String username, final Integer device) {
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
            //查询登陆时间
            StaLoginDO staLoginDO = new StaLoginDO();
            staLoginDO.setCustomerId(customerDO.getCustomerId());
            staLoginDO.setDevice(device);
            staLoginDO = staLoginService.getOne(staLoginDO);
            if (Objects.nonNull(staLoginDO)) {
                authUserBO.setLoginTime(staLoginDO.getLoginTime());
            }
        }
        return authUserBO;
    }
}

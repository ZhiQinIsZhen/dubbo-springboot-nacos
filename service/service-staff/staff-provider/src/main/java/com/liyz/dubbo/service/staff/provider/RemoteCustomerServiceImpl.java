package com.liyz.dubbo.service.staff.provider;

import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.service.sms.exception.SmsExceptionCodeEnum;
import com.liyz.dubbo.service.sms.remote.RemoteSmsService;
import com.liyz.dubbo.service.staff.bo.CustomerBO;
import com.liyz.dubbo.service.staff.bo.UserRegisterBO;
import com.liyz.dubbo.service.staff.exception.RemoteStaffServiceException;
import com.liyz.dubbo.service.staff.exception.StaffExceptionCodeEnum;
import com.liyz.dubbo.service.staff.model.CustomerDO;
import com.liyz.dubbo.service.staff.remote.RemoteCustomerService;
import com.liyz.dubbo.service.staff.service.ICustomerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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
    @DubboReference
    RemoteSmsService remoteSmsService;

    /**
     * 注册
     *
     * @param userRegisterBO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterBO userRegisterBO) {
        CustomerBO customerBO = getByUsername(userRegisterBO.getLoginName());
        if (Objects.nonNull(customerBO)) {
            throw new RemoteStaffServiceException(StaffExceptionCodeEnum.ACCOUNT_EXIST);
        }
        if (!remoteSmsService.validateImageCode(userRegisterBO.getImageToken(), userRegisterBO.getVerificationCode())) {
            throw new RemoteStaffServiceException(SmsExceptionCodeEnum.IMAGE_ERROR);
        }
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(userRegisterBO.getLoginName());
        customerDO.setEmail("xxxx@qq.com");
        customerDO.setMobile("15888888888");
        customerDO.setNickName(userRegisterBO.getNickName());
        customerDO.setPassword(userRegisterBO.getLoginPwd());
        if (!customerService.save(customerDO)) {
            throw new RemoteStaffServiceException(StaffExceptionCodeEnum.REGISTER_ERROR);
        }
    }

    /**
     * 根据用户名获取客户信息
     *
     * @param username
     * @return
     */
    @Override
    public CustomerBO getByUsername(@NotBlank String username) {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName(username);
        customerDO.setIsInactive(0);
        customerDO = customerService.getOne(customerDO);
        return CommonCloneUtil.objectClone(customerDO, CustomerBO.class);
    }
}

package com.liyz.dubbo.service.transaction.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.DateUtil;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.service.message.bo.MessageLogBO;
import com.liyz.dubbo.service.message.remote.RemoteMessageLogService;
import com.liyz.dubbo.service.transaction.bo.AccountBO;
import com.liyz.dubbo.service.transaction.model.AccountDO;
import com.liyz.dubbo.service.transaction.remote.RemoteAccountService;
import com.liyz.dubbo.service.transaction.service.AccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 14:49
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteAccountServiceImpl implements RemoteAccountService {

    @Autowired
    AccountService accountService;

    @DubboReference(version = "1.0.0")
    RemoteMessageLogService remoteMessageLogService;

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "account-tx")
    public void update(AccountBO accountBO) {
        String xid = RootContext.getXID();
        log.info("xid : {}", xid);
        accountService.updateById(CommonConverterUtil.beanCopy(accountBO, AccountDO.class));
        MessageLogBO logBO = new MessageLogBO();
        logBO.setAddress("123");
        logBO.setBizId("alala");
        logBO.setCode(1);
        logBO.setContent("Lihaile");
        logBO.setSubject("短信");
        logBO.setSuccess(1);
        logBO.setType(1);
        logBO.setCreateTime(DateUtil.currentDate());
        logBO.setUpdateTime(DateUtil.currentDate());
        remoteMessageLogService.insert(logBO);
        if (Objects.nonNull(accountBO)) {
            throw new RemoteServiceException(CommonCodeEnum.ParameterError);
        }
    }
}

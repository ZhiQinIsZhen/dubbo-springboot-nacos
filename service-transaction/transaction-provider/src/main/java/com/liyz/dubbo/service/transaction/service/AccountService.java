package com.liyz.dubbo.service.transaction.service;

import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.service.transaction.model.AccountDO;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 14:46
 */
@Service
public class AccountService extends AbstractService<AccountDO> {

    @Override
    public int updateById(AccountDO model) {
        return super.updateById(model);
    }
}

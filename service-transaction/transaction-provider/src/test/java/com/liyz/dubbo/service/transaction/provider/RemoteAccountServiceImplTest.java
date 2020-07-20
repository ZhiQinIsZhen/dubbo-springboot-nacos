package com.liyz.dubbo.service.transaction.provider;

import com.liyz.dubbo.service.transaction.bo.AccountBO;
import com.liyz.dubbo.service.transaction.remote.RemoteAccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 14:55
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteAccountServiceImplTest {

    @Autowired
    RemoteAccountService remoteAccountService;

    @Test
    public void updateTest() {
        AccountBO bo = new AccountBO();
        bo.setId(1);
        bo.setUserId("2");
        bo.setAmount(400.0);
        remoteAccountService.update(bo);
    }
}
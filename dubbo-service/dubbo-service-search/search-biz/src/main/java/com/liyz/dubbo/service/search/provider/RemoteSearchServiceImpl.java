package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.service.search.remote.RemoteSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:27
 */
@Slf4j
@DubboService
public class RemoteSearchServiceImpl implements RemoteSearchService {

    @Override
    public void test() {
        log.info("test");
    }
}

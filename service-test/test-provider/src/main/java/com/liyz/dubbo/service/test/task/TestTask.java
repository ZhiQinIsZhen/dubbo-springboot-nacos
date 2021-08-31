package com.liyz.dubbo.service.test.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/7/26 10:37
 */
@Slf4j
public class TestTask implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("test..............");
    }
}

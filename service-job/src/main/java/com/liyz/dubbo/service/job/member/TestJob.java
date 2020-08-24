package com.liyz.dubbo.service.job.member;

import com.liyz.dubbo.common.job.annotation.ElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * 注释:test
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/24 15:36
 */
@Slf4j
@ElasticJob(jobName = "test", cron = "0/5 * * * * ?")
public class TestJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("test..............");
    }
}

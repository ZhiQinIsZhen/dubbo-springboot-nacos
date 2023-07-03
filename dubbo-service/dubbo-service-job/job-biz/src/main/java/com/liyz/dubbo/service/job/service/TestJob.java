package com.liyz.dubbo.service.job.service;

import com.liyz.dubbo.common.util.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.annotation.ElasticJobConfiguration;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/26 11:15
 */
@Slf4j
@ElasticJobConfiguration(jobName = "测试", cron = "0/10 * * * * ?", shardingTotalCount = 1)
public class TestJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("test job : {}", JsonMapperUtil.toJSONString(shardingContext));
    }
}

package com.liyz.dubbo.service.job.simple;

import com.liyz.dubbo.common.job.annotation.ElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注释:test
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/3 15:15
 */
@Slf4j
@Service
@ElasticJob(cron = "0/10 * * * * ?", shardingTotalCount = 1, shardingItemParameters = "0=Beijing")
public class TestSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("Item: {} | Time: {} | Thread: {} | {}",
                shardingContext.getShardingItem(),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                Thread.currentThread().getId(),
                "SIMPLE");
    }
}

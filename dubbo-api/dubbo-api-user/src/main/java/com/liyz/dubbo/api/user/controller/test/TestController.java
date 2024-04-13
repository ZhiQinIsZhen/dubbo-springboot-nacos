package com.liyz.dubbo.api.user.controller.test;

import com.google.common.util.concurrent.RateLimiter;
import com.liyz.dubbo.api.user.dto.test.LimitDTO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/12 16:53
 */
@Api(tags = "接口测试")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@Anonymous
@RestController
@RequestMapping("/test")
public class TestController {

    private RateLimiter rateLimiter;

    @ApiOperation("初始化")
    @GetMapping("/init")
    public Result<Boolean> limit() {
        rateLimiter = RateLimiter.create(10);
        return Result.success(Boolean.TRUE);
    }

    @ApiOperation("限流")
    @GetMapping("/limit")
    public Result<Boolean> limit(LimitDTO limitDTO) {
        int count = 0;
        while (count <= limitDTO.getCount()) {
            log.info("get 1 token : {} s", rateLimiter.acquire());
            count++;
        }
        int testCount = 0;
        while (testCount <= 100) {
            log.info("get 1 token : {}", rateLimiter.tryAcquire(1, 100, TimeUnit.MILLISECONDS));
            testCount++;
        }
        return Result.success(Boolean.TRUE);
    }

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation("redis限流")
    @GetMapping("/redis/limit")
    public Result<Boolean> redisLimit(LimitDTO limitDTO) {
        redissonClient.getConfig().setCodec(new StringCodec());
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter("user_limit");
        rRateLimiter.availablePermits();
        rRateLimiter.expire(Duration.of(5, ChronoUnit.MINUTES));
        rRateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.MINUTES);
        int count = 0;
        while (count <= limitDTO.getCount()) {
            log.info("available Permits token : {}", rRateLimiter.availablePermits());
            rRateLimiter.acquire();
            log.info("available Permits token : {}", rRateLimiter.availablePermits());
            count++;
        }
        return Result.success(Boolean.TRUE);
    }

    @ApiOperation("redis限流1")
    @GetMapping("/redis/limit1")
    public Result<Boolean> redisLimit1(LimitDTO limitDTO) throws InterruptedException {
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter("user_limit");
        rRateLimiter.expire(Duration.of(5, ChronoUnit.MINUTES));
        rRateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.MINUTES);
        int count = 0;
        while (count <= limitDTO.getCount()) {
            log.info("available Permits token : {}", rRateLimiter.availablePermits());
            log.info("available Permits token : {}", rRateLimiter.tryAcquire());
            Thread.sleep(2000);
            count++;
        }
        return Result.success(Boolean.TRUE);
    }
}

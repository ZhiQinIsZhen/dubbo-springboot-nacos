package com.liyz.dubbo.api.admin.controller.test;

import com.liyz.dubbo.api.admin.dto.auth.StaffLoginDTO;
import com.liyz.dubbo.api.admin.dto.test.TestDTO;
import com.liyz.dubbo.api.admin.vo.auth.AuthLoginVO;
import com.liyz.dubbo.api.admin.vo.test.TestVO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.api.result.TestResult;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Resource
    private RedissonClient redissonClient;

    @Limits({@Limit(type = LimitType.IP, count = 5), @Limit(count = 6)})
    @ApiOperation("你好")
    @GetMapping("/hello")
    public Result<TestVO> hello(@Validated(TestDTO.Hello.class) TestDTO testDTO) {
        return Result.success(BeanUtil.copyProperties(testDTO, TestVO::new));
    }

    @ApiOperation("test")
    @PostMapping("/test")
    public TestResult<StaffLoginDTO, AuthLoginVO> test(@Validated({StaffLoginDTO.Login.class}) @RequestBody StaffLoginDTO loginDTO) {
        return TestResult.success();
    }

    @ApiOperation("test1")
    @GetMapping("/test1")
    public String test1() {
        return TestResult.success().getMessage();
    }

    @ApiOperation("test2")
    @GetMapping("/test2")
    public Result<TestVO> test2() {
        TestVO testVO = new TestVO();
        testVO.setName("王晓刚");
        testVO.setEmail("我有一颗大土豆");
        testVO.setMobile("刚出锅的");
        testVO.setRealName("我有一颗大土豆，刚出锅的");
        return Result.success(testVO);
    }

    @ApiOperation("test3")
    @GetMapping("/test3")
    public Result<Long> test3(@RequestParam(value = "count") Long count) {
        RAtomicLong atomicVar = redissonClient.getAtomicLong("test");
        if (!atomicVar.isExists()) {
            boolean isSet = atomicVar.compareAndSet(0, 100);
            log.info("isSet : {}", isSet);
            if (isSet) {
                atomicVar.expire(Duration.ofMinutes(1));
            }
        }
        boolean isSet = atomicVar.compareAndSet(0, 100);
        log.info("isSet : {}", isSet);
        long result = atomicVar.addAndGet(count);
        return Result.success(result);
    }
}

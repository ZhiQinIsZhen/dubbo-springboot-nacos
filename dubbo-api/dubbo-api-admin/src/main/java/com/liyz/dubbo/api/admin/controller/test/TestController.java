package com.liyz.dubbo.api.admin.controller.test;

import com.liyz.dubbo.api.admin.dto.test.TestDTO;
import com.liyz.dubbo.api.admin.vo.test.TestVO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.service.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/test")
public class TestController {

    @Limits({@Limit(type = LimitType.IP, count = 2), @Limit(count = 1)})
    @ApiOperation("你好")
    @GetMapping("/hello")
    public Result<TestVO> hello(@Validated(TestDTO.Hello.class) TestDTO testDTO) {
        return Result.success(BeanUtil.copyProperties(testDTO, TestVO::new));
    }
}

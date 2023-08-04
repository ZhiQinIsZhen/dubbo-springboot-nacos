package com.liyz.dubbo.api.admin.controller.test;

import com.liyz.dubbo.api.admin.dto.test.JsonTypeBaseDTO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/3 14:38
 */
@Api(tags = "Json多态测试")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@Anonymous
@RestController
@RequestMapping("/jsonType")
public class JsonTypeController {

    @ApiOperation("test(get请求是不起作用的)")
    @GetMapping("/test")
    public Result<JsonTypeBaseDTO> test(JsonTypeBaseDTO baseDTO) {
        log.info("value : {}", JsonMapperUtil.toJSONString(baseDTO));
        return Result.success(baseDTO);
    }

    @ApiOperation("test1(post请求可以)")
    @PostMapping("/test1")
    public Result<JsonTypeBaseDTO> test1(@RequestBody JsonTypeBaseDTO baseDTO) {
        log.info("value : {}", JsonMapperUtil.toJSONString(baseDTO));
        return Result.success(baseDTO);
    }
}

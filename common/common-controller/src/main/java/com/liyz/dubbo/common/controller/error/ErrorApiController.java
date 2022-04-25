package com.liyz.dubbo.common.controller.error;

import com.liyz.dubbo.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:错误重定向controller
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 14:10
 */
@Api(value = "错误重定向", tags = "错误重定向")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "非0都为失败")
})
@Slf4j
@RestController
@RequestMapping("/liyz")
public class ErrorApiController extends BasicErrorController {

    public ErrorApiController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @ApiOperation(value = "错误重定向", notes = "错误重定向")
    @RequestMapping("/error")
    public Result myError(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return Result.error(String.valueOf(status.value()), status.getReasonPhrase());
    }
}

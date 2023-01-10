package com.liyz.dubbo.api.open.controller.dfa;

import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.service.dfa.remote.RemoteDfaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 16:17
 */
@Api(tags = "敏感词")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/dfa")
public class DfaController {

    @DubboReference(timeout = 3000000)
    private RemoteDfaService remoteDfaService;

    @Anonymous
    @ApiOperation("敏感词脱敏")
    @GetMapping("/desensitize")
    public Result<String> desensitize(@RequestParam(value = "word") String word) {
        return Result.success(remoteDfaService.sensitiveWord(word));
    }
}

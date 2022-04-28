package com.liyz.dubbo.api.open.controller.sms;

import com.liyz.dubbo.api.open.vo.sms.ImageVO;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:消息以及验证码controller
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 16:23
 */
@Api(tags = "消息信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

    @ApiOperation("刷新图片验证码")
    @Anonymous
    @GetMapping(value = "/imageCode")
    public Result<ImageVO> imageCode() {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("create imageCode，ip:{}", ip);
        Result<ImageBO> boResult = feignUserSmsService.imageCode();
        if (CommonCodeEnum.success.getCode().equals(boResult.getCode())) {
            return Result.success(CommonConverterUtil.beanCopy(boResult.getData(), ImageVO.class));
        }
        return Result.error(boResult.getCode(), boResult.getMessage());
    }
}

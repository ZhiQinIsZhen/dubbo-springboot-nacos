package com.liyz.dubbo.api.open.controller.sms;

import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.common.core.util.ImageCodeUtil;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.service.sms.bo.ImageBO;
import com.liyz.dubbo.service.sms.remote.RemoteSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @DubboReference
    RemoteSmsService remoteSmsService;

    @SneakyThrows
    @ApiOperation("刷新图片验证码")
    @Anonymous
    @GetMapping(value = "/imageCode")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) {
        String ip = HttpRequestUtil.getIpAddress(request);
        log.info("create imageCode，ip:{}", ip);
        ImageBO imageBO = remoteSmsService.imageCode();
        response.addHeader("imageToken", imageBO.getImageToken());
        response.setContentType("image/jpg");
        ImageCodeUtil.outputImage(100, 40, response.getOutputStream(), imageBO.getImageCode());
    }

    @ApiOperation("验证图片验证码")
    @Anonymous
    @GetMapping(value = "/validateImageCode")
    public Result<Boolean> validateImageCode(@RequestParam("imageToken") String imageToken,
                                             @RequestParam("imageCode")String imageCode) {
        return Result.success(remoteSmsService.validateImageCode(imageToken, imageCode));
    }
}

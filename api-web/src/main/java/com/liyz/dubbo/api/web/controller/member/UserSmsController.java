package com.liyz.dubbo.api.web.controller.member;

import com.liyz.dubbo.api.web.dto.member.SmsInfoDTO;
import com.liyz.dubbo.api.web.vo.member.ImageVO;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.HttpRequestUtil;
import com.liyz.dubbo.common.security.annotation.Anonymous;
import com.liyz.dubbo.service.member.bo.ImageBO;
import com.liyz.dubbo.service.member.bo.SmsInfoBO;
import com.liyz.dubbo.service.member.remote.RemoteSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 13:11
 */
@Api(value = "验证码", tags = "验证码")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/user/sms")
public class UserSmsController {

    @DubboReference(version = "1.0.0")
    RemoteSmsService remoteSmsService;

    @ApiOperation(value = "发送信息", notes = "发送信息")
    @Anonymous
    @PostMapping(value = "/message")
    public Result<Boolean> message(@Validated(SmsInfoDTO.Sms.class) @RequestBody SmsInfoDTO smsInfoDTO) {
        SmsInfoBO smsInfoBO = CommonConverterUtil.beanCopy(smsInfoDTO, SmsInfoBO.class);
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("user login，ip:{}", ip);
        smsInfoBO.setIp(ip);
        return Result.success(remoteSmsService.message(smsInfoBO));
    }

    @SneakyThrows
    @ApiOperation(value = "刷新图片验证码", notes = "刷新图片验证码")
    @Anonymous
    @GetMapping(value = "/imageCode")
    public Result<ImageVO> imageCode() {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("create imageCode，ip:{}", ip);
        ImageBO imageBO = remoteSmsService.imageCode();
        return Result.success(CommonConverterUtil.beanCopy(imageBO, ImageVO.class));
    }
}

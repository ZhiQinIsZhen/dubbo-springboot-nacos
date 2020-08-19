package com.liyz.dubbo.api.web.controller.search;

import com.liyz.dubbo.api.web.dto.page.PageBaseDTO;
import com.liyz.dubbo.api.web.dto.search.JudgementResultDTO;
import com.liyz.dubbo.api.web.vo.search.JudgementResultVO;
import com.liyz.dubbo.common.base.result.PageResult;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.common.security.annotation.Anonymous;
import com.liyz.dubbo.service.search.bo.JudgementResultBO;
import com.liyz.dubbo.service.search.bo.JudgementResultPageQueryBO;
import com.liyz.dubbo.service.search.remote.RemoteJudgementResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/15 16:34
 */
@Api(value = "法诉信息", tags = "法诉信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/es/judgement")
public class JudgementResultController {

    @DubboReference(version = "1.0.0", timeout = 2000)
    RemoteJudgementResultService remoteJudgementResultService;

    @ApiOperation(value = "保存法诉信息", notes = "保存法诉信息")
    @Anonymous
    @PostMapping(value = "/save")
    public Result<Integer> save(@RequestBody JudgementResultDTO judgementResultDTO) {
        return Result.success(remoteJudgementResultService.save(CommonConverterUtil.beanCopy(judgementResultDTO, JudgementResultBO.class)));
    }

    @ApiOperation(value = "批量保存法诉信息", notes = "批量保存法诉信息")
    @Anonymous
    @PostMapping(value = "/saveList", consumes = "application/json")
    public Result<Integer> saveList(@RequestBody List<JudgementResultDTO> list) {
        return Result.success(remoteJudgementResultService.save(CommonConverterUtil.ListTransform(list, JudgementResultBO.class)));
    }

    @ApiOperation(value = "通过Id删除法诉信息", notes = "通过Id删除法诉信息")
    @Anonymous
    @PostMapping(value = "/deleteById")
    public Result deleteById(@RequestBody JudgementResultDTO judgementResultDTO) {
        remoteJudgementResultService.delete(judgementResultDTO.getId());
        return Result.success();
    }

    @ApiOperation(value = "通过Ids删除法诉信息", notes = "通过Ids删除法诉信息")
    @Anonymous
    @PostMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestBody List<JudgementResultDTO> list) {
        remoteJudgementResultService.delete(list.stream().map(JudgementResultDTO::getId).collect(Collectors.toList()));
        return Result.success();
    }

    @ApiOperation(value = "分页查询法诉信息", notes = "分页查询法诉信息")
    @Anonymous
    @GetMapping(value = "/search")
    public PageResult<JudgementResultVO> search(PageBaseDTO pagebasedto) {
        Page<JudgementResultBO> boPage = remoteJudgementResultService.search(CommonConverterUtil.beanCopy(pagebasedto, PageBaseBO.class));
        PageResult<JudgementResultBO> pageResult = PageResult.success(boPage);
        return CommonConverterUtil.PageTransform(pageResult, JudgementResultVO.class);
    }

    @ApiOperation(value = "分页查询法诉信息-查询条件", notes = "分页查询法诉信息-查询条件")
    @Anonymous
    @GetMapping(value = "/searchForCondition")
    PageResult<JudgementResultVO> searchForCondition(JudgementResultPageQueryBO queryDTO) {
        Page<JudgementResultBO> boPage = remoteJudgementResultService.search(CommonConverterUtil.beanCopy(queryDTO, JudgementResultPageQueryBO.class));
        PageResult<JudgementResultBO> pageResult = PageResult.success(boPage);
        return CommonConverterUtil.PageTransform(pageResult, JudgementResultVO.class);
    }
}

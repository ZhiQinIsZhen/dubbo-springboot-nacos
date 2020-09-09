package com.liyz.dubbo.api.web.controller.search;

import com.liyz.dubbo.api.web.dto.page.PageBaseDTO;
import com.liyz.dubbo.api.web.dto.search.RiskConsensusDTO;
import com.liyz.dubbo.api.web.dto.search.RiskConsensusPageQueryDTO;
import com.liyz.dubbo.api.web.vo.search.RiskConsensusVO;
import com.liyz.dubbo.common.base.result.PageResult;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.common.security.annotation.Anonymous;
import com.liyz.dubbo.service.search.bo.RiskConsensusBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusPageQueryBO;
import com.liyz.dubbo.service.search.remote.RemoteRiskConsensusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/2/29 14:53
 */
@Api(value = "舆情信息", tags = "舆情信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/es/consensus")
public class RiskConsensusController {

    @DubboReference(version = "1.0.0")
    private RemoteRiskConsensusService remoteRiskConsensusService;

    @ApiOperation(value = "保存舆情信息", notes = "保存舆情信息")
    @Anonymous
    @PostMapping(value = "/save")
    public Result<Integer> save(@RequestBody RiskConsensusDTO riskConsensusDTO) {
        return Result.success(remoteRiskConsensusService.save(CommonConverterUtil.beanCopy(riskConsensusDTO, RiskConsensusBO.class)));
    }

    @ApiOperation(value = "批量保存舆情信息", notes = "批量保存舆情信息")
    @Anonymous
    @PostMapping(value = "/saveList", consumes = "application/json")
    public Result<Integer> saveList(@RequestBody List<RiskConsensusDTO> list) {
        return Result.success(remoteRiskConsensusService.save(CommonConverterUtil.ListTransform(list, RiskConsensusBO.class)));
    }

    @ApiOperation(value = "通过Id删除舆情信息", notes = "通过Id删除舆情信息")
    @Anonymous
    @PostMapping(value = "/deleteById")
    public Result deleteById(@RequestBody RiskConsensusDTO riskConsensusDTO) {
        remoteRiskConsensusService.delete(riskConsensusDTO.getId());
        return Result.success();
    }

    @ApiOperation(value = "通过Ids删除舆情信息", notes = "通过Ids删除舆情信息")
    @Anonymous
    @PostMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestBody List<RiskConsensusDTO> list) {
        remoteRiskConsensusService.delete(list.stream().map(RiskConsensusDTO::getId).collect(Collectors.toList()));
        return Result.success();
    }

    @ApiOperation(value = "分页查询舆情信息", notes = "分页查询舆情信息")
    @Anonymous
    @GetMapping(value = "/search")
    public PageResult<RiskConsensusVO> search(PageBaseDTO pagebasedto) {
        Page<RiskConsensusBO> boPage = remoteRiskConsensusService.search(CommonConverterUtil.beanCopy(pagebasedto, PageBaseBO.class));
        PageResult<RiskConsensusBO> pageResult = PageResult.success(boPage);
        return CommonConverterUtil.PageTransform(pageResult, RiskConsensusVO.class);
    }

    @ApiOperation(value = "分页查询舆情信息-查询条件", notes = "分页查询舆情信息-查询条件")
    @Anonymous
    @GetMapping(value = "/searchForCondition")
    PageResult<RiskConsensusVO> searchForCondition(RiskConsensusPageQueryDTO queryDTO) {
        Page<RiskConsensusBO> boPage = remoteRiskConsensusService.search(CommonConverterUtil.beanCopy(queryDTO, RiskConsensusPageQueryBO.class));
        PageResult<RiskConsensusBO> pageResult = PageResult.success(boPage);
        return CommonConverterUtil.PageTransform(pageResult, RiskConsensusVO.class);
    }

    @ApiOperation(value = "分页查询舆情信息-高亮", notes = "分页查询舆情信息-高亮")
    @Anonymous
    @GetMapping(value = "/searchForHighlight")
    PageResult<RiskConsensusVO> searchForHighlight(RiskConsensusPageQueryDTO queryDTO) {
        Page<RiskConsensusBO> boPage = remoteRiskConsensusService.searchForHighlight(CommonConverterUtil.beanCopy(queryDTO, RiskConsensusPageQueryBO.class));
        PageResult<RiskConsensusBO> pageResult = PageResult.success(boPage);
        return CommonConverterUtil.PageTransform(pageResult, RiskConsensusVO.class);
    }

    @ApiOperation(value = "舆情聚合查询", notes = "舆情聚合查询")
    @Anonymous
    @GetMapping(value = "/aggregateForSentimentType")
    public Result<Map<String,Object>> aggregateForSentimentType(RiskConsensusPageQueryDTO queryDTO) {
        Map<String,Object> map = remoteRiskConsensusService.aggregateForSentimentType(CommonConverterUtil.beanCopy(queryDTO, RiskConsensusPageQueryBO.class));
        return Result.success(map);
    }
}

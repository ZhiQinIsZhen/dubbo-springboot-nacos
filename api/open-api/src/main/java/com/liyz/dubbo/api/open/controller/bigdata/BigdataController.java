package com.liyz.dubbo.api.open.controller.bigdata;

import com.liyz.dubbo.api.open.dto.bigdata.CompanyBaseDTO;
import com.liyz.dubbo.api.open.dto.bigdata.PageBaseDTO;
import com.liyz.dubbo.api.open.vo.bigdata.CompanyBasicVO;
import com.liyz.dubbo.common.core.result.PageResult;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.service.bigdata.bo.CompanyBasicBO;
import com.liyz.dubbo.service.bigdata.bo.PageBaseBO;
import com.liyz.dubbo.service.bigdata.remote.RemoteCompanyBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/20 10:54
 */
@Api(tags = "大数据")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/bigdata")
public class BigdataController {

    @DubboReference
    private RemoteCompanyBasicService remoteCompanyBasicService;

    @Anonymous
    @Limits(value = {@Limit(count = 2)})
    @ApiOperation("分页查询公司基本信息")
    @GetMapping("/company/page")
    public PageResult<CompanyBasicVO> companyPage(PageBaseDTO pageBaseDTO) {
        Page<CompanyBasicBO> page = remoteCompanyBasicService.page(CommonCloneUtil.objectClone(pageBaseDTO, PageBaseBO.class));
        return CommonCloneUtil.pageClone(PageResult.success(page), CompanyBasicVO.class);
    }

    @Anonymous
    @Limits(value = {@Limit(count = 2)})
    @ApiOperation("删除公司信息")
    @PostMapping("/company/delete")
    public Result<Boolean> companyDelete(@RequestBody CompanyBaseDTO companyBaseDTO) {
        remoteCompanyBasicService.deleteById(companyBaseDTO.getId());
        return Result.success(Boolean.TRUE);
    }
}

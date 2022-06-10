package com.liyz.dubbo.api.open.controller.excel;

import com.alibaba.excel.EasyExcel;
import com.liyz.dubbo.api.open.excel.FinancialSheetExcel;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.excel.listener.CommonReadRowListener;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 注释:excel
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/25 14:35
 */
@Api(tags = "Excel-测试")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Anonymous
    @ApiOperation("上传")
    @PostMapping("/upload")
    public Result<List> upload(MultipartFile file) throws IOException {
        CommonReadRowListener readListener = new CommonReadRowListener<>(FinancialSheetExcel.class);
        EasyExcel.read(file.getInputStream(), readListener).sheet().headRowNumber(0).doRead();
        return Result.success(readListener.getList());
    }
}

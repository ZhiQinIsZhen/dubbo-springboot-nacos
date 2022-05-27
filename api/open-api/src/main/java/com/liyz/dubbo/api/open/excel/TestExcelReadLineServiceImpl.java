package com.liyz.dubbo.api.open.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.liyz.dubbo.common.excel.service.AbstractExcelReadRowService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/25 16:20
 */
@Slf4j
@Getter
@Setter
public class TestExcelReadLineServiceImpl extends AbstractExcelReadRowService {

    @ExcelProperty("string")
    private String age;
    @ExcelProperty("date")
    private Date createTime;
    @ExcelProperty("doubleData")
    private Double balance;

    static {
        initFieldProperties(TestExcelReadLineServiceImpl.class);
    }
}

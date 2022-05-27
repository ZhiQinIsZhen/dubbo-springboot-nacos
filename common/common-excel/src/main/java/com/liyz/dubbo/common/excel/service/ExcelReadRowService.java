package com.liyz.dubbo.common.excel.service;

/**
 * 注释:excel读列的接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/27 9:31
 */
public interface ExcelReadRowService {

    /**
     * 注入属性
     *
     * @param rowName
     * @param rowValue
     */
    void invoke(String rowName, String rowValue);
}

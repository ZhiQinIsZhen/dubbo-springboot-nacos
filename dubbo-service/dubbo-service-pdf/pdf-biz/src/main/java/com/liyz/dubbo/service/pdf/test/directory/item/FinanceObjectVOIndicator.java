package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/28 10:37
 */
@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceObjectVOIndicator<T> implements Serializable {

    /**
     * 类型，type:1:主要财务数据;2:运营能力指标;3:债偿能力指标;4:文本框
     */
    private Integer type;
    /**
     * 关键财务指标名称
     */
    private String name;
    /**
     *
     */
    private T data;
}

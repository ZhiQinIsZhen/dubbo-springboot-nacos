package com.liyz.dubbo.service.pdf.pdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/7/19 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FooterParams {
    /**
     * 报告生成日期
     */
    private String genDate;
    /**
     * 开始有页眉的起始页
     */
    private Integer startPageNumber = 1;

}

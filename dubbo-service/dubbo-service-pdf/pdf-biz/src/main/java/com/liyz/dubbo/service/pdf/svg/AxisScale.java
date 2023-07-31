package com.liyz.dubbo.service.pdf.svg;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/8 10:17
 * 轴刻度
 */
@Data
@AllArgsConstructor
public class AxisScale {
    private double value;
    private String displayText;
}

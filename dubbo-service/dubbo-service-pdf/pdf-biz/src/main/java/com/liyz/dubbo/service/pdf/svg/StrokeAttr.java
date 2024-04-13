package com.liyz.dubbo.service.pdf.svg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/16 9:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrokeAttr {
    public static final StrokeAttr NONE = new StrokeAttr("none", 0);
    private String stroke;
    private double strokeWidth;

}

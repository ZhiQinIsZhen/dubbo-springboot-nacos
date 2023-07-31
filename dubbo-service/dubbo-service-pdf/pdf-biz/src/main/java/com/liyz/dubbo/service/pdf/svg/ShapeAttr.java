package com.liyz.dubbo.service.pdf.svg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/16 9:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShapeAttr {
    private Point2D coordinate;
    private Dimension2D size;


}

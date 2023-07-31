package com.liyz.dubbo.service.pdf.svg.shape;

import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.pdf.svg.ColorAttr;
import com.liyz.dubbo.service.pdf.svg.SvgDirection;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.xmlgraphics.java2d.Dimension2DDouble;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/21 17:43
 */
@Data
@AllArgsConstructor
public class SvgMultiBar {
    static final int BAR_WIDTH = 24;
    private SVGOMDocument doc;
    /**
     * 柱子朝向
     */
    private SvgDirection direction;
    private float step;
    private ColorAttr color;
    private double[] data;
    /**
     * 刻度
     */
    private Axis axis;
    /**
     * 包装柱子的容器的左上角绝对坐标
     */
    private Point2D containerCoordinate;
    /**
     * 第一个柱子相对容器左上角的偏移量
     */
    private Point2D offset;
    /**
     * 包装曲线的容器尺寸
     */
    private Dimension2D containerSize;
    private SvgMultiBar(){}
    public void draw(){
        for (int i = 0; i < data.length; i++) {
            if(SvgDirection.X == direction){
                double h = axis.calRatio(data[i]) * containerSize.getHeight();
                double x = containerCoordinate.getX() + offset.getX() + i * step;
                double y = containerCoordinate.getY() + offset.getY() + containerSize.getHeight();
                SvgBar svgBar = new SvgBar(
                        doc, new Dimension2DDouble(BAR_WIDTH, h),
                        new Point2D.Double(x, y), color
                );
                svgBar.drawRel();
            }else{
                throw new UnsupportedOperationException("暂不支持");
            }
        }
    }

    public SvgMultiBar deepCopy(){
        return BeanUtil.copyProperties(this, SvgMultiBar::new);
    }
}

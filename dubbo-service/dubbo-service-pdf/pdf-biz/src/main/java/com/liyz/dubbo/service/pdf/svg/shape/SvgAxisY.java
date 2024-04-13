package com.liyz.dubbo.service.pdf.svg.shape;

import com.liyz.dubbo.service.pdf.svg.AxisScale;
import com.liyz.dubbo.service.pdf.svg.SvgAttr;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Data;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMLineElement;
import org.apache.batik.anim.dom.SVGOMTextElement;
import org.w3c.dom.Element;

import java.awt.geom.Point2D;

/**
 * @Author ChenHao
 * @Date 2022/12/20 9:36
 */
@Data
public class SvgAxisY {
    private SVGOMDocument doc;
    /**
     * 高度
     */
    private float height;
    /**
     * 左下角坐标，相对于SVG原点的绝对坐标
     * 如果需要调整整个Y轴的位置改这个就OK
     */
    private Point2D coordinate;
    /**
     * 刻度尺
     */
    private Axis axis;
    /**
     * label
     */
    private String label;

    /**
     * Y轴是否在右侧 --默认在左侧
     * */
    private Boolean rightAxis = false;

    public SvgAxisY(SVGOMDocument doc, float height, Point2D coordinate, Axis axis, String label) {
        this.doc = doc;
        this.height = height;
        this.coordinate = coordinate;
        this.axis = axis;
        this.label = label;
    }


    public SvgAxisY(SVGOMDocument doc, float height, Point2D coordinate, Axis axis, String label,Boolean rightAxis) {
        this.doc = doc;
        this.height = height;
        this.coordinate = coordinate;
        this.axis = axis;
        this.label = label;
        this.rightAxis = rightAxis;
    }

    public void draw(){
        double x = coordinate.getX();
        double y = coordinate.getY();
        /*
         * 图表左上角y
         */
        float upY = (float) (y -height);
        /*
         * Y轴刻度间隔
         */
        float stepY = height/axis.getRegionNum();
        Element svg = doc.getDocumentElement();
        // 画Y轴实线
        SVGOMLineElement yLine = SvgUtils.createLine(doc);
        new SvgAttr(yLine).stroke("#D6DEFC", 1.2)
                .lineXy(x, upY - 10, x, y);
        svg.appendChild(yLine);

        // 画刻度
        String textAnchor = "end";
        double offsetx = x -13;
        if(rightAxis){
            offsetx = x+13;
            textAnchor = "start";
        }
        double offsety = upY+6;
        // 从上往下画
        for (int i = 0; i < axis.getLen(); i++) {
            AxisScale as = axis.getAxisScales()[i];
            SVGOMTextElement text = SvgUtils.createText(doc);
            text.setTextContent(as.getDisplayText());
            new SvgAttr(text)
                    .font("16", null)
                    .fill("#616998")
                    // 右对齐
                    .textAnchor(textAnchor)
                    .coordinate(offsetx, (stepY*i)+offsety);
            svg.appendChild(text);
        }

        // Y轴label
        double yLableX = x - 42;
        double yLableY = upY - 34;
        SVGOMTextElement yLabel = SvgUtils.createText(doc);
        yLabel.setTextContent(this.label);
        new SvgAttr(yLabel)
                .fill("#27243F")
                .font("18.23", null)
                .fontWeight("400")
                .coordinate(yLableX, yLableY);
        svg.appendChild(yLabel);
    }
}

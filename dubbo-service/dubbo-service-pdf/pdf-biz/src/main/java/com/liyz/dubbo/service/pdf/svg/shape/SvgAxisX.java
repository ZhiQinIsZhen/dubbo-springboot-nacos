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
import java.util.function.BiConsumer;

/**
 * @Author ChenHao
 * @Date 2022/12/20 9:36
 */
@Data
public class SvgAxisX {
    private SVGOMDocument doc;

    /**
     * 宽度
     */
    private float width;
    private float height;
    /**
     * 左下角坐标，相对于SVG原点的绝对坐标
     * 如果需要调整整个X轴的位置改这个就OK
     */
    private Point2D coordinate;
    /**
     * Y轴刻度尺，主要用来画X轴上面的虚线，每个Y轴刻度对应一条X轴虚线
     */
    private Axis axisY;
    /**
     * 第一列偏移量
     */
    private float firstOffset;
    /**
     * 每列间隔步长
     */
    private float step;
    /**
     * 每个X轴刻度下面的标题
     */
    private String[] titles;
    /**
     * 标题自定义设置
     */
    private BiConsumer<SVGOMTextElement, Point2D> titleCustomSetup;
    /**
     * 横坐标字号
     * */
    private String frontSize = "18";
    /**
     * X轴上的刻度线距离 --柱子的一半
     * */
    private int barWidth = 0;

    public SvgAxisX(SVGOMDocument doc, float width, float height, Point2D coordinate, Axis axisY, String[] titles, float firstOffset, float step) {
        this.doc = doc;
        this.width = width;
        this.height = height;
        this.coordinate = coordinate;
        this.axisY = axisY;
        this.titles = titles;
        this.firstOffset = firstOffset;
        this.step = step;
    }

    public SvgAxisX(SVGOMDocument doc, float width, float height, Point2D coordinate, Axis axisY, String[] titles, float firstOffset, float step,int barWidth,String frontSize) {
        this.doc = doc;
        this.width = width;
        this.height = height;
        this.coordinate = coordinate;
        this.axisY = axisY;
        this.titles = titles;
        this.firstOffset = firstOffset;
        this.step = step;
        this.barWidth = barWidth;
        this.frontSize = frontSize;
    }

    public void draw(){
        double x = coordinate.getX();
        double y = coordinate.getY();
        double upY = y - height;
        Element svg = doc.getDocumentElement();
        // 画实线
        SVGOMLineElement xLine = SvgUtils.createLine(doc);
        new SvgAttr(xLine)
                .stroke("#D6DEFC", 1.2)
                .lineXy(x, y, x+width, y);
        svg.appendChild(xLine);
        // 画虚线，从上往下画
        float stepY = height / axisY.getRegionNum();
        for (int i = 0; i < axisY.getRegionNum(); i++) {
            SVGOMLineElement xDottedLine = SvgUtils.createLine(doc);
            double xDottedLineY = upY + i * stepY;
            new SvgAttr(xDottedLine).stroke("#EFF1F5", 1)
                    .strokeDasharray("3.4 2.3")
                    .lineXy(x, xDottedLineY, x+width, xDottedLineY);
            svg.appendChild(xDottedLine);
        }

        // 画实线下面那N个小竖条和标题
        float h = 6;
        for (int i = 0; i < titles.length; i++) {
            SVGOMLineElement xMark1 = SvgUtils.createLine(doc);
            float x1 = (float) (x + firstOffset + (i * step));
            new SvgAttr(xMark1).stroke("#D6DEFC", 1.2)
                    .lineXy(x1-barWidth, y, x1-barWidth, y+h);
            svg.appendChild(xMark1);
            SVGOMTextElement titleText = SvgUtils.createText(doc);
            titleText.setTextContent(titles[i]);
            new SvgAttr(titleText)
                    .font(frontSize, null)
                    .fill("#27243F")
                    .textAnchor("middle")
                    .coordinate(x1-barWidth, y+30);
            if(titleCustomSetup != null){
                titleCustomSetup.accept(titleText, new Point2D.Double(x1,y));
            }
            svg.appendChild(titleText);
        }
    }


    /**
     * 画0轴
     * */
    public void draw(Boolean zeroFlag){
        double x = coordinate.getX();
        double y = coordinate.getY();
        double upY = y - height;
        Element svg = doc.getDocumentElement();
        /*
         * Y轴刻度间隔
         */

        double zero = y;

        float stepY = height/axisY.getRegionNum();
        for (int i = 0; i < axisY.getLen(); i++) {
            AxisScale as = axisY.getAxisScales()[i];
            if(as.getValue()==0){
                zero = (stepY*i);
            }
        }

        // 找 0 轴 -实线
        SVGOMLineElement xLine = SvgUtils.createLine(doc);
        new SvgAttr(xLine)
                .stroke("#D6DEFC", 1.2)
                .lineXy(x, upY + zero, x+width, upY + zero);
        svg.appendChild(xLine);

        // 画虚线，从上往下画
        for (int i = 0; i < axisY.getLen(); i++) {
            SVGOMLineElement xDottedLine = SvgUtils.createLine(doc);
            double xDottedLineY = upY + i * stepY;
            new SvgAttr(xDottedLine).stroke("#EFF1F5", 1)
                    .strokeDasharray("3.4 2.3")
                    .lineXy(x, xDottedLineY, x+width, xDottedLineY);
            svg.appendChild(xDottedLine);
        }

        // 画实线下面那标题
        float h = 6;
        for (int i = 0; i < titles.length; i++) {
            float x1 = (float) (x + firstOffset + (i * step));
            SVGOMTextElement titleText = SvgUtils.createText(doc);
            titleText.setTextContent(titles[i]);
            new SvgAttr(titleText)
                    .font(frontSize, null)
                    .fill("#27243F")
                    .textAnchor("middle")
                    .coordinate(x1-barWidth, y+30);
            if(titleCustomSetup != null){
                titleCustomSetup.accept(titleText, new Point2D.Double(x1,y));
            }
            svg.appendChild(titleText);
        }
    }
}

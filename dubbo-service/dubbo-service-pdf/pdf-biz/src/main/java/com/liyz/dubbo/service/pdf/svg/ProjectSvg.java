package com.liyz.dubbo.service.pdf.svg;

import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Data;
import org.apache.batik.anim.dom.*;
import org.w3c.dom.Element;

import java.awt.geom.Point2D;

/**
 * description: TODO
 * author: huanglb
 * date 2023/1/16 15:38
 */
public class ProjectSvg {


    @Data
    public static class GradientStop {
        private String offset;
        private String stopColor;
        private String stopOpacity;

        public GradientStop(String offset, String stopColor, String stopOpacity) {
            this.offset = offset;
            this.stopColor = stopColor;
            this.stopOpacity = stopOpacity;
        }
    }

    public static class LableTitle {
        private SVGOMDocument doc;
        private String title;
        private ColorAttr color;
        private Point2D point;

        public LableTitle(SVGOMDocument doc, String title, ColorAttr color, Point2D point) {
            this.doc = doc;
            this.title = title;
            this.color = color;
            this.point = point;
        }

        public void draw() {
            Element root = doc.getDocumentElement();
            double x = point.getX();
            double y = point.getY();
            SVGOMTextElement text1 = SvgUtils.createText(doc);
            new SvgAttr(text1)
                    .font("14", null)
                    .coordinate(x + 5, y + 10.4);
            text1.setTextContent(title);
            root.appendChild(text1);
        }
    }

    /**
     *
     */
    public static class PolylineTitle {
        private SVGOMDocument doc;
        private String title;
        private ColorAttr color;
        private Point2D point;

        public PolylineTitle(SVGOMDocument doc, String title, ColorAttr color, Point2D point) {
            this.doc = doc;
            this.title = title;
            this.color = color;
            this.point = point;
        }

        public void draw() {
            Element root = doc.getDocumentElement();
            double x = point.getX();
            double y = point.getY();
            SVGOMRectElement rect = SvgUtils.createRect(doc);
            new SvgAttr(rect).stroke("none", 0)
                    .fill(color)
                    .original(x, y + 2.9, 19, 3.2)
                    .rx(1.6);

            SVGOMCircleElement circle = SvgUtils.createCircle(doc);
            double circleStrokeWidth = 2.13;
            double circler = 5.34;
            new SvgAttr(circle)
                    .stroke("#FFFFFF", circleStrokeWidth)
                    .fill(color)
                    .circle(x + 19 / 2.0, y + 9 / 2.0, circler);
            root.appendChild(rect);
            root.appendChild(circle);

            SVGOMTextElement text1 = SvgUtils.createText(doc);
            new SvgAttr(text1)
                    .font("14", null)
                    .coordinate(x + 27, y + 10.4);
            text1.setTextContent(title);
            root.appendChild(text1);
        }
    }

    /**
     *
     */
    public static class BarTitle {
        private SVGOMDocument doc;
        private String title;
        private ColorAttr color;
        private Point2D point;

        public BarTitle(SVGOMDocument doc, String title, ColorAttr color, Point2D point) {
            this.doc = doc;
            this.title = title;
            this.color = color;
            this.point = point;
        }

        public void draw() {
            SVGOMRectElement rect1 = SvgUtils.createRect(doc);
            double x = point.getX();
            double y = point.getY();
            new SvgAttr(rect1)
                    .original(x, y, 19, 9)
                    .fill(color)
                    .rx(4.27);

            SVGOMTextElement text1 = SvgUtils.createText(doc);
            text1.setTextContent(title);
            new SvgAttr(text1)
                    .font("14", null)
                    .coordinate(x + 27, y + 10.4);

            doc.getDocumentElement().appendChild(rect1);
            doc.getDocumentElement().appendChild(text1);
        }
    }

    /**
     *
     */
    @Data
    public abstract static class BaseAxisY {

        protected SVGOMDocument doc;
        /**
         * 高度
         */
        protected float height;
        /**
         * 左下角坐标，相对于SVG原点的绝对坐标
         * 如果需要调整整个Y轴的位置改这个就OK
         */
        protected Point2D coordinate;
        /**
         * 刻度尺
         */
        protected Axis axis;
        /**
         * label
         */
        protected String label;

        public BaseAxisY(SVGOMDocument doc, float height, Point2D coordinate, Axis axis, String label) {
            this.doc = doc;
            this.height = height;
            this.coordinate = coordinate;
            this.axis = axis;
            this.label = label;
        }

        public abstract void draw();
    }

    /**
     *
     */
    public static class LeftAxisY extends BaseAxisY {

        public LeftAxisY(SVGOMDocument doc, float height, Point2D coordinate, Axis axis, String label) {
            super(doc, height, coordinate, axis, label);
        }

        @Override
        public void draw() {
            double x = coordinate.getX();
            double y = coordinate.getY();
            /*
             * 图表左上角y
             */
            float upY = (float) (y - height);
            /*
             * Y轴刻度间隔
             */
            float stepY = height / axis.getRegionNum();
            Element svg = doc.getDocumentElement();
            // 画Y轴实线
            SVGOMLineElement yLine = SvgUtils.createLine(doc);
            new SvgAttr(yLine).stroke("#D6DEFC", 1.2)
                    .lineXy(x, upY - 10, x, y);
            svg.appendChild(yLine);

            // 画刻度
            double offsetx = x - 6;
            double offsety = upY + 6;
            // 从上往下画
            for (int i = 0; i < axis.getLen(); i++) {
                AxisScale as = axis.getAxisScales()[i];
                SVGOMTextElement text = SvgUtils.createText(doc);
                text.setTextContent(as.getDisplayText());
                new SvgAttr(text)
                        .font("13", null)
                        .fill("#616998")
                        // 右对齐
                        .textAnchor("end")
                        .coordinate(offsetx, (stepY * i) + offsety);
                svg.appendChild(text);
            }

            // Y轴label
            double yLableX = x - 0;
            double yLableY = upY - 34;
            SVGOMTextElement yLabel = SvgUtils.createText(doc);
            yLabel.setTextContent(this.label);
            new SvgAttr(yLabel)
                    .fill("#27243F")
                    .font("14.5", null)
                    .fontWeight("400").textAnchor("end")
                    .coordinate(yLableX, yLableY);
            svg.appendChild(yLabel);
        }
    }

    /**
     *
     */
    public static class RightAxisY extends BaseAxisY {

        public RightAxisY(SVGOMDocument doc, float height, Point2D coordinate, Axis axis, String label) {
            super(doc, height, coordinate, axis, label);
        }

        @Override
        public void draw() {
            double x = coordinate.getX();
            double y = coordinate.getY();
            /*
             * 图表左上角y
             */
            float upY = (float) (y - height);
            /*
             * Y轴刻度间隔
             */
            float stepY = height / axis.getRegionNum();
            Element svg = doc.getDocumentElement();
            // 画Y轴实线
            SVGOMLineElement yLine = SvgUtils.createLine(doc);
            new SvgAttr(yLine).stroke("#D6DEFC", 1.2)
                    .lineXy(x, upY - 10, x, y);
            svg.appendChild(yLine);

            // 画刻度
            double offsetx = x + 6;
            double offsety = upY + 6;
            // 从上往下画
            for (int i = 0; i < axis.getLen(); i++) {
                AxisScale as = axis.getAxisScales()[i];
                SVGOMTextElement text = SvgUtils.createText(doc);
                text.setTextContent(as.getDisplayText());
                new SvgAttr(text)
                        .font("13", null)
                        .fill("#616998")
                        // 右对齐
                        .textAnchor("start")
                        .coordinate(offsetx, (stepY * i) + offsety);
                svg.appendChild(text);
            }

            // Y轴label
            double yLableX = x + 0;
            double yLableY = upY - 34;
            SVGOMTextElement yLabel = SvgUtils.createText(doc);
            yLabel.setTextContent(this.label);
            new SvgAttr(yLabel)
                    .fill("#27243F")
                    .font("14.5", null)
                    .fontWeight("400").textAnchor("start")
                    .coordinate(yLableX, yLableY);
            svg.appendChild(yLabel);
        }
    }

    /**
     *
     */
    @Data
    public static class AcrossAxisX {

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
         * 每列间隔步长
         */
        private float step;

        /**
         * 每个X轴刻度下面的标题
         */
        private String[] lables;

        private int maxtitles = 15;
        /**
         * 第一列偏移量
         */
        private float firstOffset;

        private int interval;

        private int rotate = -45;

        private boolean ifBgDottedLine = true;

        public AcrossAxisX(SVGOMDocument doc, float width, float height, Point2D coordinate, Axis axisY, String[] lables, int rotate, boolean ifBgDottedLine) {
            this.doc = doc;
            this.width = width;
            this.height = height;
            this.coordinate = coordinate;
            this.axisY = axisY;
            this.lables = lables;
            this.rotate = rotate;
            this.ifBgDottedLine = ifBgDottedLine;
            this.step = width * 1.0f / (lables.length + 1);
            this.firstOffset = step;
            int len = lables.length;
            this.interval = new Double(Math.ceil(len * 1.0 / maxtitles)).intValue();
            if (this.interval == 0) {
                this.interval = 1;
            }
        }

        public void draw() {

            double x = coordinate.getX();
            double y = coordinate.getY();
            double upY = y - height;
            Element svg = doc.getDocumentElement();

            // 画X实线
            SVGOMLineElement xLine = SvgUtils.createLine(doc);
            new SvgAttr(xLine)
                    .stroke("#D6DEFC", 1.2)
                    .lineXy(x, y, x + width, y);
            svg.appendChild(xLine);

            // 画虚线，从上往下画
            float stepY = height / axisY.getRegionNum();
            if (ifBgDottedLine) {
                for (int i = 0; i < axisY.getRegionNum(); i++) {
                    SVGOMLineElement xDottedLine = SvgUtils.createLine(doc);
                    double xDottedLineY = upY + i * stepY;
                    new SvgAttr(xDottedLine).stroke("#EFF1F5", 1)
                            .strokeDasharray("3.4 2.3")
                            .lineXy(x, xDottedLineY, x + width, xDottedLineY);
                    svg.appendChild(xDottedLine);
                }
            }


            // 画实线下面那N个小竖条和标题
            float h = 6;
            for (int i = 0; i < lables.length; i++) {
                if (i % interval == 0) {
                    SVGOMLineElement xMark1 = SvgUtils.createLine(doc);
                    float x1 = (float) (x + firstOffset + (i * step));
                    new SvgAttr(xMark1).stroke("#D6DEFC", 1.2)
                            .lineXy(x1, y, x1, y + h);
                    svg.appendChild(xMark1);
                    SVGOMTextElement titleText = SvgUtils.createText(doc);
                    titleText.setTextContent(lables[i]);
                    new SvgAttr(titleText)
                            .font("12.5", null)
                            .fill("#27243F")
                            .textAnchor("middle")
                            .coordinate((x1 - 0), y + 25)
                            .transform("rotate(" + rotate + "," + (x1 - 0) + ", " + (y + 25) + ")")
                            .fontWeight("400");
                    svg.appendChild(titleText);
                }
            }
        }
    }
}

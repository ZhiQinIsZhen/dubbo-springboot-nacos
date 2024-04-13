package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.shape.SvgBar;
import com.liyz.dubbo.service.pdf.utils.BezierUtils;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.batik.anim.dom.SVGOMLinearGradientElement;
import org.apache.batik.anim.dom.SVGOMPathElement;
import org.apache.batik.anim.dom.SVGOMStopElement;
import org.apache.batik.dom.svg.SVGOMPoint;
import org.apache.xmlgraphics.java2d.Dimension2DDouble;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.svg.SVGPathSegList;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * description: TODO
 * author: huanglb
 * date 2023/1/16 15:04
 */

@Getter
public abstract class AbstractProjectGenSvg extends AbstractDomGenSvg {

    protected static final String PURPLE = "#776DE6";

    protected static final String SKY_BLUE = "#4FA9EB";

    protected static final String SKY_BLUE2 = "#33BED1";

    protected static final String RED = "#FF8047";

    protected static final String RED2 = "#F55B77";

    protected static final String DEFS_Y_ID_SVG_ID_1 = "defs_y_id_svg_id_1" + ThreadLocalRandom.current().nextInt();

    protected static final String DEFS_Y_ID_SVG_ID_2 = "defs_y_id_svg_id_2" + ThreadLocalRandom.current().nextInt();

    private int WIDTH;

    private int HEIGHT;

    private int CHART_WIDTH;

    private int CHART_HEIGHT;

    private int CHART_LEFT_INTERVAL;

    private int CHART_RIGHT_INTERVAL;

    private int CHART_UP_INTERVAL;

    private int CHART_DOWN_INTERVAL;


    private int MAX_LEN = 15;


    //刻度
    @Getter
    private Axis axis1;
    private String label1;
    //刻度
    @Getter
    private Axis axis2;

    private String label2;
    /**
     * 标题
     */
    @Setter
    private String[] lables;

    @Setter
    private int rotate = 0;

    @Setter
    private boolean ifBgDottedLine = true;

    public AbstractProjectGenSvg(Dimension2D size) {
        super(size);
    }

    /**
     * @param chartWidth
     * @param chartHeight
     * @param chartLeftInterval
     * @param chartRightInterval
     * @param chartUpInterval
     * @param chartDownInterval
     */
    public AbstractProjectGenSvg(int chartWidth, int chartHeight,
                                 int chartLeftInterval, int chartRightInterval,
                                 int chartUpInterval, int chartDownInterval) {
        super(new Dimension(chartWidth + chartLeftInterval + chartRightInterval, chartHeight + chartUpInterval + chartDownInterval));
        WIDTH = chartWidth + chartLeftInterval + chartRightInterval;
        HEIGHT = chartHeight + chartUpInterval + chartDownInterval;
        CHART_WIDTH = chartWidth;
        CHART_HEIGHT = chartHeight;
        CHART_LEFT_INTERVAL = chartLeftInterval;
        CHART_RIGHT_INTERVAL = chartRightInterval;
        CHART_UP_INTERVAL = chartUpInterval;
        CHART_DOWN_INTERVAL = chartDownInterval;
    }

    /**
     * @param axis
     * @param ssss
     */
    protected void setAxis1AndLabel1(Axis axis, String ssss) {
        this.axis1 = axis;
        this.label1 = ssss;
    }

    /**
     * @param axis
     * @param ssss
     */
    protected void setAxis2AndLabel2(Axis axis, String ssss) {
        this.axis2 = axis;
        this.label2 = ssss;
    }


    /**
     * @param id
     * @param ls
     */
    protected void defsYId(String id, List<ProjectSvg.GradientStop> ls) {
        if (CollectionUtils.isEmpty(ls)) {
            return;
        }
        SVGOMLinearGradientElement linearGradient = SvgUtils.createLinearGradient(doc);
        new SvgAttr(linearGradient)
                .id(id)
                .linearGradient("50%", "0%", "50%", "100%");

        for (ProjectSvg.GradientStop l : ls) {
            SVGOMStopElement stop1 = SvgUtils.createStop(doc);
            new SvgAttr(stop1).stop(l.getOffset(), l.getStopColor(), l.getStopOpacity());
            linearGradient.appendChild(stop1);
        }
        SvgUtils.appendToDefs(doc, linearGradient);
    }

    /**
     * @param ls
     * @param color
     * @param axis
     */
    protected void drawPolyline(List<Number> ls, ColorAttr color, Axis axis) {
        drawline(ls, color, axis, LineEnum.POLYLINE);
    }

    /**
     * @param ls
     * @param color
     * @param axis
     */
    protected void drawCurve(List<Number> ls, ColorAttr color, Axis axis) {
        drawline(ls, color, axis, LineEnum.CURVE);
    }

    /**
     * @param ls
     * @param color
     * @param axis
     */
    private void drawline(List<Number> ls, ColorAttr color, Axis axis, LineEnum lineEnum) {
        double step = CHART_WIDTH * 1.0f / (lables.length + 1);
        int len = ls.size();
        double offset = step;
        Point2D.Double[] points = new Point2D.Double[len];
        for (int i = 0; i < len; i++) {

            Number number = ls.get(i);
            if (Objects.nonNull(number)) {
                double num = ls.get(i).doubleValue();
                double h = axis.calRatio(num) * CHART_HEIGHT;
                double x = CHART_LEFT_INTERVAL + offset + i * step;
                double y = CHART_UP_INTERVAL + 0 + CHART_HEIGHT - h;
                points[i] = new Point2D.Double(x, y);
            } else {
                points[i] = null;
            }
        }
        double strokeWidth = step / (45);
        List<Point2D.Double[]> cPoints = continuousPoints(points);
        for (Point2D.Double[] cPoint : cPoints) {
            SVGOMPoint[] line = new SVGOMPoint[cPoint.length];
            for (int i = 0; i < cPoint.length; i++) {
                line[i] = new SVGOMPoint(
                        new Double(cPoint[i].getX()).floatValue(),
                        new Double(cPoint[i].getY()).floatValue());
            }
            if (lineEnum == LineEnum.CURVE) {
                drawCurve(line, new StrokeAttr(color.getColor(), strokeWidth), ColorAttr.NONE);
            } else {
                drawPolyline(line, new StrokeAttr(color.getColor(), strokeWidth), ColorAttr.NONE);
            }
        }
        double r = step / (30);
        int interval = new Double(Math.ceil(len * 1.0 / MAX_LEN)).intValue();
        if (interval == 0) {
            interval = 1;
        }
        //画点
        for (int i = 0; i < points.length; i++) {
            if (i % interval == 0) {
                Point2D.Double point = points[i];
                if (Objects.nonNull(point)) {
                    drawCircle(point, r, new StrokeAttr("#FFFFFF", 1.1), color);
                }
            }
        }
    }

    /**
     * @param line
     * @param strokeAttr
     * @param none
     * @return
     */
    protected SVGOMPathElement drawCurve(SVGOMPoint[] line, StrokeAttr strokeAttr, ColorAttr none) {
        SVGOMPathElement path = SvgUtils.createPath(doc);
        new SvgAttr(path).fill(none)
                .stroke(strokeAttr.getStroke(), strokeAttr.getStrokeWidth());
        SVGPathSegList pathSegList = path.getPathSegList();
        float prevAbsX = 0, prevAbsY = 0;
        for (int i = 0; i < line.length; i++) {
            SVGOMPoint point = line[i];
            float absX = point.getX();
            float absY = point.getY();
            if (i == 0) {
                pathSegList.appendItem(
                        path.createSVGPathSegMovetoAbs(absX, absY)
                );
            } else {

                // 当前Y与上一个Y的差
                float diffX = absX - prevAbsX;
                float diffY = absY - prevAbsY;
                float[] points = calPointRel(diffX, diffY);
                pathSegList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                points[0], points[1],
                                points[2], points[3],
                                points[4], points[5])
                );
            }
            prevAbsX = absX;
            prevAbsY = absY;
            // 画圆点
        }
        svg.appendChild(path);
        return path;
    }

    protected float[] calPointRel(float relX, float relY) {
        return BezierUtils.calCubicPointRelWithX(relX, relY, 0, 0.5);
    }

    /**
     * @param points
     * @return
     */
    protected List<Point2D.Double[]> continuousPoints(Point2D.Double[] points) {

        List<Integer> integers = Lists.newArrayList();
        for (int i = 0; i < points.length; i++) {
            Point2D.Double point = points[i];
            if (Objects.isNull(point)) {
                integers.add(i);
            }
        }
        int start = 0;
        int size = integers.size();
        List<Point2D.Double[]> cPoints = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            int index = integers.get(i);
            if (start != index) {
                Point2D.Double[] cccPoints = buildContinuousPoints(start, index - 1, points);
                cPoints.add(cccPoints);
                //System.out.println("开始:" + start + "结束:" + (index - 1));
            }
            //System.out.println("null:" + index);
            start = index + 1;
        }

        int maxIndex = points.length - 1;
        if (size <= 0 || integers.get(size - 1) < maxIndex) {
            Point2D.Double[] cccPoints = buildContinuousPoints(start, maxIndex, points);
            cPoints.add(cccPoints);
        }

        return cPoints;
    }

    /**
     * @param start
     * @param end
     * @param points
     * @return
     */
    private Point2D.Double[] buildContinuousPoints(int start, int end, Point2D.Double[] points) {
        int size = end - start + 1;
        Point2D.Double[] newPoints = new Point2D.Double[size];
        int index = 0;
        for (int i = start; i <= end; i++) {
            newPoints[index] = points[i];
            index++;
        }
        return newPoints;
    }


    /**
     * 画柱子
     */
    protected void drawBar(List<List<Number>> ls, ColorAttr[] colorAttrs, Axis axis) {


        double step = CHART_WIDTH * 1.0f / (lables.length + 1);
        double offset = step;
        double halfOffset = offset / 2;
        int size = ls.size();
        double barWidth = step / (size * 2.3);
        double barInterval = 0;
        double startInterval = (step - (barWidth * size) + barInterval * (size - 1)) / 2;
        for (int i = 0; i < size; i++) {
            List<Number> list = ls.get(i);
            ColorAttr color = colorAttrs[i];
            for (int j = 0; j < list.size(); j++) {
                double num = Objects.isNull(list.get(j)) ? 0d : list.get(j).doubleValue();
                double h = axis.calRatio(num) * CHART_HEIGHT;
                double x = CHART_LEFT_INTERVAL + offset - halfOffset + startInterval + j * step + (i) * barWidth;
                double y = CHART_UP_INTERVAL + 0 + CHART_HEIGHT;
                float radius = new Double(barWidth / 4).floatValue();
                SvgBar svgBar = new SvgBar(
                        doc, new Dimension2DDouble(barWidth, h),
                        new Point2D.Double(x, y), radius
                );
                svgBar.setColor(color);
                svgBar.drawRel();
            }
        }
    }

    /**
     * 通用话边框
     */
    protected void drawOutlineBorder() {
        ShapeAttr shapeAttr = new ShapeAttr(
                new Point(0, 0),
                new Dimension2DDouble(size.getWidth(), size.getHeight()));
        StrokeAttr strokeAttr = new StrokeAttr("#EAEFF4", 1);
        ColorAttr colorAttr = new ColorAttr("#FBFCFD");
        drawRect(shapeAttr, strokeAttr, colorAttr, 0);
    }


    protected void drawXY() {
        if (Objects.nonNull(axis1)) {
            new ProjectSvg.LeftAxisY(doc, CHART_HEIGHT, new Point(CHART_LEFT_INTERVAL, HEIGHT - CHART_DOWN_INTERVAL), axis1, label1).draw();
        }
        if (Objects.nonNull(axis2)) {
            new ProjectSvg.RightAxisY(doc, CHART_HEIGHT, new Point(WIDTH - CHART_RIGHT_INTERVAL, HEIGHT - CHART_DOWN_INTERVAL), axis2, label2).draw();
        }

        new ProjectSvg.AcrossAxisX(doc, CHART_WIDTH, CHART_HEIGHT, new Point(CHART_LEFT_INTERVAL, HEIGHT - CHART_DOWN_INTERVAL), axis1, lables, rotate, ifBgDottedLine).draw();
    }

    /**
     *
     */
    protected void defs() {
    }
}

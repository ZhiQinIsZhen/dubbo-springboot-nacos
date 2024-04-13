package com.liyz.dubbo.service.pdf.svg.shape;

import com.liyz.dubbo.service.pdf.svg.ColorAttr;
import com.liyz.dubbo.service.pdf.svg.SvgAttr;
import com.liyz.dubbo.service.pdf.utils.SvgNumUtils;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Data;
import lombok.Setter;
import org.apache.batik.anim.dom.*;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGPath;
import org.apache.batik.svggen.SVGSyntax;
import org.w3c.dom.svg.SVGPathSegList;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.batik.util.SVGConstants.*;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/8 17:53
 */
@Data
public class SvgBar {
    /**
     * UI那边统一圆角半径是8
     */
    static final float RADIUS = 8f;
    /****构造参数****/
    private SVGOMDocument doc;
    /**
     * 柱子宽高
     */
    private Dimension2D size;
    /**
     * 柱子左下角坐标
     */
    private Point2D coordinate;
    @Setter
    private float radius;
    @Setter
    private ColorAttr color = ColorAttr.NONE;
    /******其他属性********/
    float halfRadius;
    boolean isSmall = false;
    float height;
    float width;
    float startYpoint;
    float startXpoint;


    public SvgBar(SVGOMDocument doc, Dimension2D size, Point2D coordinate) {
        this(doc, size, coordinate, RADIUS);
    }
    public SvgBar(SVGOMDocument doc, Dimension2D size, Point2D coordinate, ColorAttr color) {
        this(doc, size, coordinate, RADIUS);
        this.color = color;
    }
    public SvgBar(SVGOMDocument doc, Dimension2D size, Point2D coordinate, float radius) {
        this.doc = doc;
        this.size = size;
        this.coordinate = coordinate;
        this.radius = radius;

        height = (float) size.getHeight();
        width = (float) size.getWidth();
        startYpoint = (float) coordinate.getY();
        startXpoint = (float) coordinate.getX();
        if(size.getHeight() < radius){
            // 高度比圆角半径还低需要特殊处理
            isSmall = true;
            // path起点往下移
            startYpoint = startYpoint + radius;
            height = (float) size.getHeight() + radius;
        }
        halfRadius = radius / 2;
    }

    public static void main(String[] args) {
        int width = 24;

        SVGOMDocument doc = SvgUtils.createDoc();
        SVGOMElement root = (SVGOMElement) doc.getDocumentElement();
        SvgAttr rootAttr = new SvgAttr(root);
        rootAttr.size(400, 400);

        new SvgBar(doc, new Dimension(width, 80), new Point(40, 100)).drawRel();
        new SvgBar(doc, new Dimension(width, 50), new Point(80, 100)).drawRel();
        new SvgBar(doc, new Dimension(width, 25), new Point(120, 100)).drawAbs();
        new SvgBar(doc, new Dimension(width, 8), new Point(160, 100)).drawAbs();
        new SvgBar(doc, new Dimension(width, 6), new Point(200, 100)).drawAbs();
        new SvgBar(doc, new Dimension(width, 4), new Point(240, 100)).drawAbs();
        new SvgBar(doc, new Dimension(width, 2), new Point(280, 100)).drawAbs();
        new SvgBar(doc, new Dimension(width, 0), new Point(320, 100)).drawAbs();
        SvgUtils.output(doc);
    }


    /**
     * 原生DOM API，使用绝对路径
     */
    public void drawAbs() {
        SVGOMPathElement path = SvgUtils.createPath(doc);
        SvgAttr pathAttr = new SvgAttr(path);
        pathAttr.fill(color).stroke("none", 0);
        Path2D path2D = new Path2D.Double();

        path2D.moveTo(startXpoint, startYpoint);
        // 向上画一条直线
        path2D.lineTo(startXpoint, startYpoint - height + radius);
        // 获得当前坐标点，就是上一条曲线命令的终点坐标
        Point2D currentPoint = path2D.getCurrentPoint();
        // 画一条3次贝塞尔曲线（左边）
        path2D.curveTo(
                currentPoint.getX(), currentPoint.getY() - halfRadius,
            currentPoint.getX() + halfRadius, currentPoint.getY() - radius,
            currentPoint.getX() + radius, currentPoint.getY() - radius
        );
        currentPoint = path2D.getCurrentPoint();
        // 向右画一条线
        path2D.lineTo(startXpoint + width - radius, currentPoint.getY());

        currentPoint = path2D.getCurrentPoint();
        // 画一条3次贝塞尔曲线（右边）
        path2D.curveTo(
                currentPoint.getX() + halfRadius, currentPoint.getY(),
                currentPoint.getX() + radius, currentPoint.getY() + halfRadius,
                currentPoint.getX() + radius, currentPoint.getY() + radius
        );
        currentPoint = path2D.getCurrentPoint();
        // 向下画一条直线
        path2D.lineTo(currentPoint.getX(), startYpoint);
        path2D.closePath();
        // 转换成d的值
        pathAttr.d(toSVGPathData(path2D));
        if(isSmall){
            processSmallBar(pathAttr);
        }
        doc.getDocumentElement().appendChild(path);
    }

    /**
     * 使用batik svg dom api，使用相对路径
     */
    public void drawRel() {
        SVGOMPathElement path = SvgUtils.createPath(doc);
        SvgAttr pathAttr = new SvgAttr(path);
        pathAttr.fill(color).stroke("none", 0);
        SVGPathSegList segList = path.getPathSegList();
        segList.appendItem(
                path.createSVGPathSegMovetoAbs(startXpoint, startYpoint)
        );
        segList.appendItem(
                path.createSVGPathSegLinetoVerticalRel(-(height - radius))
        );
        segList.appendItem(
                path.createSVGPathSegCurvetoCubicRel(
                        radius, -radius,    // 终点
                        0, -halfRadius, // 控制点1
                        halfRadius, -radius // 控制点2

                )
        );
        segList.appendItem(
                path.createSVGPathSegLinetoHorizontalRel((float) size.getWidth()-2*radius)
        );
        segList.appendItem(
                path.createSVGPathSegCurvetoCubicRel(
                        radius, radius,
                        halfRadius, 0,
                        radius, halfRadius
                )
        );
        segList.appendItem(
                path.createSVGPathSegLinetoVerticalRel(height - radius)
        );
        if(isSmall){
            // clip-path属性优先于translate属性
            // 柱子高度比圆角半径还小，设置裁剪区域
            processSmallBar(pathAttr);
        }
        doc.getDocumentElement().appendChild(path);
    }


    /**
     * 使用batik svg dom api，使用相对路径
     */
    public void drawRelDown(float zeroY,Boolean downFlag) {
        SVGOMPathElement path = SvgUtils.createPath(doc);
        SvgAttr pathAttr = new SvgAttr(path);
        pathAttr.fill(color).stroke("none", 0);
        SVGPathSegList segList = path.getPathSegList();
        //以 X 为起始点 0轴Y坐标 开始画
        segList.appendItem(
                path.createSVGPathSegMovetoAbs(startXpoint, zeroY)
        );
        //画一条竖线 --长度是(高度 - 半径) =即 减去半径之后 柱子的长  向上画就是 - 的
        if(downFlag){
            //当高度有弧度高的时候
            if(!isSmall){
                //代表要朝下画
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel((height - radius))
                );
                segList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                radius, radius,    // 终点
                                0, halfRadius, // 控制点1
                                halfRadius, radius // 控制点2

                        )
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoHorizontalRel((float) size.getWidth()-2*radius)
                );
                segList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                radius, -radius,
                                halfRadius, 0,
                                radius, -halfRadius
                        )
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(-(height - radius))
                );
            }
            //高度么有弧度高的时候
            if(isSmall){
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(height- radius)
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoHorizontalRel((float) size.getWidth())
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(-(height- radius))
                );
            }
//            if(isSmall){
//                // clip-path属性优先于translate属性
//                // 柱子高度比圆角半径还小，设置裁剪区域
//                processSmallBar(pathAttr);
//            }

        }else {
            //高度比弧度高的时候
            if(!isSmall){
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(-(height - radius))
                );
                segList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                radius, -radius,    // 终点
                                0, -halfRadius, // 控制点1
                                halfRadius, -radius // 控制点2

                        )
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoHorizontalRel((float) size.getWidth()-2*radius)
                );
                segList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                radius, radius,
                                halfRadius, 0,
                                radius, halfRadius
                        )
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(height - radius)
                );
//                if(isSmall){
//                    // clip-path属性优先于translate属性
//                    // 柱子高度比圆角半径还小，设置裁剪区域
//                    processSmallBar(pathAttr);
//                }
            }
            if(isSmall){
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel(-(height- radius))
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoHorizontalRel((float) size.getWidth())
                );
                segList.appendItem(
                        path.createSVGPathSegLinetoVerticalRel((height- radius))
                );
            }
        }
        doc.getDocumentElement().appendChild(path);
    }

    private void processSmallBar(SvgAttr pathAttr) {
        // clip-path属性优先于translate属性
        // 柱子高度比圆角半径还小，设置裁剪区域
        SVGOMClipPathElement clipPath = SvgUtils.createClipPath(doc);
        String clipPathId = "clipPathId" + ThreadLocalRandom.current().nextInt();
        new SvgAttr(clipPath).id(clipPathId);
        // 设置显示区域，区域外的不会显示，完美解决：柱子高度比圆角半径还小情况下，柱子形状保持一致的问题
        SVGOMRectElement rect = SvgUtils.createRect(doc);
        new SvgAttr(rect).original(coordinate.getX(), coordinate.getY() - size.getHeight(), size.getWidth(), size.getHeight());
        clipPath.appendChild(rect);
        doc.getDocumentElement().appendChild(clipPath);
        pathAttr.clipPath("url(#" + clipPathId + ")");
    }

    /**
     * @see SVGPath#toSVGPathData(Shape, SVGGeneratorContext)
     */
    public static String toSVGPathData(Shape path) {
        StringBuilder d = new StringBuilder( 40 );
        PathIterator pi = path.getPathIterator(null);
        float[] seg = new float[6];
        int segType = 0;
        while (!pi.isDone()) {
            segType = pi.currentSegment(seg);
            switch(segType) {
                case PathIterator.SEG_MOVETO:
                    d.append(PATH_MOVE);
                    appendPoint(d, seg[0], seg[1]);
                    break;
                case PathIterator.SEG_LINETO:
                    d.append(PATH_LINE_TO);
                    appendPoint(d, seg[0], seg[1]);
                    break;
                case PathIterator.SEG_CLOSE:
                    d.append(PATH_CLOSE);
                    break;
                case PathIterator.SEG_QUADTO:
                    d.append(PATH_QUAD_TO);
                    appendPoint(d, seg[0], seg[1]);
                    appendPoint(d, seg[2], seg[3]);
                    break;
                case PathIterator.SEG_CUBICTO:
                    d.append(PATH_CUBIC_TO);
                    appendPoint(d, seg[0], seg[1]);
                    appendPoint(d, seg[2], seg[3]);
                    appendPoint(d, seg[4], seg[5]);
                    break;
                default:
                    throw new RuntimeException("invalid segmentType:" + segType );
            }
            pi.next();
        } // while !isDone

        if (d.length() > 0){
            return d.toString().trim();
        }
        else {
            // This is a degenerate case: there was no initial moveTo
            // in the path and no data at all. However, this happens
            // in the Java 2D API (e.g., when clipping to a rectangle
            // with negative height/width, the clip will be a GeneralPath
            // with no data, which causes everything to be clipped)
            // It is the responsibility of the users of SVGPath to detect
            // instances where the converted element (see #toSVG above)
            // returns null, which only happens for degenerate cases.
            return "";
        }
    }

    private static void appendPoint(StringBuilder d, double x, double y) {
        d.append(SvgNumUtils.doubleString(x));
        d.append(SVGSyntax.COMMA);
        d.append(SvgNumUtils.doubleString(y));
        d.append(SVGSyntax.SPACE);
    }
}

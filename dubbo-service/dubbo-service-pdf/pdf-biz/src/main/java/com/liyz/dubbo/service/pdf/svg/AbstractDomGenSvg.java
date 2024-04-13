package com.liyz.dubbo.service.pdf.svg;

import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import org.apache.batik.anim.dom.*;
import org.apache.batik.dom.svg.SVGOMPoint;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.svg.SVGPathSegList;
import org.w3c.dom.svg.SVGPointList;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/15 17:59
 */
public abstract class AbstractDomGenSvg extends AbstractGenSvg{
    protected SVGOMDocument doc;
    protected SVGOMElement svg;
    public AbstractDomGenSvg(Dimension2D size) {
        super(size);
    }

    @Override
    public String genSvg(){
        init();
        gen();
        ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
        SvgUtils.output(doc, out);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

    protected void init() {
        doc = SvgUtils.createDoc();

        // Get the root element (the 'svg' element).
        svg = (SVGOMElement) doc.getDocumentElement();

        // Set the width and height attributes on the root 'svg' element.
        svg.setAttributeNS(null,  SVGConstants.SVG_WIDTH_ATTRIBUTE, widthValue());
        svg.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, heightValue());
    }

    /**
     * 生成SVG
     * @return
     */
    protected abstract void gen();

    /**
     * 画一个矩形
     */
    protected SVGGraphicsElement drawRect(ShapeAttr shapeAttr,
                                          StrokeAttr strokeAttr,
                                          ColorAttr fill,
                                          float radius){
        float x = (float) shapeAttr.getCoordinate().getX();
        float y = (float) shapeAttr.getCoordinate().getY();
        float width = (float) shapeAttr.getSize().getWidth();
        float height = (float) shapeAttr.getSize().getHeight();
        if(radius < 1f){
            // 没有圆角
            SVGOMRectElement rect = SvgUtils.createRect(doc);
            new SvgAttr(rect).stroke(strokeAttr.getStroke(), strokeAttr.getStrokeWidth())
                    .original(x, y, width, height)
                    .fill(fill);
            svg.appendChild(rect);
            return rect;
        }else{
            SVGOMPathElement path = SvgUtils.createPath(doc);
            SvgAttr attr = new SvgAttr(path);
            attr.stroke(strokeAttr.getStroke(), strokeAttr.getStrokeWidth())
                    .fill(fill);
            SVGPathSegList segList = path.getPathSegList();
            // 开始
            segList.appendItem(
                    path.createSVGPathSegMovetoAbs(x + radius, y)
            );
            // 水平向右画一条直线
            segList.appendItem(
                    path.createSVGPathSegLinetoHorizontalRel(width - 2*radius)
            );
            // 画2次曲线
            segList.appendItem(
                    path.createSVGPathSegCurvetoQuadraticRel(
                            radius, radius,
                            radius, 0
                    )
            );
            // 向下画一条直线
            segList.appendItem(
                    path.createSVGPathSegLinetoVerticalRel(height - 2*radius)
            );
            // 画2次曲线
            segList.appendItem(
                    path.createSVGPathSegCurvetoQuadraticRel(
                             -radius, radius,
                            0, radius
                    )
            );
            // 向左画一条直线
            segList.appendItem(
                    path.createSVGPathSegLinetoHorizontalRel(-(width - 2*radius))
            );
            // 画2次曲线
            segList.appendItem(
                    path.createSVGPathSegCurvetoQuadraticRel(
                            -radius, -radius,
                            -radius, 0
                    )
            );
            // 向上画一条直线
            segList.appendItem(
                    path.createSVGPathSegLinetoVerticalRel(-(height - 2*radius))
            );
            // 画2次曲线
            segList.appendItem(
                    path.createSVGPathSegCurvetoQuadraticRel(
                            radius, -radius,
                            0, -radius
                    )
            );
            segList.appendItem(path.createSVGPathSegClosePath());
            svg.appendChild(path);
            return path;
        }
    }

    /**
     * 画一个圆
     * @param cxy
     * @param r
     * @param strokeAttr
     * @param fill
     * @return
     */
    protected SVGGraphicsElement drawCircle(Point2D cxy,
                                          double r,
                                          StrokeAttr strokeAttr,
                                          ColorAttr fill){
        SVGOMCircleElement circle = SvgUtils.createCircle(doc);
        new SvgAttr(circle).circle(cxy.getX(), cxy.getY(), r)
                .stroke(strokeAttr.getStroke(), strokeAttr.getStrokeWidth())
                .fill(fill);
        svg.appendChild(circle);
        return circle;
    }

    protected SVGOMPolylineElement drawPolyline(SVGOMPoint[] points,
                                                StrokeAttr strokeAttr,
                                                ColorAttr fill){
        return drawPolyline(svg, points, strokeAttr, fill);
    }

    /**
     * 画一条连续的折线
     * @param container
     * @param points
     * @param strokeAttr
     * @param fill
     * @return
     */
    protected SVGOMPolylineElement drawPolyline(SVGOMElement container,
                                                SVGOMPoint[] points,
                                                StrokeAttr strokeAttr,
                                                ColorAttr fill){
        SVGOMPolylineElement polyline = SvgUtils.createPolyline(doc);
        new SvgAttr(polyline)
                .fill(fill.color())
                .stroke(strokeAttr.getStroke(),strokeAttr.getStrokeWidth());
        container.appendChild(polyline);
        SVGPointList pointList = polyline.getPoints();
        Arrays.stream(points).forEach(pointList::appendItem);
        return polyline;
    }
}

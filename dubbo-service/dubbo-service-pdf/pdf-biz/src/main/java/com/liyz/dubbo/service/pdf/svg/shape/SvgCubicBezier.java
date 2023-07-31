package com.liyz.dubbo.service.pdf.svg.shape;

import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.pdf.svg.ColorAttr;
import com.liyz.dubbo.service.pdf.svg.CubicBezierRadianRel;
import com.liyz.dubbo.service.pdf.svg.SvgAttr;
import com.liyz.dubbo.service.pdf.svg.SvgDirection;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.batik.anim.dom.SVGOMCircleElement;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMPathElement;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGPathSegList;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/21 13:31
 * 3次贝塞尔曲线
 */
@Data
@AllArgsConstructor
public class SvgCubicBezier {

    private SVGOMDocument doc;
    /**
     * 曲线朝向
     */
    private SvgDirection direction;
    private float step;
    /**
     * 数据
     */
    private double[] datas;
    /**
     * 刻度
     */
    private Axis axis;
    private ColorAttr color;
    private double strokeWidth;
    /**
     * 包装曲线的容器的左上角绝对坐标
     */
    private Point2D leftUpCoordinate;
    /**
     * 第一个点相对容器左上角的偏移量
     */
    private Point2D offset;
    /**
     * 包装曲线的容器尺寸，就是图表的尺寸
     */
    private Dimension2D size;
    /**
     * 弧度计算（相对坐标）
     */
    private CubicBezierRadianRel radianRel;
    private SvgCubicBezier(){}

    public SVGOMPathElement draw(){
        Element svg = doc.getDocumentElement();
        float[][] coordinates = calXYCoordinate();
        SVGOMPathElement path = SvgUtils.createPath(doc);
        new SvgAttr(path).fill(ColorAttr.NONE)
                .stroke(color.color(), strokeWidth);
        svg.appendChild(path);
        SVGPathSegList pathSegList = path.getPathSegList();
        float prevAbsY = 0;
        for (int i = 0; i < datas.length; i++) {
            float absX = coordinates[i][0];
            float absY = coordinates[i][1];
            if(i == 0){
                pathSegList.appendItem(
                        path.createSVGPathSegMovetoAbs(absX, absY)
                );
            }else{
                // 当前Y与上一个Y的差
                float diffY = absY - prevAbsY;
                float[] points = radianRel.radian(step, diffY);
                pathSegList.appendItem(
                        path.createSVGPathSegCurvetoCubicRel(
                                points[0], points[1],
                                points[2], points[3],
                                points[4],points[5])
                );
            }
            prevAbsY = absY;
            // 画圆点
            SVGOMCircleElement circle = SvgUtils.createCircle(doc);
            new SvgAttr(circle).fill(color)
                    .stroke("#FFFFFF", 2)
                    .circle(absX, absY, 4);
            svg.appendChild(circle);
        }
        return path;
    }


    /**
     * 计算数据的坐标
     * @return
     */
    public float[][] calXYCoordinate() {
        int len = datas.length;
        float[][] coordinates = new float[len][2];
        for (int i = 0; i < len; i++) {
            float absX = 0;
            // 求y
            float absY = 0;
            if(SvgDirection.X == direction){
                absX = (float) (leftUpCoordinate.getX() + offset.getX() + i* step);
                // 求y
                absY = (float) (leftUpCoordinate.getY() + offset.getY() + (size.getHeight()-(axis.calRatio(datas[i])  * size.getHeight())));
            }else{
                absX = (float) (leftUpCoordinate.getX() + offset.getX() + (size.getWidth()-(axis.calRatio(datas[i]) * size.getWidth())));
                // 求y
                absY = (float) (leftUpCoordinate.getY() + offset.getY() + i* step);
            }

            coordinates[i] = new float[]{absX, absY};
        }
        return coordinates;
    }


    public SvgCubicBezier deepCopy(){
        return BeanUtil.copyProperties(this, SvgCubicBezier::new);
    }
}

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
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ChenHao
 * @Date 2023/01/12 13:31
 * 3次贝塞尔曲线
 * 相比 {@link SvgCubicBezier}，图表数据是对象而非基础类型，图表的曲线可能是断断续续的，中间还可能出现孤立的圆点
 */
@Data
@AllArgsConstructor
public class SvgCubicBezier2 {

    private SVGOMDocument doc;
    /**
     * 曲线朝向
     */
    private SvgDirection direction;
    private float step;
    /**
     * 数据
     */
    private Double[] datas;
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

    private SvgCubicBezier2(){}

    public void draw(){
        /*
         * 分析这个数组
         * 有连续的点才画曲线；不连续的点，则只画一个点；null，则只是占位
         */
        Double[][] coordinates = calXYCoordinate();
        List<Fragment> fragmentList = new Analysis(coordinates).parse();
        for (Fragment fragment : fragmentList) {
            if(fragment instanceof Circle){
                // 画圆点（这是一个孤立的点）
                Circle.class.cast(fragment).draw();
            }else if(fragment instanceof Curve){
                // 画曲线
                Curve.class.cast(fragment).draw();
            }
        }
    }

    /**
     * 计算数据的坐标
     * @return 返回的都是绝对坐标
     */
    public Double[][] calXYCoordinate() {
        if(null == datas){
            // 返回一个空数组
            return new Double[0][2];
        }
        int len = datas.length;
        Double[][] coordinates = new Double[len][2];
        for (int i = 0; i < len; i++) {
            if(null == datas[i]){
                // 没有数据则跳过
                coordinates[i] = null;
                continue;
            }
            Double x,y;
            if(SvgDirection.X == direction){
                x = (leftUpCoordinate.getX() + offset.getX() + i* step);
                // 求y
                y = (leftUpCoordinate.getY() + offset.getY() + (size.getHeight()-(axis.calRatio(datas[i])  * size.getHeight())));
            }else{
                x = (leftUpCoordinate.getX() + offset.getX() + (size.getWidth()-(axis.calRatio(datas[i]) * size.getWidth())));
                // 求y
                y = (leftUpCoordinate.getY() + offset.getY() + i* step);
            }
            coordinates[i] = new Double[]{x, y};
        }
        return coordinates;
    }


    public SvgCubicBezier2 copy(){
        return BeanUtil.copyProperties(this, SvgCubicBezier2::new);
    }

    private class Analysis{
        private Double[][] coordinates;
        private int len;
        private int maxIndex;
        public Analysis(Double[][] coordinates) {
            this.coordinates = coordinates;
            this.len = coordinates.length;
            this.maxIndex = this.len-1;
        }

        List<Fragment> parse(){
            List<Fragment> fragmentList = new ArrayList<>();
            Curve currentCurve = null;
            for (int i = 0; i < len; i++) {
                Double[] point = this.coordinates[i];
                if(null == point){
                    fragmentList.add(new Null());
                    currentCurve = null;
                }else{
                    // 说明当前点有值
                    // 判断当前点是否一个孤立点
                    if(isIsolated(i)){
                        fragmentList.add(new Circle(point));
                        currentCurve = null;
                    }else{
                        // 说明点是连续的，多个连续的点都是一条曲线
                        if (currentCurve == null) {
                            currentCurve = new Curve().add(point);
                            fragmentList.add(currentCurve);
                        }else{
                            currentCurve.add(point);
                        }
                    }
                }
            }
            return fragmentList;
        }
        /**
         * 判断下标的点是否一个孤立的点，下标指向的点不能为Null
         * 1. 是起点，后面的点是空
         * 2. 是结束点，前面的点是空
         * 3. 中间的点，前后的点都是空
         */
        private boolean isIsolated(int index){
            // 获得前后point，遇到越界则返回null
            Double[] before = access(index - 1);
            Double[] after = access(index + 1);
            return before == null && after == null;
        }

        private Double[] access(int index){
            return index > maxIndex || index < 0 ? null : this.coordinates[index];
        }
    }
    private abstract class Fragment{
        /**
         * 画画
         */
        abstract void draw();
    }
    private class Null extends Fragment{
        @Override
        void draw() {
            // 什么都不干
        }
    }


    private class Circle extends Fragment{
        private float x;
        private float y;
        Circle(Double[] doubles){
            this.x = doubles[0].floatValue();
            this.y = doubles[1].floatValue();
        }

        Circle(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        void draw() {
            Element root = SvgCubicBezier2.this.doc.getDocumentElement();
            SVGOMCircleElement circle = SvgUtils.createCircle(SvgCubicBezier2.this.doc);
            new SvgAttr(circle).fill(SvgCubicBezier2.this.color)
                    .stroke("#FFFFFF", 2)
                    .circle(this.x, this.y, 4.0D);
            root.appendChild(circle);
        }
    }

    private class Curve extends Fragment{
        /**
         * 曲线的坐标数据
         */
        private List<float[]> coordinateList;
        Curve add(Double[] doubles){
            if(this.coordinateList == null){
                this.coordinateList = new ArrayList<>();
            }
            this.coordinateList.add(new float[]{
                    doubles[0].floatValue(),
                    doubles[1].floatValue()
            });
            return this;
        }
        @Override
        void draw() {
            Element root = SvgCubicBezier2.this.doc.getDocumentElement();
            SVGOMPathElement path = SvgUtils.createPath(SvgCubicBezier2.this.doc);
            new SvgAttr(path).fill(ColorAttr.NONE)
                    .stroke(SvgCubicBezier2.this.color.color(), SvgCubicBezier2.this.strokeWidth);
            root.appendChild(path);
            SVGPathSegList pathSegList = path.getPathSegList();
            float prevAbsY = 0;
            for (int i = 0; i < this.coordinateList.size(); i++) {
                float[] point = this.coordinateList.get(i);
                float absX = point[0];
                float absY = point[1];
                if(i == 0){
                    pathSegList.appendItem(
                            path.createSVGPathSegMovetoAbs(absX, absY)
                    );
                }else{
                    // 当前Y与上一个Y的差
                    float diffY = absY - prevAbsY;
                    float[] points = SvgCubicBezier2.this.radianRel.radian(SvgCubicBezier2.this.step, diffY);
                    pathSegList.appendItem(
                            path.createSVGPathSegCurvetoCubicRel(
                                    points[0], points[1],
                                    points[2], points[3],
                                    points[4],points[5])
                    );
                }
                prevAbsY = absY;
                // 画圆点
                new Circle(absX, absY).draw();
            }
        }
    }


}

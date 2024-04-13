package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Getter;
import lombok.ToString;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMLineElement;
import org.apache.batik.anim.dom.SVGOMTextElement;
import org.apache.commons.lang3.RandomUtils;
import org.w3c.dom.Element;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * description: TODO 拿地趋势
 * author: huanglb
 * date 2023/1/13 14:52
 */
public class TakeLandTrendSvg extends AbstractProjectGenSvg {


    private String[] titles = new String[]{"规划建筑面积(㎡)", "权益规划建筑面积(㎡)", "拿地宗数", "拿地宗数中位线"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true),
            new ColorAttr(DEFS_Y_ID_SVG_ID_2, true),
            new ColorAttr("#F55B77", false),
            new ColorAttr("#766CE5", false)};

    //数据
    private TakeLandTrendSvgData data;

    public TakeLandTrendSvg(TakeLandTrendSvgData data) {
        super(796, 266, 100, 100, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minAreaValue().doubleValue(), data.maxAreaValue().doubleValue()}).cal();
        Axis axis2 = new AxisHelper(new double[]{data.minQtyValue().doubleValue(), data.maxQtyValue().doubleValue()}, axis1.getRegionNum()).cal();
        setAxis1AndLabel1(axis1, "㎡");
        setAxis2AndLabel2(axis2, "宗");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setIfBgDottedLine(false);
    }

    /**
     *
     */

    public static void main(String[] args) throws IOException {
        TakeLandTrendSvgData data = TakeLandTrendSvgData.mock();
        System.out.println(data);
        TakeLandTrendSvg svgBuilder = new TakeLandTrendSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);

    }

    @Override
    protected void gen() {
        //渐变色
        defs();
        //画边框
        drawOutlineBorder();
        //画标题
        drawTitle();
        //画XY
        drawXY();
        //画左边柱子
        drawAreasBar();
        //画右边柱子
        drawQtyssline();
        //画中位数线条
        drawMedianline();

    }

    private void drawMedianline() {
        double medianValue = data.medianValue();
        Axis axis = getAxis2();
        double h = axis.calRatio(medianValue) * this.getCHART_HEIGHT();
        double x = this.getCHART_LEFT_INTERVAL();
        double y = this.getCHART_UP_INTERVAL() + this.getCHART_HEIGHT() - h;
        SVGOMLineElement xDottedLine = SvgUtils.createLine(doc);

        new SvgAttr(xDottedLine).stroke(colors[3].getColor(), 2)
                .strokeDasharray("3.4 2.3")
                .lineXy(x, y, x + this.getCHART_WIDTH(), y);
        svg.appendChild(xDottedLine);
    }

    /**
     *
     */
    private void drawQtyssline() {
        List<Number> ls = Lists.newArrayList(data.getQtys());
        drawPolyline(ls, colors[2], getAxis2());
    }

    /**
     *
     */
    private void drawAreasBar() {
        List<List<Number>> ls = Lists.newArrayList(Lists.newArrayList(data.getPlanningAreas()),
                Lists.newArrayList(data.getEquityPlanningAreas()));
        ColorAttr[] colorAttrs = new ColorAttr[]{colors[0], colors[1]};
        drawBar(ls, colorAttrs, getAxis1());
    }


    /**
     *
     */
    @Override
    protected void defs() {
        defsYId(DEFS_Y_ID_SVG_ID_1, Lists.newArrayList(
                new ProjectSvg.GradientStop("0%", "#6289EF", null),
                new ProjectSvg.GradientStop("100%", "#9BBEF8", null)

        ));

        defsYId(DEFS_Y_ID_SVG_ID_2, Lists.newArrayList(
                new ProjectSvg.GradientStop("0%", "#33BED1", null),
                new ProjectSvg.GradientStop("100%", "#62E0EA", null)

        ));
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.BarTitle(doc, titles[0], colors[0], new Point(220, 20)).draw();
        new ProjectSvg.BarTitle(doc, titles[1], colors[1], new Point(375, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(560, 20)).draw();
        drawMedianlineTile(doc, titles[3], colors[3], new Point(665, 20));
    }

    /**
     * @param doc
     * @param title
     * @param color
     * @param point
     */
    private void drawMedianlineTile(SVGOMDocument doc, String title, ColorAttr color, Point point) {
        Element root = doc.getDocumentElement();
        double x = point.getX();
        double y = point.getY();

        SVGOMLineElement xDottedLine = SvgUtils.createLine(doc);
        new SvgAttr(xDottedLine).stroke(color.getColor(), 5)
                .strokeDasharray("3.4 2.3")
                .lineXy(x, y + 5.2, x + 19, y + 5.2);
        root.appendChild(xDottedLine);

        SVGOMTextElement text1 = SvgUtils.createText(doc);
        new SvgAttr(text1)
                .font("14", null)
                .coordinate(x + 25, y + 10.4);
        text1.setTextContent(title);
        root.appendChild(text1);
    }

    //数据实体
    @Getter
    @ToString
    public static class TakeLandTrendSvgData extends TakeBaseData {

        //规划建筑面积
        private List<BigDecimal> planningAreas = Lists.newArrayList();
        //权益规划建筑面积
        private List<BigDecimal> equityPlanningAreas = Lists.newArrayList();
        //拿地宗数
        private List<Integer> qtys = Lists.newArrayList();

        /**
         * @return
         */
        public static TakeLandTrendSvgData mock() {

            TakeLandTrendSvgData data = new TakeLandTrendSvgData();
            //生成10个随机数据

            int size = 6;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                double value2 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                int value3 = RandomUtils.nextInt(1, 10000);
                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue2 = new BigDecimal(String.valueOf(value2)).setScale(2, BigDecimal.ROUND_HALF_UP);

                Integer integerValue3 = value3;
//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(bigDecimalValue, bigDecimalValue2, integerValue3, String.format("第%s季度", i));

            }
            return data;
        }

        /**
         * @param planningArea
         * @param equityPlanningArea
         * @param qty
         * @param lable
         */
        public void addBucket(BigDecimal planningArea, BigDecimal equityPlanningArea, Integer qty, String lable) {
            planningAreas.add(planningArea);
            equityPlanningAreas.add(equityPlanningArea);
            qtys.add(qty);
            titleList.add(lable);
        }

        //
        public BigDecimal maxAreaValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(planningAreas);
            ls.addAll(equityPlanningAreas);
            return maxBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public Integer maxQtyValue() {
            return maxIntegerValue(qtys);
        }

        public BigDecimal minAreaValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(planningAreas);
            ls.addAll(equityPlanningAreas);
            return minBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public Integer minQtyValue() {
            return minIntegerValue(qtys);
        }

        /**
         * @return 计算中位数
         */
        public double medianValue() {
            return medianValue(Lists.newArrayList(qtys));
        }

    }
}

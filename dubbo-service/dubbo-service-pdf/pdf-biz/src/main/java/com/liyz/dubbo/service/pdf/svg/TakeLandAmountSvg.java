package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/6 15:30
 */
public class TakeLandAmountSvg extends AbstractProjectGenSvg {


    private String[] titles = new String[]{"拿地金额（万元）", "权益拿地金额（万元）", "平均溢价率"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true),
            new ColorAttr(DEFS_Y_ID_SVG_ID_2, true),
            new ColorAttr("#33BED1", false)
    };
    //数据
    private TakeLandAmountSvgData data;

    public TakeLandAmountSvg(TakeLandAmountSvgData data) {
        super(796, 266, 100, 100, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minBarValue().doubleValue(), data.maxBarValue().doubleValue()}).cal();
        Axis axis2 = new AxisHelper(new double[]{data.minLineValue().doubleValue(), data.maxLineValue().doubleValue()}, axis1.getRegionNum()).cal();
        setAxis1AndLabel1(axis1, "万元");
        setAxis2AndLabel2(axis2, "%");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        TakeLandAmountSvgData data = TakeLandAmountSvgData.mock();
        System.out.println(data);
        TakeLandAmountSvg svgBuilder = new TakeLandAmountSvg(data);
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
        drawBar();
        //画右边柱子
        drawline();

    }

    /**
     *
     */
    private void drawline() {
        List<Number> ls = Lists.newArrayList(data.getAveragePremiumRates());
        drawPolyline(ls, colors[2], getAxis2());
    }

    /**
     *
     */
    private void drawBar() {
        List<List<Number>> ls = Lists.newArrayList(Lists.newArrayList(data.getClosingCosts()),
                Lists.newArrayList(data.getShareClosingCosts()));
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
                new ProjectSvg.GradientStop("0%", "#F55B77", null),
                new ProjectSvg.GradientStop("100%", "#FFBBC7", null)

        ));
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.BarTitle(doc, titles[0], colors[0], new Point(260, 20)).draw();
        new ProjectSvg.BarTitle(doc, titles[1], colors[1], new Point(415, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(595, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class TakeLandAmountSvgData extends TakeBaseData {

        //规拿地金额(万元)
        private List<BigDecimal> closingCosts = Lists.newArrayList();
        //权益拿地金额(万元)
        private List<BigDecimal> shareClosingCosts = Lists.newArrayList();
        //溢价率(%)
        private List<BigDecimal> averagePremiumRates = Lists.newArrayList();

        /**
         * @return
         */
        public static TakeLandAmountSvgData mock() {

            TakeLandAmountSvgData data = new TakeLandAmountSvgData();
            //生成10个随机数据

            int size = 6;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                double value2 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                double value3 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;
                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue2 = new BigDecimal(String.valueOf(value2)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue3 = new BigDecimal(String.valueOf(value3)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(bigDecimalValue, bigDecimalValue2, bigDecimalValue3, String.format("第%s季度", i));

            }
            return data;
        }


        /**
         * @param value1
         * @param value2
         * @param value3
         * @param lable
         */
        public void addBucket(BigDecimal value1, BigDecimal value2, BigDecimal value3, String lable) {
            closingCosts.add(value1);
            shareClosingCosts.add(value2);
            averagePremiumRates.add(value3);
            titleList.add(lable);
        }

        //
        public BigDecimal maxBarValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(closingCosts);
            ls.addAll(shareClosingCosts);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minBarValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(closingCosts);
            ls.addAll(shareClosingCosts);
            return minBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public BigDecimal maxLineValue() {
            return maxBigDecimalValue(averagePremiumRates);
        }

        //获取做大值排除空值
        public BigDecimal minLineValue() {
            return minBigDecimalValue(averagePremiumRates);
        }

    }
}


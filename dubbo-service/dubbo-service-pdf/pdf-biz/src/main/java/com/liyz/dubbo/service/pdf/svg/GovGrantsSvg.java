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
 * date 2023/2/1 11:00
 */
public class GovGrantsSvg extends AbstractProjectGenSvg {


    private String[] titles = new String[]{"计入递延收益", "计入当前损益", "合计"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true),
            new ColorAttr(DEFS_Y_ID_SVG_ID_2, true),
            new ColorAttr(RED, false)};

    private GovGrantsSvgData data;

    public GovGrantsSvg(GovGrantsSvgData data) {
        super(846, 266, 120, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minValue().doubleValue(), data.maxValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "单位:亿元");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        GovGrantsSvgData data = GovGrantsSvgData.mock();
        System.out.println(data);
        GovGrantsSvg svgBuilder = new GovGrantsSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
//        Path path = Paths.get(new ClassPathResource("").getPath() + OcIndicatorSvg.class.getSimpleName() + ".svg");
//        Files.write(path, svg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void defs() {

        defsYId(DEFS_Y_ID_SVG_ID_1, Lists.newArrayList(
                new ProjectSvg.GradientStop("0%", "#766CE5", null),
                new ProjectSvg.GradientStop("100%", "#AEA5F4", null)

        ));

        defsYId(DEFS_Y_ID_SVG_ID_2, Lists.newArrayList(
                new ProjectSvg.GradientStop("0%", "#4FA9EB", null),
                new ProjectSvg.GradientStop("100%", "#87D4F7", null)

        ));
    }

    @Override
    protected void gen() {
        defs();
        //画边框
        drawOutlineBorder();
        //画标题
        drawTitle();
        //画XY
        drawXY();
        //画左边柱子
        List<List<Number>> ls1 = Lists.newArrayList(Lists.newArrayList(data.getTotalIncome()),
                Lists.newArrayList(data.getTotalProfitsLosses()));
        ColorAttr[] colorAttrs = new ColorAttr[]{colors[0], colors[1]};
        drawBar(ls1, colorAttrs, getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getTotalTotal());
        drawPolyline(ls2, colors[2], getAxis1());
    }

    private void drawTitle() {
        new ProjectSvg.BarTitle(doc, titles[0], colors[0], new Point(270, 20)).draw();
        new ProjectSvg.BarTitle(doc, titles[1], colors[1], new Point(430, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(590, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class GovGrantsSvgData extends TakeBaseData {

        private List<BigDecimal> totalIncome = Lists.newArrayList();

        private List<BigDecimal> totalProfitsLosses = Lists.newArrayList();

        private List<BigDecimal> totalTotal = Lists.newArrayList();


        public static GovGrantsSvgData mock() {

            GovGrantsSvgData data = new GovGrantsSvgData();
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
         * @param bigDecimalValue
         * @param bigDecimalValue2
         * @param bigDecimalValue3
         * @param format
         */
        public void addBucket(BigDecimal bigDecimalValue, BigDecimal bigDecimalValue2, BigDecimal bigDecimalValue3, String format) {
            totalIncome.add(bigDecimalValue);
            totalProfitsLosses.add(bigDecimalValue2);
            totalTotal.add(bigDecimalValue3);
            titleList.add(format);
        }


        public BigDecimal maxValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(totalIncome);
            ls.addAll(totalProfitsLosses);
            ls.addAll(totalTotal);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(totalIncome);
            ls.addAll(totalProfitsLosses);
            ls.addAll(totalTotal);
            return minBigDecimalValue(ls);
        }
    }
}

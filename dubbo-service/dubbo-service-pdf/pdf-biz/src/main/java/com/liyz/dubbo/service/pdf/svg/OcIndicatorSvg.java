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
 * date 2023/1/31 10:47
 */
public class OcIndicatorSvg extends AbstractProjectGenSvg {


    private String[] titles = new String[]{"营业收入（元）", "税前利润（元）", "销售净利率（%）"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true),
            new ColorAttr(DEFS_Y_ID_SVG_ID_2, true),
            new ColorAttr(RED, false)};

    private OcIndicatorSvgData data;

    public OcIndicatorSvg(OcIndicatorSvgData data) {
        super(796, 266, 100, 100, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minBarValue().doubleValue(), data.maxBarValue().doubleValue()}).cal();
        Axis axis2 = new AxisHelper(new double[]{data.minLineValue().doubleValue(), data.maxLineValue().doubleValue()}, axis1.getRegionNum()).cal();
        setAxis1AndLabel1(axis1, "单位：元");
        setAxis2AndLabel2(axis2, "单位：%");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        OcIndicatorSvgData data = OcIndicatorSvgData.mock();
        System.out.println(data);
        OcIndicatorSvg svgBuilder = new OcIndicatorSvg(data);
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
        List<List<Number>> ls1 = Lists.newArrayList(Lists.newArrayList(data.getMainBusiness()),
                Lists.newArrayList(data.getProfitBeforeTax()));
        ColorAttr[] colorAttrs = new ColorAttr[]{colors[0], colors[1]};
        drawBar(ls1, colorAttrs, getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getOperatingNetProfitRatio());
        drawPolyline(ls2, colors[2], getAxis2());
    }

    private void drawTitle() {
        new ProjectSvg.BarTitle(doc, titles[0], colors[0], new Point(270, 20)).draw();
        new ProjectSvg.BarTitle(doc, titles[1], colors[1], new Point(430, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(590, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class OcIndicatorSvgData extends TakeBaseData {

        // 主营业务收入（万元）
        private List<BigDecimal> mainBusiness = Lists.newArrayList();
        // 税前利润（万元）
        private List<BigDecimal> profitBeforeTax = Lists.newArrayList();
        // 净利润率
        private List<BigDecimal> operatingNetProfitRatio = Lists.newArrayList();

        public static OcIndicatorSvgData mock() {

            OcIndicatorSvgData data = new OcIndicatorSvgData();
            //生成10个随机数据

            int size = 4;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(1, 10000);

                double value2 = RandomUtils.nextDouble(10000, 20000);

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
            mainBusiness.add(bigDecimalValue);
            profitBeforeTax.add(bigDecimalValue2);
            operatingNetProfitRatio.add(bigDecimalValue3);
            titleList.add(format);
        }


        public BigDecimal maxBarValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(mainBusiness);
            ls.addAll(profitBeforeTax);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minBarValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(mainBusiness);
            ls.addAll(profitBeforeTax);
            return minBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public BigDecimal maxLineValue() {
            return maxBigDecimalValue(operatingNetProfitRatio);
        }

        public BigDecimal minLineValue() {
            return minBigDecimalValue(operatingNetProfitRatio);
        }
    }


}

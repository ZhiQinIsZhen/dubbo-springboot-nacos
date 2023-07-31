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
 * date 2023/1/31 15:49
 */
public class SolvencyIndicatorSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"流动比率", "速动比率", "资产负债率", "已获利息倍数"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(SKY_BLUE2, false),
            new ColorAttr(SKY_BLUE, false),
            new ColorAttr(RED, false),
            new ColorAttr(PURPLE, false)};

    private SolvencyIndicatorSvgData data;

    public SolvencyIndicatorSvg(SolvencyIndicatorSvgData data) {
        super(796, 266, 100, 100, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minLineValue1().doubleValue(), data.maxLineValue1().doubleValue()}).cal();
        Axis axis2 = new AxisHelper(new double[]{data.minLineValue2().doubleValue(), data.maxLineValue2().doubleValue()}, axis1.getRegionNum()).cal();
        setAxis1AndLabel1(axis1, "比率");
        setAxis2AndLabel2(axis2, "单位：%");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        SolvencyIndicatorSvgData data = SolvencyIndicatorSvgData.mock();
        System.out.println(data);
        SolvencyIndicatorSvg svgBuilder = new SolvencyIndicatorSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
//        Path path = Paths.get(new ClassPathResource("").getPath() + OcIndicatorSvg.class.getSimpleName() + ".svg");
//        Files.write(path, svg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void gen() {

        //画边框
        drawOutlineBorder();
        //画标题
        drawTitle();
        //画XY
        drawXY();

        List<Number> ls1 = Lists.newArrayList(data.getCurrentRatio());
        drawPolyline(ls1, colors[0], getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getQuickRatio());
        drawPolyline(ls2, colors[1], getAxis1());
        List<Number> ls3 = Lists.newArrayList(data.getAssetLiabilityRatio());
        drawPolyline(ls3, colors[2], getAxis1());
        List<Number> ls4 = Lists.newArrayList(data.getInterestEarned());
        drawPolyline(ls4, colors[3], getAxis2());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.PolylineTitle(doc, titles[0], colors[0], new Point(190, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[1], colors[1], new Point(350, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(510, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[3], colors[3], new Point(670, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class SolvencyIndicatorSvgData extends TakeBaseData {

        private List<BigDecimal> currentRatio = Lists.newArrayList();
        // 速动比率
        private List<BigDecimal> quickRatio = Lists.newArrayList();
        // 资产负债率
        private List<BigDecimal> assetLiabilityRatio = Lists.newArrayList();
        //已获利息倍数
        private List<BigDecimal> interestEarned = Lists.newArrayList();


        public static SolvencyIndicatorSvgData mock() {

            SolvencyIndicatorSvgData data = new SolvencyIndicatorSvgData();
            //生成10个随机数据

            int size = 4;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(1, 10000);

                double value2 = RandomUtils.nextDouble(10000, 20000);

                double value3 = RandomUtils.nextDouble(20000, 25000);
                double value4 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue2 = new BigDecimal(String.valueOf(value2)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue3 = new BigDecimal(String.valueOf(value3)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue4 = new BigDecimal(String.valueOf(value4)).setScale(2, BigDecimal.ROUND_HALF_UP);


//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(bigDecimalValue, bigDecimalValue2, bigDecimalValue3, bigDecimalValue4, String.format("第%s季度", i));

            }
            return data;
        }

        /**
         * @param bigDecimalValue
         * @param bigDecimalValue2
         * @param bigDecimalValue3
         * @param format
         */
        public void addBucket(BigDecimal bigDecimalValue, BigDecimal bigDecimalValue2, BigDecimal bigDecimalValue3, BigDecimal bigDecimalValue4, String format) {
            currentRatio.add(bigDecimalValue);
            quickRatio.add(bigDecimalValue2);
            assetLiabilityRatio.add(bigDecimalValue3);
            interestEarned.add(bigDecimalValue4);
            titleList.add(format);
        }


        public BigDecimal maxLineValue1() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(currentRatio);
            ls.addAll(quickRatio);
            ls.addAll(assetLiabilityRatio);
            return maxBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public BigDecimal maxLineValue2() {
            return maxBigDecimalValue(interestEarned);
        }

        public BigDecimal minLineValue1() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(currentRatio);
            ls.addAll(quickRatio);
            ls.addAll(assetLiabilityRatio);
            return minBigDecimalValue(ls);
        }

        //获取做大值排除空值
        public BigDecimal minLineValue2() {
            return minBigDecimalValue(interestEarned);
        }
    }
}

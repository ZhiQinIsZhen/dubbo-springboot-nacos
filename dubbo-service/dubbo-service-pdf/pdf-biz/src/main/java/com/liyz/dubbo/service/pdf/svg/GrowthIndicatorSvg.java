package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/1/31 16:43
 */
public class GrowthIndicatorSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"营业总收⼊", "归⺟公司净利率", "归⺟公司净利润-扣除⾮经常损益", "经营活动现⾦流量净额"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(PURPLE, false),
            new ColorAttr(SKY_BLUE2, false),
            new ColorAttr(RED2, false),
            new ColorAttr(SKY_BLUE, false)};

    private GrowthIndicatorSvgData data;

    public GrowthIndicatorSvg(GrowthIndicatorSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minLineValue().doubleValue(), data.maxLineValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "单位：%");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        GrowthIndicatorSvgData data = GrowthIndicatorSvgData.mock();
        System.out.println(data);
        GrowthIndicatorSvg svgBuilder = new GrowthIndicatorSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
        Path path = Paths.get(new ClassPathResource("").getPath() + OcIndicatorSvg.class.getSimpleName() + ".svg");
        Files.write(path, svg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void gen() {

        //画边框
        drawOutlineBorder();
        //画标题
        drawTitle();
        //画XY
        drawXY();

        List<Number> ls1 = Lists.newArrayList(data.getRevenueRatio());
        drawPolyline(ls1, colors[0], getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getMomNetProfitRatio());
        drawPolyline(ls2, colors[1], getAxis1());
        List<Number> ls3 = Lists.newArrayList(data.getDeficitRatio());
        drawPolyline(ls3, colors[2], getAxis1());
        List<Number> ls4 = Lists.newArrayList(data.getNetCashRatio());
        drawPolyline(ls4, colors[3], getAxis1());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.PolylineTitle(doc, titles[0], colors[0], new Point(185, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[1], colors[1], new Point(315, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(475, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[3], colors[3], new Point(735, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class GrowthIndicatorSvgData extends TakeBaseData {

        private List<BigDecimal> revenueRatio = Lists.newArrayList();
        // 速动比率
        private List<BigDecimal> momNetProfitRatio = Lists.newArrayList();
        // 资产负债率
        private List<BigDecimal> deficitRatio = Lists.newArrayList();
        //已获利息倍数
        private List<BigDecimal> netCashRatio = Lists.newArrayList();


        public static GrowthIndicatorSvgData mock() {

            GrowthIndicatorSvgData data = new GrowthIndicatorSvgData();
            //生成10个随机数据

            int size = 4;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 2.0000D) * 100;

                double value2 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                double value3 = RandomUtils.nextDouble(0.0001D, 3.0000D) * 100;

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
            revenueRatio.add(bigDecimalValue);
            momNetProfitRatio.add(bigDecimalValue2);
            deficitRatio.add(bigDecimalValue3);
            netCashRatio.add(bigDecimalValue4);
            titleList.add(format);
        }


        public BigDecimal maxLineValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(revenueRatio);
            ls.addAll(momNetProfitRatio);
            ls.addAll(deficitRatio);
            ls.addAll(netCashRatio);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minLineValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(revenueRatio);
            ls.addAll(momNetProfitRatio);
            ls.addAll(deficitRatio);
            ls.addAll(netCashRatio);
            return minBigDecimalValue(ls);
        }
    }
}

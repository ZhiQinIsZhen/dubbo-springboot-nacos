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
 * date 2023/2/1 9:50
 */
public class CompanyCreditLinesSvg extends AbstractProjectGenSvg {


    private String[] titles = new String[]{"授信额度", "已使用", "未使用"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(PURPLE, false),
            new ColorAttr(SKY_BLUE, false),
            new ColorAttr(RED, false)};

    private CompanyCreditLinesSvgData data;

    public CompanyCreditLinesSvg(CompanyCreditLinesSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minLineValue().doubleValue(), data.maxLineValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "单位:亿元");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setRotate(-20);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        CompanyCreditLinesSvgData data = CompanyCreditLinesSvgData.mock();
        System.out.println(data);
        CompanyCreditLinesSvg svgBuilder = new CompanyCreditLinesSvg(data);
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

        List<Number> ls1 = Lists.newArrayList(data.getCreditLine());
        drawPolyline(ls1, colors[0], getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getCreditLineUsed());
        drawPolyline(ls2, colors[1], getAxis1());
        List<Number> ls3 = Lists.newArrayList(data.getCreditLineUnused());
        drawPolyline(ls3, colors[2], getAxis1());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.PolylineTitle(doc, titles[0], colors[0], new Point(340, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[1], colors[1], new Point(480, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(600, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class CompanyCreditLinesSvgData extends TakeBaseData {

        private List<BigDecimal> creditLine = Lists.newArrayList();

        private List<BigDecimal> creditLineUsed = Lists.newArrayList();

        private List<BigDecimal> creditLineUnused = Lists.newArrayList();

        public static CompanyCreditLinesSvgData mock() {

            CompanyCreditLinesSvgData data = new CompanyCreditLinesSvgData();
            //生成10个随机数据

            int size = 1;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0, 10000);

                double value2 = RandomUtils.nextDouble(10000, 20000);

                double value3 = RandomUtils.nextDouble(20000, 25000);


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
            creditLine.add(bigDecimalValue);
            creditLineUsed.add(bigDecimalValue2);
            creditLineUnused.add(bigDecimalValue3);
            titleList.add(format);
        }


        public BigDecimal maxLineValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(creditLine);
            ls.addAll(creditLineUsed);
            ls.addAll(creditLineUnused);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minLineValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(creditLine);
            ls.addAll(creditLineUsed);
            ls.addAll(creditLineUnused);
            return minBigDecimalValue(ls);
        }
    }
}

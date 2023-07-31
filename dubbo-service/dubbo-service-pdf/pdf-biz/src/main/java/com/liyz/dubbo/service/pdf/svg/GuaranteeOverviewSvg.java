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
import java.util.ArrayList;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/1 10:19
 */
public class GuaranteeOverviewSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"担保规模统计(亿元)"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true)};

    private GuaranteeOverviewSvgData data;

    public GuaranteeOverviewSvg(GuaranteeOverviewSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minValue().doubleValue(), data.maxValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        GuaranteeOverviewSvgData data = GuaranteeOverviewSvgData.mock();
        System.out.println(data);
        GuaranteeOverviewSvg svgBuilder = new GuaranteeOverviewSvg(data);
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
        List<Number> datas = Lists.newArrayList(data.amountSum);
        List<List<Number>> ls1 = new ArrayList<>();
        ls1.add(datas);
        ColorAttr[] colorAttrs = new ColorAttr[]{colors[0]};
        drawBar(ls1, colorAttrs, getAxis1());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.BarTitle(doc, titles[0], colors[0], new Point(450, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class GuaranteeOverviewSvgData extends TakeBaseData {

        private List<BigDecimal> amountSum = Lists.newArrayList();

        private List<BigDecimal> num = Lists.newArrayList();


        public static GuaranteeOverviewSvgData mock() {

            GuaranteeOverviewSvgData data = new GuaranteeOverviewSvgData();
            //生成10个随机数据

            int size = 4;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 2.0000D) * 100;

                double value2 = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);

                BigDecimal bigDecimalValue2 = new BigDecimal(String.valueOf(value2)).setScale(2, BigDecimal.ROUND_HALF_UP);

//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(bigDecimalValue, bigDecimalValue2, String.format("第%s季度", i));

            }
            return data;
        }

        /**
         * @param bigDecimalValue
         * @param bigDecimalValue2
         * @param format
         */
        public void addBucket(BigDecimal bigDecimalValue, BigDecimal bigDecimalValue2, String format) {
            amountSum.add(bigDecimalValue);
            num.add(bigDecimalValue2);
            titleList.add(format);
        }


        public BigDecimal maxValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(amountSum);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(amountSum);
            return minBigDecimalValue(ls);
        }
    }
}

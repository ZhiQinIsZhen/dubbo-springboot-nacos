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
 * date 2023/2/1 13:44
 */
public class RegionIndicatorSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[1];
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(RED, false)};

    private RegionIndicatorSvgData data;

    public RegionIndicatorSvg(RegionIndicatorSvgData data, String name) {
        super(230, 135, 70, 20, 30, 30);
        this.data = data;
        this.titles[0] = name;
        Axis axis1 = new AxisHelper(new double[]{data.minValue().doubleValue(), data.maxValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        RegionIndicatorSvgData data = RegionIndicatorSvgData.mock();
        System.out.println(data);
        RegionIndicatorSvg svgBuilder = new RegionIndicatorSvg(data, "dssadsas");
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

        List<Number> ls1 = Lists.newArrayList(data.getDatas());
        drawPolyline(ls1, colors[0], getAxis1());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.LableTitle(doc, titles[0], colors[0], new Point(70, 10)).draw();
    }

    @Getter
    @ToString
    public static class RegionIndicatorSvgData extends TakeBaseData {

        private List<BigDecimal> datas = Lists.newArrayList();

        public static RegionIndicatorSvgData mock() {

            RegionIndicatorSvgData data = new RegionIndicatorSvgData();
            //生成10个随机数据

            int size = 5;
            for (int i = 1; i <= size; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 1.0000D) * 1000;

                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(bigDecimalValue, String.format("2%s11", i));

            }
            return data;
        }

        /**
         * @param bigDecimalValue
         * @param format
         */
        public void addBucket(BigDecimal bigDecimalValue, String format) {
            datas.add(bigDecimalValue);
            titleList.add(format);
        }


        public BigDecimal maxValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(datas);
            return maxBigDecimalValue(ls);
        }

        public BigDecimal minValue() {
            List<BigDecimal> ls = Lists.newArrayList();
            ls.addAll(datas);
            return minBigDecimalValue(ls);
        }
    }
}

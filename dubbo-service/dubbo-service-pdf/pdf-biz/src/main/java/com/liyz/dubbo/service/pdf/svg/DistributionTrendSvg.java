package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/6 17:03
 */
public class DistributionTrendSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"一线城市", "新一线城市", "二线城市", "三线城市", "四线城市", "五线城市"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr("#2970F6", false),
            new ColorAttr("#4FA9EB", false),
            new ColorAttr("#33BED1", false),
            new ColorAttr("#766CE5", false),
            new ColorAttr("#FF8047", false),
            new ColorAttr("#F55B77", false)
    };

    private DistributionTrendSvgData data;

    public DistributionTrendSvg(DistributionTrendSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minLineValue().doubleValue(), data.maxLineValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setRotate(-20);
        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        DistributionTrendSvgData data = DistributionTrendSvgData.mock();
        System.out.println(data);
        DistributionTrendSvg svgBuilder = new DistributionTrendSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
    }

    @Override
    protected void gen() {

        //画边框
        drawOutlineBorder();
        //画标题
        drawTitle();
        //画XY
        drawXY();

        List<Number> ls1 = Lists.newArrayList(data.getOnes());
        drawPolyline(ls1, colors[0], getAxis1());
        List<Number> ls2 = Lists.newArrayList(data.getNewOnes());
        drawPolyline(ls2, colors[1], getAxis1());
        List<Number> ls3 = Lists.newArrayList(data.getTwos());
        drawPolyline(ls3, colors[2], getAxis1());
        List<Number> ls4 = Lists.newArrayList(data.getThrees());
        drawPolyline(ls4, colors[3], getAxis1());
        List<Number> ls5 = Lists.newArrayList(data.getFours());
        drawPolyline(ls5, colors[4], getAxis1());
        List<Number> ls6 = Lists.newArrayList(data.getFives());
        drawPolyline(ls6, colors[5], getAxis1());
    }

    /**
     *
     */
    private void drawTitle() {
        new ProjectSvg.PolylineTitle(doc, titles[0], colors[0], new Point(190, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[1], colors[1], new Point(310, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[2], colors[2], new Point(440, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[3], colors[3], new Point(560, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[4], colors[4], new Point(680, 20)).draw();
        new ProjectSvg.PolylineTitle(doc, titles[5], colors[5], new Point(800, 20)).draw();
    }

    //数据实体
    @Getter
    @ToString
    public static class DistributionTrendSvgData extends TakeBaseData {

        private List<Integer> ones = Lists.newArrayList();

        private List<Integer> newOnes = Lists.newArrayList();

        private List<Integer> twos = Lists.newArrayList();

        private List<Integer> threes = Lists.newArrayList();

        private List<Integer> fours = Lists.newArrayList();

        private List<Integer> fives = Lists.newArrayList();


        public static DistributionTrendSvgData mock() {

            DistributionTrendSvgData data = new DistributionTrendSvgData();
            //生成10个随机数据

            int size = 12;
            for (int i = 1; i <= size; i++) {
                int value = RandomUtils.nextInt(1, 10000);

                int value2 = RandomUtils.nextInt(1, 10000);

                int value3 = RandomUtils.nextInt(1, 10000);

                int value4 = RandomUtils.nextInt(1, 10000);

                int value5 = RandomUtils.nextInt(1, 10000);

                int value6 = RandomUtils.nextInt(1, 10000);


//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
                data.addBucket(value, value2, value3, value4, value5, value6, String.format("第%s季度", i));

            }
            return data;
        }


        /**
         * @param value1
         * @param value2
         * @param value3
         * @param value4
         * @param value5
         * @param value6
         * @param format
         */
        public void addBucket(Integer value1,
                              Integer value2,
                              Integer value3,
                              Integer value4,
                              Integer value5,
                              Integer value6,
                              String format) {
            ones.add(value1);
            newOnes.add(value2);
            twos.add(value3);
            threes.add(value4);
            fours.add(value5);
            fives.add(value6);
            titleList.add(format);
        }


        public Integer maxLineValue() {
            List<Integer> ls = Lists.newArrayList();
            ls.addAll(ones);
            ls.addAll(newOnes);
            ls.addAll(twos);
            ls.addAll(threes);
            ls.addAll(fours);
            ls.addAll(fives);
            return maxIntegerValue(ls);
        }

        public Integer minLineValue() {
            List<Integer> ls = Lists.newArrayList();
            ls.addAll(ones);
            ls.addAll(newOnes);
            ls.addAll(twos);
            ls.addAll(threes);
            ls.addAll(fours);
            ls.addAll(fives);
            return minIntegerValue(ls);
        }
    }
}


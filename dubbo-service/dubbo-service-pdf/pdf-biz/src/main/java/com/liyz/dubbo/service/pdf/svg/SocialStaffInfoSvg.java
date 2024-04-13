package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/14 10:33
 */
public class SocialStaffInfoSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"参保人数"};
    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(DEFS_Y_ID_SVG_ID_1, true)};

    private SocialStaffInfoSvgData data;

    public SocialStaffInfoSvg(SocialStaffInfoSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minValue().doubleValue(), data.maxValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "单位:人");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setIfBgDottedLine(false);
        setRotate(-20);
    }

    public static void main(String[] args) throws IOException {
        SocialStaffInfoSvgData data = SocialStaffInfoSvgData.mock();
        System.out.println(data);
        SocialStaffInfoSvg svgBuilder = new SocialStaffInfoSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
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
        List<Number> datas = Lists.newArrayList(data.getSocialStaffNums());
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
    public static class SocialStaffInfoSvgData extends TakeBaseData {

        private List<Integer> socialStaffNums = Lists.newArrayList();


        public static SocialStaffInfoSvgData mock() {

            SocialStaffInfoSvgData data = new SocialStaffInfoSvgData();
            //生成10个随机数据

            int start = 2000;
            int size = start + 50;
            for (int i = start + 1; i <= size; i++) {
                Integer value = RandomUtils.nextInt(0, 100000);


//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }


                data.addBucket(value, String.format("%s-未公布", i));

            }
            return data;
        }


        public void addBucket(Integer value, String format) {
            socialStaffNums.add(value);
            titleList.add(format);
        }


        public Integer maxValue() {
            List<Integer> ls = Lists.newArrayList();
            ls.addAll(socialStaffNums);
            return maxIntegerValue(ls);
        }

        public Integer minValue() {
            List<Integer> ls = Lists.newArrayList();
            ls.addAll(socialStaffNums);
            return minIntegerValue(ls);
        }
    }
}
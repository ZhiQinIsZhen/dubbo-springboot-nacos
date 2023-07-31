package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.svg.axis.Axis;
import com.liyz.dubbo.service.pdf.svg.axis.AxisHelper;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/1 11:31
 */
public class DurationsSvg extends AbstractProjectGenSvg {

    private String[] titles = new String[]{"地域久期"};

    private ColorAttr[] colors = new ColorAttr[]{
            new ColorAttr(SKY_BLUE2, false)};

    private TnterestMarginsDurationsSvgData data;

    public DurationsSvg(TnterestMarginsDurationsSvgData data) {
        super(866, 266, 100, 30, 60, 60);
        this.data = data;
        Axis axis1 = new AxisHelper(new double[]{data.minValue().doubleValue(), data.maxValue().doubleValue()}).cal();
        setAxis1AndLabel1(axis1, "");
        String[] lables = data.getTitleList().toArray(new String[0]);
        setLables(lables);
        setRotate(-20);
//        setIfBgDottedLine(false);
    }

    public static void main(String[] args) throws IOException {
        TnterestMarginsDurationsSvgData data = TnterestMarginsDurationsSvgData.mock();
        System.out.println(data);
        DurationsSvg svgBuilder = new DurationsSvg(data);
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
        new ProjectSvg.PolylineTitle(doc, titles[0], colors[0], new Point(480, 20)).draw();
    }
}

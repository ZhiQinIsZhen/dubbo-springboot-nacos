package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.apache.batik.anim.dom.SVGOMLineElement;
import org.apache.batik.anim.dom.SVGOMRectElement;
import org.apache.batik.anim.dom.SVGOMTextElement;
import org.apache.batik.dom.svg.SVGOMPoint;
import org.apache.commons.lang3.RandomUtils;
import org.apache.xmlgraphics.java2d.Dimension2DDouble;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * description: TODO 拿地态度svg
 * author: huanglb
 * date 2023/1/9 9:48
 */
public class TakeLandAttitudeSvg extends AbstractProjectGenSvg {


    /**
     * 长
     */
    private final static int WIDTH = 996;
    private final static int HEIGHT = 386;
    private final static int UP_MARGIN = 20;
    private final static int DOWN_MARGIN = 50;
    private final static int LEFT_MARGIN = 100;
    private final static int RIGHT_MARGIN = 30;
    private final static int RIGHT_TEXT = 0;
    private final static int INTERVAL_0_X = 30;
    private final static int INTERVAL_1_Y = 15;
    private final static int INTERVAL_3_X = 0;
    private final static int WIDTH_X = WIDTH - RIGHT_TEXT - RIGHT_MARGIN - LEFT_MARGIN - INTERVAL_0_X - INTERVAL_3_X;
    private final static int HEIGHT_Y = (HEIGHT - (UP_MARGIN + DOWN_MARGIN + INTERVAL_1_Y));
    private final static int STEP_LENTH = 15;
    //数据
    private TakeLandAttitudeSvgData data;
    //刻度
    private TakeLandAttitudeAxis axis;

    public TakeLandAttitudeSvg(TakeLandAttitudeSvgData data) {
        //定义画布大小
        super(new Dimension(WIDTH, HEIGHT));
        this.data = data;
        this.axis = new TakeLandAttitudeAxis(data.maxSaleValue());
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TakeLandAttitudeSvgData data = TakeLandAttitudeSvgData.mock();
        System.out.println(data);
        TakeLandAttitudeSvg svgBuilder = new TakeLandAttitudeSvg(data);
        String svg = svgBuilder.genSvg();
        System.out.println(svg);
//        Path path = Paths.get(new ClassPathResource("").getPath() + TakeLandAttitudeSvgData.class.getSimpleName() + ".svg");
//        Files.write(path, svg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void gen() {
        // 画边框
        drawOutlineBorder();
        //画背景
        drawBackgroundBorder();
        //画xy轴线
        drawXY();
        //画折线点位
        drawBrokenPoint(new ColorAttr("#2970F6"));
        //画分组类型
        drawLable(-20);
    }

    //
    private void drawLable(int rotate) {
        List<String> salesTimeBuckets = data.getTitleList();
        int len = salesTimeBuckets.size();
        int step = new Double(Math.ceil(len * 1.0 / STEP_LENTH)).intValue();
        if (step == 0) {
            step = 1;
        }
        double oneStepX = WIDTH_X * 1.0 / len;
        double halfStepX = WIDTH_X * 1.0 / (2 * len);
        Point2D.Double[] points = new Point2D.Double[len];
        for (int i = 0; i < len; i++) {

            double y1 = HEIGHT_Y + UP_MARGIN + INTERVAL_1_Y;
            double x1 = oneStepX * (i + 1) + INTERVAL_3_X + LEFT_MARGIN - halfStepX;
            points[i] = new Point2D.Double(x1, y1);
        }

        for (int i = 0; i < len; i++) {
            if (i % step == 0) {
                Point2D.Double point1 = points[i];
                SVGOMLineElement xMark1 = SvgUtils.createLine(doc);
                new SvgAttr(xMark1).stroke("#D6DEFC", 1.2)
                        .lineXy(point1.getX(), point1.getY(), point1.getX(), point1.getY() + 2);
                svg.appendChild(xMark1);


                SVGOMTextElement yearText = SvgUtils.createText(doc);
                yearText.setTextContent(salesTimeBuckets.get(i));
                new SvgAttr(yearText)
                        .font("12.5", "PingFangSC-Regular, PingFang SC")
                        .fill("#27243F")
                        .coordinate(point1.getX(), point1.getY() + 25)
                        .textAnchor("middle")
                        .fontWeight("400")
                        .transform("rotate(" + rotate + "," + (point1.getX()) + ", " + (point1.getY() + 25) + ")")
                ;
                svg.appendChild(yearText);
            }

        }
    }

    //画点
    private void drawBrokenPoint(ColorAttr color) {

        TakeLandAttitudeAxis takeLandAttitudeAxis = axis;
        Map<String, String> attitudeHashMap = TakeLandAttitudeAxis.ATTITUDE_MAP;
        int[] scale = takeLandAttitudeAxis.getScale();
        int maxValue = takeLandAttitudeAxis.getMaxValue();
        //分短刻度
        List<Attitude> attitudes = takeLandAttitudeAxis.getAttitudes();

        List<BigDecimal> salesRates = data.getSalesRates();
        int len = salesRates.size();
        int interval = len / STEP_LENTH;
        if (interval == 0) {
            interval = 1;
        }
        double reHe = HEIGHT_Y * 1.0 / attitudeHashMap.size();
        double oneStepX = WIDTH_X * 1.0 / len;
        double oneStepW = WIDTH_X * 1.0f / (len + 1);
        double halfStepX = WIDTH_X * 1.0 / (2 * len);
        Point2D.Double[] points = new Point2D.Double[len];
        for (int i = 0; i < len; i++) {
            BigDecimal salesRate = salesRates.get(i);
            if (Objects.nonNull(salesRate)) {
                Attitude attitude = withAttitudeBYSalesRate(salesRate, attitudes);

                double x1 = oneStepX * (i + 1) + INTERVAL_3_X + LEFT_MARGIN - halfStepX;
                double y1 = HEIGHT_Y * (maxValue - salesRate.doubleValue()) * 1.0 / maxValue + UP_MARGIN + INTERVAL_1_Y;

                y1 = (attitude.getStep() - 1) * reHe +
                        (1 - (salesRate.doubleValue() - attitude.getMin()) * 1.0 / attitude.getDiff()) * reHe + UP_MARGIN + INTERVAL_1_Y;

                points[i] = new Point2D.Double(x1, y1);
            } else {
                points[i] = null;
            }

        }
        List<Point2D.Double[]> cPoints = continuousPoints(points);
        for (Point2D.Double[] cPoint : cPoints) {
            SVGOMPoint[] line = new SVGOMPoint[cPoint.length];
            for (int i = 0; i < cPoint.length; i++) {
                line[i] = new SVGOMPoint(
                        new Double(cPoint[i].getX()).floatValue(),
                        new Double(cPoint[i].getY()).floatValue());
            }
            drawCurve(line, new StrokeAttr(color.getColor(), oneStepW / (38)), ColorAttr.NONE);
        }
        double r = oneStepW / (24);
        //画点
        for (int i = 0; i < points.length; i++) {
            if (i % interval == 0) {
                Point2D.Double point = points[i];
                if (Objects.nonNull(point)) {
                    drawCircle(point, r, new StrokeAttr("#FFFFFF", 1.2), color);
                }
            }
        }

//        for (int i = 0; i < len; i++) {
//            Point2D.Double point1 = points[i];
//            Point2D.Double point2 = null;
//            if (i != len - 1) {
//                point2 = points[i + 1];
//            }
//            if (Objects.nonNull(point1) && Objects.nonNull(point2)) {
//                SVGOMLineElement xLine = SvgUtils.createLine(doc);
//                new SvgAttr(xLine).stroke("#F55B77", 1.0)
//                        .lineXy(point1.getX(), point1.getY(), point2.getX(), point2.getY());
//                svg.appendChild(xLine);
//            }
//
//            //加个原点
//            if (i % interval == 0) {
//                if (Objects.nonNull(point1)) {
//
//                    drawCircle(point1, 1.2, new StrokeAttr("#FFFFFF", 1.1), new ColorAttr("#F55B77"));
//                }
//            }
//        }
    }

    /**
     * 返回分段刻度
     *
     * @param salesRate
     * @param attitudes
     * @return
     */
    private Attitude withAttitudeBYSalesRate(BigDecimal salesRate, List<Attitude> attitudes) {
        double sale = salesRate.doubleValue();
        Attitude dd = null;
        for (Attitude attitude : attitudes) {
            if (attitude.min <= sale && sale <= attitude.max) {
                dd = attitude;
                break;
            }
        }
        return dd;
    }

    @Override
    protected void drawXY() {
        // Y轴的竖向实线和X轴的横向实线
        SVGOMLineElement yLine = SvgUtils.createLine(doc);
        double ySt = HEIGHT_Y + UP_MARGIN + INTERVAL_1_Y;
        new SvgAttr(yLine).stroke("#EAEFF4", 1.2)
                .lineXy(LEFT_MARGIN, UP_MARGIN, LEFT_MARGIN, ySt);
        svg.appendChild(yLine);
        SVGOMLineElement xLine = SvgUtils.createLine(doc);
        new SvgAttr(xLine).stroke("#EAEFF4", 1.2)
                .lineXy(LEFT_MARGIN, ySt, WIDTH, ySt);
        svg.appendChild(xLine);
    }

    /**
     *
     */
    private void drawBackgroundBorder() {

        TakeLandAttitudeAxis takeLandAttitudeAxis = axis;
        Map<String, String> attitudeHashMap = TakeLandAttitudeAxis.ATTITUDE_MAP;
        int[] scale = takeLandAttitudeAxis.getScale();
        int maxValue = takeLandAttitudeAxis.getMaxValue();
        //区间分开
        double reSize = attitudeHashMap.size();

        int size = 1;
        for (Map.Entry<String, String> entry : attitudeHashMap.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            double y1 = HEIGHT_Y * scale[size - 1] * 1.0 / maxValue + UP_MARGIN + INTERVAL_1_Y;

            double y2 = HEIGHT_Y * scale[size] * 1.0 / maxValue + UP_MARGIN + INTERVAL_1_Y;

            y1 = HEIGHT_Y * (size - 1) * 1.0 / reSize + UP_MARGIN + INTERVAL_1_Y;

            y2 = HEIGHT_Y * size * 1.0 / reSize + UP_MARGIN + INTERVAL_1_Y;

            double reHeight = y2 - y1;

            //化矩形
            SVGOMRectElement rectElement1 = SvgUtils.createRect(doc);

            new SvgAttr(rectElement1).stroke("#EAEFF4", 0)
                    .original(LEFT_MARGIN + INTERVAL_3_X, y1, WIDTH_X, reHeight)
                    .fill(mapValue).fillOpacity(0.24);

            SVGOMRectElement rectElement2 = SvgUtils.createRect(doc);

//            new SvgAttr(rectElement2).stroke("#EAEFF4", 0)
//                    .original(WIDTH - RIGHT_TEXT - RIGHT_MARGIN, y1, RIGHT_TEXT, reHeight)
//                    .fill(mapValue);

            SVGOMTextElement textElement = SvgUtils.createText(doc);
            textElement.setTextContent(mapKey);
            new SvgAttr(textElement)
                    .fill("#27243F")
                    .font("16", "PingFangSC-Medium, PingFang SC")
                    .fontWeight("400")
//                    .textAnchor("middle")
//                    .coordinate(WIDTH - RIGHT_TEXT * 1.0 / 2 - RIGHT_MARGIN, (reHeight) * 1.0 / 2 + y1)
                    .textAnchor("end")
                    .coordinate(LEFT_MARGIN - 20, (reHeight) * 1.0 / 2 + y1);
            ;

            svg.appendChild(rectElement1);
//            svg.appendChild(rectElement2);
            svg.appendChild(textElement);
            size++;
        }
    }

    /**
     *
     */
    @Override
    protected void drawOutlineBorder() {
        ShapeAttr shapeAttr = new ShapeAttr(
                new Point(0, 0),
                new Dimension2DDouble(WIDTH, HEIGHT));
        StrokeAttr strokeAttr = new StrokeAttr("#EAEFF4", 1);
        ColorAttr colorAttr = new ColorAttr("#FBFCFD");
        drawRect(shapeAttr, strokeAttr, colorAttr, 0);
    }

    @Data
    public static class Attitude {

        private String attitude;

        private int step;

        private double diff;

        private double min;

        private double max;
    }

    //刻度
    @Getter
    public static class TakeLandAttitudeAxis {
        //一格10的数据量
        private final static int ONE_STEP_SIZE = 10;

        //态度
        private final static Map<String, String> ATTITUDE_MAP = new LinkedHashMap<String, String>() {
            {
                put("激进", "#FFAEAC");
                put("积极", "#F9D4AE");
                put("适度", "#FBF2D7");
                put("稳健", "#B7E4E9");
                put("保守", "#D9F1F0");
            }
        };

        List<Attitude> attitudes;

        private int[] scale;

        private int maxValue;
//        //一共需要多少格子
//        private int allStep;

        public TakeLandAttitudeAxis(BigDecimal max) {
            maxValue = 80;
            int[] boundaryArray = {60, 40, 30, 20, 0};
            int len = boundaryArray.length;
            int[] boundaryAndMaxArray = new int[len + 1];

            if (!max.equals(BigDecimal.ZERO)) {
                //最大值向上取整
                maxValue = max.divide(new BigDecimal(String.valueOf(ONE_STEP_SIZE))).setScale(0, BigDecimal.ROUND_UP).intValue() * ONE_STEP_SIZE;
            }
            /**
             * - 激进：拿地销售比 ≥ 60%
             * - 积极：40% ≤ 拿地销售比 ＜ 60%
             * - 适度：30% ≤ 拿地销售比 ＜ 40%
             * - 稳健：20% ≤ 拿地销售比 ＜ 30%
             * - 保守：拿地销售比 ≤ 20%
             */


            boundaryAndMaxArray[0] = maxValue;
            System.arraycopy(boundaryArray, 0, boundaryAndMaxArray, 1, len);

//            allStep = maxValue / oneStepSize;
            scale = new int[boundaryAndMaxArray.length];
            for (int i = 0; i < boundaryAndMaxArray.length; i++) {
                int boundary = boundaryAndMaxArray[i];
                int remain = maxValue - boundary;
                scale[i] = remain;
            }
            //处理分段刻度
            attitudes = buildAttitudes();
        }

        private List<Attitude> buildAttitudes() {
            int[] scale = this.getScale();
            int maxValue = this.getMaxValue();
            Map<String, String> attitudeHashMap = TakeLandAttitudeAxis.ATTITUDE_MAP;
            int size = 1;
            //分短刻度
            List<Attitude> attitudes = Lists.newArrayList();
            for (Map.Entry<String, String> entry : attitudeHashMap.entrySet()) {

                String mapKey = entry.getKey();
                Attitude attitude = new Attitude();
                attitude.setAttitude(mapKey);
                attitude.setStep(size);

                int scale2 = scale[size];
                int scale1 = scale[size - 1];
                attitude.setDiff(scale2 - scale1);
                attitude.setMin(maxValue - scale2);
                attitude.setMax(maxValue - scale1);
                attitudes.add(attitude);
                size++;
            }

            return attitudes;
        }
        //分短刻度

    }

    //拿地态度数据
    @Getter
    @ToString
    public static class TakeLandAttitudeSvgData extends TakeBaseData {

        //每季度拿地销售比
        private List<BigDecimal> salesRates = Lists.newArrayList();

        /**
         * @return
         */
        public static TakeLandAttitudeSvgData mock() {

            TakeLandAttitudeSvgData data = new TakeLandAttitudeSvgData();
            //生成10个随机数据

            for (int i = 1; i <= 15; i++) {
                double value = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

                BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);

//                if (i%5==0){
//                    bigDecimalValue=null;
//                }
                data.addBucket(bigDecimalValue, String.format("第%s季度", i));

            }
            return data;
        }

        public void addBucket(BigDecimal rate, String timeBucket) {
            salesRates.add(rate);
            titleList.add(timeBucket);
        }

        //获取做大值排除空值
        public BigDecimal maxSaleValue() {
            return maxBigDecimalValue(salesRates);
        }
    }
}

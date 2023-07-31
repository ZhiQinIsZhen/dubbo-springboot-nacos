package com.liyz.dubbo.service.pdf.svg.axis;

import com.liyz.dubbo.service.pdf.svg.AxisScale;
import com.liyz.dubbo.service.pdf.utils.SvgNumUtils;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/10 10:35
 */
@UtilityClass
public class AxisUtils {
    private static final int MAX_Y = 400_000_000;
    public static void main(String[] args) {

//        print(AxisUtils.calAxis(0,0.01, 6));
//        print(AxisUtils.calAxis(0,0.1, 5));
//        print(AxisUtils.calAxis(0,1, 11));
//        print(AxisUtils.calAxis(0,333, 11));
//        print(AxisUtils.calAxis(0,455, 5));
//        print(AxisUtils.calAxis(0,678, 21));
//        print(AxisUtils.calAxis(0,676676, 11));
//        print(AxisUtils.calAxis(0,543, 11));
//        print(AxisUtils.calAxis(0,566556, 5));

//        System.out.println(Arrays.toString(new BestMatchLowerUpper(-99,0,4).cal()));
//        System.out.println(Arrays.toString(new BestMatchLowerUpper(0,10,5).cal()));
//        System.out.println(Arrays.toString(new BestMatchLowerUpper(-9,236,6).cal()));
//        System.out.println(Arrays.toString(new BestMatchLowerUpper(-988,2600,7).cal()));
//        System.out.println(Arrays.toString(new BestMatchLowerUpper(-988,23600,8).cal()));
//        System.out.println(Arrays.toString(new BestMatchLowerUpper(-988,0,8).cal()));
//        System.out.println(Arrays.toString(calParts(0.01,0.01, 6)));
//        System.out.println(Arrays.toString(calParts(-0.01,0.9, 6)));
//        System.out.println(Arrays.toString(calParts(-234.01,65.9, 6)));
//        System.out.println(Arrays.toString(calParts(-234.0,65.0, 6)));
//        System.out.println(Arrays.toString(calParts(0,200, 6)));
//        System.out.println(Arrays.toString(calParts(0,100, 6)));
//        System.out.println(Arrays.toString(calParts(0,10, 6)));
//        System.out.println(Arrays.toString(calParts(0,500, 6)));
    }

    public static void print(Axis axis){
        if (axis != null) {
            StringBuilder display = new StringBuilder("文本：\t");
            StringBuilder value = new StringBuilder("值：\t");
            for (AxisScale yax : axis.getAxisScales()) {
                display.append(yax.getDisplayText()).append("\t");
                value.append(BigDecimal.valueOf(yax.getValue()).stripTrailingZeros().toPlainString()).append("\t");
            }
            System.out.println(display.toString());
            System.out.println(value.toString());
            System.out.println();
        }
    }

//    /**
//     * 依据最大值、最小值、刻度数量自动计算出合适的刻度区间数组
//     * 区间数组的最大值可能会比给予的大，区间数组的最小值可能会比给予的小
//     * @param min 整数：int表数范围内；小数：精确到小数点后2位
//     * @param max 整数：int表数范围内；小数：精确到小数点后2位
//     * @param scales 刻度数量，必须>=2
//     * @return 返回的对象的数组的长度是scales-1
//     */
//    @Deprecated
//    public static Axis calAxis(double min, double max, int scales){
//        double[] parts = SvgNumUtils.reverseOrder(calParts(min, max, scales));
//        // 处理格式问题
//        return format(parts);
//    }

    public static Axis format(double[] parts) {
        double maxWidth = maxWidth(parts);
        Function<Double, String> func;
        if(SvgNumUtils.isInteger(maxWidth)){
            DecimalFormat df = new DecimalFormat("0");
            func = df::format;
        }else{
            // 依据最大值创建格式化对象，目的是小数点对齐
            DecimalFormat df = createDecimalFormatByDouble(maxWidth);
            func = df::format;
        }
        AxisScale[] axisScales = Arrays.stream(parts)
                .boxed()
                .map(d -> new AxisScale(d, func.apply(d)))
                .toArray(AxisScale[]::new);
        return new Axis(axisScales);
    }

//    @Deprecated
//    private static double[] calParts(double lower, double upper, int scales){
//        if(!(scales>=2)){
//            throw new IllegalArgumentException("scales（"+scales+"）参数不合法");
//        }
//        if(!(upper >= lower && lower <= MAX_Y && upper <= MAX_Y)){
//            throw new IllegalArgumentException("lower（"+lower+"）和upper（"+upper+"）参数不合法");
//        }
//        // 分成num个区间
//        int num = scales -1;
//        boolean upIsInteger = SvgNumUtils.isInteger(upper);
//        boolean downIsInteger = SvgNumUtils.isInteger(upper);
//        int upInt=0,downInt=0,times=1;
//        if(upIsInteger && downIsInteger){
//            upInt= (int) upper;
//            downInt= (int) lower;
//        }else{
//            /*
//             * 处理成整数
//             */
//            upper = BigDecimal.valueOf(upper).setScale(2, BigDecimal.ROUND_CEILING).multiply(BigDecimal.valueOf(100)).doubleValue();
//            lower = BigDecimal.valueOf(lower).setScale(2, BigDecimal.ROUND_FLOOR).multiply(BigDecimal.valueOf(100)).doubleValue();
//            times = 100;
//            upInt = (int) (upper);
//            downInt = (int) (lower);
//        }
//        if(upInt == downInt){
//            upInt = downInt + scales;
//        }
//
//        if(1 == num){
//            return new double[]{downInt, upInt};
//        }
//        int[] ints1 = new BestMatchLowerUpper(downInt, upInt, num).cal();
//        int[] ints = splitParts(ints1[0], ints1[1], num);
//        return SvgNumUtils.toDoubleArray(ints, times);
//    }

//    /**
//     * 在{lower,upper}区间内分成num-1个区间，数组长度是num
//     * 必须保证满足：upper-lower是num的倍数 && num >=2
//     * @return 返回数组长度为num+1
//     */
//    private static int[] splitParts(int lower, int upper, int num){
//        int diff = upper - lower;
//        if(!(upper > lower && num >=2 && diff%num == 0)){
//            throw new IllegalArgumentException(String.format("参数不合法： %s %s %s", lower, upper, num));
//        }
//        int step = diff / num;
//        int[] parts = new int[num+1];
//        for (int i = 0; i < parts.length; i++) {
//            parts[i] = lower + i * step;
//        }
//        return parts;
//    }

    private static double maxWidth(double[] parts){
        List<Double> list = Arrays.stream(parts)
                .boxed()
                .sorted(Comparator.comparing(d -> SvgNumUtils.isInteger(d) ? String.valueOf(d.intValue()).length() : String.valueOf(d).length()))
                .collect(Collectors.toList());
        return list.get(list.size()-1);
    }

    public static DecimalFormat createDecimalFormatByDouble(double d){
        DecimalFormat df2 = new DecimalFormat("0.####");
        String format = df2.format(d);
        int decimalDigits = format.length()-format.lastIndexOf(".")-1;
        // 只考虑1位小数点和两位小数点
        if(decimalDigits == 1){
            return new DecimalFormat("0.0");
        }else{
            return new DecimalFormat("0.00");
        }
    }

//    /**
//     * 求最佳匹配倍数
//     */
//    @ToString
//    private static class BestMatchMultiple {
//        private static int limitCount = 100_000;
//        /**
//         * 因子
//         */
//        private int[] factors;
//        private NextNum nextNum;
//
//        public BestMatchMultiple(int[] factors, NextNum nextNum) {
//            this.factors = factors;
//            this.nextNum = nextNum;
//        }
//
//        public int cal(){
//            int count = 0;
//            int nextNumber = nextNum.initValue();
//            boolean isRemainder = SvgNumUtils.isCommonMultiple(nextNumber, factors);
//            while (!isRemainder){
//                nextNumber = nextNum.next();
//                if(0 == nextNumber){
//                    continue;
//                }
//                isRemainder = SvgNumUtils.isCommonMultiple(nextNumber, factors);
//                count ++;
//                if(count > limitCount){
//                    throw new RuntimeException("超出循环上限也找不到最小公约数！"+toString());
//                }
//            }
//            return nextNumber;
//        }
//
//    }
//    /**
//     * 求匹配的数
//     */
//    @ToString
//    private static class BestMatchNum {
//        private NextNum nextNum;
//        private Predicate<Integer> predicate;
//
//        public BestMatchNum(NextNum nextNum, Predicate<Integer> predicate) {
//            this.nextNum = nextNum;
//            this.predicate = predicate;
//        }
//
//        public int cal(){
//            int number = nextNum.initValue();
//            boolean test = predicate.test(number);
//            while (!test){
//                number = nextNum.next();
//                test = predicate.test(number);
//            }
//            return number;
//        }
//    }

//    /**
//     * 输入两个浮点数最多精确到两位小数点，求最佳放大倍数使浮点数变为整数
//     */
//    private static class BestMatchGain{
//        private double[] origins;
//
//        public BestMatchGain(double[] origins) {
//            this.origins = origins;
//        }
//
//        public int cal(){
//            int times = 1;
//            for (double origin : origins) {
//                int zoom = SvgNumUtils.minOfNumDigits(SvgNumUtils.decimalAccurateDigit(origin, 2));
//                times = Math.max(zoom, times);
//            }
//            return times;
//        }
//    }
//    /**
//     * 求一个区间，这个区间包含给予的最大值和最小值，并且区间的最大值和最小值的差是num的倍数
//     * 如果区间最小值是一个2位以上的数（包含），还会给予一定的美观优化
//     */
//    private static class BestMatchLowerUpper{
//        private int lower;
//        private int upper;
//        private int num;
//
//        public BestMatchLowerUpper(int lower, int upper, int num) {
//            this.lower = lower;
//            this.upper = upper;
//            this.num = num;
//        }
//
//        public int[] cal(){
//            int min = lower;
//            int max = upper;
//            int diff = max - min;
//            // 求步长
//            int step = calStep(diff);
//            int minFinal = trancate(min, step, false);
//            max = trancate(max, step, true);
//            // 求max的值，max需要满足(max-min)%num==0 且 (max-min)%step==0
//            int[] factors = {num, step};
//            BestMatchNum bestMatchNum = new BestMatchNum(
//                    new NextNumDefault(max, true),
//                    nextNumber -> SvgNumUtils.isCommonMultiple(nextNumber - minFinal, factors)
//            );
//            int maxFinal = bestMatchNum.cal();
//            return new int[]{minFinal, maxFinal};
//        }
//
//        /**
//         * 进一步处理，保证min是一个5的倍数，例如0、5、10、100、500
//         * 如果min是2位数，则个位数可以是0或5
//         * 如果min是3位数，则个位数必须是0,十位数必须是0或5
//         * 。。。
//         */
//        private int trancate(int initValue, int factor, boolean upDown) {
//            NextNum nextNum = new NextNumDefault(initValue, upDown, 1);
//            BestMatchNum bestMatchNum = new BestMatchNum(nextNum, nextNumber -> nextNumber % factor == 0);
//            return bestMatchNum.cal();
//        }
//
//        private int calStep(int diff){
//            int len = String.valueOf(Math.abs(diff)).length();
//            if(len == 1){
//                return 1;
//            }else if(len == 2){
//                return 5;
//            }else if(len == 3){
//                return 50;
//            }
//            // 接下来至少起步是3位数了
//            String stepStr = IntStream.range(0, len -1)
//                    .boxed()
//                    .map(i -> i == 0 ? "1" : "0")
//                    .collect(Collectors.joining());
//            return Integer.parseInt(stepStr);
//        }
//    }


}

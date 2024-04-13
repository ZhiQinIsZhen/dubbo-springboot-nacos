package com.liyz.dubbo.service.pdf.svg.axis;

import com.liyz.dubbo.service.pdf.utils.SvgNumUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author ChenHao
 * @Date 2022/12/7 11:05
 */
public class AxisStepHelper {
    /**
     * 区间步长的规律数组
     * 区间步长=这些数乘以或除以(10的倍数)的商
     */
    public static final int[] REGION_STEP_ARR_DEFAULT = new int[]{1,2,3,5};
    /**
     * 这个舍入模式对于刻度尺的计算有这举足轻重的作用，只推荐BigDecimal.ROUND_UP、BigDecimal.ROUND_HALF_UP
     */
    private static final int ROUND_MODE = BigDecimal.ROUND_UP;

    private double quotient;

    public AxisStepHelper(double quotient) {
        this.quotient = quotient;
    }

    /**
     * 依据商得出区间步长
     */
    public double cal(){
        if(SvgNumUtils.isInteger(quotient)){
            // 整数
            return bestRegionStepNumber((long)quotient);
        }else{
            // 浮点数
            double abs = Math.abs(quotient);
            if(abs < 1){
                // 小于1的小数
                /*
                 * 转换
                 */
                int[] ints = firstFraction(quotient);
                double stepNumber = bestRegionStepNumber(ints[0]);
                // 放大多少倍缩小多少倍
                return stepNumber/ints[1];
            }else{
                // 舍入取整，按整数处理
                long longValue = BigDecimal.valueOf(quotient).setScale(0, ROUND_MODE).longValue();
                return bestRegionStepNumber(longValue);
            }
        }
    }
    /**
     * 给定一个整数得出区间步长
     */
    private double bestRegionStepNumber(long number){
        long n = String.valueOf(Math.abs(number)).length();
        long multiple = 1;
        if(n > 1){
            StringBuilder sb = new StringBuilder("1");
            for (int i = 1; i < n; i++) {
                sb.append("0");
            }
            multiple = Long.valueOf(sb.toString());
        }
        long step = REGION_STEP_ARR_DEFAULT[0] * multiple;
        while(step < number){
            for (int i = 0; i < REGION_STEP_ARR_DEFAULT.length; i++) {
                step = REGION_STEP_ARR_DEFAULT[i] * multiple;
                if(step >= number){
                    return step;
                }
            }
            multiple*=10;
        }
        return step;
    }

    /**
     * 乘数X被乘数=积
     * 把小数转换为只有一位有效位数的位数 和 这个小数位数的求整数（0.1*10=1、0.02*100=1，被乘数就是这个求整数，乘数是转换过后的小数）
     */
    public int[] firstFraction(double number){
        BigDecimal a = BigDecimal.valueOf(Math.abs(number));
        BigDecimal b = a.setScale(0, RoundingMode.DOWN);
        // 得到小数部分（需要设置精度，否则后面转int报错了）
        BigDecimal c = a.subtract(b).setScale(AxisHelper.SCALE_DEFAULT, BigDecimal.ROUND_HALF_UP);
        if(BigDecimal.ZERO.compareTo(c) == 0){
            // 整数
            return new int[]{0,1};
        }

        /*
         * 把这个小于1的小数转换为只保留一位小数的数
         */
        BigDecimal d = keepOneValidFraction(c);
        if(SvgNumUtils.isInteger(d)){
            return new int[]{1,1};
        }

        /*
         * 到这一步啦一定是一个只有一位有效位数的小数，并且是小于1的正整数
         */
        String fractionStr = d.stripTrailingZeros().toPlainString();
        int int1 = fractionStr.charAt(fractionStr.length()-1)-'0';
        int int2 = multipleOfTen(fractionStr.length() - 2);
        return new int[]{int1, int2};
    }

    /**
     * 保留一位最高的有效小数位数
     * @param c c的绝对值必须小于1
     * @return
     */
    private BigDecimal keepOneValidFraction(BigDecimal c) {
        c = c.abs();
        if(c.compareTo(BigDecimal.ONE) >= 0){
            throw new IllegalArgumentException("c必须小于1");
        }
        char[] fractionChars = c.stripTrailingZeros()
                .toPlainString()
                .replaceFirst("^0\\.", "")
                .toCharArray();
        int invalidChar = 0;
        for (int i = 0; i < fractionChars.length; i++) {
            char fractionChar = fractionChars[i];
            if(fractionChar == '0'){
                invalidChar ++;
            }else{
                break;
            }
        }

        BigDecimal d = c.setScale(invalidChar+1, ROUND_MODE);
        return d;
    }

    /**
     *
     * @param n
     * @return
     */
    private int multipleOfTen(int n) {
        if(n <=0){
            return 1;
        }
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }
        return Integer.parseInt(sb.toString());
    }
}

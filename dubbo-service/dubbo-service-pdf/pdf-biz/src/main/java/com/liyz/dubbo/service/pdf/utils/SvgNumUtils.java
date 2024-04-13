package com.liyz.dubbo.service.pdf.utils;

import lombok.experimental.UtilityClass;
import org.apache.batik.svggen.SVGGeneratorContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/8 9:12
 */
@UtilityClass
public class SvgNumUtils {
    private static final float EPSILON = 1e-6f;
    /**
     * 默认保留3位小数
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");
    /**
     * 判断double是否一个整数
     * @param d
     * @return
     */
    public static final boolean isInteger(double d){
        return Math.abs(d- Math.round(d)) <= Double.MIN_VALUE;
    }
    public static final boolean isInteger(BigDecimal d){
        BigDecimal c = d.setScale(0, RoundingMode.DOWN);
        return d.compareTo(c) == 0;
    }
    public static final boolean isAllInteger(double[] ds){
        for (double d : ds) {
            if(!isInteger(d)){
                return false;
            }
        }
        return true;
    }

    public static final double[] reverseOrder(double[] doubles){
        return Arrays.stream(doubles)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToDouble(Double::doubleValue)
                .toArray();
    }




    /**
     * 求一个比num位数大的最小整数，也就是求10、100、1000、10000。。。
     * @param num
     * @return
     */
    public static int minOfNumDigits(int num){
        char[] chars = new char[1+num];
        chars[0] = '1';
        for (int i = 1; i <= num; i++) {
            chars[i] = '0';
        }
        return Integer.parseInt(new String(chars));
    }

    /**
     * 求小数精确位数
     * @param scale 精确小数位数
     */
    @Deprecated
    public static int decimalAccurateDigit(double d, int scale){
        BigDecimal b1 = BigDecimal.valueOf(d);
        BigDecimal b2 = b1.setScale(0, BigDecimal.ROUND_HALF_UP);
        BigDecimal b3 = b1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        BigDecimal diff = b2.subtract(b3);
        if(BigDecimal.ZERO.compareTo(diff) == 0){
            return 0;
        }
        String s = diff.stripTrailingZeros().toPlainString();
        int length = s.substring(s.lastIndexOf(".") + 1).length();
        return length;
    }

    public static double[] toDoubleArray(int[] ints, double divisor){
        return Arrays.stream(ints)
                .mapToDouble(value -> value/divisor)
                .toArray();
    }

    /**
     * Converts the input double value to a string with a number of
     * decimal places controlled by the precision attribute.
     * @see SVGGeneratorContext#doubleString(double)
     */
    public static final String doubleString(double value) {
        double absvalue = Math.abs(value);
        // above 10e7 we do not output decimals as anyway
        // in scientific notation they were not available
        if (absvalue >= 10e7 || (int)value == value) {
            return Integer.toString((int)value);
        }
        // under 10e-3 we have to put decimals
        else {
            return DECIMAL_FORMAT.format(value);
        }
    }

    public static final boolean eq(double a, double b){
        return (Math.abs(a - b) < EPSILON);
    }

    /**
     * 求有效的小数位数数量
     * @param v
     * @return
     */
    public static final int fractionNumber(double v){
        String str = BigDecimal.valueOf(v).stripTrailingZeros().toPlainString();
        int i = str.lastIndexOf(".");
        if(i < 0){
            // 整数
            return 0;
        }else{
            return str.length() - i - 1;
        }
    }



    /**
     * hcms数组里的所有数是否都是v的因子
     * @param v
     * @param hcms
     * @return
     */
    public static final boolean isCommonMultiple(int v, int[] hcms){
        for (int hcm : hcms) {
            if(hcm == 0){
                throw new IllegalArgumentException("除数为0！");
            }
            if(v%hcm !=0){
                return false;
            }
        }
        return true;
    }

    public static final double doubleformat(double d){
        String format = String.format("%.3f", d);
        return Double.parseDouble(format);
    }
    public static void main(String[] args) {
        System.out.println(doubleformat(0.00000004));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(10,2)));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(11,2)));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(0.1,2)));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(0.11,2)));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(-0.111,2)));
//        System.out.println(minOfNumDigits(decimalAccurateDigit(-0.115,2)));


    }
}

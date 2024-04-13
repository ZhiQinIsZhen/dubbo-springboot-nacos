package com.liyz.dubbo.service.pdf.svg.axis;

import com.itextpdf.styledxmlparser.css.util.CssUtils;
import com.liyz.dubbo.service.pdf.utils.SvgNumUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ChenHao
 * @Date 2022/12/5 16:16
 * 模仿百度charts的刻度尺（值类型）计算帮助类
 */
@Slf4j
public class AxisHelper {
//    private static volatile Axis defaultAxis;
//    private static final Object DEFAULT_AXIS_LOCK = new Object();
    /**
     * 默认保留4位小数
     */
    public static final int SCALE_DEFAULT = 4;
    /**
     * 默认的区间数量
     */
    private static final int[] REGION_NUM_ARR_DEFAULT = new int[]{5,6,7,4,8};
    private static final int REGION_NUM_DEFAULT = 5;

    private final double max;
    private final double min;
//    private final double[] origin;
    /**
     * 指定的区间数量，未指定为0
     */
    private final int assignRegionNum;
    private final int[] regionNumArr;
    public AxisHelper(double[] data){
        this(data, 0);
    }

    public AxisHelper(double[] data, int assignRegionNum) {
        if(data == null || data.length < 1){
            throw new IllegalArgumentException("data不能为空。");
        }
//        this.origin = data;

        int len = data.length;
        double[] dataCopy = new double[len];
        System.arraycopy(data, 0, dataCopy, 0, len);
        Arrays.sort(dataCopy);
        this.max = Math.max(0, truncateScale(dataCopy[len-1]));
        this.min = Math.min(0, truncateScale(dataCopy[0]));

        this.assignRegionNum = assignRegionNum;
        if(isCustomAssignRegionNum(assignRegionNum) && !ArrayUtils.contains(REGION_NUM_ARR_DEFAULT, assignRegionNum)){
            this.regionNumArr = new int[REGION_NUM_ARR_DEFAULT.length +1];
            System.arraycopy(REGION_NUM_ARR_DEFAULT, 0, this.regionNumArr, 0, REGION_NUM_ARR_DEFAULT.length);
            this.regionNumArr[REGION_NUM_ARR_DEFAULT.length] = assignRegionNum;
        }else {
            this.regionNumArr = REGION_NUM_ARR_DEFAULT;
        }
    }

    private boolean isCustomAssignRegionNum(int assignRegionNum){
//        return !ArrayUtils.contains(REGION_NUM_DEFAULT, assignRegionNum);
        return assignRegionNum != 0;
    }

    /**
     * 计算刻度尺
     * @return
     */
    public Axis cal(){
        if(CssUtils.compareFloats(max, 0) && CssUtils.compareFloats(min, 0)){
            Candidacy candidacy = getDefaultAxis();
            return candidacy.toAxis();
        }
        List<Candidacy> candidacyList = calCandidacyList();
        candidacyList = candidacyList.stream()
                .sorted(Comparator.comparing(Candidacy::getAffinity))
                .collect(Collectors.toList());
        return candidacyList.get(0).toAxis();
    }

    /**
     * 计算刻度尺 --只要整数刻度
     * @return
     */
    public Axis cal(int[] step){
        if(CssUtils.compareFloats(max, 0) && CssUtils.compareFloats(min, 0)){
            Candidacy candidacy = getDefaultAxis();
            return candidacy.toAxis();
        }
        List<Candidacy> candidacyList = calCandidacyList(step);
        candidacyList = candidacyList.stream().filter(x->x.getRegionStepNumber()>=1).collect(Collectors.toList());
        //添加了一种整数的 --判断整数数组中 最合适的
        int index = 0;
        if(!CollectionUtils.isEmpty(candidacyList) && candidacyList.size()>1){
            index = 1;
        }
        candidacyList = candidacyList.stream()
                .sorted(Comparator.comparing(Candidacy::getAffinity))
                .collect(Collectors.toList());
        return candidacyList.get(index).toAxis();
    }


    private Candidacy getDefaultAxis(){
        int len = REGION_NUM_DEFAULT + 1;
        double[] scaleArr = new double[len];
        int step = AxisStepHelper.REGION_STEP_ARR_DEFAULT[0];
        for (int i = 0; i < len; i++) {
            scaleArr[i] = i * step;
        }
        return new Candidacy(scaleArr, step, REGION_NUM_DEFAULT);
    }

    private List<Candidacy> calCandidacyList(){
        // 上限与下限的差
        double diff = this.max - this.min;
        List<Candidacy> candidacyList = new ArrayList<>();
        for (int i = 0; i < regionNumArr.length; i++) {
            // 区间数量（这个只是预计值不是最终值）
            int preRegionNum = regionNumArr[i];
            // 商
            double quotient = diff / preRegionNum;
            // 根据商得出区间步长
            double regionStepNumber = new AxisStepHelper(quotient).cal();
            // 依据区间步长、区间数得出刻度尺
            double[] scaleArr = calScaleArr(regionStepNumber, preRegionNum);
            Candidacy candidacy = new Candidacy(scaleArr, regionStepNumber, preRegionNum);
            candidacyList.add(candidacy);
        }
        return candidacyList;
    }


    private List<Candidacy> calCandidacyList(int[] divide){
        // 上限与下限的差
        List<Candidacy> candidacyList = calCandidacyList();
        double diff = this.max - this.min;
        for (int i = 0; i < divide.length; i++) {
            // 区间数量（这个只是预计值不是最终值）
            int preRegionNum = divide[i];
            // 商
            double quotient = diff / preRegionNum;
            // 根据商得出区间步长
            double regionStepNumber = new AxisStepHelper(quotient).cal();
            // 依据区间步长、区间数得出刻度尺
            double[] scaleArr = calScaleArr(regionStepNumber, preRegionNum);
            Candidacy candidacy = new Candidacy(scaleArr, regionStepNumber, preRegionNum);
            candidacyList.add(candidacy);
        }
        return candidacyList;
    }

//    /**
//     * 是否一个符合区间间隔规律的数
//     * @param v
//     * @return
//     */
//    private boolean isRegionLawNumber(double v){
//        if(SvgNumUtils.isInteger(v)){
//            // 是整数
//            return isRegionLawNumber((int)v);
//        }else{
//            // 是小数
//            double abs = Math.abs(v);
//            if(abs > 1){
//                // 绝对值大于1的浮点数一定不是
//                return false;
//            }
//            String str = BigDecimal.valueOf(abs).stripTrailingZeros().toPlainString();
//            String integerStr = str.replaceFirst("^0\\.0*", "");
//            return isRegionLawNumber(Integer.parseInt(integerStr));
//        }
//    }
//    private boolean isRegionLawNumber(int v){
//        int multiple = multipleOfTen(v);
//        for (int i = 0; i < REGION_STEP_DEFAULT.length; i++) {
//            if(v == REGION_STEP_DEFAULT[i]*multiple){
//                return true;
//            }
//        }
//        return false;
//    }




    /**
     * 依据区间步长和区间数计算出刻度尺
     * 刻度尺必须包含0
     * @param regionStepNumber 区间步长
     * @return 刻度尺数组的长度=区间数+1
     */
    private double[] calScaleArr(double regionStepNumber, int preRegionNum){
        int length = preRegionNum + 1;
        List<Double> axisList = new ArrayList<>();
        if(min > 0 || SvgNumUtils.eq(min, 0)){
            // 如果最小值大于等于0
            for(int i=0; ; i++){
                double v = truncateScale(regionStepNumber * i);
                axisList.add(v);
                if(i >= length - 1 && v >= max){
                    break;
                }
            }
        }else if(max < 0 || SvgNumUtils.eq(max, 0)){
            // 如果最大值小于等于0
            for(int i=0; ; i++){
                double v = truncateScale(0 - regionStepNumber * i);
                axisList.add(v);
                if(i >= length - 1 && min >= v){
                    break;
                }
            }
        }else{
            // 0在最小值和最大值中间
            axisList.add(0d);
            boolean findUp = false;
            double upValue=0d;
            double downValue=0d;
            for (int i=0,upIndex=1,downIndex=1; ;i++) {
                if(findUp){
                    // 从0向上找
                    upValue = truncateScale(regionStepNumber * upIndex);
                    axisList.add(upValue);
                    upIndex++;
                }else{
                    // 从0向下找
                    downValue = truncateScale(0 - regionStepNumber * downIndex);
                    axisList.add(downValue);
                    downIndex++;
                    if(downValue > min){
                        // 继续找
                        continue;
                    }else{
                        // 向下找完啦,下面向上找吧
                        findUp = true;
                    }
                }
                //
                if(i >= length - 1 && upValue >= max && min >= downValue){
                    break;
                }
            }
        }
        //
        return axisList.stream()
                .sorted()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }




    private double truncateScale(double d){
        return BigDecimal.valueOf(d)
                .setScale(SCALE_DEFAULT, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }



    /**
     * 候选刻度尺
     */
    @Data
    private class Candidacy{
        /**
         * 刻度数组
         * 从高到低
         */
        private double[] scaleArr;
        /**
         * 区间步长
         */
        private double regionStepNumber;
        /**
         * 实际区间数
         */
        private int regionNum;
        /**
         * 预计区间数
         */
        private int preRegionNum;
        /**
         * 亲和度
         */
        private double affinity;

        public Candidacy(double[] scaleArr, double regionStepNumber, int preRegionNum) {
            // 反向排序一下
            this.scaleArr = SvgNumUtils.reverseOrder(scaleArr);
            this.regionStepNumber = regionStepNumber;
            this.regionNum = scaleArr.length-1;
            this.preRegionNum = preRegionNum;
            this.affinity = calAffinity();
        }

//        /**
//         * 判断刻度尺是否符合要求
//         */
//        private boolean isValid(){
//            double scaleMin = scaleArr[0];
//            double scaleMax = scaleArr[scaleArr.length - 1];
//            boolean valid = (SvgNumUtils.eq(scaleMax, max) || scaleMax > max)
//                    && (SvgNumUtils.eq(scaleMin, min) || scaleMin < min)
//                    && scaleArr.length == regionNum +1;
//            if(!valid && AxisHelper.this.isCustomAssignRegionNum(this.regionNum)){
//                AxisHelper.log.warn("根据原始数据（{}）、步长（{}）、区间数量（{}），计算出的刻度尺（{}）不符合要求，将被丢弃！",
//                        Arrays.toString(AxisHelper.this.origin), this.regionStepNumber, this.regionNum, Arrays.toString(this.scaleArr));
//            }
//            return valid;
//        }

        /**
         * 返回刻度尺的亲和度
         * 亲和度越小越好，亲和度不会小于0
         * @return
         */
        private double calAffinity(){
            // 如果当前区间步长是指定的，则直接返回0
            if(AxisHelper.this.assignRegionNum == preRegionNum){
                return 0;
            }
            return new AffinityStrategy(this).cal();
        }


        private Axis toAxis(){
            Axis axis = AxisUtils.format(this.scaleArr);
            axis.setRegionStepNumber(this.regionStepNumber);
            return axis;
        }
    }

    /**
     * 刻度尺亲和度计算策略
     */
    private class AffinityStrategy{
        private Candidacy candidacy;

        public AffinityStrategy(Candidacy candidacy) {
            this.candidacy = candidacy;
        }

        public double cal(){
            double[] scaleArr = candidacy.getScaleArr();
            // 基础分
            double affinity = 0.1d;
            // double affinity = ((scaleMax - max) + (min - scaleMin))/(scaleMax - scaleMin);
            String regionStepNumberStr = String.valueOf(candidacy.getRegionStepNumber());
            if(regionStepNumberStr.contains("5")){
                affinity *=1;
            }else if(regionStepNumberStr.contains("3")){
                affinity *=1.35;
            }else if(regionStepNumberStr.contains("2")){
                affinity *=1.2;
            }else {
                affinity *=1.1;
            }
            // 5,6,7,4,8
            switch (candidacy.getPreRegionNum()){
                case 5:
                    affinity *= 1;
                    break;
                case 6:
                    affinity *= 1.1;
                    break;
                case 7:
                    affinity *= 1.2;
                    break;
                case 8:
                    affinity *= 1.3;
                    break;
                case 4:
                    affinity *= 1.8;
                    break;
                default:
                    affinity *= 1;
                    break;
            }
            if(locate(AxisHelper.this.max, scaleArr[1], scaleArr[0])){
                affinity *= 0.6;
            }
            if(locate(AxisHelper.this.min, scaleArr[scaleArr.length - 1], scaleArr[scaleArr.length - 2])){
                affinity *= 0.6;
            }
            return affinity;
        }
        private boolean locate(double value, double start, double end){
            return value >= start && value <= end;
        }
    }
    public static void main(String[] args) {
//        AxisUtils.print(new AxisHelper(new double[]{1.61,-0.03},13).cal());
        AxisUtils.print(new AxisHelper(new double[]{1.61,-0.03}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.61,0,53}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.61,0,93}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.61,0,193}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.61,0,293}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.61,0,493}).cal());
        AxisUtils.print(new AxisHelper(new double[]{16,0,89}).cal());
        AxisUtils.print(new AxisHelper(new double[]{-8.5,1.76}).cal());
        AxisUtils.print(new AxisHelper(new double[]{0.36, 13.89}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{70,1933.59}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{70,1933.59},7).cal());

//        AxisUtils.print(new AxisHelper(new double[]{0,0}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1,1}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{-1.22323,5986}).cal());
//        AxisUtils.print(new AxisHelper(new double[]{1.6,-0.03}).cal());
//        System.out.println(Arrays.toString("-".split("-")));
//        System.out.println(Arrays.toString("0-101".split("-")));
//        System.out.println(Arrays.toString("-101".split("-")));

    }
}

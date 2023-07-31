package com.liyz.dubbo.service.pdf.svg.axis;

import com.liyz.dubbo.service.pdf.svg.AxisScale;
import lombok.Data;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/29 15:57
 */
@Data
public class Axis {
    /**
     * 刻度的排序必须是从大到小
     */
    private AxisScale[] axisScales;

    /**
     * 刻度的长度
     */
    private int len;
    /**
     * 刻度的最大值
     */
    private double max;
    /**
     * 刻度的最小值
     */
    private double min;
    /**
     * 刻度的最大值-最小值
     */
    private double totalInterval;
    /**
     * 区间步长
     *
     */
    private double regionStepNumber;
    /**
     * 区间数量
     */
    private int regionNum;


    public Axis(AxisScale[] axisScales) {
        this.axisScales = axisScales;
        this.len = axisScales.length;
        this.max = axisScales[0].getValue();
        this.min = axisScales[len-1].getValue();
        this.totalInterval = height();
        this.regionNum = len -1;
    }

    private double height(){
        return max - min;
    }

    /**
     * 每个刻度的长度
     * @return
     */
    public double interval(){
        return height()/(len -1);
    }

    /**
     * 计算一个值在这个刻度的高度占比（高度位置）
     * 这个值必须在这个刻度的范围内
     */
    public double calRatio(double v){
        return (v-min)/totalInterval;
    }


    public double calRatio(double v,Boolean zeroFlag){
        //如果v大于 0 要取 0 以上的长度 取比例
        if(v>=0){
            return (v-0)/getZeroUp();
        }
        //小于0 的话同理 要拿0以下的长度 比例
        return (0-v)/getZeroDown();
    }
    /**
     *
     * */
    private double getZeroUp(){
        //拿当前值减去0 以上的区间的最小直 = 0  然后除以 最大值到0 的长度
        return max-0;
    }


    /**
     * 获取 0 以上的刻度长度有多长
     * */
    private double getZeroDown(){
        //拿当前值 用 0 作为除数
        return 0-min;
    }
}

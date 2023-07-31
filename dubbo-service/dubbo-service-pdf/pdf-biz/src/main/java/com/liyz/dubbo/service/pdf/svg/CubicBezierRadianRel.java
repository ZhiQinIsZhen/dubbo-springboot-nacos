package com.liyz.dubbo.service.pdf.svg;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/21 14:03
 */
@FunctionalInterface
public interface CubicBezierRadianRel {

    /**
     * 给定曲线终点（相对坐标），返回3次贝塞尔曲线的坐标（终点、控制点1、控制点2）
     * @param relX
     * @param relY
     * @return
     */
    float[] radian(float relX, float relY);
}

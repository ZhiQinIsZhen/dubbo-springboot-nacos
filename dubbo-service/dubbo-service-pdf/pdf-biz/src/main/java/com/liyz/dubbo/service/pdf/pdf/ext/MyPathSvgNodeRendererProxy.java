package com.liyz.dubbo.service.pdf.pdf.ext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/30 17:50
 * 代理PathSvgNodeRenderer类
 */
public class MyPathSvgNodeRendererProxy implements MethodInterceptor {

    /**
     * MoveTo: M, m
     * LineTo: L, l, H, h, V, v
     * Cubic Bezier Curve: C, c, S, s
     * Quadratic Bezier Curve: Q, q, T, t
     * Elliptical Arc Curve: A, a
     * ClosePath: Z, z
     */
    private static String[] filterStrs = {
            "M", "m",
            "L", "l", "H", "h", "V", "v",
            "C", "c", "S", "s", "Q", "q", "T","t",
            "A","a","Z","z"
    };
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if("separateDecimalPoints".equals(method.getName())){
            processSeparateDecimalPointsParams(objects);
        }
        Object r = methodProxy.invokeSuper(o, objects);
        return r;
    }

    /**
     * 解决iText解析Path的d属性中指数表示法浮点数异常的问题
     * @param objects
     */
    private void processSeparateDecimalPointsParams(Object[] objects){
        if(objects == null || objects.length < 1){
            return;
        }
        String input = (String) objects[0];
        if(StringUtils.startsWithAny(input, filterStrs)){
            input = input.replaceAll("E", "e");
            objects[0] = input;
        }
    }
}

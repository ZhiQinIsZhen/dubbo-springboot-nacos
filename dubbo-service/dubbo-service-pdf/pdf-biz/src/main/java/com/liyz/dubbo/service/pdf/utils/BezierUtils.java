package com.liyz.dubbo.service.pdf.utils;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/18 17:20
 */
public class BezierUtils {
    public static void main(String[] args) {
        float dushu = 10;
        float a=0.27f;
        System.out.println(Math.tan(Math.toRadians(11)) * a);
        System.out.println(Math.tan(Math.toRadians(10)) * a);
        System.out.println(Math.tan(Math.toRadians(9)) * a);
        System.out.println(Math.tan(Math.toRadians(8)) * a);
    }

    /**
     * 计算三次贝塞尔曲线坐标（相对坐标c，注意和绝对坐标C的区别）
     * 适合X轴固定偏移量的曲线
     * degree=45，ratio=1 会变成都是直线
     * degree=0，ratio=0.5 是标准的平滑贝塞尔曲线
     * @see BezierUtils#calCubicPointRelWithY(double, double, double, double)
     * @param dx 终点X偏移量，是上一条曲线命令的终点控制点沿x轴的偏移量
     * @param dy 终点Y偏移量，是上一条曲线命令的终点控制点沿y轴的偏移量
     * @param degree 曲线和X轴的角度
     * @param factor 控制点偏移量计算系数
     * @return
     */
    public static float[] calCubicPointRelWithX(double dx, double dy, double degree, double factor){
        if(dx < 0.1d){
            throw new IllegalArgumentException("X轴偏移量太小啦，换个方法吧");
        }
        double radians = Math.toRadians(degree);
        // X轴的偏移量
        double offsetx = dx * factor;
        // 通过三角函数计算Y轴偏移量
        double offsety = Math.tan(radians) * offsetx;
        // 需要换算成实际Y轴偏移量
        offsety = offsety/dx * dy;
        return new float[]{
                (float) dx, (float) dy,
                (float) offsetx, (float) offsety,
                (float) (dx - offsetx), (float) (dy - offsety)
        };
    }

    /**
     * 适合Y轴固定偏移量的曲线
     * @see BezierUtils#calCubicPointRelWithX(double, double, double, double)
     */
    public static float[] calCubicPointRelWithY(double dx, double dy, double degree, double factor){
        // todo 未测试过
        if(dy < 0.1d){
            throw new IllegalArgumentException("Y轴偏移量太小啦，换个方法吧");
        }
        double radians = Math.toRadians(degree);
        // Y轴的偏移量
        double offsety = dy * factor;
        // 通过三角函数计算X轴偏移量
        double offsetx = offsety / Math.tan(radians);
        // 需要换算成实际X轴偏移量
        offsetx = offsetx/dy * dx;
        return new float[]{
                (float) dx, (float) dy,
                (float) offsetx, (float) offsety,
                (float) (dx - offsetx), (float) (dy - offsety)
        };
    }

//    /**
//     * 计算数据的坐标
//     * @param maxAxisValue
//     * @param datas
//     * @return
//     */
//    private float[][] calXYCoordinate(double maxAxisValue,
//                                      double[] datas,
//                                      SvgDirection direction,
//                                      double firstXY,
//                                      double step) {
//        int len = datas.length;
//        float[][] coordinates = new float[len][2];
//        for (int i = 0; i < len; i++) {
//            float absX = charLeftX + charColOffsetX + i* charStepX;
//            // 求y
//            float absY = (float) (charLeftDownY - datas[i]/ maxAxisValue * charHeight);
//            if(direction == SvgDirection.X){
//                float absX = firstXY + i* step;
//                // 求y
//                float absY = (float) (charLeftDownY - datas[i]/ maxAxisValue * charHeight);
//            }else{
//                float absX = charLeftX + charColOffsetX + i* charStepX;
//                // 求y
//                float absY = (float) (charLeftDownY - datas[i]/ maxAxisValue * charHeight);
//            }
//            coordinates[i] = new float[]{absX, absY};
//        }
//        return coordinates;
//    }
}

package com.liyz.dubbo.service.pdf.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;


/**
 * description: TODO
 * author: huanglb
 * date 2023/2/28 10:33
 */
@UtilityClass
@Slf4j
public class FinanceTypeConvertUtils {


    // 财报类别 1：年报；2：中报 ；3：一季度；4:三季度
    private static Function<Integer, String> financeTypeConvert = type -> {
        if (null == type) {
            return "未知";
        }
        return 1 == type ? "年报" : 2 == type ? "中报" : 3 == type ? "一季度" : "三季度";
    };

    /**
     * @param type
     * @return
     */
    public static String Type2Chinese(Integer type) {
        return financeTypeConvert.apply(type);
    }
}

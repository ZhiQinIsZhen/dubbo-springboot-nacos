package com.liyz.dubbo.common.apollo.util;

import org.springframework.core.env.Environment;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:19
 */
public class ProductModeUtil {

    public static Environment environment;

    public static boolean isOnlineMode() {
        String productMode = environment.getProperty("product.mode", "online");
        return productMode.equalsIgnoreCase("online");
    }
}

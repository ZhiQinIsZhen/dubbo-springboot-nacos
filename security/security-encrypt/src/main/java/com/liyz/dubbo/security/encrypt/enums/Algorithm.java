package com.liyz.dubbo.security.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:算法枚举
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 15:20
 */
@AllArgsConstructor
public enum Algorithm {

    AES_ECB("AES/ECB/PKCS5Padding"),
    AES_CBC("AES/CBC/PKCS5Padding"),
    RSA("RSA"),
    ;

    @Getter
    private String code;

    /**
     * 根据code获取对应的算法
     *
     * @param code
     * @return
     */
    public static Algorithm getByCode(String code) {
        for (Algorithm algorithm : Algorithm.values()) {
            if (algorithm.code.equals(code)) {
                return algorithm;
            }
        }
        return null;
    }
}

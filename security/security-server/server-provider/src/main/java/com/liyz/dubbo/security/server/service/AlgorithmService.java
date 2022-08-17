package com.liyz.dubbo.security.server.service;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 15:28
 */
public interface AlgorithmService {

    /**
     * 加密
     *
     * @param content 内容
     * @param keys 秘钥
     * @return
     */
    String encrypt(String content, String... keys);

    /**
     * 解密
     *
     * @param content 内容
     * @param keys 秘钥
     * @return
     */
    String decrypt(String content, String... keys);

    /**
     * 创建秘钥
     *
     * @param keySize
     * @return
     */
    List<String> createKeys(int keySize);
}

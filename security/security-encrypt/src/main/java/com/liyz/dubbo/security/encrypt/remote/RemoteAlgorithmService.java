package com.liyz.dubbo.security.encrypt.remote;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 16:26
 */
public interface RemoteAlgorithmService {

    /**
     * 加密
     *
     * @param content 内容
     * @param userId 用户id
     * @return
     */
    String encrypt(String content, Long userId);

    /**
     * 解密
     *
     * @param content 内容
     * @param userId 用户id
     * @return
     */
    String decrypt(String content, Long userId);

    /**
     * 创建非对称加密密码对
     *
     * @param keySize
     * @return
     */
    List<String> createKeys(int keySize);
}

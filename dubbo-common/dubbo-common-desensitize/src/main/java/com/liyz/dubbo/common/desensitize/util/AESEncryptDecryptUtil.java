package com.liyz.dubbo.common.desensitize.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/2 15:27
 */
@Slf4j
@UtilityClass
@Deprecated
public class AESEncryptDecryptUtil {

    private static final String DEFAULT_AES_KEY = "rZxl3zy!rZxl3zy!";
    private static final String DEFAULT_AES_PARAMETER = "rZxl3zy!rZxl3zy!";
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * CBC默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    /**
     * 加密(使用默认key)
     *
     * @param content
     * @return
     */
    public String encrypt(String content) {
        return encrypt(content, DEFAULT_AES_KEY);
    }

    /**
     * 加密(使用自定义key)
     *
     * @param content 明文
     * @param key 加密key
     * @return 密文
     */
    public String encrypt(String content, String key) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
            log.error("AES encrypt error ", ex);
        }
        return null;
    }

    /**
     * 解密(使用默认key)
     *
     * @param content 密文
     * @return 明文
     */
    public String decrypt(String content) {
        return decrypt(content, DEFAULT_AES_KEY);
    }

    /**
     * 解密(使用自定义key)
     *
     * @param content 密文
     * @param key 加密key
     * @return 明文
     */
    public String decrypt(String content, String key) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM));
            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("AES decrypt error ", ex);
        }
        return null;
    }

    /**
     * CBC AES加密操作(默认参数)
     *
     * @param content 明文
     * @return 密文
     */
    public String encryptCBC(String content) {
        return encryptCBC(content, DEFAULT_AES_KEY, DEFAULT_AES_PARAMETER);
    }

    /**
     * CBC AES加密操作(自定义参数)
     *
     * @param content 明文
     * @param key 加密key
     * @param ivParameter 加密参数
     * @return 密文
     */
    public String encryptCBC(String content, String key, String ivParameter) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_CBC);

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM), ivParameterSpec);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
            log.error("CBC encrypt error ", ex);
        }
        return null;
    }

    /**
     * CBC AES 解密操作(默认参数)
     *
     * @param content 密文
     * @return 明文
     */
    public String decryptCBC(String content) {
        return decryptCBC(content, DEFAULT_AES_KEY, DEFAULT_AES_PARAMETER);
    }

    /**
     * CBC AES 解密操作(自定义参数)
     *
     * @param content 密文
     * @param key 加密key
     * @param ivParameter 加密参数
     * @return 明文
     */
    public String decryptCBC(String content, String key, String ivParameter) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_CBC);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), KEY_ALGORITHM), ivParameterSpec);
            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("CBC decrypt error ", ex);
        }
        return null;
    }
}

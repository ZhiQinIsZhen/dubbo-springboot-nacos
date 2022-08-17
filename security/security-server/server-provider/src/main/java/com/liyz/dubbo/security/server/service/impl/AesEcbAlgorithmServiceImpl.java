package com.liyz.dubbo.security.server.service.impl;

import com.liyz.dubbo.security.encrypt.enums.Algorithm;
import com.liyz.dubbo.security.server.service.abs.AbstractAlgorithmService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 15:44
 */
@Service
public class AesEcbAlgorithmServiceImpl extends AbstractAlgorithmService {

    private static final String KEY_ALGORITHM = "AES";

    @Override
    protected Algorithm getAlgorithm() {
        return Algorithm.AES_ECB;
    }

    @Override
    protected String doEncrypt(String content, String... keys) throws Exception {
        Cipher cipher = Cipher.getInstance(getAlgorithm().getCode());
        if (keys.length > 1) {
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keys[0].getBytes(), KEY_ALGORITHM), new IvParameterSpec(keys[1].getBytes()));
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keys[0].getBytes(), KEY_ALGORITHM));
        }
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    protected String doDecrypt(String content, String... keys) throws Exception {
        Cipher cipher = Cipher.getInstance(getAlgorithm().getCode());
        if (keys.length > 1) {
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keys[0].getBytes(), KEY_ALGORITHM), new IvParameterSpec(keys[1].getBytes()));
        } else {
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keys[0].getBytes(), KEY_ALGORITHM));
        }
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)), StandardCharsets.UTF_8);
    }

    @Override
    protected List<String> doCreateKeys(int keySize) throws Exception {
        return null;
    }
}

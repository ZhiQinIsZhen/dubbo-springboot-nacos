package com.liyz.dubbo.security.server.service.impl;

import com.google.common.collect.Lists;
import com.liyz.dubbo.security.encrypt.enums.Algorithm;
import com.liyz.dubbo.security.server.service.abs.AbstractAlgorithmService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
public class RsaAlgorithmServiceImpl extends AbstractAlgorithmService {

    @Override
    protected Algorithm getAlgorithm() {
        return Algorithm.RSA;
    }

    @Override
    protected String doEncrypt(String content, String... keys) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(getAlgorithm().getCode());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(keys[0]));
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(getAlgorithm().getCode());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    protected String doDecrypt(String content, String... keys) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(getAlgorithm().getCode());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keys[0]));
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(getAlgorithm().getCode());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)), StandardCharsets.UTF_8);
    }

    @Override
    protected List<String> doCreateKeys(int keySize) throws Exception {
        List<String> keys = Lists.newArrayList();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(getAlgorithm().getCode());
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        keys.add(Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        keys.add(Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
        return keys;
    }

}

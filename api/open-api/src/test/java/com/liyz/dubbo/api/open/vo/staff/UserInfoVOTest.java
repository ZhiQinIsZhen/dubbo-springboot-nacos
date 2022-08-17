package com.liyz.dubbo.api.open.vo.staff;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 11:18
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserInfoVO.class})
public class UserInfoVOTest {

    @Test
    public void test() throws Exception {
        String data = "你就是个小垃圾";
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        log.info("public key : {}", Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        log.info("private key : {}", Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));

        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        String enStr = Base64.getEncoder().encodeToString(result);
        log.info("result:{}", enStr);

        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] res = cipher1.doFinal(Base64.getDecoder().decode(enStr));
        log.info("解密后:{}", new String(res));
    }

    @Test
    public void encrypt() throws Exception {
        String data = "你就是个小垃圾";
        String publicKeys = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAkBLqO6SpdK6UWCPx5WKr6GgMw7qWMKqyspylOPhF11uKFf3kidAKyKEmqgGJzw8aghhxkuc0S8mgPUb2pYefh7RPVCFZLPaKE9XHoqQbUsxlVxJAcWtMEXUtplS+/7BDx8IIHARPUXA0/ysZ2368rZQNWTsIyICg0NeERZAOeQIDAQAB";
        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeys));
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        String enStr = Base64.getEncoder().encodeToString(result);
        log.info("result:{}", enStr);
    }

    @Test
    public void decrypt() throws Exception {
        String data = "a4KzgusO6YlvT6jI98JjE317iehJ3/55MGPJoWev4ra4bLY6to20Pe+wbBCzklFATtmwWBvPq/dT+goPERHts0VOGS55k4dpUBUQQfYpqXrl3rB5NlXT72re1rmqBUphx5q76MO4KxmtPTz8STILu6e7M0dnbeWixhkTwyEQ6uI=";
        String privateKeys = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAICQEuo7pKl0rpRYI/HlYqvoaAzDupYwqrKynKU4+EXXW4oV/eSJ0ArIoSaqAYnPDxqCGHGS5zRLyaA9Rvalh5+HtE9UIVks9ooT1ceipBtSzGVXEkBxa0wRdS2mVL7/sEPHwggcBE9RcDT/KxnbfrytlA1ZOwjIgKDQ14RFkA55AgMBAAECgYBazWeOqLGUPiVQATDiGPGoGW3kFhojGhx2OlEJIUO5kHHHBeFdGTknZKztcgXEH8q7HkfFb8x800qFMsJ8Uk2HC45zj3uhC809rxMGSiAnhUa9WO7gjc2Wwi/r2Tfi5cEFKsXryO7cpO47PSjvZ0dy2HgPcUsxei/Skn3F8LpeQQJBANbjX/IOtPHe+zSynvo+uVLFWgW9PawBmK5EmDrfhL0DsTjc6dtC0WT48OYfVVx64c/dTq3+KXjK8eLrib5NaLcCQQCZKLi1QeRYNutdDc2eLyEyH0abgdKQEGO/iSwnvioUdgCpjrdYls8i06XneAJZXoAqMmjISr/00ucbffCJjzJPAkEAurgjvH7O94Qbn3S98w/hlGxrhqUjG3zfoD12UWG7Q0ocCh7bDWyH6kbte+gqEgusfyP7o/A/NPiTKewkajl1DQJALFUnMgBCsm4jLqpCLFmECFgZPgYUBvNXjCeOimp3D7kgc/QFijUM/A27ZgbM10WNX6l7vw40Bg/OKZ0ItSin7QJBAJX1HsqqyknvT/qTknqKLA0tavvBNtmYEe05hdreh7cDal2frMDE8QgwF5WRXFNzGpbfU4DUBkO4j1+PZsWdSek=";
        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeys));
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] res = cipher.doFinal(Base64.getDecoder().decode(data));
        log.info("解密后:{}", new String(res));
    }
}
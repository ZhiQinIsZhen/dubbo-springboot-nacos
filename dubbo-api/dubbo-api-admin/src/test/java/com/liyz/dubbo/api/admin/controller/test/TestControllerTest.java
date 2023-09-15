package com.liyz.dubbo.api.admin.controller.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.dfa.SensitiveUtil;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.liyz.dubbo.api.admin.vo.staff.StaffInfoVO;
import com.liyz.dubbo.api.admin.vo.test.TestVO;
import com.liyz.dubbo.common.desensitize.util.CryptoUtil;
import com.liyz.dubbo.common.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/11 15:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestUtil.class})
public class TestControllerTest {

    @SneakyThrows
    @Test
    public void test() {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        objectMapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
        TestVO testVO = new TestVO();
        testVO.setName("2222");
        log.info("data : {}", objectMapper.writeValueAsString(testVO));
    }

    @Test
    @SneakyThrows
    public void test1() {
        List<String> words = Lists.newArrayList("大", "大土豆", "土豆", "刚出锅", "出锅");
        SensitiveUtil.init(words);
        String text = SensitiveUtil.sensitiveFilter("我有一颗大土豆，刚出锅的");
        log.info("data : {}", text);

        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        objectMapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);

        TestVO testVO = new TestVO();
        testVO.setEmail("我有一颗大土豆");
        testVO.setMobile("刚出锅的");
        testVO.setRealName("我有一颗大土豆，刚出锅的");
        log.info("data : {}", objectMapper.writeValueAsString(SensitiveUtil.sensitiveFilter(testVO, true, null)));
    }

    @Test
    public void test3() {
        Long time = Long.parseLong("1505232000000");
        Date date = DateUtil.date(time).toJdkDate();
        log.info("date : {}", date);
        date =  new Date(time);
        log.info("date : {}", date);
    }

    @Test
    public void test4() {
        String context = "他就是青藏高原";
        log.info("明文: {}", context);
        String key = "12345678";
        String iv = "rZxl3zy!";

        String text = CryptoUtil.Symmetric.encryptDES(context, key);
        log.info("密文: {}", text);

        String text1 = CryptoUtil.Symmetric.decryptDES(text, key);
        log.info("明文: {}", text1);

        text = CryptoUtil.Symmetric.encryptDES(context, key, iv);
        log.info("密文: {}", text);

        text1 = CryptoUtil.Symmetric.decryptDES(text, key, iv);
        log.info("明文: {}", text1);
    }

    @Test
    public void test5() {
        String context = "他就是青藏高原";
        log.info("明文: {}", context);
        String key = "123456781234567812345678";
        String iv = "rZxl3zy!rZxl3zy!";

        String text = CryptoUtil.Symmetric.encryptAES(context, key);
        log.info("密文: {}", text);

        String text1 = CryptoUtil.Symmetric.decryptAES(text, key);
        log.info("明文: {}", text1);

        text = CryptoUtil.Symmetric.encryptAES(context, key, iv);
        log.info("密文: {}", text);

        text1 = CryptoUtil.Symmetric.decryptAES(text, key, iv);
        log.info("明文: {}", text1);
    }

    @Test
    public void test6() {
        Pair<String, String> pair = CryptoUtil.Asymmetric.generateKeyPair();
        log.info("pair : {}", pair);
    }

    @Test
    public void test7() {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNZ/NjsSEOoD6T7qOuDQzkAjMtV62ufILzlLYFrygs1KHfbN2RnTU+pHzES1W91MqrSt6zLQrFDv9WDI0pKAFdFkAGi+o34jmVhg1TamSDbpD2HwMyCZOs4p0D4nILep1PIx4PISl8sRXy//vK/JRuvKhlpmVMz5EXybf/inG6TwIDAQAB";
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAI1n82OxIQ6gPpPuo64NDOQCMy1Xra58gvOUtgWvKCzUod9s3ZGdNT6kfMRLVb3UyqtK3rMtCsUO/1YMjSkoAV0WQAaL6jfiOZWGDVNqZINukPYfAzIJk6zinQPicgt6nU8jHg8hKXyxFfL/+8r8lG68qGWmZUzPkRfJt/+KcbpPAgMBAAECgYEAgURhDGaVqLA25LdIbElD2oBkVnQLszHh6FivGX0pBeqP3BbCamZDkXN/F1cjF7maWILGGRf4+3IdW4V32gLXUuwtIoEqs1q+ey0i1t8LeIF8sLmUl+pHDGEoBszN7jYOjgB8drAZ9/o1gHzHRuxRlS/lNu6wMC4lpJS7q7E3HrECQQDI72lgptVr7DNW8q/r9iZmI9ILs9zO/3n9fiev6h+HGvGchaqdktbMJfwbDowKxJWijo1oZfq0PTBTq9TchsX5AkEAtChHNBGqfYHcU0Dsu9HlfLt/lb8Ma/DY+i9KP9V+ZmPVn2cBJsNfjAtC/bGvjGyVgD/sE/AJaJ916J9wM570hwJBAJsuex3P+wfMdaHy7/a2NdWwWGn9UMz09YvWwGPV1t+K6YpOmQP1AbrMozVqOLGgPUg7++3ixIoqr4YPMOfXllECQQCQgZ5bjbALbBamUPWS0hIZsuvr2YrFKjNgun38Jet1ha7cZWrvBNenJYFerR/6qz5f2OYsN+XUHdQd+4WsYlwRAkAQr88j/IfjSVY2Pz/MTq2QqfzqKay5UPqknAt7ADFDPzd59+wMPhczobNwYAarHbknaIDUtkhPTjZZPQFPRJ4C";

        String context = "他就是青藏高原";
        log.info("明文: {}", context);

        String text = CryptoUtil.Asymmetric.encryptPublicRSA(context, publicKey);
        log.info("公钥加密密文: {}", text);

        String text1 = CryptoUtil.Asymmetric.decryptPrivateRSA(text, privateKey);
        log.info("私钥解密明文: {}", text1);

        String text2 = CryptoUtil.Asymmetric.encryptPrivateRSA(context, privateKey);
        log.info("私钥加密密文: {}", text2);

        String text3 = CryptoUtil.Asymmetric.decryptPublicRSA(text2, publicKey);
        log.info("公钥解密明文: {}", text3);
    }
}
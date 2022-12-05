package com.liyz.dubbo.api.open.vo.staff;

import cn.hutool.core.util.PinyinUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liyz.dubbo.api.open.vo.bigdata.RaProjectSocialStaffResponse;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Test
    public void test1() {
        System.out.println(Integer.parseInt("111111", 2));
        System.out.println(Long.parseLong("1111111", 32));

        String s[] = {"abc", "bcd", "acd", "bba", "bacd"};
        Arrays.sort(s);
        System.out.println(JsonMapperUtil.toJSONString(s));
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        List<String> list1 = Lists.newArrayList("1", "2");
        List<String> list2 = Lists.newArrayList("3", "4");
        list.addAll(list1);
        list.addAll(list2);
        System.out.println(JsonMapperUtil.toJSONString(list));
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        List<String> list1 = Lists.newArrayList("1", "2");
        List<String> list2 = Lists.newArrayList("3", "4");
        list.addAll(list1);
        list.addAll(list2);
        System.out.println(JsonMapperUtil.toJSONString(list));
    }

    @Test
    public void test4() {
        List<String> list = Lists.newArrayList("3", "2", "4", "1");
        System.out.println(JsonMapperUtil.toJSONString(list.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList())));
    }

    /**
     *
     */
    @Test
    public void test5() {
        List<String> list = Lists.newArrayList("中山市复和房地产有限公司", "中山市复祥房地产有限公司", "山西万厦房地产开发有限公司", "中山市复中房地产有限公司",
                "中山市复厦房地产有限公司","山西祥欣物业管理有限公司","山西世家基业房地产开发有限公司","太原市万新商贸有限公司","山西日月峰建筑工程有限公司");
        List<String> list1 = list.stream().map(item -> PinyinUtil.getAllFirstLetter(item)).sorted().collect(Collectors.toList());
        Map<String, String> map = list.stream().collect(Collectors.toMap(String -> PinyinUtil.getAllFirstLetter(String), Function.identity(), (o, n) -> n));
        List<String> pyList = map.keySet().stream().sorted().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list1));

    }

    @Test
    public void test6() {
        List<UserInfoVO> list = Lists.newArrayList();
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(1L);
        list.add(userInfoVO);
        List<UserInfoVO> list1 = Lists.newArrayList();
        UserInfoVO userInfoVO1 = new UserInfoVO();
        userInfoVO1.setUserId(2L);
        list1.add(userInfoVO1);
        List<UserInfoVO> list2 = Lists.newArrayList();
        list2.addAll(list);
        list2.addAll(list1);
        for (UserInfoVO item : list2) {
            item.setUserName("111111");
        }
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list1));
    }

    @Test
    public void test7() {
        Function<Integer, Integer> function1 = integer -> integer * 10;
        //将数减5
        Function<Integer, Integer> function2 = integer -> integer - 5;
        System.out.println(function1.compose(function2).apply(10));
        System.out.println(function1.andThen(function2).apply(10));
        System.out.println(function1.apply(1));
        System.out.println(function2.apply(1));
    }

    @Test
    public void test8() {
        int year = 2023;
        Integer socialStaffNum = null;
        List<RaProjectSocialStaffResponse> list = Lists.newArrayList();
        RaProjectSocialStaffResponse response1 = new RaProjectSocialStaffResponse();
        response1.setYear(2015);
        response1.setSocialStaffNum(10);
        list.add(response1);

        response1 = new RaProjectSocialStaffResponse();
        response1.setYear(2019);
        response1.setSocialStaffNum(15);
        list.add(response1);
        list = list.stream().sorted((Comparator.comparingInt(RaProjectSocialStaffResponse::getYear).reversed())).collect(Collectors.toList());

        //补足中间空余年份
        if (CollectionUtils.isEmpty(list)) {
            if (Objects.nonNull(socialStaffNum)) {
                RaProjectSocialStaffResponse response = new RaProjectSocialStaffResponse();
                response.setYear(year);
                response.setSocialStaffNum(socialStaffNum);
                list.add(response);
            }
        } else {
            if (list.get(0).getYear().compareTo(year) == 0 && Objects.nonNull(socialStaffNum)) {
                list.get(0).setSocialStaffNum(socialStaffNum);
            } else if (list.get(0).getYear().compareTo(year) < 0) {
                RaProjectSocialStaffResponse response = new RaProjectSocialStaffResponse();
                response.setYear(year);
                response.setSocialStaffNum(Objects.isNull(socialStaffNum) ? list.get(0).getSocialStaffNum() : socialStaffNum);
                list.add(0, response);
            }
            List<RaProjectSocialStaffResponse> addList = Lists.newArrayList();
            Integer lastNum = list.get(list.size() - 1).getSocialStaffNum();
            Integer lastYear = list.get(list.size() - 1).getYear();
            for (int i = list.size() - 2; i >= 0; i--) {
                RaProjectSocialStaffResponse item = list.get(i);
                if (lastYear + 1 < item.getYear()) {
                    for (int j =  item.getYear() - lastYear - 1; j > 0; j--) {
                        RaProjectSocialStaffResponse response = new RaProjectSocialStaffResponse();
                        response.setYear(lastYear + j);
                        response.setSocialStaffNum(lastNum);
                        addList.add(response);
                    }
                }
                lastYear = item.getYear();
                lastNum = item.getSocialStaffNum();
            }
            if (CollectionUtils.isNotEmpty(addList)) {
                list.addAll(addList);
                list = list.stream().sorted((Comparator.comparingInt(RaProjectSocialStaffResponse::getYear).reversed())).collect(Collectors.toList());
            }
        }
        log.info("测试数据 : {}", JsonMapperUtil.toJSONString(list));
    }

    @Test
    public void test9() {
        String a = "a";
        String b = "c";
        log.info("blank:{}", StringUtils.isNoneBlank(a, b));
    }
}
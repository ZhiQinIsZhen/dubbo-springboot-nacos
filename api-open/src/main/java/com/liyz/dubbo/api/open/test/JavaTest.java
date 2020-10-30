package com.liyz.dubbo.api.open.test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/9/15 17:43
 */
public class JavaTest {

    public static void main(String[] args) {
        String str = "http://wenshu.court.gov.cn/content/content?DocID=e18b6e7b-ab28-4f56-be5b-a9fc0162c9f2&KeyWord=连云港市巨生实业有限公司";
        String url = "http://wenshu.court.gov.cn/website/wenshu/181107ANFZ0BXSK4/index.html?docId=c3df3edb55304fcd8888ac2800a7f494";
        Pattern pattern = Pattern.compile("(?<=DocID=).*?(?=(&|$))|(?<=docId=).*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("args = " + matcher.group());
        }
        matcher = pattern.matcher(url);
        if (matcher.find()) {
            System.out.println("args = " + matcher.group());
        }
    }
}

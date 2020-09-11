package com.liyz.dubbo.api.open.test;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/27 17:05
 */
public class Test {

    public static void main(String[] args) {
        String s = "IIIVXCDII";
        int x  = 123464321;
//        System.out.println(romanToInt(s));
        test();
    }

    public static String longestCommonPrefix(String[] strs) {
            String s = "";
            if (strs==null||strs.length==0)
                return s;
            for (int i=0,j=strs[0].length();i<j;i++) {
                s = strs[0].substring(0, i+1);
                for (String str : strs) {
                    if (!str.startsWith(s)) {
                        return s.substring(0, i);
                    }
                }
            }
            return s;
    }

    private static int reverse(int x) {
        int b = Math.abs(x);
        int c = 0;
        while (b/10 > 0 || b%10 > 0) {
            if (c > Integer.MAX_VALUE/10 || (c == Integer.MAX_VALUE/10 && b%10 > 7))
                return 0;
            if (c < Integer.MIN_VALUE/10 || (c == Integer.MIN_VALUE/10 && b%10 <-8))
                return 0;
            c = c * 10 + b%10;
            b = b/10;
        }
        if (x<0) {
            return Long.valueOf(c*-1).intValue();
        }
        return Long.valueOf(c).intValue();
    }

    private static int maxLength(String s) {
        if (s.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0, j = s.length(); i < j; i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i-left+1);
        }
        return max;
    }

    private static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        String a = String.valueOf(x);
        int length = a.length();
        if (length == 1)
            return true;
        for (int i = 0, j =length/2;i<j;i++) {
            if (Integer.valueOf(a.charAt(i)) != Integer.valueOf(a.charAt(length -1-i)))
                return false;
        }
        return true;
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int left = 0;
        int total = 0;
        for (int i = 0,j=s.length();i<j;i++) {
            if (left == 0) {
                left = map.get(s.charAt(i));
                continue;
            }
            if (left >= map.get(s.charAt(i))) {
                total = total + map.get(s.charAt(i-1));
                left = map.get(s.charAt(i));
            } else {
                total = total + map.get(s.charAt(i)) - map.get(s.charAt(i-1));
                left = 0;
            }
        }
        return total+left;
    }

    @SneakyThrows
    private static void test()  {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getId() + "开始等待。。。。。。。。。。。。。。");
                    countDownLatch.await();
                    System.out.println("打印数值 " + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        Thread.sleep(5000);
        countDownLatch.countDown();
    }
}

package com.liyz.dubbo.api.test.algorithm.string;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/13 16:46
 */
public class Kmp {

    public static void main(String[] args) {
        String str1 = "ABCDEFG";
        String strPattern = "CD";
        int[] next = new int[strPattern.length()];
        getNext(strPattern.toCharArray(), next);
        int i  = search(str1.toCharArray(), strPattern.toCharArray(), next);
        System.out.println(Arrays.toString(next));
        System.out.println(i);
        System.out.println(str1.indexOf(strPattern));
    }

    public static int search(char[] str, char[] pattern, int[] next) {
        int i = 0, j = 0;
        while (i < str.length && j < pattern.length) {
            if (j == -1 || str[i] == pattern[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == pattern.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void getNext(char[] pattern, int[] next) {
        next[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length) {
            if (j == -1) {
                i++;
                j++;
            } else if (pattern[i] == pattern[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
}

package com.liyz.dubbo.api.test.algorithm.sort;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/13 10:51
 */
public class TwoArraySort {

    public static void main(String[] args) {
        int[] num1 = {1, 3, 5, 7, 9};
        int[] num2 = {2, 4, 6, 8};
        int[] num3 = mergeSort(num1, num2);
        System.out.println(num3);
    }

    public static int[] mergeSort(int[] num1, int[] num2) {
        int num1Length = num1.length;
        int num2Length = num2.length;
        int[] numCopy = new int[num1Length + num2Length];
        int p1 = 0, p2 = 0, p = 0;
        while (p1 < num1Length && p2 < num2Length) {
            numCopy[p++] = num1[p1] < num2[p2] ? num1[p1++] : num2[p2++];
        }
        if (p1 < num1Length) {
            System.arraycopy(num1, p1, numCopy, p1 + p2, num1Length + num2Length - p1 - p2);
        } else if (p2 < num2Length) {
            System.arraycopy(num2, p2, numCopy, p1 + p2, num1Length + num2Length - p1 - p2);
        }
        return numCopy;
    }
}

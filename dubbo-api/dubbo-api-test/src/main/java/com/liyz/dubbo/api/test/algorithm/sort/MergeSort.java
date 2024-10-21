package com.liyz.dubbo.api.test.algorithm.sort;

import com.liyz.dubbo.common.util.JsonMapperUtil;

/**
 * Desc:归并排序
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/2 18:30
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] list = {2,1,3,9,7,4,8,5,6};
        System.out.println(JsonMapperUtil.toJSONString(mergeSortAsc(list)));
        System.out.println(JsonMapperUtil.toJSONString(mergeSortDesc(list)));
    }

    public static int[] mergeSortAsc(int[] array) {
        int[] temp = array.clone();
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (temp[j-1] > temp[j]) {
                    swap(temp, j, j-1);
                } else {
                    break;
                }
            }
        }
        return temp;
    }

    public static int[] mergeSortDesc(int[] array) {
        int[] temp = array.clone();
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (temp[j-1] < temp[j]) {
                    swap(temp, j, j-1);
                } else {
                    break;
                }
            }
        }
        return temp;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

package com.liyz.dubbo.api.test.algorithm.Qurry;

import com.liyz.dubbo.api.test.algorithm.sort.MergeSort;
import com.liyz.dubbo.common.util.JsonMapperUtil;

/**
 * Desc:二分法查询
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/2 18:36
 */
public class DichotomyQuery {

    public static void main(String[] args) {
        int[] list = {2,1,3,9,7,4,8,5,6};
        System.out.println(JsonMapperUtil.toJSONString(list));
        int[] sortedList = MergeSort.mergeSortAsc(list);
        System.out.println(JsonMapperUtil.toJSONString(sortedList));
        System.out.println(getIndex(sortedList, 6, 0, list.length - 1));
    }

    private static int getIndex(int[] array, int i, int start, int end) {
        if (array == null) {
            return -1;
        }
        int length = end - start;
        if (length <= 1) {
            for (int j = start; j <= end; j++) {
                if (array[j] == i) {
                    return j;
                }
            }
            return -1;
        }
        if (array[start] > i || array[end] < i) {
            return -1;
        }
        int midIndex = length >>> 1;
        if (array[midIndex+start] == i) {
            return i;
        } else if (array[midIndex+start] > i) {
            return getIndex(array, i, start, midIndex - 1 + start);
        } else {
            return getIndex(array, i, midIndex + start + 1, end);
        }
    }

}

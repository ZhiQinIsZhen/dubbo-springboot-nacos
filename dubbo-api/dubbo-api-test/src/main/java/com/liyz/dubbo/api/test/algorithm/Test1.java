package com.liyz.dubbo.api.test.algorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/7/23 13:18
 */
public class Test1 {


    public static void main(String[] args) {
        int[][] intArr = {{1,1,0,0,0,0},{1,1,1,0,0,0},{0,1,1,0,0,0},{0,1,1,0,0,0},{0,0,1,1,0,0},{0,0,0,1,1,0},{0,0,0,0,1,1}};
        int balanceNum = getBalanceNum(1, 2, intArr);
        System.out.println(balanceNum);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    }

    private static int getBalanceNum(int start,int end,int[][] intArr){
        //获取火车余票
        //定义一个占用票
        int usedNum = 0;
        for (int i = 0; i < intArr.length; i++){
            int tempNum = 0;
            for (int j = 0; j < intArr[i].length; j++){
                //在这个站中间的叠加使用
                if(intArr[i][j]==1 && j>=start-1 && j<=end-1){
                    tempNum ++;
                }
                if(tempNum>=2){
                    usedNum++;
                    break;
                }
                if (j>=end-1)
                    break;
            }
        }
        return 20-usedNum;
    }

}

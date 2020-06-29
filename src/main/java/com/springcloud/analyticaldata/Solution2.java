package com.springcloud.analyticaldata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Solution {

    // 数组个数
    private static int arrLen1 = 5;

    // 数组里的长度(长度越大组合越多)
    private static int arrLen2 = 6;

    // number的范围
    //private static int maxNum = 50;

    // 二维数组集合
    private static int[][] arr = new int[arrLen1][arrLen2];

    // 所有质数
    private static Set<Integer> prime;

    // 返回结果
    private static List<int[]> resultSet = new ArrayList<int[]>();

    // 记录执行数组
    private static int[] result = new int[arrLen1];

    // 上一级位置
    private static int[] index = new int[arrLen1];

    // 质数位置,假设最后一位+1
    private static int primeIndex = arrLen1 + 1;


    private static void permutation(int[][] arr) {
        primeNumber();
        // 处理数组
        recurInt(0, 0);
    }

    private static boolean recurInt(int i, int j) {
        if (j >= arrLen2) {
            // 当第一个数组遍历完结束
            if (i == 0) {
                return false;
            }
            //是否为质数位置
            if (i - 1 == primeIndex) {
                primeIndex = arrLen1 + 1;
            }
            index[i - 1]++;

            return recurInt(i - 1, index[i - 1]);
        }
        int num = arr[i][j];

        //所有质数和合数大于1
        if (num <= 2) {
            return recurInt(i, j + 1);
        }

        // 比前面的数要大
        if (i > 0 && num <= result[i - 1]) {
            return recurInt(i, j + 1);
        }

        // 如果是质数进行下一个
        if (prime.contains(num) && primeIndex < i) {
            return recurInt(i, j + 1);

        }

        result[i] = num;

        if (prime.contains(num)) {
            primeIndex = i;
        }

        //最后一个数组满足所有条件
        if (i == arrLen1 - 1) {
            int[] res = new int[arrLen1];
            for (int k = 0; k < result.length; k++) {
                res[k] = result[k];
            }
            resultSet.add(res);

        }else {
            // 当前i的位置为j
            index[i] = j;
            return recurInt(i + 1, 0);
        }
        return recurInt(i, j + 1);
    }


    /**
     * 所有质数
     */
    private static void primeNumber(){
        prime = new HashSet<Integer>();
        int i, j;
        for (i = 2; i <= 35; i++) {
            for (j = 2; j <= i/2; j++) {
                if (i%j == 0) {
                    break;
                }
            }
            if (j > i/2) {
                prime.add(i);
            }
        }
    }

    /*private static void init(){
        for (int i = 0; i < arrLen1; i++) {
            int[] arr2 = new int[arrLen2];
            // 记录数组已存在的数值
            Set<Integer> intSet = new HashSet<Integer>();
            for (int j = 0; j < arrLen2; j++) {
                Random random = new Random();
                int num = random.nextInt(35);
                // 单个数组不重复数字
                while (intSet.contains(num) || num == 0) {
                    num = random.nextInt(35);
                }
                arr2[j] = num;
                intSet.add(num);
            }
            // 升序
            Arrays.sort(arr2);

            System.out.println();
            for (int j = 0; j < arr2.length; j++) {
                System.out.print(arr2[j] + " ");
            }
            arr[i] = arr2;
        }
    }*/

    /**
     * 打印
     */
    private static void printing() {
        Iterator<int[]> it = resultSet.iterator();
        while (it.hasNext()) {
            int[] arr = it.next();
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // 初始化数组
        //init();

        // 计算结果
        permutation(arr);

        System.out.println();
        System.out.println("*******结果***********");
        // 打印结果
        printing();
    }
}

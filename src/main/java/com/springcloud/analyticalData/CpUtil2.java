package com.springcloud.analyticalData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: ClassName
 * @Create: Date Time
 */
public class CpUtil2 {


    public static void main(String[] args) {

        List<Integer> fristArr = new ArrayList<>();
        fristArr.add(1);fristArr.add(2);/*fristArr.add(3);fristArr.add(4);fristArr.add(5);*/

        List<Integer> secondArr = new ArrayList<>();
        secondArr.add(3);secondArr.add(4);secondArr.add(5);secondArr.add(6);secondArr.add(7);

        List<Integer> thirdArr = new ArrayList<>();
        thirdArr.add(7);thirdArr.add(8);thirdArr.add(9);thirdArr.add(10);thirdArr.add(11);


        List<Integer> num01 = new ArrayList<>();
        for(Integer num : fristArr){

            for(int i=2;i<=num;i++){
                if(num%i==0){
                    break;
                }else{
                    num01.add(num);
                }
            }
            System.out.println(num01);
        }

    }



}

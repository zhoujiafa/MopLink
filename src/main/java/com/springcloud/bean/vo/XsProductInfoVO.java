package com.springcloud.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : XsProdoctInfoVO
 * @Description :
 * @Author : Joe
 * @Date: 2020-04-30 14:02
 */
@Data
public class XsProductInfoVO {


    String BarCode;
    String ItemCode;
    String ItemName;
    List<String> ColorName;
    List<String> Size;
    Double Price;

}

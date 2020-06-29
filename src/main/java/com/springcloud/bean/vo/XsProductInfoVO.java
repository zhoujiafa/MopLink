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
public class XsProdoctInfoVO {


    String BarCode;
    String ItemCode;
    List<String> ItemName;
    String ColorName;
    List<String> Size;
    Double Price;

}

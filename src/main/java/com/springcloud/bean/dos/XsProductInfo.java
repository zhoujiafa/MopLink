package com.springcloud.bean.dos;

import lombok.Data;

/**
 * @ClassName : XsProdoctInfo
 * @Description :
 * @Author : Joe
 * @Date: 2020-04-30 13:57
 */
@Data
public class XsProductInfo {

    String BarCode;
    String ItemCode;
    String ItemName;
    String ColorName;
    String Size;
    Double Price;
}

package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : XsProductInfo
 * @Description :
 * @Author : Joe
 * @Date: 2020-04-30 13:57
 */
@Data
@TableName("XsProductInfo")
public class XsProductInfo {

    String BarCode;
    String ItemCode;
    String ItemName;
    String ColorName;
    String Size;
    Double Price;
}

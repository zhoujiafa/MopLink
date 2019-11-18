package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("needOrderDetail")
public class NeedOrderDetail {
    String docNum;
    String itemName;
    String itemQuantity;
    String quantity;
    String itemCode;
    String itemUnit;
    String remark;
    String unit;
    String price;
    String barCode;
    String lineNum;
    String itemNum;
}

package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("LinesItem")
public class LinesItem {

    private String docNum;
    public String purchasingQuantity ;
    public String itemName ;
    public String unit ;
    public Integer itemQuantity ;
    public Integer quantity ;
    public String specName ;
    public String specCode ;
    public String itemUnit ;
    public String itemCode ;
    public String remark ;
    public String skuBarcode ;
    public Integer allocationQuantity ;
}

package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: ClassName
 * @Create: Date Time
 */
@Data
@TableName
public class DeliverGoodsCount {

   private String skuBarcode;
   private String designNumber;
   private String color;
   private String designName;
   private String size;
   private String count;
   private String priceTag;
   private String outboundPrice;
   private String needOrderNo;
   private String baseDocNum;
   private String unit;
   private String remark;

}
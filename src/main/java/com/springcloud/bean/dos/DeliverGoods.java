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
public class DeliverGoods {

   private String docNum;
   private String skuBarcode;
   private String designNumber;
   private String designName;
   private String color;
   private String size;
   private String priceTag;
   private String outboundPrice;

}

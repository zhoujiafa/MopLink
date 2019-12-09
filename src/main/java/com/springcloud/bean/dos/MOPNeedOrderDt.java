package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("mopNeedOrderDt")
public class MOPNeedOrderDt {

    /** 主键ID */
    private Long iD;
    /** 单号 */
    private String docNum;
    /** 采购数量 */
    private String purchasingQuantity ;
    /** 商品名称 */
    private String itemName ;
    /** 商品基本单位 */
    private String unit ;
    /** 商品当前单位数量 */
    private Integer itemQuantity ;
    /** 商品基本单位数量 */
    private Integer quantity ;
    /** 商品规格名称	 */
    private String specName ;
    /** 规格说明  */
    private String specCode ;
    /** 商品当前单位 */
    private String itemUnit ;
    /** 商品编码 */
    private String itemCode ;
    /** 商品行备注 */
    private String remark ;
    /**  商品sku码 */
    private String skuBarcode ;
    /** 调拨数量 */
    private Integer allocationQuantity ;

}



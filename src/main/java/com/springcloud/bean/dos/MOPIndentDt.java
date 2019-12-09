package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : MOPIndentDt
 * @Description : 订货单子单据信息
 * @Author : Joe
 * @Date: 2019/12/7 9:57
 */
@Data
@TableName("mopIndentDt")
public class MOPIndentDt {

    /** 单号  */
    private String docNum;
    /** 实付单价 */
    private Double realPrice;
    /** 实付总额 */
    private Double realPriceLineTotal;
    /** 商品名称 */
    private String itemName;
    /** 商品当前单位数量 */
    private Double itemQuantity;
    /** 商品基本单位数量 */
    private Double quantity;
    /** 商品编码 */
    private String itemCode;
    /** 商品当前单位 */
    private String itemUnit;
    /** 商品行备注 */
    private String remark;
    /** 商品sku码 */
    private String skuBarcode;
    /** 商品规格名称 */
    private String specName;
    /** 商品基本单位 */
    private Double unit;
    /** 调拨数量 订货价 */
    private Double listPrice;
    /** 调拨数量	商品总额 */
    private Double lineTotal;
    /** 调拨数量	优惠金额 */
    private String promotionMoney;
    /** 运费 */
    private String totalExpressFee;

}

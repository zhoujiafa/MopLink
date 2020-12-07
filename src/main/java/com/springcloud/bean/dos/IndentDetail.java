package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : IndentDetail
 * @Description : 本地订货单子项信息
 * @Author : Joe
 * @Date: 2019/12/7 9:57
 */
@Data
@TableName("indentDetail")
public class IndentDetail {

    /** ID */
    private Long iD;
    /** 单号  */
    private String docNum;
    /** 商品名称 */
    private String itemName;
    /** 商品数量 */
    private Double quantity;
    /** 商品编码 */
    private String itemCode;
    /** 规格编码 */
    private String specCode;
    /** 商品单位 */
    private String itemUnit;
    /** 备注 */
    private String remark;
    /** SKU编码 */
    private String skuBarcode;
    /** 单位 */
    private String unit;
    /** 单价 */
    private Double price;
    /** 小计 */
    private Double subtotal;
    /** 卡号 */
    private String barCode;
    /** 条码 */
    private String lineNum;
    /** 商品编码 */
    private String itemNum;
    /** 实付单价 */
    private Double realPrice;
    /** 商品总数量 */
    private Double needsCount;
    /** 单据实付金额 */
    private Double realTotal;
    /** 调拨数量 订货价 */
    private Double listPrice;
    /** 调拨数量	商品总额 */
    private Double lineTotal;
    /** 调拨数量	优惠金额 */
    private Double promotionMoney;
    /** 运费 */
    private Double totalExpressFee;
    /** 实付总额 */
    private Double realPriceLineTotal;
    /** 商品当前单位数量 */
    private Double itemQuantity;
    /** 商品规格名称 */
    private String specName;

}

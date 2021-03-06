package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("needOrderDetail")
public class NeedOrderDetail {

    /** ID */
    private Long iD;
    /** 单号  */
    private String docNum;
    /** 商品名称 */
    private String itemName;
    /** 商品数量 */
    private String quantity;
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
    private String price;
    /** 小计 */
    private String subtotal;
    /** 卡号 */
    private String barCode;
    /** 条码 */
    private String lineNum;
    /** 商品编码 */
    private String itemNum;
}

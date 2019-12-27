package com.springcloud.bean.dos;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author darren.zhou
 * @Description: 商品物料信息
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
@TableName("goodsDict")
public class GoodsDict  implements Serializable{

    @TableField("iD")
    Long iD;
    @Excel(name = "skuBarcode")
    @TableField("skuBarcode")
    String skuBarcode;

    @TableField("itemCode")
    @Excel(name = "itemCode")
    String itemCode;

    @TableField("specName")
    @Excel(name = "specName")
    String specName;

    @TableField("barCode")
    @Excel(name = "barCode")
    String barCode;

    @Excel(name = "barName")
    @TableField("barName")
    String barName;

    @Excel(name = "price")
    @TableField("price")
    Double price;

}

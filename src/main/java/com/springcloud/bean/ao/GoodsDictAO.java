package com.springcloud.bean.ao;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.springcloud.bean.dos.CompanyDict;
import lombok.Data;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
public class GoodsDictAO {

    @Excel(name = "skuBarcode")
    @TableField("skuBarcode")
    String skuBarcode;

    @TableField("itemCode")
    @Excel(name = "itemCode")
    String itemCode;

    @Excel(name = "colourId")
    String colourId;

    @Excel(name = "sizeId")
    String sizeId;
}

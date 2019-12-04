package com.springcloud.bean.dos;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : CompanyDict
 * @Description : 商品档案信息
 * @Author : Joe
 * @Date: 2019-11-18 10:33
 */
@Data
@TableName("companyDict")
public class CompanyDict implements Serializable {
    @TableField("iD")
    Long iD;
    @Excel(name = "companyCode")
    @TableField("companyCode")
    String companyCode;

    @TableField("companyName")
    @Excel(name = "companyName")
    String companyName;

    @TableField("mopDeptCode")
    @Excel(name = "distributorCode")
    String mopDeptCode;

    @TableField("mopDeptName")
    @Excel(name = "distributorName")
    String mopDeptName;

    /*@TableField("distributorName")
    @Excel(name = "distributorName")
    String distributorName;*/

    @Excel(name = "customerCode")
    @TableField("customerCode")
    String customerCode;

    @Excel(name = "customerName")
    @TableField("customerName")
    String customerName;

    @Excel(name = "xunsoftDeptCode")
    @TableField("xunsoftDeptCode")
    String xunsoftDeptCode;

    //@Excel(name = "distributorName")
    @TableField("xunsoftDeptName")
    String xunsoftDeptName;

}

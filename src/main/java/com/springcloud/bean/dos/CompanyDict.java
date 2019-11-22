package com.springcloud.bean.dos;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    Long iD;
    @Excel(name = "companyCode")
    String companyCode;
    @Excel(name = "companyName")
    String companyName;
    @Excel(name = "mopDeptCode")
    String mopDeptCode;
    @Excel(name = "distributorName")
    String distributorName;
    @Excel(name = "customerCode")
    String customerCode;
    @Excel(name = "customerName")
    String customerName;
    @Excel(name = "xunsoftDeptCode")
    String xunsoftDeptCode;

}

package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : AssociateCompany
 * @Description : 讯商门店信息
 * @Author : Joe
 * @Date: 2019-11-20 16:18
 */
@Data
@TableName("xtm14")
public class CompanyDivision {


    String customerName;
    String customerCode;
    String xunsoftDeptCode;
    String xunsoftDeptName;

}

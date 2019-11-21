package com.springcloud.bean.query;

import lombok.Data;

/**
 * @ClassName : AssociateCompanyQuery
 * @Description :  查询参数实体
 * @Author : Joe
 * @Date: 2019-11-20 15:59
 */
@Data
public class AssociateCompanyQuery {

    String customerName;
    String customerCode;
    String xunsoftDeptCode;
}

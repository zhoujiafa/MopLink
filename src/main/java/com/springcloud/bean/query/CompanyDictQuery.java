package com.springcloud.bean.query;

import com.springcloud.util.BasePageQuery;
import lombok.Data;

/**
 * @ClassName : CompanyDictQuery
 * @Description : 公司商品信息查询实体
 * @Author : Joe
 * @Date: 2019-11-19 11:10
 */
@Data
public class CompanyDictQuery extends BasePageQuery {

    String companyCode;
    String companyName;
    String mopDeptCode;

}

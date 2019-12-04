package com.springcloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.query.AssociateCompanyQuery;

import java.util.List;

/**
* @ClassName : AssociateCompanyService
* @Description :
* @Author : Joe
* @Date: 2019/11/20 16:00
*/

public interface CompanyDivisionService {

    /**
     * 分页
     * @param page
     * @param size
     * @param query
     * @return
     */
    IPage<CompanyDivision> page(int page, int size, AssociateCompanyQuery query);

    /**
     * 列表
     * @param query
     * @return
     */
    List<CompanyDivision> list(AssociateCompanyQuery query);

    /**
     * 个体
     * @param customerCode
     * @return
     */
    CompanyDivision getOneBycustomerCode(String customerCode);

}

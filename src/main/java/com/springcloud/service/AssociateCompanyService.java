package com.springcloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.bean.dos.AssociateCompany;
import com.springcloud.bean.query.AssociateCompanyQuery;

import java.util.List;

/**
* @ClassName : AssociateCompanyService
* @Description :
* @Author : Joe
* @Date: 2019/11/20 16:00
*/

public interface AssociateCompanyService {

    IPage<AssociateCompany> page(int page, int size, AssociateCompanyQuery query);


    List<AssociateCompany> list(AssociateCompanyQuery query);
}

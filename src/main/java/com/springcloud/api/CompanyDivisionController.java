package com.springcloud.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.query.AssociateCompanyQuery;
import com.springcloud.service.CompanyDivisionService;
import com.springcloud.util.QueryResultBean;
import com.springcloud.util.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/associatecompany")
public class CompanyDivisionController {

    @Autowired
    CompanyDivisionService companyDivisionService;


    @ApiOperation(value = "分页查询" , notes = "分页查询")
    @GetMapping("/page")
    public ResultBean page(AssociateCompanyQuery query) {
        IPage<CompanyDivision> ipage = companyDivisionService.page(1, 6, query);
        QueryResultBean queryResultBean = new QueryResultBean();
        queryResultBean.setRecords(ipage.getRecords());
        queryResultBean.setTotal(ipage.getTotal());
        return new ResultBean(100, "成功", queryResultBean);
    }

    @GetMapping("/list")
    public List<CompanyDivision> list(AssociateCompanyQuery query){

        return companyDivisionService.list(query);
    }

    @GetMapping("/one")
    public CompanyDivision getOneBycustomerCode(String customerCode){

        return companyDivisionService.getOneBycustomerCode(customerCode);
    }

}

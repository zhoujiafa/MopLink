package com.springcloud.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.query.AssociateCompanyQuery;
import com.springcloud.mapper.CompanyDivisionMapper;
import com.springcloud.service.CompanyDivisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @ClassName : CompanyDictServiceImpl
* @Description : 公司目录
* @Author : Joe
* @Date: 2019/11/18 10:51
*/
@DS("jzn_wms_test")
@Service
@Transactional(readOnly = true)
public class CompanyDivisionServiceImpl implements CompanyDivisionService {

    @Autowired
    CompanyDivisionMapper companyDivisionMapper;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public IPage<CompanyDivision> page(int page, int size, AssociateCompanyQuery query) {

        Page<CompanyDivision> pageInfo = new Page<>(page, size);
        //是否查询总数据行
        /** pageInfo.setSearchCount(true); */
        QueryWrapper<CompanyDivision> queryWrapper = new QueryWrapper<>();
        //查询参数处理
        queryWrapper.like("customerName",query.getCustomerName());
        //其他的请参照SelectTest 类的      queryWrapper的测试方法，更详细的使用
        IPage<CompanyDivision> iPage = companyDivisionMapper.selectPage(pageInfo, queryWrapper);
        return iPage;
    }

    @Override
    public List<CompanyDivision> list(AssociateCompanyQuery query) {
        CompanyDivision searchBean = new CompanyDivision();
        BeanUtils.copyProperties(query,searchBean);
        return companyDivisionMapper.list(searchBean);
    }
    @Override
    public CompanyDivision getOneBycustomerCode(String customerCode) {

        return companyDivisionMapper.getOneByCustomerCode(customerCode);
    }


}

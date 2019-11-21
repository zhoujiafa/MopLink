package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.bean.dos.AssociateCompany;
import com.springcloud.bean.query.AssociateCompanyQuery;
import com.springcloud.mapper.AssociateCompanyMapper;
import com.springcloud.service.AssociateCompanyService;
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

@Service
@Transactional(readOnly = true)
public class AssociateCompanyServiceImpl implements AssociateCompanyService {

    @Autowired
    AssociateCompanyMapper associateCompanyMapper;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public IPage<AssociateCompany> page(int page, int size, AssociateCompanyQuery query) {

        Page<AssociateCompany> pageInfo = new Page<>(page, size);
        //是否查询总数据行
        /** pageInfo.setSearchCount(true); */
        QueryWrapper<AssociateCompany> queryWrapper = new QueryWrapper<>();
        //查询参数处理
        queryWrapper.like("customerName",query.getCustomerName());
        //其他的请参照SelectTest 类的      queryWrapper的测试方法，更详细的使用
        IPage<AssociateCompany> iPage = associateCompanyMapper.selectPage(pageInfo, queryWrapper);
        return iPage;
    }

    @Override
    public List<AssociateCompany> list(AssociateCompanyQuery query) {
        AssociateCompany searchBean = new AssociateCompany();
        BeanUtils.copyProperties(query,searchBean);
        return associateCompanyMapper.list(searchBean);
    }


}

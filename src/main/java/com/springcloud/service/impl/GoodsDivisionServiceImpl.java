package com.springcloud.service.impl;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.GoodsDictQuery;
import com.springcloud.bean.query.GoodsDivisionQuery;
import com.springcloud.mapper.GoodsDivisionMapper;
import com.springcloud.service.GoodsDivisionService;
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
public class GoodsDivisionServiceImpl implements GoodsDivisionService {

    @Autowired
    GoodsDivisionMapper goodsDictMapper;

    @Override
    public GoodsDivision getOneBybarCode(String barCode) {

        return goodsDictMapper.getOneBybarCode(barCode);
    }

    @Override
    public List<GoodsDivision> list(GoodsDivisionQuery query) {
        GoodsDivision searchBean = new GoodsDivision();
        BeanUtils.copyProperties(query,searchBean);
        return goodsDictMapper.list(searchBean);
    }
}

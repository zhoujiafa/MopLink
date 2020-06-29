package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.bean.dos.XsProductInfo;
import com.springcloud.mapper.XsProdoctInfoMapper;
import com.springcloud.service.XsProdoctInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Service
public class XsProdoctInfoServiceImpl implements XsProdoctInfoService {

    @Autowired
    XsProdoctInfoMapper xsProdoctInfoMapper;


    @Override
    public List<XsProductInfo> list(String sectionNumber) {
        if(StringUtils.isNotBlank(sectionNumber)){
            //查询款号
            //String num = "D011";
            List<XsProductInfo> list01 = xsProdoctInfoMapper.list(sectionNumber);
            return list01;
        }else{
            return null;
        }

    }

    @Override
    public List<String> allItemCode() {

        List<String> list = xsProdoctInfoMapper.allItemCode();
        return list;
    }

    @Override
    public XsProductInfo detailByCode(String sectionNumber) {

        QueryWrapper<XsProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ItemCode",sectionNumber);
        List<XsProductInfo> list = xsProdoctInfoMapper.selectList(queryWrapper);

        List<String> strSize = new ArrayList<>();
        strSize.add(list.stream().distinct())
        for(XsProductInfo xsProductInfo : list){

        }

        return null;
    }
}

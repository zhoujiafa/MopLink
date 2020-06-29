package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.bean.dos.XsProductInfo;
import com.springcloud.bean.vo.XsProductInfoVO;
import com.springcloud.mapper.XsProductInfoMapper;
import com.springcloud.service.XsProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Service
public class XsProductInfoServiceImpl implements XsProductInfoService {

    @Autowired
    XsProductInfoMapper xsProdoctInfoMapper;


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
    public XsProductInfoVO detailByCode(String sectionNumber) {

        XsProductInfoVO vo = new XsProductInfoVO();

        QueryWrapper<XsProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ItemCode",sectionNumber);
        List<XsProductInfo> list = xsProdoctInfoMapper.selectList(queryWrapper);

        List<String> strSize = new ArrayList<>();
        List<String> strColorName = new ArrayList<>();


        for(XsProductInfo xsProductInfo : list){
            strSize.add(xsProductInfo.getSize());
            vo.setItemName(xsProductInfo.getItemName());
            vo.setPrice(xsProductInfo.getPrice());
            vo.setBarCode(xsProductInfo.getBarCode());
            vo.setItemCode(xsProductInfo.getItemCode());
            strColorName.add(xsProductInfo.getColorName());
        }
        removeDuplicate(strSize);
        removeDuplicate(strColorName);
        vo.setSize(strSize);
        vo.setColorName(strColorName);
        System.out.println(vo);
        return vo;
    }

    public static void removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        System.out.println(list);
    }
}

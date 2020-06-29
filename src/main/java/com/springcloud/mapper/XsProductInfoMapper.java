package com.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.XsProductInfo;

import java.util.List;

/**
* @ClassName : XsProdoctInfoMapper
* @Description :
* @Author : Joe
* @Date: 2020-04-30 14:18
*/

public interface XsProdoctInfoMapper extends BaseMapper<XsProductInfo> {

    /**
     * 查询款号信息
     * @param sectionNumber
     * @return
     */
    List<XsProductInfo> list(String sectionNumber);

    /**
     * 查询款号信息
     * @return
     */
    List<String> allItemCode();
}

package com.springcloud.service;

import com.springcloud.bean.dos.XsProductInfo;

import java.util.List;

/**
* @ClassName : XsProdoctInfoService
* @Description :
* @Author : Joe
* @Date: 2020-04-30 13:56
*/

public interface XsProdoctInfoService {

    /**
     * 获取款号价格
     * @param sectionNumber
     * @return
     */
    List<XsProductInfo> list(String sectionNumber);

    /**
     * 获取款号信息
     * @return
     */
    List<String> allItemCode();

    /**
     * 获取款号详情
     * @param sectionNumber
     * @return
     */
    XsProductInfo detailByCode(String sectionNumber);

}

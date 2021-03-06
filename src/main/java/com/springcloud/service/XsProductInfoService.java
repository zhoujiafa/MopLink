package com.springcloud.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.dos.XsProductInfo;
import com.springcloud.bean.vo.XsProductInfoVO;

import java.util.List;

/**
* @ClassName : XsProdoctInfoService
* @Description :
* @Author : Joe
* @Date: 2020-04-30 13:56
*/

@DS("stock")
public interface XsProductInfoService {

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
    XsProductInfoVO detailByCode(String sectionNumber);

}

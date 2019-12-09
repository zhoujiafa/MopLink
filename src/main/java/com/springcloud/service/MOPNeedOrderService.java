package com.springcloud.service;

import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.util.QueryResult;

import java.util.List;

/**
* @ClassName : DataLineService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface MOPNeedOrderService {


    /**
     * 新增数据
     * @param MOPNeedOrder
     * @return
     */
    Boolean insert(MOPNeedOrder MOPNeedOrder);

    List<MOPNeedOrderVO> getdataline(String companyCode, String needOrderNo);

    List<MOPNeedOrderVO> getLocalInfo(String companyCode, String needOrderNo);

    Boolean saveDataLine(MOPNeedOrderVO MOPNeedOrderVO);

    List<MOPNeedOrderVO> list(MOPNeedOrderQuery query);

    QueryResult<MOPNeedOrderVO> page(long page, long size, MOPNeedOrderQuery query);

}

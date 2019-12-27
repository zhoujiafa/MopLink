package com.springcloud.service;

import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.ao.MOPNeedOrderAO;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;

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

    MOPNeedOrderVO getdataline(String companyCode, String needOrderNo);

    ResponseBean2<SaveResult> thirdPartyUse(MOPNeedOrderAO mopNeedOrderAO);

    List<MOPNeedOrderVO> getLocalInfo(String companyCode, String needOrderNo);

    ResponseBean<SaveResult> saveNeedOrder(MOPNeedOrderVO MOPNeedOrderVO);

    List<MOPNeedOrderVO> list(MOPNeedOrderQuery query);

    QueryResult<MOPNeedOrderVO> page(long page, long size, MOPNeedOrderQuery query);

}

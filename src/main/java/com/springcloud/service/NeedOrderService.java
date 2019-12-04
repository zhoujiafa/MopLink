package com.springcloud.service;

import com.springcloud.bean.ao.NeedOrderSaveAO;
import com.springcloud.bean.query.DataLineQuery;
import com.springcloud.bean.query.NeedOrderQuery;
import com.springcloud.bean.vo.DataLineVO;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.util.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
public interface NeedOrderService {


    SaveResult saveNeedOrder(Map<String, Object> map);


    List<NeedOrderVO> list(String companyCode, String needOrderNo);


    QueryResult<NeedOrderVO> page(long page, long size, NeedOrderQuery query);


}

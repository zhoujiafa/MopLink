package com.springcloud.service;

import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.GoodsDictQuery;
import com.springcloud.bean.query.GoodsDivisionQuery;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
public interface GoodsDivisionService {

    /**
     * 个体
     * @param barCode
     * @return
     */
    GoodsDivision getOneBybarCode(String barCode);

    /**
     *  物料关联列表
     * @param query
     * @return
     */
    List<GoodsDivision> list(GoodsDivisionQuery query);

}

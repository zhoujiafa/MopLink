package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.GoodsDict;

import java.util.List;


/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@DS(value = "stock")
public interface GoodsDictMapper extends BaseMapper<GoodsDict> {


    /**
     * 批量新增
     * @param companyDictList
     * @return
     */
    Boolean batchInsert(List<GoodsDict> companyDictList);


}

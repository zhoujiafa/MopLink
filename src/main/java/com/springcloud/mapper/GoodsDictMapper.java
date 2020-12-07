package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.GoodsDict;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 吊牌查询（颜色分组）
     * @param itemCode
     * @return
     */
    List<GoodsDict>  selectBatchItemCode(@Param("itemCode")List<String> itemCode);

    /**
     * 吊牌查询（款式分组）
     * @param itemCode
     * @return
     */
    List<GoodsDict>  selectBatchItemCode2(@Param("itemCode")List<String> itemCode);


}

package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.dos.DeliverGoodsCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author darren.zhou
 * @Description: 发货接口信息
 * @ClassName: ClassName
 * @Create: Date Time
 */
//@DS(value = "jzn_wms_test")
@Mapper
public interface DeliverGoodsMapper extends BaseMapper<DeliverGoods> {

    List<DeliverGoods> getDeliverGoods(Map<String, Object> map);

    List<DeliverGoodsCount> getDeliveryorderDetail(Map<String, Object> map);

    List<DeliverGoods> getDeliveryOrderUniqueCode(Map<String, Object> map);


}

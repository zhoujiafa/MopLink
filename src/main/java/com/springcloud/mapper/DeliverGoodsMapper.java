package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.DeliverGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description: 发货接口信息
 * @ClassName: ClassName
 * @Create: Date Time
 */
@DS(value = "jzn_wms")
@Mapper
public interface DeliverGoodsMapper extends BaseMapper<DeliverGoods> {

    List<DeliverGoods> getDeliverGoods(String orderNo);

}

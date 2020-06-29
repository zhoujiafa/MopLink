package com.springcloud.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.dos.DeliverGoods;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
@DS("jzn_wms")
public interface DeliverGoodsService {

    List<DeliverGoods> listByneedNo(String orderNo);
}

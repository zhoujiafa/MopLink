package com.springcloud.response;

import com.springcloud.bean.dos.DeliverGoods;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : ResponseDeliverGoods
 * @Description : 发货清单
 * @Author : Joe
 * @Date: 2020-07-13 14:33
 */
@Data
public class ResponseDeliverGoods {

    List<DeliverGoods> deliverGoods;

    Integer total;

    Double priceTag;

    Double outboundPrice;
}

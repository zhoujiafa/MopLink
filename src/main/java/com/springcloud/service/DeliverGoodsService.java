package com.springcloud.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.ao.CompanyDictAO;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.response.ResponseDeliverGoods;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @ClassName : DeliverGoodsService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
//@DS("jzn_wms")
@DS("jzn_wms_test")
public interface DeliverGoodsService {

    /**
     * listByneedNo
     * @param orderNo
     * @param companyCode
     * @return
     */
    List<DeliverGoods> listByneedNo(String orderNo,String companyCode);

    /**
     * listByneedNo
     * @param orderNo
     * @param companyCode
     * @return
     */
    List<DeliverGoods> listByneedNo_xls(String orderNo,String companyCode);


    /**
     * 讯商发货单转MOP 01（调用第三方接口修改原订货单）
     * @param list
     * @param destineOrderNo
     * @param customer
     * @return String
     */
    String responseTorequest(List<DeliverGoods> list,String shipmentOrderNo, String destineOrderNo, String customer);

    /**
     * 供应销售订单明细更改（调用第三方接口修改工供应销售单）
     * @param list
     * @param shipmentOrderNo
     * @param destineOrderNo
     * @param customer
     * @return String
     */
    String updateSalesOrderDetails(List<DeliverGoods> list,String shipmentOrderNo,String destineOrderNo, String customer, String distrCode);

}

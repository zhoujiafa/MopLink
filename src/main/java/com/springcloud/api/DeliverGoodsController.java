package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.query.Query;
import com.springcloud.response.ResponseDeliverGoods;
import com.springcloud.service.DeliverGoodsService;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : UserController
 * @Description : 发货数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/deliverGoods")
public class DeliverGoodsController {

    @Autowired
    DeliverGoodsService deliverGoodsService;


    @ApiOperation(value = "提供第三方信息接口（提供发货单）", notes = "提供第三方信息接口")
    @PostMapping("/DeliverGoodsList")
    public ResponseBean2<List<DeliverGoods>> DeliverGoodsList(@RequestBody Map data) {
        String sign = data.get("sign").toString();
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        //String shipmentOrderNo = data.get("orderNo").toString();
        //String customer = data.get("customer").toString();
        String  shipmentOrderNo = "201100000001";
        String customer = "0181";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderNo", shipmentOrderNo);
        params.put("appId", appId);
        params.put("customer", customer);

        List<DeliverGoods> list = deliverGoodsService.listByneedNo(shipmentOrderNo, customer);
        if (list == null || list.size() == 0) {
            return ResponseBean2.fail("无此发货单信息，请检查订单号是否错误");
        }else{
            String  destineOrderNo = list.iterator().next().getOrderNo();
            //修改订单明细接口01
            //String jsonStr = deliverGoodsService.responseTorequest(list,shipmentOrderNo,destineOrderNo,customer);
            String jsonStr = deliverGoodsService.updateSalesOrderDetails(list,shipmentOrderNo,destineOrderNo,customer);

            JSONObject jsonObject = JSON.parseObject(jsonStr);

        }
        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        if (!newSign.equals(sign)) {
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
        }

        //计算总价格和总件数
        Integer total = 0;
        Double priceTag = 0.00;
        Double outboundPrice = 0.00;
        ResponseDeliverGoods responseDeliverGoods = new ResponseDeliverGoods();
        for (DeliverGoods deliverGoods : list) {
            priceTag += Double.valueOf(deliverGoods.getPriceTag());
            outboundPrice += Double.valueOf(deliverGoods.getOutboundPrice());
            total = list.size();
        }
        responseDeliverGoods.setPriceTag(priceTag);
        responseDeliverGoods.setOutboundPrice(outboundPrice);
        responseDeliverGoods.setTotal(total);
        responseDeliverGoods.setDeliverGoods(list);
        return ResponseBean2.ok(responseDeliverGoods);


    }


    @ApiOperation(value = "列表查询", notes = "列表查询(新零售)")
//    @RequestMapping(value = "/listByOrderNo",method = {RequestMethod.POST,RequestMethod.GET})
    @PostMapping("/listByOrderNo")
    public List<DeliverGoods> listByOrderNo(@RequestBody Map data) {

        String orderNo = data.get("orderNo").toString();
        String companyCode = data.get("companyCode").toString();
        //String orderNo = "200900001619";
        //String companyCode = "GCTNB";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderNo", orderNo);
        params.put("companyCode", companyCode);

        List<DeliverGoods> list = deliverGoodsService.listByneedNo_xls(orderNo, companyCode);

        return list;
        }
        }

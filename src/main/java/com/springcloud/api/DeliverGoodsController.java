package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.DeliverGoods;
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
    @RequestMapping(value = "/DeliverGoodsList",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2<List<DeliverGoods>> DeliverGoodsList(HttpServletResponse response, @RequestBody Map data) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String sign = data.get("sign").toString();
        //String sign = "16A3BF4158C21EA5BA231A83772971A4";
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        String orderNo = data.get("orderNo").toString();
        //String needNo = "191200000382";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderNo", orderNo);
        params.put("appId", appId);


        List<DeliverGoods> list = deliverGoodsService.listByneedNo(orderNo);
        if(list==null || list.size()==0){
            return ResponseBean2.fail("无此发货单信息，请检查订单号是否错误");
        }
        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        if(!newSign.equals(sign)){
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
        }

        return ResponseBean2.ok(list);
    }
}

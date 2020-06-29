package com.springcloud.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.analyticaldata.SystemUtil;
import com.springcloud.bean.vo.SalesOrderVO;
import com.springcloud.mapper.SalesOrderMapper;
import com.springcloud.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.springcloud.analyticaldata.SystemUtil.invoke;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    SalesOrderMapper salesOrderMapper;


    @Override
    public SalesOrderVO getSaleStock(Map data) {

        HashMap<String,String> map=new HashMap<String, String>();
        map.put("appKey","U1CITYCKTEST");
        //data.put("billNo","XSC1901241502814495");
        //data.put("pageIndex","1");
        map.put("pageSize","500");
        map.put("startTime","2017-01-01");
        map.put("endTime","2020-03-05");


        HashMap<String,String> restJson=invoke("http://wqbopenapi.ushopn6.com/wqbnew/api.rest","IOpenAPI.GetSaleStock","U1CITYCKTEST","U1CITYCKTESTJJKIUNHB","json",data);
        System.out.println(restJson);

        String mapjson = restJson.get("result");
        JSONObject jsonObj1 = JSONObject.parseObject(mapjson);
        JSONArray array = jsonObj1.getJSONArray("Result");

        List<SalesOrderVO> list =(SystemUtil.getJsonArrayItem(array));


        return null;
    }
}

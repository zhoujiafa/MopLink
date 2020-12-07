package com.springcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticaldata.MOPIndentAD;
import com.springcloud.bean.vo.MOPIndentVO;
import org.json.JSONException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.springcloud.service.impl.MOPIndentServiceImpl.sendPost;

/**
 * @ClassName : Test
 * @Description : 11
 * @Author : Joe
 * @Date: 2020-09-15 09:45
 */
public class Test2 {


    private static final String url = "http://test.try-shopping.com/ts-openapi";
    //修改MOP接口KEY
    private static final String key = "285e11c1e83a4094b35cc3cf320ad820";
    //测试下单接口
    //private static final String key = "A1DF1E59090EAFF035C3091003A05CFC";

    public static void main(String[] args) throws JSONException {

                Map<String, Object> params = new HashMap<String, Object>();
        List<Map> mapList = new LinkedList<>();
        params.put("companyCode", "W144");
        params.put("appId", "8edd25b7f74f44a7bff1e9f2af49c930");
        //params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");

        Map<String, Object> dataJson = new HashMap<String, Object>();
        dataJson.put("sourceType", "RK002");
        dataJson.put("sourceId", "K7879");
        //taJson.put("taskId", "20190119151800");
        dataJson.put("remark", "备注");

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mitemCode","SP000164");
        map.put( "skuBarcode","SP000164");
        map.put( "quantity",10);
        map.put( "remark","行备注");

//        map1.put("fID","934350120700477252");
//        map1.put("quantity","1");
//        map1.put( "skuBarcode","C19113ZCU2XL");
//        map1.put( "itemCode","ZC19113");
//        map1.put( "unit","g");
//        map1.put( "realPrice","168.8");
//        map1.put( "isGive",0);
        mapList.add(map);
        //mapList.add(map1);

        dataJson.put("lines", mapList);

        params.put("dataJson", dataJson);
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String requsetUrl = url + "/openapi/whsIn/add";
        String resultJson = sendPost(requsetUrl, paramsJson);

        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        JSONObject jsonObj1 = JSONObject.parseObject(resultJson);

    }
}

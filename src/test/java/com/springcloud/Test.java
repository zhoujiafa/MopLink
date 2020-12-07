package com.springcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticaldata.MOPIndentAD;
import com.springcloud.bean.vo.MOPIndentVO;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;

import static com.springcloud.service.impl.MOPIndentServiceImpl.sendPost;

/**
 * @ClassName : Test
 * @Description : 11
 * @Author : Joe
 * @Date: 2020-09-15 09:45
 */
public class Test {

//    public static void main(String[] args) {
//        String key = "A1DF1E59090EAFF035C3091003A05CFC";
//        //测试签名结果
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("appId", "94916115E6732C11D5984075C4DB588B");
//        params.put("key", "A1DF1E59090EAFF035C3091003A05CFC");
//        params.put("companyCode", "0181");
//        //具体业务参数
//        Map<String, Object> dataJson = new HashMap<String, Object>();
//        dataJson.put("orderNo", "DO8191209190458085");
//        params.put("dataJson", dataJson);
//        System.out.println(JSON.toJSON(SignUtil.sign(params,key).get("sign")).toString());
//    }
    private static final String url = "http://test.try-shopping.com/ts-openapi";
    //修改MOP接口KEY
    //private static final String key = "285e11c1e83a4094b35cc3cf320ad820";
    //测试下单接口
    private static final String key = "A1DF1E59090EAFF035C3091003A05CFC";

    public static void main(String[] args) throws JSONException {

//        Map<String, Object> params = new HashMap<String, Object>();
//        List<Map> mapList = new LinkedList<>();
//        params.put("companyCode", "W144");
//        params.put("appId", "8edd25b7f74f44a7bff1e9f2af49c930");
//        //params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");
//
//        Map<String, Object> dataJson = new HashMap<String, Object>();
//        dataJson.put("orderNo", "DO1200909103634321");
//
//        Map<String, Object> map1 = new HashMap<String, Object>();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("fID","934350120700477125");
//        map.put("quantity","1");
//        map.put( "skuBarcode","643503ZENL");
//        map.put( "itemCode","E643503");
//        map.put( "unit","g");
//        map.put( "realPrice","79.8");
//        map.put( "isGive",0);
//
//        map1.put("fID","934350120700477252");
//        map1.put("quantity","1");
//        map1.put( "skuBarcode","C19113ZCU2XL");
//        map1.put( "itemCode","ZC19113");
//        map1.put( "unit","g");
//        map1.put( "realPrice","168.8");
//        map1.put( "isGive",0);
//        mapList.add(map);
//        //mapList.add(map1);
//
//        dataJson.put("lines", mapList);
//
//        params.put("dataJson", dataJson);
//        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
//        String requsetUrl = url + "/openapi/b2borderItem/changeStatus";
//        String resultJson = sendPost(requsetUrl, paramsJson);
//
//        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
//        JSONObject jsonObj1 = JSONObject.parseObject(resultJson);





        /**
         * 测试下单接口
          */
       MOPIndentVO response = new MOPIndentVO();
        Map<String, Object> params = new HashMap<>();
        params.put("companyCode", "0181");
        params.put("appId", "94916115E6732C11D5984075C4DB588B");
        Map<String, Object> dataJson = new HashMap<>();
        //Map<String, Object> dataJson = new HashMap<>();
        dataJson.put("orderType", "1");
        dataJson.put("orderStatusName", "待发货");
        dataJson.put("orderNo", "S01810050");
        dataJson.put("companyName", "广州（旗舰店）戴芝蒂/吉芝奴");
        dataJson.put("payStatusName", "已付款");
        dataJson.put("orderStatus", "0");
        dataJson.put("deliveryDate", "");
        dataJson.put("createName", "朱素勇");
        dataJson.put("orderTypeName", "订货");
        dataJson.put("createDate", "2020-11-25 15:57:33");
        //lines处理
        Map<String, Object> lines = new HashMap<>();
        lines.put("realPriceLineTotal", 320.4);
        lines.put("itemQuantity", 2);
        lines.put("quantity", 2);
        lines.put("lineTotal", 320.4);
        lines.put("itemCode", "SP000001");
        lines.put("itemUnit", "件");
        lines.put("needsCount", 2);
        lines.put("promotionMoney", 0);
        lines.put( "remark", 553);
        lines.put("realTotal",320.4);
        lines.put("itemName", "双芝 可外穿休闲家居舒适 运动内衣#S3198（唯一码）");
        lines.put("unit", "件");
        lines.put("specName", "兰色 70B");
        lines.put("totalExpressFee", 0);
        lines.put("skuBarcode", "3198BER70");
        lines.put("listPrice", 160.2);
        lines.put("realPrice", 160.2);



        Map<String, Object> lines2 = new HashMap<>();
        lines2.put("realPriceLineTotal", 320.4);
        lines2.put("itemQuantity", 2);
        lines2.put("quantity", 2);
        lines2.put("lineTotal", 320.4);
        lines2.put("itemCode", "SP000001");
        lines2.put("itemUnit", "件");
        lines2.put("promotionMoney", 780);
        lines2.put("itemName", "双芝 可外穿休闲家居舒适 运动内衣#S3198（唯一码）");
        lines2.put("unit", "g");
        lines2.put("totalExpressFee", 1);
        lines2.put("skuBarcode", "3198BER70");
        lines2.put("listPrice", 2);
        lines2.put("realPrice", 160.2);
        lines2.put( "remark", "1052466067847398545");
        lines2.put("needsCount", 2);
        lines2.put("realTotal",2);

        List<Map> mapList = new LinkedList<>();
        mapList.add(lines);
        //mapList.add(lines2);

        dataJson.put("array",mapList);

        params.put("dataJson", dataJson);
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();

        String requsetUrl = "http://localhost:8087/MOPIndent/PlaceAnMOPIndents";
        //String requsetUrl = "http://14.17.96.21:8087/MOPIndent/PlaceAnMOPIndents";
        //String requsetUrl = "https://gzshuangzhi.com/MOPIndent/PlaceAnMOPIndents";
        String paramsJson2 = "{\"companyCode\":\"0181\",\"sign\":\"D5C2D8C2D666CAD1E4CFFE83BCA23DB8\",\"dataJson\":\"{\\\"orderType\\\":1,\\\"orderStatusName\\\":\\\"待发货\\\",\\\"orderNo\\\":\\\"S01810049\\\",\\\"array\\\":[{\\\"realPriceLineTotal\\\":480.6,\\\"itemQuantity\\\":3,\\\"quantity\\\":3,\\\"lineTotal\\\":480.6,\\\"itemCode\\\":\\\"SP000001\\\",\\\"itemUnit\\\":\\\"件\\\",\\\"needsCount\\\":3,\\\"promotionMoney\\\":\\\"0\\\",\\\"remark\\\":551,\\\"realTotal\\\":480.6,\\\"itemName\\\":\\\"双芝 可外穿休闲家居舒适 运动内衣#S3198（唯一码）\\\",\\\"unit\\\":\\\"件\\\",\\\"totalExpressFee\\\":\\\"0\\\",\\\"skuBarcode\\\":\\\"3198B8070\\\",\\\"listPrice\\\":160.2,\\\"realPrice\\\":160.2}],\\\"companyName\\\":\\\"广州（旗舰店）戴芝蒂/吉芝奴\\\",\\\"payStatusName\\\":\\\"已付款\\\",\\\"orderStatus\\\":0,\\\"deliveryDate\\\":\\\"\\\",\\\"createName\\\":\\\"小橘子\\\",\\\"orderTypeName\\\":\\\"订货\\\",\\\"createDate\\\":\\\"2020-11-24 18:05:13\\\"}\",\"appId\":\"94916115E6732C11D5984075C4DB588B\"}";
        String resultJson = sendPost(requsetUrl, paramsJson);
        JSONObject jsonObj1 = JSONObject.parseObject(resultJson);
        if (!jsonObj1.getString("ResultInt").equals("0")) {
            Integer error;
            if (jsonObj1.getString("ResultString").equals("找不到此订货单")) {
                error = Integer.valueOf(jsonObj1.getString("ErrorCode"));
            } else {
                error = Integer.valueOf(jsonObj1.getString("code"));
            }
            response.setCode(error);
            String message = jsonObj1.getString("ResultString");
            response.setMessage(message);
        } else {
            MOPIndentAD a = new MOPIndentAD();
            response = a.getJsonToBeanSecond(resultJson);
            JSONObject jsonObj = JSONObject.parseObject(resultJson);
            JSONObject dataLine = jsonObj.getJSONObject("DataLine");

        }

      //销售单入库出库

   }
}

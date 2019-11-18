package com.springcloud.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.bean.vo.DataLineVO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.reabam.sign.SignUtil;


public class OrderDetail {


    static final String appId = "BC7CEC0171504DF799CB4972541C0FXS";
    static final String key = "285e11c1e83a4094b35cc3cf320ad820";
    static final String companyCode = "0144";
    private static final String url = "http://test.try-shopping.com/ts-openapi";

    public static DataLineVO DataLineVO()  {

        // 测试签名结果
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appId);
        //params.put("taskId", "20180125165130");
        params.put("companyCode", "0324");
        // 具体业务参数
        Map<String, Object> dataJson = new HashMap<String, Object>();

        //dataJson.put("needOrderNo", "301445621412");
        params.put("dataJson", "{\"needOrderNo\":\"303248530001\"}");
        //params.put("dataJson", dataJson);
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
        //System.out.println(paramsJson);
        String requsetUrl = url + "/openapi/needOrder/detail";
        String resultJson = sendPost(requsetUrl, paramsJson);

        System.out.println(resultJson);

        AnalyticalData a = new AnalyticalData();
        DataLineVO bean = a.getJsonToBeanSecond(resultJson);


        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        JSONObject dataLine = jsonObj.getJSONObject("DataLine");

        bean.setLines(a.getJsonToBeanThird(dataLine));

        return bean;
    }


    /**
     * 生成主键(年月日+ 4位随机数)
     */
    public static String getMopPrimaryKey(){

        String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 获取4位随机数
        int ranNum = (int) (new Random().nextDouble() * (9999 - 1000 + 1)) + 1000;
        return str+ranNum;
    }

    public static String getBetweenPrimaryKey(){

        String str = new SimpleDateFormat("MMddyyyy").format(new Date());
        // 获取4位随机数
        int ranNum = (int) (new Random().nextDouble() * (9999 - 1000 + 1)) + 1000;
        return str+ranNum;
    }


    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 请求参数 json格式：{'name':'ABC','age':'20'}
     * @return
     */
    public static String sendPost(String url, String params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity sEntity = new StringEntity(params, "UTF-8");
            httpPost.setEntity(sEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

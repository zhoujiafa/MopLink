package com.springcloud.analyticaldata;


import com.alibaba.fastjson.JSON;
import com.reabam.sign.SecretUtil;
import com.springcloud.bean.vo.SalesOrderVO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SalesOrderDetail {

    /**第三方Key*/
    static final String sKey = "U1CITYCKTEST";
    /**第三方Secret*/
    static final String sSecret = "U1CITYCKTESTJJKIUNHB";
    /** 接口名称*/
    static final String sMethod = "IOpenAPI.GetSaleStock";
    /** 返回方式[xml 或 json]*/
    static final String sFormat = "json";

    public static void main(String[] args) {
        SalesOrderVO MOPNeedOrderVO = SalesOrderDetail.DataLineVO();
    }
    public static SalesOrderVO DataLineVO() {

        String sToken = SecretUtil.MD5(Asc(sSecret + sMethod + "appKey" + sKey).replace(" ","").toLowerCase());

        Map<String, Object> params = new HashMap<String, Object>();
        //String postData = String.format("user={0}&method={1}&token={2}&format={3}&appKey={4}", sKey, sMethod, sToken, sFormat, sKey);
        params.put("user",sKey);
        params.put("method",sMethod);
        params.put("token",sToken);
        params.put("format",sFormat);
        params.put("appKey",sKey);

        /*测试的接口地址：http://wqbopenapi.ushopn6.com/wqbnew/api.rest
        仓库端的第三方对接Key：U1CITYCKTEST
        第三方对接Secert：U1CITYCKTESTJJKIUNHB*/
        /*接口名称+Key参数名称+ Key参数值+参数名称1+参数值1+参数名称2+参数值2+接口Secert*/


        String paramsJson = JSON.toJSON(params).toString();
        String resultJson = sendPost("http://wqbopenapi.ushopn6.com/wqbnew/api.rest", paramsJson);

        System.out.println(resultJson);
        return null;
    }


    /*public static String Encrypt_MD5(String AppKey)
    {
        MD5 MD5 = new MD5CryptoServiceProvider();
        byte[] datSource = Encoding.GetEncoding("gb2312").GetBytes(AppKey);
        byte[] newSource = MD5.ComputeHash(datSource);

        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < newSource.length; i++)
        {
            sb.append(newSource[i].ToString("x").PadLeft(2, '0'));
        }
        String crypt = sb.toString();
        return crypt;
    }*/


    //排序
    static String Asc(String input) {
        char[] arr = input.toCharArray();
        Arrays.sort(arr);
        String strAsc = "";
        for (char item : arr) {
            strAsc += item;
        }
        return strAsc;
    }

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

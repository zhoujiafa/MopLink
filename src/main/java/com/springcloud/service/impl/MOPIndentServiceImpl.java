package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticalData.MOPIndentAD;
import com.springcloud.bean.dos.MOPIndent;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.mapper.MOPNeedOrderDtMapper;
import com.springcloud.mapper.MOPNeedOrderMapper;
import com.springcloud.mapper.NeedOrderMapper;
import com.springcloud.analyticalData.MOPNeedOrderAD;
import com.springcloud.service.MOPIndentService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName : MOPNeedOrderServiceImpl
 * @Description : 订货单接口服务
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */


@Service
public class MOPIndentServiceImpl implements MOPIndentService {

    private static final String url = "http://test.try-shopping.com/ts-openapi";
    private static final String key = "285e11c1e83a4094b35cc3cf320ad820";


    @Autowired
    MOPNeedOrderMapper MOPNeedOrderMapper;
    @Autowired
    MOPNeedOrderDtMapper MOPNeedOrderDtMapper;
    @Autowired
    NeedOrderMapper needOrderMapper;


    @Override
    public List<MOPIndentVO> getmopIndent(String companyCode, String orderNo) {
        List<MOPIndentVO> lineVOList = new ArrayList<>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyCode", companyCode);
        params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");

        Map<String, Object> dataJson = new HashMap<String, Object>();
        dataJson.put("orderNo", orderNo);

        params.put("dataJson", dataJson);
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String requsetUrl = url + "/openapi/order/detail";
        String resultJson = sendPost(requsetUrl, paramsJson);
        MOPIndentAD a = new MOPIndentAD();
        MOPIndentVO bean = a.getJsonToBeanSecond(resultJson);


        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        JSONObject dataLine = jsonObj.getJSONObject("DataLine");
        bean.setCompanyCode(companyCode);
        bean.setLines(a.getJsonToBeanThird(dataLine));
        lineVOList.add(bean);
        return lineVOList;
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

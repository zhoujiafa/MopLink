package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticalData.MOPIndentAD;
import com.springcloud.analyticalData.OrderDetail;
import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.dos.MOPIndent;
import com.springcloud.bean.dos.MOPIndentDt;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.IndentMapper;
import com.springcloud.mapper.MOPIndentDtMapper;
import com.springcloud.mapper.MOPIndentMapper;
import com.springcloud.mapper.MOPNeedOrderMapper;
import com.springcloud.service.MOPIndentService;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean;
import com.springcloud.bean.dos.Indent;
import com.springcloud.util.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    MOPIndentMapper mopIndentMapper;
    @Autowired
    MOPIndentDtMapper mopIndentDtMapper;
    @Autowired
    IndentMapper indentMapper;


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
        JSONObject jsonObj1 = JSONObject.parseObject(resultJson);
        if(jsonObj1.getString("ResultInt").equals("-1")){
            String message = jsonObj1.getString("ResultString");
            Integer error = Integer.valueOf(jsonObj1.getString("code"));
            ResultBean response = new ResultBean();
            response.setCode(error);
            response.setMsg(message);
            return null;
        }
        MOPIndentAD a = new MOPIndentAD();
        MOPIndentVO bean = a.getJsonToBeanSecond(resultJson);
        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        JSONObject dataLine = jsonObj.getJSONObject("DataLine");
        bean.setCompanyCode(companyCode);
        bean.setLines(a.getJsonToBeanThird(dataLine));
        lineVOList.add(bean);
        //return ResponseBean.ok(lineVOList);
        return lineVOList;
    }

    @Override
    public ResponseBean<SaveResult> saveMopIndent(MOPIndentAO mopIndentAO) {
        SaveResult saveResult;
        MOPIndent addMOPIndent = new MOPIndent();
        BeanUtils.copyProperties(mopIndentAO, addMOPIndent);
        addMOPIndent.setDocNum(OrderDetail.getMopPrimaryKey());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addMOPIndent.setCreateDate(dateFormat.format(new Date()));
        List<MOPIndentDt> mopIndentDts = mopIndentAO.getLines();
        if (mopIndentDts.size() > 0 && mopIndentDts != null) {
            for(MOPIndentDt mopIndentDt : mopIndentDts){
                mopIndentDt.setDocNum(addMOPIndent.getDocNum());
            }
            boolean bool = mopIndentDtMapper.batchInsert(mopIndentDts);
            if(!bool){
                return ResponseBean.fail("订单明细数据异常，录入失败!");
            }
        }
        QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderNo", mopIndentAO.getOrderNo());
        queryWrapper.eq("companyCode", mopIndentAO.getCompanyCode());
        Indent searchModel = indentMapper.selectOne(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyCode",  mopIndentAO.getCompanyCode());
        map.put("ResultString", "success");
        map.put("docNum", addMOPIndent.getDocNum());
        map.put("createName", mopIndentAO.getNewCreateName());
        map.put("ResultInt", 0);
        if(searchModel==null){
            map.put("IsRetransmit", false);
            map.put("IsCompulsorySubmission", false);
        }else{
            if(mopIndentAO.getIsRetransmit()==true && mopIndentAO.getIsCompulsorySubmission()==false){
                map.put("IsRetransmit", true);
                map.put("IsCompulsorySubmission", false);
            }else if(mopIndentAO.getIsRetransmit()==true && mopIndentAO.getIsCompulsorySubmission()==true){
                map.put("IsRetransmit", true);
                map.put("IsCompulsorySubmission", true);
            }
        }
        mopIndentMapper.insert(addMOPIndent);
        saveResult = indentMapper.saveIndent(map);
        return ResponseBean.ok(saveResult);
    }

    @Override
    public QueryResult<MOPIndentVO> page(long page, long size, MOPIndentQuery query) {
        QueryWrapper<MOPIndent> queryWrapper = new QueryWrapper<>();
        /*queryWrapper = queryEntity(query, queryWrapper);*/
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (!StringUtils.isEmpty(query.getOrderNo())) {
                queryWrapper.eq("docNum", query.getOrderNo());
            }
        }
        Page<MOPIndent> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<MOPIndent> ipage = mopIndentMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<MOPIndent>();
        BeanUtils.copyProperties(ipage, queryResult);
        return queryResult;
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

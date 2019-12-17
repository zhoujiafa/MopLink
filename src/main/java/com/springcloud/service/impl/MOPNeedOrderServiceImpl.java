package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.dos.MOPNeedOrderDt;
import com.springcloud.bean.dos.NeedOrder;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.MOPNeedOrderMapper;
import com.springcloud.mapper.MOPNeedOrderDtMapper;
import com.springcloud.mapper.NeedOrderMapper;
import com.springcloud.analyticalData.MOPNeedOrderAD;
import com.springcloud.analyticalData.OrderDetail;
import com.springcloud.service.MOPNeedOrderService;
import com.springcloud.util.QueryResult;
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
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName : MOPNeedOrderServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */


@Service
public class MOPNeedOrderServiceImpl implements MOPNeedOrderService {

    private static final String url = "http://test.try-shopping.com/ts-openapi";
    private static final String key = "285e11c1e83a4094b35cc3cf320ad820";



    @Autowired
    MOPNeedOrderMapper MOPNeedOrderMapper;
    @Autowired
    MOPNeedOrderDtMapper MOPNeedOrderDtMapper;
    @Autowired
    NeedOrderMapper needOrderMapper;

    @Override
    public Boolean insert(MOPNeedOrder MOPNeedOrder) {
        return MOPNeedOrderMapper.insert(MOPNeedOrder) > 0;
    }

    @Override
    public List<MOPNeedOrderVO> getdataline(String companyCode, String needorderNo) {
        List<MOPNeedOrderVO> lineVOList = new ArrayList<>();
        Map<String, Object> params = new HashMap<String, Object>();
        //companyCode = "0324";
        params.put("companyCode", companyCode);
        params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");

        Map<String, Object> dataJson = new HashMap<String, Object>();
        dataJson.put("needOrderNo", needorderNo);

        params.put("dataJson", dataJson);
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String requsetUrl = url + "/openapi/needOrder/detail";
        String resultJson = sendPost(requsetUrl, paramsJson);
        MOPNeedOrderAD a = new MOPNeedOrderAD();
        MOPNeedOrderVO bean = a.getJsonToBeanSecond(resultJson);


        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        JSONObject dataLine = jsonObj.getJSONObject("DataLine");
        bean.setCompanyCode(companyCode);
        bean.setLines(a.getJsonToBeanThird(dataLine));

        //DataLine addDataLine = new DataLine();
        //BeanUtils.copyProperties(bean,addDataLine);
        //addDataLine.setDocNum(OrderDetail.getMopPrimaryKey());
        lineVOList.add(bean);
        return lineVOList;
    }

    @Override
    public List<MOPNeedOrderVO> getLocalInfo(String companyCode, String needOrderNo) {
        List<MOPNeedOrderVO> lineVOList = new ArrayList<>();
        List<MOPNeedOrder> MOPNeedOrderList;
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(needOrderNo)) {
            QueryWrapper<MOPNeedOrder> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.like("needNo", needOrderNo);
            MOPNeedOrderList = MOPNeedOrderMapper.selectList(queryWrapper);
            for (MOPNeedOrder MOPNeedOrder : MOPNeedOrderList) {
                MOPNeedOrderVO MOPNeedOrderVO = new MOPNeedOrderVO();
                BeanUtils.copyProperties(MOPNeedOrder, MOPNeedOrderVO);
                lineVOList.add(MOPNeedOrderVO);
            }
        }
        return lineVOList;
    }
    @Transactional
    @Override
    public Boolean saveDataLine(MOPNeedOrderVO mopNeedOrderAO){
        MOPNeedOrder addMOPNeedOrder = new MOPNeedOrder();
        BeanUtils.copyProperties(mopNeedOrderAO, addMOPNeedOrder);
        addMOPNeedOrder.setDocNum(OrderDetail.getMopPrimaryKey());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addMOPNeedOrder.setCreateDate(dateFormat.format(new Date()));

        List<MOPNeedOrderDt> MOPNeedOrderDts = mopNeedOrderAO.getLines();
        if (MOPNeedOrderDts.size() > 0 && MOPNeedOrderDts != null) {
            for(MOPNeedOrderDt MOPNeedOrderDt : MOPNeedOrderDts){
                MOPNeedOrderDt.setDocNum(addMOPNeedOrder.getDocNum());
                //测试数据
                //MOPNeedOrderDt.setItemQuantity(1);
            }
            boolean bool = MOPNeedOrderDtMapper.batchInsert(MOPNeedOrderDts);
            if(!bool){
                return false;
            }
        }
        QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("needNo", mopNeedOrderAO.getNeedNo());
        queryWrapper.eq("companyCode", mopNeedOrderAO.getCompanyCode());
        NeedOrder searchModel = needOrderMapper.selectOne(queryWrapper);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyCode", mopNeedOrderAO.getCompanyCode());
            map.put("IsCompulsorySubmission", false);
            map.put("ResultString", "aa");
            map.put("docNum", addMOPNeedOrder.getDocNum());
            map.put("createName", "admin");
            map.put("IsRetransmit", false);
            map.put("ResultInt", 0);
        MOPNeedOrderMapper.insert(addMOPNeedOrder);
            SaveResult saveResult = needOrderMapper.saveNeedOrder(map);
        return saveResult==null;

    }

    @Override
    public List<MOPNeedOrderVO> list(MOPNeedOrderQuery query) {
        List<MOPNeedOrderVO> lineVOList = new ArrayList<>();
        List<MOPNeedOrder> MOPNeedOrderList;
        if (query != null) {
            QueryWrapper<MOPNeedOrder> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (StringUtils.isNotBlank(query.getNeedOrderNo())) {
                queryWrapper.like("needNo", query.getNeedOrderNo());
            }
            MOPNeedOrderList = MOPNeedOrderMapper.selectList(queryWrapper);
            for (MOPNeedOrder MOPNeedOrder : MOPNeedOrderList) {
                MOPNeedOrderVO MOPNeedOrderVO = new MOPNeedOrderVO();
                BeanUtils.copyProperties(MOPNeedOrder, MOPNeedOrderVO);
                lineVOList.add(MOPNeedOrderVO);
            }
        }
        return lineVOList;
    }

    @Override
    public QueryResult<MOPNeedOrderVO> page(long page, long size, MOPNeedOrderQuery query) {
        QueryWrapper<MOPNeedOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryEntity(query, queryWrapper);

        Page<MOPNeedOrder> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<MOPNeedOrder> ipage = MOPNeedOrderMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<MOPNeedOrder>();
        BeanUtils.copyProperties(ipage, queryResult);
        return queryResult;
    }

    /*@PostConstruct
    private Boolean insert() {
        DataLineVO dataLineVO = OrderDetail.DataLineVO();
        DataLine addDataLine = new DataLine();
        BeanUtils.copyProperties(dataLineVO,addDataLine);
        addDataLine.setDocNum(OrderDetail.getMopPrimaryKey());
        return dataLineMapper.insert(addDataLine) > 0;

    }*/


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


    /**
     * 查询实体处理
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<MOPNeedOrder> queryEntity(MOPNeedOrderQuery query, QueryWrapper<MOPNeedOrder> queryWrapper) {
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (!StringUtils.isEmpty(query.getNeedOrderNo())) {
                queryWrapper.eq("docNum", query.getNeedOrderNo());
            }
        }
        return queryWrapper;
    }
}

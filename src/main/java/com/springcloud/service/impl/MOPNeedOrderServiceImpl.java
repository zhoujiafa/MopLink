package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.ao.MOPNeedOrderAO;
import com.springcloud.bean.dos.*;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.MOPNeedOrderMapper;
import com.springcloud.mapper.MOPNeedOrderDtMapper;
import com.springcloud.mapper.NeedOrderMapper;
import com.springcloud.service.MOPNeedOrderService;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;
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
    MOPNeedOrderMapper mopNeedOrderMapper;
    @Autowired
    MOPNeedOrderDtMapper mopNeedOrderDtMapper;
    @Autowired
    NeedOrderMapper needOrderMapper;

    @Override
    public Boolean insert(MOPNeedOrder MOPNeedOrder) {
        return mopNeedOrderMapper.insert(MOPNeedOrder) > 0;
    }

    @Override
    public MOPNeedOrderVO getdataline(String companyCode, String needorderNo) {
        MOPNeedOrderVO response = new MOPNeedOrderVO();
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
        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        if (!jsonObj.getString("ResultInt").equals("0")) {
            Integer error;
            if (jsonObj.getString("ResultString").equals("找不到此要货单")) {
                error = Integer.valueOf(jsonObj.getString("ErrorCode"));
            } else {
                error = Integer.valueOf(jsonObj.getString("code"));
            }
            response.setCode(error);
            String message = jsonObj.getString("ResultString");
            response.setMessage(message);
        } else {
            com.springcloud.analyticaldata.MOPNeedOrderAD a = new com.springcloud.analyticaldata.MOPNeedOrderAD();
            response = a.getJsonToBeanSecond(resultJson);
            JSONObject jsonObj2 = JSONObject.parseObject(resultJson);
            JSONObject dataLine = jsonObj2.getJSONObject("DataLine");
            response.setCompanyCode(companyCode);
            response.setLines(a.getJsonToBeanThird(dataLine));
        }
        return response;
    }

    @Override
    public List<MOPNeedOrderVO> getLocalInfo(String companyCode, String needOrderNo) {
        List<MOPNeedOrderVO> lineVOList = new ArrayList<>();
        List<MOPNeedOrder> MOPNeedOrderList;
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(needOrderNo)) {
            QueryWrapper<MOPNeedOrder> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.like("needNo", needOrderNo);
            MOPNeedOrderList = mopNeedOrderMapper.selectList(queryWrapper);
            for (MOPNeedOrder MOPNeedOrder : MOPNeedOrderList) {
                MOPNeedOrderVO MOPNeedOrderVO = new MOPNeedOrderVO();
                BeanUtils.copyProperties(MOPNeedOrder, MOPNeedOrderVO);
                lineVOList.add(MOPNeedOrderVO);
            }
        }
        return lineVOList;
    }
    @Override
    public ResponseBean<SaveResult> saveNeedOrder(MOPNeedOrderVO mopNeedOrderAO){

        SaveResult saveResult;
        MOPNeedOrder addMOPNeedOrder = new MOPNeedOrder();
        BeanUtils.copyProperties(mopNeedOrderAO, addMOPNeedOrder);
        addMOPNeedOrder.setNeedNo(mopNeedOrderAO.getNeedNo());
        addMOPNeedOrder.setDocNum(com.springcloud.analyticaldata.OrderDetail.getMopPrimaryKey());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addMOPNeedOrder.setCreateDate(dateFormat.format(new Date()));

        List<MOPNeedOrderDt> MOPNeedOrderDts = mopNeedOrderAO.getLines();
        if (MOPNeedOrderDts.size() > 0 && MOPNeedOrderDts != null) {
            for(MOPNeedOrderDt MOPNeedOrderDt : MOPNeedOrderDts){
                MOPNeedOrderDt.setDocNum(addMOPNeedOrder.getDocNum());
            }
            boolean bool = mopNeedOrderDtMapper.batchInsert(MOPNeedOrderDts);
            if(!bool){
                return ResponseBean.fail("订单明细数据异常，录入失败!");
            }
        }else {
            return ResponseBean.fail("无传入订单明细数据，操作失败!");
        }
        mopNeedOrderMapper.insert(addMOPNeedOrder);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyCode", mopNeedOrderAO.getCompanyCode());
        map.put("IsCompulsorySubmission", false);
        map.put("ResultString", "success");
        map.put("docNum", addMOPNeedOrder.getDocNum());
        map.put("createName", mopNeedOrderAO.getNewCreateName());
        map.put("IsRetransmit", false);
        map.put("ResultInt", 0);

        QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("needNo", mopNeedOrderAO.getNeedNo());
        queryWrapper.eq("companyCode", mopNeedOrderAO.getCompanyCode());
        NeedOrder searchModel = needOrderMapper.selectOne(queryWrapper);
        //转换已存在的订货单
        if (searchModel != null) {
            if (mopNeedOrderAO.getIsRetransmit() == true && mopNeedOrderAO.getIsCompulsorySubmission() == false) {
                QueryWrapper<MOPNeedOrder> query = new QueryWrapper<>();
                query.eq("needNo", mopNeedOrderAO.getNeedNo());
                query.eq("companyCode", mopNeedOrderAO.getCompanyCode());
                List<MOPNeedOrder> list = mopNeedOrderMapper.selectList(query);
                map.put("docNum", list.get(0).getDocNum());
                map.put("IsRetransmit", false);
                map.put("IsCompulsorySubmission", false);
            } else if (mopNeedOrderAO.getIsRetransmit() == true && mopNeedOrderAO.getIsCompulsorySubmission() == true) {
                map.put("IsRetransmit", true);
                map.put("IsCompulsorySubmission", true);
            }
        }
        saveResult = needOrderMapper.saveNeedOrder(map);
        if(saveResult.getResultInt()!=0 && saveResult.getResultInt()!=2){
            QueryWrapper<MOPNeedOrder> query = new QueryWrapper<>();
            query.eq("docNum", addMOPNeedOrder.getDocNum());
            mopNeedOrderMapper.delete(query);
            QueryWrapper<MOPNeedOrderDt> queryDt = new QueryWrapper<>();
            queryDt.eq("docNum", addMOPNeedOrder.getDocNum());
            mopNeedOrderDtMapper.delete(queryDt);
        }
        return ResponseBean.ok(saveResult);

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
            MOPNeedOrderList = mopNeedOrderMapper.selectList(queryWrapper);
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
        IPage<MOPNeedOrder> ipage = mopNeedOrderMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<MOPNeedOrder>();
        BeanUtils.copyProperties(ipage, queryResult);
        return queryResult;
    }



    /**
     * 第三方接口使用（下单—获取—解析—上传）
     * @param mopNeedOrderAO
     * @return
     */
    @Transactional
    @Override
    public ResponseBean2<SaveResult> thirdPartyUse(MOPNeedOrderAO mopNeedOrderAO) {
        SaveResult saveResult = new SaveResult();
        MOPNeedOrder addMOPNeedOrder = new MOPNeedOrder();
        Map<String, Object> map = new HashMap<String, Object>();
        addMOPNeedOrder.setDocNum(com.springcloud.analyticaldata.OrderDetail.getMopPrimaryKey());
        map.put("companyCode", mopNeedOrderAO.getCompanyCode());
        map.put("ResultString", "success");
        map.put("createName", mopNeedOrderAO.getCreateName());
        map.put("ResultInt", 0);
        map.put("IsRetransmit", false);
        map.put("IsCompulsorySubmission", false);
        map.put("docNum", addMOPNeedOrder.getDocNum());
        QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("needNo", mopNeedOrderAO.getNeedNo());
        queryWrapper.eq("companyCode", mopNeedOrderAO.getCompanyCode());
        NeedOrder searchModel = needOrderMapper.selectOne(queryWrapper);
        //转换已存在的订货单
        if (searchModel != null) {
            return ResponseBean2.fail("此要货单已存在，请勿重复操作!");
            /*saveResult.setResultString("此订单已存在，请勿重复操作!");*/
        }else{
            BeanUtils.copyProperties(mopNeedOrderAO, addMOPNeedOrder);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            addMOPNeedOrder.setCreateDate(dateFormat.format(new Date()));
            mopNeedOrderMapper.insert(addMOPNeedOrder);
            List<MOPNeedOrderDt> mopNeedOrderDts = mopNeedOrderAO.getLines();
            if (mopNeedOrderDts.size() > 0 && mopNeedOrderDts != null) {
                for (MOPNeedOrderDt mopNeedOrderDt : mopNeedOrderDts) {
                    mopNeedOrderDt.setDocNum(addMOPNeedOrder.getDocNum());
                }
                boolean bool = mopNeedOrderDtMapper.batchInsert(mopNeedOrderDts);
                if (!bool) {
                    return ResponseBean2.fail("要货单明细数据异常，录入失败!");
                }
            }
        }
        saveResult = needOrderMapper.saveNeedOrder(map);
        return ResponseBean2.ok(saveResult.getResultString());
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

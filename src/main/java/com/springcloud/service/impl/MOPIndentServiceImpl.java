package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticaldata.MOPIndentAD;
import com.springcloud.analyticaldata.OrderDetail;
import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.dos.*;
import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.IndentDetailMapper;
import com.springcloud.mapper.IndentMapper;
import com.springcloud.mapper.MOPIndentDtMapper;
import com.springcloud.mapper.MOPIndentMapper;
import com.springcloud.service.MOPIndentService;
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
    @Autowired
    IndentDetailMapper indentDetailMapper;


    @Override
    public MOPIndentVO downloadMOPIndent(String companyCode, String orderNo) {
        MOPIndentVO response = new MOPIndentVO();
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
            response.setCompanyCode(companyCode);
            response.setLines(a.getJsonToBeanThird(dataLine));
        }
        return response;
    }

    @Override
    public ResponseBean<SaveResult> saveMopIndent(MOPIndentAO mopIndentAO) {
        //响应消息
        SaveResult saveResult;
        MOPIndent addMOPIndent = new MOPIndent();
        Map<String, Object> map = new HashMap<String, Object>();
        addMOPIndent.setDocNum(OrderDetail.getMopPrimaryKey());
        map.put("companyCode", mopIndentAO.getCompanyCode());
        map.put("ResultString", "success");
        map.put("createName", mopIndentAO.getNewCreateName());
        map.put("ResultInt", 0);
        map.put("IsRetransmit", false);
        map.put("IsCompulsorySubmission", false);
        map.put("docNum", addMOPIndent.getDocNum());
        BeanUtils.copyProperties(mopIndentAO, addMOPIndent);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addMOPIndent.setCreateDate(new Date());
        List<MOPIndentDt> mopIndentDts = mopIndentAO.getLines();
        if (mopIndentDts.size() > 0 && mopIndentDts != null) {
            for (MOPIndentDt mopIndentDt : mopIndentDts) {
                mopIndentDt.setDocNum(addMOPIndent.getDocNum());
            }
            boolean bool = mopIndentDtMapper.batchInsert(mopIndentDts);
            if (!bool) {
                return ResponseBean.fail("订单明细数据异常，录入失败!");
            }
        }else {
            return ResponseBean.fail("无传入订单明细数据，操作失败!");
        }
        mopIndentMapper.insert(addMOPIndent);
        QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderNo", mopIndentAO.getOrderNo());
        queryWrapper.eq("companyCode", mopIndentAO.getCompanyCode());
        Indent searchModel = indentMapper.selectOne(queryWrapper);
        //转换已存在的订货单
        if (searchModel != null) {
            if (mopIndentAO.getIsRetransmit() == true && mopIndentAO.getIsCompulsorySubmission() == false) {
                QueryWrapper<MOPIndent> query = new QueryWrapper<>();
                query.eq("orderNo", mopIndentAO.getOrderNo());
                query.eq("companyCode", mopIndentAO.getCompanyCode());
                List<MOPIndent> list = mopIndentMapper.selectList(query);
                map.put("docNum", list.get(0).getDocNum());
                map.put("IsRetransmit", false);
                map.put("IsCompulsorySubmission", false);
            } else if (mopIndentAO.getIsRetransmit() == true && mopIndentAO.getIsCompulsorySubmission() == true) {
                map.put("IsRetransmit", true);
                map.put("IsCompulsorySubmission", true);
            }

        }
        saveResult = indentMapper.saveIndent(map);
        return ResponseBean.ok(saveResult);
    }

    /**
     * 第三方接口使用（下单—获取—解析—上传）
     * @param mopIndentAO
     * @return
     */
    @Transactional
    @Override
    public ResponseBean2<SaveResult> thirdPartyUse(MOPIndentAO mopIndentAO) {
        SaveResult saveResult = new SaveResult();

        MOPIndent addMOPIndent = new MOPIndent();
        Indent addIndent = new Indent();
        Map<String, Object> map = new HashMap<String, Object>();
        addIndent.setDocNum(OrderDetail.getMopPrimaryKey());
        addMOPIndent.setDocNum(OrderDetail.getMopPrimaryKey());
        map.put("companyCode", mopIndentAO.getCompanyCode());
        map.put("ResultString", "success");
        map.put("createName", mopIndentAO.getCreateName());
        map.put("ResultInt", 0);
        map.put("IsRetransmit", false);
        map.put("IsCompulsorySubmission", false);
        map.put("docNum", addMOPIndent.getDocNum());
        //检查 MOP 数据重复
        QueryWrapper<MOPIndent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderNo", mopIndentAO.getOrderNo());
        queryWrapper.eq("companyCode", mopIndentAO.getCompanyCode());
        List<MOPIndent> searchModels = mopIndentMapper.selectList(queryWrapper);
        //转换已存在的订货单
        if (searchModels.size()> 0) {
            return ResponseBean2.fail("此订单已存在，请勿重复操作!");
        }else{
            BeanUtils.copyProperties(mopIndentAO, addMOPIndent);
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            addMOPIndent.setCreateDate(new Date());
            mopIndentMapper.insert(addMOPIndent);
            List<MOPIndentDt> mopIndentDts = mopIndentAO.getLines();
            if (mopIndentDts.size() > 0 && mopIndentDts != null) {
                for (MOPIndentDt mopIndentDt : mopIndentDts) {
                    mopIndentDt.setDocNum(addMOPIndent.getDocNum());
                }

                boolean bool = mopIndentDtMapper.batchInsert(mopIndentDts);
                if (!bool) {
                    return ResponseBean2.fail("订单明细数据异常，录入失败!");
                }
            }
        }
        saveResult = mopIndentMapper.saveMopIndent(map);
        return ResponseBean2.ok(saveResult.getResultString());
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


    @Override
    public List<MOPIndentVO> getMOPIndentByOrder(String companyCode, String orderNo) {
        List<MOPIndentVO> mopIndentVO = new ArrayList<>();
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(orderNo)) {
            QueryWrapper<MOPIndent> query = new QueryWrapper<>();
            query.eq("companyCode", companyCode);
            query.eq("orderNo", orderNo).orderByDesc("createDate");
            List<MOPIndent> response = mopIndentMapper.selectList(query);
            if (response != null) {
                for (MOPIndent model : response) {
                    MOPIndentVO vo = new MOPIndentVO();
                    BeanUtils.copyProperties(model, vo);
                    mopIndentVO.add(vo);
                }
            }
        }
        return mopIndentVO;
    }


    @Override
    public MOPIndentVO getMOPIndentByDocNum(String companyCode, String docNum) {
        MOPIndentVO mopIndentVO = new MOPIndentVO();
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(docNum)) {
            QueryWrapper<MOPIndent> query = new QueryWrapper<>();
            query.eq("companyCode", companyCode);
            query.eq("docNum", docNum);
            MOPIndent response = mopIndentMapper.selectOne(query);
            if (response != null) {
                BeanUtils.copyProperties(response, mopIndentVO);
                QueryWrapper<MOPIndentDt> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("docNum", response.getDocNum());
                List<MOPIndentDt> list = mopIndentDtMapper.selectList(queryWrapper);
                if (list.size() > 0 && list != null) {
                    mopIndentVO.setLines(list);
                }
            }
        }
        return mopIndentVO;
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
            httpPost.setHeader("Content-Type", "application/json");
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

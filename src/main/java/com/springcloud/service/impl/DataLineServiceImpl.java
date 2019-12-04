package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.DataLine;
import com.springcloud.bean.dos.LinesItem;
import com.springcloud.bean.dos.NeedOrder;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.query.DataLineQuery;
import com.springcloud.bean.vo.DataLineVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.mapper.DataLineMapper;
import com.springcloud.mapper.LinesItemMapper;
import com.springcloud.mapper.NeedOrderMapper;
import com.springcloud.order.AnalyticalData;
import com.springcloud.order.OrderDetail;
import com.springcloud.service.DataLineService;
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
 * @ClassName : DataLineServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */

@Transactional
@Service
public class DataLineServiceImpl implements DataLineService {

    private static final String url = "http://test.try-shopping.com/ts-openapi";
    private static final String key = "285e11c1e83a4094b35cc3cf320ad820";

    @Autowired
    DataLineMapper dataLineMapper;
    @Autowired
    LinesItemMapper linesItemMapper;
    @Autowired
    NeedOrderMapper needOrderMapper;

    @Override
    public Boolean insert(DataLine dataLine) {
        return dataLineMapper.insert(dataLine) > 0;
    }

    @Override
    public List<DataLineVO> getdataline(String companyCode, String needorderNo) {
        List<DataLineVO> lineVOList = new ArrayList<>();
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
        AnalyticalData a = new AnalyticalData();
        DataLineVO bean = a.getJsonToBeanSecond(resultJson);


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
    public List<DataLineVO> getLocalInfo(String companyCode, String needOrderNo) {
        List<DataLineVO> lineVOList = new ArrayList<>();
        List<DataLine> dataLineList;
        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(needOrderNo)) {
            QueryWrapper<DataLine> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("companyCode", companyCode);
                queryWrapper.like("needNo", needOrderNo);
            dataLineList = dataLineMapper.selectList(queryWrapper);
            for (DataLine dataLine : dataLineList) {
                DataLineVO dataLineVO = new DataLineVO();
                BeanUtils.copyProperties(dataLine, dataLineVO);
                lineVOList.add(dataLineVO);
            }
        }
        return lineVOList;
    }


    @Override
    public Boolean saveDataLine(DataLineVO dataLineVO) {
        DataLine addDataLine = new DataLine();
        BeanUtils.copyProperties(dataLineVO, addDataLine);
        addDataLine.setDocNum(OrderDetail.getMopPrimaryKey());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addDataLine.setCreateDate(dateFormat.format(new Date()));
        List<LinesItem> linesItems = dataLineVO.getLines();
        if (linesItems.size() > 0 && linesItems != null) {
            for(LinesItem linesItem : linesItems){
                linesItem.setDocNum(addDataLine.getDocNum());
                //测试数据
                linesItem.setItemQuantity(1);
            }
            boolean bool = linesItemMapper.batchInsert(linesItems);
            if(!bool){
                return false;
            }
        }
        QueryWrapper<NeedOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("needNo",dataLineVO.getNeedNo());
        queryWrapper.eq("companyCode",dataLineVO.getCompanyCode());
        NeedOrder searchModel = needOrderMapper.selectOne(queryWrapper);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyCode", dataLineVO.getCompanyCode());
            map.put("IsCompulsorySubmission", false);
            map.put("ResultString", "aa");
            map.put("docNum", addDataLine.getDocNum());
            map.put("createName", "admin");
            map.put("IsRetransmit", false);
            map.put("ResultInt", 0);
            SaveResult saveResult = needOrderMapper.saveNeedOrder(map);
            if(saveResult==null){
                return false;
            }

        return dataLineMapper.insert(addDataLine) > 0;
    }

    @Override
    public List<DataLineVO> list(DataLineQuery query) {
        List<DataLineVO> lineVOList = new ArrayList<>();
        List<DataLine> dataLineList;
        if (query != null) {
            QueryWrapper<DataLine> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (StringUtils.isNotBlank(query.getNeedOrderNo())) {
                queryWrapper.like("needNo", query.getNeedOrderNo());
            }
            dataLineList = dataLineMapper.selectList(queryWrapper);
            for (DataLine dataLine : dataLineList) {
                DataLineVO dataLineVO = new DataLineVO();
                BeanUtils.copyProperties(dataLine, dataLineVO);
                lineVOList.add(dataLineVO);
            }
        }
        return lineVOList;
    }

    @Override
    public QueryResult<DataLineVO> page(long page, long size, DataLineQuery query) {
        QueryWrapper<DataLine> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryEntity(query, queryWrapper);

        Page<DataLine> pageinfo = new Page(page, size);
        pageinfo.setSearchCount(true);
        IPage<DataLine> ipage = dataLineMapper.selectPage(pageinfo, queryWrapper);

        QueryResult queryResult = new QueryResult<DataLine>();
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
    private QueryWrapper<DataLine> queryEntity(DataLineQuery query, QueryWrapper<DataLine> queryWrapper) {
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyCode())) {
                queryWrapper.eq("companyCode", query.getCompanyCode());
            }
            if (!StringUtils.isEmpty(query.getNeedOrderNo())) {
                queryWrapper.like("needOrderNo", query.getNeedOrderNo());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }
}

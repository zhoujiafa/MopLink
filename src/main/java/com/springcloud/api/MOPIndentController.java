package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.analyticaldata.MOPIndentAD;
import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.MOPIndentDt;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.service.CompanyDictService;
import com.springcloud.service.MOPIndentService;
import com.springcloud.util.PageResult;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @ClassName : MOPIndentController
 * @Description : 订货单信息接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/MOPIndent")
public class MOPIndentController {

    @Autowired
    MOPIndentService mopIndentService;
    @Autowired
    CompanyDictService companyDictService;

    @ApiOperation(value = "根据companyCode和orderNo获取信息", notes = "根据companyCode和orderNo获取信息")
    @PostMapping("/downloadMOPIndent")
    public ResponseBean<MOPIndentVO> getdataline(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String orderNo = data.get("orderNo").toString();
        /*String companyCode = "0181";
        String orderNo = "DO8191209190458085"*/;//164489302C4DC802F13D3CC9AFB85C0A
        //String orderNo = "DO6191211170417270"/0D4074059F042A6FC5CFE5CB34C447DC;
        MOPIndentVO mopIndentVO = mopIndentService.downloadMOPIndent(companyCode, orderNo);
        return ResponseBean.ok(mopIndentVO);
    }

    @ApiOperation(value = "根据companyName和orderNo获取信息", notes = "根据companyName和orderNo获取信息")
    @PostMapping("/downloadMOPIndentFromApp")
    public ResponseBean<MOPIndentVO> getdatalineFromApp(@RequestBody Map data) {
        String companyName = data.get("companyName").toString();
        String orderNo = data.get("orderNo").toString();

        CompanyDictQuery query = new CompanyDictQuery();
        query.setCompanyName(companyName);
        String companyCode = null;
        QueryResult<CompanyDict> queryResult = companyDictService.page(1,1,query);
        if(queryResult.getRecords().size()>0){
            companyCode = queryResult.getRecords().get(0).getCompanyCode();
        }

        MOPIndentVO mopIndentVO = mopIndentService.downloadMOPIndent(companyCode, orderNo);
        return ResponseBean.ok(mopIndentVO);
    }


    @ApiOperation(value = "保存下载信息", notes = "保存下载信息")
    @PostMapping("/save")
    public ResponseBean<Boolean> save(@RequestBody MOPIndentAO mopIndentAO) {

        return ResponseBean.ok(mopIndentService.saveMopIndent(mopIndentAO));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<MOPIndentVO>> page(@PathVariable int page, @PathVariable int size, MOPIndentQuery query) {

        return ResponseBean.ok(mopIndentService.page(page, size, query));
    }

   /* @ApiOperation(value = "列表信息", notes = "列表信息")
    @GetMapping("/list")
    public ResponseBean<List<MOPIndentVO>> list(MOPIndentQuery query) {

        return ResponseBean.ok(mopIndentService.list(query));
    }*/

    /*因为订单可以重复提交，所以可能会产生有多条原订单号相同的数据（根据需求需要分为按新订单号和就订单号查询）*/
    @ApiOperation(value = "根据公司编码和旧订单号查询本地信息", notes = "根据公司编码和旧订单号查询本地信息")
    @PostMapping("/getMOPIndentByOrder")
    public ResponseBean<List<MOPIndentVO>> getMOPIndentByOrder(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String orderNo = data.get("orderNo").toString();
        List<MOPIndentVO> mopIndentVO = mopIndentService.getMOPIndentByOrder(companyCode, orderNo);
        return ResponseBean.ok(mopIndentVO);
    }

    @ApiOperation(value = "根据公司编码和旧订单号查询本地信息", notes = "根据公司编码和旧订单号查询本地信息")
    @PostMapping("/getMOPIndentByDocNum")
    public ResponseBean<MOPIndentVO> getMOPIndentByDocNum(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String docNum = data.get("docNum").toString();
        MOPIndentVO mopIndentVO = mopIndentService.getMOPIndentByDocNum(companyCode, docNum);
        return ResponseBean.ok(mopIndentVO);
    }

    @Transactional
    @ApiOperation(value = "提供第三方接口（第三方下订单直接上传单据：下单事件）", notes = "提供第三方接口（第三方下单直接上传单据）")
    @RequestMapping(value = "/PlaceAnMOPIndent", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2 PlaceAnOrder(HttpServletResponse response, @RequestBody Map data) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseBean2 responseBean2 = new ResponseBean2();
        /*根据提供单号调用第三方接口获取数据*/
        String companyCode = data.get("companyCode").toString();
        //map里不嵌套
        //String orderNo = data.get("orderNo").toString();
        //String sign = "AD60B1A79F4D0EA9BD6B8020E10A89E4"
        //map里嵌套map
        /*{
            "companyCode":"0181",
                "sign":"164489302C4DC802F13D3CC9AFB85C0A",
                "dataJson":{
            "orderNo":"DO8191209190458085"
        }
        }*/
        JSONObject dataJson = (JSONObject) JSON.toJSON(data.get("dataJson"));
        String orderNo = dataJson.getString("orderNo");
        String sign = data.get("sign").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        //嵌套sign
        //String sign = "BBF0605CCAA033E59A70175885C04431";
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appId);
        params.put("companyCode", companyCode);
        params.put("dataJson", map);
        /*params.put("orderNo", orderNo);*/
        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        /*String MD5Str = MD5Util.getMD5(newSign);*/

        MOPIndentVO mopIndentVO = mopIndentService.downloadMOPIndent(companyCode, orderNo);
        if (!newSign.equals(sign)) {
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
            /*saveResult.setResultString("签名无效，请检查和核实签名信息");*/
        }
        if (mopIndentVO != null) {
            MOPIndentAO mopIndentAO = new MOPIndentAO();
            BeanUtils.copyProperties(mopIndentVO, mopIndentAO);
            responseBean2 = mopIndentService.thirdPartyUse(mopIndentAO);
            if (responseBean2.getResultInt() != 0) {
                responseBean2.setResultInt(-1);
            }
        }

        return responseBean2;
    }


    @Transactional
    @ApiOperation(value = "提供第三方接口（第三方下订单直接上传单据：下单事件）")
    @RequestMapping(value = "/PlaceAnMOPIndents", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2 PlaceAnOrders(HttpServletResponse response, @RequestBody Map data) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseBean2 responseBean2 = new ResponseBean2();
        String companyCode = data.get("companyCode").toString();
        JSONObject dataJson = JSONObject.parseObject(data.get("dataJson").toString());
        //postman测试
        //JSONObject dataJson = (JSONObject) JSON.toJSON(data.get("dataJson"));


        String orderNo = dataJson.getString("orderNo");
        String sign = data.get("sign").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("companyName", dataJson.getString("companyName"));
        map.put("deliveryDate", dataJson.getString("deliveryDate"));
        map.put("orderType", dataJson.getString("orderType"));
        map.put("orderTypeName", dataJson.getString("orderTypeName"));
        map.put("payStatusName", dataJson.getString("payStatusName"));
        map.put("orderStatus", dataJson.getString("orderStatus"));
        map.put("orderStatusName", dataJson.getString("orderStatusName"));
        map.put("createDate", dataJson.getString("createDate"));
        map.put("createName", dataJson.getString("createName"));
        //添加经销商编码
        map.put("distrCode", dataJson.getString("distrCode"));

        //JSONArray array = dataJson.getJSONArray(dataJson.getString("array"));
        JSONArray array = dataJson.getJSONArray("array");

        //JSONObject line = JSONObject.parseObject(dataJson.getString("lines"));
        //JSONArray array = new JSONArray();
        //array.add(line);
        List<Map> mapList = new LinkedList<>();
        for (Object maps:array){
            Map<String, Object> linesMaps = new HashMap<>();
            linesMaps = (Map<String, Object>) maps;
            mapList.add(linesMaps);
        }

        map.put("array", mapList);

        //map.put("lines", array);
        String json=JSON.toJSONString(map);
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        Map<String, Object> params = new HashMap<>();
        params.put("appId", appId);
        params.put("companyCode", companyCode);
        params.put("dataJson", json);
        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        /*String MD5Str = MD5Util.getMD5(newSign);*/

        MOPIndentVO mopIndentVOs = new MOPIndentVO();
        mopIndentVOs.setCompanyName(dataJson.getString("companyName"));
        mopIndentVOs.setCompanyCode(companyCode);
        mopIndentVOs.setOrderNo(dataJson.getString("orderNo"));
        mopIndentVOs.setDeliveryDate(dataJson.getString("deliveryDate"));
        mopIndentVOs.setOrderType(dataJson.getString("orderType"));
        mopIndentVOs.setOrderTypeName(dataJson.getString("orderTypeName"));
        mopIndentVOs.setPayStatusName(dataJson.getString("payStatusName"));
        mopIndentVOs.setOrderStatus(dataJson.getString("orderStatus"));
        mopIndentVOs.setOrderStatusName(dataJson.getString("orderStatusName"));
        mopIndentVOs.setCreateDate(dataJson.getString("createDate"));
        mopIndentVOs.setCreateName(dataJson.getString("createName"));

        mopIndentVOs.setDistrCode(dataJson.getString("distrCode"));


        List<MOPIndentDt> mopIndentDts = new ArrayList<>();
        //JSONArray dataLine = dataJson.getJSONArray(dataJson.getString("lines"));


        mopIndentDts = MOPIndentAD.getJsonArrayItem(array);

        mopIndentVOs.setLines(mopIndentDts);

        if (!newSign.equals(sign)) {
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
            /*saveResult.setResultString("签名无效，请检查和核实签名信息");*/
        }
        if (mopIndentVOs != null) {
            MOPIndentAO mopIndentAO = new MOPIndentAO();
            BeanUtils.copyProperties(mopIndentVOs, mopIndentAO);
            responseBean2 = mopIndentService.thirdPartyUse(mopIndentAO);
            if (responseBean2.getResultInt() != 0) {
                responseBean2.setResultInt(-1);
            }
        }

        return responseBean2;
    }
}
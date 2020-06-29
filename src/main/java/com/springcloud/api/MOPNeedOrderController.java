package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.ao.MOPNeedOrderAO;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.service.MOPNeedOrderService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/MOPNeedOrder")
public class MOPNeedOrderController {

    @Autowired
    MOPNeedOrderService MOPNeedOrderService;

    @ApiOperation(value = "根据companyCode和appId下载信息", notes = "根据companyCode和appId下载信息")
    @PostMapping("/downloadMOPNeedOrder")
    public ResponseBean<MOPNeedOrderVO> getdataline(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needOrderNo").toString();
        /*String companyCode = "0324";
        String needOrderNo = "303248530001";*/
        MOPNeedOrderVO MOPNeedOrderVO = MOPNeedOrderService.getdataline(companyCode,needOrderNo);
        return ResponseBean.ok(MOPNeedOrderVO);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<MOPNeedOrderVO>> page(@PathVariable int page, @PathVariable int size, MOPNeedOrderQuery query) {

        return ResponseBean.ok(MOPNeedOrderService.page(page,size,query));
    }

    @ApiOperation(value = "列表信息", notes = "列表信息")
    @GetMapping("/list")
    public ResponseBean<List<MOPNeedOrderVO>> list(MOPNeedOrderQuery query) {

        return ResponseBean.ok(MOPNeedOrderService.list(query));
    }

    @ApiOperation(value = "保存下载信息", notes = "保存下载信息")
    @PostMapping("/save")
    public ResponseBean<Boolean> save(@RequestBody MOPNeedOrderVO lineVO){

        return ResponseBean.ok(MOPNeedOrderService.saveNeedOrder(lineVO));
    }

    @ApiOperation(value = "根据companyCode和appId查询本地信息", notes = "根据companyCode和appId查询本地信息")
    @GetMapping("/getMOPNeedOrderByNeedNo")
    public ResponseBean<MOPNeedOrderVO> getMOPNeedOrderByNeedNo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needNo").toString();
        List<MOPNeedOrderVO> MOPNeedOrderVO = MOPNeedOrderService.getLocalInfo(companyCode,needOrderNo);
        return ResponseBean.ok(MOPNeedOrderVO);
    }


    @Transactional
    @ApiOperation(value = "提供第三方接口（第三方下要货单直接上传单据：下单事件）", notes = "提供第三方接口（第三方下单直接上传单据）")
    @RequestMapping(value = "/PlaceAnMOPNeedOrder", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2 PlaceAnOrder(HttpServletResponse response, @RequestBody Map data) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseBean2 responseBean2 = new ResponseBean2();
        /*根据提供单号调用第三方接口获取数据*/
        String companyCode = data.get("companyCode").toString();

        JSONObject dataJson = (JSONObject) JSON.toJSON(data.get("dataJson"));
        String needNo = dataJson.getString("needOrderNo");
        String sign = data.get("sign").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("needNo", needNo);
        //String needNo = data.get("needOrderNo").toString();
        //String sign = data.get("sign").toString();
        //String sign = "AD60B1A79F4D0EA9BD6B8020E10A89E4";
        //String sign = "91E2076E31D6127848015E539EDD936B(map嵌套)";
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appId);
        params.put("companyCode", companyCode);
        params.put("dataJson", map);

        //params.put("needNo", needNo);

        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        /*String MD5Str = MD5Util.getMD5(newSign);*/
        if (!newSign.equals(sign)) {
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
            /*saveResult.setResultString("签名无效，请检查和核实签名信息");*/
        }
        MOPNeedOrderVO mopNeedOrderVO = MOPNeedOrderService.getdataline(companyCode, needNo);
        if (mopNeedOrderVO != null) {
            MOPNeedOrderAO mopIndentAO = new MOPNeedOrderAO();
            BeanUtils.copyProperties(mopNeedOrderVO, mopIndentAO);
            responseBean2 = MOPNeedOrderService.thirdPartyUse(mopIndentAO);
            if (responseBean2.getResultInt() != 0) {
                responseBean2.setResultInt(-1);
            }
        }
        return responseBean2;
    }
    
}

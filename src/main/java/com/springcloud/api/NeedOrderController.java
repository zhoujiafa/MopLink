package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.NeedOrder;
import com.springcloud.bean.query.NeedOrderQuery;
import com.springcloud.bean.vo.IndentVO;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.service.NeedOrderService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : NeedOrderController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/needOrder")
public class NeedOrderController {

    @Autowired
    NeedOrderService needOrderService;


    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean2<PageResult<NeedOrderVO>> page(@PathVariable int page, @PathVariable int size, NeedOrderQuery query) {
        return ResponseBean2.ok(needOrderService.page(page,size,query));
    }



    @ApiOperation(value = "根据companyCode和needOrderNo查询本地信息", notes = "根据companyCode和needOrderNo查询本地信息")
    @PostMapping("/getNeedOrderByNeedNo")
    public ResponseBean2<NeedOrderVO> getLocalInfo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needNo").toString();
        /*String companyCode = "0324";
        String needOrderNo = "303248530001";*/
        NeedOrderVO dataLineVO = needOrderService.getNeedOrderByNeedNo(companyCode,needOrderNo);
        return ResponseBean2.ok(dataLineVO);
    }


    @ApiOperation(value = "根据companyCode和needOrderNo查询本地信息", notes = "根据companyCode和needOrderNo查询本地信息")
    @PostMapping("/save")
    public SaveResult save(Map<String, Object> map) {
        return needOrderService.saveNeedOrder(map);
    }


    @ApiOperation(value = "提供第三方信息接口（提供要货单）", notes = "提供第三方信息接口")
    @RequestMapping(value = "/detail",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2<NeedOrderVO> detail(HttpServletResponse response, @RequestBody Map data) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //String sign = 0D2DB74C5AA719A2F454C52256BD684B;
        //String sign = C64EF24FEF4CBD5A6BB7087E73783868（嵌套）;
        String sign = data.get("sign").toString();
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        String companyCode = data.get("companyCode").toString();
        JSONObject dataJson = (JSONObject) JSON.toJSON(data.get("dataJson"));
        String needOrderNo = dataJson.getString("needOrderNo");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("needOrderNo", needOrderNo);
        //String needOrderNo = data.get("needOrderNo").toString();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appId);
        params.put("companyCode", companyCode);
        params.put("dataJson", map);

        //params.put("needOrderNo", needOrderNo);
        NeedOrderVO model = needOrderService.getNeedOrderByNeedNo(companyCode,needOrderNo);
        if(model==null || StringUtils.isBlank(model.getDocNum())){
            return ResponseBean2.fail("无此订单信息，请检查订单号或门店编码是否错误");
        }
        //String newSign = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String newSign = JSON.toJSON(SignUtil.sign(params, key).get("sign")).toString();
        /*String MD5Str = MD5Util.getMD5(newSign);*/
        if(!newSign.equals(sign)){
            return ResponseBean2.fail("签名无效，请检查和核实签名信息");
        }

        return ResponseBean2.ok(model);
    }
}

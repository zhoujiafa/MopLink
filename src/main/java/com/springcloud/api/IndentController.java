package com.springcloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.query.IndentQuery;
import com.springcloud.bean.vo.IndentVO;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.service.IndentService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : NeedOrderController
 * @Description : 数据访问接口2
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/indent")
public class IndentController {

    @Autowired
    IndentService indentService;


    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean2<PageResult<IndentVO>> page(@PathVariable int page, @PathVariable int size, IndentQuery query) {
        return ResponseBean2.ok(indentService.page(page,size,query));
    }


    @ApiOperation(value = "根据companyCode和orderNo查询本地信息", notes = "根据companyCode和orderNo查询本地信息")
    @PostMapping("/getLocalInfo")
    public ResponseBean2<List<IndentVO>> getLocalInfo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String orderNo = data.get("orderNo").toString();
        //String companyCode = "0181";
        //String orderNo = "121720190007";
        List<IndentVO> list = indentService.list(companyCode,orderNo);
        return ResponseBean2.ok(list);
    }



    @ApiOperation(value = "提供第三方信息接口（提供订货单）", notes = "提供第三方信息接口")
    @RequestMapping(value = "/detail",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean2<IndentVO> detail(HttpServletResponse response,@RequestBody Map data) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String sign = data.get("sign").toString();
        String key = "A1DF1E59090EAFF035C3091003A05CFC";
        String appId = "94916115E6732C11D5984075C4DB588B";
        //String sign = "230763AAB6866D3C8F98DF27E4A7E944";
        //String sign = "38C2B61F305D0BA50710CBA36AC90593（嵌套）";
        /*String companyCode = "0181";
        String orderNo = "121720190007";*/
        String companyCode = data.get("companyCode").toString();

        JSONObject dataJson = (JSONObject) JSON.toJSON(data.get("dataJson"));
        String orderNo = dataJson.getString("orderNo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        //String orderNo = data.get("orderNo").toString();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appId);
        params.put("companyCode", companyCode);
        params.put("dataJson", map);
        //params.put("orderNo", orderNo);

        IndentVO model = indentService.getOne(companyCode,orderNo);
        if(model==null){
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

package com.springcloud.api;

import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.service.MOPIndentService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : MOPIndentController
 * @Description : 订货单信息接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/mopIndent")
public class MOPIndentController {

    @Autowired
    MOPIndentService mopIndentService;

    @ApiOperation(value = "根据companyCode和appId获取信息", notes = "根据companyCode和appId获取信息")
    @PostMapping("/getMOPIndent")
    public ResponseBean<List<MOPIndentVO>> getdataline(@RequestBody Map data) {
        /*String companyCode = data.get("companyCode").toString();
        String orderNo = data.get("orderNo").toString();*/
        String companyCode = "0181";
        String orderNo = "301812200202";
        List<MOPIndentVO> MOPIndentVO = mopIndentService.getmopIndent(companyCode,orderNo);
        return ResponseBean.ok(MOPIndentVO);
    }

 /*   @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean<PageResult<MOPIndentVO>> page(@PathVariable int page, @PathVariable int size, MOPIndentQuery query) {

        return ResponseBean.ok(mopIndentService.page(page,size,query));
    }

    @ApiOperation(value = "列表信息", notes = "列表信息")
    @GetMapping("/list")
    public ResponseBean<List<MOPIndentVO>> list(MOPIndentQuery query) {

        return ResponseBean.ok(mopIndentService.list(query));
    }

    @ApiOperation(value = "保存下载信息", notes = "保存下载信息")
    @PostMapping("/save")
    public ResponseBean<Boolean> save(@RequestBody MOPIndentVO lineVO){

        return ResponseBean.ok(mopIndentService.saveDataLine(lineVO));
    }*/

    /*@ApiOperation(value = "根据companyCode和appId查询本地信息", notes = "根据companyCode和appId查询本地信息")
    @PostMapping("/getMOPIndentfromLocal")
    public ResponseBean<List<MOPIndentVO>> getLocalInfo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needOrderNo").toString();
        List<MOPIndentVO> MOPIndentVO = mopIndentService.getLocalInfo(companyCode,needOrderNo);
        return ResponseBean.ok(MOPIndentVO);
    }*/

}

package com.springcloud.api;

import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.service.MOPNeedOrderService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : DataLineController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/mopNeedorder")
public class MOPNeedOrderController {

    @Autowired
    MOPNeedOrderService MOPNeedOrderService;

    @ApiOperation(value = "根据companyCode和appId下载信息", notes = "根据companyCode和appId下载信息")
    @PostMapping("/getMopNeedorder")
    public ResponseBean<List<MOPNeedOrderVO>> getdataline(@RequestBody Map data) {
        /*String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needOrderNo").toString();*/
        String companyCode = "0324";
        String needOrderNo = "303248530001";
        List<MOPNeedOrderVO> MOPNeedOrderVO = MOPNeedOrderService.getdataline(companyCode,needOrderNo);
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

        return ResponseBean.ok(MOPNeedOrderService.saveDataLine(lineVO));
    }

    @ApiOperation(value = "根据companyCode和appId查询本地信息", notes = "根据companyCode和appId查询本地信息")
    @PostMapping("/getone")
    public ResponseBean<List<MOPNeedOrderVO>> getLocalInfo(@RequestBody Map data) {
        String companyCode = data.get("companyCode").toString();
        String needOrderNo = data.get("needOrderNo").toString();
        List<MOPNeedOrderVO> MOPNeedOrderVO = MOPNeedOrderService.getLocalInfo(companyCode,needOrderNo);
        return ResponseBean.ok(MOPNeedOrderVO);
    }

}

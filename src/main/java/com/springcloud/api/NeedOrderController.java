package com.springcloud.api;

import com.springcloud.bean.query.NeedOrderQuery;
import com.springcloud.bean.vo.NeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.service.NeedOrderService;
import com.springcloud.util.PageResult;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : NeedOrderController
 * @Description : 数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/needorder")
public class NeedOrderController {

    @Autowired
    NeedOrderService needOrderService;


    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean2<PageResult<NeedOrderVO>> page(@PathVariable int page, @PathVariable int size, NeedOrderQuery query) {
        return ResponseBean2.ok(needOrderService.page(page,size,query));
    }



    @ApiOperation(value = "根据companyCode和needOrderNo查询本地信息", notes = "根据companyCode和needOrderNo查询本地信息")
    @PostMapping("/getLocalInfo")
    public ResponseBean2<List<NeedOrderVO>> getLocalInfo(@RequestBody Map data) {
        //String companyCode = data.get("companyCode").toString();
        //String needOrderNo = data.get("needOrderNo").toString();
        String companyCode = "0324";
        String needOrderNo = "303248530001";
        List<NeedOrderVO> dataLineVO = needOrderService.list(companyCode,needOrderNo);
        return ResponseBean2.ok(dataLineVO);
    }


    @ApiOperation(value = "根据companyCode和needOrderNo查询本地信息", notes = "根据companyCode和needOrderNo查询本地信息")
    @PostMapping("/save")
    public SaveResult save(Map<String, Object> map) {
        return needOrderService.saveNeedOrder(map);
    }

}

package com.springcloud.api;

import com.springcloud.bean.vo.XsProductInfoVO;
import com.springcloud.service.XsProductInfoService;
import com.springcloud.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @ClassName : XsProductInfoController
* @Description : 讯商商品下单接口
* @Author : Joe
* @Date: 2020-04-30 13:50
*/
@RestController
@RequestMapping("/xsprodoctinfo")
public class XsProductInfoController {

    @Autowired
    XsProductInfoService xsProdoctInfoService;

    @ApiOperation(value = "根据款号查询商品价格", notes = "根据款号查询商品价格")
    @PostMapping("/list")
    public ResponseBean<List<XsProductInfoVO>> list(String sectionNumber) {
        return ResponseBean.ok(xsProdoctInfoService.list(sectionNumber));
    }

    @ApiOperation(value = "查询所有款号信息", notes = "查询所有款号信息")
    @PostMapping("/itemCodeList")
    public ResponseBean<List<String>> itemCodeList() {
        return ResponseBean.ok(xsProdoctInfoService.allItemCode());
    }

    @ApiOperation(value = "根据款号查询商品详情", notes = "根据款号查询商品详情")
    @PostMapping("/detailByCode")
    public ResponseBean<XsProductInfoVO> detailByCode(String sectionNumber) {
        return ResponseBean.ok(xsProdoctInfoService.detailByCode(sectionNumber));
    }

}

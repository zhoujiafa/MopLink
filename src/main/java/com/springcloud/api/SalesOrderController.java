package com.springcloud.api;

import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.vo.SalesOrderVO;
import com.springcloud.bean.vo.UserVO;
import com.springcloud.service.SalesOrderService;
import com.springcloud.service.UserService;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName : UserController
 * @Description : 用户数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/salesorderc")
public class SalesOrderController {

    @Autowired
    SalesOrderService salesOrderService;


    @ApiOperation(value = "销售订单明细", notes = "销售订单明细")
    @PostMapping("/detail")
    public ResponseBean2<SalesOrderVO> getSaleStock(@RequestBody Map data) {

        return ResponseBean2.ok(salesOrderService.getSaleStock(data));
    }

}

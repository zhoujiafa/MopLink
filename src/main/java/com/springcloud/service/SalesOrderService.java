package com.springcloud.service;

import com.springcloud.bean.vo.SalesOrderVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
/**
* @ClassName : SalesOrderService
* @Description :
* @Author : Joe
* @Date: 2020/3/7 9:31
*/

public interface SalesOrderService {

    /**
     * 获取销售单明细
     * @param data
     * @return
     */
    SalesOrderVO getSaleStock(@RequestBody Map data);
}

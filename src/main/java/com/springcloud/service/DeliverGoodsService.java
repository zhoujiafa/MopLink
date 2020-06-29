package com.springcloud.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.ao.CompanyDictAO;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.vo.CompanyDictVO;
import com.springcloud.util.QueryResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @ClassName : DeliverGoodsService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
@DS("jzn_wms")
public interface DeliverGoodsService {

    /**
     * listByneedNo
     * @param orderNo
     * @return
     */
    List<DeliverGoods> listByneedNo(String orderNo);

}

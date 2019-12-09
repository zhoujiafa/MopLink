package com.springcloud.service;

import com.springcloud.bean.dos.MOPNeedOrderDt;

import java.util.List;
/**
* @ClassName : MOPNeedOrderDtService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 14:20
*/

public interface MOPNeedOrderDtService {

    Boolean batchInsert(List<MOPNeedOrderDt> MOPNeedOrderDtList);


}

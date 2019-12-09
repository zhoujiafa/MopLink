package com.springcloud.service;

import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.util.QueryResult;

import java.util.List;

/**
* @ClassName : MOPIndentService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface MOPIndentService {


    List<MOPIndentVO> getmopIndent(String companyCode, String orderNo);

    /*List<MOPIndentVO> getLocalInfo(String companyCode, String orderNo);

    Boolean savemopIndent(MOPIndentAO mopIndentAO);

    List<MOPIndentVO> list(MOPIndentQuery query);

    QueryResult<MOPIndentVO> page(long page, long size, MOPIndentQuery query);*/

}

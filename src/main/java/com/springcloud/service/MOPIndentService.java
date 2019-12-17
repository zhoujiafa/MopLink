package com.springcloud.service;

import com.springcloud.bean.ao.MOPIndentAO;
import com.springcloud.bean.dos.MOPNeedOrder;
import com.springcloud.bean.query.MOPIndentQuery;
import com.springcloud.bean.query.MOPNeedOrderQuery;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.MOPNeedOrderVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.util.QueryResult;
import com.springcloud.util.ResponseBean;

import java.util.List;

/**
* @ClassName : MOPIndentService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface MOPIndentService {


    //ResponseBean<List<MOPIndentVO>> getmopIndent(String companyCode, String orderNo);

    List<MOPIndentVO> getmopIndent(String companyCode, String orderNo);



    ResponseBean<SaveResult> saveMopIndent(MOPIndentAO mopIndentAO);


    QueryResult<MOPIndentVO> page(long page, long size, MOPIndentQuery query);

    /*List<MOPIndentVO> getLocalInfo(String companyCode, String orderNo);



    List<MOPIndentVO> list(MOPIndentQuery query);

   */

}

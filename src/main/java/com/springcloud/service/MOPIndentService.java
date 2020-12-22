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
import com.springcloud.util.ResponseBean2;

import java.util.List;

/**
* @ClassName : MOPIndentService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface MOPIndentService {


    //ResponseBean<List<MOPIndentVO>> getmopIndent(String companyCode, String orderNo);

    MOPIndentVO downloadMOPIndent(String companyCode, String orderNo);

    ResponseBean<SaveResult> saveMopIndent(MOPIndentAO mopIndentAO);

    ResponseBean2<SaveResult> thirdPartyUse(MOPIndentAO mopIndentAO);

    QueryResult<MOPIndentVO> page(long page, long size, MOPIndentQuery query);

    List<MOPIndentVO> getMOPIndentByOrder(String companyCode, String orderNo);

    MOPIndentVO getMOPIndentByDocNum(String companyCode, String docNum);

    MOPIndentVO getdistrCode(String orderNo);



}

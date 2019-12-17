package com.springcloud.service;

import com.springcloud.bean.query.IndentQuery;
import com.springcloud.bean.vo.IndentVO;
import com.springcloud.bean.vo.SaveResult;
import com.springcloud.util.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
public interface IndentService {


    //SaveResult saveIndent(Map<String, Object> map);


    List<IndentVO> list(String companyCode, String orderNo);

    IndentVO getOne(String companyCode, String orderNo);


    QueryResult<IndentVO> page(long page, long size, IndentQuery query);
}

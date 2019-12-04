package com.springcloud.service;

import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.DataLine;
import com.springcloud.bean.query.CompanyDictQuery;
import com.springcloud.bean.query.DataLineQuery;
import com.springcloud.bean.vo.DataLineVO;
import com.springcloud.bean.vo.GoodsDictVO;
import com.springcloud.util.QueryResult;

import java.util.List;

/**
* @ClassName : DataLineService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
public interface DataLineService {


    /**
     * 新增数据
     * @param dataLine
     * @return
     */
    Boolean insert(DataLine dataLine);

    List<DataLineVO> getdataline(String companyCode, String needOrderNo);

    List<DataLineVO> getLocalInfo(String companyCode, String needOrderNo);

    Boolean saveDataLine(DataLineVO dataLineVO);

    List<DataLineVO> list(DataLineQuery query);

    QueryResult<DataLineVO> page(long page, long size, DataLineQuery query);

}

package com.springcloud.service;

import com.springcloud.bean.dos.DataLine;
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



}

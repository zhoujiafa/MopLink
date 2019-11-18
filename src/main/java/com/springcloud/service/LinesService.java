package com.springcloud.service;

import com.springcloud.bean.dos.LinesItem;

import java.util.List;
/**
* @ClassName : LinesService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 14:20
*/

public interface LinesService {

    Boolean batchInsert(List<LinesItem> linesItemList);


}

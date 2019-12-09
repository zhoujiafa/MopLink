package com.springcloud.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.MOPNeedOrderDt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
* @ClassName : LinesItemMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/22 13:32
*/

@DS(value = "stock")
@Mapper
public interface MOPNeedOrderDtMapper extends BaseMapper<MOPNeedOrderDt> {


    Boolean batchInsert(List<MOPNeedOrderDt> MOPNeedOrderDtList);



}

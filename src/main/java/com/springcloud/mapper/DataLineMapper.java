package com.springcloud.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.DataLine;
import org.apache.ibatis.annotations.Mapper;
/**
* @ClassName : DataLineMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/22 13:32
*/

@DS(value = "stock")
@Mapper
public interface DataLineMapper extends BaseMapper<DataLine> {

    //int insert(DataLine dataLine);
}

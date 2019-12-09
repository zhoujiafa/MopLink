package com.springcloud.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.MOPNeedOrder;
import org.apache.ibatis.annotations.Mapper;
/**
* @ClassName : DataLineMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/22 13:32
*/

@DS(value = "stock")
@Mapper
public interface MOPNeedOrderMapper extends BaseMapper<MOPNeedOrder> {

    //int insert(DataLine dataLine);
}

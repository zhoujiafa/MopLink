package com.springcloud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.DataLine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataLineMapper extends BaseMapper<DataLine> {

    //int insert(DataLine dataLine);
}

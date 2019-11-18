package com.springcloud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.LinesItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LinesItemMapper extends BaseMapper<LinesItem> {


    Boolean batchInsert(List<LinesItem> linesItemList);



}

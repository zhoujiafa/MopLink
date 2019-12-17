package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.Indent;
import com.springcloud.bean.vo.SaveResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: ClassName
 * @Create: Date Time
 */
@DS(value = "stock")
@Mapper
public interface IndentMapper extends BaseMapper<Indent> {

    SaveResult saveIndent(Map<String, Object> map);
}

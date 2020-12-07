package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.MOPIndent;
import com.springcloud.bean.vo.SaveResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @ClassName : MOPIndentMapper
 * @Description : 下载单:订货单
 * @Author : Joe
 * @Date: 2019/12/10 14:09
 */
@DS(value = "stock")
@Mapper
public interface MOPIndentMapper extends BaseMapper<MOPIndent> {

    MOPIndent  getOne(String companyCode,String orderNo);

    SaveResult saveMopIndent(Map<String, Object> map);

}

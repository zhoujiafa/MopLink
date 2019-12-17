package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.MOPIndentDt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
@DS(value = "stock")
@Mapper
public interface MOPIndentDtMapper extends BaseMapper<MOPIndentDt> {

    Boolean batchInsert(List<MOPIndentDt> mopIndentDts);

}

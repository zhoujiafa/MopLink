package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.NeedOrder;
import com.springcloud.bean.vo.SaveResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@DS(value = "stock")
@Mapper
public interface NeedOrderMapper extends BaseMapper<NeedOrder>{

    public SaveResult saveNeedOrder(Map<String, Object> map);
}

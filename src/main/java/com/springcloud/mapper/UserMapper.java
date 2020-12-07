package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
@DS(value = "stock_test")
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

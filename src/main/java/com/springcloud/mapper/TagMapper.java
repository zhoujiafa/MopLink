package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.Tag;
import com.springcloud.bean.dos.TagInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    List<Tag> tagListByPhone(String phone);

}

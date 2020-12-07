package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
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
public interface TagInfoMapper extends BaseMapper<TagInfo> {

    Boolean batchInsert(List<TagInfo> list);

    /**
     * 根据吊牌扫描人信息查询以往记录
     * @param phone
     * @return
     */
    List<TagInfo> tagColorByPerson(String phone);

}

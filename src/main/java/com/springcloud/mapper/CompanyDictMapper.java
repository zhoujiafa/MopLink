package com.springcloud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.CompanyDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @ClassName : CompanyDictMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 10:50
*/

@Mapper
public interface CompanyDictMapper extends BaseMapper<CompanyDict> {

    Boolean batchInsert(List<CompanyDict> companyDictList);

}

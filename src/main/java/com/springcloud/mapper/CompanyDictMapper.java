package com.springcloud.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.CompanyDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
* @ClassName : CompanyDictMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 10:50
*/
@DS(value = "stock")
@Mapper
public interface CompanyDictMapper extends BaseMapper<CompanyDict> {

    /**
     * 批量新增
     * @param companyDictList
     * @return
     */
    Boolean batchInsert(List<CompanyDict> companyDictList);


    Boolean save(CompanyDict companyDict);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<CompanyDict> getPageCompanyDict(Map<String,Object> map);


}

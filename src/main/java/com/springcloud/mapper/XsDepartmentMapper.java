package com.springcloud.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.CompanyDict;
import com.springcloud.bean.dos.XsDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
* @ClassName : CompanyDictMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 10:50
*/
@DS(value = "jzn_wms_test")
@Mapper
public interface XsDepartmentMapper extends BaseMapper<XsDepartment> {


    XsDepartment getOneByXsDeptCode(String xsDeptCode);

}

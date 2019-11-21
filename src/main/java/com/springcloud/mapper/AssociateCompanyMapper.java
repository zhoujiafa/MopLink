package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.AssociateCompany;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @ClassName : AssociateCompanyMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/20 16:58
*/
@DS(value = "jzn_wms_test")
@Mapper
public interface AssociateCompanyMapper extends BaseMapper<AssociateCompany> {

    List<AssociateCompany> list(AssociateCompany query);
}

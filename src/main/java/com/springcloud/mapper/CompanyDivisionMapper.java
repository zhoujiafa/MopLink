package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.CompanyDivision;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName : AssociateCompanyMapper
* @Description : 
* @Author : Joe
* @Date: 2019/11/20 16:58
*/
@DS(value = "jzn_wms_test")
@Mapper
public interface CompanyDivisionMapper extends BaseMapper<CompanyDivision> {

    /**
     * 关联公司列表
     * @param query
     * @return
     */
    List<CompanyDivision> list(CompanyDivision query);

    /**
     *  根据公司编码查询关联公司信息
     * @param customerCode
     * @return
     */
    CompanyDivision getOneByCustomerCode(@Param("customerCode")String customerCode);

}

package com.springcloud.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.bean.dos.CompanyDivision;
import com.springcloud.bean.dos.GoodsDivision;
import com.springcloud.bean.query.GoodsDictQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@DS(value = "jzn_wms_test")
public interface GoodsDivisionMapper extends BaseMapper<GoodsDivision> {


    /**
     *  根据公司编码查询关联公司信息
     * @param barCode
     * @return
     */
    GoodsDivision getOneBybarCode(@Param("barCode")String barCode);


    /**
     * 关联物料列表
     * @param query
     * @return
     */
    List<GoodsDivision> list(GoodsDivision query);


}

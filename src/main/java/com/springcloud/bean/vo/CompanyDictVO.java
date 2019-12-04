package com.springcloud.bean.vo;

import lombok.Data;
/**
* @ClassName : CompanyDictVO
* @Description : 页面展示分页数据
* @Author : Joe
* @Date: 2019/11/19 11:06
*/

import java.util.List;

@Data
public class CompanyDictVO {

    private Long iD;
    private String companyCode;
    private String companyName;
    private String mopDeptCode;
    private String xunsoftDeptName;


}

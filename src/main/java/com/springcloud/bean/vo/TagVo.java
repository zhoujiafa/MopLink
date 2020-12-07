package com.springcloud.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName : TagInfoVo
 * @Description :
 * @Author : Joe
 * @Date: 2020-11-18 16:09
 */
@Data
public class TagVo {

    String tagNo;
    int count;
    String creator;
    String address;
    Date createtime;
    String remark;
}

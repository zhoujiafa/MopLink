package com.springcloud.bean.vo;

import lombok.Data;

/**
 * @ClassName : TagInfoVo
 * @Description :
 * @Author : Joe
 * @Date: 2020-11-18 16:09
 */
@Data
public class TagInfoVo {

    /**
     * 款号
     */
    String itemCode;
    /**
     * 颜色
     */
    String color;
    /**
     * 数量
     */
    Integer count;
    /**
     * 总数量
     */
    Integer totalCount;
}

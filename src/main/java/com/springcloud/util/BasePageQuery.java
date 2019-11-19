package com.springcloud.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

/**
 * @ClassName : BasePageQuery
 * @Description : 分页属性基类
 * @Author : Joe
 * @Date: 2019-11-19 11:38
 */
public class BasePageQuery {

    /** 最小页码 */
    private final Integer MIN_NUM = 1;
    /** 最小分页条数 */
    private final Integer MIN_SIZE = 10;

    @ApiModelProperty("分页查询页数")
    @Setter
    private Integer num;

    @ApiModelProperty("分页查询每页条数")
    @Setter
    private Integer size;

    public Integer getNum(){
        return num == null || num<MIN_NUM?MIN_NUM:num;
    }

    public Integer getSize(){
        return size == null || size<MIN_SIZE?MIN_SIZE:size;
    }

}

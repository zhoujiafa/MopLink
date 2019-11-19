package com.springcloud.util;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@ApiModel("分页结果")
@Data
@NoArgsConstructor
public class PageResult<T> {

    @ApiModelProperty("分页总数")
    private Long total;
    @ApiModelProperty("当前页")
    private Integer pageIndex;
    @ApiModelProperty("分页大小")
    private Integer size;
    @ApiModelProperty("数据")
    private List<T> list;

    public PageResult(PageInfo pageInfo, List<T> list){
        this(pageInfo);
        this.list=list;
    }

    public PageResult(PageInfo pageInfo) {
        this.total = pageInfo.getTotal();
        this.pageIndex = pageInfo.getPageNum();
        this.size = pageInfo.getSize();
        this.list = pageInfo.getList();
    }
}

package com.springcloud.util;

import lombok.Data;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
public class RequestPage {

    private int pageIndex;
    private int pageSize;

    public RequestPage() {
        this.pageSize=10;
    }
}

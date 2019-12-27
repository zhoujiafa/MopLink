package com.springcloud.bean.query;

import lombok.Data;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
public class NeedOrderQuery {

    String docNum;
    String needNo;
    String companyName;
}

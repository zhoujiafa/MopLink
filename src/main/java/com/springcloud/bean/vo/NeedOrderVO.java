package com.springcloud.bean.vo;

import lombok.Data;

/**
 * @Author darren.zhou
 * @Description:
 * @Param: $params
 * @Return: ${returns}
 * @Create: $Date $Time
 */
@Data
public class NeedOrderVO {
    Long  iD;
    String docNum;
    String needNo;
    String companyCode;
    String companyName;
    String remark;
    String createName;
    String needStatus;
    String createDate;
    String downLoadMark;
    String upLoadMark;
    String upLoadDate;
    Integer docQtyTotal;
    Double  docTotal;
    String cardCode;
    String cardName;
    String department;
    String saleCode;
    String baseDocNum;
}

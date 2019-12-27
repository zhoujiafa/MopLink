package com.springcloud.bean.vo;

import com.springcloud.bean.dos.NeedOrderDetail;
import lombok.Data;

import java.util.List;

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

    /** 子项单据 */
    private List<NeedOrderDetailVO> list;
}

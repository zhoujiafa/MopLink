package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("needOrder")
public class NeedOrder {

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

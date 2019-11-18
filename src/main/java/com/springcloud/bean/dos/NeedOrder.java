package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("needOrder")
public class NeedOrder {

    String docNum;
    String ntypeName;
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
    Double  vdocTotal;
    String cardCode;
    String cardName;
    String department;
    String saleCode;
}

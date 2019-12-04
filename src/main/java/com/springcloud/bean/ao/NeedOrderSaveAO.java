package com.springcloud.bean.ao;

import com.springcloud.bean.dos.CompanyDict;
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
public class NeedOrderSaveAO {

    //Mop门店编码
    String companyCode;
    //单号
    String needOrderNo;
    //操作人
    String creator;
    //是否重复提交
    Boolean isRepeatSubmit;
    //是否强制提交
    Boolean isforceSubmit;
    //返回结果
    Integer resultInt;
    //返回消息
    String resultString;
}

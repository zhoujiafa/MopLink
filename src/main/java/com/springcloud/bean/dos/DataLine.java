package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("DataLine")
public class DataLine {

    private String docNum;
    private String needStatusName;
    private String needNo;
    private String needDate;
    private String ntypeCode;
    private String ntypeName;
    private String companyName;
    private String remark;
    private String createName;
    private String needStatus;
    private String createDate;


}

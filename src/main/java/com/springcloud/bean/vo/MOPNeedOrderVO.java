package com.springcloud.bean.vo;

import com.springcloud.bean.dos.MOPNeedOrderDt;
import lombok.Data;

import java.util.List;

@Data
public class MOPNeedOrderVO {

    private String docNum;
    private String needStatusName;
    private String needNo;
    private String needDate;
    private String ntypeCode;
    private String ntypeName;
    private String companyName;
    private String companyCode;
    private String remark;
    private String createName;
    private String needStatus;
    private String createDate;
    private List<MOPNeedOrderDt> lines;

    private Boolean IsRetransmit;
    private Boolean IsCompulsorySubmission;




}

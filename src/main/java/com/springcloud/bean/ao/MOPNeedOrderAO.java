package com.springcloud.bean.ao;

import com.springcloud.bean.dos.MOPIndentDt;
import com.springcloud.bean.dos.MOPNeedOrderDt;
import io.swagger.annotations.ApiModelProperty;
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
public class MOPNeedOrderAO {

    private String needStatusName;
    private String needNo;
    private String needDate;
    private String ntypeCode;
    private String ntypeName;
    private String companyName;
    private String companyCode;
    private String remark;
    private String createName;
    private String newCreateName;
    private String needStatus;
    private String createDate;
    private List<MOPNeedOrderDt> lines;

    private int code;
    private String message;
    private Boolean IsRetransmit;
    private Boolean IsCompulsorySubmission;

}

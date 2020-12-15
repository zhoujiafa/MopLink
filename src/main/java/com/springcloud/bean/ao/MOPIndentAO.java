package com.springcloud.bean.ao;

import com.springcloud.bean.dos.MOPIndentDt;
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
public class MOPIndentAO {

    /**  门店名称 */
    private String companyName;
    /**  门店编码 */
    private String companyCode;
    /**  订货单号 */
    private String orderNo;
    /**  到货日期 */
    private String deliveryDate;
    /**  单据类型编码 */
    private String orderType;
    /**  单据类型名称 */
    private String orderTypeName;
    /**  付款状态 */
    private String payStatusName;
    /**  单据状态编码NA("待确认"),YA("已确认"),YR("已拒绝"),YC("已取消"),YD("已发货"),BD("部分发货"),YS("已收货"),BS("部分收货"),YF("已完成"),AR("申请退款"),YO("已关闭") */
    private String orderStatus;
    /**  订货单状态名称 */
    private String orderStatusName;
    /**  单据创建时间 */
    private String createDate;
    /**  下载单据创建人 */
    private String createName;
    /**  本地单据创建人（备用） */
    private String newCreateName;

    /** 经销商编码 */
    private String distrCode;

    /*子项单据*/
    private List<MOPIndentDt> lines;

    /**  是否重复提交 */
    private Boolean isRetransmit;

    /**  是否强制提交 */
    private Boolean isCompulsorySubmission;

}

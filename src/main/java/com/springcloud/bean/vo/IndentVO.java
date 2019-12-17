package com.springcloud.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: ClassName
 * @Create: Date Time
 */
@Data
public class IndentVO {

    /** ID  */
    private Long iD;
    /** 新单号 */
    private String docNum;
    /** 下载单号 */
    private String orderNo;
    /** 门店名称 */
    private String companyName;
    /** 门店编号 */
    private String companyCode;
    /** 到货日期 */
    private Date deliveryDate;
    /** 订单状态 */
    private String orderStatus;
    /** 订单状态名称 */
    private String orderStatusName;
    /** 创建日期 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    /** 创建人 */
    private String createName;
    /** 下载标识 */
    private String downLoadMark;
    /** 上传标识 */
    private String upLoadMark;
    /** 上传日期 */
    private Date upLoadDate;
    /** 数量总计 */
    private Integer docQtyTotal;
    /** 金额总计 */
    private Double docTotal;
    /** 卡号 */
    private String cardCode;
    /** 卡名 */
    private String cardName;
    /** 部门 */
    private String department;
    /** 销售代码 */
    private String saleCode;
    /** 基本编码 */
    private String baseDocNum;
    /** 备注 */
    private String remark;
    /** 子项单据 */
    private List<IndentDtVO> list;
}

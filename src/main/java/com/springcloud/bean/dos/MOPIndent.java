package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : MOPIndent
 * @Description :
 * @Author : Joe
 * @Date: 2019/12/7 10:12
 */
@Data
@TableName("mopIndent")
public class MOPIndent {

    /** 主键(生成新单号) */
    private String docNum;
    /**  门店编号 */
    private String companyCode;
    /**  门店名称 */
    private String companyName;
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
    /**  单据创建人 */
    private String createName;


}

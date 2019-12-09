package com.springcloud.bean.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mopNeedOrder")
public class MOPNeedOrder {

    /** 主键(生成新单号) */
    private String docNum;
    /** 单据状态名称 */
    private String needStatusName;
    /** 要货单号 */
    private String needNo;
    /** 到货日期 */
    private String needDate;
    /** 单据状态编码 */
    private String ntypeCode;
    /** 单据状态名称 */
    private String ntypeName;
    /** 门店名称 */
    private String companyName;
    /** 门店编码 */
    private String companyCode;
    /** 备注 */
    private String remark;
    /** 单据创建人 */
    private String createName;
    /** 单据状态编码（N - 待受理,Y - 已受理,C - 已拒绝,F - 已配货,S - 已取消,G - 草稿）*/
    private String needStatus;
    /** 单据创建时间 */
    private String createDate;



}

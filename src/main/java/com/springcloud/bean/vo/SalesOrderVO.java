package com.springcloud.bean.vo;

import lombok.Data;

import java.util.List;


/**
* @ClassName : SalesOrderVO
* @Description : 销售订单明细
* @Author : Joe
* @Date: 2020/3/4 14:36
*/

@Data
public class SalesOrderVO {


        String BillNo;
        String OrderId;
        String OrderNo;
        String FxsNo;
        String UserCode;
        String EShopName;
        String EshopCode;
        String C_UserName;
        String C_Name;
        String Province;
        String City;
        String County;
        String Address;
        String MobiTel;
        String Phone;
        String PostCode;
        String EMail;
        String C_Remark;
        String O_Remark;
        String OrderCheckRemark;
        String ReceiptSpec;
        String StockOrderSumPrice;
        String StockOrderSumFxPrice;
        String Exp_Fee;
        String EShop_Exp_Price;
        String Exp_Name;
        String Sel_Exp_Name;
        String Sel_Exp_Code;
        String Exp_Code;
        String Exp_Cod;
        String Cod_Fee;
        String Exp_Codfee;
        String UnpaidPrice;
        String PaidPrice;
        String SurplusPrice;
        List<ProSpec> list;

        String StockOrder_Flag;
        String StockOrder_CancelFlag;
        String AddTime;
        String BillExp_Num;
        String Fxs_Type;
        String Exp_Time;
        String Is_Receipt;
        String Send_Sms;
        String Scan_Check;
        String Total_Weight;
        String Pf_Order;
        String Order_Refund;
        String Order_From;
        String C_Paytime;
        String Pay_Type;
        String Pay_NO;
        String Card;
        String RealName;
        String Fxs_PayTime;

}

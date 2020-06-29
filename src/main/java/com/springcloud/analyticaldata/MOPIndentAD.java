package com.springcloud.analyticaldata;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.bean.dos.MOPIndentDt;
import com.springcloud.bean.vo.MOPIndentVO;
import com.springcloud.bean.vo.ReturnObject;

import java.util.ArrayList;
import java.util.List;

public class MOPIndentAD {

    /**
     * 解析第一层
     */
    public ReturnObject getJsonToBeanFrist(String jsonObject)  {
        ReturnObject returnObject = new ReturnObject();
        if (jsonObject != null) {
            JSONObject jsonObj =  JSONObject.parseObject(jsonObject);
            returnObject.setDataLine(jsonObj.getString("DataLine"));
            returnObject.setErrorCode(jsonObj.getString("ErrorCode"));
            returnObject.setResultString(jsonObj.getString("ResultString"));
            returnObject.setResultInt(jsonObj.getString("ResultInt"));
        }
        return returnObject;
    }

    /**
     * 解析订货单第二层
     */
    public MOPIndentVO getJsonToBeanSecond(String jsonObject) {
        MOPIndentVO mopIndentVO = new MOPIndentVO();
        if (jsonObject != null) {
            JSONObject jsonObj1 = JSONObject.parseObject(jsonObject);
            JSONObject jsonObj = jsonObj1.getJSONObject("DataLine");
            mopIndentVO.setCompanyName(jsonObj.getString("companyName"));
            mopIndentVO.setOrderNo(jsonObj.getString("orderNo"));
            mopIndentVO.setDeliveryDate(jsonObj.getString("deliveryDate"));
            mopIndentVO.setOrderType(jsonObj.getString("orderType"));
            mopIndentVO.setOrderTypeName(jsonObj.getString("orderTypeName"));
            mopIndentVO.setPayStatusName(jsonObj.getString("payStatusName"));
            mopIndentVO.setOrderStatus(jsonObj.getString("orderStatus"));
            mopIndentVO.setOrderStatusName(jsonObj.getString("orderStatusName"));
            mopIndentVO.setCreateDate(jsonObj.getString("createDate"));
            mopIndentVO.setCreateName(jsonObj.getString("createName"));
        }
        return mopIndentVO;
    }

    /**
     * 解析第三层
     */
    public List<MOPIndentDt> getJsonToBeanThird(JSONObject dataLine) {
        List<MOPIndentDt> mopIndentDts = new ArrayList<>();

        if (dataLine != null) {
            JSONArray array = dataLine.getJSONArray("lines");
            mopIndentDts =(getJsonArrayItem(array));
        }
        return mopIndentDts;
    }

    /**
     * 依次取出JSONArray中的值
     */
    private List<MOPIndentDt> getJsonArrayItem(JSONArray array)  {
        List<MOPIndentDt> mopIndentDts = new ArrayList<>();
        if (array != null) {
            for (int i=0;i<array.size();i++) {
                MOPIndentDt mopIndentDt = new MOPIndentDt();
                String lines = array.getString(i);
                JSONObject jsonObj1 =  JSONObject.parseObject(lines);

                mopIndentDt.setRealPrice(jsonObj1.getDouble("realPrice"));
                mopIndentDt.setRealPriceLineTotal(jsonObj1.getDouble("realPriceLineTotal"));
                mopIndentDt.setItemName(jsonObj1.getString("itemName"));
                mopIndentDt.setItemQuantity(jsonObj1.getDouble("itemQuantity"));
                mopIndentDt.setQuantity(jsonObj1.getDouble("quantity"));
                mopIndentDt.setItemCode(jsonObj1.getString("itemCode"));
                mopIndentDt.setItemUnit(jsonObj1.getString("itemUnit"));
                mopIndentDt.setRemark(jsonObj1.getString("remark"));
                mopIndentDt.setSkuBarcode(jsonObj1.getString("skuBarcode"));
                mopIndentDt.setSpecName(jsonObj1.getString("specName"));
                mopIndentDt.setUnit(jsonObj1.getString("unit"));
                mopIndentDt.setListPrice(jsonObj1.getDouble("listPrice"));
                mopIndentDt.setLineTotal(jsonObj1.getDouble("lineTotal"));
                mopIndentDt.setPromotionMoney(jsonObj1.getDouble("promotionMoney"));
                mopIndentDt.setTotalExpressFee(jsonObj1.getDouble("totalExpressFee"));
                mopIndentDts.add(mopIndentDt);
            }
        }
        return mopIndentDts;
    }


}

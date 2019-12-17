package com.springcloud.analyticalData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.bean.dos.MOPNeedOrderDt;
import com.springcloud.bean.vo.ReturnObject;
import com.springcloud.bean.vo.MOPNeedOrderVO;

import java.util.ArrayList;
import java.util.List;

public class MOPNeedOrderAD {

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
     * 解析要货单第二层
     */
    public MOPNeedOrderVO getJsonToBeanSecond(String jsonObject) {
        MOPNeedOrderVO MOPNeedOrderVo = new MOPNeedOrderVO();
        if (jsonObject != null) {
            JSONObject jsonObj1 = JSONObject.parseObject(jsonObject);
            JSONObject jsonObj = jsonObj1.getJSONObject("DataLine");
            MOPNeedOrderVo.setNeedStatusName(jsonObj.getString("needStatusName"));
            MOPNeedOrderVo.setNeedNo(jsonObj.getString("needNo"));
            MOPNeedOrderVo.setNeedDate(jsonObj.getString("needDate"));
            MOPNeedOrderVo.setNtypeCode(jsonObj.getString("ntypeCode"));
            MOPNeedOrderVo.setNtypeName(jsonObj.getString("ntypeName"));
            MOPNeedOrderVo.setCompanyName(jsonObj.getString("companyName"));
            MOPNeedOrderVo.setRemark(jsonObj.getString("remark"));
            MOPNeedOrderVo.setCreateName(jsonObj.getString("createName"));
            MOPNeedOrderVo.setNeedStatus(jsonObj.getString("needStatus"));
            MOPNeedOrderVo.setCreateDate(jsonObj.getString("createDate"));
        }
        return MOPNeedOrderVo;
    }

    /**
     * 解析第三层
     */
    public List<MOPNeedOrderDt> getJsonToBeanThird(JSONObject dataLine) {
        List<MOPNeedOrderDt> MOPNeedOrderDts = new ArrayList<>();

        if (dataLine != null) {
            JSONArray array = dataLine.getJSONArray("lines");
            MOPNeedOrderDts =(getJsonArrayItem(array));
        }
        return MOPNeedOrderDts;
    }

    /**
     * 依次取出JSONArray中的值
     */
    private List<MOPNeedOrderDt> getJsonArrayItem(JSONArray array)  {
        List<MOPNeedOrderDt> MOPNeedOrderDts = new ArrayList<>();
        if (array != null) {
            for (int i=0;i<array.size();i++) {
                MOPNeedOrderDt MOPNeedOrderDt = new MOPNeedOrderDt();
                String lines = array.getString(i);
                JSONObject jsonObj1 =  JSONObject.parseObject(lines);
                MOPNeedOrderDt.setPurchasingQuantity(jsonObj1.getString("purchasingQuantity"));
                MOPNeedOrderDt.setItemName(jsonObj1.getString("itemName"));
                MOPNeedOrderDt.setUnit(jsonObj1.getString("unit"));
                MOPNeedOrderDt.setItemQuantity(jsonObj1.getInteger("itemQuantity"));
                MOPNeedOrderDt.setQuantity(jsonObj1.getInteger("quantity"));
                MOPNeedOrderDt.setSpecName(jsonObj1.getString("specName"));
                MOPNeedOrderDt.setSpecCode(jsonObj1.getString("specCode"));
                MOPNeedOrderDt.setItemCode(jsonObj1.getString("itemCode"));
                MOPNeedOrderDt.setItemUnit(jsonObj1.getString("itemUnit"));
                MOPNeedOrderDt.setRemark(jsonObj1.getString("remark"));
                MOPNeedOrderDt.setSkuBarcode(jsonObj1.getString("skuBarcode"));
                MOPNeedOrderDt.setAllocationQuantity(jsonObj1.getInteger("allocationQuantity"));
                MOPNeedOrderDts.add(MOPNeedOrderDt);
            }
        }
        return MOPNeedOrderDts;
    }


}

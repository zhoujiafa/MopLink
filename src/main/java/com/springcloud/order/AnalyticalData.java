package com.springcloud.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.bean.dos.LinesItem;
import com.springcloud.bean.vo.ReturnObject;
import com.springcloud.bean.vo.DataLineVO;

import java.util.ArrayList;
import java.util.List;

public class AnalyticalData {

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
     * 解析第二层
     */
    public DataLineVO getJsonToBeanSecond(String jsonObject) {
        DataLineVO dataLineVo = new DataLineVO();
        if (jsonObject != null) {
            JSONObject jsonObj1 = JSONObject.parseObject(jsonObject);
            JSONObject jsonObj = jsonObj1.getJSONObject("DataLine");
            dataLineVo.setNeedStatusName(jsonObj.getString("needStatusName"));
            dataLineVo.setNeedNo(jsonObj.getString("needNo"));
            dataLineVo.setNeedDate(jsonObj.getString("needDate"));
            dataLineVo.setNtypeCode(jsonObj.getString("ntypeCode"));
            dataLineVo.setNtypeName(jsonObj.getString("ntypeName"));
            dataLineVo.setCompanyName(jsonObj.getString("companyName"));
            dataLineVo.setRemark(jsonObj.getString("remark"));
            dataLineVo.setCreateName(jsonObj.getString("createName"));
            dataLineVo.setNeedStatus(jsonObj.getString("needStatus"));
            dataLineVo.setCreateDate(jsonObj.getString("createDate"));
        }
        return dataLineVo;
    }

    /**
     * 解析第三层
     */
    public List<LinesItem> getJsonToBeanThird(JSONObject dataLine) {
        List<LinesItem> linesItems = new ArrayList<>();

        if (dataLine != null) {
            JSONArray array = dataLine.getJSONArray("lines");
            linesItems=(getJsonArrayItem(array));
        }
        return linesItems;
    }

    /**
     * 依次取出JSONArray中的值
     */
    private List<LinesItem> getJsonArrayItem(JSONArray array)  {
        List<LinesItem> linesItems = new ArrayList<>();
        if (array != null) {
            for (int i=0;i<array.size();i++) {
                LinesItem linesItem = new LinesItem();
                String lines = array.getString(i);
                JSONObject jsonObj1 =  JSONObject.parseObject(lines);
                linesItem.setPurchasingQuantity(jsonObj1.getString("purchasingQuantity"));
                linesItem.setItemName(jsonObj1.getString("itemName"));
                linesItem.setUnit(jsonObj1.getString("unit"));
                linesItem.setItemQuantity(jsonObj1.getInteger("itemQuantity"));
                linesItem.setQuantity(jsonObj1.getInteger("quantity"));
                linesItem.setSpecName(jsonObj1.getString("specName"));
                linesItem.setSpecCode(jsonObj1.getString("specCode"));
                linesItem.setItemCode(jsonObj1.getString("itemCode"));
                linesItem.setItemUnit(jsonObj1.getString("itemUnit"));
                linesItem.setRemark(jsonObj1.getString("remark"));
                linesItem.setSkuBarcode(jsonObj1.getString("skuBarcode"));
                linesItem.setAllocationQuantity(jsonObj1.getInteger("allocationQuantity"));
                linesItems.add(linesItem);
            }
        }
        return linesItems;
    }


}

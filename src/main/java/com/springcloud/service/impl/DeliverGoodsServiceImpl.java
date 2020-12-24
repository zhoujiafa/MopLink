package com.springcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reabam.sign.SignUtil;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.dos.DeliverGoodsCount;
import com.springcloud.bean.dos.Indent;
import com.springcloud.bean.query.IndentQuery;
import com.springcloud.mapper.DeliverGoodsJznMapper;
import com.springcloud.mapper.DeliverGoodsMapper;
import com.springcloud.response.ResponseDeliverGoods;
import com.springcloud.service.DeliverGoodsService;
import com.springcloud.util.ResponseBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.springcloud.service.impl.MOPIndentServiceImpl.sendPost;

/**
 * @ClassName : MOPNeedOrderServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class DeliverGoodsServiceImpl implements DeliverGoodsService {

    @Autowired
    DeliverGoodsMapper deliverGoodsMapper;
    @Autowired
    DeliverGoodsJznMapper deliverGoodsJznMapper;

    private static final String url = "http://test.try-shopping.com/ts-openapi";
    private static final String key = "285e11c1e83a4094b35cc3cf320ad820";


    @Override
    public List<DeliverGoods> listByneedNo(String orderNo,String companyCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("docNum", orderNo);
        map.put("customer", companyCode);
        //List<DeliverGoods> list = deliverGoodsMapper.getDeliverGoods(map);
        List<DeliverGoods> list = deliverGoodsMapper.getDeliveryOrderUniqueCode(map);
        for(DeliverGoods deliverGoods:list){
            deliverGoods.setDocNum(deliverGoods.getDocNum().trim());
            deliverGoods.setOrderNo(deliverGoods.getOrderNo().trim());
            deliverGoods.setSkuBarcode(deliverGoods.getSkuBarcode().trim());
            deliverGoods.setDesignName(deliverGoods.getDesignName().trim());
            deliverGoods.setDesignNumber(deliverGoods.getDesignNumber().trim());
            deliverGoods.setRemark(deliverGoods.getRemark().trim());
            deliverGoods.setUnit(deliverGoods.getUnit().trim());
            deliverGoods.setBaseDocNum(deliverGoods.getBaseDocNum().trim());
            deliverGoods.setColor(deliverGoods.getColor().trim());
            deliverGoods.setSize(deliverGoods.getSize().trim());
            deliverGoods.setPriceTag(deliverGoods.getPriceTag().trim());
            deliverGoods.setOutboundPrice(deliverGoods.getOutboundPrice().trim());
        }
        return list;
    }

    @Override
    public List<DeliverGoods> listByneedNo_xls(String orderNo, String companyCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("docNum", orderNo);
        map.put("customer", companyCode);
        //List<DeliverGoods> list = deliverGoodsMapper.getDeliverGoods(map);
        List<DeliverGoods> list = deliverGoodsJznMapper.getDeliveryOrderUniqueCode(map);
        for(DeliverGoods deliverGoods:list){
            deliverGoods.setDocNum(deliverGoods.getDocNum().trim());
            deliverGoods.setSkuBarcode(deliverGoods.getSkuBarcode().trim());
            deliverGoods.setDesignName(deliverGoods.getDesignName().trim());
            deliverGoods.setDesignNumber(deliverGoods.getDesignNumber().trim());
            deliverGoods.setColor(deliverGoods.getColor().trim());
            deliverGoods.setSize(deliverGoods.getSize().trim());
            deliverGoods.setPriceTag(deliverGoods.getPriceTag().trim());
            deliverGoods.setOutboundPrice(deliverGoods.getOutboundPrice().trim());
        }
        return list;
    }

    @Override
    public String responseTorequest(List<DeliverGoods> list,String shipmentOrderNo, String destineOrderNo, String customer) {

        Map<String, Object> params = new HashMap<String, Object>();
        List<Map> mapList = new LinkedList<>();
        List<Map> mapList2 = new LinkedList<>();
        List<Map> mapList3 = new LinkedList<>();
        params.put("companyCode", customer);
        params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");
        //params.put("appId", "8edd25b7f74f44a7bff1e9f2af49c930");
        Map<String, Object> dataJson = new HashMap<String, Object>();
        Map<String, Object> dataJson2 = new HashMap<String, Object>();
        Map<String, Object> dataJson3 = new HashMap<String, Object>();

        //dataJson.put("orderNo", orderNo);
        //dataJson.put("orderNo", "DO7201012122658583");
        dataJson.put("orderNo", destineOrderNo);

        Map<String, Object> ddmap = new HashMap<String, Object>();
        ddmap.put("docNum", shipmentOrderNo);
        ddmap.put("customer", customer);
        List<DeliverGoodsCount> getDeliveryorderDetail = deliverGoodsMapper.getDeliveryorderDetail(ddmap);
        if(getDeliveryorderDetail.size() > 0) {
            for(DeliverGoodsCount deliveryorderDetail : getDeliveryorderDetail){
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Object> map2 = new HashMap<String, Object>();
                Map<String, Object> map3 = new HashMap<String, Object>();
                List<String> array = new ArrayList<>();
                for(DeliverGoods deliverGoods : list){
                    if(deliveryorderDetail.getSkuBarcode().trim().equals(deliverGoods.getSkuBarcode().trim())){
                        Integer deliveryorderDetailcount = Integer.valueOf(deliveryorderDetail.getCount().substring(0, deliveryorderDetail.getCount().indexOf(".")));
                        Double deliverGoodsoutboundPrice = Double.valueOf(deliverGoods.getOutboundPrice());
                        map.put("fID",deliverGoods.getRemark());
                        //map.put( "skuBarcode",deliverGoods.getSkuBarcode());
                        //map.put( "itemCode",deliverGoods.getDesignNumber());
                        //map.put( "itemCode","sp000001");
                        map.put( "distributorAmount",deliverGoods.getOutboundPrice());
                        map.put( "distributorRealPrice",deliveryorderDetailcount*deliverGoodsoutboundPrice);
                        map.put( "distributorMoney",deliveryorderDetailcount*deliverGoodsoutboundPrice);
                        map.put( "unit",deliverGoods.getUnit());
                        map.put( "realPrice",deliverGoods.getOutboundPrice());
                        map.put( "isGive",0);
                        map.put("quantity",deliveryorderDetailcount);
                        map2.put( "quantity",deliveryorderDetailcount);
                        //array.add(deliverGoods.getDocNum());

                        //array.add("sp04000"+(sp+1));
                        //sp +=1;
                        map3.put( "itemCode",deliverGoods.getDesignNumber());
                        map3.put("itemCode","sp000001");
                        map3.put( "skuBarcode",deliverGoods.getSkuBarcode());
                        map3.put( "quantity",deliveryorderDetailcount);
                        map.put( "tatalRealPrice", deliveryorderDetailcount*deliverGoodsoutboundPrice);
                        map2.put( "skuBarcode",deliverGoods.getSkuBarcode());
                    }

                }
                map3.put( "barcodes",array);
                mapList.add(map);
                mapList2.add(map2);
                mapList3.add(map3);
            }

        }
        dataJson.put("lines", mapList);
        dataJson2.put("lines", mapList2);
        dataJson3.put("lines", mapList3);

        params.put("dataJson", dataJson);
        String paramsJson1 = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String requsetUrl1 = url + "/openapi/b2borderItem/changeStatus";
        String resultJson1 = sendPost(requsetUrl1, paramsJson1);
        JSONObject jsonObject = JSON.parseObject(resultJson1);
        //if(jsonObject.getString("ResultInt").equals("0") && jsonObject.getString("ResultString").equals("操作成功")){
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("companyCode", customer);
            params2.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");
            dataJson2.put("orderNo", destineOrderNo);
            dataJson2.put("status", "YA");
            params2.put("dataJson", dataJson2);

            String paramsJson2 = JSON.toJSON(SignUtil.sign(params2, key)).toString();
            String requsetUrl2 = url + "/openapi/b2border/changeStatus";
            String resultJson2 = sendPost(requsetUrl2, paramsJson2);

            JSONObject jsonObject3 = JSON.parseObject(resultJson2);
            //if(jsonObject3.getString("ResultInt").equals("0")){

                Map<String, Object> params3 = new HashMap<String, Object>();
                params3.put("companyCode", customer);
                params3.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");
                dataJson3.put("orderNo", destineOrderNo);
                params3.put("dataJson", dataJson3);

                String paramsJson3 = JSON.toJSON(SignUtil.sign(params3, key)).toString();
                String requsetUrl3 = url + "/openapi/b2border/delivery";
                String resultJson3 = sendPost(requsetUrl3, paramsJson3);

                return resultJson3;
            //}


            //return resultJson2;
       //}

        //return resultJson1;
    }

    @Override
    public String updateSalesOrderDetails(List<DeliverGoods> list, String shipmentOrderNo, String destineOrderNo, String customer, String distrCode) {
        Double priceSubtotal = 0.00;
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> dataJson = new HashMap<String, Object>();
        List<Map> mapList = new LinkedList<>();

        Map<String, Object> ddmap = new HashMap<String, Object>();
        ddmap.put("docNum", shipmentOrderNo);
        ddmap.put("customer", customer);
        List<DeliverGoodsCount> getDeliveryorderDetail = deliverGoodsMapper.getDeliveryorderDetail(ddmap);
        Double TransAmount = 0.00;
        for(DeliverGoodsCount deliveryorderDetail : getDeliveryorderDetail){
            Map<String, Object> map = new HashMap<String, Object>();
            for(DeliverGoods deliverGoods : list){
                if(deliveryorderDetail.getSkuBarcode().trim().equals(deliverGoods.getSkuBarcode().trim())){
                    Integer deliveryorderDetailcount = Integer.valueOf(deliveryorderDetail.getCount().substring(0, deliveryorderDetail.getCount().indexOf(".")));
                    Double deliverGoodsoutboundPrice = Double.valueOf(deliverGoods.getOutboundPrice());
                    map.put("fID",deliverGoods.getRemark());
                    map.put( "quantity",String.valueOf(deliveryorderDetailcount));
                    map.put( "itemName",deliverGoods.getDesignName());
                    map.put( "priceSubtotal",String.valueOf(deliveryorderDetailcount*deliverGoodsoutboundPrice));
                    priceSubtotal = deliveryorderDetailcount*deliverGoodsoutboundPrice;
                    TransAmount += Double.valueOf(deliverGoods.getOutboundPrice());
                }
            }
            mapList.add(map);
        }
        dataJson.put("orderNo", destineOrderNo);
        dataJson.put("lines", mapList);
        params.put("dataJson", dataJson);
        params.put("companyCode", customer);
        params.put("appId", "BC7CEC0171504DF799CB4972541C0FXS");
        String paramsJson = JSON.toJSON(SignUtil.sign(params, key)).toString();
        String requsetUrl = url + "/openapi/supply/orderItem/change";
        String resultJson = sendPost(requsetUrl, paramsJson);
        JSONObject jsonObject = JSON.parseObject(resultJson);
        if(jsonObject.getString("ResultInt").equals("0")){
            //条码变更
            Map<String, Object> map = new HashMap<>();
            map.put("docNum",shipmentOrderNo);
            map.put("customer",customer);
            List<DeliverGoods> delist = deliverGoodsMapper.getDeliveryOrderUniqueCode(map);
            if(delist.size()>0 && delist!=null){
                Map<String, Object> parms = new HashMap<String, Object>();
                parms.put("appId","BC7CEC0171504DF799CB4972541C0FXS");
                //存dataJson下的各个款号
                List<Map> Isck = new LinkedList<>();
                List<Map> Iscks = new LinkedList<>();
                //按款号分组
                Map<String, List<DeliverGoods>> collect = delist.stream().collect(Collectors.groupingBy(DeliverGoods::getDesignNumber));
                //collect.entrySet()等于分组后的数量，entry为分组后的款号代表
                for(Map.Entry<String, List<DeliverGoods>> entry : collect.entrySet()){
                     //循环一次添加一个分组到dataJson的集合里
                     Map<String, Object> icsk = new HashMap<String, Object>();

                     icsk.put("itemCode",entry.getKey());
                     icsk.put("skubarcode",entry.getValue().iterator().next().getSkuBarcode());
                     //给最后销售交货接口使用
                    Map<String, Object> icsks = new HashMap<String, Object>();
                    icsks.put("itemCode",entry.getKey().trim());
                    //icsks.put("itemCode","SP000001");
                    icsks.put("skuBarcode",entry.getValue().iterator().next().getSkuBarcode().trim());
                    icsks.put("quantity",entry.getValue().size());

                    List<Map> Bdcdslist = new LinkedList<>();
                    for( int i=0;i<entry.getValue().size();i++){
                        Map<String, Object> bdcds = new HashMap<String, Object>();
                          bdcds.put("barcode",entry.getValue().get(i).getDocNum());
                          bdcds.put("isAvailableForSale",1);
                        Bdcdslist.add(bdcds);
                    }
                     //存当前款号下的唯一码
                     icsk.put("barcodeChangeDetails",Bdcdslist);
                     Isck.add(icsk);
                     Iscks.add(icsks);
                }
                parms.put("dataJson",Isck);
                String paramsJsons = JSON.toJSON(SignUtil.sign(parms, key)).toString();
                String requsetUrls = url + "/openapi/mitemBarcode/change";
                String resultJsons = sendPost(requsetUrls, paramsJsons);
                JSONObject jsonObjects = JSON.parseObject(resultJsons);
                if(jsonObjects.getString("ResultInt").equals("0")){
                    //账户余额充值和扣减
                    Map<String, Object> balanceLiftparams = new HashMap<String, Object>();
                    Map<String, Object> dJmap = new HashMap<String, Object>();
                    //@TODO 确定distrCode
                    dJmap.put("distrCode",distrCode);
                    dJmap.put("TransAmount",TransAmount);
                    dJmap.put("TranCode",shipmentOrderNo);
                    //@TODO 确定是否写死Deposit03
                    dJmap.put("TransTypeCode","Consume02");
                    //dJmap.put("Remark","测试");
                    balanceLiftparams.put("appId","BC7CEC0171504DF799CB4972541C0FXS");
                    balanceLiftparams.put("companyCode",customer);
                    balanceLiftparams.put("dataJson",dJmap);

                    String blparamsJson = JSON.toJSON(SignUtil.sign(balanceLiftparams, key)).toString();
                    String blrequsetUrl = url + "/openapi/distributor/accountBalance/change";
                    String blresultJson = sendPost(blrequsetUrl, blparamsJson);
                    JSONObject bljsonObject = JSON.parseObject(blresultJson);
                    if(jsonObjects.getString("ResultInt").equals("0")){
                        //销售交货
                        Map<String, Object> saleDeliveryparams = new HashMap<String, Object>();
                        Map<String, Object> dataJsonSDP = new HashMap<String, Object>();
                        //List<Map> linesList = new LinkedList<>();
                        dataJsonSDP.put("orderNo",destineOrderNo);
                        //@TODO 确定是否写死0
                        dataJsonSDP.put("type",1);
                        //@TODO 无法获取物流单号
                        //dataJsonSDP.put("logisticsNumber",null);
                        dataJsonSDP.put("lines",Iscks);
                        saleDeliveryparams.put("appId","BC7CEC0171504DF799CB4972541C0FXS");
                        saleDeliveryparams.put("companyCode",customer);
                        saleDeliveryparams.put("dataJson",dataJsonSDP);

                        String sdparamsJson = JSON.toJSON(SignUtil.sign(saleDeliveryparams, key)).toString();
                        String sdrequsetUrl = url + "/openapi/supply/order/delivery";
                        String sdresultJson = sendPost(sdrequsetUrl, sdparamsJson);
                        JSONObject sdjsonObject = JSON.parseObject(sdresultJson);

                    }
                }


            }

        }
        return null;
    }

    /**
     * 查询实体处理
     *
     * @param
     * @param queryWrapper
     * @return
     */
    private QueryWrapper<Indent> queryEntity(IndentQuery query, QueryWrapper<Indent> queryWrapper) {
        if (query != null) {
            if (!StringUtils.isEmpty(query.getCompanyName())) {
                queryWrapper.like("companyName", query.getCompanyName());
            }
            if (!StringUtils.isEmpty(query.getOrderNo())) {
                queryWrapper.eq("docNum", query.getOrderNo());
            }
            return queryWrapper;
        }
        return queryWrapper;
    }

}




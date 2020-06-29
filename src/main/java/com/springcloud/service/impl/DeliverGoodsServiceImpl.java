package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.dos.Indent;
import com.springcloud.bean.query.IndentQuery;
import com.springcloud.mapper.DeliverGoodsMapper;
import com.springcloud.service.DeliverGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<DeliverGoods> listByneedNo(String orderNo) {
        List<DeliverGoods> list = deliverGoodsMapper.getDeliverGoods(orderNo);
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

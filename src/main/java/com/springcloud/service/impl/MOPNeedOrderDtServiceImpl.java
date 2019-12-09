package com.springcloud.service.impl;

import com.springcloud.bean.dos.MOPNeedOrderDt;
import com.springcloud.mapper.MOPNeedOrderDtMapper;
import com.springcloud.service.MOPNeedOrderDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joe
 */
@Service
public class MOPNeedOrderDtServiceImpl implements MOPNeedOrderDtService {

    @Autowired
    MOPNeedOrderDtMapper MOPNeedOrderDtMapper;

    @Override
    public Boolean batchInsert(List<MOPNeedOrderDt> MOPNeedOrderDtList) {
        return MOPNeedOrderDtMapper.batchInsert(MOPNeedOrderDtList);
    }

    /*@PostConstruct
    private Boolean insert() {
        DataLineVO dataLineVO = OrderDetail.DataLineVO();
        List<LinesItem> linesItems = dataLineVO.getLines();
        List<LinesItem> addList = new ArrayList<>();
        LinesItem test = new LinesItem();
        test.setPurchasingQuantity("111");
        test.setItemName("444");
        test.setUnit("111");
        test.setItemQuantity(12);
        test.setQuantity(23);
        test.setSpecName("打算");
        test.setSpecCode("的");
        test.setItemCode("单位");
        test.setItemUnit("安慰道");
        test.setRemark("达");
        test.setSkuBarcode("阿瓦达");
        test.setAllocationQuantity(44);

        addList.add(test);
        for (LinesItem linesItem : linesItems) {
            LinesItem addBean = new LinesItem();
            BeanUtils.copyProperties(linesItem, addBean);
            addList.add(addBean);
        }
        return linesItemMapper.batchInsert(addList);
    }*/


}

package com.springcloud.service.impl;

import com.springcloud.bean.dos.LinesItem;
import com.springcloud.mapper.LinesItemMapper;
import com.springcloud.service.LinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joe
 */
@Service
public class LinesServiceImpl implements LinesService {

    @Autowired
    LinesItemMapper linesItemMapper;

    @Override
    public Boolean batchInsert(List<LinesItem> linesItemList) {
        return linesItemMapper.batchInsert(linesItemList);
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

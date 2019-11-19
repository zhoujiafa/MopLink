package com.springcloud.service.impl;

import com.springcloud.bean.dos.DataLine;
import com.springcloud.bean.vo.DataLineVO;
import com.springcloud.mapper.DataLineMapper;
import com.springcloud.order.OrderDetail;
import com.springcloud.service.DataLineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
/**
* @ClassName : DataLineServiceImpl
* @Description :
* @Author : Joe
* @Date: 2019/11/18 17:22
*/

@Service
public class DataLineServiceImpl implements DataLineService {

    @Autowired
    DataLineMapper dataLineMapper;


    @Override
    public Boolean insert(DataLine dataLine) {
        return dataLineMapper.insert(dataLine) > 0;
    }


    /*@PostConstruct
    private Boolean insert() {
        DataLineVO dataLineVO = OrderDetail.DataLineVO();
        DataLine addDataLine = new DataLine();
        BeanUtils.copyProperties(dataLineVO,addDataLine);
        addDataLine.setDocNum(OrderDetail.getMopPrimaryKey());
        return dataLineMapper.insert(addDataLine) > 0;
    }*/
}

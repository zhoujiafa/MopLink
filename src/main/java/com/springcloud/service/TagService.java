package com.springcloud.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.dos.Tag;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.util.ResponseBean2;

import java.util.List;

/**
* @ClassName : DeliverGoodsService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
@DS("stock")
public interface TagService {


    /**
     * 根据吊牌扫描人信息查询以往记录
     * @param phone
     * @return
     */
    List<Tag> tagListByPhone(String phone);

}

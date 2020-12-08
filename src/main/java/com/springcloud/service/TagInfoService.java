package com.springcloud.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.springcloud.bean.dos.DeliverGoods;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.TagInfoVo;
import com.springcloud.util.ResponseBean2;

import java.util.List;

/**
* @ClassName : DeliverGoodsService
* @Description : 
* @Author : Joe
* @Date: 2019/11/18 9:17
*/
@DS("stock")
public interface TagInfoService {


    /**
     *  保存吊牌
     * @param tagInfo
     * @param remaek
     * @param user
     * @return
     */
    Boolean saveTagInfo(List<TagInfo> tagInfo,String remaek,User user);

    /**
     * 根据吊牌款号查询颜色
     * @param tagCode
     * @return
     */
    ResponseBean2 tagColorByCode(List<String> tagCode,int tab);

    /**
     * 根据吊牌扫描人信息查询以往记录
     * @param phone
     * @return
     */
    List<TagInfo> tagColorByPerson(String phone);

    /**
     * 根据吊牌扫描人信息查询以往记录
     * @param uniqueCode
     * @return
     */
    TagInfo tagInfodoesItExist(String uniqueCode);



}

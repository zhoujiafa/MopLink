package com.springcloud.service.impl;

import com.springcloud.bean.dos.GoodsDict;
import com.springcloud.bean.dos.Tag;
import com.springcloud.bean.dos.TagInfo;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.TagInfoVo;
import com.springcloud.mapper.GoodsDictMapper;
import com.springcloud.mapper.TagInfoMapper;
import com.springcloud.mapper.TagMapper;
import com.springcloud.service.TagInfoService;
import com.springcloud.service.TagService;
import com.springcloud.util.ResponseBean2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagMapper tagMapper;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMss");

    @Override
    public List<Tag> tagListByPhone(String phone) {

        List<Tag> list = tagMapper.tagListByPhone(phone);

        return list;
    }
}
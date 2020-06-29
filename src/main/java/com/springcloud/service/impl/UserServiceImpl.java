package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.analyticaldata.OrderDetail;
import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.UserVO;
import com.springcloud.mapper.UserMapper;
import com.springcloud.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : Joe
 * @Date: 2019/11/18 17:22
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Boolean saveUser(UserAO userAO) {
        Boolean bool = true;
        if (userAO != null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName",userAO.getUserName());
            queryWrapper.eq("telePhone",userAO.getTelePhone());
            if(userMapper.selectOne(queryWrapper)!=null){
                return false;
            }
            User user = new User();
            BeanUtils.copyProperties(userAO, user);
            user.setId(OrderDetail.getMopPrimaryKey());
            bool = userMapper.insert(user) > 0;
        }
        return bool;
    }

    @Override
    public UserVO login(UserAO userAO) {
        UserVO userVO = new UserVO();
        if(userAO!=null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName",userAO.getUserName());
            queryWrapper.eq("password",userAO.getPassword());
            User user = userMapper.selectOne(queryWrapper);
            if(user!=null){
                BeanUtils.copyProperties(user, userVO);
            }
        }
        return userVO;
    }
}

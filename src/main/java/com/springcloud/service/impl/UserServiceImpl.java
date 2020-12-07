package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.analyticaldata.OrderDetail;
import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.UserVO;
import com.springcloud.mapper.UserMapper;
import com.springcloud.service.UserService;
import com.springcloud.util.ResponseBean;
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

    private static String audited = "已审核";

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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(userAO.getUserName()!=null && userAO.getPassword()!=null){
            queryWrapper.eq("userName",userAO.getUserName());
            queryWrapper.eq("password",userAO.getPassword());
        }else if(userAO.getPhone()!=null && userAO.getPwd()!=null){
            queryWrapper.eq("telephone",userAO.getPhone());
            queryWrapper.eq("password",userAO.getPwd());
            queryWrapper.eq("status","已审核");
        }else {
            return null;
        }
            User user = userMapper.selectOne(queryWrapper);
            if(user!=null){
                BeanUtils.copyProperties(user, userVO);
            }
        return userVO;
    }

    @Override
    public ResponseBean<String> wechatAppRegister(UserAO userAO) {
        Boolean bool = false;
        if(userAO != null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("telePhone",userAO.getTelePhone());
            User search = userMapper.selectOne(queryWrapper);
            if(search!=null){
                if(!search.getStatus().equals(audited)){
                    return ResponseBean.fail("审核中");
                }
                return ResponseBean.fail("用户已被注册");
            }else{
                User user = new User();
                BeanUtils.copyProperties(userAO,user);
                user.setStatus("待审核");
                user.setDeleted(0);
                user.setId(OrderDetail.getMopPrimaryKey());
                user.setUserName(userAO.getName());
                bool = userMapper.insert(user) > 0;
            }
        }
        return ResponseBean.ok(bool);
    }


    @Override
    public User xcxlogin(UserAO userAO) {

            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName",userAO.getUserName());
            queryWrapper.eq("telePhone",userAO.getTelePhone());


        return userMapper.selectOne(queryWrapper);

    }
}

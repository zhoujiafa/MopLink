package com.springcloud.service;

import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.dos.User;
import com.springcloud.bean.vo.UserVO;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: $ClassName$
 * @Create: $Date$ $Time$
 */
public interface UserService {

   Boolean saveUser(UserAO userAO);

   UserVO login(UserAO userAO);

}

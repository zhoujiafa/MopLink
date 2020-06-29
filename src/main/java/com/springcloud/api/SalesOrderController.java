package com.springcloud.api;

import com.springcloud.bean.ao.UserAO;
import com.springcloud.bean.vo.UserVO;
import com.springcloud.service.UserService;
import com.springcloud.util.ResponseBean2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : UserController
 * @Description : 用户数据访问接口
 * @Author : Joe
 * @Date: 2019-11-18 09:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    /*@ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{page}/{size}")
    public ResponseBean2<PageResult<UserVO>> page(@PathVariable int page, @PathVariable int size, UserQuery query) {
        return ResponseBean2.ok(userService.page(page,size,query));
    }*/

   /* @ApiOperation(value = "用户详情信息", notes = "用户详情信息")
    @PostMapping("/userInfo")
    public ResponseBean2<NeedOrderVO> getuserInfo(@RequestBody Map data) {
        String userId = data.get("userId").toString();
        String userName = data.get("userName").toString();
        List<UserVO> list = userService.getuserInfo(userId,userName);
        return ResponseBean2.ok(list);
    }*/


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public ResponseBean2<UserVO> login(@RequestBody UserAO userAO) {
        return ResponseBean2.ok(userService.login(userAO));
    }


    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/save")
    public ResponseBean2<Boolean> save(@RequestBody UserAO userAO) {

        return ResponseBean2.ok(userService.saveUser(userAO));
    }

}

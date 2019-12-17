package com.springcloud.bean.ao;

import lombok.Data;

/**
 * @Author darren.zhou
 * @Description:
 * @ClassName: ClassName
 * @Create: Date Time
 */
@Data
public class UserAO {

    /** 姓名 */
    private String name;
    /** 用户名 */
    private String userName;
     /** 登录密码 */
    private String password;
    /** 联系电话 */
    private String telePhone;
    /** 性别 */
    private Long sex;
    /** 邮箱 */
    private String email;
}

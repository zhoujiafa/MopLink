package com.springcloud.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author darren.zhou
 * @Description: 登录用户
 * @ClassName: ClassName
 * @Create: Date Time
 */
@Data
public class UserVO {

    /** 用户ID */
    private String id;
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
    /** 角色ID */
    private Long roleId;
    /** 角色名称 */
    private String roleName;
    /** 头像 */
    private String avatar;
    /** 地址 */
    private String address;
    /** 创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

}

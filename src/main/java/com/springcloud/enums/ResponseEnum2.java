package com.springcloud.enums;

import java.text.MessageFormat;

/**
* @ClassName : ResponseEnum
* @Description :
* @Author : Joe
* @Date: 2019/11/19 11:31
*/

public enum ResponseEnum2 {
    /**
     * 请求响应状态
     */

    Ok(0, "操作成功"),
    //Ok(200, "操作成功"),
    ArgumentInvalid(300, "参数不合法{0}"),
    //Custom(400, ""),
    Custom(-1, ""),
    Fail(500, "发生未知错误");

    private final int ResultInt;

    private final String ResultString;

    ResponseEnum2(int ResultInt, String ResultString) {
        this.ResultInt = ResultInt;
        this.ResultString = ResultString;
    }

    public int ResultInt() {
        return this.ResultInt;
    }

    public String ResultString() {
        return this.ResultString;
    }

    public String format(String args) {
        return MessageFormat.format(this.ResultString, args);
    }

}

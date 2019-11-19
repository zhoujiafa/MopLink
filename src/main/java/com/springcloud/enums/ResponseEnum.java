package com.springcloud.enums;

import java.text.MessageFormat;

/**
* @ClassName : ResponseEnum
* @Description :
* @Author : Joe
* @Date: 2019/11/19 11:31
*/

public enum ResponseEnum {
    /**
     * 请求响应状态
     */

    Ok(200, "OK"),
    ArgumentInvalid(300, "参数不合法{0}"),
    Custom(400, ""),
    Fail(500, "发生未知错误");

    private final int code;

    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public String format(String args) {
        return MessageFormat.format(this.message, args);
    }

}

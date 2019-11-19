package com.springcloud.util;

import com.springcloud.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 接口响应对象
 * @className: Response
 * @author: zk
 * @date: 2019-08-22 12:38
 */
@Data
@ApiModel("接口响应对象")
public class ResponseBean<T> {

    @ApiModelProperty(value = "响应状态码")
    private int code;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "响应数据",name = "响应实体")
    private T body;

    public static ResponseBean ok(Object body) {
        ResponseBean<Object> response = new ResponseBean<>();
        response.setCode(ResponseEnum.Ok.code());
        response.setMessage(ResponseEnum.Ok.message());
        response.setBody(body);
        return response;
    }

    public static ResponseBean fail() {
        ResponseBean<Object> response = new ResponseBean<>();
        response.setCode(ResponseEnum.Fail.code());
        response.setMessage(ResponseEnum.Fail.message());
        return response;
    }

    public static ResponseBean fail(String message) {
        ResponseBean<Object> response = new ResponseBean<>();
        response.setCode(ResponseEnum.Custom.code());
        response.setMessage(message);
        return response;
    }

    public static ResponseBean argumentInvalid(String... fieldNames) {
        String message = "";
        if (fieldNames != null && fieldNames.length > 0) {
            StringBuffer strBuffer = new StringBuffer();
            strBuffer.append("，参数：（");
            for (int i = 0; i < fieldNames.length; i++) {
                String field = fieldNames[i];
                if (i > 0) {
                    strBuffer.append(",");
                }
                strBuffer.append(field);
            }
            strBuffer.append("）");
            message = strBuffer.toString();
        }
        ResponseBean<Object> response = new ResponseBean<>();
        response.setCode(ResponseEnum.ArgumentInvalid.code());
        response.setMessage(ResponseEnum.ArgumentInvalid.format(message));
        return response;
    }
}

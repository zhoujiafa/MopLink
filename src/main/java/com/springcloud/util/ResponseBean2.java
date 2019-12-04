package com.springcloud.util;

import com.springcloud.enums.ResponseEnum2;
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
public class ResponseBean2<T> {

    @ApiModelProperty(value = "响应状态码")
    private int resultInt;

    @ApiModelProperty(value = "响应信息")
    private String resultString;

    @ApiModelProperty(value = "响应数据",name = "响应实体")
    private T body;

    public static ResponseBean2 ok(Object body) {
        ResponseBean2<Object> response = new ResponseBean2<>();
        response.setResultInt(ResponseEnum2.Ok.ResultInt());
        response.setResultString(ResponseEnum2.Ok.ResultString());
        response.setBody(body);
        return response;
    }

    public static ResponseBean2 fail() {
        ResponseBean2<Object> response = new ResponseBean2<>();
        response.setResultInt(ResponseEnum2.Fail.ResultInt());
        response.setResultString(ResponseEnum2.Fail.ResultString());
        return response;
    }

    public static ResponseBean2 fail(String resultString) {
        ResponseBean2<Object> response = new ResponseBean2<>();
        response.setResultInt(ResponseEnum2.Custom.ResultInt());
        response.setResultString(resultString);
        return response;
    }

    public static ResponseBean2 argumentInvalid(String... fieldNames) {
        String resultString = "";
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
            resultString = strBuffer.toString();
        }
        ResponseBean2<Object> response = new ResponseBean2<>();
        response.setResultInt(ResponseEnum2.ArgumentInvalid.ResultInt());
        response.setResultString(ResponseEnum2.ArgumentInvalid.format(resultString));
        return response;
    }
}

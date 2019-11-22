package com.springcloud.exceptions;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * @ClassName : ServiceException
 * @Description : 自定义业务异常
 * @Author : Joe
 * @Date: 2019-11-22 09:23
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(String msg, Throwable e) {
        super(msg,e);
        this.msg = msg;
    }

    public ServiceException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ServiceException(String msg,int code, Throwable e) {
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }

}

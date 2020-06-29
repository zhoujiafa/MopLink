package com.springcloud.analyticaldata;
import java.util.UUID;


/**
 * @ClassName : SystemUtil
 * @Description : 测试
 * @Author : Joe
 * @Date: 2020-03-05 08:50
 */
public class Constant {
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "woyebuzhidaoxiediansha";
    /**
     *   millisecond
     */
    public static final int JWT_TTL = 60*60*1000;
}

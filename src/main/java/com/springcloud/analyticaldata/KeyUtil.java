package com.springcloud.analyticaldata;
import com.springcloud.util.MD5Util;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName : KeyUtil
 * @Description : 生成密钥
 * @Author : Joe
 * @Date: 2020-03-05 08:50
 */
public class KeyUtil {


    public static void main(String[] args) {


        String key = "直营仓Key";
        String keyValue=MD5Util.getMD5(key);
        int k = 15;
        System.out.println(keyValue.substring(keyValue.length()-k));

        String secret = "直营仓Secret";
        String secretValue=MD5Util.getMD5(secret);
        int s = 18;
        System.out.println(secretValue.substring(keyValue.length()-s));



        System.out.println("java生成随机数字和字母组合10位数：" + getRandomNickname(15));

    }



    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
                // 数字
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


}

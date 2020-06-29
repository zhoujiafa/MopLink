package com.springcloud.analyticaldata;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.bean.vo.SalesOrderVO;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
/**
 * @ClassName : SystemUtil
 * @Description : 测试
 * @Author : Joe
 * @Date: 2020-03-05 08:50
 */
public class SystemUtil {
    public final static String METHOD_GET="GET";
    public final static String METHOD_PUT="PUT";
    public final static String METHOD_DELETE="DELETE";
    public final static String METHOD_POST="POST";
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static HashMap<String,String> rest(String serviceUrl,String parameter,String restMethod){
        try {
            URL url= new URL(serviceUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(restMethod);
            con.setConnectTimeout(300000);
            con.addRequestProperty("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(parameter.getBytes("UTF-8"));
            os.close();


            HashMap<String,String> result=new HashMap<String,String>();
            result.put("code",String.valueOf(con.getResponseCode()));
            result.put("msg",con.getResponseMessage());

            //读取返回信息
            InputStream inputStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String  strMessage;
            StringBuffer buffer=new StringBuffer();
            while ((strMessage = reader.readLine()) != null) {
                buffer.append(strMessage);
            }
            result.put("result",buffer.toString());
            return result;
        } catch ( Exception e ) {
            HashMap<String,String> result=new HashMap<String,String>();
            result.put("code","0");
            result.put("msg",e.getMessage());
            result.put("result","Failed,Pls Check your url to verify it right") ;
            return result;
        }
    }

    //字符串按字母升序排序
    public static String strAsc(String origin){
        StringBuffer result=new StringBuffer();
        //去掉空格、转化成小写、升序
        char [] originChars=origin.replace(" ","").toLowerCase().toCharArray();
        Arrays.sort(originChars);
        for(char s: originChars){
            result.append(s);
        }
        return result.toString();
    }

    //md5加密
    public static String strMd5(String origin){
        String result="";
        try{
            result= SystemUtil.encodeByMD5(origin, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * encode By MD5
     *
     * @param str
     * @param encode
     * @return String
     */
    public static String encodeByMD5(String str,String encode) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(encode));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static   HashMap<String,String> invoke(String pUrl,String pMethod, String pUser, String pSession, String pFormat,Map<String,String> data ){
        StringBuilder pStr=new StringBuilder();
        pStr.append("user=").append(SystemUtil.encodeURL(pUser,"utf-8"));
        pStr.append("&").append("method=").append(SystemUtil.encodeURL(pMethod,"utf-8"));
        if(!isBlank(pFormat)) {
            pStr.append("&").append("format=").append(SystemUtil.encodeURL(pFormat, "utf-8"));
        }
        String str=pMethod+pSession;
        for(String key:data.keySet()){
            str=str+key+data.get(key);
            pStr.append("&").append(key).append("=").append(SystemUtil.encodeURL(data.get(key),"utf-8"));
        }

        String token=strMd5(strAsc(str));
        pStr.append("&").append("token=").append(SystemUtil.encodeURL(token,"utf-8"));
        return rest(pUrl,pStr.toString(),SystemUtil.METHOD_POST);
    }

    /**
     * 对url进行编码
     */
    public static String encodeURL(String url,String charset) {
        try {
            return URLEncoder.encode(url, charset).replace("+","%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static void main(String[] args)
    {
        HashMap<String,String> data=new HashMap<String, String>();
        data.put("appKey","U1CITYCKTEST");
        //data.put("billNo","XSC1901241502814495");
        //data.put("pageIndex","1");
        data.put("pageSize","500");
        data.put("startTime","2020-03-01");
        data.put("endTime","2020-03-09");

        /*data.put("expCode","DHL00023923");
        data.put("expName","DHL");

        data.put("orderId","2014060300001375");
        data.put("cancelReason","Warehouse cancel");
        data.put("proSkuNo","0404002");
        data.put("status","");
        data.put("orderStatus","4");
        data.put("proNum","61");*/

        //文档中LIST对象属性如此传值即可（如需要增加对象属性值，先转成json类型）
        //String jsonPro = "[{\"proTitle\":\"维摩族2014新款初秋情侣款男时尚圆领宽松短袖T恤打底衫VMODERN\",\"proNo\":\"V1499010\",\"proSku\":\"V1499010106108 \",\"proPrice\":\"379\",\"proCount\":\"1\"},{\"proTitle\":\"德国Hipp喜宝有机免敏小米米粉米糊 4个月以上 \",\"proNo\":\"SN88888888\",\"proSku\":\"SN88888888 \",\"proPrice\":\"200\",\"proCount\":\"1\"}]";
        //data.put("OrderPro",jsonPro);


        //测试系统 U1CITYCKTEST  U1CITYCKTESTJJKIUNHB 有效可查数据
        HashMap<String,String> restJson=invoke("http://wqbopenapi.ushopn6.com/wqbnew/api.rest","IOpenAPI.GetSaleStock","U1CITYCKTEST","U1CITYCKTESTJJKIUNHB","json",data);
        //正式系统 U1CITYCKTEST  U1CITYCKTESTJJKIUNHB 无效
        HashMap<String,String> restJsons=invoke("http://39.98.246.54/Jizn/api.rest","IOpenAPI.GetOrder","U1CITYCKTEST","U1CITYCKTESTJJKIUNHB","json",data);
        System.out.println(restJsons);

        String mapjson = restJson.get("result");
        JSONObject jsonObj1 = JSONObject.parseObject(mapjson);
        JSONArray array = jsonObj1.getJSONArray("Result");


        List<SalesOrderVO> list =(getJsonArrayItem(array));

        System.out.println(list);

    }

    /**
     * 依次取出JSONArray中的值
     */
    public static List<SalesOrderVO> getJsonArrayItem(JSONArray array)  {
        List<SalesOrderVO> list = new ArrayList<>();
        if (array != null) {
            for (int i=0;i<array.size();i++) {
                SalesOrderVO salesOrderVO = new SalesOrderVO();
                String lines = array.getString(i);
                JSONObject jsonObj1 =  JSONObject.parseObject(lines);
                salesOrderVO.setBillNo(jsonObj1.getString("BillNo"));
                salesOrderVO.setOrderNo(jsonObj1.getString("OrderNo"));
                list.add(salesOrderVO);
            }
        }
        return list;
    }
}

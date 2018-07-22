package com.dgjs.es.client.init;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;  
import java.util.TreeMap;  
  
  
import org.apache.commons.codec.digest.DigestUtils;
  
/** 
 * 类MD5Sign.java的实现描述：MD5签名和验签 
 *  
 * @author leon 2016年10月10日 下午2:52:04 
 */  
public class MD5Sign {  
  
    /** 
     * 方法描述:将字符串MD5加码 生成32位md5码 
     */  
    public static String md5(String inStr) {  
        try {  
            return DigestUtils.md5Hex(inStr.getBytes("UTF-8"));  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("MD5签名过程中出现错误");  
        }  
    }  
  
    /** 
     * 方法描述:签名字符串 
     * @param params 需要签名的参数 
     * @param appSecret 签名密钥 
     * @return 
     */  
    public static String sign(HashMap<String, String> params, String appSecret) {  
        StringBuilder valueSb = new StringBuilder();  
        params.put("appSecret", appSecret);  
        // 将参数以参数名的字典升序排序  
        Map<String, String> sortParams = new TreeMap<String, String>(params);  
        Set<Entry<String, String>> entrys = sortParams.entrySet();  
        // 遍历排序的字典,并拼接value1+value2......格式  
        for (Entry<String, String> entry : entrys) {  
            valueSb.append(entry.getValue());  
        }  
        params.remove("appSecret");  
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String sign = md5(valueSb.toString());
        sign = md5(sign+appSecret+params.get("timestamp"));
        return sign;  
    }  
  
    /** 
     * 方法描述:验证签名 
     * @param appSecret 加密秘钥 
     * @param request 
     * @return 
     * @throws Exception 
     */  
    public static boolean verify(String appSecret, HashMap<String, String> params) throws Exception {  
        String sign = params.remove("sign");
        String timestamp= params.remove("timestamp");
        params.put("appSecret", appSecret);  
        // 将参数以参数名的字典升序排序  
        Map<String, String> sortParams = new TreeMap<String, String>(params);  
        Set<Entry<String, String>> entrys = sortParams.entrySet();  
  
        // 遍历排序的字典,并拼接value1+value2......格式  
        StringBuilder valueSb = new StringBuilder();  
        for (Entry<String, String> entry : entrys) {  
            valueSb.append(entry.getValue());  
        }  
        String mysign = md5(valueSb.toString());  
        mysign = md5(mysign+appSecret+timestamp);
        if (mysign.equals(sign)) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /*
     * 暂时不用
     */
    public static String getMD5(String message) {  
        MessageDigest messageDigest = null;  
        StringBuffer md5StrBuff = new StringBuffer();  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(message.getBytes("UTF-8"));  
              
            byte[] byteArray = messageDigest.digest();  
            for (int i = 0; i < byteArray.length; i++)   
            {  
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
                else  
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
            }  
        } catch (Exception e) {  
            throw new RuntimeException();  
        }  
        return md5StrBuff.toString().toUpperCase();//字母大写  
    }  
    
    public static void main(String[] args) throws Exception {
    	long start = System.currentTimeMillis();
    	String appSecret = "abcdefg";
    	HashMap<String, String> params = new HashMap<String, String>();
    	params.put("name", "joyance");
    	params.put("price", "200");
    	params.put("flag", "3");
    	String sign = MD5Sign.sign(params, appSecret);
    	System.out.println("sign="+sign+" sign_length:"+sign.length());
    	params.put("sign", sign);
    	
//    	params.put("price", "100");
    	boolean isVerifySuccess = MD5Sign.verify(appSecret, params);
    	long end = System.currentTimeMillis();
    	System.out.println("time used="+(end-start));
    	System.out.println(isVerifySuccess);
	}
}  

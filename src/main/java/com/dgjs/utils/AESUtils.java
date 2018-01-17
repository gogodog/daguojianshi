package com.dgjs.utils;


import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;

public class AESUtils {

	 public String Encrypt(String str) {  
	        try{  
	            String key = "1468133156538546"; //密钥  
	            byte[] kb = key.getBytes("utf-8");  
	            SecretKeySpec sks = new SecretKeySpec(kb, "AES");  
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式  
	            cipher.init(Cipher.ENCRYPT_MODE, sks);  
	            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));  
	            return new Base64().encodeToString(eb);  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	 
	   //解密  
	    public String Decrypt(String str) {  
	        try{  
	            String key = "1468133156538546"; //密钥  
	            byte[] kb = key.getBytes("utf-8");  
	            SecretKeySpec sks = new SecretKeySpec(kb, "AES");  
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
	            cipher.init(Cipher.DECRYPT_MODE, sks);  
	            byte[] by = new Base64().decode(str);  
	            byte[] db = cipher.doFinal(by);  
	            return new String(db);  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	 
	    /**将二进制转换成16进制 
	     * @param buf 
	     * @return 
	     */  
	    public static String parseByte2HexStr(byte buf[]) {  
	            StringBuffer sb = new StringBuffer();  
	            for (int i = 0; i < buf.length; i++) {  
	                    String hex = Integer.toHexString(buf[i] & 0xFF);  
	                    if (hex.length() == 1) {  
	                            hex = '0' + hex;  
	                    }  
	                    sb.append(hex.toUpperCase());  
	            }  
	            return sb.toString();  
	    }  
	    
	    /**将16进制转换为二进制 
	     * @param hexStr 
	     * @return 
	     */  
	    public static byte[] parseHexStr2Byte(String hexStr) {  
	            if (hexStr.length() < 1)  
	                    return null;  
	            byte[] result = new byte[hexStr.length()/2];  
	            for (int i = 0;i< hexStr.length()/2; i++) {  
	                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                    result[i] = (byte) (high * 16 + low);  
	            }  
	            return result;  
	    }  
	    
	    
	    public static int getX(){
          String x = "abc";	    
          try{
        	  int y=Integer.parseInt(x);
          }catch(Exception e){
        	  e.printStackTrace();
          }
          return 0;
	    }
	    
	 public static void main(String[] args) {
//		Random random = new Random();
//		StringBuilder str = new StringBuilder();
//		for(int i=0;i<16;i++){
//			str.append(random.nextInt(10));
//		}
//		System.out.println(str);
		
		AESUtils aes=new AESUtils();  
	    String en = aes.Encrypt("123");  
	    System.out.println("加密:"+en);  
	    String de = aes.Decrypt("/5jrW9RUWddvEI7I1ghV/A==");  
	    System.out.println("解密:"+de);  
	    
	    AESUtils.getX();
	}
}

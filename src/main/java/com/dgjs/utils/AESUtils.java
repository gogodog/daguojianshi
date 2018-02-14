package com.dgjs.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESUtils {

	public static String Encrypt(String str, String key) {
		try {
			byte[] kb = key.getBytes("utf-8");
			SecretKeySpec sks = new SecretKeySpec(kb, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 算法/模式/补码方式
			cipher.init(Cipher.ENCRYPT_MODE, sks);
			byte[] eb = cipher.doFinal(str.getBytes("utf-8"));
			return new Base64().encodeToString(eb);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String Decrypt(String str, String key) {
		try {
			byte[] kb = key.getBytes("utf-8");
			SecretKeySpec sks = new SecretKeySpec(kb, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, sks);
			byte[] by = new Base64().decode(str);
			byte[] db = cipher.doFinal(by);
			return new String(db);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将二进制转换成16进制
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

	/**
	 * 将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}

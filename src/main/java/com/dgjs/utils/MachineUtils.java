package com.dgjs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineUtils {
	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
	// 字符串在编译时会被转码一次,所以是 "\\b"
	// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
	static String phoneReg = "\\bNOKIA|SAMSUNG|MIDP-2|CLDC1.1|SYMBIANOS|MAUI|UNTRUSTED/1.0"
			+ "|WINDOWS CE|IPHONE|IPAD|ANDROID|BLACKBERRY|UCWEB|UCWEB|BREW|J2ME|YULONG|YULONG|COOLPAD|TIANYU|TY-"
			+ "|K-TOUCH|HAIER|DOPOD|LENOVO|LENOVO|HUAQIN|AIGO-|CTC/1.0"
			+ "|CTC/2.0|CMCC|DAXIAN|MOT-|SONYERICSSON|GIONEE|HTC|ZTE|HUAWEI|WEBOS|GOBROWSER|IEMOBILE|WAP2.0\\b";
	static String tableReg = "\\b(IPAD|TABLET|(NEXUS 7)|UP.BROWSER" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	// 移动设备正则匹配：手机端、平板
	static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
	static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

	/**
	 * 检测是否是移动设备访问
	 */
	public static boolean check(String userAgent) {
		if (null == userAgent) {
			userAgent = "";
		}
		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent.toUpperCase());
		Matcher matcherTable = tablePat.matcher(userAgent.toUpperCase());
		if (matcherPhone.find() || matcherTable.find()) {
			return true;
		} else {
			return false;
		}
	}
}

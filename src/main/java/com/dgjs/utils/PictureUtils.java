package com.dgjs.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

public class PictureUtils {

	public final static String URL_SEPERATOR = "/";

	public static final String SYSTEM_FILE_SEPERATOR = System.getProperty("file.separator");

	public static final String SUFFIX = ".jpg";

	/**
	 * 日期+6位随机数
	 */
	public static String generateImageName() {
		String str = DateUtils.parseStringFromDate(new Date(), "yyyyMMddhhmmss");
		String random = getRandomNumber();
		return StringUtils.jointString(str, random);
	}

	public static String getRandomNumber() {
		Random random = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

	private static void initDir(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static String getPath(String imagePath, String basePath, String seperator, boolean isNeedInitDir) {
		if (basePath.endsWith("/")) {
			basePath = basePath.substring(0, basePath.length() - 1);
		}
		String[] paths = imagePath.split("/");
		for (String path : paths) {
			basePath = StringUtils.jointString(basePath, seperator, path);
			if (isNeedInitDir)
				initDir(new File(basePath));
		}
		return basePath;
	}

	public static String getImageSavePath(String saveRealBasePath, String imagePath, HttpServletRequest request, String imageName) {
		// saveRealBasePath=系统路径+存放图片文件夹名称
		String path = getPath(imagePath, saveRealBasePath, SYSTEM_FILE_SEPERATOR, true);
		String imageSavePath = StringUtils.jointString(path, SYSTEM_FILE_SEPERATOR, imageName, SUFFIX);
		System.out.println("imageSavePath=" + imageSavePath);
		return imageSavePath;
	}

	public static String getImageAccessPath(String basePath, String imagePath, HttpServletRequest request, String imageName) {
		// basePath=存放图片文件夹名称
		String path = getPath(imagePath, basePath, URL_SEPERATOR, false);
		String imageAccessPath = StringUtils.jointString(path, URL_SEPERATOR, imageName, SUFFIX);
		System.out.println("imageAccessPath=" + imageAccessPath);
		return imageAccessPath;
	}

	public static String thumbnailatorImage(String maxfilePath, String minFilePath, float scale)throws IOException {
		Thumbnails.of(maxfilePath).scale(scale).toFile(minFilePath);
		String minHttpPath = minFilePath;
		return minHttpPath;
	}
}

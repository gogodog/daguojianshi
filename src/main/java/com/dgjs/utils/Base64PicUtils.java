package com.dgjs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Base64PicUtils {
	/** 
     * 替换html中的base64图片数据为实际图片 
     * @param html 
     * @param fileRoot 本地路径 
     * @param serRoot 服务器路径 
     * @return 
     */  
    public static String replaceBase64Image(String html,String fileRoot,String serRoot){  
        File file = new File(fileRoot);  
        if(!file.exists()){//文件根目录不存在时创建  
            new File(fileRoot).mkdirs();  
        }  
        String htmlContent = html;  
        Pattern pattern = Pattern.compile("\\<img[^>]*src=\"data:image/[^>]*>");  
        Matcher matcher = pattern.matcher(html);      
        while(matcher.find()){      //找出base64图片元素    
            String str = matcher.group();     
            String ext = str.split("image/")[1].split(";base64")[0];//图片后缀     
            String base64ImgData = str.split("base64,")[1];//图片数据  
            base64ImgData=base64ImgData.split("\"")[0];
            if(!StringUtils.isNullOrEmpty(ext)&&!StringUtils.isNullOrEmpty(base64ImgData)){  
                //data:image/gif;base64,base64编码的gif图片数据  
                //data:image/png;base64,base64编码的png图片数据  
                if("jpeg".equalsIgnoreCase(ext)){//data:image/jpeg;base64,base64编码的jpeg图片数据  
                    ext = "jpg";  
                } else if("x-icon".equalsIgnoreCase(ext)){//data:image/x-icon;base64,base64编码的icon图片数据  
                    ext = "ico";  
                }  
                String fileName = PictureUtils.generateImageName()+"."+ext;//待存储的文件名  
                String filePath = fileRoot+File.separator+fileName;//图片路径  
                try {  
                    convertBase64DataToImage(base64ImgData, filePath);//转成文件  
//                    String serPath = serRoot+fileName;//服务器地址  
//                    htmlContent = htmlContent.replace(src, serPath);//替换src为服务器地址  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }         
            }             
        }     
        return htmlContent;  
    }  
    /** 
     * 把base64图片数据转为本地图片 
     * @param base64ImgData 
     * @param filePath 
     * @throws IOException 
     */  
    @SuppressWarnings("restriction")
	public static void convertBase64DataToImage(String base64ImgData,String filePath) throws IOException {  
    	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
    	byte[] bs = decoder.decodeBuffer( base64ImgData );    
        FileOutputStream os = new FileOutputStream(filePath);  
        os.write(bs);  
        os.close();  
    }  

    public static void main(String[] args) {
    	String html="<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAJAQMAAADaX5RTAAAAA3NCSVQICAjb4U/gAAAABlBMVEX///+ZmZmOUEqyAAAAAnRSTlMA/1uRIrUAAAAJcEhZcwAACusAAArrAYKLDVoAAAAWdEVYdENyZWF0aW9uIFRpbWUAMDkvMjAvMTIGkKG+AAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAAB1JREFUCJljONjA8LiBoZyBwY6BQQZMAtlAkYMNAF1fBs/zPvcnAAAAAElFTkSuQmCC\" />";
    	Base64PicUtils.replaceBase64Image(html, "/Users/user/Documents", "");
	}
}

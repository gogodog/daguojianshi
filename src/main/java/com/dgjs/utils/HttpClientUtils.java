package com.dgjs.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

public class HttpClientUtils {

	/**
     * 中转文件
     * 
     * @param file
     *            上传的文件
     * @return 响应结果
     */
    public static String uploadFile(String remote_url,List<MultipartFile> multipartFiles,String charset,String path){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig  requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(remote_url);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for(MultipartFile multipartFile:multipartFiles){
            	builder.addBinaryBody(multipartFile.getName(), multipartFile.getInputStream(), 
            			ContentType.create(multipartFile.getContentType(),Consts.UTF_8), multipartFile.getOriginalFilename()).setCharset(Consts.UTF_8);// 文件流
            }
            builder.addTextBody("imagePath", path);// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity,charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

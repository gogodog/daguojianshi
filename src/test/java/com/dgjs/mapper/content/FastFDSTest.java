package com.dgjs.mapper.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class FastFDSTest {

	@Test
    public void testUpload() throws Exception {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("/Users/user/Documents/workspaces/elong_workspace_b170327/daguojianshi/src/test/resources/config.properties");
        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        
        // 上传文件
//        uploadFile(storageClient,"/Users/user/Downloads/hdImg_a574f8b4611a2ff64d203dffe1924d3515115068177.jpg");
        // 上传文件
//        uploadFile(storageClient,"/Users/user/Downloads/hdImg_e92e084f17d1386890e173076306fdc815115068408.jpg");
        // 上传文件
        uploadGroupFile(storageClient,"/Users/user/Documents/pic/默认图片.jpg");
        // 删除文件
//        deleteFile(storageClient,"group1","M00/00/00/rBHGsFn73Z-Aa5JoAAAbCGPhKqk109.jpg");
        
        // 查询metadata
//        NameValuePair[] pairs = new NameValuePair[2];
//        pairs[0] = new NameValuePair();
//        pairs[0].setName("uid");
//        pairs[0].setValue("1");
//        pairs[1] = new NameValuePair();
//        pairs[1].setName("x");
//        pairs[1].setValue("abc");
//        int number = storageClient.set_metadata("group1", "M00/00/00/eSqPpln5h7aAXY6uAAAbCGPhKqk755.jpg", pairs, ProtoCommon.STORAGE_SET_METADATA_FLAG_OVERWRITE);
//        System.out.println(number);
//        
//        pairs = storageClient.get_metadata("group1", "M00/00/00/eSqPpln5h7aAXY6uAAAbCGPhKqk755.jpg");
//        for(NameValuePair nvp:pairs){
//      	  System.out.println("name:"+nvp.getName()+",value:"+nvp.getValue());
//        }
//        uploadFile2(storageClient,"/Users/user/Documents/pic/guanggao.png");
//        uploadAppenderFile(storageClient,"/Users/user/Documents/pic/guanggao.png");
//        truncateFile(storageClient,"M00/00/00/rBHGsFn8IDmEEw7VAAAAAGPhKqk398.jpg");
    }
	
	/*
	 * 上传文件
	 */
	private void uploadFile(StorageClient storageClient,String filePath) throws IOException, MyException{
		//直接调用StorageClient对象方法上传文件即可。
		String[] strings = storageClient.upload_file(filePath,"jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println("上传文件成功");
	}
	
	/*
	 * 删除文件
	 */
	private void deleteFile(StorageClient storageClient,String groupName,String filePath) throws IOException, MyException{
		int number=storageClient.delete_file(groupName, filePath);
		System.out.println(number);
	}
	
	/*
	 * 上传文件2
	 */
	private void uploadFile2(StorageClient storageClient,String filePath) throws IOException, MyException{
		String[] values=storageClient.upload_file("group1", "M00/00/00/rBHGsFn4koiAP5QKAAB4-4yq-KU136.jpg", "_10_10", filePath, "jpg", null);
	    for(String value:values){
	    	System.out.println(value);
	    }
	}
	
	/*
	 * 上传可修改文件
	 */
	private void uploadAppenderFile(StorageClient storageClient,String filePath) throws IOException, MyException{
		String[] values= storageClient.upload_appender_file(filePath, "jpg", null);
		for(String value:values){
			System.out.println(value);
		}
	}
	
	/*
	 * 压缩文件(不知道咋玩)
	 */
	private void truncateFile(StorageClient storageClient,String filePath) throws IOException, MyException{
		int number = storageClient.truncate_file("group1", filePath,6920);
		System.out.println(number);
	}
	
	private void uploadGroupFile(StorageClient storageClient,String filePath) throws IOException, MyException{
	    FileInputStream file = new FileInputStream(filePath);
	    List<Byte> list = new LinkedList<>();
	    byte[] bytes = new byte[1024];
	    while(file.read(bytes)!=-1){
	    	for(byte b:bytes){
	    		list.add(b);
	    	}
	    }
	    if(list!=null && list.size()>0){
	    	int index = 0;
	    	byte[] array = new byte[list.size()];
	    	for(byte b:list){
	    		array[index++]=b;
	    	}
	    	String[] values=storageClient.upload_file("group2", array, "jpg", null);
	    	for(String value:values){
				System.out.println(value);
			}
	    }
	}
}

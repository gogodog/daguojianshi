package com.dgjs.test.es;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class EsClient {
	private static Client client;
	static{
		EsClient.client = EsClient.initClient();
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private static Client initClient(){
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		try {
			return new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Client getInstence(){
		if(client == null)
			return EsClient.initClient();
		else
			return EsClient.client;
	}
	
	public static void close(){
		if(EsClient.client != null)
			EsClient.client.close();
	}
}

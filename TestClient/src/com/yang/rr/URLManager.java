package com.yang.rr;

import java.util.ArrayList;
import java.util.List;

public class URLManager {
	
	private static URLManager instance = null;
	private RoundRobin rr = null;
	
	public static URLManager getInstance() {
		if(instance == null) {
			instance = new URLManager();
		}
		
		return instance;
	}
	
	private URLManager() {
		String urls = Constants.URLS;
		String[] urlArray = urls.split(Constants.URL_DELIMITER);
		
		List<String> list = new ArrayList<String>();
	
		for(String url:urlArray) {
			
			list.add(url);
		}
		
		rr = new RoundRobin(list);
		
		
		rr.start();
		
	}
	
	//roundrobin 방식으로 쓰레드 돌면서 다음 url가져오기
	public String getURL() {
		return rr.next();
	}

	public void fail(String url) {
		System.out.println("is fail : " +  url);
		rr.isFail(url);
	
	}
	

}

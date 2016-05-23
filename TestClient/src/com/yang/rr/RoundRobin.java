package com.yang.rr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundRobin extends Thread {
	
	private int curr_idx = -1;
	private List<String> validList;
	private List<String> invalidList;
	
	private final Object syncValidObj = new Object();
	private final Object syncinvalidObj = new Object();
	    
	public RoundRobin(List<String> validList) {
		this.validList = validList;
		curr_idx = 0;
		if(invalidList == null) {
			invalidList = new ArrayList<String>();
		}
	}
	 
	//validList에서 다음 url가져오는 함수
	public String next() {
		int size = validList.size();
		
		//validList가 비어있으면 return null
		if(size  == 0) {
			curr_idx = -1;
			return null;
		} 
		
		//circular 구현?
		if (curr_idx >= validList.size()) {
			curr_idx = 0;
		} 
		
		String returnValue = null;
		try {
			returnValue = validList.get(curr_idx);
			curr_idx++;
		} catch(Exception e) {
			e.printStackTrace();
			returnValue = null;
		}
		
		System.out.println("size : " + size + "  current index : " + (curr_idx-1));
		
		System.out.println(toString());
		
		return returnValue;

	}
	
	public void isFail(String url) {
		System.out.println("isFail==============" + url);
		
		this.addInvalidList(url);
		this.removeValidList(url);
	
//		System.out.println(toString());
	}

	private void isSuccess(String url) {
		System.out.println("isSuccess==============" + url);
		this.addValidList(url);
		this.removeInvalidList(url);
//		System.out.println(toString());
	}
	
	private void addValidList(String url) {
		int size;
		synchronized (syncValidObj) {
			validList.add(url);
			
			//why?
			size = validList.size();
			
			if(curr_idx < 0) {
				curr_idx = 0;
			} 
		}
	}
	
	private void removeValidList(String url) {
		int size;
		synchronized (syncValidObj) {
			validList.remove(url);
			size = validList.size();
			if(size == 0) {
				curr_idx = -1;
			} else if(curr_idx > size) {
				System.out.println("\n\ncurr_idx > size ::::::::::: " + curr_idx + ",  " + size + "\n\n\n");
				curr_idx = 0;
			} else {
				if(curr_idx == size)
					System.out.println("\n\ncurr_idx == size ::::::::::: " + curr_idx + ",  " + size + "\n\n\n");
				
				curr_idx--;
			}
		}
	}
	
	
	
	
	
	
	private void addInvalidList(String url) {
		synchronized (syncinvalidObj) {
			invalidList.add(url);
		}
	}
	
	private void removeInvalidList(String url) {
		synchronized (syncinvalidObj) {
			invalidList.remove(url);
		}
	}
	
	
	
	public void run() {
		int invalidListSize = 0;
		boolean result = false;
		List<String> cloneInvalidList = null;
	
		String invalidURL = null;
		while(true) {
			cloneInvalidList = getCloneInvalidList();
			invalidListSize = cloneInvalidList.size();
			
			//invalidList에 data가 존재할 때만 체크
			if(invalidListSize > 0 ) {
				System.out.println("invalid url cnt : " + invalidListSize);
				
				result = false;
				
				for(int index = 0; index < invalidListSize; index++) {
					invalidURL = cloneInvalidList.get(index);
					for(int i = 0; i < Constants.URL_CHECK_CNT; i++) {
						result = callInvalidURL(invalidURL);
						if(result) break;
					}
					
					System.out.println("invalid url : " + invalidURL + " -- final result :"+ result);
					if(result) {
						isSuccess(invalidURL);
					}
				}
				
				
			}
			
			try {
				Thread.sleep(Constants.URL_CHECK_TIME_MS);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	private List<String> getCloneInvalidList() {
		List<String> clone = new ArrayList<String>();
		int size = this.invalidList.size();
		for(int i=0; i < size; i++) {
			clone.add(this.invalidList.get(i));
		}
		return clone;
	}

	private boolean callInvalidURL(String string) {
		
		Random r = new Random();
		return (r.nextInt()%5 == 0)?true:false;
		
//		return false;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("ValidList\n");
		sb.append("-. size :"+ this.validList.size()+"\n");
		if(this.validList.size() > 0) {
			sb.append("-. value:");
			for(int i=0; i < this.validList.size(); i++) {
				sb.append(this.validList.get(i)+",");
			}
			sb.append("\n");
		}
		
		sb.append("InvalidList\n");
		sb.append("-. size :"+ this.invalidList.size()+"\n");
		if(this.invalidList.size() > 0) {
			
			sb.append("-. value:");
			for(int i=0; i < this.invalidList.size(); i++) {
				sb.append(this.invalidList.get(i)+",");
			}
			sb.append("\n");
		}	
		return sb.toString();
	}
}

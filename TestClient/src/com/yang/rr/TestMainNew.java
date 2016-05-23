package com.yang.rr;

import java.util.Random;

public class TestMainNew {

	public static void main(String[] args) {
		int cnt = 0;
		while(cnt < 200) {
			String url = URLManager.getInstance().getURL();
			
			System.out.println("\ncnt: " + cnt + "  pick url : " + url);
			
			//url을 가져왔을 때 랜덤하게 fail() 호출
			if(url != null) {
				Random r = new Random();
				if(!r.nextBoolean()) {
					URLManager.getInstance().fail(url);
				}				
			}
			
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnt++;
		}
		
		
	}

}

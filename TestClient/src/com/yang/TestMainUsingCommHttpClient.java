//package com.yang;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.httpclient.HostConfiguration;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.methods.PostMethod;
//
//import com.tys.frameplus.core.DataContainer;
//import com.tys.frameplus.core.DataWindow;
//
//public class TestMainUsingCommHttpClient {
//	private static final String DEFAULT_ENCODING = "EUC-KR";
//	
//	
//	private String url;
//	private HostConfiguration hostConfig;
//    private String encoding;
//    
//    private static final String[] USER_HEADER_KEYWORDS = { 
//    	"LGNIP", "LGNID", "LGNTIME",  "DLNG_STRT_TIME", "TASK_ID", "OP_CODE", "g_ssoToken", "_user_log_yn" };
//	
//    
//    private Map<String, DataContainer> datacontainers;
//    
//    private DataContainer targetObj;
////    private Map param;
//    
//    private Map<String, String> parameters;
//
//	public TestMainUsingCommHttpClient(String url, String encoding) {
//		super();
//		this.url = url;
//		if(encoding == null) this.encoding = DEFAULT_ENCODING;
//		else this.encoding = encoding;
//		
//		this.parameters = new HashMap<String, String>();
//		this.datacontainers = new HashMap<String, DataContainer>();
//	}
//
//
//	public static void main(String[] args) {
//		DataContainer requestDC = new DataContainer();
//		DataWindow dw = new DataWindow(10, 10, "myDW");
//		dw.addRow(0, new String[]{"1","2"});
//		dw.addRow(1, new String[]{"11","22"});
//		requestDC.addDataWindow(dw);
//		requestDC.setParameter("param1", "val1");
//		
////		TestMainUsingCommHttpClient http = new TestMainUsingCommHttpClient("http://localhost:7001/test/MyServlet", null);
////		TestMainUsingCommHttpClient http = new TestMainUsingCommHttpClient("http://128.134.74.27:20001/test/MyServlet", null);
//				
//		requestDC.setParameter("name","TestMain");
//		
//		HashMap<String, String> header = new HashMap<String, String>();
//		header.put("TaskID", "FDG_HelloWorld");
//		header.put("OpCode", "_R");
//		
//		requestDC.setObject("_H", header);
//		
//		HashMap<String, String> userHeader = new HashMap<String, String>();
//		
//		for(String key: USER_HEADER_KEYWORDS) {
//			userHeader.put( key, "***");
//		}
//		
//		requestDC.setObject("_U", userHeader);
//		TestMainUsingCommHttpClient http = new TestMainUsingCommHttpClient("http://128.134.74.27:20001/eGIS/MESubscribeObj/FDG_HelloWorld", "EUC-KR");
//		
//	    Object result = http.addParam("requestDC", requestDC)
//	                       .submit();
//	    
//	    
//	  
//	                   
//	         
////		 System.out.println(result);
//		 
//		 DataContainer responseDC = null;
//		 if(result instanceof DataContainer) {
//			 responseDC = (DataContainer)result;
//			 System.out.println(responseDC);
//			 System.out.println("ok");
//		 }
//		 
//	    
//
//	}
//	
//	
//	public Object submit() {
//		
//		HttpClient httpclient = new HttpClient();
//		PostMethod httppost = new PostMethod(url);
//		  
//		Object result = null;
//		  
//		for(String key:parameters.keySet()) {
//			httppost.setParameter(key, parameters.get(key));
//			System.out.println(key + ": " + parameters.get(key));
//		}
//		  
//		if(targetObj != null) {
//			httppost.setRequestEntity(new ObjectRequestEntity(targetObj));
//					  
//		}
//		  
//		int status = 0;
//		try {
//			status = httpclient.executeMethod(httppost);
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  
//		  if( status == HttpStatus.SC_OK ) {
//			  
////			  Reader reader = new InputStreamReader(
////					  httppost.getResponseBodyAsStream(), httppost.getResponseCharSet()); 
//				  ObjectInputStream ois = null;
//				try {
//					ois = new ObjectInputStream(httppost.getResponseBodyAsStream());
//				
//					result = ois.readObject();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} finally {
//			  
//					httppost.releaseConnection();
//		
//				}
//				
//	        }
//	        else {
//	        	System.out.println("url:" + url + " (Error:"+status+") "+HttpStatus.getStatusText(status));
//	        	return result;
//	        }
//
//		  
//		  
//		  
//		
//		
////        hostConfig = new HostConfiguration();
////		this.hostConfig.setHost(url);
////		HostParams params;
////		params = new HostParams();
////		params.setParameter(HttpMethodParams.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
////		params.setParameter(HttpMethodParams.USE_EXPECT_CONTINUE, false);
////		params.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, DEFAULT_ENCODING);
////		
////	
////		for(String key:parameters.keySet()) {
////			params.setParameter(key, parameters.get(key));
////		}
////		
////		this.hostConfig.setParams(params);
////		
////		HttpConnection conn = new HttpConnection(hostConfig);
////		
////		OutputStream os;
////		ObjectInputStream objIn =null;
////		ObjectOutputStream objOut=null;
////        Object result = null;
////
////		
////		// request 
////		try {
////			conn.open();
////			
////			os = conn.getRequestOutputStream();
////			InputStream is = conn.getResponseInputStream();
////
////			
////			objOut = new ObjectOutputStream(os);
////	        
////	         objOut.flush();
////	         objOut.writeObject(targetObj);
////	        objOut.flush();
////		} catch (IllegalStateException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
////		
////		try 
////		{
////	        int status = client.executeMethod(post);
////	        if( status == HttpStatus.SC_OK ) {
////	        	InputStream inStream = null;
////				ObjectInputStream objInStream = null;
////				
////	        	try {
////		        	inStream = post.getResponseBodyAsStream();
////					objInStream = new ObjectInputStream(inStream);
////					result = objInStream.readObject();
////	        	}
////	        	finally {
////	        		if( objInStream!=null ) try { objInStream.close(); } catch(IOException e) {}
////	        		if( inStream!=null ) try { inStream.close(); } catch(IOException e) {}
////	        	}
////	        }
////	        else {
////	        	System.out.println("url:" + url + " (Error:"+status+") "+HttpStatus.getStatusText(status));
////	        	return result;
////	        }
////		}
////		catch (Exception e) {
////			e.printStackTrace();
////		}
////		finally {
////			this.parameters.clear();
////			post.releaseConnection();
////			conn.releaseConnection();
////			conn.close();
////
////		}
////		DataContainer requestDC = new DataContainer();
////		DataWindow dw = new DataWindow(10, 10, "myDW");
////		dw.addRow(0, new String[]{"1","2"});
////		dw.addRow(1, new String[]{"11","22"});
////		requestDC.addDataWindow(dw);
////		requestDC.setParameter("param1", "val1");
////		
////		ObjectOutputStream objOut=null;
////		// send object
////        try {
////			objOut = new ObjectOutputStream(client.getHttpConnectionManager().getConnection(hostConfig).getRequestOutputStream());
////			  objOut.writeObject(requestDC);
////		       objOut.flush();
////		      
////		} catch (IllegalStateException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		} finally {
////			if(objOut != null)
////				try {
////					objOut.close();
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////		}
////        try 
////		{
////	        int status = client.executeMethod(post);
////	        if( status == HttpStatus.SC_OK ) {
////	        	InputStream inStream = null;
////				ObjectInputStream objInStream = null;
////				
////	        	try {
////		        	inStream = post.getResponseBodyAsStream();
////					objInStream = new ObjectInputStream(inStream);
////					result = objInStream.readObject();
////	        	}
////	        	finally {
////	        		if( objInStream!=null ) try { objInStream.close(); } catch(IOException e) {}
////	        		if( inStream!=null ) try { inStream.close(); } catch(IOException e) {}
////	        	}
////	        }
////	        else {
////	        	System.out.println("url:" + url + " (Error:"+status+") "+HttpStatus.getStatusText(status));
////	        	return result;
////	        }
////		}
////		catch (Exception e) {
////			e.printStackTrace();
////		}
////		finally {
////			this.parameters.clear();
////			post.releaseConnection();
////
////		}
//        
//        return result;
//	}
//	
//
//	
//	
//	 /**
//     * �씪諛� �뙆�씪硫뷀꽣 異붽�
//     * @param name  �뙆�씪硫뷀꽣 �씠由�
//     * @param value 媛�
//     * @return
//     */
//    public TestMainUsingCommHttpClient addParam(String key, String value){
//    	
////    	NameValuePair nvp = new NameValuePair(name, value);
////		this.parameters.add(nvp);
//    	parameters.put(key, value);
//        return this;
//    }
//    
//    
//	 /**
//     * Object �뙆�씪硫뷀꽣 異붽�
//     * @param name  �뙆�씪硫뷀꽣 �씠由�
//     * @param value 媛�
//     * @return
//     */
//    public TestMainUsingCommHttpClient addParam(String name, DataContainer value){
//    	
////    	ObjectOutputStream outObjStream = null;
////		try
////		{
////			ByteArrayOutputStream outByteStream = new BufferNotCopiedByteArrayOutputStream();
////			outObjStream = new ObjectOutputStream(outByteStream);
////			outObjStream.writeObject(value);
////			
////			String objString = new String(new String(Base64.encodeBase64(outByteStream.toByteArray())));
////			NameValuePair nvp = new NameValuePair(name, objString);
////			this.parameters.add(nvp);
////		}
////		catch(Exception e) {
////			System.out.println("Object 留ㅺ컻蹂��닔瑜� 異붽��븷 �닔 �뾾�뒿�땲�떎.");
////			e.printStackTrace();
////		}
////		finally {
////			if( outObjStream!=null ) try { outObjStream.close(); } catch(IOException e) {}
////		}
//    	
//    	
////		this.datacontainers.put(name, value);
//		
//	
//		    
//		targetObj = value;
//
//		return this;
//    }
//     
//    /**
//     * �뾽濡쒕뱶�븷 �뙆�씪�쓣 �뙆�씪硫뷀꽣濡� 異붽�
//     * @param name  <input type="file" name="�슂湲곕뱾�뼱媛��뒗 媛�"/>
//     * @param file
//     * @return
//     */
////    public TestMainUsingCommHttpClient addParam(String name, File file){
////        param.put(name, file);
////         
////        return this;
////    }
//
//}

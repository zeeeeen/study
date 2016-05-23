//package com.yang;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//
//import org.apache.commons.httpclient.methods.RequestEntity;
//
//import com.tys.frameplus.core.DataContainer;
//
//public class ObjectRequestEntity implements RequestEntity {
//
//	
//	private DataContainer dc = null;
//	private long size = -1;
//    
//    public ObjectRequestEntity(DataContainer dc) {
//        super();
//        this.dc = dc;
//    }
//
//    public boolean isRepeatable() {
//        return true;
//    }
//
//    public String getContentType() {
//        return "application/octet-stream; charset=UTF-8";
//    }
//    
//    public void writeRequest(OutputStream out) throws IOException {
//    	
//    	ObjectOutputStream oos = new ObjectOutputStream(out);
//        try {
//        	oos.writeObject(this.dc);
//        	oos.flush();
//        } finally {
//           
//        }
//    }
//
//    public long getContentLength() {
//    	
//    	if(this.dc != null) {
//    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    		ObjectOutputStream oos = null;
//			try {
//				oos = new ObjectOutputStream(baos);
//				oos.writeObject(this.dc);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				if(oos != null) {
//					try {
//						oos.close();
//					} catch (IOException e) {
//						;;
//					}
//				}
//			}
//    	
//    		return baos.size();
//    	}
//    	
//        return -1;
//    }
//	
//}
//

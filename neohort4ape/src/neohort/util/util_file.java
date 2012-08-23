package neohort.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class util_file {
	public static boolean writeByteToFile( byte[] bytes, String path ) throws Exception {
		boolean result = false;
/*
		FileOutputStream fileOutputStream = new FileOutputStream(path, false);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			result=true;
*/			
		return result;
	}
	 public static byte[] getBytesFromFile(String path) throws Exception {
		 File file = new File(path);
		 return getBytesFromFile(file);
	 }	
	 public static byte[] getBytesFromFile(File file) throws Exception {

		 if(!file.exists()){
			 throw new IOException("File "+file.getAbsolutePath()+" non exist");
		 }
	     InputStream is = new FileInputStream(file);
	     long length = file.length();
	     if (length > Integer.MAX_VALUE) {
	     }
	     byte[] bytes = new byte[(int)length];
	     int offset = 0;
	     int numRead = 0;
	     while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	           offset += numRead;
	     }
	     if (offset < bytes.length) {
	         throw new IOException("Could not completely read file "+file.getName());
	     }
	     is.close();
	     return bytes;
	 }		 

	public static Properties loadExternalProperty(String property_name) throws Exception{
			Properties property = new Properties();
			try{
				InputStream instrm = null;
				instrm = ClassLoader.getSystemResourceAsStream(property_name);
				if(instrm!=null) property.load(instrm);
				else property.load(new FileInputStream(property_name));		
			}catch(Exception e){
				property=null;
				throw e;
			}
			return property;
	}
	
	
	public static Properties loadProperty(String property_name) throws Exception{
		Properties property = new Properties();

		try{
			ResourceBundle rb = ResourceBundle.getBundle(property_name);

			Enumeration en = rb.getKeys();
			while(en.hasMoreElements()){
				if(property==null) property = new Properties();
				String key = (String)en.nextElement();
				if(rb.getString(key)==null || rb.getString(key).equals("")){}
				else property.setProperty(key,rb.getString(key));
			}
		}catch(Exception e){	

			try{
				if(property_name.indexOf(".properties")==-1) property_name+=".properties";		
				property = util_file.loadExternalProperty(property_name);					
			}catch (Exception ex) {
				throw e;
			}
			
		}
		return property;
	}
}

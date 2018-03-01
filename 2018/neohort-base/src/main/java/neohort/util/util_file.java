package neohort.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class util_file {
	public static boolean writeByteToFile( byte[] bytes, String path ) throws Exception {
		boolean result = false;
			FileOutputStream fileOutputStream = new FileOutputStream(path, false);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			result=true;
		return result;
	}
	 public static byte[] getBytesFromFile(String path) throws Exception {
		 File file = new File(path);
		 if(!file.exists()){
			 throw new IOException("File "+path+" non exist");
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
	    	 is.close();
	         throw new IOException("Could not completely read file "+file.getName());
	     }
	     is.close();
	     return bytes;
	 }	


}

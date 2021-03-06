package neohort.universal.output.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

public class ByteStreamWrapper implements I_StreamWrapper {
	private ByteArrayOutputStream bos = null;

	
	@Override
	public OutputStream createOutputStream(Hashtable _tagLibrary, Hashtable _beanLibrary) {
		bos = new ByteArrayOutputStream();
		return bos;
	}
	
	@Override
	public OutputStream createOutputStream(byte[] input, Hashtable _tagLibrary, Hashtable _beanLibrary) {
		bos = new ByteArrayOutputStream();
		if(input!=null){
			try{
				bos.write(input);
			}catch(Exception e){
				System.out.println(e);
			}
		}
		return bos;
	}	

	@Override
	public OutputStream getOutputStream(Hashtable _tagLibrary, Hashtable _beanLibrary) {
		return bos;
	}

	@Override
	public byte[] getByteFromStream(Hashtable _tagLibrary, Hashtable _beanLibrary) {
		if(bos!=null)
			return bos.toByteArray();
		return null;
	}

	@Override
	public void close() {
		try{
			if(bos!=null)
				bos.close();
		}catch(Exception e){
			
		}
		
	}

}

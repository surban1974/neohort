package neohort.universal.output.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import neohort.universal.output.lib.report_element_base;

public class ByteStreamWrapper implements I_StreamWrapper {
	private ByteArrayOutputStream bos = null;

	
	@Override
	public OutputStream createOutputStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
		bos = new ByteArrayOutputStream();
		return bos;
	}
	
	@Override
	public OutputStream createOutputStream(byte[] input, Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
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
	public OutputStream getOutputStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
		return bos;
	}

	@Override
	public byte[] getByteFromStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
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

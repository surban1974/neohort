package neohort.universal.output.util;

import java.io.OutputStream;
import java.util.Hashtable;

import neohort.universal.output.lib.report_element_base;

public interface I_StreamWrapper {
	OutputStream createOutputStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);
	OutputStream createOutputStream(byte[] input, Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);
	OutputStream getOutputStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);
	byte[] getByteFromStream(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);
	void close();
}

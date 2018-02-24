package neohort.universal.output.util;

import java.io.OutputStream;
import java.util.Hashtable;

public interface I_StreamWrapper {
	OutputStream createOutputStream(Hashtable _tagLibrary, Hashtable _beanLibrary);
	OutputStream createOutputStream(byte[] input, Hashtable _tagLibrary, Hashtable _beanLibrary);
	OutputStream getOutputStream(Hashtable _tagLibrary, Hashtable _beanLibrary);
	byte[] getByteFromStream(Hashtable _tagLibrary, Hashtable _beanLibrary);
	void close();
}

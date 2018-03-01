package neohort.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class util_cloner {
private util_cloner() {
}
static public Object clone(Object oldObj) throws Exception {
	Object retVal = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	try {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(bos);
		oos.writeObject(oldObj); 
		oos.flush(); 

		ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray()); 
		ois = new ObjectInputStream(bin); 
		retVal = ois.readObject(); 

	} catch (Exception e) {
		throw (e);
	} finally {
		try {
			oos.close();
			ois.close();
		} catch (java.io.IOException e) {
			throw (e);
		}
	}
	return retVal;
}

static public <T extends Object> T clone(T oldObj, Class<T> type) throws Exception {
	
	Object retVal = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	try {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(bos);
		oos.writeObject(oldObj); 
		oos.flush(); 

		ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray()); 
		ois = new ObjectInputStream(bin); 
		retVal = ois.readObject(); 

	} catch (Exception e) {
		throw (e);
	} finally {
		try {
			oos.close();
			ois.close();
		} catch (java.io.IOException e) {
			throw (e);
		}
	}
	return type.cast(retVal);
}

static public int sizeByte(Object oldObj) throws Exception {
	ObjectOutputStream oos = null;
	try {
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(bos);
		oos.writeObject(oldObj); 
		oos.flush();
		return bos.toByteArray().length; 
	} catch (Exception e) {
		throw (e);
	} finally {
		try {
			oos.close();	
		} catch (java.io.IOException e) {
			throw (e);
		}
	}	
}

}

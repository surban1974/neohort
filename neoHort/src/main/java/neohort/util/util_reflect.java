package neohort.util;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;




public class util_reflect { 
	private static StringBuffer logString = new StringBuffer();
	private Object object;
	private int leng;
	private int indexOfList;
	private static boolean error = false;
public util_reflect() {
	super();
}




public static synchronized String prepareClassInfo(String name){
	try{
		name = name.substring(name.lastIndexOf("."),name.length());
	}catch(Exception e){		
	}
	
	return prepareClassInfo(new String[]{name+".java"},new String[]{});
}

public static synchronized String prepareClassInfo(String[] javaNames, String[] methodNames){
	String classInfo="";
	StackTraceElement ste = null;
	int counter=0;
	try{
		if(javaNames.length==0 || methodNames.length==0){
			Object[] result = forPrepareClassInfo("","",counter);
			if(result!=null){
				counter = ((Integer)result[0]).intValue();
				ste = (StackTraceElement)result[1];
				classInfo = ste.getClassName()+":"+ste.getMethodName();	
			}	
		}else{

			for(int i=0;i<javaNames.length;i++){
				Object[] result = forPrepareClassInfo(javaNames[i],methodNames[i],counter);
				if(result!=null){
					counter = ((Integer)result[0]).intValue();
					ste = (StackTraceElement)result[1];
					classInfo = ste.getClassName()+":"+ste.getMethodName();	
				}	
			}
		}
	}catch(Exception e){	

	}
	return classInfo;
}

private static synchronized Object[] forPrepareClassInfo(String javaName, String methodName,int counter){
	Object[] result = new Object[2];
	try{
		StackTraceElement ste = null;
		int i=counter;

		
		/*		
		 * JDK 1.5
				while (i<Thread.currentThread().getStackTrace().length && ste==null){
					if(	Thread.currentThread().getStackTrace()[i].getFileName() != null && 
						Thread.currentThread().getStackTrace()[i].getFileName().equals(javaName) &&
						Thread.currentThread().getStackTrace()[i].getMethodName().equals(methodName)){
						result[0]= new Integer(i+1);
						result[1] = Thread.currentThread().getStackTrace()[i+1];	
						return result;
					}	
					i++;
				}
		*/
		//JDK 1.4		
				while (i<new Exception().getStackTrace().length && ste==null){
					if(	new Exception().getStackTrace()[i].getFileName() != null && 
						new Exception().getStackTrace()[i].getFileName().equals(javaName) &&
						new Exception().getStackTrace()[i].getMethodName().equals(methodName)){
						result[0]= new Integer(i+1);
						result[1] = new Exception().getStackTrace()[i+1];	
						return result;
					}	
					i++;
				}			
	}catch(Exception e){			
	}
	
	return null;
}


public static Object convertType(Class CTarget, Object source, String format) throws Exception {
	String classNameSource = source.getClass().getName();
	String classNameTarget = CTarget.getName();
	String err = "SourceType: "+classNameSource+", TargetTipe: "+classNameTarget+", formato: "+format;
	try {
		if ( classNameSource.equals(classNameTarget) ) {
			if (format==null || format.equals("") ) return source;
			// Questa condizione per il momento è prevista solo per le date!
			return util_format.dataToString( util_format.stringToData((String)source,0), format );
		}
		// Inizio gestione da e verso tipi Date
		if ( classNameSource.equals("java.util.Date") ) { // Source di tipo Date
			if ( classNameTarget.equals("short") || classNameTarget.equals("java.lang.Short") )
				return (Object)new Short( util_format.dataToString((Date)source,format).trim() );
			if ( classNameTarget.equals("int") || classNameTarget.equals("java.lang.Integer") )
				return (Object)new Integer( util_format.dataToString((Date)source,format).trim() );
			if ( classNameTarget.equals("java.lang.String") )
				return (Object)util_format.dataToString((Date)source,format);
		}
		if ( classNameTarget.equals("java.util.Date")) { // Target di tipo Date
			if ( classNameSource.equals("short")
			|| classNameSource.equals("java.lang.Short")
			|| classNameSource.equals("int")
			|| classNameSource.equals("java.lang.Integer")
			|| classNameSource.equals("long")
			|| classNameSource.equals("java.lang.Long")
			|| classNameSource.equals("java.lang.String"))	return (Object)util_format.stringToData(""+source,format);
		}
		// Fine gestione da e verso tipi Date
		// Inizio conversioni da tipi java ai corrispondenti tipi primitivi
		if ( classNameSource.equals("java.lang.Integer") &&
			( classNameTarget.equals("int") ||
			classNameTarget.equals("long") ) ) return source;
		if ( classNameSource.equals("java.lang.Short") &&
			( classNameTarget.equals("short") ||
			classNameTarget.equals("int") ||
			classNameTarget.equals("long") ) ) return source;
		if ( classNameSource.equals("java.lang.Long") &&
			classNameTarget.equals("long") ) return source;
		if ( classNameSource.equals("java.lang.Float") &&
			( classNameTarget.equals("float") ||
			classNameTarget.equals("double") ) ) return source;
		if ( classNameSource.equals("java.lang.Double") && classNameTarget.equals("double") ) return source;
		if ( classNameSource.equals("java.lang.Boolean") && classNameTarget.equals("boolean") ) return source;
		if ( classNameSource.equals("java.lang.Character") && classNameTarget.equals("char") ) return source;
		// Fine conversioni da tipi java ai corrispondenti tipi primitivi
		// Inizio conversioni pericolose!!!
		if ( classNameSource.equals("java.lang.Integer") &&	classNameTarget.equals("short") ) return (Object)new Short(source.toString());
		if ( classNameSource.equals("java.lang.Long") ) {
			if ( classNameTarget.equals("int") ) return (Object)new Integer(source.toString());
			if ( classNameTarget.equals("short") ) return (Object)new Short(source.toString());
		}
		if ( classNameSource.equals("java.lang.Float") && classNameTarget.equals("duble") ) return (Object)new Double(source.toString());
		// Fine conversioni pericolose!!!
		// Inizio conversioni dal tipo java.lang.String ai corrispondenti tipi primitivi
		if ( classNameSource.equals("java.lang.String") ) {
			if ( classNameTarget.equals("int") ) return (Object)new Integer(source.toString());
			if ( classNameTarget.equals("short") ) return (Object)new Short(source.toString());
			if ( classNameTarget.equals("long") ) return (Object)new Long(source.toString());
			if ( classNameTarget.equals("float") ) return (Object)new Float(source.toString());
			if ( classNameTarget.equals("double") ) return (Object)new Double(source.toString());
			if ( classNameTarget.equals("char") ) return (Object)new Character(source.toString().charAt(0));
			if ( classNameTarget.equals("boolean") ) return (Object)new Boolean(source.toString());
		}
		// Fine conversioni dal tipo java.lang.String ai corrispondenti tipi primitivi
	} catch( NumberFormatException  e) {
		errorMsg("ConvertType",err,e);
	} catch(ClassCastException e) {
		errorMsg("ConvertType",err,e);
	} catch(Exception e) {
		errorMsg("ConvertType",err,e);
	}
	errorMsg("ConvertType","La conversione tra:\n"+err+"\nnon è contemplata.\nPossibile perdita di dati.",null);
	return source;
}
public static void errorMsg(String location, String msg, Exception e) throws Exception {
	String errmsg = "Messagge: " + msg ;
	setLogString(errmsg);
	error = true;
	throw new Exception(errmsg);
}
public Field findField(String name, Object obj) throws Exception {
	Field fld = null;
	Field[] allFld= null;
	setObject(obj);
	try {
		allFld = obj.getClass().getFields();
	} catch (SecurityException e) {
		errorMsg("findField","Il campo: "+name+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+"\nnon è accessibile.",e);
	} catch(NullPointerException e) {
		errorMsg("findField","Il campo: "+name+" è invocato su un oggetto nullo.",e);
	}
	for (int i=0;i<allFld.length;i++) {	
		Class tipo = allFld[i].getType(); 
		if ( allFld[i].getName().equals(name) ) return allFld[i];
		Object campo = getField(allFld[i],obj);
		if (campo == null) continue;
		if ( !tipo.isPrimitive() ) fld = findFieldArray(name,tipo);		
		if ( fld != null) return fld;
	}
	return fld;
}
public Field findFieldArray(String name, Object obj) throws Exception {
	Field fld = null;
	Field[] allFld= null;
	setObject(obj);
	try {
		allFld = obj.getClass().getFields();
	} catch (SecurityException e) {
		errorMsg("findFieldArray","Il campo: "+name+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+"\nnon è accessibile.",e);
	} catch(NullPointerException e) {
		errorMsg("findFieldArray","Il campo: "+name+" è invocato su un oggetto nullo",e);
	}
	for (int i=0;i<allFld.length;i++) {
		Class tipo = allFld[i].getType();
		if ( tipo.isPrimitive() ) continue;
		if ( tipo.getName().equals("java.lang.String") ) continue;
		if ( allFld[i].getName().equals(name) && allFld[i].getType().isArray() ) return allFld[i];
		Object campo = getField(allFld[i],obj);
		if (campo == null) continue;
		fld = findFieldArray(name,campo);
		if ( fld != null) return fld;
	}
	return fld;
}
public Field findFieldList(String name, Object obj) throws Exception {
	//class CopyField { public Object field; };
	//CopyField newField = new CopyField();
	Field fld = null;
	Field[] allFld= null;
	setObject(obj);
	try {
		allFld = obj.getClass().getFields();
	} catch (SecurityException e) {
		errorMsg("findFieldArray","Il campo: "+name+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+"\nnon è accessibile.",e);
	} catch(NullPointerException e) {
		errorMsg("findFieldArray","Il campo: "+name+" è invocato su un oggetto nullo.",e);
	}
	for (int i=0;i<allFld.length;i++) {	// Inizia la ricerca in tutti i campi trovati
		if ( allFld[i].getName().equals(name) ) { // verifica se il nome è quello cercato
			if ( allFld[i].getType().isArray() ) { // e un array?
				setLeng( Array.getLength( getFieldOnly(allFld[i],obj) ) );
				return allFld[i];
			}
			return allFld[i];
		}
		Class tipo = allFld[i].getType(); // ne deternina il tipo
		if ( tipo.isPrimitive() || tipo.getName().equals("java.lang.String") ) continue; // scarta i tipi string e i tipi primitivi
		Object campo = getField(allFld[i],obj); // altrimenti ne calcola il valore
		if (campo == null) continue; // verifica che sia diverso da null
		if ( campo.getClass().isArray() ) { // e un array?
			setLeng(Array.getLength(campo));// ne determina la lunghezza
			campo = Array.get(campo,indexOfList); // altrimenti ne calcola il valore
			if (campo != null) fld = findFieldList(name,campo); // verifica che sia diverso da null
		} else fld = findFieldList(name,campo);
		// verifica se il campo calcolato appartiene a una classe che può contenere, a sua volta, il vettore cercato
		if ( fld != null) return fld;
	}
	return fld; // nel caso in cui non venga trovato il campo viene restituito null
}
public static int findMethod(String name, Method mtd[]) throws Exception {
	if ((mtd == null) || (name == null)) {
		errorMsg("findMethod", "Questo metodo è stato invocato con parametri errati:\nnome: " + name + "\nmetodo: " + mtd, null);
		return -1;
	}
	for (int i = 0; i < mtd.length; i++)
		if (mtd[i].getName().equals(name))
			return i;
	String elenco = "";
	for (int i = 0; i < mtd.length; i++) {
		elenco += mtd[i].getName() + "\n";
	}
	errorMsg("findMethod", "Non è stato trovato alcun metodo con nome: " + name + " tra:\n" + elenco, null);
	return -1;
}
public static java.lang.reflect.Constructor[] getConstructors(Object obj, String s) throws Exception {
	Constructor mtd[] = null;
	Constructor retVal[] = null;
	try {
		mtd = obj.getClass().getConstructors(); //prelevo tutti i metodi della classe corrente obj
	} catch (SecurityException e) {
		errorMsg("getMethod", "L'accesso alle informazioni della classe " + obj.getClass().getName() + " è negato.", e);
	}
	int count = 0;
	for (int i = 0; i < mtd.length; i++) {
		if (mtd[i].getName().startsWith(s)) {
			count++;
		}
	}
	retVal = new Constructor[count];
	count = 0;
	for (int i = 0; i < mtd.length; i++) {
		if (mtd[i].getName().startsWith(s)) {
			retVal[count] = mtd[i];
			count++;
		}
	}
	return retVal;
}
public Object getField(Field fld, Object obj) throws Exception {
	Object result = null;
	result = getFieldOnly(fld, obj);
	if ( fld.getType().isArray() ) { // se è un array
		try {
			result = Array.get( result, indexOfList); // ritorna il valore dell'array di indice i-esimo
		} catch(ArrayIndexOutOfBoundsException e) {
			errorMsg("setField","Il campo: "+fld+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+"\nnon è accessibile.",e);
		} catch(IllegalArgumentException e) {
			errorMsg("setField","Il campo: "+fld+"\nnon è un'istanza dell'oggetto tipo: "+obj.getClass().getName()+".",e);
		} catch(NullPointerException e) {
			errorMsg("setField","Il campo: "+fld+" è invocato su un oggetto nullo.",e);
		}
	}

	return result;
}
public static Object getFieldOnly(Field fld, Object obj) throws Exception {
	Object result = null;
	try {
		result = fld.get(obj);
	} catch(IllegalAccessException e) {
		errorMsg("getField","Il campo: "+fld+"\ninvocato per l'oggetto: "+obj.getClass().getName()+"\nnon è accessibile.",e);
	} catch(IllegalArgumentException e) {
		errorMsg("getField","Il campo: "+fld+"\nnon è un'istanza dell'oggetto: "+obj.getClass().getName()+".",e);
	} catch(NullPointerException e) {
		errorMsg("getField","Il campo: "+fld+"\nè invocato su un oggetto nullo.",e);
	}
	return result;
}
public int getIndexOfList() {
	return indexOfList;
}
public int getLeng() {
	return leng;
}
public StringBuffer getLogString() {
	return logString;
}
public static Method getMethod(String name, Object obj) throws Exception {
	Method mtd[] = getMethods(obj);
	int i = findMethod(name,mtd);
	if (i<0) return null;
	return mtd[i];
}
public static java.lang.reflect.Method[] getMethods(Object obj) throws Exception {
	Method mtd[] = null;
	try {
		mtd = obj.getClass().getMethods();	//prelevo tutti i metodi della classe corrente obj
	} catch(SecurityException e) {
		errorMsg("getMethod","L'accesso alle informazioni della classe "+obj.getClass().getName()+" è negato.",e);
	}
	return mtd;
}
public static java.lang.reflect.Method[] getMethods(Object obj, String s) throws Exception {
	Method mtd[] = null;
	Method retVal[] = null;
	try {
		mtd = obj.getClass().getMethods(); //prelevo tutti i metodi della classe corrente obj
	} catch (SecurityException e) {
		errorMsg("getMethod", "L'accesso alle informazioni della classe " + obj.getClass().getName() + " è negato.", e);
	}
	int count = 0;
	for (int i = 0; i < mtd.length; i++) {
		if (mtd[i].getName().startsWith(s)) {
			count++;
		}
	}
	retVal = new Method[count];
	count = 0;
	for (int i = 0; i < mtd.length; i++) {
		if (mtd[i].getName().startsWith(s)) {
			retVal[count] = mtd[i];
			count++;
		}
	}
	return retVal;
}
protected Object getObject() {
	return object;
}
public Class getType(Field fld) throws Exception {
	if ( fld.getType().isArray() ) { // se è un array
		Object campo = getFieldOnly(fld,getObject()); // calcola il valore
		return Array.get( campo, indexOfList).getClass(); // gli copia il valore dell'array di indice i-esimo
	}
	return fld.getType();
}
public static Object invocaGet(Method mtd, Object obj) throws Exception {
	Object value = null;
	Object[] prm = null;
	try {
		value = mtd.invoke(obj,prm); // invoco il metodo che mi restituisce il valore
	} catch(IllegalAccessException e) {
		errorMsg("invocaGet","Il methodo: "+mtd+" non è accessibile.",e);
	} catch(IllegalArgumentException e) {
		errorMsg("invocaGet","Il methodo: "+mtd+"\nè invocato con un numero o con tipi di argomenti errati.",e);
	} catch(java.lang.reflect.InvocationTargetException e) {
		errorMsg("invocaGet","Il methodo: "+mtd+" ha generato un errore.",e);
	} catch(NullPointerException e) {
		errorMsg("invocaGet","Il methodo: "+mtd+" è invocato su un oggetto nullo.",e);
	}
	return value;
}
public static Object invocaMetodo(Method mtd, Object obj, Object dato) throws Exception {
	Object value = null;
	Object prm[] = new Object[1];
	prm[0] = dato;
	try {
		value = mtd.invoke(obj, prm); // invoco il metodo che mi setta il valore
	} catch (IllegalAccessException e) {
		errorMsg("invocaMetodo","Il methodo: "+mtd+"\ninvocato con parametro tipo: "+dato.getClass().getName()+"\nnon è accessibile.",e);
	} catch (IllegalArgumentException e) {
		errorMsg("invocaMetodo","Il methodo: "+mtd+"\ninvocato con parametro tipo: "+dato.getClass().getName()+"\nha numero o tipi di argomenti errati.",e);
	} catch (InvocationTargetException e) {
		errorMsg("invocaMetodo","Il methodo: "+mtd+"\ninvocato con parametro tipo: "+dato.getClass().getName()+"\nha generato un errore.",e);
	} catch (NullPointerException e) {
		errorMsg("invocaMetodo","Il methodo: "+mtd+"\nè invocato su un oggetto nullo.",e);
	}
	return value;
}
public static void invocaSet(Method mtd, Object obj, Object dato) throws Exception {
	Object prm[] = new Object[1];
	prm[0] = dato;
	try {
		mtd.invoke(obj, prm); // invoco il metodo che mi setta il valore
	} catch (IllegalAccessException e) {
		errorMsg("invocaSet", "Il methodo: " + mtd + "\ninvocato con parametro tipo: " + dato.getClass().getName() + "\nnon è accessibile.", e);
	} catch (IllegalArgumentException e) {
		errorMsg("invocaSet", "Il methodo: " + mtd + "\ninvocato con parametro tipo: " + dato.getClass().getName() + "\nha numero o tipi di argomenti errati.", e);
	} catch (InvocationTargetException e) {
		errorMsg("invocaSet", "Il methodo: " + mtd + "\ninvocato con parametro tipo: " + dato.getClass().getName() + "\nha generato un errore.", e);
	} catch (NullPointerException e) {
		errorMsg("invocaSet", "Il methodo: " + mtd + " è invocato su un oggetto nullo.", e);
	}
}
public boolean isError() {
	return error;
}
public void setField(Field fld, Object obj, Object value) throws Exception {
	if ( fld.getType().isArray() ) { // se è un array
		Object campo = getFieldOnly(fld,obj); // calcola il valore
		try {
			Array.set( campo, indexOfList, value); // gli copia il valore dell'array di indice i-esimo
		} catch(ArrayIndexOutOfBoundsException e) {
			errorMsg("setField","Il campo: "+fld+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+"\nnon è accessibile.",e);
		} catch(IllegalArgumentException e) {
			errorMsg("setField","Il campo: "+fld+"\nnon è un'istanza dell'oggetto "+obj.getClass().getName()+".",e);
		} catch(NullPointerException e) {
			errorMsg("setField","Il campo: "+fld+" è invocato su un oggetto nullo.",e);
		}
	} else {
		try {
//			obj.getClass().isArray();
			fld.set(obj,value);
		} catch(IllegalAccessException e) {
			errorMsg("setField","Il campo: "+fld+"\ninvocato per l'oggetto tipo: "+obj.getClass().getName()+" non è accessibile.",e);
		} catch(IllegalArgumentException e) {
			errorMsg("setField","Il campo: "+fld+"\nnon è un'istanza dell'oggetto "+obj.getClass().getName()+".",e);
		} catch(NullPointerException e) {
			errorMsg("setField","Il campo: "+fld+" è invocato su un oggetto nullo.",e);
		}
	}
}
public void setIndexOfList(int newValue) {
	this.indexOfList = newValue;
}
public void setLeng(int newValue) {
	this.leng = newValue;
}
public static void setLogString(String newValue) {
	logString.append(newValue);
}
public void setLogString(StringBuffer newValue) {
	logString = newValue;
}
private void setObject(Object newValue) {
	this.object = newValue;
}

public static Object getValue(Object requested, String nome, Object[] value) throws Exception{
	if (nome == null || nome.trim().length()==0 || requested==null) return null;
	if(value==null) value = new Object[0];
	Object resultObject = null;
	try{
		java.lang.reflect.Method mtd = null;
		Class[] cls = new Class[value.length];
		for(int i=0;i<value.length;i++) cls[i]=value[i].getClass();
		mtd = requested.getClass().getMethod(nome,cls);		
		if(mtd==null) return null;
		resultObject =mtd.invoke(requested, value);
	}catch(Exception e){
		
		java.lang.reflect.Method mtd = null;
		Class[] cls = new Class[value.length];
		for(int i=0;i<value.length;i++) cls[i]=value[i].getClass();
		int maxCount = 0;
		boolean fine = false;
		while(!fine && maxCount<20){
			int count = 0;
			for(int i=0;i<cls.length;i++){
				if(cls[i]!=null && !cls[i].isPrimitive() && !cls[i].getName().equals("java.lang.Object")){
					boolean corrected=false;
					if(!(corrected) && cls[i].getName().indexOf(".Long")>0){
						cls[i] = long.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Integer")>0){
						cls[i] = int.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Short")>0){
						cls[i] = short.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Float")>0){
						cls[i] = float.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Double")>0){
						cls[i] = double.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Byte")>0){
						cls[i] = byte.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Boolean")>0){
						cls[i] = boolean.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Character")>0){
						cls[i] = char.class; 
						corrected = true;
					}
//					if(!corrected)cls[i] = cls[i].getSuperclass();
					count++;
				}	
			}
			try{						
				mtd = requested.getClass().getMethod(nome.trim(),cls);
				resultObject =mtd.invoke(requested, value);
				 
			}catch(Exception ex){
				mtd = null;
			}
			if(count>0 && mtd==null) fine = false;
			else fine = true;
			maxCount++;
		}
			
	}	
	return  resultObject;
}

public static java.lang.reflect.Method getMethodNull(Object requested, String nome, Class[] cls) throws Exception{
	java.lang.reflect.Method result=null;
	java.lang.reflect.Method[] mtds = requested.getClass().getMethods();
	for(int i=0;i<mtds.length;i++){
		java.lang.reflect.Method current = mtds[i];
		if(nome.equals(current.getName())){
		Class[] par = current.getParameterTypes();
		if(par.length==cls.length){
			boolean isCorrect=true;
			for(int j=0;j<cls.length;j++){
				if(cls[j]!=null){
					if(cls[j].getName().equals(par[j].getName())) isCorrect&=true;
					else isCorrect&=false;
				}
			}
			if(isCorrect) return current;
		}
		}
	}
	return result;
}

public static boolean setValue(Object requested, String nome, Object[] value) throws Exception{
	if (nome == null || nome.trim().length()==0 || requested==null) return false;
	Class[] cls = null;
	if(value==null)	value = new Object[0];
	try{
		java.lang.reflect.Method mtd = null;
		cls = new Class[value.length];
//		boolean classIsNull = false;
		for(int i=0;i<value.length;i++){ 
			if(value[i]==null){
				cls[i]=null;
//				classIsNull=true;
			}
			else cls[i]=value[i].getClass();
		}
//		if(classIsNull)  mtd = getMethodNull(requested, nome, cls);
//		else 
			mtd = requested.getClass().getMethod(nome,cls);
		if(mtd==null) return false;
		mtd.invoke(requested, value);
	}catch(Exception e){
		java.lang.reflect.Method mtd = null;
		cls = new Class[value.length];
		for(int i=0;i<value.length;i++) cls[i]=value[i].getClass();
		int maxCount = 0;
		boolean fine = false;
		while(!fine && maxCount<20){
			int count = 0;
			for(int i=0;i<cls.length;i++){
				if(cls[i]!=null && !cls[i].isPrimitive() && !cls[i].getName().equals("java.lang.Object")){
					boolean corrected=false;
					if(!(corrected) && cls[i].getName().indexOf(".Long")>0){
						cls[i] = long.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Integer")>0){
						cls[i] = int.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Short")>0){
						cls[i] = short.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Float")>0){
						cls[i] = float.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Double")>0){
						cls[i] = double.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Byte")>0){
						cls[i] = byte.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Boolean")>0){
						cls[i] = boolean.class; 
						corrected = true;
					}
					if(!(corrected) && cls[i].getName().indexOf(".Character")>0){
						cls[i] = char.class; 
						corrected = true;
					}
//					if(!corrected)cls[i] = cls[i].getSuperclass();
					count++;
				}	
			}
			try{						
				mtd = requested.getClass().getMethod(nome.trim(),cls); 
			}catch(Exception ex){
				mtd = null;
			}
			if(count>0 && mtd==null) fine = false;
			else fine = true;
			maxCount++;
		}	
		if(mtd==null){
			for(int i=0;i<value.length;i++) cls[i]=Object.class;
			try{						
				mtd = requested.getClass().getMethod(nome.trim(),cls); 
			}catch(Exception ex){
				mtd = null; 
			}			
		}
		if(mtd==null){
			String types="";
			if(cls!=null){
				for(int k=0;k<cls.length;k++) types+=cls[k]+",";
			}	
//			if(nome!=null && !nome.toLowerCase().equals("setclass"))
//				bsController.writeLog("util_reflect->setValue: class: ["+requested.getClass().getName()+"] method: ["+nome+"("+types+")] not found. sorry.",iStub.log_DEBUG);
			return false; 
		}
		mtd.invoke(requested, value);
		
	}	
	return true;
}

public static boolean setValue_BasedOnlyCountParameters(Object requested, String nome, Object[] value) throws Exception{
	if (nome == null || nome.trim().length()==0 || requested==null) return false;
	if(value==null)	value = new Object[0];
	try{
		Method[] methods = requested.getClass().getMethods();
		for(int j=0;j<methods.length;j++){
			Method current = methods[j];
			if(current.getParameterTypes().length==value.length && nome.equals(current.getName())){
				current.invoke(requested, value);
				return true;
			}
		}
	}catch(Exception e){
		return false;
	}	
	return true;
}

public static Object getValue_BasedOnlyCountParameters(Object requested, String nome, Object[] value) throws Exception{
	if (nome == null || nome.trim().length()==0 || requested==null) return null;
	Object resultObject = null;
	if(value==null)	value = new Object[0];
	try{
		Method[] methods = requested.getClass().getMethods();
		for(int j=0;j<methods.length;j++){
			Method current = methods[j];
			if(current.getParameterTypes().length==value.length && nome.equals(current.getName())){
				resultObject = current.invoke(requested, value);
				return resultObject;
			}
		}
	}catch(Exception e){
		return resultObject;
	}	
	return resultObject;
}
public static String adaptMethodName(String value){
	if(value==null) return "";
	String result="";
	if(value.length()>1) result=value.substring(0,1).toUpperCase()+value.substring(1);
	else result = value.toUpperCase();
	return result;
}

public static Object prepareWriteValueForTag(Object requested, String method_prefix,String field_name, Object[] parameters){ 
	if(requested==null || field_name==null) return null;
	if(method_prefix==null) method_prefix="";	
		
	Object writeValue=null;	
	
	if(requested instanceof String ) return requested;
	
	StringTokenizer st = new StringTokenizer(field_name,".");
	Object current_requested = requested;
	while(st.hasMoreTokens()){
		String current_field_name = st.nextToken();		
		try{
			boolean isException=false;
			if(current_requested!=null && current_requested instanceof HashMap){
				try{
					writeValue = ((HashMap)current_requested).get(current_field_name);
				}catch(Exception ex){
					isException=true;
				}
			}else writeValue = util_reflect.getValue(current_requested,method_prefix+util_reflect.adaptMethodName(current_field_name),parameters);					 
			if(isException) writeValue = util_reflect.getValue(current_requested,method_prefix+util_reflect.adaptMethodName(current_field_name),parameters);					 
			if(writeValue==null) writeValue = util_reflect.getValue(current_requested,current_field_name,parameters);
		}catch(Exception e){
		}
		current_requested = writeValue;
	}
	

	
	return writeValue;
}

}

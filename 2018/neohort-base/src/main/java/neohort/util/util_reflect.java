package neohort.util;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.StringTokenizer;




public class util_reflect { 


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


public static Object getValue(Object requested, String nome, Object[] value) throws Exception{
	if (nome == null || nome.trim().length()==0 || requested==null) return null;
	if(value==null) value = new Object[0];
	Object resultObject = null;
	try{
		java.lang.reflect.Method mtd = null;
		Class<?>[] cls = new Class[value.length];
		for(int i=0;i<value.length;i++) cls[i]=value[i].getClass();
		mtd = requested.getClass().getMethod(nome,cls);		
		if(mtd==null) return null;
		resultObject =mtd.invoke(requested, value);
	}catch(Exception e){
		
		java.lang.reflect.Method mtd = null;
		Class<?>[] cls = new Class[value.length];
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

public static java.lang.reflect.Method getMethodNull(Object requested, String nome, Class<?>[] cls) throws Exception{
	java.lang.reflect.Method result=null;
	java.lang.reflect.Method[] mtds = requested.getClass().getMethods();
	for(int i=0;i<mtds.length;i++){
		java.lang.reflect.Method current = mtds[i];
		if(nome.equals(current.getName())){
		Class<?>[] par = current.getParameterTypes();
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
	Class<?>[] cls = null;
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
//			String types="";
//			if(cls!=null){
//				for(int k=0;k<cls.length;k++) types+=cls[k]+",";
//			}	
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

@SuppressWarnings("unchecked")
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
					writeValue = ((HashMap<Object,Object>)current_requested).get(current_field_name);
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

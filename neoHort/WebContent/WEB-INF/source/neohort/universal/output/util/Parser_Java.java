/**
* Creation date: (14/12/2005)
* @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
*/

/********************************************************************************
*
*	Copyright (C) 2005  Svyatoslav Urbanovych
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.

* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*********************************************************************************/

package neohort.universal.output.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.lib.report_element;
import neohort.util.util_format;

public class Parser_Java {
	private boolean viewError = true;
	private String type_s_tag = "";
	private String type_tag = "";
	private I_OutputRunTime motore = null;
Parser_Java() {
	super();
}
public Parser_Java(String type_tag, I_OutputRunTime motore) {
	this.viewError=true;
	this.type_tag = type_tag.toUpperCase();
	if(type_tag!=null && type_tag.indexOf(":")>-1)
		this.type_s_tag = type_tag.substring(0,type_tag.indexOf(":")+1);
	else this.type_s_tag = "";	
	this.motore = motore;
}
public Parser_Java(String type_tag, I_OutputRunTime motore, boolean vError) {
	this.viewError=vError;
	this.type_tag = type_tag.toUpperCase();
	if(type_tag!=null && type_tag.indexOf(":")>-1)
		this.type_s_tag = type_tag.substring(0,type_tag.indexOf(":")+1);
	else this.type_s_tag = "";	
	this.motore = motore;
}

private String adaptResult(Object value, String className) {
	try{	
		String type_prim = "INT;SHORT;DOUBLE;LONG;FLOAT;CHAR;BYTE;BOOLEAN;";
		if(type_prim.indexOf(className.toUpperCase()+";")>-1)
			return String.valueOf(value);
		else return value.toString();
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		return "";
	}	
}
public String checkAttribute(String value, Hashtable _tagLibrary, Hashtable _beanLibrary) { 
	String result=value;  
	if(value.toUpperCase().indexOf("%EXECUTE")==-1) return result;
	try{
		if(value.toUpperCase().indexOf("%EXECUTEBEAN.")>-1){
			int start = value.toUpperCase().indexOf("%EXECUTEBEAN."); 
			int finish = value.indexOf("%",start+1);
			return checkAttribute(value.substring(0,start)+
				direttivaBean(preDirettiva(value.substring(start+1,finish),_tagLibrary, _beanLibrary), _beanLibrary)+
				value.substring(finish+1),_tagLibrary, _beanLibrary);
		}
		if(value.toUpperCase().indexOf("%EXECUTETAG.")>-1){
			int start = value.toUpperCase().indexOf("%EXECUTETAG."); 
			int finish = value.indexOf("%",start+1);
			return checkAttribute(value.substring(0,start)+
				direttivaTag(preDirettiva(value.substring(start+1,finish),_tagLibrary, _beanLibrary), _tagLibrary)+
				value.substring(finish+1),_tagLibrary, _beanLibrary);
		}
		if(value.toUpperCase().indexOf("%EXECUTEFORMAT.")>-1){
			int start = value.toUpperCase().indexOf("%EXECUTEFORMAT."); 
			int finish = value.indexOf("%",start+1);
			return 	checkAttribute(value.substring(0,start)+
					direttivaFormat(preDirettiva(value.substring(start+1,finish),_tagLibrary, _beanLibrary))+
					value.substring(finish+1),_tagLibrary, _beanLibrary);
		}
		
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
		return value;
	}	
	return value;
	
}
private String direttivaBean(String direttiva, Hashtable _beanLibrary) {
    try {
		String result = "";
		if(direttiva.toUpperCase().indexOf("EXECUTE")!=0) return direttiva;
		Object o_requsting = null;
		o_requsting = executeBean(o_requsting,direttiva,_beanLibrary,1); 
        if(o_requsting!=null) result = o_requsting.toString();
        else return "";
        return result;
    } catch (Exception e) {
        setError(e,iStub.log_ERROR);
        return "";
    }
}
private String direttivaFormat(String direttiva) {
    try {
		String result = "";
		if(direttiva.toUpperCase().indexOf("EXECUTE")!=0) return direttiva;
		direttiva = precompileDirettivaBean(direttiva,0);
		StringTokenizer st = new StringTokenizer(direttiva, ".");
		if (st.hasMoreTokens()) st.nextToken(); 
		if (st.hasMoreTokens()){ 
			String value = st.nextToken();
			if(	value!=null &&
				value.length()>1 &&
				value.charAt(0)=='(' &&
				value.charAt(value.length()-1)==')'){
				value = re_precompileDirettivaBean(precompileDirettivaBean(value.substring(1,value.length()-1),2));
				if (st.hasMoreTokens()){
					String format = st.nextToken();
					if(	format!=null &&
						format.length()>1 &&
						format.charAt(0)=='(' &&
						format.charAt(format.length()-1)==')'){
						return prepareContentString(value,re_precompileDirettivaBean(format.substring(1,format.length()-1)));
					}else return result; 
				}else return result; 
			}else return result;
		}else return result;	
    } catch (Exception e) {
        setError(e,iStub.log_ERROR);
        return "";
    }
}
private String direttivaTag(String direttiva, Hashtable _tagLibrary) {
	try{
		if(direttiva.toUpperCase().indexOf("EXECUTE")!=0) return direttiva;	
		String tagName="";
		String tagMethod="";
		String tagParameter="";
		StringTokenizer st = new StringTokenizer(direttiva, ".");
		if (st.hasMoreTokens()){
			st.nextToken();
			if (st.hasMoreTokens()){
				tagName = st.nextToken(); 
					if (st.hasMoreTokens()){
						tagMethod = st.nextToken();
						if (st.hasMoreTokens())	tagParameter = st.nextToken(); 
					}	
			}		
		}
		report_element tag_lib = (report_element)_tagLibrary.get(tagName+"_ids_"+motore.hashCode());
		if(tag_lib==null) tag_lib = (report_element)_tagLibrary.get(tagName);
		if (tag_lib!=null){
			String result = "";
			if (tagParameter.length()==0)
				result = getValue(tag_lib,tagMethod).toString();
			else result = getValueWithParemeters(null,tag_lib,tagMethod, "java.lang.String:"+tagParameter).toString();	
			return result;			
		}
		else return "";
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
		return "";
	}	
}
public Object executeBean(Object requested, String execution, Hashtable _beanLibrary, int check) {
    try {
    	

    	
	    if(execution==null || execution.trim().length()==0) return requested; 
	    execution = precompileDirettivaBean(execution,0);
	    Vector pre_elements = new Vector();
		StringTokenizer st_elements = new StringTokenizer(execution, ".");	    
		if(check==1){
        	if (st_elements.hasMoreTokens()) st_elements.nextToken();
        	if (st_elements.hasMoreTokens()) {
	        	String beanName = st_elements.nextToken();
	    		report_element bean_lib;
 	       		if (beanName.indexOf("SYSTEM:") == 0)
  	          		bean_lib = (report_element) _beanLibrary.get(beanName);
   		   		else{
            		bean_lib = (report_element) _beanLibrary.get(this.type_s_tag +"BEAN:" + beanName+"_ids_"+motore.hashCode());
            		if(bean_lib==null) bean_lib = (report_element) _beanLibrary.get(this.type_s_tag +"BEAN:" + beanName);
   		   		}	
            	if (bean_lib!=null) requested = bean_lib.getContent();	
        	}
		}
        while (st_elements.hasMoreTokens()) pre_elements.addElement(st_elements.nextToken());

        String cur_method = ""; 
        for(int i=0;i<pre_elements.size();i++){
			cur_method+= (String)pre_elements.elementAt(i);
	        boolean ver_method = (cur_method.indexOf("(") > -1 &&
            					 cur_method.indexOf(")") == cur_method.length()-1 &&
            					 cur_method.indexOf(")") > cur_method.indexOf("("));
	        if(!ver_method && i==pre_elements.size()-2){
				String cur_method_next = (String)pre_elements.elementAt(i+1);
	        	boolean ver_method_next = 	(cur_method_next.indexOf("(") > -1 &&
            					 		cur_method_next.indexOf(")") == cur_method_next.length()-1 &&
            					 		cur_method_next.indexOf(")") > cur_method_next.indexOf("("));
		        if(!ver_method_next){
		        	cur_method+="(java.lang.String:"+(String)pre_elements.elementAt(i+1)+")";
		        	ver_method=true;
		        	i++;		        	
		        }else cur_method+=".";	
	        }
	        if(!ver_method && i==pre_elements.size()-1){ 
		        cur_method+="()";
		        ver_method=true;
	        }
			if(!ver_method && i<pre_elements.size()-2){
		        cur_method+=".";
	        }
			if(ver_method){
        		String beanMethod = ""; 
        		String beanParameter = "";
            	beanMethod = cur_method.substring(0, cur_method.indexOf("("));	        	
            	beanParameter = cur_method.substring(cur_method.indexOf("(") + 1, cur_method.indexOf(")"));            	
				beanParameter = precompileDirettivaBean(beanParameter,1);
				if(requested==null)
	           		requested = this.getValueWithParemeters(_beanLibrary,requested,beanMethod,beanParameter);
	           	else{	
	            	if (beanParameter.length() > 0) 
 		           		requested = this.getValueWithParemeters(_beanLibrary,requested,beanMethod,beanParameter);
   		         	else requested = this.getValue(requested, beanMethod);
	           	} 	
            	cur_method=""; 
	        }
        }    
		return requested;
    } catch (Exception e) {
        setError(e,iStub.log_ERROR);
        return null;
    } 	    

}
public Object getValue(Object requested, String nome){ 
	if (nome == null || nome.trim().length()==0) return null;
	try{
		java.lang.reflect.Method mtd = null;
		mtd = requested.getClass().getMethod(nome,null);
		if(mtd==null) return null;
		Object resultObject = null;

		resultObject = mtd.invoke(requested, null);
		if(mtd.getReturnType().getName().equalsIgnoreCase("JAVA.LANG.STRING")) return adaptResult(resultObject,mtd.getReturnType().getName());
		else return resultObject;
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		return null;
	}	

}
public Object getValue(String nome){ 
	if (nome == null || nome.trim().length()==0) return null;
	try{
		java.lang.reflect.Method mtd = null;
		mtd = this.getClass().getMethod(nome,null);
		if(mtd==null) return null;
		Object resultObject = null;

		resultObject = mtd.invoke(this, null);
		if(mtd.getReturnType().getName().equalsIgnoreCase("JAVA.LANG.STRING")) return adaptResult(resultObject,mtd.getReturnType().getName());
		else return resultObject;
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		return null;
	}	

}
public Object getValueWithParemeters(Hashtable _beanLibrary, Object requested, String nome, String parameters){ 
	if (nome == null || nome.trim().length()==0) return null;
	Object resultObject = null;
	try{ 
			String retName = "";
			java.lang.reflect.Method mtd = null;
			Hashtable out = prepareParemeters(_beanLibrary,parameters); 
	
			Object prm[] = (Object[])out.get("prs");
			Class[] cls = (Class[])out.get("cls");
			try{
				if(requested!=null) mtd = requested.getClass().getMethod(nome.trim(),cls);
			}catch(Exception e){
					int maxCount = 0;
					boolean fine = false;
					while(!fine && maxCount<20){
						int count = 0;
						for(int i=0;i<cls.length;i++){
							if(cls[i]!=null && !cls[i].isPrimitive() && !cls[i].getName().equals("java.lang.Object")){
								cls[i] = cls[i].getSuperclass();
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
			}
				
			
			if(requested==null){ 
				try{	
					Class cl = Class.forName(nome); 
					resultObject = cl.getConstructor(cls).newInstance(prm); 
					return resultObject; 
				}catch(Exception e){
					setError(e,iStub.log_WARN); 
					return null;
				}	
			} 
			
			if (mtd==null){ 
				try{
					Class cl = Class.forName(nome); 
					resultObject = cl.getConstructor(cls).newInstance(prm); 
					retName = requested.getClass().getName();
					if(retName.equalsIgnoreCase("JAVA.LANG.STRING")) return adaptResult(resultObject,retName);
					else return resultObject; 
				}catch(Exception e){
					setError(e,iStub.log_WARN); 
					return null; 
				}	
			}	
			if(mtd!=null){ 
					resultObject = mtd.invoke(requested,  prm);
					retName = mtd.getReturnType().getName();
			}	
		if(retName.equalsIgnoreCase("JAVA.LANG.STRING")) return adaptResult(resultObject,retName);
		else return resultObject; 
	}catch(Exception e){
		setError(e,iStub.log_WARN); 
		return null;
	}
}

private static String normalizePG(String input){
	
	if(	input.toUpperCase().indexOf("(CHAR:")!=-1 ||
		input.toUpperCase().indexOf(";CHAR:")!=-1 ||
		input.toUpperCase().indexOf("(STRING:")!=-1 ||
		input.toUpperCase().indexOf(";STRING:")!=-1 ||
		input.toUpperCase().indexOf("."+"STRING:")!=-1
	   ){
			input = normalizeParentezi_Grafi(0,"CHAR:",input); 
			input = normalizeParentezi_Grafi(0,";CHAR:",input);
			input = normalizeParentezi_Grafi(0,"(STRING:",input);
			input = normalizeParentezi_Grafi(0,";STRING:",input);
			input = normalizeParentezi_Grafi(0,"."+"STRING:",input+"");
		}
	return input;	
}

private static String normalizeParentezi_Grafi(int start,String paR, String input){
//	if(true) return input;
	String result="";
	if(	input.toUpperCase().indexOf(paR,start)==-1 ||
		(input.indexOf("(",start)==-1 &&
		input.indexOf(")",start)==-1 &&
		input.indexOf("{",start)==-1 &&
		input.indexOf("}",start)==-1 
		)) return input;
	int first=0;
	try{
		

		first = input.toUpperCase().indexOf(paR,start)+paR.length();
		char finish_s=input.charAt(first);
		char finish_s2='-';
		int i=0;
		if(finish_s!='"' && finish_s!='\''){
			i=first;
			finish_s=';';
			finish_s2=')';
		}else{
			i=first+1;
		}
				
		result=input.substring(0,first+1);
		boolean fin=true;
		while(fin && i<input.length()){
			char corrente = input.charAt(i);
			switch (corrente){
				case '(':
				result+=report_element.SYMBOL_7;
				break;
				case ')':
				result+=report_element.SYMBOL_8;
				break;
				case '{':
				result+=report_element.SYMBOL_5;
				break;
				case '}':
				result+=report_element.SYMBOL_6;
				break;
			default:			
				result+=corrente;				
			}
			i++;
			if(input.charAt(i)==finish_s) fin=false;
			if(finish_s2!='-' && input.charAt(i)==finish_s2) fin=false;
		}
		result+=input.substring(i,input.length());			
	}catch(Exception e){		
	}
	result = normalizeParentezi_Grafi(first,paR,result);	
	return result;
}

public static String precompileDirettivaBean(String value, int level) {
	boolean flag_level_0 = false;		//	()
	boolean flag_level_char_0 = false;	//	CHAR:
	boolean flag_level_char_1 = false;	//	CHAR:'
	boolean flag_level_string_0 = false;//	STRING:
	boolean flag_level_string_1 = false;//	STRING:"
	boolean flag_level_string_2 = false;//	STRING:'		
	String Result = "";
	if(level==0){
		if(value.indexOf("(")==-1) return value;
		
		value = normalizePG(value);
		for(int i=0;i<value.length();i++){
			char corrente = value.charAt(i);
			if(flag_level_0){
				switch (corrente){
					case '.':
						Result+=report_element.SYMBOL_0;
						break;
					case ')':
						flag_level_0=!flag_level_0;
						Result+=corrente;
						break;
					default:			
						Result+=corrente;
				}		
			}else{
				switch (corrente){				
					case '(':
						flag_level_0=!flag_level_0;
						Result+=corrente;
						break;
					default:			
						Result+=corrente;
				}		
			}
			
		}

	}
	if(level==1){
		if(	value.toUpperCase().indexOf("(CHAR:")==-1 &&
			value.toUpperCase().indexOf(";CHAR:")==-1 &&
			value.toUpperCase().indexOf("(STRING:")==-1 &&
			value.toUpperCase().indexOf(";STRING:")==-1 &&
			value.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:")==-1) return value;
		String buf = value;
		for(int i=0;i<value.length();i++){
			buf = value.substring(i);
			boolean is = false;
			if(	buf.toUpperCase().indexOf("(CHAR:'")==0 ||
				buf.toUpperCase().indexOf(";CHAR:'")==0){
					flag_level_char_1=!flag_level_char_1;
					Result+=buf.substring(0,6);
					i=i+7;
					is = true;
				}
			if(!is &&
				(buf.toUpperCase().indexOf("(CHAR:")==0 ||
				buf.toUpperCase().indexOf(";CHAR:")==0)){
					flag_level_char_0=!flag_level_char_0;
					Result+=buf.substring(0,6);
					i=i+6;
					is = true;
				}		
				
			if(flag_level_char_1) flag_level_char_0=false;

			if(!is &&
				(buf.toUpperCase().indexOf("(STRING:\"")==0 ||
				buf.toUpperCase().indexOf(";STRING:\"")==0 ||
				buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:\"")==0 )){
					flag_level_string_1=true;
					if(buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:\"")==0){
						Result+=buf.substring(0,13);
						i=i+14;
					}else{
						Result+=buf.substring(0,8);
						i=i+9;
					}						
					is = true;
				}
			if(!is &&				
				(buf.toUpperCase().indexOf("(STRING:'")==0 ||
				buf.toUpperCase().indexOf(";STRING:'")==0 ||
				buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:'")==0 )){
					flag_level_string_2=true;
					if(buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:'")==0){
						Result+=buf.substring(0,13);
						i=i+14;
					}else{
						Result+=buf.substring(0,8);
						i=i+9;
					}						
					is = true;
				}
			if(!is &&				
				(buf.toUpperCase().indexOf("(STRING:")==0 ||
				buf.toUpperCase().indexOf(";STRING:")==0 ||
				buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:")==0 )){
					flag_level_string_0=true;
					if(buf.toUpperCase().indexOf(report_element.SYMBOL_0+"STRING:")==0){
						Result+=buf.substring(0,13);
						i=i+13;
					}else{
						Result+=buf.substring(0,8);
						i=i+8;
					}						
					is = true;
				}		
			if(flag_level_string_1 || flag_level_string_2) flag_level_string_0=false; 
			char corrente = value.charAt(i);
			if(	!flag_level_char_0 &&
				!flag_level_char_1 &&				
				!flag_level_string_0 &&
				!flag_level_string_1 &&
				!flag_level_string_2){
				if(corrente == '\\'){
					if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
				}else Result+=corrente;
			}		
			
			if(flag_level_char_0 || flag_level_char_1){
				switch (corrente){
					case ';':
						if(flag_level_char_0) flag_level_char_0=false;
						else Result+=report_element.SYMBOL_2;
						break;
					case ':':
						Result+=report_element.SYMBOL_1;
						break;
					case '\'':
						if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
						else{
							if(flag_level_char_1)
								flag_level_char_1=false;
							else Result+=corrente;
						}	
						break;
					case '\\':
						if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
						break;
					default:
						Result+=corrente;
				}		
			}
			if(flag_level_string_0 || flag_level_string_1 || flag_level_string_2){
				switch (corrente){
					case ';':
						if(flag_level_string_0) flag_level_string_0=false;
						else Result+=report_element.SYMBOL_2;
						break;
					case ':':
						Result+=report_element.SYMBOL_1;
						break;
					case '"':
						if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
						else{
							if(flag_level_string_1)
								flag_level_string_1=false;
							else Result+=corrente;
						}	
						break;
					case '\'':
						if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
						else{
							if(flag_level_string_2)
								flag_level_string_2=false;
							else Result+=corrente;
						}	
						break;
					case '\\':
						if(i>0 && value.charAt(i-1)=='\\') Result+=corrente;
						break;
					default:
						Result+=corrente;
				}		
			}
		} 
	}
	if(level==2){
		if(	(value.charAt(0)!='"' && value.charAt(value.length()-1)!='"') &&
			(value.charAt(0)!='\'' && value.charAt(value.length()-1)!='\'')
			) return value;
		else{
			if(value.length()>1) Result = value.substring(1,value.length()-1);
			else return value;
		}
	}	
	return Result;
}
private String preDirettiva(String value, Hashtable _tagLibrary, Hashtable _beanLibrary) {
	String result=value;	 
	if(value.toUpperCase().indexOf("{EXECUTE")==-1 && value.toUpperCase().indexOf("EXECUTE")==-1 && value.toUpperCase().indexOf("%EXECUTE")==-1) return result;
	String valueBuf = value;
	try{
		if(valueBuf.toUpperCase().indexOf("{EXECUTE")>-1)
			result="";
		else {
			if(result.toUpperCase().indexOf("EXECUTEBEAN.")==0) return preDirettiva(direttivaBean(result, _beanLibrary), _tagLibrary, _beanLibrary);
			if(result.toUpperCase().indexOf("EXECUTETAG.")==0) return preDirettiva(direttivaTag(result, _tagLibrary), _tagLibrary, _beanLibrary);
			if(result.toUpperCase().indexOf("EXECUTEFORMAT.")==0) return preDirettiva(direttivaFormat(result), _tagLibrary, _beanLibrary);
		}	
		while(valueBuf.toUpperCase().indexOf("{EXECUTE")>-1){   
			int start = valueBuf.toUpperCase().indexOf("{EXECUTE"); 
			int sub_start = start+1; 
			int counter = 1;
			String sub_value = valueBuf.substring(sub_start);				
			while (	sub_value.toUpperCase().indexOf("{EXECUTE")>-1 &&
					sub_value.toUpperCase().indexOf("{EXECUTE")<sub_value.toUpperCase().indexOf("}")
				){
				sub_start = sub_value.toUpperCase().indexOf("{EXECUTE")+1;
				counter++;
				sub_value = sub_value.substring(sub_start);
			}
			sub_start = start+1;
			int finish = 0;	
			while(counter!=0 && valueBuf.indexOf("}",sub_start)>-1) {
				finish = valueBuf.indexOf("}",sub_start); 
				sub_start = finish+1; 
				counter--;
			}
			String value_d = 	preDirettiva(valueBuf.substring(start+1,finish), _tagLibrary, _beanLibrary);
			if(value_d.toUpperCase().indexOf("EXECUTEBEAN.")==0) value_d = preDirettiva(direttivaBean(value_d, _beanLibrary), _tagLibrary, _beanLibrary);
			if(value_d.toUpperCase().indexOf("EXECUTETAG.")==0) value_d = preDirettiva(direttivaTag(value_d, _tagLibrary), _tagLibrary, _beanLibrary);
			if(value_d.toUpperCase().indexOf("EXECUTEFORMAT.")==0) value_d = preDirettiva(direttivaFormat(value_d), _tagLibrary, _beanLibrary);
			result+=valueBuf.substring(0,start)+value_d;
			valueBuf=valueBuf.substring(finish+1);
		}
		result+=valueBuf;
		return result;
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
		return value;
	}	
}
public static String prepareContentString(String value, String formatSG) {
	String content=value;
	java.util.StringTokenizer st = new java.util.StringTokenizer(formatSG, "|");
	while (st.hasMoreTokens()){
		String formatS = st.nextToken();
		boolean is=false;
		if(formatS.length()>0){
			if (formatS.toUpperCase().indexOf("NUMBER:")==0){
				try{
					String format = formatS.substring(7);
					DecimalFormat df = new DecimalFormat(format);
					content = df.format(new java.math.BigDecimal(content.trim()).doubleValue());
				}catch(Exception e){}
				is=true;
			}
			if(!is){
				if (formatS.toUpperCase().indexOf("DATE:")==0){ 
					try{
						String format = formatS.substring(5);
						SimpleDateFormat df = new SimpleDateFormat(format);
						content = df.format(new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd").getTime()));
					}catch(Exception e){
						try{
							String format = formatS.substring(5);
							java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
							content = df.format(new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime()));						
						}catch(Exception ex){
						}
					}
					is=true;
				}
				if(!is){
					if (formatS.toUpperCase().indexOf("ISNULL:")==0){ 
						try{
							String format = formatS.substring(7);
							if (content.trim().equals("0")) content = format;
							else{
								if (new java.math.BigDecimal(content).doubleValue()==0) content = format;
							}									
						}catch(Exception e){}
						is=true;
					}
					if(!is){
						if (formatS.toUpperCase().indexOf("NOTNULL:")==0){ 
							try{
								String format = formatS.substring(8);
								if (content.trim().equals("")) content = format;
							}catch(Exception e){}
							is=true;
						}
						if(!is){
							if (formatS.toUpperCase().indexOf("TRIM:")>-1){
								try{
									content = content.trim();
								}catch(Exception e){}
								is=true;	
							}
							if(!is){
								if (formatS.toUpperCase().indexOf("UPPERCASE:")>-1){
									try{
										content = content.toUpperCase();
									}catch(Exception e){}
									is=true;	
								}
								if(!is){
									if (formatS.toUpperCase().indexOf("LOWERCASE:")>-1){
										try{
											content = content.toLowerCase();
										}catch(Exception e){}
										is=true;	
									}
									if(!is){
										if (formatS.toUpperCase().indexOf("SUBSTRING:")>-1){
											try{
												String format = formatS.substring(10+formatS.indexOf("SUBSTRING:"));					
												content = content.substring(0,Integer.valueOf(format).intValue());
											}catch(Exception e){}
											is=true;	
										}
										if(!is){
											if (formatS.toUpperCase().indexOf("REPLACE:")>-1){
												try{
													String format = formatS.substring(8);
													if(format.charAt(0)=='[' && format.charAt(format.length()-1)==']'){						
														java.util.StringTokenizer stf = new java.util.StringTokenizer(format, "--");
														String vFirst = stf.nextToken();
														String vSecond = stf.nextToken();
														content = content.replace(vFirst.charAt(1),vSecond.charAt(0));	
													}	
													content = content.substring(0,Integer.valueOf(format).intValue());
												}catch(Exception e){}
												is=true;
											}		
										}
									}	
								}	
							}	
						}	
					}	
				}	
			}	
		}
	}		
	return content;		
}
private static String re_precompileDirettivaBean(String value) {
	String Result = value;
	if(value.indexOf("_$")==-1) return value;
	else{
		boolean fine = true;
		int index=-1;
		while (fine){
			index=-1;
			fine = false;
			index = Result.indexOf(report_element.SYMBOL_0);
			if (index>-1){
				Result=Result.substring(0,index)+"."+Result.substring(index+6);
				fine = true;
			}
			if(!fine){
				index = Result.indexOf(report_element.SYMBOL_1);
				if (index>-1){
					Result=Result.substring(0,index)+":"+Result.substring(index+6);
					fine = true;
				}				
				if(!fine){				
					index = Result.indexOf(report_element.SYMBOL_2);
					if (index>-1){
						Result=Result.substring(0,index)+";"+Result.substring(index+6);
						fine = true;
					}
					if(!fine){
						index = Result.indexOf(report_element.SYMBOL_3);
						if (index>-1){
							Result=Result.substring(0,index)+"["+Result.substring(index+6);
							fine = true;
						}
						if(!fine){
							index = Result.indexOf(report_element.SYMBOL_4);
							if (index>-1){
								Result=Result.substring(0,index)+"]"+Result.substring(index+6);
								fine = true;
							}
							if(!fine){
								index = Result.indexOf(report_element.SYMBOL_5);
								if (index>-1){
									Result=Result.substring(0,index)+"{"+Result.substring(index+6);
									fine = true;
								}
								if(!fine){
									index = Result.indexOf(report_element.SYMBOL_6);
									if (index>-1){
										Result=Result.substring(0,index)+"}"+Result.substring(index+6);
										fine = true;
									}
									if(!fine){
										index = Result.indexOf(report_element.SYMBOL_7);
										if (index>-1){
											Result=Result.substring(0,index)+"("+Result.substring(index+6);
											fine = true;
										}
										if(!fine){
											index = Result.indexOf(report_element.SYMBOL_8);
											if (index>-1){
												Result=Result.substring(0,index)+")"+Result.substring(index+6);
												fine = true;
											}
											if(!fine){
												index = Result.indexOf(report_element.SYMBOL_9);
												if (index>-1){
													Result=Result.substring(0,index)+"\\"+Result.substring(index+6);
													fine = true;
												}
												if(!fine){
													index = Result.indexOf(report_element.SYMBOL_10);
													if (index>-1){
														Result=Result.substring(0,index)+"\""+Result.substring(index+6);
														fine = true;
													}
													if(!fine){
														index = Result.indexOf(report_element.SYMBOL_11);
														if (index>-1){
															Result=Result.substring(0,index)+"'"+Result.substring(index+6);
															fine = true;
														}
														if(!fine){
															index = Result.indexOf(report_element.SYMBOL_12);
															if (index>-1){
																Result=Result.substring(0,index)+","+Result.substring(index+6);
																fine = true;
															}
														}	
													}	
												}	
											}	
										}	
									}	
								}
							}
						}
					}
				}
			}
		}	
	}
	return Result;
}
public void setError(Exception e, String level) {
	if(!viewError) return;
	try {
		if (motore!=null)
			motore.setError(e,this.type_tag,level);
	} catch (Exception ex) {
	}
}

public Hashtable prepareParemeters (Hashtable _beanLibrary, String parameters) {
	Hashtable out = new Hashtable();
	Vector datesFT = new Vector();
	Vector datesST = new Vector();
	String type_prim = "INT;SHORT;DOUBLE;LONG;FLOAT;CHAR;BYTE;BOOLEAN;"; 
	StringTokenizer st = new StringTokenizer(parameters, ";");
	while (st.hasMoreTokens()){ 
		String curToken = st.nextToken();
		java.util.StringTokenizer stInt = new java.util.StringTokenizer(curToken, ":");  
		String FT = "";
		String ST = "";
		if (stInt.hasMoreTokens()){
			FT = re_precompileDirettivaBean(stInt.nextToken());
			if (stInt.hasMoreTokens()){
				ST = re_precompileDirettivaBean(stInt.nextToken());						
				datesFT.add(FT);
				datesST.add(ST);
			}else{
				ST = "";						
				datesFT.add(FT);
				datesST.add(ST);
			}	
		}		
	}
	Object[] prs = new Object[datesFT.size()];
	Class[] cls = new Class[datesFT.size()];

	for(int i=0;i<datesFT.size();i++){
		try{
			String FT = ((String)datesFT.get(i)).trim();
			String ST = (String)datesST.get(i);
			
			boolean is = false;
			if(ST!=null && ST.length()>0 && ST.charAt(0)=='[' && ST.charAt(ST.length()-1)==']'){
				ST = ST.substring(1,ST.length()-1);				
				report_element bean_set = (report_element)_beanLibrary.get(this.type_s_tag +"BEAN:"+ST+"_ids_"+motore.hashCode());
				if(bean_set==null) bean_set = (report_element)_beanLibrary.get(this.type_s_tag +"BEAN:"+ST);
				prs[i] = bean_set.getContent();
				cls[i] = bean_set.getContent().getClass();
				is = true;
			}
			if(!is){
				if(type_prim.indexOf(FT.toUpperCase()+";")>-1){
					if(FT.equalsIgnoreCase("int")){
						prs[i] = new Integer(ST);
						cls[i] = java.lang.Integer.TYPE;
						is = true;
					}
					if(!is){	
						if(FT.equalsIgnoreCase("short")){
							prs[i] = new Short(ST);
							cls[i] = java.lang.Short.TYPE;
							is = true;
						}
						if(!is){	
							if(FT.equalsIgnoreCase("double")){
								prs[i] = new Double(ST);
								cls[i] = java.lang.Double.TYPE;
								is = true;
							}
							if(!is){	
								if(FT.equalsIgnoreCase("long")){
									prs[i] = new Long(ST);
									cls[i] = java.lang.Long.TYPE;
									is = true;
								}
								if(!is){	
									if(FT.equalsIgnoreCase("float")){
										prs[i] = new Float(ST);
										cls[i] = java.lang.Float.TYPE;
										is = true;
									}
									if(!is){	
										if(FT.equalsIgnoreCase("byte")){
											prs[i] = new Byte(ST);
											cls[i] = java.lang.Byte.TYPE;
											is = true;
										}
										if(!is){	
											if(FT.equalsIgnoreCase("boolean")){
												prs[i] = new Boolean(ST);
												cls[i] = java.lang.Boolean.TYPE;
												is = true;
											}
											if(!is){	
												if(FT.equalsIgnoreCase("char")){
													if(ST!=null && ST.length()>0)
														prs[i] = new Character(ST.charAt(0));
													else{
														ST = " ";
														prs[i] =new Character(ST.charAt(0));
													}	
													cls[i] = java.lang.Character.TYPE;
													is = true;
												}
											}	
										}	
									}	
								}	
							}	
						}	
					}		
				}
				if(!is){
					if(FT.equalsIgnoreCase("STRING") || FT.equalsIgnoreCase("JAVA.LANG.STRING")){
						prs[i]=ST;
						cls[i]=prs[i].getClass();
						is = true;
					}
					if(!is){
						if(FT.equalsIgnoreCase("BIGDECIMAL") || FT.equalsIgnoreCase("JAVA.MATH.BIGDECIMAL")){
							prs[i]=new java.math.BigDecimal(ST);
							cls[i]=prs[i].getClass();				
							is = true;
						}
						if(!is){
							report_element bean_set = (report_element)_beanLibrary.get(this.type_s_tag +"BEAN:"+ST+"_ids_"+motore.hashCode());
							if(bean_set==null) bean_set = (report_element)_beanLibrary.get(this.type_s_tag +"BEAN:"+ST);
							prs[i] = bean_set.getContent();
							cls[i] = bean_set.getContent().getClass();
							is = true;
						}
					}	
				}	
			}	
		}catch(Exception e){
			setError(e,iStub.log_WARN);
		}
	}	
	out.put("prs",prs);
	out.put("cls",cls);
	return out;
}
}

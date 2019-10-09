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

package neohort.universal.output.lib;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.util.I_OutputRunTime;
import neohort.universal.output.util.Parser_Java;
import neohort.util.util_format;
import neohort.util.util_reflect;
import neohort.util.util_xml_ibm;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public abstract class report_element_base implements report_element {
	private static final long serialVersionUID = -1L;
	public String ID = "";
	public Vector<String> Parameters = new Vector<String>();
	public String name = "REPORT_ELEMENT_BASE";
	public Object content;
	public report_element parent;
	public I_OutputRunTime motore;
	public boolean drawCanvas = false;
	public int _sys_border = 0;
	public int _sys_align = 0;
	public boolean isError=false;
	public Node nodeCurrent;
	public String SYSATTR = "";
	public String STYLE_ID = "";
	public String PARSER_JAVA = "true";
	public style internal_style;	
	public HashMap<String,Object> external_parameters;
	public HashMap<String,String> unsupported_attributes = new HashMap<String, String>();
	
		
public report_element_base() {
	super();
	Parameters.addElement("ID");
	reimposta();	
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {}
public Object execute(Hashtable<String, report_element_base> _beanLibrary) {
	return null;
}
public Object executeBean(Object requested, String execution, Hashtable<String, report_element_base> _beanLibrary, int check) {
	return new Parser_Java(this.name,this.motore).executeBean(requested, execution, _beanLibrary, check);
}
public abstract void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);
public abstract void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary);

@SuppressWarnings({ "unchecked", "rawtypes" })
public boolean add2content(Object value) {
	if(content==null || value==null)
		return false;
	if(content instanceof Collection){
		((Collection)content).add(value);
		return true;
	}
	return false;
}
@SuppressWarnings({ "rawtypes" })
public Object getContentLastElement() {
	if(content==null)
		return null;
	if(content instanceof java.util.List){
		if(!((java.util.List)content).isEmpty())
			return ((java.util.List)content).get(((java.util.List)content).size()-1);
	}
	return null;
}

@SuppressWarnings({ "rawtypes", "unchecked" })
public List<Object> getContentAsList() {
	if(content==null)
		return null;
	if(content instanceof List)
		return (List)content;
	return null;
}

@SuppressWarnings({ "rawtypes", "unchecked" })
public Map<Object,Object> getContentAsMap() {
	if(content==null)
		return null;
	if(content instanceof Map)
		return (Map)content;
	return null;
}

public Object getContent() {
	return content;
}
public Color getField_Color(Class<?> cl, String name, Color d_value) {
	return getField_Color(name, d_value);
}
public Color getField_Color( String name, Color d_value) {
	Color result = d_value;
	if(name==null || name.equals(""))
		return result;
	if(name.indexOf(",")>-1){
		try{
			int _r = -1;
			int _g = -1;
			int _b = -1;
			java.util.StringTokenizer st = new java.util.StringTokenizer(name, ",");
			if(st.hasMoreTokens()) _r = Integer.valueOf(st.nextToken()).intValue();
			if(st.hasMoreTokens()) _g = Integer.valueOf(st.nextToken()).intValue();
			if(st.hasMoreTokens()) _b = Integer.valueOf(st.nextToken()).intValue();
			if(	_r != -1 &&
				_g != -1 &&
				_b != -1)
				result = new Color(_r,_g,_b);
		}catch(Exception e){}	
	}else{	
		try{
			result = (Color)Color.class.getField(name).get(Color.class);
		}catch(Exception e){
			try{
				result = (Color)Color.class.getField(name.toUpperCase()).get(Color.class);
			}catch(Exception ex){
				try{
					result = (Color)Color.class.getField(name.toLowerCase()).get(Color.class);
				}catch(Exception exp){}
			}
		}
	}	
	return result;
}
public int getField_Int(Class<?> cl, String name, int d_value) {
	int result = d_value;
	if(name==null || name.equals("") || cl==null)
		return result;
	try{
		result = cl.getField(name).getInt(cl);
	}catch(Exception e){
		try{
			result = cl.getField(name.toUpperCase()).getInt(cl);
		}catch(Exception ex){
			try{
				result = cl.getField(name.toLowerCase()).getInt(cl);
			}catch(Exception exp){}
		}
	}
	return result;
}
public java.lang.String getID() {
	return ID;
}
public Node getNodeCurrent() {
	return nodeCurrent;
}
public Vector<String> getParameters() {
	return Parameters;
}
public report_element getParent() {
	return parent;
}
public String getName() {
	if (name!=null) return name;
	else return "";
}
public java.lang.String getSTYLE_ID() {
	return STYLE_ID;
}
public java.lang.String getSYSATTR() {
	return SYSATTR;
}
public Object getValue(String nome){
	return new Parser_Java(this.name,this.motore).getValue(nome);	
}
public boolean isActive(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary, Hashtable<String, report_element_base> _styleLibrary) {
	return isActive();
}
public boolean isActive() {
	return false;
}
public boolean isActiveCycle() {
	return false;
}
public boolean isPreActive(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary, Hashtable<String, report_element_base> _styleLibrary) {
	return isPreActive();
}
public boolean isPreActive() {
	return true;
}
public boolean isPreActiveCycle() {
	return true;
}
public String prepareContentString(String formatSG) {
	String content=(String)this.getContent();
	java.util.StringTokenizer st = new java.util.StringTokenizer(formatSG, "|");
	while (st.hasMoreTokens()){
		String formatS = st.nextToken();
		if(formatS.length()>0){
			boolean is=false;
			if (formatS.toUpperCase().indexOf("NUMBER:")==0){
				try{
					String format = formatS.substring(7);
					java.text.DecimalFormat df = new java.text.DecimalFormat(format);
					content = df.format(new java.math.BigDecimal(content.trim()).doubleValue());
				}catch(Exception e){}
				is=true;
			}
			if(!is){
				if (formatS.toUpperCase().indexOf("DATE:")==0){ 
					try{
						String format = formatS.substring(5);
						java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
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
public boolean refreshText() {
	return false;
}
public abstract void reimposta();
public String RETURN(String nome){ 
	return getValue(nome).toString();	
}
public void setCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {}
public void setContent(Object newContent) {
	content = newContent;
}
public void setError(Exception e, String level) {
	try {
		isError = true;
		motore.setError(e,name,level);
	} catch (Exception ex) {
	}
}
public void setID(java.lang.String newID) {
	ID = newID;
}
public void setMotore(I_OutputRunTime newMotore) {
	motore = newMotore;
}
public void setNodeCurrent(Node newNodeCurrent) {
	nodeCurrent = newNodeCurrent;
}
public void setParameters(Vector<String> newParameters) {
	Parameters = newParameters;
}
public void setParent(report_element newParent) {
	parent = newParent;
}
public void setName(java.lang.String newPdf_name) {
	name = newPdf_name;
}

public void setSTYLE_ID(java.lang.String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}

public void setSYSATTR(java.lang.String newSYSATTR) {
	SYSATTR = newSYSATTR;
}

public boolean setValue(String nome, Object[] value){
	if (nome == null || nome.trim().length()==0 || value == null) return false;
	try{
		return util_reflect.setValue(this,nome,value);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}catch(Throwable t){
		setError(new Exception(t.toString()),iStub.log_WARN);
	}
	return false;
/*
	try{
		java.lang.reflect.Method mtd = null;
		Class[] cls = new Class[value.length];
		for(int i=0;i<value.length;i++) cls[i]=value[i].getClass();
		mtd = this.getClass().getMethod(nome,cls);
		if(mtd==null) return;
		mtd.invoke(this, value);		
	}catch(java.lang.IllegalAccessException e){
		setError(e,iStub.log_WARN);
	}catch(java.lang.reflect.InvocationTargetException e){
		setError(e,iStub.log_WARN);
	}catch(Exception e){
		setError(e,iStub.log_WARN);		
	}	
*/	
}

public String toString() {
	String result = "";
	if (parent==null) result+= "[NULL -> ";
	else result+=parent.toString();
	result+=" "+getName()+":"+getID()+"]";
	return result;
}
public String toXMLString_finish() {
	try{
		if(SYSATTR.length()==2 && SYSATTR.charAt(0)=='0'){
			if(nodeCurrent!=null){
				if(
					nodeCurrent.getNodeType()==1
					&&
					content!=null)
					return verification((String)content)+"</"+this.name.trim()+">";
				else return _separator()+"</"+this.name.trim()+">";	
			}else return _separator()+"</"+this.name.trim()+">";			
		}else return "";
	}catch(Exception e){
		if(SYSATTR.length()==2 && SYSATTR.charAt(0)=='0')
			return _separator()+"</"+this.name.trim()+">";
		else return "";	
	}	
}

public String toXMLString_start() {
	String result = "";
	if(SYSATTR.length()==2 && SYSATTR.charAt(0)=='0'){
		result+=_separator()+"<"+this.name.trim()+" ";
		if(nodeCurrent!=null){
			for (int i=0;i<nodeCurrent.getAttributes().getLength();i++){
				String paramName = nodeCurrent.getAttributes().item(i).getNodeName();
				if(!paramName.equals("SYSATTR")){
					try{
						Object res = new Parser_Java(this.name,this.motore,false).getValue(this,"get"+paramName);
						if(res!=null)
							result+=paramName.trim()+"=\""+verificationATTRIBUTE((String)res)+"\" ";
					}catch(Exception e){}
				}	
			}
			result+=">";
		}

	}	
	return result;
}

public String verification(String input) {
	if (input==null || motore==null || motore.getSaveAs()==null) return input;
	if(	input.indexOf("&")>-1 ||
		input.indexOf("\\")>-1 ||
		input.indexOf(">")>-1 ||
		input.indexOf("<")>-1 ||
		input.indexOf("\"")>-1){
		 
		try{
			return util_xml_ibm.normalFE(input,"");
		}catch(Exception e){
			return input;
		}
	}else return input;
}
public String verificationATTRIBUTE(String input) {
	if (input==null || motore==null || motore.getSaveAs()==null) return input;
	String result="";
	if(	input.indexOf("&")>-1 ||
		input.indexOf("\\")>-1 ||
		input.indexOf(">")>-1 ||
		input.indexOf("<")>-1 ||
		input.indexOf("\"")>-1){ 
	
		for(int i=0;i<input.length();i++){
			if (input.charAt(i)=='&') result+="&amp;";
			else if (input.charAt(i)=='\'') result+="&apos;";
			else if (input.charAt(i)=='>') result+="&gt;";
			else if (input.charAt(i)=='<') result+="&lt;";
			else if (input.charAt(i)=='"') result+="&quot;";
			else result+=input.charAt(i);
		
		}
		return result;
	}else return input;
}
public String _separator() {
	String sep = System.getProperty("line.separator");
	if(sep==null || sep.equals("")) return "\r\n";
	else return sep;
}

public Object init_element(Node node, report_element_base element_parent, 
		Hashtable<String, report_element_base> _tagLibrary,
		Hashtable<String, report_element_base> _beanLibrary,
		Hashtable<String, report_element_base> _styleLibrary) {
	if(node!=null) nodeCurrent = node; 
	Object result = this;
	String styleID = "";
	if(internal_style==null){
		internal_style = new style();
	}
	try{
		NamedNodeMap nnm = node.getAttributes();	 		
		if (nnm!=null){
			for (int i=0;i<node.getAttributes().getLength();i++){
				String paramName = node.getAttributes().item(i).getNodeName();
				boolean isStyled = false;
				if(!this.name.equalsIgnoreCase("STYLE") && style.PROPERTYS.indexOf(";"+paramName.toUpperCase()+";")>-1) isStyled = true; 
				Node node_nnm =	nnm.getNamedItem(paramName);
				if (node_nnm!=null){
					Object prm[] = new Object[1];
					prm[0] = new Parser_Java(this.name,this.motore).checkAttribute(node_nnm.getNodeValue(), _tagLibrary, _beanLibrary);
					if(isStyled) {
						if(!internal_style.setValue("set"+paramName.toUpperCase(),prm))
							unsupported_attributes.put(paramName.toUpperCase(),(prm[0]==null)?null:prm[0].toString());
					}
					else {
						if(!setValue("set"+paramName.toUpperCase(),prm))
							unsupported_attributes.put(paramName.toUpperCase(),(prm[0]==null)?null:prm[0].toString());
					}
				}
				else {
					Object prm[] = new Object[1];
					prm[0] = "";					
					if(isStyled) {
						if(!internal_style.setValue("set"+paramName.toUpperCase(),prm))
							unsupported_attributes.put(paramName.toUpperCase(),"");
					}
					else {
						if(!setValue("set"+paramName.toUpperCase(),prm))
							unsupported_attributes.put(paramName.toUpperCase(),"");
					}
				}
				if(paramName.equalsIgnoreCase("STYLE_ID")){
					Node node_nnm0 =	nnm.getNamedItem(paramName);
					if (node_nnm0!=null){
						try{
							String paramValue = node_nnm0.getNodeValue(); 
							styleID = new Parser_Java(this.name,this.motore).checkAttribute(paramValue, _tagLibrary, _beanLibrary);
						}catch(Exception e){
							setError(e,iStub.log_INFO);
						}
					}
				}
			}
		}

		if (getID()==null || getID().trim().length()==0) setID(String.valueOf(new java.util.Date().getTime()));
		parent = element_parent;
		if(styleID.length()>0 && _styleLibrary!=null){
			style external_style = (style)_styleLibrary.get("STYLE:"+styleID+"_ids_"+motore.hashCode());
			reStyle(external_style);
		}else{
			if(parent!=null){
				reStyle(((report_element_base)parent).getStyle());
			}
		}
		try{
			if(refreshText()){
				try{
					if(((Text)node.getFirstChild()).getData()!=null)
						setContent(new Parser_Java(this.name,this.motore).checkAttribute(((Text)node.getFirstChild()).getData(), _tagLibrary, _beanLibrary));
				}catch(Exception exText){}
			}
		}catch(Exception e){
		}			
	}catch(Exception e){
		setError(e,iStub.log_WARN); 
		result = null;
	}
	return result;
}

public void reStyle(style style) {}
public void setStyle(style newStyle) {
	internal_style = newStyle;
}
public style getStyle() {
	return internal_style;
}

public static String replace (String target, String from, String to) {   
	int start = target.indexOf (from);
	if (start==-1) return target;
	int lf = from.length();
	char [] targetChars = target.toCharArray();
	StringBuffer buffer = new StringBuffer();
	int copyFrom=0;
	while (start != -1) {
		buffer.append (targetChars, copyFrom, start-copyFrom);
		buffer.append (to);
		copyFrom=start+lf;
		start = target.indexOf (from, copyFrom);
	}
	buffer.append (targetChars, copyFrom, targetChars.length-copyFrom);
	return buffer.toString();
}
public void add(report_element_base child) {}
public String getPARSER_JAVA() {
	return PARSER_JAVA;
}
public void setPARSER_JAVA(String pARSERJAVA) {
	PARSER_JAVA = pARSERJAVA;
}
public HashMap<String, String> getUnsupported_attributes() {
	return unsupported_attributes;
}
}

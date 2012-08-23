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

import java.util.Hashtable;

import neohort.log.stubs.iStub;

public class object_java extends report_element_base{
	private static final long serialVersionUID = 3535062471126131192L;
	private java.lang.String TYPE;
	private java.lang.String CLASS;
	private java.lang.String SOURCE;
	private java.lang.String METHOD;
public object_java() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		Object result = null; 
		if(this.getSOURCE().length()>0){
			report_element_base bean_lib = (report_element_base)_beanLibrary.get("BEAN:"+this.getSOURCE()+"_ids_"+motore.hashCode());
			if(bean_lib==null) bean_lib = (report_element_base)_beanLibrary.get("BEAN:"+this.getSOURCE());
			result = executeBean(bean_lib.getContent(),this.getMETHOD(),_beanLibrary,0); 
		}else{			 
			if(this.getMETHOD().length()>0)
				result = executeBean(result,this.getMETHOD(),_beanLibrary,0); 
			else result = executeBean(result,this.getTYPE()+"()",_beanLibrary,0);	
		}
		setContent(result);	
		_beanLibrary.put("BEAN:"+this.getID()+"_ids_"+motore.hashCode(),this);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
	
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());		

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
	
}
public java.lang.String getCLASS() {
	if (CLASS!=null) return CLASS;
	else return "";
}
public java.lang.String getMETHOD() {
	if(METHOD!=null) return METHOD;
	else return "";
}
public java.lang.String getSOURCE() {
	if (SOURCE!=null) return SOURCE;
	else return "";
}
public java.lang.String getTYPE() {
	if (TYPE!=null) return TYPE.trim();
	else return "";
}
public void reimposta() {
	setName("OBJECT_JAVA");
	TYPE = "";
	CLASS = "";
	SOURCE = "";
	METHOD = "";	
}
public void setCLASS(java.lang.String newCLASS) {
	CLASS = newCLASS;
}
public void setMETHOD(java.lang.String newMETHOD) {
	METHOD = newMETHOD;
}
public void setSOURCE(java.lang.String newSCOPE) {
	SOURCE = newSCOPE;
}
public void setTYPE(java.lang.String newTYPE) {
	TYPE = newTYPE.trim();
}
}

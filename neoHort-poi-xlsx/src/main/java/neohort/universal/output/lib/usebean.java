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

public class usebean extends report_element_base{
	private static final long serialVersionUID = -4863264758730383381L;
	private java.lang.String METHOD;
	private java.lang.String PARAMETERS;
	private java.lang.String SET;
public usebean() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		report_element_base bean_lib = (report_element_base)_beanLibrary.get("BEAN:"+this.getID()+"_ids_"+motore.hashCode());
		if(bean_lib==null) bean_lib = (report_element_base)_beanLibrary.get("BEAN:"+this.getID());
		report_element_base bean_set = null; 
		try{
			bean_set = (report_element_base)_beanLibrary.get("BEAN:"+this.getSET()+"_ids_"+motore.hashCode());
			if(bean_set==null) bean_set = (report_element_base)_beanLibrary.get("BEAN:"+this.getSET());
		}catch(Exception e){}
		String _method = "";
		if(!METHOD.equals("")) _method+=METHOD;
		boolean ver_method = 	(METHOD.indexOf("(") > -1 &&
            					 METHOD.indexOf(")") > -1 &&
            					 METHOD.indexOf(")") > METHOD.indexOf("("));
		if(!PARAMETERS.equals("") && !ver_method) _method+="("+PARAMETERS+")";
		if(!PARAMETERS.equals("") && ver_method && _method.length()>0) _method=_method.substring(0,_method.length()-1)+";"+PARAMETERS+")";
		if(_method.equals("")) return;	
		Object resultObject = executeBean(bean_lib.getContent(),_method,_beanLibrary,0);
		if(resultObject!=null && bean_set!=null) bean_set.setContent(resultObject);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}	
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public java.lang.String getMETHOD() {
	if(METHOD!=null) return METHOD;
	else return "";
}
public java.lang.String getPARAMETERS() {
	return PARAMETERS;
}
public java.lang.String getSET() {
	return SET;
}
public void reimposta() {
	setName("USEBEAN");
	METHOD = "";
	PARAMETERS = "";
	SET = "";	
}
public void setMETHOD(java.lang.String newMETHOD) {
	METHOD = newMETHOD;
}
public void setPARAMETERS(java.lang.String newPARAMETERS) {
	PARAMETERS = newPARAMETERS;
}
public void setSET(java.lang.String newSET) {
	SET = newSET;
}
}

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

import org.w3c.dom.Node;

public class general extends report_element_base{
	private static final long serialVersionUID = 14828475440818093L;


	Hashtable _beanLibrary;
	private String LIB;
	private String TYPE_DOCUMENT;

public general() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public java.lang.String getLIB() {
	return LIB;
}
public void reimposta() {
	setName("GENERAL");
	LIB="";
	TYPE_DOCUMENT="attachment";
}
public Object init_element(Node node, report_element_base element_parent,Hashtable _tagLibrary, Hashtable _beanLibrary, Hashtable _styleLibrary) {
	report_element_base ret_obj = null;
	try{
		ret_obj = (report_element_base)super.init_element(node,element_parent,_tagLibrary,_beanLibrary,_styleLibrary);
		if(LIB.equals("")) LIB="pdf";
		super.motore.setLIB(LIB);
	
		String className = replace(super.motore.getNamePac()+"lib_",".lib_",".lib_"+LIB) +"."+ node.getNodeName().toLowerCase();
		try {
			if (!className.equals("")) {
				ret_obj = (report_element_base)Class.forName(className).newInstance();
				ret_obj = (report_element_base)ret_obj.init_element(node,element_parent,_tagLibrary,_beanLibrary,_styleLibrary);
			}else ret_obj=null;
		} catch (Exception e) {
			ret_obj=null;
		}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
		ret_obj=null;
	}
	return ret_obj;
}

public void setLIB(java.lang.String newLIB) {
	if(newLIB!=null) LIB = newLIB.toLowerCase();
}

public String getTYPE_DOCUMENT() {
	return TYPE_DOCUMENT;
}
public void setTYPE_DOCUMENT(String type_document) {
	if(type_document!=null && !type_document.trim().equals(""))
		TYPE_DOCUMENT = type_document;
}
}

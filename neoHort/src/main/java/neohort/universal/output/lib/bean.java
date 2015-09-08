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

public class bean extends report_element_base{
	private static final long serialVersionUID = -6608491638333795248L;
	private java.lang.String TYPE = "";
	private java.lang.String CLASS = "";
	private java.lang.String SCOPE = "";
public bean() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
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
public java.lang.String getSCOPE() {
	if (SCOPE!=null) return SCOPE;
	else return "";
}
public java.lang.String getTYPE() {
	if (TYPE!=null) return TYPE.trim();
	else return "";
}
public void reimposta() {
	setName("BEAN");
	TYPE = "";
	CLASS = "";
	SCOPE = "";
}
public void setCLASS(java.lang.String newCLASS) {
	CLASS = newCLASS;
}
public void setSCOPE(java.lang.String newSCOPE) {
	SCOPE = newSCOPE;
}
public void setTYPE(java.lang.String newTYPE) {
	TYPE = newTYPE.trim();
}
}

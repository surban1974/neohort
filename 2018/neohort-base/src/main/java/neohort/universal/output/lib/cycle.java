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

public class cycle extends report_element_base{
	private static final long serialVersionUID = 6383305123356231225L;
	private java.lang.String CONDITION_ID;
	private report_element_base condition;
	private java.lang.String CONDITION_TYPE;
public cycle() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if (condition==null)
			condition = (report_element_base)_tagLibrary.get(getCONDITION_TYPE()+":"+getCONDITION_ID()+"_ids_"+motore.hashCode());
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	if (condition!=null) condition.executeLast(_tagLibrary, _beanLibrary);
	if(_tagLibrary.get(getName()+":"+getID())==null)
		_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
	else _tagLibrary.remove(getName()+":"+getID());		

}
public java.lang.String getCONDITION_ID() {
	return CONDITION_ID;
}
public java.lang.String getCONDITION_TYPE() {
	return CONDITION_TYPE;
}
public boolean isActive() {
	if (condition==null) return false;
	else return condition.isActiveCycle();
}
public boolean isPreActive() {
	if (condition==null) return true;
	else return condition.isPreActiveCycle();
}
public void reimposta() {
	setName("CYCLE");
	CONDITION_ID = "";
	CONDITION_TYPE = "";
}
public String RETURN(String nome){ 
	if (condition==null) return "";
	else return condition.RETURN(nome);
}
public void setCONDITION_ID(java.lang.String newCONDITION_ID) {
	CONDITION_ID = newCONDITION_ID;
}
public void setCONDITION_TYPE(java.lang.String newCONDITION_TYPE) {
	CONDITION_TYPE = newCONDITION_TYPE;
}
}

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

package neohort.universal.output.lib_pdf;

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.lib.style;

public class table extends element{
	private static final long serialVersionUID = 3741454443562801743L;
	private java.lang.String COL;
	private java.lang.String ROW;
	private java.lang.String COLLS_DIMENTION;

public table() {
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
public java.lang.String getCOL() {
	return COL;
}
public java.lang.String getCOLLS_DIMENTION() {
	return COLLS_DIMENTION;
}
public java.lang.String getROW() {
	return ROW;
}
public void reimposta() {
	setName("TABLE");
	STYLE_ID = "";
	COL = "";
	ROW = "";
	COLLS_DIMENTION = "";
}
public void setCOL(java.lang.String newCOL) {
	COL = newCOL;
}
public void setCOLLS_DIMENTION(java.lang.String newCOLLS_DIMENTION) {
	COLLS_DIMENTION = newCOLLS_DIMENTION;
}
public void setROW(java.lang.String newROW) {
	ROW = newROW;
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}

}

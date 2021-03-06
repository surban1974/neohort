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

package neohort.universal.output.lib_html_xls;

import java.io.DataOutputStream;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;

public class pagebreak extends element{
	private static final long serialVersionUID = -5187428462148694902L;

	private java.lang.String STYLE_ID;

	private int pagebreak_row;
public pagebreak() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		this._header = "<TR STYLE='page-break-before:always'>"+_separator();
		this._footer = "</TR>"+_separator();
		table table = new table();
		try{
			report_element_base el = this;
			while(el.getParent()!=null && !el.getName().equalsIgnoreCase("TABLE"))
				el = (report_element_base)el.getParent();
			table = (table)el;
		}catch(Exception e){
			table = null;
		}
		if (table!=null){
			pagebreak_row = table.getCurrentRow();
			table.nextCurrentRow();
		}
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageBreak)).getContent())).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public int getPagebreak_row() {
	return pagebreak_row;
}
public java.lang.String getSTYLE_ID() {
	return STYLE_ID;
}
public void reimposta() {
	setName("PAGEBREAK");
	getParameters().addElement("STYLE_ID");
	STYLE_ID= "";
}
public void setSTYLE_ID(java.lang.String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}
}

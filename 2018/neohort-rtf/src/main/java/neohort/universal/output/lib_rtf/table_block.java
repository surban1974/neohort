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

package neohort.universal.output.lib_rtf;

import java.util.Hashtable;


import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;



public class table_block extends element{
	private static final long serialVersionUID = -1L;
public table_block() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		try{
			_sys_border = Integer.valueOf(internal_style.getBORDER()).intValue();
		}catch(Exception e){}
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta() {
	setName("TABLE_BLOCK");
	STYLE_ID = "";
}
public void setCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	try{
		bean _sysPdfTableBlock = new bean();
		_sysPdfTableBlock.setName("SYSTEM");
		_sysPdfTableBlock.setID(iConst.iHORT_SYSTEM_TableBlock);

		_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(_sysPdfTableBlock);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}

}

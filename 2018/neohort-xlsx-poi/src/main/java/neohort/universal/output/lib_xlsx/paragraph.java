/**
* Creation date: (22/12/2005)
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

package neohort.universal.output.lib_xlsx;

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;

public class paragraph extends element{
	private static final long serialVersionUID = -1L;
	private table_cell parentCell = null;


public paragraph() {
	super();
}

public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{


		report_element_base parentC = (report_element_base)getParent();
		while (parentC!=null && !parentC.getName().equalsIgnoreCase("TABLE_CELL"))
			parentC=(report_element_base)parentC.getParent();
		if(parentC!=null && parentC.getName().equalsIgnoreCase("TABLE_CELL"))
			parentCell = (table_cell)parentC;

		if(parentCell!=null)
			return;


		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);

		int X = 0;
		int Y = 0;
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}


		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);

		_sysPdfCC.setContent(new Integer(X));
		_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){

	try{


		if(parentCell!=null){
			return;
		}


		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);


		int Y = 0;
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}
		Y++;
		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void reimposta() {
	setName("PARAGRAPH");
	STYLE_ID = "";
}
}

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

package neohort.universal.output.lib_xls;

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class table_row extends element{
	private static final long serialVersionUID = -1L;
	private String HEIGHT;
	private int deltaRow;
public table_row() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){

	try{
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);

		int X = 0;
		int Y = 0;

		if(parent!=null && parent instanceof table){
			try{
				X = ((table)parent).getStartX();
			}catch(Exception e){
			}
		}
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}

/*
		try{
			if(!getHEIGHT().equals(""))
				document.setRowView(Y,new Integer(getHEIGHT()).intValue());
		}catch(Exception e){
		}
*/
		if(getParent()!=null && getParent() instanceof table && ((table)getParent()).getParentTable_Block()!=null)
			((table)getParent()).getParentTable_Block().setDeltaRow(((table)getParent()).getParentTable_Block().getDeltaRow()+1);

		if(deltaRow==0)	Y++;
		else Y=Y+deltaRow;


		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);

		_sysPdfCC.setContent(new Integer(X));
		_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta() {
	setName("TABLE_ROW");
	STYLE_ID = "";
	HEIGHT = "";
}
public String getHEIGHT() {
	return HEIGHT;
}

public void setHEIGHT(String string) {
	HEIGHT = string;
}

	public int getDeltaRow() {
		return deltaRow;
	}

	public void setDeltaRow(int i) {
		deltaRow = i;
	}
	public void reStyle(style style_new) {
		if(internal_style==null) return;
		internal_style.reStyle(style_new);
	}

}

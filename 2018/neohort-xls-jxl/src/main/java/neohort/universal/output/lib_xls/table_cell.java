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

import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.*;

public class table_cell extends element{
	private static final long serialVersionUID = -1L;
public table_cell() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);

		int X = 0;
		int Y = 0;
		try{
			X = ((Integer)_sysPdfCC.getContent()).intValue();
		}catch(Exception e){
		}
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}


		WritableSheet document = (WritableSheet)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());


		bean _defDatetimeformat = (bean)_beanLibrary.get("SYSTEM:DEFDATETIMEFORMAT");

		if(_defDatetimeformat!=null && _defDatetimeformat.getContent()!=null) setDefDATETIMEFORMAT((WritableCellFormat)_defDatetimeformat.getContent());
		else{
			_defDatetimeformat = new bean();
			_defDatetimeformat.setContent(new WritableCellFormat (new DateFormat("dd/MM/yyyy hh:mm")));
			_defDatetimeformat.setID("DEFDATETIMEFORMAT");
			_beanLibrary.put("SYSTEM:DEFDATETIMEFORMAT",_defDatetimeformat);
			setDefDATETIMEFORMAT((WritableCellFormat)_defDatetimeformat.getContent());
		}

		bean _defDateformat = (bean)_beanLibrary.get("SYSTEM:DEFDATEFORMAT");
		if(_defDateformat!=null && _defDateformat.getContent()!=null) setDefDATEFORMAT((WritableCellFormat)_defDateformat.getContent());
		else{
			_defDateformat = new bean();
			_defDateformat.setContent(new WritableCellFormat (new DateFormat("dd/MM/yyyy")));
			_defDatetimeformat.setID("DEFDATEFORMAT");
			_beanLibrary.put("SYSTEM:DEFDATEFORMAT",_defDateformat);
			setDefDATEFORMAT((WritableCellFormat)_defDateformat.getContent());
		}

		_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(this.getCellC(document.getWritableCell(X,Y),X,Y,_tagLibrary,_beanLibrary));



		try{
			if(!internal_style.getWIDTH().equals(""))
				document.setColumnView(X,new Integer(internal_style.getWIDTH()).intValue());
		}catch(Exception e){
		}
		if(!internal_style.getHEIGHT().equals("") && parent!=null && parent instanceof table_row)
			((table_row)parent).setHEIGHT(internal_style.getHEIGHT());

		table parentTable = null;
		try{
			parentTable = (table)((table_row)this.getParent()).getParent();
		}catch(Exception e){
		}

		if(	parentTable!=null &&
			parentTable.getParentTable_Block()!=null &&
			parentTable.getParentTable_Block().getDeltaCol()==0)
			parentTable.getParentTable_Block().setDeltaCol(parentTable.getParentTable_Block().getDeltaCol()+1);

		X++;


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

public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("TABLE_CELL");
}
}

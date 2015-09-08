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

public class table_block extends element{
	private static final long serialVersionUID = -1412738670812683414L;
	private int deltaCol;
	private int deltaRow;
	private int startCol;
	private int startRow;
	
public table_block() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
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
		startCol = X;
		startRow = Y;
	}catch(Exception e){	
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{

		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
			_sysPdfCC.setContent(new Integer(startCol+deltaCol));
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);
			_sysPdfCR.setContent(new Integer(startRow));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);
		
		if(getParent()!=null && getParent() instanceof table_row)
			((table_row)getParent()).setDeltaRow(deltaRow);
				
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
	public int getDeltaCol() {
		return deltaCol;
	}

	public int getDeltaRow() {
		return deltaRow;
	}

	public void setDeltaCol(int i) {
		deltaCol = i;
	}

	public void setDeltaRow(int i) {
		deltaRow = i;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int i) {
		startRow = i;
	}

}

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
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.style;

public class table extends element{

	private static final long serialVersionUID = 4421566444036916843L;
	private java.lang.String COL;
	private java.lang.String ROW;
	private java.lang.String COLLS_DIMENTION;
	private int startX=0;
	private table_block parentTable_Block;
public table() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW); 

		report_element parentForINCLUDE = null;
		if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_ParentForINCLUDE+"_ids_"+motore.hashCode())!=null)
			parentForINCLUDE = (report_element)((bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_ParentForINCLUDE+"_ids_"+motore.hashCode())).getContent();

		if(	parent!=null && parent instanceof table_block) parentTable_Block = (table_block)parent;
		else{
			if(parentForINCLUDE!=null && parentForINCLUDE  instanceof table_block) parentTable_Block = (table_block)parentForINCLUDE; 
		}

		int X = 0;
		int Y = 0;

		if(	parentTable_Block!=null){
			try{
				startX = ((Integer)_sysPdfCC.getContent()).intValue();
			}catch(Exception e){
			}
		}else startX=0;

			
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}
		
		X=startX;
		
		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR); 
	
		_sysPdfCC.setContent(new Integer(X));
		_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
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
public int getStartX() {
	return startX;
}

public void setStartX(int i) {
	startX = i;
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public table_block getParentTable_Block() {
	return parentTable_Block;
}

}

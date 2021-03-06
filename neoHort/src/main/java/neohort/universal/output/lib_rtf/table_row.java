/**
* Creation date: (24/01/2006)
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



import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;

import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;

public class table_row extends element{
	private static final long serialVersionUID = -532623496974995818L;
	PdfPRow row;
	Vector cells; 
public table_row() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		cells = new Vector();

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){	
	table parentT = null;
	report_element_base parentC = (report_element_base)getParent();
	while (parentC!=null && !parentC.getName().equalsIgnoreCase("TABLE"))
		parentC=(report_element_base)parentC.getParent();
	parentT = (table)parentC;
	
	int col=parentT.getTable().getNumberOfColumns();

	PdfPCell[] pcells = new PdfPCell[col];
	for(int i=0;i<cells.size();i++){ 
		if(i<col){
			if(cells.get(i) instanceof PdfPCell)
				pcells[i]=(PdfPCell)cells.get(i);
			else 
				pcells[i]=new PdfPCell();
		}
	}
	if(col>cells.size()){
		for(int i=0;i<col-cells.size();i++)
			pcells[i]=new PdfPCell();			
	}
	
	parentT.getTable().getRows().add(new PdfPRow(pcells));

	try{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta() {
	setName("TABLE_ROW");
	STYLE_ID= "";
}
public void setCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(cells);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	} 

}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public PdfPRow getRow() {
	return row;
}
public void setRow(PdfPRow row) {
	this.row = row;
}
public List getCells() {
	return cells;
}
public void setCells(Vector cells) {
	this.cells = cells;
}
}

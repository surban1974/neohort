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
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class goto_destination extends element{
	private static final long serialVersionUID = 8430731815952106984L;
	private java.lang.String ID_REFERENCE;
public goto_destination() {
	super();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{

		int _align = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
		BaseColor _bColor = getField_Color(internal_style.getBACK_COLOR(),BaseColor.WHITE);
		String content=prepareContentString(internal_style.getFORMAT());

		com.itextpdf.text.Font font = getFont();

		Object canvas_prev = null;
		try{
			canvas_prev = ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement();
		}catch(Exception e){}

		if(	canvas_prev!=null &&
			(canvas_prev instanceof com.itextpdf.text.pdf.PdfPCell ||
			canvas_prev instanceof com.itextpdf.text.pdf.PdfPTable)){

			int border = 15;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
			}catch(Exception e){}

			Chunk chunk = new Chunk(content);
			chunk.setLocalDestination(getID_REFERENCE());
			chunk.setFont(font);
			PdfPCell cell = new PdfPCell(new Phrase(chunk));
			cell.setHorizontalAlignment(_align);
			cell.setBorder(border);
			cell.setBackgroundColor(_bColor);
			if(padding!=0) cell.setPadding(padding);
			if(!internal_style.getDIRECTION().equals("") && internal_style.getDIRECTION().equalsIgnoreCase("RTL")){
				if(cell!=null)
					cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}


			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);
		}else{
			Chunk chunk = new Chunk(content);
			chunk.setLocalDestination(getID_REFERENCE());
			chunk.setFont(font);
				((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(chunk);
		}

		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public String getID_REFERENCE() {
	return ID_REFERENCE;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("GOTO_DESTINATION");
	ID_REFERENCE = "";
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setID_REFERENCE(String newLINK) {
	ID_REFERENCE = newLINK;
}
}

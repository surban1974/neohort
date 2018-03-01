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
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;


public class action_gopage extends element{
	private static final long serialVersionUID = -1L;
	private java.lang.String ST_PAGE;
	private java.lang.String N_PAGE;
public action_gopage() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{

		int _align = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
		float padding = 0;
		try{
			padding = Float.valueOf(internal_style.getPADDING()).floatValue();
		}catch(Exception e){}

		BaseColor _bColor = getField_Color(internal_style.getBACK_COLOR(),BaseColor.WHITE);
		String content=prepareContentString(internal_style.getFORMAT());

		com.itextpdf.text.Font font = getFont();



		Object canvas_prev = null;
		try{
			canvas_prev = _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).getContentLastElement();
		}catch(Exception e){}

		PdfAction anAction = null;
		if(getST_PAGE().equals("")){
			PdfWriter writer = (PdfWriter)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent();
			int n_page = 0;
			try{
				n_page = Integer.valueOf(getN_PAGE()).intValue();
			}catch(Exception e){}
			anAction = PdfAction.gotoLocalPage(n_page, new PdfDestination(n_page),writer);
			writer.setOpenAction(anAction);
		}else{
			int st_page = getField_Int(new com.itextpdf.text.pdf.PdfAction(0).getClass(),getST_PAGE(),com.itextpdf.text.pdf.PdfAction.NEXTPAGE);
			anAction = new PdfAction(st_page);
		}

		Chunk chunk = new Chunk(content);
		chunk.setAction(anAction);
		chunk.setFont(font);

		if(	canvas_prev!=null &&
			(canvas_prev instanceof com.itextpdf.text.pdf.PdfPCell ||
			canvas_prev instanceof com.itextpdf.text.pdf.PdfPTable)){

			int border = 15;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}


			PdfPCell cell = new PdfPCell(new Phrase(chunk));
			cell.setHorizontalAlignment(_align);
			cell.setBorder(border);
			cell.setBackgroundColor(_bColor);
			if(padding!=0) cell.setPadding(padding);
			if(!internal_style.getDIRECTION().equals("") && internal_style.getDIRECTION().equalsIgnoreCase("RTL")){
				if(cell!=null)
					cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}

			_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(cell);
		}else
			_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(chunk);
		

		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public java.lang.String getN_PAGE() {
	return N_PAGE;
}
public java.lang.String getST_PAGE() {
	return ST_PAGE;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("ACTION_GOPAGE");
	ST_PAGE = "";
	N_PAGE = "";
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setN_PAGE(java.lang.String newN_PAGE) {
	N_PAGE = newN_PAGE;
}
public void setST_PAGE(java.lang.String newST_PAGE) {
	ST_PAGE = newST_PAGE;
}
}

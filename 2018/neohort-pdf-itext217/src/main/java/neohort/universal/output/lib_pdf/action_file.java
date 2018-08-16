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

import java.awt.Color;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;


import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

public class action_file extends element{
	private static final long serialVersionUID = -1L;
	private java.lang.String TYPE;
	private java.lang.String PARAMETER1;
	private java.lang.String PARAMETER2;
public action_file() {
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
		int _f_size = 10;
			try{
				_f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
			}catch(Exception e){}
		int _f_name = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT(),com.lowagie.text.Font.COURIER);
		int _f_type = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT_TYPE(),com.lowagie.text.Font.NORMAL);
		float padding = 0;
		try{
			padding = Float.valueOf(internal_style.getPADDING()).floatValue();
		}catch(Exception e){}

		Color _fColor =getField_Color(internal_style.getFONT_COLOR(),Color.black);
		Color _bColor = getField_Color(internal_style.getBACK_COLOR(),Color.white);
		String content=prepareContentString(internal_style.getFORMAT());

		com.lowagie.text.Font font = new com.lowagie.text.Font(_f_name, _f_size, _f_type);
		font.setColor(_fColor);
		if(getStyle()!=null && !getStyle().getFONT_STYLE().equals(""))
			font.setStyle(getStyle().getFONT_STYLE().toLowerCase());


		Object canvas_prev = null;
		try{
			canvas_prev = _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).getContentLastElement();
		}catch(Exception e){}

		PdfAction anAction = null;
		if(getTYPE().equalsIgnoreCase("FILE")){
			int n_page = 0;
			try{
				n_page = Integer.valueOf(getPARAMETER2()).intValue();
			}catch(Exception e){}
			anAction = new PdfAction(getPARAMETER1(),n_page);
		}else{
			anAction = new PdfAction(getPARAMETER1());
		}
		Chunk chunk = new Chunk(content);
		chunk.setAction(anAction);
		chunk.setFont(font);

		if(	canvas_prev!=null &&
			(canvas_prev instanceof com.lowagie.text.pdf.PdfPCell ||
			canvas_prev instanceof com.lowagie.text.pdf.PdfPTable)){

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
public java.lang.String getPARAMETER1() {
	return PARAMETER1;
}
public java.lang.String getPARAMETER2() {
	return PARAMETER2;
}
public java.lang.String getTYPE() {
	return TYPE;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("ACTION_FILE");
	TYPE = "";
	PARAMETER1 = "";
	PARAMETER2 = "";
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setPARAMETER1(java.lang.String newPARAMETER1) {
	PARAMETER1 = newPARAMETER1;
}
public void setPARAMETER2(java.lang.String newPARAMETER2) {
	PARAMETER2 = newPARAMETER2;
}
public void setTYPE(java.lang.String newTYPE) {
	TYPE = newTYPE;
}
}

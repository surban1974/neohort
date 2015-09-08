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

import java.awt.Color;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

public class goto_ extends element{
	private static final long serialVersionUID = 7510114779261477980L;
	private java.lang.String REFERENCE;
public goto_() {
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
		int _f_size = 10;
		try{
			_f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
		}catch(Exception e){}
		int _f_name = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT(),com.lowagie.text.Font.COURIER);
		int _f_type = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT_TYPE(),com.lowagie.text.Font.NORMAL);
		Color _fColor =getField_Color(new Color(0).getClass(),internal_style.getFONT_COLOR(),Color.black);
		Color _bColor = getField_Color(new Color(0).getClass(),internal_style.getBACK_COLOR(),Color.white);
		String content=prepareContentString(internal_style.getFORMAT());

		com.lowagie.text.Font font = new com.lowagie.text.Font(_f_name, _f_size, _f_type);
		font.setColor(_fColor);
		if(getStyle()!=null && !getStyle().getFONT_STYLE().equals(""))
			font.setStyle(getStyle().getFONT_STYLE().toLowerCase());

		Object canvas_prev = null;
		try{
			canvas_prev = ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement();
		}catch(Exception e){}

		if(	canvas_prev!=null &&
			(canvas_prev instanceof com.lowagie.text.pdf.PdfPCell ||
			canvas_prev instanceof com.lowagie.text.pdf.PdfPTable)){

			int border = 15;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
			}catch(Exception e){}

			Chunk chunk = new Chunk(content);
			chunk.setLocalGoto(getREFERENCE());
			chunk.setFont(font);
			PdfPCell cell = new PdfPCell(new Phrase(chunk));
			cell.setHorizontalAlignment(_align);
			cell.setBorder(border);
			cell.setBackgroundColor(_bColor);
			if(padding!=0) cell.setPadding(padding);

			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);
		}else{
			Chunk chunk = new Chunk(content);
			chunk.setLocalGoto(getREFERENCE());
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
public String getREFERENCE() {
	return REFERENCE;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("GOTO_");
	REFERENCE = "";
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setREFERENCE(String newLINK) {
	REFERENCE = newLINK;
}
}

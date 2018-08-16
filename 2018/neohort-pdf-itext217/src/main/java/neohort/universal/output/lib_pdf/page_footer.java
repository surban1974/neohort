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

import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

public class page_footer extends element{
	private static final long serialVersionUID = -1L;
	private String PAGE_N;
public page_footer() {
	super();
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
		Color _fColor =getField_Color(internal_style.getFONT_COLOR(),Color.black);
		String content=(String)this.getContent();

		if(_f_size == 0) _f_size = 8;
		com.lowagie.text.Font font = new com.lowagie.text.Font(_f_type, _f_size, _f_name);
		font.setColor(_fColor);
		if(getStyle()!=null && !getStyle().getFONT_STYLE().equals(""))
			font.setStyle(getStyle().getFONT_STYLE().toLowerCase());

		HeaderFooter footer;

		int _f_leading = -1;
		try{
			_f_leading = Integer.valueOf(internal_style.getLEADING()).intValue();
		}catch(Exception e){}

		if(getPAGE_N().length()==0)
			if(_f_leading==-1) footer = new HeaderFooter(new Phrase(content,font), false);
			else footer = new HeaderFooter(new Phrase(_f_leading,content,font), false);
		else
			if(_f_leading==-1) footer = new HeaderFooter(new Phrase(content,font), true);
			else footer = new HeaderFooter(new Phrase(_f_leading,content,font), true);

		footer.setAlignment(_align);
		footer.setBorder(0);
		((Document)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent()).setFooter(footer);

		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public String getPAGE_N() {
	return PAGE_N;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("PAGE_FOOTER");
	PAGE_N = "";
	STYLE_ID = "";
}
public void setPAGE_N(String newPAGE_N) {
	PAGE_N = newPAGE_N;
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
}

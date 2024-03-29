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

import com.lowagie.text.Phrase;

public class phrase extends element{
	private static final long serialVersionUID = -1L;
public phrase() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{

		int _f_size = 10;
			try{
				_f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
			}catch(Exception e){}

		int _f_name = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT(),com.lowagie.text.Font.COURIER);
		int _f_type = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT_TYPE(),com.lowagie.text.Font.NORMAL);
		Color _fColor =getField_Color(internal_style.getFONT_COLOR(),Color.black);

//		String content=(String)this.getContent();

		String content=prepareContentString(internal_style.getFORMAT()); if(content==null) content="";

		com.lowagie.text.Font font = new com.lowagie.text.Font(_f_name, _f_size, _f_type);
		font.setColor(_fColor);
		if(getStyle()!=null && !getStyle().getFONT_STYLE().equals(""))
			font.setStyle(getStyle().getFONT_STYLE().toLowerCase());

		Phrase phrase = null;
		int _f_leading = -1;
		try{
			_f_leading = Integer.valueOf(internal_style.getLEADING()).intValue();
		}catch(Exception e){}
		if(_f_leading==-1) phrase = new Phrase(content,font);
		else phrase = new Phrase(_f_leading,content,font);

		_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(phrase);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("PHRASE");
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
}

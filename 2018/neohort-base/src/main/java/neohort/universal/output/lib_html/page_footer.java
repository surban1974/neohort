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

package neohort.universal.output.lib_html;

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;

public class page_footer extends element{
	private static final long serialVersionUID = -1L;
	private java.lang.String FONT;
	private java.lang.String FONT_SIZE;
	private java.lang.String FONT_TYPE;
	private java.lang.String ALIGN;
	private java.lang.String STYLE_ID;

public page_footer() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(!getALIGN().equals(""))
			this._header+="&"+getALIGN().charAt(0);
		else this._header+="&C";
		if(!getFONT().equals(""))
			this._header+="&\\0022"+getFONT()+"\\";
		else this._header+="&\\0022Arial\\";
		if(!getFONT_TYPE().equals("")){
			if(getFONT_TYPE().equals("BOLDITALIC"))
				this._header+=",BOLD ITALIC\\";
			else this._header+=","+getFONT_TYPE()+"\\";
		}else this._header+=",Normal\\";
		if(!getFONT_SIZE().equals(""))
			this._header+="0022&"+getFONT_SIZE();
		else this._header+="0022&10";

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		this._content = (String)getContent();
		bean _sysPdfPageHeader = new bean();
				_sysPdfPageHeader.setContent(this);
				_sysPdfPageHeader.setName("SYSTEM");
				_sysPdfPageHeader.setID(iConst.iHORT_SYSTEM_PageFooter);
				_beanLibrary.put(_sysPdfPageHeader.getName()+":"+_sysPdfPageHeader.getID(),_sysPdfPageHeader);
		_tagLibrary.remove(getName()+":"+getID());


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public java.lang.String getALIGN() {
	return ALIGN;
}
public java.lang.String getFONT() {
	return FONT;
}
public java.lang.String getFONT_SIZE() {
	return FONT_SIZE;
}
public java.lang.String getFONT_TYPE() {
	return FONT_TYPE;
}
public java.lang.String getSTYLE_ID() {
	return STYLE_ID;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("PAGE_FOOTER");
	FONT = "";
	FONT_SIZE = "";
	FONT_TYPE = "";
	STYLE_ID = "";
	ALIGN = "";
}
public void setALIGN(java.lang.String newALIGN) {
	ALIGN = newALIGN;
}
public void setFONT(java.lang.String newFONT) {
	FONT = newFONT;
}
public void setFONT_SIZE(java.lang.String newFONT_SIZE) {
	FONT_SIZE = newFONT_SIZE;
}
public void setFONT_TYPE(java.lang.String newFONT_TYPE) {
	FONT_TYPE = newFONT_TYPE;
}
public void setSTYLE_ID(java.lang.String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}
}

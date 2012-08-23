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
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;


public class page_header_ extends element{
	private static final long serialVersionUID = -5661760756007798635L;
	boolean draw = true;
public page_header_() {
	super();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	if(draw)
		initCanvas(_tagLibrary,_beanLibrary);
	else
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).remove(((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:Canvas")).getContent())).lastElement());
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
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
public void reimposta() {
	setName("PAGE_HEADER_");
	STYLE_ID = "";
}
public void setCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
		try{
			bean _sysPdfPageHeader = ((bean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_))));
			_sysPdfPageHeader.setContent(new Vector());
			draw = false;
			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(_sysPdfPageHeader);
		}catch(Exception ex){
			try{
				bean _sysPdfPageHeader = new bean();
					_sysPdfPageHeader.setContent(new Vector());
					_sysPdfPageHeader.setName("SYSTEM");
					_sysPdfPageHeader.setID(iConst.iHORT_SYSTEM_PageHeader_);
					_beanLibrary.put(_sysPdfPageHeader.getName()+":"+_sysPdfPageHeader.getID(),_sysPdfPageHeader);
				((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(_sysPdfPageHeader);
			}catch(Exception e){
				setError(e,iStub.log_ERROR);
			}
			draw = true;
		}
}
}

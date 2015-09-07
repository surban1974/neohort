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

public class page_header extends element{
	private static final long serialVersionUID = 6487886982010388276L;
	private String STYLE_ID;
	private int header_rows_start;
	private int header_rows_finish;
public page_header() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		header_rows_start = ((table)this.getParent()).getCurrentRow();
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
	
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		header_rows_finish = ((table)this.getParent()).getCurrentRow();
		bean _sysPdfPageHeader = new bean();
				_sysPdfPageHeader.setContent(this);
				_sysPdfPageHeader.setName("SYSTEM");
				_sysPdfPageHeader.setID(iConst.iHORT_SYSTEM_PageHeader);
				_beanLibrary.put(_sysPdfPageHeader.getName()+":"+_sysPdfPageHeader.getID(),_sysPdfPageHeader);	
				if(_tagLibrary.get(getName()+":"+getID())==null)
					_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
				else _tagLibrary.remove(getName()+":"+getID());		

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public int getHeader_rows_finish() {
	return header_rows_finish;
}
public int getHeader_rows_start() {
	return header_rows_start+1;
}
public String getSTYLE_ID() {
	return STYLE_ID;
}
public void reimposta() {
	setName("PAGE_HEADER");
	getParameters().addElement("STYLE_ID");
	STYLE_ID = "";
}
public void setSTYLE_ID(String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}
}

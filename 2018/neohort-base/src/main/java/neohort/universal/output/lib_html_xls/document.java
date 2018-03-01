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

package neohort.universal.output.lib_html_xls;

import java.io.DataOutputStream;
import java.util.Hashtable;
import java.util.List;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.*;

public class document extends element{
	private static final long serialVersionUID = -1L;
	document document;
public document() {
	super();
}
public void add(element child) {
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
			Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
			if(included!=null && included.booleanValue()==true){}
			else{
				document = (document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
				document._header="<FORM>"+_separator();
				document._footer="</FORM>"+_separator();

				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(document._header);
			}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{
			document = (document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
			List<Object> vector = _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).getContentAsList();
			if(!vector.isEmpty())
				vector.remove(vector.size()-1);
			if(_tagLibrary.get(getName()+":"+getID())==null)
				_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
			else _tagLibrary.remove(getName()+":"+getID());

			((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(document._content+document._comment+document._footer);
		}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}

}
public void reimposta() {
	setName("DOCUMENT");
	STYLE_ID = "";
}
public void setCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else
			_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(document);
		
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
}

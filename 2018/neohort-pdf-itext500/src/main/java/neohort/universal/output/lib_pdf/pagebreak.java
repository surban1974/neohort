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

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

public class pagebreak extends element{
	private static final long serialVersionUID = -8931736222173377614L;
	private java.lang.String ORIENTATION;
	private java.lang.String MARGINS;

public pagebreak() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	
	try{
		Document document = ((Document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent()));
		if(getORIENTATION()!=null && getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
			document.setPageSize(PageSize.A4.rotate());
		if(getORIENTATION()!=null && getORIENTATION().trim().equalsIgnoreCase("PORTRAIT"))
			document.setPageSize(PageSize.A4);
		if(getMARGINS()!=null && !getMARGINS().equals("")){
			try{
				int p1 = 30;
				int p2 = 30;
				int p3 = 30;
				int p4 = 30;
				java.util.StringTokenizer st = new java.util.StringTokenizer(getMARGINS(), ",");
					p1 = Integer.valueOf(st.nextToken()).intValue();
					p2 = Integer.valueOf(st.nextToken()).intValue();
					p3 = Integer.valueOf(st.nextToken()).intValue();
					p4 = Integer.valueOf(st.nextToken()).intValue();
				document.setMargins(p1, p2, p3, p4);
			}catch(Exception e){
				document.setMargins(30, 30, 30, 30);
			}
		}
		
		document.newPage();
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

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
	setName("PAGEBREAK");
	ORIENTATION = "";
	MARGINS = "";
}
public java.lang.String getORIENTATION() {
	return ORIENTATION;
}
public void setORIENTATION(java.lang.String string) {
	ORIENTATION = string;
}
public java.lang.String getMARGINS() {
	return MARGINS;
}
public void setMARGINS(java.lang.String string) {
	MARGINS = string;
}

}

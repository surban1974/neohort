/**
* Creation date: (22/12/2005)
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

package neohort.universal.output.lib_xls;

import java.net.URL;
import java.util.Hashtable;
import jxl.write.WritableHyperlink;


import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.*;

public class link extends element{
	private static final long serialVersionUID = -1L;
	private String LINK;

public link() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
		initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);

		int X = 0;
		int Y = 0;
		try{
			X = ((Integer)_sysPdfCC.getContent()).intValue();
		}catch(Exception e){
		}
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}




			double width=X;
			try{
				width = new Double(internal_style.getWIDTH()).doubleValue();
			}catch(Exception e){
			}
			double height=Y;
			try{
				height = new Double(internal_style.getHEIGHT()).doubleValue();
			}catch(Exception e){
			}

			WritableHyperlink hyper = new WritableHyperlink(X,Y,(int)width,(int)height,new URL(getLINK()));

			_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(hyper);
			X++;


			_sysPdfCR.setContent(new Integer(Y));
			_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);

			_sysPdfCC.setContent(new Integer(X));
			_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}

public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("IMAGE");
	STYLE_ID="";
	LINK="";
}

public String getLINK() {
	return LINK;
}

public void setLINK(String string) {
	LINK = string;
}

}

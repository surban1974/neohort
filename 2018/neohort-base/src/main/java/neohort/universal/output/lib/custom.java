/**
* Creation date: (14/04/2017)
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

package neohort.universal.output.lib;

import java.util.Hashtable;

import neohort.log.stubs.iStub;

public class custom extends report_element_base{
	private static final long serialVersionUID = -1L;
	private java.lang.String PROVIDER;
	private report_element delegate;
public custom() {
	super();	
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	if(delegate!=null)
		delegate.executeFirst(_tagLibrary, _beanLibrary);
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	if(delegate!=null)
		delegate.executeLast(_tagLibrary, _beanLibrary);
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	if(delegate!=null)
		delegate.drawCanvas(_tagLibrary, _beanLibrary);
}
public void reimposta() {
	setName("CUSTOM");
	STYLE_ID = "";
	PROVIDER = "";
}
public java.lang.String getPROVIDER() {
	return PROVIDER;
}
public void setPROVIDER(java.lang.String pROVIDER) {
	PROVIDER = pROVIDER;
	if(PROVIDER!=null && PROVIDER.trim().length()>0){
		try{
			delegate = Class.forName(PROVIDER).asSubclass(report_element.class).newInstance();
			delegate.reimposta();
		}catch(Exception e){
			setError(e, iStub.log_ERROR);
		}
	}
}


}

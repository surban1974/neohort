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

package neohort.universal.output.lib;

import java.util.HashMap;
import java.util.Hashtable;

import neohort.log.stubs.iStub;

public class parameter extends report_element_base{
	private static final long serialVersionUID = 2401089161630448270L;
	private String TYPE;
public parameter() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){	
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(parent!=null){
			Object result = executeBean(null,this.getTYPE(),_beanLibrary,0);
			if(((report_element_base)parent).external_parameters==null) ((report_element_base)parent).external_parameters = new HashMap();
			((report_element_base)parent).external_parameters.put(ID,result); 			
		}
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta() {
	setName("PARAMETER");
	TYPE="";
	STYLE_ID= "";
}
public String getTYPE() {
	return TYPE;
}
public void setTYPE(String string) {
	TYPE = string;
}
}

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

package neohort.universal.output.util;

import java.io.Serializable;
import java.util.Hashtable;

import neohort.universal.output.lib.report_element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


public abstract class OutputRunTimeService implements I_OutputRunTime,Serializable{

	private static final long serialVersionUID = 1L;
	public Document documentXML;
	public String pathXML;
	public String LIB;
	public String LOG_INTERCEPTOR;

	public Hashtable _tagLibrary = new Hashtable();
	public Hashtable _beanLibrary = new Hashtable();
	public Hashtable _styleLibrary = new Hashtable();
	public String namePac = "";



	public String getNamePac() {
		return namePac;
	}

	public void setNamePac(String namePac) {
		this.namePac = namePac;
	}
	public String ID = "";
	public int status = -1;
	public boolean isReimposta = true;

	public boolean _wait = false;

	public String saveAs;
	public String saveAsName;
	public String nameXML="";
	
public void _notify() {
	_wait = false;
}

public String _separator() {
	String sep = System.getProperty("line.separator");
	if(sep==null || sep.equals("")) return "\r\n";
	else return sep;
}

public void _wait() {
	_wait = true;
}

public Hashtable get_beanLibrary() {
	return _beanLibrary;
}

public String getID(){
	return ID;
}

public String getSaveAs() {
	return saveAs;
}
public int getStatus(){
	return status;
}
public abstract void initXML(String fname);
public abstract void recompileProfile();
public abstract void recompileProfile(report_element _child, report_element _parent);
public abstract void recompileProfile(Node node, report_element _parent);
public abstract void reimposta();
public abstract void setError(Exception e, String level);
public abstract void setError(Exception e, String des, String level);
public void setLIB(String lib) {
	LIB = lib;
}
public void setLOG_INTERCEPTOR(String string) {
	LOG_INTERCEPTOR = string;
}
public void clear(){
	if(documentXML!=null)
		documentXML=null;

	if(_tagLibrary!=null){
		_tagLibrary.clear();
		_tagLibrary=null;
	}
	if(_beanLibrary!=null){
		_beanLibrary.clear();
		_beanLibrary=null;
	}
	if(_styleLibrary!=null){
		_styleLibrary.clear();
		_styleLibrary=null;
	}

}
}

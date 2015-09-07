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

package neohort.universal.output.lib_pln;

import java.util.Hashtable;

import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_baseawt;

public abstract class element extends report_element_baseawt implements report_element {

	private static final long serialVersionUID = 1L;
	public String _content = "";
public element() {
	super();
	Parameters.addElement("ID");
	reimposta();
}
public void add(element child) {
	this._content =	this._content	+
					child._content;
;
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {}
public Object execute(Hashtable _beanLibrary) {
	return null;
}
public void reimposta(){
	name = "ELEMENT";
}
}

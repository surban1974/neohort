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

import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;



public abstract class element extends report_element_base  implements report_element {

	private static final long serialVersionUID = -1L;
	public String _header = "";
	public String _footer = "";
	public String _content = "";
	public String _comment = "";
public element() {
	super();
	Parameters.addElement("ID");
	reimposta();
}
public void add(report_element_base child) {}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {}
public Object execute(Hashtable<String, report_element_base> _beanLibrary) {
	return null;
}
public void reimposta(){
	name = "ELEMENT";
}
public String getSize(String value) {
	try {
//		int vInt = Integer.valueOf(value).intValue();
		return Integer.valueOf(value).toString();
/*
		if (vInt >= 8 && vInt < 10)
			return "1";
		if (vInt >= 10 && vInt < 12)
			return "2";
		if (vInt >= 12 && vInt < 14)
			return "3";
		if (vInt >= 14 && vInt < 18)
			return "4";
		if (vInt >= 18 && vInt < 24)
			return "5";
		if (vInt >= 24 && vInt < 36)
			return "6";
		if (vInt >= 37)
			return "7";
		return "";
*/
	} catch (Exception e) {
		return "";
	}
}

public String analiseBorder(String BORDER){
	String result="";
	if(BORDER.equals("0") && BORDER.length()>0) result+="border:none;";
	int border=0;
	try{
		border=new Integer(BORDER).intValue();
	}catch(Exception e){
	}
	if(border>0){
		if(border==1) result+="border-top:solid;";
		if(border==2) result+="border-bottom:solid;";
			if(border==3) result+="border-top:solid;border-bottom:solid;";
		if(border==4) result+="border-left:solid;";
			if(border==5) result+="border-left:solid;border-top:solid;";
			if(border==6) result+="border-left:solid;border-bottom:solid;";
			if(border==7) result+="border-left:solid;border-top:solid;border-bottom:solid;";
		if(border==8) result+="border-right:solid;";
			if(border==9) result+="border-right:solid;border-top:solid;";
			if(border==10) result+="border-right:solid;border-bottom:solid;";
			if(border==11) result+="border-right:solid;border-top:solid;;border-bottom:solid;";
			if(border==12) result+="border-right:solid;border-left:solid;";
			if(border==13) result+="border-right:solid;border-left:solid;border-top:solid;";
			if(border==14) result+="border-right:solid;border-left:solid;border-bottom:solid;";
			if(border==15) result+="border-right:solid;border-left:solid;border-bottom:solid;border-top:solid;";

	}

	return result+"border-width:1px;";
}

}

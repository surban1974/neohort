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

import java.io.DataOutputStream;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class phrase extends element{

	private static final long serialVersionUID = -1L;
public phrase() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
try{
	this._header+="<SPAN ";
	this._footer="</SPAN>"+this._footer;



	this._header = this._header.trim();
	this._header+=" ";

	if(	!internal_style.getFONT().equals("") ||
		!internal_style.getFONT_COLOR().equals("") ||
		!internal_style.getFONT_SIZE().equals("") ||
		!internal_style.getFONT_TYPE().equals("")){

		this._header+="style=\"";
		if(!internal_style.getFONT().trim().equals("")) this._header+="font-family:"+internal_style.getFONT()+";";
		if(!internal_style.getFONT_COLOR().trim().equals("")){
			if(internal_style.getFONT_COLOR().indexOf(",")>-1) this._header+="color:rgb("+internal_style.getFONT_COLOR()+");";
			else this._header+="color:"+internal_style.getFONT_COLOR()+";";
		}
		if(!internal_style.getFONT_SIZE().trim().equals("")) this._header+="font-size:"+getSize(internal_style.getFONT_SIZE())+"pt;";
		if(!internal_style.getFONT_TYPE().trim().equals("0") && internal_style.getFONT_TYPE().length()>0){
			if(internal_style.getFONT_TYPE().toUpperCase().equals("BOLD"))
				this._header+="font-style:normal;font-weight:bold;";
			if(internal_style.getFONT_TYPE().toUpperCase().equals("BOLDITALIC"))
				this._header+="font-style:italic;font-weight:bold;";
			if(internal_style.getFONT_TYPE().toUpperCase().equals("ITALIC"))
				this._header+="font-style:italic;";
		}

	}
	this._header+="\">";
	this._header = this._header.trim();
	((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
}catch(Exception e){
	setError(e,iStub.log_WARN);
}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(this.getContent()!=null){
			String result=(String)this.getContent();
			String buf="";
			int ch=0;
			boolean inFirst=true;
			while(ch<result.length()){
				if(result.charAt(ch)==' ' && inFirst) buf+="&nbsp;";
				else{
					inFirst=false;
					buf+=result.charAt(ch);
				}
				ch++;
			}
			this.setContent(buf);
			this._content+=prepareContentString(internal_style.getFORMAT());
//			this._content=this._content.replace(" ", "&nbsp;");

		}
		((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).getContentLastElement()).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("PHRASE");
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
}

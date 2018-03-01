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

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class table_cell extends element{

	private static final long serialVersionUID = -1L;
public table_cell() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
try{
	this._header+=	"<TD ";
	this._footer=	"</TD>"+_separator()+this._footer;
	table table = new table();
		try{
			report_element_base el = this;
			while(el.getParent()!=null && !el.getName().equalsIgnoreCase("TABLE"))
				el = (report_element_base)el.getParent();
			table = (table)el;
		}catch(Exception e){
			table = null;
		}

	if (table!=null && !table.getCOLLS_DIMENTION().equalsIgnoreCase("")){
		int colspn = 1;
		try{
			colspn = Integer.valueOf(internal_style.getCOL_SPAN()).intValue();
		}catch(Exception e){
			colspn = 1;
		}
		int WidthMax = 0;
		for(int i=0;i<colspn;i++){
			try{
				WidthMax+=Integer.valueOf(table.getCurrentWidth()).intValue();
			}catch(Exception e){}
		}
		this._header+="WIDTH=\""+WidthMax+((table.isPercent())?"%":"")+"\" ";
	}

	if(!internal_style.getALIGN().equals("")) this._header+="ALIGN=\""+internal_style.getALIGN()+"\" ";
	if(!internal_style.getCOL_SPAN().equals("")) this._header+="COLSPAN=\""+internal_style.getCOL_SPAN()+"\" ";
	if(!internal_style.getROW_SPAN().equals("")) this._header+="ROWSPAN=\""+internal_style.getROW_SPAN()+"\" ";

	if(	(!internal_style.getBORDER().equals("0") && internal_style.getBORDER().length()>0)||
		(!internal_style.getPADDING().equals("0") && internal_style.getPADDING().length()>0)||
		!internal_style.getBACK_COLOR().equals("")){
		this._header+="STYLE=\"";
		if(!internal_style.getBORDER().trim().equals("")) this._header+=analiseBorder(internal_style.getBORDER());
		else this._header+=analiseBorder("15");
		if(!internal_style.getBORDER_WIDTH().equals("0") && internal_style.getBORDER_WIDTH().length()>0) this._header+="border-width:"+internal_style.getBORDER_WIDTH()+";";
		if(!internal_style.getBORDER_COLOR().equals("")){
			if(internal_style.getBORDER_COLOR().indexOf(",")>-1) this._header+="border-color:rgb("+internal_style.getBORDER_COLOR()+");";
			else this._header+="border-color:"+internal_style.getBORDER_COLOR()+";";
		}
		if(!internal_style.getBACK_COLOR().equals("")){
			if(internal_style.getBACK_COLOR().indexOf(",")>-1) this._header+="background-color:rgb("+internal_style.getBACK_COLOR()+");";
			else this._header+="background-color:"+internal_style.getBACK_COLOR()+";";
		}

		if(!internal_style.getPADDING().equals("0") && internal_style.getPADDING().length()>0) this._header+="padding: "+internal_style.getPADDING()+";";
		this._header+="\"";
	}


	this._header = this._header.trim();
	this._header+=">";

	if(	!internal_style.getFONT().equals("") ||
		!internal_style.getFONT_COLOR().equals("") ||
		!internal_style.getFONT_SIZE().equals("") ||
		!internal_style.getFONT_TYPE().equals("")){
		this._header+="<SPAN ";
		this._footer="</SPAN>"+this._footer;

		this._header+="style=\"";
		if(!internal_style.getFONT().trim().equals("")) this._header+="font-family:"+internal_style.getFONT()+";";
		if(!internal_style.getFONT_COLOR().trim().equals("")){
			if(internal_style.getFONT_COLOR().indexOf(",")>-1) this._header+="color:rgb("+internal_style.getFONT_COLOR()+");";
			else this._header+="color:"+internal_style.getFONT_COLOR()+";";
		}
		if(!internal_style.getFONT_SIZE().trim().equals("")) this._header+="font-size:"+getSize(internal_style.getFONT_SIZE())+"pt;";
		if(!internal_style.getFONT_TYPE().trim().equals("0") && internal_style.getFONT_TYPE().length()>0) this._header+="font-type:"+internal_style.getFONT_TYPE().toLowerCase()+";";
		this._header+="\">";
		this._header = this._header.trim();
	}

	((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
}catch(Exception e){
	setError(e,iStub.log_WARN);
}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(this.getContent()!=null){
			this._content+=prepareContentString(internal_style.getFORMAT());
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
	setName("TABLE_CELL");
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
}

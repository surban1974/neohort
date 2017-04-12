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
import neohort.util.util_web;

public class image extends element{
	private static final long serialVersionUID = -4696982253417908863L;
	private java.lang.String IMAGE_SOURCE;
	private java.lang.String IMAGE_LINK;
public image() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
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
		(!internal_style.getPADDING().equals("0") && internal_style.getPADDING().length()>0)){
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

	if(!getIMAGE_LINK().equals("")){
		this._header+="<A HREF=\""+getIMAGE_LINK()+"\">";
		this._footer="</A>"+this._footer;
	}

	if(!getIMAGE_SOURCE().equals("")){
		String pathImg = "";
		try{
			pathImg = util_web.adaptPath(getIMAGE_SOURCE(), _beanLibrary);
		}catch(Exception e){
			pathImg=null;
		}catch(Throwable e){
			pathImg=null;
		}
		if(pathImg==null) pathImg = getIMAGE_SOURCE();

			this._header+=	"<IMG SRC=\""+pathImg+"\" ";
			if(!internal_style.getDIMENTION_H().equals("")) this._header+=	"WIDTH=\""+internal_style.getDIMENTION_H()+"\" ";
			if(!internal_style.getDIMENTION_V().equals("")) this._header+=	"HEIGHT=\""+internal_style.getDIMENTION_V()+"\" ";
			this._header+=	">";
			this._footer=	"</IMG>"+this._footer;

	}
((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:Writer")).getContent())).writeBytes(this._header);
}catch(Exception e){
	setError(e,iStub.log_WARN);
}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		String content=(String)this.getContent();
		this._content+=prepareContentString(content);
		((report_element_base)((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement()).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public java.lang.String getIMAGE_LINK() {
	return IMAGE_LINK;
}
public java.lang.String getIMAGE_SOURCE() {
	return IMAGE_SOURCE;
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("IMAGE");
	IMAGE_SOURCE = "";
	IMAGE_LINK = "";
	STYLE_ID = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setIMAGE_LINK(java.lang.String newIMAGE_LINK) {
	IMAGE_LINK = newIMAGE_LINK;
}
public void setIMAGE_SOURCE(java.lang.String newIMAGE_SOURCE) {
	IMAGE_SOURCE = newIMAGE_SOURCE;
}


}

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
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class table extends element{
	private static final long serialVersionUID = 561345809529655249L;
	private java.lang.String STYLE_ID;
	private java.lang.String COL;
	private java.lang.String COLLS_DIMENTION;
	private java.lang.String ALIGN;
	Vector width = new Vector();
	int cur_col = 0;
	int max_col = 0;
	int cur_row = 0;
	private boolean percent = false;
	private java.lang.String BORDER;
public table() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		String border = "";
		if (!getBORDER().trim().equals("")) border = " border=\""+getBORDER()+"\" ";
		width = new Vector();
		int WidthMax = 0;
		if(getCOLLS_DIMENTION().equals("")){
			max_col = 1;
			this._header = "<TABLE "+border+" >"+_separator();
			this._footer = "</TABLE>"+_separator();
		}else {
			max_col = Integer.valueOf(getCOL()).intValue();
			java.util.StringTokenizer st = new java.util.StringTokenizer(getCOLLS_DIMENTION(), ",");
			if(getCOLLS_DIMENTION().indexOf("%")>-1){
				percent = true;
				while (st.hasMoreTokens()){
					String curWidth = st.nextToken();
					if(curWidth.indexOf("%")>-1){
						curWidth = curWidth.substring(0,curWidth.indexOf("%"));
					}
					width.addElement(curWidth);
					try{
						WidthMax+=Integer.valueOf(curWidth).intValue();
					}catch(Exception e){}
				this._header = "<TABLE "+border+" WIDTH=\"100%\">"+_separator();
				this._footer = "</TABLE>"+_separator();
				}
			}else{
				while (st.hasMoreTokens()){
					String curWidth = st.nextToken();
					width.addElement(curWidth);
					try{
						WidthMax+=Integer.valueOf(curWidth).intValue();
					}catch(Exception e){}
				}
				this._header = "<TABLE  "+border+" WIDTH=\""+WidthMax+"\">"+_separator();
				this._footer = "</TABLE>"+_separator();
			}
		}
		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		java.util.Vector vector = ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent()));
		vector.remove(vector.lastElement());

		((report_element_base)((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement()).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());
		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public java.lang.String getALIGN() {
	return ALIGN;
}
public java.lang.String getBORDER() {
	return BORDER;
}
public java.lang.String getCOL() {
	return COL;
}
public java.lang.String getCOLLS_DIMENTION() {
	return COLLS_DIMENTION;
}
public int getCurrentRow() {
	return cur_row;
}
public String getCurrentWidth() {
	try{
		String res = (String)width.elementAt(cur_col);
		cur_col++;
		if (cur_col==max_col) cur_col=0;
		return res;
	}catch(Exception e){}
	return "";
}
public java.lang.String getSTYLE_ID() {
	return STYLE_ID;
}
public boolean isPercent() {
	return percent;
}
public void nextCurrentRow() {
	cur_row++;
}
public void reimposta() {
	setName("TABLE");
	STYLE_ID = "";
	COL = "";
	COLLS_DIMENTION = "";
	ALIGN = "";
	BORDER = "";
}
public void setALIGN(java.lang.String newALIGN) {
	ALIGN = newALIGN;
}
public void setBORDER(java.lang.String newBORDER) {
	BORDER = newBORDER;
}
public void setCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(this);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void setCOL(java.lang.String newCOL) {
	COL = newCOL;
}
public void setCOLLS_DIMENTION(java.lang.String newCOLLS_DIMENTION) {
	COLLS_DIMENTION = newCOLLS_DIMENTION;
}
public void setCurrentCol(int cr) {
	cur_col = cr;
}
public void setPercent(boolean newPercent) {
	percent = newPercent;
}
public void setSTYLE_ID(java.lang.String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
}

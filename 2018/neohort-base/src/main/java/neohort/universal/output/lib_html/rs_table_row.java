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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class rs_table_row extends element{
	private static final long serialVersionUID = 1L;
	private java.lang.String STYLE_ID;
	private java.lang.String RS_ID;
	private ResultSet resultSet;
	private boolean next = false;
	private style row_style;
	private Hashtable<String, Object> lastRow;
	private Hashtable<String, style> column_styles;
public rs_table_row() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(resultSet==null || resultSet.isClosed()){
			Object obj = null; 
			if(_beanLibrary.get("BEAN:"+this.getRS_ID()+"_ids_"+motore.hashCode())!=null)
				obj = ((report_element_base)_beanLibrary.get("BEAN:"+this.getRS_ID()+"_ids_"+motore.hashCode())).getContent(); 
			else if(_beanLibrary.get("BEAN:"+this.getRS_ID())!=null)
				obj = ((report_element_base)_beanLibrary.get("BEAN:"+this.getRS_ID())).getContent(); 
			if(obj!=null && obj instanceof ResultSet)
				resultSet = (ResultSet)obj;
		}
	}catch(Exception e){
		
	}
/*
	try{
		this._header = "<TR>"+_separator();
		this._footer = "</TR>"+_separator();
		table table = new table();
		try{
			report_element_base el = this;
			while(el.getParent()!=null && !el.getName().equalsIgnoreCase("TABLE"))
				el = (report_element_base)el.getParent();
			table = (table)el;
			table.setCurrentCol(0);
		}catch(Exception e){
			table = null;
		}
		if (table!=null) table.nextCurrentRow();
		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
*/	
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
/*	
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
*/
}
public java.lang.String getSTYLE_ID() {
	return STYLE_ID;
}
public void reimposta() {
	setName("RS_TABLE_ROW");
	getParameters().addElement("STYLE_ID");
	STYLE_ID= "";
	RS_ID = "";
	lastRow = new Hashtable<String, Object>();
	column_styles = new Hashtable<String, style>();
}
@SuppressWarnings({ "unchecked", "rawtypes" })
public void setCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	try{
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(this);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void setSTYLE_ID(java.lang.String newSTYLE_ID) {
	STYLE_ID = newSTYLE_ID;
}
public java.lang.String getRS_ID() {
	return RS_ID;
}
public void setRS_ID(java.lang.String rS_ID) {
	RS_ID = rS_ID;
}
public ResultSet getResultSet() {
	return resultSet;
}
public style getRow_style() {
	return row_style;
}
public Hashtable<String, Object> getLastRow() {
	return lastRow;
}
@SuppressWarnings("rawtypes")
public boolean isActive(Hashtable<String, report_element_base> _tagLibrary,Hashtable<String, report_element_base> _beanLibrary,Hashtable<String, report_element_base> _styleLibrary) {
	row_style = null;
	column_styles.clear();
	try{
		((report_element_base)((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement()).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());
		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

	return next;
}
public boolean isPreActive(Hashtable<String, report_element_base> _tagLibrary,Hashtable<String, report_element_base> _beanLibrary,Hashtable<String, report_element_base> _styleLibrary) {
	try{
		next = resultSet.next();
		String row_style_id = null;
		try{
			row_style_id = resultSet.getString("row_style_id");
		}catch(Exception e){				
		}
		if(next && row_style_id!=null){
			try{
				row_style = (style)_styleLibrary.get("STYLE:"+row_style_id+"_ids_"+motore.hashCode());
			}catch(Exception e){
				setError(e,iStub.log_WARN);
			}
		}else if(next){
			ResultSetMetaData resultSetMD = resultSet.getMetaData();
			for (int i = 1; i <= resultSetMD.getColumnCount(); i++ ) {
				String name = resultSetMD.getColumnName(i);
				  if(name.toLowerCase().startsWith("column_style_id_")){
					  style current = (style)_styleLibrary.get("STYLE:"+name+"_ids_"+motore.hashCode());
					  if(current!=null)
						  column_styles.put(name, current);
				  }
			}			
		}
		try{
			this._header = "<TR>"+_separator();
			this._footer = "</TR>"+_separator();
			table table = new table();
			try{
				report_element_base el = this;
				while(el.getParent()!=null && !el.getName().equalsIgnoreCase("TABLE"))
					el = (report_element_base)el.getParent();
				table = (table)el;
				table.setCurrentCol(0);
			}catch(Exception e){
				table = null;
			}
			if (table!=null) table.nextCurrentRow();
			((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
		}catch(Exception e){
			setError(e,iStub.log_WARN);
		}
		return next;
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
	return false;
}
public Hashtable<String, style> getColumn_styles() {
	return column_styles;
}
}

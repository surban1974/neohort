/**
* Creation date: (22/12/2005)
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

package neohort.universal.output.lib_xls;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class rs_table_row extends element{
	private static final long serialVersionUID = -1L;
	private String HEIGHT;
	private int deltaRow;
	private String RS_ID;
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
	}
	public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	
		try{
			bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
			bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);
	
			int X = 0;
			int Y = 0;
	
			if(parent!=null && parent instanceof table){
				try{
					X = ((table)parent).getStartX();
				}catch(Exception e){
				}
			}
			try{
				Y = ((Integer)_sysPdfCR.getContent()).intValue();
			}catch(Exception e){
			}
	
			if(getParent()!=null && getParent() instanceof table && ((table)getParent()).getParentTable_Block()!=null)
				((table)getParent()).getParentTable_Block().setDeltaRow(((table)getParent()).getParentTable_Block().getDeltaRow()+1);
	
			if(deltaRow==0)	Y++;
			else Y=Y+deltaRow;
	
	
			_sysPdfCR.setContent(new Integer(Y));
			_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR);
	
			_sysPdfCC.setContent(new Integer(X));
			_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);
	
	
			if(_tagLibrary.get(getName()+":"+getID())==null)
				_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
			else _tagLibrary.remove(getName()+":"+getID());
	
		}catch(Exception e){
			setError(e,iStub.log_WARN);
		}
	
	}
	public void reimposta() {
		setName("RS_TABLE_ROW");
		STYLE_ID = "";
		HEIGHT = "";
		RS_ID = "";
		lastRow = new Hashtable<String, Object>();
		column_styles = new Hashtable<String, style>();
	}
	public String getHEIGHT() {
		return HEIGHT;
	}
	
	public void setHEIGHT(String string) {
		HEIGHT = string;
	}

	public int getDeltaRow() {
		return deltaRow;
	}

	public void setDeltaRow(int i) {
		deltaRow = i;
	}
	public void reStyle(style style_new) {
		if(internal_style==null) return;
		internal_style.reStyle(style_new);
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
	public String getRS_ID() {
		return RS_ID;
	}
	public void setRS_ID(String rS_ID) {
		RS_ID = rS_ID;
	}
	
	public boolean isActive(Hashtable<String, report_element_base> _tagLibrary,Hashtable<String, report_element_base> _beanLibrary,Hashtable<String, report_element_base> _styleLibrary) {
		row_style = null;
		column_styles.clear();
		if(next){
			try{
				bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
				bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW); 
				
				int X = 0;
				int Y = 0;
				
				if(parent!=null && parent instanceof table){
					try{
						X = ((table)parent).getStartX();
					}catch(Exception e){
					}
				}
				try{
					Y = ((Integer)_sysPdfCR.getContent()).intValue();
				}catch(Exception e){
				}
			
			
				if(getParent()!=null && getParent() instanceof table && ((table)getParent()).getParentTable_Block()!=null)
					((table)getParent()).getParentTable_Block().setDeltaRow(((table)getParent()).getParentTable_Block().getDeltaRow()+1);

				if(deltaRow==0)	Y++;
				else Y=Y+deltaRow;		
				
			
				_sysPdfCR.setContent(new Integer(Y));
				_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR); 

				_sysPdfCC.setContent(new Integer(X));
				_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);
			}catch(Exception e){
				setError(e,iStub.log_WARN);
			}
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

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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;


public class condition_query extends report_element_base{
	private static final long serialVersionUID = 2765908908525724082L;
	private java.lang.String Q_INIT;
	private java.lang.String Q_QUERY;
	private java.lang.String Q_MAX_ELEMENT;

	private Vector rows = new Vector();
	private Vector tabName = new Vector();
	private int cur_element = 0;
	private int max_element = -1;
public condition_query() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{		
		if(getQ_INIT().equals("") || getQ_QUERY().equals("")) return;
		try{
			max_element = Integer.valueOf(getQ_MAX_ELEMENT()).intValue();
		}catch(Exception e){}
		Object obj = ((report_element_base)_beanLibrary.get("BEAN:"+this.getQ_INIT()+"_ids_"+motore.hashCode())).getContent(); 
		Connection connection = (Connection)obj;
		Statement statment = connection.createStatement();
		ResultSet rset = null;
		try {
			rset = statment.executeQuery(getQ_QUERY());
				ResultSetMetaData rsetMD = rset.getMetaData();
				for (int i = 1; i <= rsetMD.getColumnCount(); i++) tabName.addElement(rsetMD.getColumnLabel(i));
				int c_counter = 0;
				if(max_element>-1){
		  			while (rset.next() && c_counter<max_element) {
						java.util.Vector newRow = new java.util.Vector();
						for (int i = 1; i <= rsetMD.getColumnCount(); i++) {
	  	      				newRow.addElement(rset.getObject(i));
						}
						rows.addElement(newRow);
						c_counter++;
					}
				}else{
		  			while (rset.next()) {
						java.util.Vector newRow = new java.util.Vector();
						for (int i = 1; i <= rsetMD.getColumnCount(); i++) {
	  	      				newRow.addElement(rset.getObject(i));
						}
						rows.addElement(newRow);
					}
				}
			  	rset.close();
			  	statment.close();			
		} catch (java.sql.SQLException e) {
			setError(e,iStub.log_ERROR);
		} finally {
			if(rset!=null){
				try{
					rset.close();
					rset=null;
				}catch(Exception e){}
			}
			if(statment!=null){
				try{
					statment.close();
					statment=null;
				}catch(Exception e){}
			}
		}
		cur_element = 0;	
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		cur_element = 0;
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public java.lang.String getQ_INIT() {
	return Q_INIT;
}
public java.lang.String getQ_MAX_ELEMENT() {
	return Q_MAX_ELEMENT;
}
public java.lang.String getQ_QUERY() {
	return Q_QUERY;
}
public boolean isActiveCycle() {
	if (cur_element<rows.size()){
		cur_element+=1;
		return true;
	}	
	return false;
}
public boolean isPreActiveCycle() {
	if (cur_element<rows.size()){
		return true;
	}	
	return false;
}
public void reimposta() {
	setName("CONDITION_QUERY");
	Q_INIT = "";
	Q_QUERY = "";
	Q_MAX_ELEMENT = "";	
}
public String RETURN(String nome){
	try{
		if(nome.trim().toUpperCase().indexOf("SYSTEM:")==0){
			String diret = nome.trim().toUpperCase().substring(7);
			if(diret.equals(iConst.iHORT_LIB_CONDITION_QUERY_SIZE)) return String.valueOf(rows.size());
			if(diret.equals(iConst.iHORT_LIB_CONDITION_QUERY_INDEX)) return String.valueOf(cur_element);			
		}
		else{
			int cont = 0;
			while(	cont<tabName.size() && 
					!((String)tabName.get(cont)).trim().equalsIgnoreCase(nome.trim())){
						cont+=1;
			}			
			if (cont>=tabName.size()) return "";
			Object res = ((Vector)rows.elementAt(cur_element)).elementAt(cont);
			String curValue = "";
			if(res instanceof String)  curValue = (String)res;
			else curValue = String.valueOf(res);
			return curValue;
		}
		return "";	
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		return "";
	}
}
public void setQ_INIT(java.lang.String newQ_INIT) {
	Q_INIT = newQ_INIT;
}
public void setQ_MAX_ELEMENT(java.lang.String newQ_MAX_ELEMENT) {
	Q_MAX_ELEMENT = newQ_MAX_ELEMENT;
}
public void setQ_QUERY(java.lang.String newQ_QUERY) {
	Q_QUERY = newQ_QUERY;
}
public String SYS_RECORDSETCOUNT() {
	return String.valueOf(cur_element);
}
}

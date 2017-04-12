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
import java.sql.DriverManager;
import java.util.Hashtable;

import neohort.log.stubs.iStub;

public class init_db_connection extends report_element_base{
	private static final long serialVersionUID = 1431745749861977477L;
	private java.lang.String DB_USER;
	private java.lang.String DB_PASSWORD;
	private java.lang.String DB_DRIVER;
	private java.lang.String DB_URL;
public init_db_connection() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(	!getDB_USER().equals("") &&
			!getDB_PASSWORD().equals("") &&
			!getDB_DRIVER().equals("") &&
			!getDB_URL().equals("")){
				Class.forName(getDB_DRIVER());
				Connection connection = DriverManager.getConnection(getDB_URL(),getDB_USER(),getDB_PASSWORD());
				this.setContent(connection);
				_beanLibrary.put("BEAN:"+this.getID()+"_ids_"+motore.hashCode(),this);				
			}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());		

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
	
}
public java.lang.String getDB_DRIVER() {
	return DB_DRIVER;
}
public java.lang.String getDB_PASSWORD() {
	return DB_PASSWORD;
}
public java.lang.String getDB_URL() {
	return DB_URL;
}
public java.lang.String getDB_USER() {
	return DB_USER;
}
public void reimposta() {
	setName("INIT_DB_CONNECTION");
	DB_USER = "";
	DB_PASSWORD = "";
	DB_DRIVER = "";
	DB_URL = "";
}
public void setDB_DRIVER(java.lang.String newDB_DRIVER) {
	DB_DRIVER = newDB_DRIVER;
}
public void setDB_PASSWORD(java.lang.String newDB_PASSWORD) {
	DB_PASSWORD = newDB_PASSWORD;
}
public void setDB_URL(java.lang.String newDB_URL) {
	DB_URL = newDB_URL;
}
public void setDB_USER(java.lang.String newDB_USER) {
	DB_USER = newDB_USER;
}
}

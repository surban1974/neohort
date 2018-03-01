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

package neohort.universal.output.util;

import java.io.Serializable;
import java.util.Hashtable;


public class Dispatcher implements Serializable{
	private static final long serialVersionUID = 1L;
	private Hashtable<String, Dispatcher_element> reports = new Hashtable<String, Dispatcher_element>();
public Dispatcher() {
	super();
}
public void destroyReport(String key) {
	if(reports.get(key)!=null)
		reports.remove(key);
}
public Dispatcher_element getReport(String key) {
	try{
		return (Dispatcher_element)reports.get(key);
	}catch(Exception e){
		return null;
	}	
}
public Hashtable<String, Dispatcher_element> getReports() {
	return reports;
}
public void setReport(String key, Dispatcher_element report) {
	if(reports.get(key)==null)
		reports.put(key,report);
	else{
		reports.remove(key);
		reports.put(key,report);
	}	
}
public void setReports(Hashtable<String, Dispatcher_element> newReports) {
	reports = newReports;
}
}

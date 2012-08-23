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
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Request_thread implements Serializable{

	private static final long serialVersionUID = -8172395474515196080L;
	private java.util.Hashtable request_value = new java.util.Hashtable();
	private java.util.Hashtable session_scope_value = new java.util.Hashtable();
	private java.util.Hashtable session_request_value = new java.util.Hashtable();
	private java.util.Hashtable application_scope_value = new java.util.Hashtable();
	private HttpServletRequest request;
	private HttpServlet context;
	private String Path_Loc = "";
Request_thread() {
	super();
}
public Request_thread(HttpServletRequest req) {
	super();
	this.request = req;
	prepareDates();
}
public Request_thread(HttpServletRequest req, String path) {
	super();
	this.request = req;
	this.Path_Loc = path;
	prepareDates();
}
public Request_thread(HttpServletRequest req, HttpServlet context) {
	super();
	this.request = req;
	this.context = context;
	prepareDates();
}
public Request_thread(HttpServletRequest req, HttpServlet context, String path) {
	super();
	this.request = req;
	this.Path_Loc = path;
	prepareDates();
}
public Object getAttribute(String key) {
	return this.session_request_value.get(key);
}
public Object getAttributeAH(String key) {
	return this.application_scope_value.get(key);
}
public Object getAttributeSH(String key) {
	return this.session_scope_value.get(key);
}
public String getParameter(String key) {
	if(request_value.get(key)==null) return "";
	else return request_value.get(key).toString();
}
public String getParameterValues(String key) {
	if(request_value.get(key)==null) return "";
	else return (String)request_value.get(key);
}
private void prepareDates() {
	try{
		if(request!=null){
				Enumeration e = request.getParameterNames();
				while (e.hasMoreElements()) {
					String name = e.nextElement().toString();
					try{
						this.request_value.put(name,request.getParameter(name));
					}catch(Exception ex){
						System.out.println("prepareDates_getParameterValues: "+ ex.toString());
					}	
				}
			HttpSession tsxSessionHolder = request.getSession(false);
			Enumeration names = tsxSessionHolder.getAttributeNames();
			while(names.hasMoreElements()){
				String curName = names.nextElement().toString();
				try{
					this.session_scope_value.put(curName,tsxSessionHolder.getAttribute(curName));
				}catch(Exception ex){
					System.out.println("prepareDates_getAttributeFromSession: "+ ex.toString());				
				}
			}
			Enumeration names_r = request.getAttributeNames();
			while(names_r.hasMoreElements()){
				String curName = names_r.nextElement().toString();
				try{
					this.session_request_value.put(curName,request.getAttribute(curName));
				}catch(Exception ex){
					System.out.println("prepareDates_getAttributeFromRequest: "+ ex.toString());				
				}
			}

		}
		if(context!=null && context.getServletContext()!=null){
			Enumeration names = context.getServletContext().getAttributeNames();
			while(names.hasMoreElements()){
				String curName = names.nextElement().toString();
				try{
					this.application_scope_value.put(curName,context.getServletContext().getAttribute(curName));
				}catch(Exception ex){
					System.out.println("prepareDates_getAttributeFromApplication: "+ ex.toString());				
				}
			}
			
		}
			
		if(Path_Loc!=null && !Path_Loc.equals("")){
			java.util.StringTokenizer st = new java.util.StringTokenizer(Path_Loc, "&");
			while(st.hasMoreTokens()){
				String el = st.nextToken();
				java.util.StringTokenizer st_el = new java.util.StringTokenizer(el, "=");
				String T_par = "";
				String T_val = "";
				if(st_el.hasMoreElements()){
					T_par = st_el.nextToken();
					try{
						T_val = st_el.nextToken();
					}catch(Exception ex){}
					this.request_value.put(T_par,T_val);
				}
			}	
		}

	}catch(Exception e){
		System.out.println("prepareDates: "+ e.toString());
	}	
}
}

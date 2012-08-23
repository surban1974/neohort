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

package neohort.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.util.OutputRunTime;
public class util_web {

public static String environmentState(HttpServletRequest request){
	String result="";
		try{		
			result+=("Start DEBUG --------------------"+System.getProperty("line.separator"));
			Enumeration en = request.getSession().getAttributeNames();		
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				try{
					result+=("SESSION."+key+ "= " +util_cloner.sizeByte(request.getSession().getAttribute(key))+" bytes;"+System.getProperty("line.separator"));
				}catch(Exception e){
					result+=("SESSION."+key+ "= ? bytes;"+System.getProperty("line.separator"));	
				}
			}
			en = request.getAttributeNames();
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				try{
					result+=("REQUEST."+key+ "= " +util_cloner.sizeByte(request.getAttribute(key))+" bytes;"+System.getProperty("line.separator"));
				}catch(Exception e){
					result+=("REQUEST."+key+ "= ? bytes;"+System.getProperty("line.separator"));	
				}
			}
			en = request.getParameterNames();
			while(en.hasMoreElements()){
				String key = (String)en.nextElement();
				try{
					result+=("REQUEST.PARAMETER."+key+ "= " +util_cloner.sizeByte(request.getParameter(key))+" bytes;"+System.getProperty("line.separator"));
				}catch(Exception e){
					result+=("REQUEST.PARAMETER."+key+ "= ? bytes;"+System.getProperty("line.separator"));	
				}
			}
		}catch(Exception e){	
		}
	return result;
}

public static String adaptPath(String IMAGE_SOURCE, Hashtable _beanLibrary){
	javax.servlet.http.HttpServletRequest request = null;
	try{
		request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:Request")).getContent());
	}catch(Exception e){
	}
	String pathImg = "";
	pathImg = OutputRunTime.service_adaptPath(IMAGE_SOURCE,null,request);
	return pathImg;
}
}

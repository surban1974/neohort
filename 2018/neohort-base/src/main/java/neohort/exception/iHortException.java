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

package neohort.exception;

import javax.servlet.http.HttpServletRequest;

import neohort.log.log;
import neohort.util.util_reflect;

public class iHortException extends Exception {
	private static final long serialVersionUID = -1L;
	private static String levels=";DEBUG;INFO;WARN;ERROR;FATAL;";

public iHortException(String mess, Exception ex, HttpServletRequest request, String level) {
	if(!checkLevel(level)) return;
	String _log="["+level+"] ";
		_log+="["+util_reflect.prepareClassInfo(new String[]{"iHort.java","report_element_base.java"},new String[]{"setError","setError"})+"] ";

	if(mess!=null) _log+=mess;
	if(ex!=null){
		if(ex.getMessage()!=null) _log+=" : "+ex.getMessage();
		else _log+=" : "+ex.toString();
	}		
	try{
		log.writeLog(request,_log,log.getLogInit().get_LogLevel());
	}catch(Exception e){
	}
	
}
public iHortException(String mess, Throwable t, HttpServletRequest request, String level) {
	if(!checkLevel(level)) return;
	String _log="["+level+"] ";
		_log+="["+util_reflect.prepareClassInfo(new String[]{"iHort.java","report_element_base.java"},new String[]{"setError","setError"})+"] ";

	if(mess!=null) _log+=mess;
	if(t!=null){
		if(t.getMessage()!=null) _log+=" : "+t.getMessage();
		else _log+=" : "+t.toString();
	}		
	try{
		log.writeLog(request,_log,log.getLogInit().get_LogLevel());
	}catch(Exception e){
	}
	
}
public iHortException() {
	super();	
	try{
		String _log="";
			_log+="["+util_reflect.prepareClassInfo(new String[]{"iHort.java","report_element_base.java"},new String[]{"setError","setError"})+"] ";

		if(getMessage()!=null) _log+=getMessage()+" ";
		log.writeLog(_log+toString(),"DEBUG");
	}catch(Exception e){
	}	
}

public static boolean checkLevel(String level) {
	if(level==null) level="DEBUG";
	String curr_level="DEBUG"; 
	try{
		curr_level = log.getLogInit().get_LogLevel();
	}catch(Exception e){
	}
	String loc_levels = levels.substring((levels.indexOf(";"+curr_level+";")==-1)?0:levels.indexOf(";"+curr_level+";"));
	if(loc_levels.indexOf(";"+level+";")==-1) return false;
	return true;
}

}
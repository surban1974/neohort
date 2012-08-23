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

package neohort.log;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import neohort.log.stubs.iStub;

public class log_generator {
	private log_init init;
	private iStub logStub;

	
public log_generator(log_init init) {
	super();
	this.init = init;
	try{
		writeLog(init,"INIT","DEBUG");
	}catch(Exception e){	
		System.out.println("iHort : Log : Init "+e.toString());
	}
}

public static i_log_pattern patternFactory(String className){
	i_log_pattern element = null;
	if(className==null || className.equals("")) return new log_patternSimple();
	if(element==null){
		try{	
			element = (i_log_pattern)Class.forName(className).newInstance();
		}catch (Exception e) {
			element = new log_patternSimple();
		}
	}
	return element;
}

public static iStub stubFactory(String className){
	iStub element = null;
	if(className==null || className.equals("")) return null;
	if(element==null){
		try{	
			element = (iStub)Class.forName(className).newInstance();
		}catch (Exception e) {
			element = null;
		}
	}
	return element;
}

public static iStub stubFactory(log_init _init){
	if(_init==null) return null;
	String className = _init.get_LogStub();
	if(className==null || className.equals("")) return null;
	iStub element = null;
	
	if(element==null){
		try{	
			element = (iStub)Class.forName(className).newInstance();
		}catch (Exception e) {
			element = null;
		}
	}
	return element;
}

public static HashMap prepare4stub(
		String mess,
		Exception exception,
		Throwable throwable,
		HttpServletRequest request,
		String level){
	
	HashMap result = new HashMap();
		result.put(iStub.log_stub_mess,mess);
		result.put(iStub.log_stub_exception,exception);
		result.put(iStub.log_stub_throwable,throwable);
		result.put(iStub.log_stub_request,request);
		result.put(iStub.log_stub_level,level);
	
	return result;
}


public synchronized i_log_pattern get_log_Pattern(){ 
	return patternFactory( init.get_LogPattern());
}	

public synchronized void writeLog(HttpServletRequest request, String msg, String level) throws IOException {
	i_log_pattern logPattern = patternFactory( init.get_LogPattern());
	if(logPattern==null) logPattern = new log_patternSimple();
	String log_mess = logPattern.prepare(request,msg,level);
	if(logStub==null) logStub = stubFactory(init);
	if(logStub!=null){
		logStub.write(prepare4stub(log_mess, null, null, request, level));
	}
}

public synchronized void writeLog(String msg,String level) throws IOException {
		i_log_pattern logPattern = patternFactory( init.get_LogPattern());
		if(logPattern==null) logPattern = new log_patternSimple();
		if(logStub==null) logStub = stubFactory(init);
		if(logStub!=null){
			logStub.write(prepare4stub(msg, null, null, null, level));
		}
}

public static synchronized void writeLog(log_init _init, String msg, String level) throws IOException {
	i_log_pattern logPattern = patternFactory( _init.get_LogPattern());
	if(logPattern==null) logPattern = new log_patternSimple();

	iStub _logStub = stubFactory(_init);
	if(_logStub==null) _logStub = stubFactory(_init);
	if(_logStub!=null){
		_logStub.write(prepare4stub(msg, null, null, null, level));
	}
}



public String get_log_Content(String lineSep) throws IOException {

	return "";
}
}



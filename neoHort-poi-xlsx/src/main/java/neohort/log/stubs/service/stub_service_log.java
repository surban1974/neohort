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

package neohort.log.stubs.service;

import java.util.HashMap;

import neohort.exception.iHortException;
import neohort.exception.service.iHortExceptionService;
import neohort.log.i_log_pattern;
import neohort.log.log;
import neohort.log.log_generator;
import neohort.log.log_init;
import neohort.log.log_patternSimple;
import neohort.log.stubs.iStub;
import neohort.util.util_reflect;

public class stub_service_log {

	public stub_service_log() {
		super();
	}

public void write(HashMap hm){
	try{
		Object mess = hm.get(iStub.log_stub_mess);
		Object exc = hm.get(iStub.log_stub_exception);

		Object level = hm.get(iStub.log_stub_level);


		new iHortExceptionService(
			(mess==null)?"":(String)mess,
			(exc==null)?null:(Exception)exc,			
			(level==null)?"DEBUG":(String)level);
	}catch(Exception e){
	}
}

public String getMess(String mess, Exception ex,  String level) {
	String log_mess = "";
	if(!iHortException.checkLevel(level)) return log_mess;
	String _log="["+level+"] ";
		_log+="["+util_reflect.prepareClassInfo(new String[]{"iHort.java","report_element_base.java"},new String[]{"setError","setError"})+"] ";
	if(mess!=null) _log+=mess;
	if(ex!=null){
		if(ex.getMessage()!=null) _log+=" : "+ex.getMessage();
		else _log+=" : "+ex.toString();
	}		
	try{
		i_log_pattern logPattern = null;
		log_init li = null;
		if(log.getLogInit()!=null) li = log.getLogInit();
		else{ 
			li = new log_init();
			li.init();
		}
			logPattern = log_generator.patternFactory( li.get_LogPattern());
		if(logPattern==null) logPattern = new log_patternSimple();
		log_mess = logPattern.prepare(_log,level);
	}catch(Exception e){
		log_mess = e.toString();
	}
	return log_mess;
}


}

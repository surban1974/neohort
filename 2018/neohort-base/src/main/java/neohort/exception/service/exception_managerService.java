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

package neohort.exception.service;

import java.util.HashMap;



import neohort.log.log_init;
import neohort.log.stubs.iStub;
import neohort.util.util_reflect;

public class exception_managerService {

public static void exception_to_log(String _ExternalLogStub,Exception exception, String description, String level){ 

	String _LogStub = null;
	if(System.getProperty(log_init.id_LogStub)!=null) _LogStub = System.getProperty(log_init.id_LogStub);
	if(_LogStub==null) _LogStub = _ExternalLogStub;
	if(_LogStub==null) _LogStub = "neohort.log.stubs.service.stub_service_log";
	
	try{
		Object log_stub = Class.forName(_LogStub).newInstance();
		Object[] prm = new Object[1];
		HashMap<String,Object> hm = new HashMap<String,Object>();
			hm.put(iStub.log_stub_mess,description);
			hm.put(iStub.log_stub_exception,exception);
			hm.put(iStub.log_stub_level,level);

		prm[0]=hm;	
		util_reflect.setValue(log_stub,"write",prm);
	}catch(Exception ex){
		System.out.println("Load Log Stub: "+ex.toString());
		new iHortExceptionService(description,exception,level);
	}
}



public static void exception_to_log(String _ExternalLogStub, Throwable throwable, String description, String level){ 

	String _LogStub = null;
	if(System.getProperty(log_init.id_LogStub)!=null) _LogStub = System.getProperty(log_init.id_LogStub);
	if(_LogStub==null) _LogStub = _ExternalLogStub;
	if(_LogStub==null) _LogStub = "neohort.log.stubs.stub_websession_log";
	
	try{
		Object log_stub = Class.forName(_LogStub).newInstance();
		Object[] prm = new Object[1];
		HashMap<String,Object> hm = new HashMap<String,Object>();
			hm.put(iStub.log_stub_mess,description);
			hm.put(iStub.log_stub_exception,throwable);
			hm.put(iStub.log_stub_level,level);

		prm[0]=hm;	
		util_reflect.setValue(log_stub,"write",prm);
	}catch(Exception ex){
		System.out.println("Load Log Stub: "+ex.toString());
		new iHortExceptionService(description,throwable,level);
	}
}


}

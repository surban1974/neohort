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

package neohort.log.stubs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import neohort.exception.iHortException;

public class stub_log {

	public stub_log() {
		super();
	}

public void write(HashMap hm){
	try{
		Object mess = hm.get(iStub.log_stub_mess);
		Object exc = hm.get(iStub.log_stub_exception);
		Object request = hm.get(iStub.log_stub_request);
		Object level = hm.get(iStub.log_stub_level);
		new iHortException(
			(mess==null)?"":(String)mess,
			(exc==null)?null:(Exception)exc,
			(request==null)?null:(HttpServletRequest)request,
			(level==null)?"DEBUG":(String)level);
	}catch(Exception e){
	}
}
}

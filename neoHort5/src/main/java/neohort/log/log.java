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

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class log extends HttpServlet {
	private static final long serialVersionUID = 5512145504900891088L;
	private static log_init logInit;
	private static log_generator logG;
		
	public void init() throws ServletException, UnavailableException {
		logInit = new log_init();
			logInit.init();
		logG = new log_generator(logInit);
	}

	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, UnavailableException {
	}
		
	private static void service_mountLog(){
		logInit = new log_init();
			logInit.init();
		logG = new log_generator(logInit);
	}
	
	public static synchronized void writeLog(String msg, String level) {
		try{
			if(logG==null) service_mountLog();
			logG.writeLog(msg,level);
		}catch(Exception e){}
	}
	public static synchronized void writeLog(HttpServletRequest _request, String msg, String level){
		try{
			logG.writeLog(_request,msg,level);
		}catch(Exception e){}
	}
	
	public static log_generator getLogG() {
		return logG;
	}
	public static log_init getLogInit() {
		return logInit;
	}

}

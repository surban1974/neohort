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


package neohort.log.service;

import neohort.log.service.log_init_service;



public class log_service {
	private static final long serialVersionUID = 5512145504900891088L;
	private static log_init_service logInit;
	private static log_generator_service logG;
		
	public log_service(){
		try{
			init();
		}catch (Exception e) {

		}	
	}
	
	public void init() throws Exception {
		logInit = new log_init_service();
			logInit.init();
		logG = new log_generator_service(logInit);
	}


		
	private static void service_mountLog(){
		logInit = new log_init_service();
			logInit.init();
		logG = new log_generator_service(logInit);
	}
	
	public static synchronized void writeLog(String msg, String level) {
		try{
			if(logG==null) service_mountLog();
			logG.writeLog(msg,level);
		}catch(Exception e){}
	}

	
	public static log_generator_service getLogG() {
		return logG;
	}
	public static log_init_service getLogInit() {
		return logInit;
	}

}

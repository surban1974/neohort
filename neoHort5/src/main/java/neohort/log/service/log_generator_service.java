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

import java.io.BufferedReader;
import java.io.IOException;




public class log_generator_service {
	private log_init_service init;
	
public log_generator_service(log_init_service init) {
	super();
	this.init = init;
	try{
//		writeLog(init,"INIT","DEBUG");
	}catch(Exception e){	
//		System.out.println("iHort : Log : Init "+e.toString());
	}
}

public static i_log_pattern_service patternFactory(String className){
	i_log_pattern_service element = null;
	if(className==null || className.equals("")) return new log_patternSimple_service();
	if(element==null){
		try{	
			element = (i_log_pattern_service)Class.forName(className).newInstance();
		}catch (Exception e) {
			element = new log_patternSimple_service();
		}
	}
	return element;
}

public synchronized i_log_pattern_service get_log_Pattern(){ 
	return patternFactory( init.get_LogPattern());
}	


public synchronized void writeLog(String msg,String level) throws IOException {
		i_log_pattern_service logPattern = patternFactory( init.get_LogPattern());
		if(logPattern==null) logPattern = new log_patternSimple_service();
		log_FileManager_service fm = new log_FileManager_service(init);
			fm.createFile(true,msg);
			fm.writeLineRecord(logPattern.prepare(msg,level));
		fm.closeFile();
}

public static synchronized void writeLog(log_init_service _init, String msg, String level) throws IOException {
	i_log_pattern_service logPattern = patternFactory( _init.get_LogPattern());
	if(logPattern==null) logPattern = new log_patternSimple_service();
	log_FileManager_service fm = new log_FileManager_service(_init);
		fm.createFile(true,logPattern.prepare(msg,level));
		fm.writeLineRecord(logPattern.prepare(msg,level));
	fm.closeFile();
}

public String get_log_Filename() throws IOException {
	log_FileManager_service fm = new log_FileManager_service(init);
	fm.createFile(true,"");
	fm.closeFile();
	return fm.getRealPathName();
}

public String get_log_Content(String lineSep) throws IOException {
	if(lineSep==null) lineSep = System.getProperty("line.separator");
	log_FileManager_service fm = new log_FileManager_service(init);
	fm.createFile(true,"");
	fm.readFile();
	BufferedReader br = fm.getBufferedReader();
	String content="";
	String thisLine;
	while ((thisLine = br.readLine()) != null) { 
		content+=thisLine+lineSep;
	} 	
	br.close();
	fm.closeFile();
	return content;
}
}



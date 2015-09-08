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

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class log_generator {
	private log_init init;
	
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

public synchronized i_log_pattern get_log_Pattern(){ 
	return patternFactory( init.get_LogPattern());
}	

public synchronized void writeLog(HttpServletRequest request, String msg, String level) throws IOException {
	i_log_pattern logPattern = patternFactory( init.get_LogPattern());
	if(logPattern==null) logPattern = new log_patternSimple();
	String log_mess = logPattern.prepare(request,msg,level);
	log_FileManager fm = new log_FileManager(init);
		fm.createFile(true,msg);
		fm.writeLineRecord(log_mess);
	fm.closeFile();
}

public synchronized void writeLog(String msg,String level) throws IOException {
		i_log_pattern logPattern = patternFactory( init.get_LogPattern());
		if(logPattern==null) logPattern = new log_patternSimple();
		log_FileManager fm = new log_FileManager(init);
			fm.createFile(true,msg);
			fm.writeLineRecord(logPattern.prepare(msg,level));
		fm.closeFile();
}

public static synchronized void writeLog(log_init _init, String msg, String level) throws IOException {
	i_log_pattern logPattern = patternFactory( _init.get_LogPattern());
	if(logPattern==null) logPattern = new log_patternSimple();
	log_FileManager fm = new log_FileManager(_init);
		fm.createFile(true,logPattern.prepare(msg,level));
		fm.writeLineRecord(logPattern.prepare(msg,level));
	fm.closeFile();
}

public String get_log_Filename() throws IOException {
	log_FileManager fm = new log_FileManager(init);
	fm.createFile(true,"");
	fm.closeFile();
	return fm.getRealPathName();
}

public String get_log_Content(String lineSep) throws IOException {
	if(lineSep==null) lineSep = System.getProperty("line.separator");
	log_FileManager fm = new log_FileManager(init);
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



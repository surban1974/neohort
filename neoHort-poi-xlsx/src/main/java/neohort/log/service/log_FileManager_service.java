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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import neohort.log.log_FilesFilter;
import neohort.util.util_sort;

public class log_FileManager_service {
	private FileOutputStream fileOutputStream; 	
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private String message;
	private long oldFlush;
	private log_init_service init;
	
	public class log_element{
		private String Path;
		private String Mod;
		
		log_element(String path, String mod){
			this.Path = path;
			this.Mod = mod;
		}
		public String getPath() {
			return Path;
		}
		public String getMod() {
			return Mod;
		}
		public void setPath(String string) {
			Path = string;
		}
		public void setSize(String string) {
			Mod = string;
		}
		public String toString(){
			return Path+"|"+Mod;
		}
	}
public log_FileManager_service(log_init_service init) {
	super();
    this.init = init;    
	oldFlush = -1;
}
public boolean closeFile(){
	boolean ok = true;
	if(bufferedWriter != null){
		try{
			bufferedWriter.close();
			fileOutputStream.close();
	    }catch(IOException e){
	    	ok = false;
	   }
	}
	if(bufferedReader != null){ 
		try{
			bufferedReader.close();
		}catch(IOException e){
			ok = false; 
		}
	}
    return ok;
}

public byte createFile(boolean appendfile, String message){
	this.message = message;
	String fileName = getRealPathName();
	try{
		fileOutputStream = new FileOutputStream(fileName, appendfile);
     }catch(IOException e){
		return 1; 
	 }
	 try{
		bufferedWriter = new BufferedWriter(new FileWriter(fileName, appendfile));  
	 }catch(IOException e){
		return 2; 
	}
	return 0; 
}

public static boolean delete(String fileToDelete){
	File file = new File(fileToDelete);
	try{
		if(!file.delete()) return false;
			System.out.println("Could not delete the file "+fileToDelete);
     	}catch(Exception e){
			System.out.println("Could not delete the file "+fileToDelete);
      	return false;
	 }
	return true;
}
 
public boolean readFile(){
	File filei;
	try{
		filei = new File(getRealPathName());   
     	bufferedReader = new BufferedReader(new FileReader(filei));
	}catch(IOException ioe){
		System.out.println("Could not read the file: "+init.get_LogPath()+init.get_LogName());
	return false;
	}
	return true;
}

public String readLine() throws IOException{
	try{
		return bufferedReader.readLine();
	}catch(IOException e){
		System.out.println("I/O error. Could not read the line from the file");
		throw e;
	}
}

public int resetFile(boolean appendFile){
	closeFile();
	try{
	  	fileOutputStream = new FileOutputStream(getRealPathName(), false);
    }catch(IOException e){
	  	return 1; //apertura/creazione fallita
	}
	try{
	  	bufferedWriter = new BufferedWriter(new FileWriter(getRealPathName(), appendFile));  
	}catch(IOException e){
	 	return 2; //impossibile preparare il file per la scrittura
	}
	return 0;
}

public boolean writeLineRecord(String record){
	try{
		bufferedWriter.write(record, 0, record.length());
	  	bufferedWriter.newLine();
  	  	bufferedWriter.flush();
	}catch(IOException e){
		System.out.println("Could not write the line "+record);
		return false; 
	}
	return true;
}
public boolean writeLineRecordFlush(String record){
	try{
		bufferedWriter.write(record, 0, record.length());
		bufferedWriter.newLine();
		if(init.get_LogFlashRate() == 0) bufferedWriter.flush();
		else{
       		long newFlush = (new Date()).getTime();
	   		if( (newFlush - oldFlush) >= init.get_LogFlashRate()*1000){
	    		bufferedWriter.flush();
	    		oldFlush = newFlush;
	   		}
		} 
	 }catch(IOException e){
		return false; 
	}
	return true;
}
public boolean writeRecord(String record){
	try{
		bufferedWriter.write(record, 0, record.length());
	}catch(IOException e){
		System.out.println("Could not write the line "+record);
		return false;
	}
	return true;
}

public String getRealPathName(){
	String pathLogsDirectory = init.get_LogPath()+init.get_LogName();
	File f=new File( init.get_LogPath());
	log_FilesFilter filter = new log_FilesFilter();
	File[] list = f.listFiles(filter);
	if(list==null || list.length == 0) return pathLogsDirectory + "_0.log";
	
	Vector vList = new Vector();
	for(int i=0;i<list.length;i++) vList.add(new log_element(list[i].getAbsolutePath(),String.valueOf(list[i].lastModified())));
	vList = new util_sort().sort(vList,"Mod","D");
	
	log_element maxEl = (log_element)vList.get(0);
	int index = -1;
	try{
		index = new Integer(maxEl.getPath().substring(maxEl.getPath().lastIndexOf("_")+1,maxEl.getPath().length()-4)).intValue();
	}catch(Exception e){}
	
	String fileName="";
	
	if((new File(maxEl.getPath()).length()+message.length())>init.get_LogMaxLength()){
		index++;
		if(vList.size()>=init.get_LogMaxFiles()){
			if(index>=init.get_LogMaxFiles()) index=0;
			delete(maxEl.getPath());
			fileName = pathLogsDirectory + "_"+(index)+".log";
			return fileName;
		}else{
			fileName = pathLogsDirectory + "_"+(index)+".log";
			return fileName;
		}
	}else return maxEl.getPath();
}

public log_init_service getInit() {
	return init;
}
public void setInit(log_init_service log_init) {
	init = log_init;
}
public BufferedReader getBufferedReader() {
	return bufferedReader;
}
public BufferedWriter getBufferedWriter() {
	return bufferedWriter;
}

}

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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Properties;

import neohort.util.util_format;

public class log_init_service implements Serializable{
	private static final long serialVersionUID = -1L;
	static public String id_LogStub =			"application.log.stub";
	static public String id_LogLevel =			"application.log.level";
		
	static public String id_LogPattern = 		"application.log.pattern";
	static public String id_LogPath = 			"application.log.path";
	static public String id_LogName = 			"application.log.name";
	static public String id_LogMaskName =		"application.log.maskname";
	static public String id_LogMaskFormat =		"application.log.maskformat";
	static public String id_LogMaxLength =		"application.log.maxlength";
	static public String id_LogMaxFiles =		"application.log.maxfiles";
	static public String id_LogFlashRate =		"application.log.flashrate";
	static public String id_LogFlashSize =		"application.log.flashsize";
	
	
	
	private String _LogPattern;
	private String _LogPath;
	private String _LogName;
	private String _LogMaskName;
	private String _LogMaskFormat;
	private String _LogMaxLength;
	private String _LogMaxFiles;
	private String _LogFlashRate;
	private String _LogFlashSize;
	private String _LogStub;
	private String _LogLevel;
	

public  log_init_service() {
	super();
}	

public void reimposta(){
	_LogPattern=			"neohort.log.service.log_patternSimple_service";
	_LogPath=				"iHort.logs/";
	_LogName=				"iHort";
	_LogMaskName=			"application";
	_LogMaskFormat=			"yyyyMMdd_hhmmssss";
	_LogMaxLength=			"1000000";
	_LogMaxFiles=			"10";
	_LogFlashRate=			"0";
	_LogFlashSize=			"1024";
	_LogLevel=				"DEBUG";	
}
public void init() {
	_LogStub = (_LogStub==null)?System.getProperty(id_LogStub):_LogStub;
	_LogLevel = (_LogLevel==null)?System.getProperty(id_LogLevel):"DEBUG";
	_LogPath = (_LogPath==null)?System.getProperty(id_LogPath):_LogPath;
	_LogMaskName = (_LogMaskName==null)?((System.getProperty(id_LogMaskName)==null)?"application":System.getProperty(id_LogMaskName)):_LogMaskName;
	_LogMaskFormat = (_LogMaskFormat==null)?((System.getProperty(id_LogMaskFormat)==null)?"yyyyMMdd_hhmmssss":System.getProperty(id_LogMaskFormat)):_LogMaskFormat;
	_LogName = (_LogName==null)?System.getProperty(id_LogName):_LogName;
	_LogMaxLength = (_LogMaxLength==null)?((System.getProperty(id_LogMaxLength)==null)?"1000000":System.getProperty(id_LogMaxLength)):_LogMaxLength;
	_LogMaxFiles = (_LogMaxFiles==null)?((System.getProperty(id_LogMaxFiles)==null)?"10":System.getProperty(id_LogMaxFiles)):_LogMaxFiles;
	_LogFlashSize = (_LogFlashSize==null)?((System.getProperty(id_LogFlashSize)==null)?"1024":System.getProperty(id_LogFlashSize)):_LogFlashSize;
	_LogFlashRate = (_LogFlashRate==null)?((System.getProperty(id_LogFlashRate)==null)?"0":System.getProperty(id_LogFlashRate)):_LogFlashRate;
	_LogPattern = (_LogPattern==null)?((System.getProperty(id_LogPattern)==null)?"neohort.log.service.log_patternSimple_service":System.getProperty(id_LogPattern)):_LogPattern;
	if(_LogPath==null || _LogPath.equals("")) return;
	if(_LogName==null || _LogName.equals("")){
		if(_LogMaskFormat==null || _LogMaskFormat.equals("")) _LogMaskFormat = "yyyyMMdd_hhmmssss";
		if(_LogMaskName==null) _LogMaskName = "application";
		_LogName = _LogMaskName + util_format.dataToString(Calendar.getInstance().getTime(),_LogMaskFormat);
	}	
}

public void init(Properties ex_property) {
	_LogPath = (_LogPath==null)?ex_property.getProperty(id_LogPath):_LogPath;
	_LogMaskName = (_LogMaskName==null)?((ex_property.getProperty(id_LogMaskName)==null)?"application":ex_property.getProperty(id_LogMaskName)):_LogMaskName;
	_LogMaskFormat = (_LogMaskFormat==null)?((ex_property.getProperty(id_LogMaskFormat)==null)?"yyyyMMdd_hhmmssss":ex_property.getProperty(id_LogMaskFormat)):_LogMaskFormat;
	_LogName = (_LogName==null)?ex_property.getProperty(id_LogName):_LogName;
	_LogMaxLength = (_LogMaxLength==null)?((ex_property.getProperty(id_LogMaxLength)==null)?"1000000":ex_property.getProperty(id_LogMaxLength)):_LogMaxLength;
	_LogFlashSize = (_LogFlashSize==null)?((ex_property.getProperty(id_LogFlashSize)==null)?"1024":ex_property.getProperty(id_LogFlashSize)):_LogFlashSize;
	_LogFlashRate = (_LogFlashRate==null)?((ex_property.getProperty(id_LogFlashRate)==null)?"0":ex_property.getProperty(id_LogFlashRate)):_LogFlashRate;
	_LogPattern = (_LogPattern==null)?((ex_property.getProperty(id_LogPattern)==null)?"neohort.log.service.log_patternSimple_service":ex_property.getProperty(id_LogPattern)):_LogPattern;
	if(_LogPath==null || _LogPath.equals("")) return;
	if(_LogName==null || _LogName.equals("")){
		if(_LogMaskFormat==null || _LogMaskFormat.equals("")) _LogMaskFormat = "yyyyMMdd_hhmmssss";
		if(_LogMaskName==null) _LogMaskName = "application";
		_LogName = _LogMaskName + util_format.dataToString(Calendar.getInstance().getTime(),_LogMaskFormat);
	}	
}


public int get_LogFlashRate() {
	try{
		return new Integer(_LogFlashRate).intValue();
	}catch(Exception e){
		return 0;
	}
}
public int get_LogFlashSize() {
	try{
		return new Integer(_LogFlashSize).intValue();
	}catch(Exception e){
		return 1024;
	}
}
public String get_LogMaskFormat() {
	return _LogMaskFormat;
}
public String get_LogMaskName() {
	return _LogMaskName;
}
public long get_LogMaxLength() {
	try{
		return new Long(_LogMaxLength).longValue();
	}catch(Exception e){
		return 1000000;
	}
}
public String get_LogName() {
	return _LogName;
}
public String get_LogPath() {
	return _LogPath;
}
public String get_LogPattern() {
	return _LogPattern;
}
public void set_LogFlashRate(String string) {
	_LogFlashRate = string;
}
public void set_LogFlashSize(String string) {
	_LogFlashSize = string;
}
public void set_LogMaskFormat(String string) {
	_LogMaskFormat = string;
}
public void set_LogMaskName(String string) {
	_LogMaskName = string;
}
public void set_LogMaxLength(String string) {
	_LogMaxLength = string;
}
public void set_LogName(String string) {
	_LogName = string;
}
public void set_LogPath(String string) {
	_LogPath = string;
}
public void set_LogPattern(String string) {
	_LogPattern = string;
}
public int get_LogMaxFiles() {
	try{
		return new Integer(_LogMaxFiles).intValue();
	}catch(Exception e){
		return 1;
	}
	
}
public void set_LogMaxFiles(String string) {
	_LogMaxFiles = string;
}

	public static String getId_LogFlashRate() {
		return id_LogFlashRate;
	}

	public static String getId_LogFlashSize() {
		return id_LogFlashSize;
	}

	public static String getId_LogLevel() {
		return id_LogLevel;
	}

	public static String getId_LogMaskFormat() {
		return id_LogMaskFormat;
	}

	public static String getId_LogMaskName() {
		return id_LogMaskName;
	}

	public static String getId_LogMaxFiles() {
		return id_LogMaxFiles;
	}

	public static String getId_LogMaxLength() {
		return id_LogMaxLength;
	}

	public static String getId_LogName() {
		return id_LogName;
	}

	public static String getId_LogPath() {
		return id_LogPath;
	}

	public static String getId_LogPattern() {
		return id_LogPattern;
	}

	public static String getId_LogStub() {
		return id_LogStub;
	}

	public String get_LogLevel() {
		return _LogLevel;
	}

	public String get_LogStub() {
		return _LogStub;
	}

	public static void setId_LogFlashRate(String string) {
		id_LogFlashRate = string;
	}

	public static void setId_LogFlashSize(String string) {
		id_LogFlashSize = string;
	}

	public static void setId_LogLevel(String string) {
		id_LogLevel = string;
	}

	public static void setId_LogMaskFormat(String string) {
		id_LogMaskFormat = string;
	}

	public static void setId_LogMaskName(String string) {
		id_LogMaskName = string;
	}

	public static void setId_LogMaxFiles(String string) {
		id_LogMaxFiles = string;
	}

	public static void setId_LogMaxLength(String string) {
		id_LogMaxLength = string;
	}

	public static void setId_LogName(String string) {
		id_LogName = string;
	}

	public static void setId_LogPath(String string) {
		id_LogPath = string;
	}

	public static void setId_LogPattern(String string) {
		id_LogPattern = string;
	}

	public static void setId_LogStub(String string) {
		id_LogStub = string;
	}

	public void set_LogLevel(String string) {
		_LogLevel = string;
	}

	public void set_LogStub(String string) {
		_LogStub = string;
	}

}

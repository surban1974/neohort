/**
* Creation date: (22/12/2005)
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

 package neohort.universal.output.lib_xls;

import java.util.Hashtable;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;


public class sheet extends element{
	private static final long serialVersionUID = 4161088494447853191L;
	WritableSheet document;
	WritableWorkbook writer;

	private String SHEET_NUMBER="";
	private String SHEET_NAME="";
	
public sheet() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
			document = (WritableSheet)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());				
			bean _sysPdfCR = new bean();
					_sysPdfCR.setContent(new Integer("0"));
					_sysPdfCR.setName("SYSTEM");
					_sysPdfCR.setID(iConst.iHORT_SYSTEM_CurrentROW);
					_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR); 

			bean _sysPdfCC = new bean();
					_sysPdfCC.setContent(new Integer("0"));
					_sysPdfCC.setName("SYSTEM");
					_sysPdfCC.setID(iConst.iHORT_SYSTEM_CurrentCELL);
					_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);
			if(!getSHEET_NUMBER().equals("")){
				try{
					int current_sheet = new Integer(SHEET_NUMBER).intValue();
					writer = (WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
					try{
						document = writer.getSheet(current_sheet);
					}catch(Exception ex){
							 
						if(writer.getSheets().length>current_sheet) document = writer.getSheet(current_sheet);
						else{
							int len = writer.getSheets().length;
							for(int l=len;l<=current_sheet;l++)  document = writer.createSheet("Sheet "+l,l);
						}
					}
					bean _sysDocument = new bean();
							_sysDocument.setContent(document);
							_sysDocument.setName("SYSTEM");
							_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
							_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);
						
				}catch(Exception e){
					setError(e,iStub.log_ERROR);	
				}
			}
			try{
				if(!getSHEET_NAME().equals("")) document.setName(getSHEET_NAME());
			}catch(Exception e){
				setError(e,iStub.log_WARN);
			}

	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{
			if(_tagLibrary.get(getName()+":"+getID())==null)
				_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
			else _tagLibrary.remove(getName()+":"+getID());
		}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}

}
public void reimposta() {
	setName("SHEET");
	STYLE_ID = "";
	SHEET_NUMBER = "";
	SHEET_NAME = "";
}
public void setCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{
			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(document);
		}
	}catch(Exception e){}
}
	public String getSHEET_NUMBER() {
		return SHEET_NUMBER;
	}

	public void setSHEET_NUMBER(String string) {
		SHEET_NUMBER = string;
	}

	public String getSHEET_NAME() {
		return SHEET_NAME;
	}

	public void setSHEET_NAME(String string) {
		SHEET_NAME = string;
	}

}

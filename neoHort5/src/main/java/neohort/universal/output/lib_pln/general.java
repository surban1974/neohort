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

package neohort.universal.output.lib_pln;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_pln.general_util.general_j2ee;
import neohort.universal.output.util.OutputRunTime;
import neohort.universal.output.util.OutputRunTimeService;

public class general extends element{

	private static final long serialVersionUID = 229981380741589124L;
	public Object writer;
	public Hashtable _beanLibrary;
	private java.lang.String TYPE_DOCUMENT;
	private java.lang.String SOURCE_DOCUMENT;
	private java.lang.String SOURCE_AFTER_FIXED;
	private java.lang.String SOURCE_BEFORE_FIXED;
	private java.lang.String SOURCE_ERROR_FIXED;
	private java.lang.String CONTENT_DISPOSITION;
	private java.lang.String CONTENT_TYPE;
	private java.lang.String CONTENT_TRANSFER_ENCODING;
	private java.lang.String LIB;
public general() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	if(motore instanceof OutputRunTime){
		general_j2ee.executeFirst(this, _tagLibrary, _beanLibrary);
	}

	
	if(motore instanceof OutputRunTimeService){		
		try{
			Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
			Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

			if(included!=null && included.booleanValue()==true){}
			else{
		
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && getSOURCE_DOCUMENT()!=null){
						writer = new java.io.DataOutputStream(new BufferedOutputStream(new FileOutputStream(getSOURCE_DOCUMENT(),false)));
		
				}
				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
					if(!noGenerate.booleanValue()) writer = new java.io.DataOutputStream((OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent()));

		
				if(writer!=null){
					this._beanLibrary = _beanLibrary;
				}
		
		
				bean _sysDocument = new bean();
						_sysDocument.setContent(new document());
						_sysDocument.setName("SYSTEM");
						_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
						_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);
		
				bean _sysPdfWriter = new bean();
						_sysPdfWriter.setContent(writer);
						_sysPdfWriter.setName("SYSTEM");
						_sysPdfWriter.setID(iConst.iHORT_SYSTEM_Writer);
						_beanLibrary.put(_sysPdfWriter.getName()+":"+_sysPdfWriter.getID(),_sysPdfWriter);
		
				bean _sysPdfPN = new bean();
						_sysPdfPN.setContent(new Integer("0"));
						_sysPdfPN.setName("SYSTEM");
						_sysPdfPN.setID(iConst.iHORT_SYSTEM_Document_PageNumber);
						_beanLibrary.put(_sysPdfPN.getName()+":"+_sysPdfPN.getID(),_sysPdfPN);
		
						((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes("");
		
			}
		}catch(Exception e){
			setError(e);
		}
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	if(motore instanceof OutputRunTime){
		general_j2ee.executeLast(this, _tagLibrary, _beanLibrary);
	}

	
	if(motore instanceof OutputRunTimeService){	
		try{
			Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
			Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

			if(included!=null && included.booleanValue()==true){}
			else{
				((OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write(this._content.getBytes());

				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if(!noGenerate.booleanValue()) ((OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
					if (this.motore.getClass().getName().indexOf("PLNRunThread") > -1) {
						try{
							java.io.File fin = new java.io.File(this.SOURCE_DOCUMENT+"finished");
							fin.createNewFile();
						}catch(Exception e){}
					}
				}
				_tagLibrary = new Hashtable();
				_beanLibrary = new Hashtable();
		
			}
		}catch(Exception e){
			setError(e);
		}
	}
}
public java.lang.String getCONTENT_DISPOSITION() {
	return CONTENT_DISPOSITION;
}
public java.lang.String getCONTENT_TRANSFER_ENCODING() {
	return CONTENT_TRANSFER_ENCODING;
}
public java.lang.String getCONTENT_TYPE() {
	return CONTENT_TYPE;
}
public java.lang.String getSOURCE_AFTER_FIXED() {
	return SOURCE_AFTER_FIXED;
}
public java.lang.String getSOURCE_BEFORE_FIXED() {
	return SOURCE_BEFORE_FIXED;
}
public java.lang.String getSOURCE_DOCUMENT() {
	return SOURCE_DOCUMENT;
}
public java.lang.String getSOURCE_ERROR_FIXED() {
	return SOURCE_ERROR_FIXED;
}
public java.lang.String getTYPE_DOCUMENT() {
	return TYPE_DOCUMENT;
}
public void reimposta() {
	setName("GENERAL");
	TYPE_DOCUMENT = "";
	SOURCE_DOCUMENT = "";
	SOURCE_BEFORE_FIXED ="";
	SOURCE_AFTER_FIXED ="";
	SOURCE_ERROR_FIXED ="";
	CONTENT_DISPOSITION ="";
	CONTENT_TYPE ="";
	CONTENT_TRANSFER_ENCODING = "";
	LIB="pln";
}
public void setCONTENT_DISPOSITION(java.lang.String newCONTENT_DISPOSITION) {
	CONTENT_DISPOSITION = newCONTENT_DISPOSITION;
}
public void setCONTENT_TRANSFER_ENCODING(java.lang.String newCONTENT_TRANSFER_ENCODING) {
	CONTENT_TRANSFER_ENCODING = newCONTENT_TRANSFER_ENCODING;
}
public void setCONTENT_TYPE(java.lang.String newCONTENT_TYPE) {
	CONTENT_TYPE = newCONTENT_TYPE;
}
public void setError(Exception e) {
	if(motore instanceof OutputRunTime){
		general_j2ee.setError(this, e);
	}

	
	if(motore instanceof OutputRunTimeService){		
		try {
			isError = true;
			if(e.toString().indexOf("java.io.IOException")>-1){
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if(!getSOURCE_ERROR_FIXED().equals("")){
						try{
							((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
						}catch (Exception ex) {}
					}
					if (this.motore.getClass().getName().indexOf("PLNRunThread") > -1) {
						try{
							java.io.File fin = new java.io.File(this.SOURCE_DOCUMENT+"error");
							fin.createNewFile();
						}catch(Exception ex){}
					}
				}
	
			}else{
				motore.setError(e,this.getName(),iStub.log_ERROR);
			}
		} catch (Exception ex) {
			ex.toString();
		}
	}
}
public void setSOURCE_AFTER_FIXED(java.lang.String newSOURCE_AFTER_FIXED) {
	SOURCE_AFTER_FIXED = newSOURCE_AFTER_FIXED;
}
public void setSOURCE_BEFORE_FIXED(java.lang.String newSOURCE_BEFOR_FIXED) {
	SOURCE_BEFORE_FIXED = newSOURCE_BEFOR_FIXED;
}
public void setSOURCE_DOCUMENT(java.lang.String newSOURCE_DOCUMENT) {
	SOURCE_DOCUMENT = newSOURCE_DOCUMENT;
}
public void setSOURCE_ERROR_FIXED(java.lang.String newSOURCE_ERROR_FIXED) {
	SOURCE_ERROR_FIXED = newSOURCE_ERROR_FIXED;
}
public void setTYPE_DOCUMENT(java.lang.String newTYPE_DOCUMENT) {
	TYPE_DOCUMENT = newTYPE_DOCUMENT;
}
	public java.lang.String getLIB() {
		return LIB;
	}

	public void setLIB(java.lang.String string) {
		LIB = string;
	}

}

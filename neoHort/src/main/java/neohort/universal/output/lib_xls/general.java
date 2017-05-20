/**
* Creation date: (19/12/2005)
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

/*
* ENCODED = "iso-8859-1" or "cp1252" or ...
*/

package neohort.universal.output.lib_xls;


import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.PageOrientation;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_xls.general_util.general_j2ee;
import neohort.universal.output.util.I_StreamWrapper;
import neohort.universal.output.util.OutputRunTime;
import neohort.universal.output.util.OutputRunTimeService;


public class general extends element{
	private static final long serialVersionUID = 3788220213800261200L;

	public Hashtable _beanLibrary;
	private String TYPE_DOCUMENT;
	private String SOURCE_DOCUMENT;
	private String CLASS_STREAM_WRAPPER;
	private String ORIENTATION;
	private String MARGINS;
	private String SOURCE_AFTER_FIXED;
	private String SOURCE_BEFORE_FIXED;
	private String SOURCE_ERROR_FIXED;
	private String TEMPLATE;
	private String LIB;
	private String ENCODED;
	private String TEMPORARYFILEDURINGWRITE;
	private String TEMPORARYFILEDURINGWRITEDIRECTORY;
	private String ARRAYGROWSIZE;


public general() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	WritableSheet document = null;
	WritableWorkbook writer = null;
	WorkbookSettings settings = null;

	if(motore instanceof OutputRunTime){
		general_j2ee.executeFirst(this, _tagLibrary, _beanLibrary);
	}


	if(motore instanceof OutputRunTimeService){
		try{
			Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
			Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

			OutputStream oStream = null;
			if(included!=null && included.booleanValue()==true){}
			else{
				Workbook workbook = null;
				I_StreamWrapper iStreamWrapper = null;
				if(getSOURCE_DOCUMENT()==null) setSOURCE_DOCUMENT("");
				if(getARRAYGROWSIZE()==null) setARRAYGROWSIZE("");
				if(getTEMPORARYFILEDURINGWRITE()==null) setTEMPORARYFILEDURINGWRITE("");
				if(getCLASS_STREAM_WRAPPER()==null) setCLASS_STREAM_WRAPPER("");
				else if(!getCLASS_STREAM_WRAPPER().equals("")){
					try{
						iStreamWrapper = (I_StreamWrapper)Class.forName(getCLASS_STREAM_WRAPPER()).newInstance();
					}catch(Exception e){
						setError(e);
					}
				}


				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
					oStream = (OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent());


				if(getTEMPORARYFILEDURINGWRITE()!=null && getTEMPORARYFILEDURINGWRITE().equalsIgnoreCase("true")){
					if(settings==null)
						settings = new WorkbookSettings();
					settings.setUseTemporaryFileDuringWrite(true);
					if(getTEMPORARYFILEDURINGWRITEDIRECTORY()!=null && !getTEMPORARYFILEDURINGWRITEDIRECTORY().equals(""))
						settings.setTemporaryFileDuringWriteDirectory(new File(getTEMPORARYFILEDURINGWRITEDIRECTORY()));
				}
				if(getARRAYGROWSIZE()!=null && !getARRAYGROWSIZE().equals("")){
					if(settings==null)
						settings = new WorkbookSettings();
					try{
						settings.setArrayGrowSize(Integer.valueOf(getARRAYGROWSIZE()));
					}catch(Exception e){

					}
				}


				if(getTEMPLATE()!=null && !getTEMPLATE().equals("")){


					String path = getTEMPLATE();
					try{
						workbook = Workbook.getWorkbook(new File(path));
					}catch(Exception e){
						setError(e,iStub.log_WARN);
					}
					if(workbook==null){
						try{
							workbook = Workbook.getWorkbook(new URL(path).openStream());
						}catch(Exception e){
							setError(e,iStub.log_ERROR);
						}
					}

					if(ENCODED!=null){
						if(settings==null)
							settings = new WorkbookSettings();
						settings.setEncoding(ENCODED);

					}

					if(workbook!=null){
						if (getTYPE_DOCUMENT()!=null && !getSOURCE_DOCUMENT().equals("")){
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()),workbook);
								else writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()),workbook,settings);
							}
						}else if (getTYPE_DOCUMENT()!=null && iStreamWrapper!=null){
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook);
								else writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook,settings);
							}
						}else{
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(oStream,workbook);
								else writer = Workbook.createWorkbook(oStream,workbook,settings);
							}
						}
						try{
							document = writer.getSheet(0);
						}catch(Exception e){
							document = writer.createSheet("Sheet 0", 0);
						}
					}else{
						if (getTYPE_DOCUMENT()!=null && !getSOURCE_DOCUMENT().equals("")){
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()));
								else writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()),settings);
							}
						}else if (getTYPE_DOCUMENT()!=null && iStreamWrapper!=null){
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary));
								else writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),settings);
							}
						}else{
							if(!noGenerate.booleanValue()){
								if(settings==null) writer = Workbook.createWorkbook(oStream);
								else writer = Workbook.createWorkbook(oStream,settings);
							}
						}

						document = writer.createSheet("Sheet 0", 0);
					}
					bean _sysXlsT = new bean();
						_sysXlsT.setContent(new Boolean(true));
						_sysXlsT.setName("SYSTEM");
						_sysXlsT.setID(iConst.iHORT_SYSTEM_Excel_template_present);
						_beanLibrary.put(_sysXlsT.getName()+":"+_sysXlsT.getID(),_sysXlsT);
				}
				else{
					if(!noGenerate.booleanValue()){
						if (getTYPE_DOCUMENT()!=null && !getSOURCE_DOCUMENT().equals("")){
							if(settings==null) writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()));
							else writer = Workbook.createWorkbook(new File(getSOURCE_DOCUMENT()),settings);
						}else{
							if(settings==null) writer = Workbook.createWorkbook(oStream);
							else  writer = Workbook.createWorkbook(oStream,settings);
						}
					}
					document = writer.createSheet("Sheet 0", 0);

					if(getORIENTATION()!=null && getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
						document.getSettings().setOrientation(PageOrientation.LANDSCAPE);
					else document.getSettings().setOrientation(PageOrientation.PORTRAIT);


					try{
						java.util.StringTokenizer st = new java.util.StringTokenizer(getMARGINS(), ",");

						document.getSettings().setLeftMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
						document.getSettings().setRightMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
						document.getSettings().setTopMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
						document.getSettings().setBottomMargin(Double.valueOf(st.nextToken()).doubleValue()/96);


					}catch(Exception e){
					}
					bean _sysXlsT = new bean();
						_sysXlsT.setContent(new Boolean(false));
						_sysXlsT.setName("SYSTEM");
						_sysXlsT.setID(iConst.iHORT_SYSTEM_Excel_template_present);
						_beanLibrary.put(_sysXlsT.getName()+":"+_sysXlsT.getID(),_sysXlsT);

				}

				bean _sysPdfWorkbook = new bean();
					_sysPdfWorkbook.setContent(workbook);
					_sysPdfWorkbook.setName("SYSTEM");
					_sysPdfWorkbook.setID(iConst.iHORT_SYSTEM_Workbook);
					_beanLibrary.put(_sysPdfWorkbook.getName()+":"+_sysPdfWorkbook.getID(),_sysPdfWorkbook);

				bean _sysPdfWriter = new bean();
						_sysPdfWriter.setContent(writer);
						_sysPdfWriter.setName("SYSTEM");
						_sysPdfWriter.setID(iConst.iHORT_SYSTEM_Writer);
						_beanLibrary.put(_sysPdfWriter.getName()+":"+_sysPdfWriter.getID(),_sysPdfWriter);

				bean _sysDocument = new bean();
						_sysDocument.setContent(document);
						_sysDocument.setName("SYSTEM");
						_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
						_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);

				bean _sysPdfPN = new bean();
						_sysPdfPN.setContent(new Integer("0"));
						_sysPdfPN.setName("SYSTEM");
						_sysPdfPN.setID(iConst.iHORT_SYSTEM_Document_PageNumber);
						_beanLibrary.put(_sysPdfPN.getName()+":"+_sysPdfPN.getID(),_sysPdfPN);


				if(iStreamWrapper!=null){
					bean _sysSW = new bean();
					_sysSW.setContent(iStreamWrapper);
					_sysSW.setName("SYSTEM");
					_sysSW.setID(iConst.iHORT_SYSTEM_STREAM_WRITER);
					_beanLibrary.put(_sysSW.getName()+":"+_sysSW.getID(),_sysSW);
				}
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
				((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write();
				if(!noGenerate.booleanValue()){
					((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				}
				if (getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if (this.motore.getClass().getName().indexOf("RunThread") > -1) {
						try{
							java.io.File fin = new java.io.File(this.SOURCE_DOCUMENT+"finished");
							fin.createNewFile();
						}catch(Exception e){}
					}
				}


				try{
					Workbook workbook = (Workbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Workbook)).getContent());
					if(workbook!=null)
						workbook.close();
				}catch(Exception e){
					e.toString();
				}

				_tagLibrary = new Hashtable();
				_beanLibrary = new Hashtable();

			}
		}catch(Exception e){
			setError(e);
		}
	}
}
public String getMARGINS() {
	return MARGINS;
}
public String getORIENTATION() {
	return ORIENTATION;
}
public String getSOURCE_AFTER_FIXED() {
	return SOURCE_AFTER_FIXED;
}
public String getSOURCE_BEFORE_FIXED() {
	return SOURCE_BEFORE_FIXED;
}
public String getSOURCE_DOCUMENT() {
	return SOURCE_DOCUMENT;
}
public String getSOURCE_ERROR_FIXED() {
	return SOURCE_ERROR_FIXED;
}
public String getTYPE_DOCUMENT() {
	return TYPE_DOCUMENT;
}
public void reimposta() {
	setName("GENERAL");
	TYPE_DOCUMENT = "attachment";
	SOURCE_DOCUMENT = "";
	CLASS_STREAM_WRAPPER = "";
	SOURCE_BEFORE_FIXED ="";
	SOURCE_AFTER_FIXED ="";
	SOURCE_ERROR_FIXED ="";
	ORIENTATION = "";
	MARGINS = "";
	LIB="xls";
	TEMPLATE="";
	ENCODED="";
	TEMPORARYFILEDURINGWRITE="";
	TEMPORARYFILEDURINGWRITEDIRECTORY="";
	ARRAYGROWSIZE="";
}
public void setError(Exception e) {
	if(motore instanceof OutputRunTime){
		general_j2ee.setError(this,e);
	}


	if(motore instanceof OutputRunTimeService){
		try {
			isError = true;
			if(e.toString().indexOf("java.io.IOException")>-1){
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if(!getSOURCE_ERROR_FIXED().equals("")){
						try{
							((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write();
							((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
						}catch (Exception ex) {}
					}
					if (this.motore.getClass().getName().indexOf("RunThread") > -1) {
						try{
							java.io.File fin = new java.io.File(this.SOURCE_DOCUMENT+"error");
							fin.createNewFile();
						}catch(Exception ex){}
					}
				}

			}else{
				((iHort)motore).setError(e,this.getName(),iStub.log_ERROR);
			}
		} catch (Exception ex) {
			ex.toString();
		}
	}
}

public void setMARGINS(String newMARGINS) {
	MARGINS = newMARGINS;
}
public void setORIENTATION(String newORINENTATION) {
	ORIENTATION = newORINENTATION;
}
public void setSOURCE_AFTER_FIXED(String newSOURCE_AFTER_FIXED) {
	SOURCE_AFTER_FIXED = newSOURCE_AFTER_FIXED;
}
public void setSOURCE_BEFORE_FIXED(String newSOURCE_BEFOR_FIXED) {
	SOURCE_BEFORE_FIXED = newSOURCE_BEFOR_FIXED;
}
public void setSOURCE_DOCUMENT(String newSOURCE_DOCUMENT) {
	SOURCE_DOCUMENT = newSOURCE_DOCUMENT;
}
public void setSOURCE_ERROR_FIXED(String newSOURCE_ERROR_FIXED) {
	SOURCE_ERROR_FIXED = newSOURCE_ERROR_FIXED;
}
public void setTYPE_DOCUMENT(String newTYPE_DOCUMENT) {
	if(newTYPE_DOCUMENT!=null && !newTYPE_DOCUMENT.trim().equals(""))
		TYPE_DOCUMENT = newTYPE_DOCUMENT;

}

public String getLIB() {
	return LIB;
}
public void setLIB(String newLIB) {
	LIB = newLIB;
}
public String getTEMPLATE() {
	return TEMPLATE;
}

public void setTEMPLATE(String string) {
	TEMPLATE = string;
}

public String getENCODED() {
	return ENCODED;
}

public void setENCODED(String string) {
	ENCODED = string;
}
public String getCLASS_STREAM_WRAPPER() {
	return CLASS_STREAM_WRAPPER;
}
public void setCLASS_STREAM_WRAPPER(String cLASS_STREAM_WRAPPER) {
	CLASS_STREAM_WRAPPER = cLASS_STREAM_WRAPPER;
}
public String getTEMPORARYFILEDURINGWRITE() {
	return TEMPORARYFILEDURINGWRITE;
}
public void setTEMPORARYFILEDURINGWRITE(String tEMPORARYFILEDURINGWRITE) {
	TEMPORARYFILEDURINGWRITE = tEMPORARYFILEDURINGWRITE;
}
public String getARRAYGROWSIZE() {
	return ARRAYGROWSIZE;
}
public void setARRAYGROWSIZE(String aRRAYGROWSIZE) {
	ARRAYGROWSIZE = aRRAYGROWSIZE;
}
public String getTEMPORARYFILEDURINGWRITEDIRECTORY() {
	return TEMPORARYFILEDURINGWRITEDIRECTORY;
}
public void setTEMPORARYFILEDURINGWRITEDIRECTORY(String tEMPORARYFILEDURINGWRITEDIRECTORY) {
	TEMPORARYFILEDURINGWRITEDIRECTORY = tEMPORARYFILEDURINGWRITEDIRECTORY;
}

}

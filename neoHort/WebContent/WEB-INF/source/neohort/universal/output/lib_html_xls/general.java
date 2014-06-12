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

package neohort.universal.output.lib_html_xls;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_html_xls.general_util.general_j2ee;
import neohort.universal.output.util.OutputRunTime;
import neohort.universal.output.util.OutputRunTimeService;

public class general extends element{
	private static final long serialVersionUID = 4150104211154836519L;
	public Object writer;
	public Hashtable _beanLibrary;

	public document document;
	private java.lang.String TYPE_DOCUMENT;
	private java.lang.String SOURCE_DOCUMENT;
	private java.lang.String ORIENTATION;
	private java.lang.String TYPE_RESPONSE;
	private java.lang.String SOURCE_AFTER_FIXED;
	private java.lang.String SOURCE_BEFORE_FIXED;
	public java.lang.String SOURCE_ERROR_FIXED;
	private java.lang.String LIB;
public general() {
	super();
}
public String _meta() {
	return
		"<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">"+_separator()+
		"<meta name=ProgId content=Excel.Sheet>"+_separator()+
		"<meta name=Generator content=\"Microsoft Excel 9\">";
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
	
			this._header = "<HTML xmlns:o=\"urn:schemas-microsoft-com:office:office\""+_separator()+
							"xmlns:x=\"urn:schemas-microsoft-com:office:excel\""+_separator()+
							"xmlns=\"http://www.w3.org/TR/REC-html40\">"+_separator();
			this._header+="<HEAD>"+_separator();
			this._header+=_meta();
			this._header+="</HEAD>"+_separator()+"<BODY>"+_separator();
	
	
			((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
	
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
				this._footer = "</BODY>"+_separator()+"</HTML>"+_separator();
				document = (document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
				prepareComment(_tagLibrary,_beanLibrary);
				if(!noGenerate.booleanValue()) ((OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write((this._comment+this._content+this._footer).getBytes());
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if(!noGenerate.booleanValue()) ((OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
					if (this.motore.getClass().getName().indexOf("XLSRunThread") > -1) {
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
public java.lang.String getORIENTATION() {
	return ORIENTATION;
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
public java.lang.String getTYPE_RESPONSE() {
	return TYPE_RESPONSE;
}
public void prepareComment(Hashtable _tagLibrary, Hashtable _beanLibrary) {

	this._comment+="<style>"+_separator();
	this._comment+="<!--table"+_separator();
	this._comment+="	{mso-displayed-decimal-separator:\"\\,\";"+_separator();
	this._comment+="	mso-displayed-thousand-separator:\"\\.\";}"+_separator();
	this._comment+="@page"+_separator();
	this._comment+="	{";
	page_footer pf = new page_footer();
	try{
		pf = (page_footer)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageFooter)).getContent();
	}catch(Exception e){
		pf = null;
	}
	if(pf!=null){
		this._comment+="	mso-footer-data:\""+pf._header+pf._content+"\";"+_separator();
		this._comment+="	margin:.5in .5in 1.0in .5in;"+_separator();
		this._comment+="	mso-header-margin:.5in;"+_separator();
		this._comment+="	mso-footer-margin:.5in;"+_separator();
	}else{
		this._comment+="	margin:.5in .5in .5in .5in;"+_separator();
		this._comment+="	mso-header-margin:.5in;"+_separator();
		this._comment+="	mso-footer-margin:.5in;"+_separator();
	}

	String orientation = (getORIENTATION().trim().toLowerCase().equals("landscape"))?"landscape":"portrait";
		this._comment+="	mso-page-orientation:"+orientation+";"+_separator();

	this._comment+="	mso-horizontal-page-align:center;}"+_separator();
	this._comment+="-->"+_separator();
	this._comment+="</style>"+_separator();


	this._comment+="<!--[if gte mso 9]>"+_separator();
	this._comment+="<xml>"+_separator();
	this._comment+="<x:ExcelWorkbook>"+_separator();
	this._comment+="	<x:ExcelWorksheets>"+_separator();
	this._comment+="		<x:ExcelWorksheet>"+_separator();
	this._comment+="			<x:Name>Rapporto</x:Name>"+_separator();
	this._comment+="			<x:WorksheetOptions>"+_separator();
	this._comment+="				<x:Print>"+_separator();
	this._comment+="					<x:ValidPrinterInfo/>"+_separator();
	this._comment+="					<x:PaperSizeIndex>9</x:PaperSizeIndex>"+_separator();
	this._comment+="					<x:HorizontalResolution>300</x:HorizontalResolution>"+_separator();
	this._comment+="					<x:VerticalResolution>300</x:VerticalResolution>"+_separator();
	this._comment+="				</x:Print>"+_separator();
//	this._comment+="				<x:PageBreakZoom>60</x:PageBreakZoom>"+_separator();
	this._comment+="				<x:Selected/>"+_separator();
	this._comment+="				<x:DoNotDisplayGridlines/>"+_separator();
	this._comment+="				<x:ProtectContents>False</x:ProtectContents>"+_separator();
	this._comment+="				<x:ProtectObjects>False</x:ProtectObjects>"+_separator();
	this._comment+="				<x:ProtectScenarios>False</x:ProtectScenarios>"+_separator();
	this._comment+="			</x:WorksheetOptions>"+_separator();

	Vector pb =null;
	try{
		pb= ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageBreak)).getContent()));
	}catch(Exception e){}	
	
	if(pb==null || pb.size()==0){
	}else{
		this._comment+="	    	<x:PageBreaks>"+_separator();
		this._comment+="	    		<x:RowBreaks>"+_separator();
		for(int i=0;i<pb.size();i++){
			pagebreak ph = (pagebreak)pb.elementAt(i);
			this._comment+="	    			<x:RowBreak>"+_separator();
			this._comment+="	    				<x:Row>"+ph.getPagebreak_row()+"</x:Row>"+_separator();
			this._comment+="	    			</x:RowBreak>"+_separator();
		}
		this._comment+="	    		</x:RowBreaks>"+_separator();
		this._comment+="	    	</x:PageBreaks>"+_separator();
	}

	this._comment+="		</x:ExcelWorksheet>"+_separator();
	this._comment+="	</x:ExcelWorksheets>"+_separator();
	this._comment+="	<x:ProtectStructure>False</x:ProtectStructure>"+_separator();
	this._comment+="	<x:ProtectWindows>False</x:ProtectWindows>"+_separator();
	this._comment+="</x:ExcelWorkbook>"+_separator();

	page_header ph = new page_header();
	try{
		ph = (page_header)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader)).getContent();
	}catch(Exception e){
		ph = null;
	}
	if(ph!=null){
		this._comment+="<x:ExcelName>"+_separator();
		this._comment+="	<x:Name>Print_Titles</x:Name>"+_separator();
		this._comment+="	<x:SheetIndex>1</x:SheetIndex>"+_separator();
		this._comment+="	<x:Formula>=Rapporto!$"+ph.getHeader_rows_start()+":$"+ph.getHeader_rows_finish()+"</x:Formula>"+_separator();
		this._comment+="</x:ExcelName>"+_separator();
	}

	this._comment+="</xml>"+_separator();
	this._comment+="<![endif]-->"+_separator();



}
public void reimposta() {
	setName("GENERAL");
	TYPE_DOCUMENT = "attachment";
	SOURCE_DOCUMENT = "";
	ORIENTATION = "";
	TYPE_RESPONSE = "";
	SOURCE_BEFORE_FIXED ="";
	SOURCE_AFTER_FIXED ="";
	SOURCE_ERROR_FIXED ="";
	LIB="html_xls";
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
							((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:Writer")).getContent())).close();
						}catch (Exception ex) {}
					}
					if (this.motore.getClass().getName().indexOf("XLSRunThread") > -1) {
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
public void setORIENTATION(java.lang.String newORIENTATION) {
	ORIENTATION = newORIENTATION;
}
public void setSOURCE_AFTER_FIXED(java.lang.String newSOURCE_AFTER_FIXED) {
	SOURCE_AFTER_FIXED = newSOURCE_AFTER_FIXED;
}
public void setSOURCE_BEFORE_FIXED(java.lang.String newSOURCE_BEFORE_FIXED) {
	SOURCE_BEFORE_FIXED = newSOURCE_BEFORE_FIXED;
}
public void setSOURCE_DOCUMENT(java.lang.String newSOURCE_DOCUMENT) {
	SOURCE_DOCUMENT = newSOURCE_DOCUMENT;
}
public void setSOURCE_ERROR_FIXED(java.lang.String newSOURCE_ERROR_FIXED) {
	SOURCE_ERROR_FIXED = newSOURCE_ERROR_FIXED;
}
public void setTYPE_DOCUMENT(java.lang.String newTYPE_DOCUMENT) {
	if(newTYPE_DOCUMENT!=null && !newTYPE_DOCUMENT.trim().equals(""))
		TYPE_DOCUMENT = newTYPE_DOCUMENT;

}
public void setTYPE_RESPONSE(java.lang.String newTYPE_RESPONSE) {
	TYPE_RESPONSE = newTYPE_RESPONSE;
}
	public java.lang.String getLIB() {
		return LIB;
	}

	public void setLIB(java.lang.String string) {
		LIB = string;
	}

}

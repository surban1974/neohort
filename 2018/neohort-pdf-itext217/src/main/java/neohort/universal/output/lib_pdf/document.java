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

 package neohort.universal.output.lib_pdf;

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

public class document extends element{
	private static final long serialVersionUID = -1L;
	Document document;
	PdfWriter writer;

	private String TITLE;
	private String AUTHOR;
	private String SUBJECT;
	private String KEYWORDS;
	private String CREATOR;


public document() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
			Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
			if(included!=null && included.booleanValue()==true){}
			else{
				document = (Document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
				if(!TITLE.equals(""))
						document.addTitle(TITLE);
				if(!AUTHOR.equals(""))
						document.addAuthor(AUTHOR);
				if(!SUBJECT.equals(""))
						document.addSubject(SUBJECT);
				if(!KEYWORDS.equals(""))
						document.addKeywords(KEYWORDS);
				if(!CREATOR.equals(""))
						document.addCreator(CREATOR);

				document.open();

			}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{


				try{
					report_element_base PdfPageFooter_ = ((((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageFooter_))));
					if(PdfPageFooter_!=null){
						_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(PdfPageFooter_);
                    	isError=false;
						initCanvas(null, _beanLibrary);
						_beanLibrary.remove("SYSTEM:"+iConst.iHORT_SYSTEM_PageFooter_);
					}
				}catch(Exception e){
				}



			document.close();
			if(_tagLibrary.get(getName()+":"+getID())==null)
				_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
			else _tagLibrary.remove(getName()+":"+getID());
		}
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}

}
public void reimposta() {
	setName("DOCUMENT");
	STYLE_ID = "";
	TITLE = "";
	AUTHOR = "";
	SUBJECT = "";
	KEYWORDS = "";
	CREATOR = "";
}
public void setCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else
			_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(document);
		
	}catch(Exception e){}
}
public String getTITLE() {
	return TITLE;
}
public void setTITLE(String title) {
	TITLE = title;
}
public String getAUTHOR() {
	return AUTHOR;
}
public void setAUTHOR(String author) {
	AUTHOR = author;
}
public String getSUBJECT() {
	return SUBJECT;
}
public void setSUBJECT(String subject) {
	SUBJECT = subject;
}
public String getKEYWORDS() {
	return KEYWORDS;
}
public void setKEYWORDS(String keywords) {
	KEYWORDS = keywords;
}
public String getCREATOR() {
	return CREATOR;
}
public void setCREATOR(String creator) {
	CREATOR = creator;
}
}

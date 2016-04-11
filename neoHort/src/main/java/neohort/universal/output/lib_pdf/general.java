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


import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_pdf.general_util.general_j2ee;
import neohort.universal.output.util.OutputRunTime;
import neohort.universal.output.util.OutputRunTimeService;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfWriter;

public class general extends element{
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public PdfWriter getWriter() {
		return writer;
	}
	public void setWriter(PdfWriter writer) {
		this.writer = writer;
	}
	private static final long serialVersionUID = 1772526932083369013L;
	Document document;
	PdfWriter writer;
	public Hashtable _beanLibrary;
	private String TYPE_DOCUMENT;
	private String SOURCE_DOCUMENT;
	private String ORIENTATION;
	private String MARGINS;
	private String SOURCE_AFTER_FIXED;
	private String SOURCE_BEFORE_FIXED;
	private String SOURCE_ERROR_FIXED;
	private String LIB;
	private String DIRECTION;

	private boolean documentClosed=false;
	private int cur_page = 0;
	
	public class PageEvent_0 implements PdfPageEvent{
		
		
		public void onOpenDocument(PdfWriter _writer, Document _document){
	        try {
	            com.lowagie.text.pdf.PdfPTable PdfPageHeader = ((com.lowagie.text.pdf.PdfPTable) (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader)).getContent()));
				((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader);
				isError=false;
	   	        initCanvas(null, _beanLibrary);
	        } catch (Exception e) {}
			try {
				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight)==null) _beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight,new bean());
				bean _sysPdfPH = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight);
				_sysPdfPH.setContent(new Float(0));
			} catch (Exception e) {}  
/*			
	        try {
	        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
				((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
				isError=false;
				initCanvas(null, _beanLibrary);
	        } catch (Exception e) {}
*/
	        try {
	        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
				if(PdfPageHeader_.getNodeCurrent()!=null){
					motore.recompileProfile(PdfPageHeader_.getNodeCurrent(), PdfPageHeader_);
				}else{
	                ((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
					isError=false;
	                initCanvas(null, _beanLibrary);
				}
	        } catch (Exception e) {}
			
		}
		
		public void onCloseDocument(PdfWriter _writer, Document _document){

			try {		            
				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber)==null) _beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber,new bean());
				bean _sysPdfPN = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber);
				_sysPdfPN.setContent(new Integer(((Integer)_sysPdfPN.getContent()).intValue()-1));
			} catch (Exception e) {}
			
	        try {
	            com.lowagie.text.pdf.PdfPTable PdfPageHeader = ((com.lowagie.text.pdf.PdfPTable) (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)).getContent()));
				((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader);
				isError=false;
				initCanvas(null, _beanLibrary);
	        } catch (Exception e) {}
	        
/*	        
	        try {
	        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
				((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
				isError=false;
	            initCanvas(null, _beanLibrary);
	        } catch (Exception e) {}
*/	
	        try {
	        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
				if(PdfPageHeader_.getNodeCurrent()!=null){
					motore.recompileProfile(PdfPageHeader_.getNodeCurrent(), PdfPageHeader_);
				}else{
	                ((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
					isError=false;
	                initCanvas(null, _beanLibrary);
				}
	        } catch (Exception e) {}
	        
	        try{
	        	Hashtable container = (Hashtable)((bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Templates)).getContent();
	        	Vector list = new Vector(container.values());
	        	for(int i=0;i<list.size();i++){
	        		String key = (String)list.get(i);
	        		if(_beanLibrary.get(key)!=null){
	        			text current = (text)_beanLibrary.get(key);
	        			current.setDrawTextInTemplate(true);
	        			current.drawDirect(_beanLibrary);
	        		}
	        	}
			
	        	
	        }catch(Exception e){
	        }
		}
		public void onStartPage(PdfWriter _writer, Document _document) {
			
		    try {
			    try {		            
			        if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber)==null) _beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber,new bean());
			        bean _sysPdfPN = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document_PageNumber);
			        _sysPdfPN.setContent(new Integer(_document.getPageNumber()));
			    } catch (Exception e) {}
				try {
					if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight)==null) _beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight,new bean());
					bean _sysPdfPH = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Page_TablesHeight);
					_sysPdfPH.setContent(new Float(0));
				} catch (Exception e) {}			        
		        try {
		            com.lowagie.text.pdf.PdfPTable PdfPageHeader = ((com.lowagie.text.pdf.PdfPTable) (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader)).getContent()));
	                ((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader);
					isError=false;
	                initCanvas(null, _beanLibrary);
		        } catch (Exception e) {}
/*		        
		        try {
		        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
	                ((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
					isError=false;
	                initCanvas(null, _beanLibrary);
		        } catch (Exception e) {}
*/
		        try {
		        	report_element_base PdfPageHeader_ = (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageHeader_)));
					if(PdfPageHeader_.getNodeCurrent()!=null){
						motore.recompileProfile(PdfPageHeader_.getNodeCurrent(), PdfPageHeader_);
					}else{
		                ((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageHeader_);
						isError=false;
		                initCanvas(null, _beanLibrary);
					}
		        } catch (Exception e) {}
		        
		    } catch (Exception e) {
		    }
		}
		
  		public void onEndPage(PdfWriter _writer, Document _document){
	  		if(_document.getPageNumber()>cur_page){
/*	  			
				try{
					cur_page = _document.getPageNumber();
					report_element_base PdfPageFooter_ = ((((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageFooter_))));
					if(PdfPageFooter_!=null){
                    	((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageFooter_);
                    	isError=false;
						initCanvas(null, _beanLibrary);
					}
				}catch(Exception e){
				}
*/
				try{
					cur_page = _document.getPageNumber();
					report_element_base PdfPageFooter_ = ((((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_PageFooter_))));
					if(PdfPageFooter_!=null){
						if(PdfPageFooter_.getNodeCurrent()!=null){
							motore.recompileProfile(PdfPageFooter_.getNodeCurrent(), PdfPageFooter_);
						}else{
	                    	((java.util.Vector)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(PdfPageFooter_);
	                    	isError=false;
							initCanvas(null, _beanLibrary);						
						}
					}
				}catch(Exception e){
				}	  			
			}
	  	}
	  	
		public void onParagraph(PdfWriter _writer, Document _document, float paragraphPosition){
		}
		public void onParagraphEnd(PdfWriter _writer,Document _document,float paragraphPosition){
		}
		public void onChapter(PdfWriter _writer,Document _document,float paragraphPosition, Paragraph title){
		}
		public void onChapterEnd(PdfWriter _writer,Document _document,float paragraphPosition){
		}
		public void onSection(PdfWriter _writer,Document _document,float paragraphPosition, int depth, Paragraph title){
		}
		public void onSectionEnd(PdfWriter _writer,Document _document,float paragraphPosition){
		}
		public void onGenericTag(PdfWriter _writer, Document _document, Rectangle rect, String text){
		}
	}

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
				if(getSOURCE_DOCUMENT()==null) setSOURCE_DOCUMENT("");
				if(getORIENTATION()!=null && getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
					document = new Document(PageSize.A4.rotate());
				else document = new Document(PageSize.A4);
				try{
					int p1 = 30;
					int p2 = 30;
					int p3 = 30;
					int p4 = 30;
					java.util.StringTokenizer st = new java.util.StringTokenizer(getMARGINS(), ",");
						p1 = Integer.valueOf(st.nextToken()).intValue();
						p2 = Integer.valueOf(st.nextToken()).intValue();
						p3 = Integer.valueOf(st.nextToken()).intValue();
						p4 = Integer.valueOf(st.nextToken()).intValue();
					document.setMargins(p1, p2, p3, p4);
				}catch(Exception e){
					document.setMargins(30, 30, 30, 30);
				}
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && !getSOURCE_DOCUMENT().equals("")){
					if(!noGenerate.booleanValue())
						writer = PdfWriter.getInstance(document, new java.io.FileOutputStream(getSOURCE_DOCUMENT()));

		
				}
				if (getTYPE_DOCUMENT()!=null && !getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && !getSOURCE_DOCUMENT().equals("")){
					if(!noGenerate.booleanValue())
						writer = PdfWriter.getInstance(document, new java.io.FileOutputStream(getSOURCE_DOCUMENT()));

		
				}
				

					if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
						if(!noGenerate.booleanValue()) writer = PdfWriter.getInstance(document,(OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent()));
				
				if(writer!=null){
					this._beanLibrary = _beanLibrary;
					PageEvent_0 pev = new PageEvent_0();
					writer.setPageEvent(pev);
					if(!getDIRECTION().equals("") && getDIRECTION().equalsIgnoreCase("RTL")){
						writer.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
					}
				}
		
				bean _sysDocument = new bean();
						_sysDocument.setContent(document);
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
				if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).flush();
				if (getTYPE_DOCUMENT()!=null && getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
					if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
					if (this.motore.getClass().getName().indexOf("RunThread") > -1) {
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
	SOURCE_BEFORE_FIXED ="";
	SOURCE_AFTER_FIXED ="";
	SOURCE_ERROR_FIXED ="";
	ORIENTATION = "";
	MARGINS = "";
	LIB="pdf";
	DIRECTION="";
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
							((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
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
public boolean isDocumentClosed() {
	return documentClosed;
}
public void setDocumentClosed(boolean documentClosed) {
	this.documentClosed = documentClosed;
}
public int getCur_page() {
	return cur_page;
}
public String getDIRECTION() {
	return DIRECTION;
}
public void setDIRECTION(String dIRECTION) {
	DIRECTION = dIRECTION;
}
}

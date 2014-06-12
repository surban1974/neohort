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

package neohort.universal.output;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Hashtable;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.util.Dispatcher;
import neohort.universal.output.util.Dispatcher_element;
import neohort.universal.output.util.I_OutputRunTime;
import neohort.universal.output.util.OutputRunTime;
import neohort.universal.output.util.Request_thread;

import neohort.util.util_xml;
import neohort.exception.exception_manager;

public class iHort extends OutputRunTime implements I_OutputRunTime{
	private static final long serialVersionUID = 4687357971197883208L;
	private report_element_base element_current;
//	private iHort instance = null;

	public class CharResponseWrapper extends HttpServletResponseWrapper {
		   private CharArrayWriter output;
		   public String toString() {
			  return output.toString();
		   }
		   public CharResponseWrapper(HttpServletResponse response){
			  super(response);
			  output = new CharArrayWriter();
		   }
		   public PrintWriter getWriter(){
			  return new PrintWriter(output);
		   }
		}	
	
public iHort() {
	super();
}
 iHort(String fname) {
	super();
	initXML(fname);
}

public iHort(String fname, Hashtable _beanLibrary, report_element parentForINCLUDE) {
	String XML_name="";
	String parameters="";
	if(fname==null || _beanLibrary==null) return;
		
	java.util.StringTokenizer st = new java.util.StringTokenizer(fname, "?");
	if(st.hasMoreElements()) XML_name = st.nextToken();
	if(st.hasMoreElements()) parameters = st.nextToken();		
	
	this._beanLibrary = _beanLibrary;
	isReimposta = false;
	String fullName = this.getClass().getName();
	while (fullName.indexOf(".")>-1){
		namePac+=fullName.substring(0,fullName.indexOf("."))+".";
		fullName = fullName.substring(fullName.indexOf(".")+1);
	}

	try{
		response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
		request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
	}catch(Exception e){}	
	bean _sysIncluded = new bean();
		_sysIncluded.setContent(new Boolean(true));
		_sysIncluded.setName("SYSTEM");
		_sysIncluded.setID(iConst.iHORT_SYSTEM_Included);
		_beanLibrary.put(_sysIncluded.getName()+":"+_sysIncluded.getID(),_sysIncluded);

	bean _sysParameters = new bean();
		_sysParameters.setContent(new Request_thread(request,parameters));
		_sysParameters.setName("BEAN:INCLUDE");
		_sysParameters.setID("Parameters"+"_ids_"+this.hashCode());
		_beanLibrary.put(_sysParameters.getName()+":"+_sysParameters.getID(),_sysParameters);

	bean _sysParentForINCLUDE = new bean();
		_sysParentForINCLUDE.setContent(parentForINCLUDE);
		_sysParentForINCLUDE.setName("SYSTEM");
		_sysParentForINCLUDE.setID(iConst.iHORT_SYSTEM_ParentForINCLUDE);
		_beanLibrary.put(_sysParentForINCLUDE.getName()+":"+_sysParentForINCLUDE.getID()+"_ids_"+this.hashCode(),_sysParentForINCLUDE);

	initXML(XML_name);
	
}

public iHort(HttpServletRequest req, HttpServletResponse res, ServletConfig sConfig) {
	super();
	try {
		request = req;
		response = res; 
		isReimposta = true;
		servletConfig = sConfig;
		if (request.getParameter(iConst.iHORT_INPUT_$log)!=null)
			setLOG_INTERCEPTOR(request.getParameter(iConst.iHORT_INPUT_$log));
		if (request.getParameter(iConst.iHORT_INPUT_$source_stream)!=null){
			initXML(request.getParameter(iConst.iHORT_INPUT_$source_stream),false);
			return;				
		}
		if (request.getAttribute(iConst.iHORT_INPUT_$source_stream)!=null){
			initXML(request.getAttribute(iConst.iHORT_INPUT_$source_stream),false);
			return;				
		}
		
		if (request.getParameter(iConst.iHORT_INPUT_$source)!=null){
			initXML(request.getParameter(iConst.iHORT_INPUT_$source));
			return;
		}	

	} catch (Exception exc) {
		setError(exc,iStub.log_FATAL);
	} catch (Error err) {
		setError(new Exception(err.toString()),iStub.log_FATAL);
	}
}


public OutputStream transformXMLtoReport(String xml, OutputStream report){	
	if(isReimposta) reimposta(); 
	
	bean _sysOutputStream = new bean();
	_sysOutputStream.setContent(report);
	_sysOutputStream.setName("SYSTEM");
	_sysOutputStream.setID(iConst.iHORT_INPUT_$external_output_stream);
	_beanLibrary.put(_sysOutputStream.getName()+":"+_sysOutputStream.getID(),_sysOutputStream);	

	try {
		documentXML = util_xml.readXMLData(xml);
	}
	catch (Exception e) {
		setError(e,iStub.log_FATAL);
		try{
			report.write(e.toString().getBytes());
		}catch(Exception exp){				
		}		
	}

	if(documentXML!=null){
		recompileProfile();
	}		
	return report;
}
public OutputStream transformXMLtoReport(InputStream xml, OutputStream report){
	if(isReimposta) reimposta(); 
	
	bean _sysOutputStream = new bean();
	_sysOutputStream.setContent(report);
	_sysOutputStream.setName("SYSTEM");
	_sysOutputStream.setID(iConst.iHORT_INPUT_$external_output_stream);
	_beanLibrary.put(_sysOutputStream.getName()+":"+_sysOutputStream.getID(),_sysOutputStream);	

	try {
		documentXML = util_xml.readXMLData(xml);
	}
	catch (Exception e) {
		setError(e,iStub.log_FATAL);
		try{
			report.write(e.toString().getBytes());
		}catch(Exception exp){				
		}		
	}

	if(documentXML!=null){
		recompileProfile();
	}		
	return report;
}

public void initXML(String fname) {
	if(isReimposta) reimposta(); 
	
	pathXML = analisePath(fname);
	try {
		documentXML = util_xml.readXML(pathXML,false);
	}catch (Exception ex) {
		setError(ex,iStub.log_FATAL);
		try{
			response.getWriter().print(ex.toString());
		}catch(Exception exp){				
		}
	}

	
	if(isReimposta){ 
		setError(null,"Report povered by [neoHort:)-... Visit http://sourceforge.net/projects/neohort; Copyright (C) 2005 Svyatoslav Urbanovych surban@bigmir.net svyatoslav.urbanovych@gmail.com. General Public License.",iStub.log_INFO);
		try{
			((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_pathXML)).setContent(pathXML);		
		}catch(Exception e){}
	}	
	if (request!=null && request.getParameter("ID")!=null)
		ID = request.getParameter("ID");
	else ID = "SYS_ID_"+new java.util.Date().getTime();


	if(documentXML!=null){
		setDispatcher();
		writeOpen();
		recompileProfile();
		writeClose();		
		status = 100;
		setDispatcher();
	}	
}
public void initXML(Object xml_text,boolean validate) {
	if(xml_text==null || !(xml_text instanceof String)) return; 
	if(isReimposta) reimposta(); 		
	if(isReimposta){ 
		try{
			((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_pathXML)).setContent(pathXML);		
		}catch(Exception e){}
	}	
	if (request!=null && request.getParameter("ID")!=null)
		ID = request.getParameter("ID");
	else ID = "SYS_ID_"+new java.util.Date().getTime();
	try {
		documentXML = util_xml.readXMLData((String)xml_text,false);
	}
	catch (Exception e) {
		setError(e,iStub.log_FATAL);
		try{
			response.getWriter().print(e.toString());
		}catch(Exception exp){				
		}		
	}

	if(documentXML!=null){
		setDispatcher();
		writeOpen();
		recompileProfile();
		writeClose();		
		status = 100;
		setDispatcher();
	}	
}

public void recompileProfile() {
	NodeList nList = documentXML.getChildNodes();
	if (nList==null || nList.getLength()==0) return;
	for(int i=0;i<nList.getLength();i++){
		if(nList.item(i).getNodeType()==3 || nList.item(i).getNodeType()==1){ 
			tagFactory(nList.item(i),null); 
			beanFactory(nList.item(i),null);
			styleFactory(nList.item(i),null);
			if (element_current!=null){
				element_current.executeFirst(_tagLibrary,_beanLibrary);
				write(element_current.toXMLString_start());
			}	
			recompileProfile(nList.item(i),element_current);
			if (element_current!=null){
				write(element_current.toXMLString_finish());
				element_current.executeLast(_tagLibrary,_beanLibrary);
			}	
		}
		status = 100*i/nList.getLength() - 1;
		setDispatcher();
		
//		setError(null,Util_web.environmentState(request),iStub.log_INFO);
	}
}
public void recompileProfile(report_element _child, report_element _parent) {	
	Node node = ((report_element_base)_child).getNodeCurrent();
	recompileProfile(node,_parent);
}

public void recompileProfile(Node node, report_element _parent) {	
	report_element_base parent = (report_element_base)_parent;
	NodeList nList = node.getChildNodes();
	boolean useBeanCheck = false;
	if (nList==null || nList.getLength()==0) return;
	element_current = null;
	boolean is_cycle = false;
	for(int i=0;i<nList.getLength();i++){
		if((nList.item(i).getNodeType()==3 || nList.item(i).getNodeType()==1)){
			if (nList.item(i).getNodeName().trim().toUpperCase().equalsIgnoreCase("USEBEAN_")){
				usebeanFactory(nList.item(i),parent);
				useBeanCheck = true;
				try{
					if(((Text)node.getFirstChild()).getData()!=null)
						parent.setContent(((Text)node.getFirstChild()).getData());
				}catch(Exception exText){}
			}else{
				if(!is_cycle){					
					tagFactory(nList.item(i),parent);
					beanFactory(nList.item(i),parent);
					styleFactory(nList.item(i),null); 
					if (element_current!=null){
						element_current.executeFirst(_tagLibrary,_beanLibrary);
						write(element_current.toXMLString_start());
						element_current.setCanvas(_tagLibrary,_beanLibrary);						
					}	
				}	
				if(element_current!=null){
					if (element_current.isPreActive())	recompileProfile(nList.item(i),element_current);
					if (element_current.isActive()){
						i=i-1;
						is_cycle=true;
					}else{ 	
						if (element_current!=null){						
							element_current.executeLast(_tagLibrary,_beanLibrary);
							write(element_current.toXMLString_finish());
						}	
						element_current.drawCanvas(_tagLibrary,_beanLibrary);
						is_cycle=false;
					}
				}	
			}
		}	
	}
	if(useBeanCheck && parent != null && parent.refreshText()){
		try{
			if(((Text)node.getFirstChild()).getData()!=null)
				parent.setContent(((Text)node.getFirstChild()).getData());
		}catch(Exception exText){}
	}	
	element_current = parent;
}
public void reimposta() {
//	this.instance = this;
	this.documentXML = null;
	status = -1;
	namePac = "";
	saveAs = null;
	saveAsName = null;
	pathXML = "";

	
	String fullName = this.getClass().getName();
	while (fullName.indexOf(".")>-1){
		namePac+=fullName.substring(0,fullName.indexOf("."))+".";
		fullName = fullName.substring(fullName.indexOf(".")+1);
	}
	_tagLibrary = new Hashtable();
	_beanLibrary = new Hashtable();
	_styleLibrary = new Hashtable();
	element_current = null;

	bean _sysIncluded = new bean();
		_sysIncluded.setContent(new Boolean(false));
		_sysIncluded.setName("SYSTEM");
		_sysIncluded.setID(iConst.iHORT_SYSTEM_Included);
		_beanLibrary.put(_sysIncluded.getName()+":"+_sysIncluded.getID(),_sysIncluded);

	bean _syspathXML = new bean();
		_syspathXML.setContent(pathXML);
		_syspathXML.setName("SYSTEM");
		_syspathXML.setID(iConst.iHORT_SYSTEM_pathXML);
		_beanLibrary.put(_syspathXML.getName()+":"+_syspathXML.getID(),_syspathXML);

	bean _syssaveAs = new bean();
		_syssaveAs.setContent(saveAs);
		_syssaveAs.setName("SYSTEM");
		_syssaveAs.setID(iConst.iHORT_SYSTEM_saveAs);
		_beanLibrary.put(_syssaveAs.getName()+":"+_syssaveAs.getID(),_syssaveAs);
	
	bean _sysResponse = new bean();
		_sysResponse.setContent(response);
		_sysResponse.setName("SYSTEM");
		_sysResponse.setID(iConst.iHORT_SYSTEM_Response);
		_beanLibrary.put(_sysResponse.getName()+":"+_sysResponse.getID(),_sysResponse);

	bean _sysRequest = new bean();
		_sysRequest.setContent(request);
		_sysRequest.setName("SYSTEM");
		_sysRequest.setID(iConst.iHORT_SYSTEM_Request);
		_beanLibrary.put(_sysRequest.getName()+":"+_sysRequest.getID(),_sysRequest);

	bean _sysCanvas = new bean();
		_sysCanvas.setContent(new java.util.Vector());
		_sysCanvas.setName("SYSTEM");
		_sysCanvas.setID(iConst.iHORT_SYSTEM_Canvas);
		_beanLibrary.put(_sysCanvas.getName()+":"+_sysCanvas.getID(),_sysCanvas);	

	bean _sysParagraph = new bean();
		_sysParagraph.setContent(new java.util.Vector());
		_sysParagraph.setName("SYSTEM");
		_sysParagraph.setID(iConst.iHORT_SYSTEM_Paragraph);
		_beanLibrary.put(_sysParagraph.getName()+":"+_sysParagraph.getID(),_sysParagraph);	

	bean _sysPageBreak = new bean();
		_sysPageBreak.setContent(new java.util.Vector());
		_sysPageBreak.setName("SYSTEM");
		_sysPageBreak.setID(iConst.iHORT_SYSTEM_PageBreak);
		_beanLibrary.put(_sysPageBreak.getName()+":"+_sysPageBreak.getID(),_sysPageBreak);
	
	if(request!=null){
		bean _sysSessionID = new bean();	
			_sysSessionID.setContent(request.getSession());
			_sysSessionID.setName("SYSTEM");
			_sysSessionID.setID(iConst.iHORT_SYSTEM_Session);
			_beanLibrary.put(_sysSessionID.getName()+":"+_sysSessionID.getID(),_sysSessionID);
			
		Boolean noGenerate = null;	
		try{
			noGenerate = (Boolean)request.getAttribute(iConst.iHORT_SYSTEM_NOGENERATE);
		}catch(Exception e){			
		}
		if(noGenerate==null) noGenerate = new Boolean(false);
			bean _sysISBlank = new bean();
			_sysISBlank.setContent(noGenerate);
			_sysISBlank.setName("SYSTEM");
			_sysISBlank.setID(iConst.iHORT_SYSTEM_NOGENERATE);
			_beanLibrary.put(_sysISBlank.getName()+":"+_sysISBlank.getID(),_sysISBlank);
			
	}else{
		bean _sysISBlank = new bean();
		_sysISBlank.setContent(new Boolean(false));
		_sysISBlank.setName("SYSTEM");
		_sysISBlank.setID(iConst.iHORT_SYSTEM_NOGENERATE);
		_beanLibrary.put(_sysISBlank.getName()+":"+_sysISBlank.getID(),_sysISBlank);		
	}
		
}
private void setDispatcher() {
	try {
		Boolean included = new Boolean(false);
		try{
			if(_beanLibrary!=null)
 		   		included = (Boolean) (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		}catch(Exception e){}
		if(included.booleanValue()) return;
		
		HttpSession tsxSessionHolder = request.getSession(false);
		if (tsxSessionHolder==null) return;
		if(tsxSessionHolder.getAttribute(iConst.iHORT_INPUT_report_dispatcher)==null){
			Dispatcher r_disp = new Dispatcher();
			Dispatcher_element r_disp_el = new Dispatcher_element();
			r_disp_el.setStatus(status);
			tsxSessionHolder.setAttribute(iConst.iHORT_INPUT_report_dispatcher,r_disp);
		}
		else{
			Dispatcher r_disp = (Dispatcher)tsxSessionHolder.getAttribute(iConst.iHORT_INPUT_report_dispatcher);
			if(r_disp!=null){
				Dispatcher_element r_disp_el = r_disp.getReport(ID);
				if(r_disp_el==null){		
					r_disp_el = new Dispatcher_element();						
					r_disp_el.setStatus(status);
					r_disp.setReport(ID,r_disp_el);
				}else r_disp_el.setStatus(status);	
			}	
		}	
	} catch (Exception e) {
		setError(e,iStub.log_WARN);
	}
}

public void setError(Exception e,String level) {
	setError(e,"",level);
}
public void setError(Exception e, String des, String level) {
	
	exception_manager.exception_to_log(LOG_INTERCEPTOR, e,des,request,level);

	if(ID==null || ID.indexOf("SYS_ID_")==0) return;
	try {
		HttpSession tsxSessionHolder = request.getSession(false);
		if (tsxSessionHolder==null) return;
		if(tsxSessionHolder.getAttribute(iConst.iHORT_INPUT_report_dispatcher)==null){
			Dispatcher r_disp = new Dispatcher();
//			Dispatcher_element r_disp_el = new Dispatcher_element();
			tsxSessionHolder.setAttribute(iConst.iHORT_INPUT_report_dispatcher,r_disp);
		}
		else{
			Dispatcher r_disp = (Dispatcher)tsxSessionHolder.getAttribute(iConst.iHORT_INPUT_report_dispatcher);
			if(r_disp!=null){
				Dispatcher_element r_disp_el = r_disp.getReport(ID);
				if(r_disp_el==null){		
					r_disp_el = new Dispatcher_element();						
					r_disp.setReport(ID,r_disp_el);
				}
			}	
		}	
	} catch (Exception ex) {
	}
}
private Object prepareBean(bean element) {
	try {
		if (element.getSCOPE().equalsIgnoreCase("REQUEST")){
			if(request.getAttribute(element.getID())!=null) return request.getAttribute(element.getID());
		}
		if (element.getSCOPE().equalsIgnoreCase("SESSION")){
			HttpSession tsxSessionHolder = request.getSession(false);
			if(tsxSessionHolder.getAttribute(element.getID())!=null) return tsxSessionHolder.getAttribute(element.getID());
		}
		if (element.getSCOPE().equalsIgnoreCase("APPLICATION")){
			if(servletConfig.getServletContext().getAttribute(element.getID())!=null) return servletConfig.getServletContext().getAttribute(element.getID());
		}	
		return null;
	} catch (Exception e) {
		setError(e,iStub.log_WARN);
		return null;
	}
}
private report_element_base objectFactory(Node node, report_element_base element_parent)throws Exception{
	report_element_base element = null;
	String className = "";
	if(node.getNodeName().toLowerCase().equals("#text")) return null;
	if(LIB!=null && !LIB.equals("")){
		try{
			className=namePac + "lib_" + LIB + "." + node.getNodeName().toLowerCase();
			element = (report_element_base)Class.forName(className).newInstance();
		}catch (Exception e) {}
	}
	if(element==null){
		try{	
			className=namePac + "lib." + node.getNodeName().toLowerCase();
			element = (report_element_base)Class.forName(className).newInstance();
		}catch (Exception e) {}
		
	}
	if(element==null && LIB!=null && !LIB.equals("")){
		try{	
			className=namePac + "lib_" + LIB + ".error";
			element = (report_element_base)Class.forName(className).newInstance();
			element.setName(element.getName()+" : "+namePac + "lib_" + LIB +"."+node.getNodeName().toLowerCase());
		}catch (Exception e) {}
	}
	
	
	
	if(element==null) return element;
	element.setMotore(this);
	Object ret_obj = element.init_element(node,element_parent,_tagLibrary,_beanLibrary,_styleLibrary); 
	if (ret_obj!=null){
		if(!element.equals(ret_obj)){
			element = (report_element_base)ret_obj;
			element.setMotore(this);
		}
	}
	return element;
}

private void beanFactory(Node node, report_element_base element_parent) {
	if (node==null) return;
	if (node.getNodeType()!=3 && node.getNodeType()!=1) return;
	if (!node.getNodeName().trim().equalsIgnoreCase("BEAN")) return;
	report_element_base element = null;
	try {
		element = objectFactory(node, element_parent);
		if(element!=null){
			element.setContent(prepareBean((bean)element));
			_beanLibrary.put(node.getNodeName()+":"+element.getID()+"_ids_"+this.hashCode(),element);
			element_current = element;
		}else element_current = null;		
	} catch (Exception e) {
		setError(e,iStub.log_ERROR);
		element_current = null;
	}	
}

private void styleFactory(Node node, report_element_base element_parent) {
	if (node==null) return;
	if (node.getNodeType()!=3 && node.getNodeType()!=1) return;
	if (!node.getNodeName().trim().equalsIgnoreCase("STYLE")) return;

	report_element_base element = null;
	try {
		element = objectFactory(node, element_parent);
		if(element!=null){
			if (_styleLibrary.get(node.getNodeName().toUpperCase()+":"+element.getID()+"_ids_"+this.hashCode())==null)
				_styleLibrary.put(node.getNodeName().toUpperCase()+":"+element.getID()+"_ids_"+this.hashCode(),element);
			element_current = element;
		}else element_current = null;		
	} catch (Exception e) {
		setError(e,iStub.log_WARN);
		element_current = null;
	}	
}
private void tagFactory(Node node, report_element_base element_parent) {
	if (node==null) return;
	if (node.getNodeType()!=Node.ELEMENT_NODE && node.getNodeType()!=Node.TEXT_NODE) return;	
	if (node.getNodeName().trim().equalsIgnoreCase("BEAN")) return;
	if (node.getNodeName().trim().equalsIgnoreCase("STYLE")) return;
	
	report_element_base element = null;
	try {
		element = objectFactory(node, element_parent);
		if(element!=null){
			_tagLibrary.put(node.getNodeName()+":"+element.getID()+"_ids_"+this.hashCode(),element);
			if(node.getNodeName().equalsIgnoreCase("general")){
				try{
					bean _sysPdfParseJava = new bean();
					_sysPdfParseJava.setName("SYSTEM");
					_sysPdfParseJava.setID(iConst.iHORT_SYSTEM_ParseJava);
					_sysPdfParseJava.setContent(element.getPARSER_JAVA());
					_beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_ParseJava, _sysPdfParseJava);
				}catch(Exception e){
				}
			}
			
			element_current = element;
		}else element_current = null;		
	} catch (Exception e) {
		setError(e,iStub.log_ERROR);
		element_current = null;
	}	
}
private void usebeanFactory(Node node, report_element_base element_parent) {
	if (node==null) return;
	if (node.getNodeType()!=3 && node.getNodeType()!=1) return;
	if (node.getNodeName().trim().equalsIgnoreCase("BEAN")) return;

	report_element_base element = null;
	try {
		element = objectFactory(node, element_parent);
		if(element!=null){
			Object result = element.execute(_beanLibrary);
			if (result!=null)
				node.getParentNode().replaceChild(documentXML.createTextNode(result.toString()),node);
		}else element_current = null;		
	} catch (Exception e) {
		setError(e,iStub.log_ERROR);
		element_current = null;
	}	
}

public Hashtable get_tagLibrery(){
	return this._tagLibrary;
}


private void write(String value) {
	if(saveAs==null) return;		
	try {
        ((java.io.DataOutputStream)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_saveAsFile+"_ids_"+this.hashCode())).getContent())).writeBytes(value);
	}catch(Exception e){}
}

private void writeClose() {
	if(saveAs==null) return;	
	try {
        ((java.io.DataOutputStream)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_saveAsFile+"_ids_"+this.hashCode())).getContent())).close();
	}catch(Exception e){}
}

private void writeOpen() {
	if(saveAs==null) return;
	try{
		String separatore = System.getProperty("file.separator");
		if(separatore==null || separatore.equals("")){
			if(saveAsName.indexOf("\\")>-1 && saveAsName.indexOf("/")>-1){
				if(saveAsName.indexOf("\\")<saveAsName.indexOf("/")){
					saveAsName=saveAsName.replace('/','\\');
					separatore="\\";
				}	
				else{
					saveAsName=saveAsName.replace('\\','/');
					separatore="/";
				}	
			}else{
				if(saveAsName.indexOf("\\")==-1)
					separatore="/";
				else
					separatore="\\"; 
			}
		}else{
			if(saveAsName.indexOf("\\")>-1 && saveAsName.indexOf("/")>-1){
				if(separatore.equals("\\"))
					saveAsName=saveAsName.replace('/','\\');
				else saveAsName=saveAsName.replace('\\','/');
			}
		}
		String afterSaveAs = saveAsName.substring(saveAs.length(),saveAsName.length());
		java.util.StringTokenizer st = new java.util.StringTokenizer(afterSaveAs, separatore);
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		String buf=saveAs;	
		for(int i=0;i<parts.size();i++){
			File dir = new File(buf);
			if(!dir.exists()) dir.mkdir();
			buf+=(String)parts.elementAt(i)+separatore;
			
		}		
		
	}catch(Exception e){
		return;
	}	
	
	try {
		java.io.DataOutputStream LogFile = null;
	  	LogFile = new java.io.DataOutputStream(new BufferedOutputStream(new FileOutputStream(saveAsName,false)));
	  		LogFile.writeBytes("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+_separator());
			bean _sysParameters = new bean();
			_sysParameters.setContent(LogFile);
			_sysParameters.setName("SYSTEM");
			_sysParameters.setID(iConst.iHORT_SYSTEM_saveAsFile+"_ids_"+this.hashCode());
			_beanLibrary.put(_sysParameters.getName()+":"+_sysParameters.getID(),_sysParameters);
	  
	}catch(Exception e){}
}



private String analisePath(String path) {
	try{		
		saveAs =
		(String) (((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_saveAs)).getContent());
		if(saveAs==null){
			saveAs = request.getParameter(iConst.iHORT_SYSTEM_saveAs);
			((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_saveAs)).setContent(saveAs);
		}	
	}catch(Exception e){}
		
	
	String fullPath="";
	try{
		fullPath =
		(String) (((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_pathXML)).getContent());		
	}catch(Exception e){}
	if(path==null) return "";
	if(	path.trim().toLowerCase().indexOf("http://")==0 ||
		path.trim().toLowerCase().indexOf("http:\\\\")==0){
		java.util.StringTokenizer st = new java.util.StringTokenizer(path.replace('\\','/'), "/");
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+nameXML;	
		return path.trim();
	}	
	if(	path.trim().toLowerCase().indexOf("file://")==0 ||
		path.trim().toLowerCase().indexOf("file:\\\\")==0){
		java.util.StringTokenizer st = new java.util.StringTokenizer(path.replace('\\','/'), "/");
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+nameXML;	
		return path.trim().substring(7,path.trim().length());
		}	
	if(	path.trim().toLowerCase().indexOf("/")==0 ||
		path.trim().toLowerCase().indexOf("\\")==0){
			java.util.StringTokenizer st = new java.util.StringTokenizer(path.replace('\\','/'), "/");
			Vector parts = new Vector();
			while (st.hasMoreTokens())
				parts.add(st.nextToken());
			nameXML=(String)parts.elementAt(parts.size()-1);			
			if(saveAs!=null) saveAsName = saveAs+nameXML;				
			if(request!=null)	
				return "http://"+request.getServerName()+":"+request.getServerPort()+path.trim();
			else return path;
		}
			
	if(	path.trim().toLowerCase().indexOf("../")==0){
		java.util.StringTokenizer st = new java.util.StringTokenizer(fullPath, "/");
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		String returnR="";
		for(int i=0;i<parts.size()-2;i++)
			returnR+=(String)parts.elementAt(i)+((i==0)?"//":"/");
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+path.trim().substring(3,path.trim().length());		
		return returnR+path.trim().substring(3,path.trim().length());	
		
	}	
	if(	path.trim().toLowerCase().indexOf("..\\")==0){
		java.util.StringTokenizer st = new java.util.StringTokenizer(fullPath, "\\");
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		String returnR="";
		for(int i=0;i<parts.size()-2;i++)
			returnR+=(String)parts.elementAt(i)+"\\";
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+path.trim().substring(3,path.trim().length());
		return returnR+path.trim().substring(3,path.trim().length());	
	}
	if(	fullPath.trim().toLowerCase().indexOf("http://")==0 ||
		fullPath.trim().toLowerCase().indexOf("http:\\\\")==0){
		java.util.StringTokenizer st = new java.util.StringTokenizer(fullPath, "/"); 
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		String returnR="";
		for(int i=0;i<parts.size()-1;i++)
			returnR+=(String)parts.elementAt(i)+((i==0)?"//":"/");
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+path.trim();		
		return returnR+path.trim();	
	}else{
		java.util.StringTokenizer st = new java.util.StringTokenizer(fullPath, "\\");
		Vector parts = new Vector();
		while (st.hasMoreTokens())
			parts.add(st.nextToken());
		String returnR="";
		for(int i=0;i<parts.size()-1;i++)
			returnR+=(String)parts.elementAt(i)+"\\";
		nameXML=(String)parts.elementAt(parts.size()-1);
		if(saveAs!=null) saveAsName = saveAs+path.trim();	
		return returnR+path.trim();	
	}

		
}
}


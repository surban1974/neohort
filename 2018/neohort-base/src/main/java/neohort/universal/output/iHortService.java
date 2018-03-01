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



import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;

import neohort.exception.service.exception_managerService;
import neohort.log.stubs.iStub;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.util.I_OutputRunTime;
import neohort.universal.output.util.OutputRunTimeService;
import neohort.util.util_xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class iHortService extends OutputRunTimeService implements I_OutputRunTime{
	private static final long serialVersionUID = -1L;
	private report_element_base element_current;
	private Vector<Object[]> info_warning_error;
//	private iHort instance = null;  


	
public iHortService() {
	super();
}

public iHortService(Hashtable<String, report_element_base> extBeanLibrary) {
	super();
	if(extBeanLibrary!=null) _beanLibrary = extBeanLibrary;
}
 iHortService(String fname) {
	super();
	initXML(fname);
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
					if (element_current.isPreActive(_tagLibrary,_beanLibrary,_styleLibrary))	recompileProfile(nList.item(i),element_current);
					if (element_current.isActive(_tagLibrary,_beanLibrary,_styleLibrary)){
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
	if(_tagLibrary==null) _tagLibrary = new Hashtable<String, report_element_base>();
	if(_beanLibrary==null) _beanLibrary = new Hashtable<String, report_element_base>();
	if(_styleLibrary==null) _styleLibrary = new Hashtable<String, report_element_base>();
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

	bean _sysCanvas = new bean();
		_sysCanvas.setContent(new Vector<Object>());
		_sysCanvas.setName("SYSTEM");
		_sysCanvas.setID(iConst.iHORT_SYSTEM_Canvas);
		_beanLibrary.put(_sysCanvas.getName()+":"+_sysCanvas.getID(),_sysCanvas);	

	bean _sysParagraph = new bean();
		_sysParagraph.setContent(new Vector<Object>());
		_sysParagraph.setName("SYSTEM");
		_sysParagraph.setID(iConst.iHORT_SYSTEM_Paragraph);
		_beanLibrary.put(_sysParagraph.getName()+":"+_sysParagraph.getID(),_sysParagraph);	

	bean _sysPageBreak = new bean();
		_sysPageBreak.setContent(new Vector<Object>());
		_sysPageBreak.setName("SYSTEM");
		_sysPageBreak.setID(iConst.iHORT_SYSTEM_PageBreak);
		_beanLibrary.put(_sysPageBreak.getName()+":"+_sysPageBreak.getID(),_sysPageBreak);	

	bean _sysISBlank = new bean();
		_sysISBlank.setContent(new Boolean(false));
		_sysISBlank.setName("SYSTEM");
		_sysISBlank.setID(iConst.iHORT_SYSTEM_NOGENERATE);
		_beanLibrary.put(_sysISBlank.getName()+":"+_sysISBlank.getID(),_sysISBlank);		

		
}


public void setError(Exception e,String level) {
	setError(e,"",level);
}
public void setError(Exception e, String des, String level) {
	if(info_warning_error==null) info_warning_error=new Vector<Object[]>();
	info_warning_error.add(new Object[]{e,des,level});
	exception_managerService.exception_to_log(LOG_INTERCEPTOR, e,des,level);

}
private Object prepareBean(bean element) {

		return null;

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

public Hashtable<String, report_element_base> get_tagLibrery(){
	return this._tagLibrary;
}


private void write(String value) {
	if(saveAs==null) return;		
	try {
        ((java.io.DataOutputStream)(((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_saveAsFile+"_ids_"+this.hashCode())).getContent())).writeBytes(value);
	}catch(Exception e){}
}
public void initXML(String fname) {
	// TODO Auto-generated method stub
	
}
public Vector<Object[]> getInfo_warning_error() {
	return info_warning_error;
}

public Vector<Object[]> getInfo_warning_error(String type) {
	Vector<Object[]> result = new Vector<Object[]>();
	if(info_warning_error==null) info_warning_error=new Vector<Object[]>();
	for(int i=0;i<info_warning_error.size();i++){
		Object[] current = (Object[])info_warning_error.get(i);
		if(current[2].equals(type)) result.add(current);
	}
	return result;
}

public void clear() {
	element_current=null;
	if(info_warning_error!=null)
		info_warning_error.clear();
	info_warning_error=null;
	super.clear();
}
}


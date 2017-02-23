

package neohort.universal.output.lib_xls.general_util;


import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.PageOrientation;
import jxl.write.WritableWorkbook;
import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_xls.general;
import neohort.universal.output.util.I_StreamWrapper;
import neohort.universal.output.util.OutputRunTime;
import neohort.util.util_file;


public class general_j2ee {

public static void executeFirst(general body, Hashtable _tagLibrary, Hashtable _beanLibrary){
try{
	Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
	Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

	OutputStream oStream = null;
	if(included!=null && included.booleanValue()==true){}
	else{
		I_StreamWrapper iStreamWrapper = null;
		if(body.getSOURCE_DOCUMENT()==null) body.setSOURCE_DOCUMENT("");
		if(body.getCLASS_STREAM_WRAPPER()==null) body.setCLASS_STREAM_WRAPPER("");
		else{
			try{
				iStreamWrapper = (I_StreamWrapper)Class.forName(body.getCLASS_STREAM_WRAPPER()).newInstance();
			}catch(Exception e){
				body.setError(e);
			}
		}
		
		
		HttpServletResponse response = null;
		try{
			response = (HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
		}catch(Exception e){
		}
		if(response!=null){
			oStream = response.getOutputStream();
		}else{
			if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
				oStream = (OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent());
		}
		if (body.getTYPE_DOCUMENT()!=null  && !body.getSOURCE_DOCUMENT().equals("")){
			if(!body.getSOURCE_BEFORE_FIXED().equals("")){
				try{
					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
					if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
						((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_BEFORE_FIXED()).include(request,response);
				}catch(Exception e){
					e.toString();
				}
			}
		}
		if (response!=null){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT")){
				response.setHeader("Content-Disposition","attachment; filename="+body.ID+".xls");
				response.setHeader("Content-Transfer-Encoding","base64");
				response.setContentType("Application/xls");
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
				response.setHeader("Content-Type","Application/xls");
			}
		}
		if(body.getTEMPLATE()!=null && !body.getTEMPLATE().equals("")){
			Workbook workbook = null;
			HttpServletRequest request = null;
			try{
				request = (HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
			}catch(Exception e){}
			
			String path = normalizeURIPath(body.getTEMPLATE(),request);
			try{
				workbook = Workbook.getWorkbook(new File(path));
			}catch(Exception e){
				body.setError(e,iStub.log_WARN);
			}
			if(workbook==null){
				try{
					workbook = Workbook.getWorkbook(new URL(path).openStream());
				}catch(Exception e){
					body.setError(e,iStub.log_ERROR);
				}
			}
			WorkbookSettings settings = null;
			if(body.getENCODED()!=null){
				settings = new WorkbookSettings();
				settings.setEncoding(body.getENCODED());

			}
			
			if(workbook!=null){
				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals("")){
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()),workbook);
						else body.writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()),workbook,settings);
					}
				}else if(iStreamWrapper!=null){
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook);
						else body.writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook,settings);
					}
				}else{
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(oStream,workbook); 
						else body.writer = Workbook.createWorkbook(oStream,workbook,settings); 
					}
				}
				try{
					body.document = body.writer.getSheet(0);
				}catch(Exception e){	
					body.document = body.writer.createSheet("Sheet 0", 0);
				}
			}else{
				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals("")){
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()));
						else body.writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()),settings);
					}	
				}else if(iStreamWrapper!=null){
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary));
						else body.writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),settings);
					}	
				}else{
					if(!noGenerate.booleanValue()){
						if(settings==null) body.writer = Workbook.createWorkbook(oStream);
						else body.writer = Workbook.createWorkbook(oStream,settings); 
					}
				}
								 
				body.document = body.writer.createSheet("Sheet 0", 0);
			}
			bean _sysXlsT = new bean();
				_sysXlsT.setContent(new Boolean(true));
				_sysXlsT.setName("SYSTEM");
				_sysXlsT.setID(iConst.iHORT_SYSTEM_Excel_template_present);
				_beanLibrary.put(_sysXlsT.getName()+":"+_sysXlsT.getID(),_sysXlsT);
		}
		else{
			if(!noGenerate.booleanValue()){
				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals(""))
					body.writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()));
				else if (body.getTYPE_DOCUMENT()!=null && iStreamWrapper!=null)
					Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary));
				else
					body.writer = Workbook.createWorkbook(oStream);	
			}
			body.document = body.writer.createSheet("Sheet 0", 0);
			
			if(body.getORIENTATION()!=null && body.getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
				body.document.getSettings().setOrientation(PageOrientation.LANDSCAPE);
			else body.document.getSettings().setOrientation(PageOrientation.PORTRAIT);
			
			
			try{
				java.util.StringTokenizer st = new java.util.StringTokenizer(body.getMARGINS(), ",");

				body.document.getSettings().setLeftMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
				body.document.getSettings().setRightMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
				body.document.getSettings().setTopMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
				body.document.getSettings().setBottomMargin(Double.valueOf(st.nextToken()).doubleValue()/96);
				
	
			}catch(Exception e){
			}
			bean _sysXlsT = new bean();
				_sysXlsT.setContent(new Boolean(false));
				_sysXlsT.setName("SYSTEM");
				_sysXlsT.setID(iConst.iHORT_SYSTEM_Excel_template_present);
				_beanLibrary.put(_sysXlsT.getName()+":"+_sysXlsT.getID(),_sysXlsT);
			
		}

		bean _sysPdfWriter = new bean();
				_sysPdfWriter.setContent(body.writer);
				_sysPdfWriter.setName("SYSTEM");
				_sysPdfWriter.setID(iConst.iHORT_SYSTEM_Writer);
				_beanLibrary.put(_sysPdfWriter.getName()+":"+_sysPdfWriter.getID(),_sysPdfWriter);

		bean _sysDocument = new bean();
				_sysDocument.setContent(body.document);
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
	body.setError(e);
}
}
public static void executeLast(general body, Hashtable _tagLibrary, Hashtable _beanLibrary){
try{
	Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
	Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

	if(included!=null && included.booleanValue()==true){}
	else{
		I_StreamWrapper iStreamWrapper = null;

		if(body.getCLASS_STREAM_WRAPPER()!=null && !body.getCLASS_STREAM_WRAPPER().equals("")){
			try{
				iStreamWrapper = (I_StreamWrapper)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_STREAM_WRITER)).getContent();
			}catch(Exception e){
				body.setError(e);
			}
		}
		
		
		if(!noGenerate.booleanValue()){
			((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write();
			((WritableWorkbook)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
		}
		if (body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
			if(!body.getSOURCE_AFTER_FIXED().equals("")){
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
				if(request!=null && request.getParameter("source")!=null){
					if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
						((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_AFTER_FIXED()).include(request,response);
				}
			}
			if (body.motore.getClass().getName().indexOf("RunThread") > -1) {
				try{
					java.io.File fin = new java.io.File(body.getSOURCE_DOCUMENT()+"finished");
					fin.createNewFile();
				}catch(Exception e){}
			}
		}
		if (!body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && !body.getSOURCE_DOCUMENT().equals("")){
			HttpServletResponse response = null;
			try{
				response = (HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.getOutputStream().write(util_file.getBytesFromFile(body.getSOURCE_DOCUMENT()));
				response.flushBuffer();

			}catch(Exception e){
			}

		}else if (!body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && iStreamWrapper!=null){
			HttpServletResponse response = null;
			try{
				response = (HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.getOutputStream().write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
				response.flushBuffer();

			}catch(Exception e){
			}
			
		}
		
		_tagLibrary = new Hashtable();
		_beanLibrary = new Hashtable();

	}
}catch(Exception e){
	body.setError(e);
}
}

public static void setError(general body, Exception e) {
	try {
		body.isError = true;
		if(e.toString().indexOf("java.io.IOException")>-1){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
				if(!body.getSOURCE_ERROR_FIXED().equals("")){
					try{						
						((WritableWorkbook)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write();
						((WritableWorkbook)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
					}catch (Exception ex) {}
					javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
					if(request!=null && request.getParameter("source")!=null){
						if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
							((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_ERROR_FIXED()).include(request,response);
					}
				}
				if (body.motore.getClass().getName().indexOf("RunThread") > -1) {
					try{
						java.io.File fin = new java.io.File(body.getSOURCE_DOCUMENT()+"error");
						fin.createNewFile();
					}catch(Exception ex){}
				}
			}

		}else{
			((iHort)body.motore).setError(e,body.getName(),iStub.log_ERROR);
		}
	} catch (Exception ex) {
		ex.toString();
	}
}
public static String normalizeURIPath(String path, HttpServletRequest request) {
	
	String fullPath="";
	if(path==null) return "";
	if(	path.trim().toLowerCase().indexOf("http://")==0 ||
		path.trim().toLowerCase().indexOf("http:\\\\")==0){
		return path.trim();
	}	
	if(	path.trim().toLowerCase().indexOf("file://")==0 ||
		path.trim().toLowerCase().indexOf("file:\\\\")==0){
		return path.trim().substring(7,path.trim().length());
		}	
	if(	path.trim().toLowerCase().indexOf("/")==0 ||
		path.trim().toLowerCase().indexOf("\\")==0){
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
		return returnR+path.trim().substring(3,path.trim().length());	
	}
	return path;		
}
}

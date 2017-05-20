

package neohort.universal.output.lib_xlsx.general_util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_xlsx.general;
import neohort.universal.output.util.I_StreamWrapper;
import neohort.universal.output.util.OutputRunTime;
import neohort.util.util_file;


public class general_j2ee {

public static void executeFirst(general body, Hashtable _tagLibrary, Hashtable _beanLibrary){
try{
	
	Sheet document = null;

	
	Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
	Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());


	if(included!=null && included.booleanValue()==true){}
	else{
		Workbook workbook = null;
		
		I_StreamWrapper iStreamWrapper = null;
		if(body.getSOURCE_DOCUMENT()==null) body.setSOURCE_DOCUMENT("");
		if(body.getCLASS_STREAM_WRAPPER()==null) body.setCLASS_STREAM_WRAPPER("");
		else if(!body.getCLASS_STREAM_WRAPPER().equals("")){
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
				response.setHeader("Content-Disposition","attachment; filename="+body.ID+".xlsx");
				response.setHeader("Content-Transfer-Encoding","base64");
				response.setContentType("Application/xls");
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
				response.setHeader("Content-Type","Application/xls");
			}
		}

		
		if(body.getTEMPLATE()!=null && !body.getTEMPLATE().equals("")){
			
			HttpServletRequest request = null;
			try{
				request = (HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
			}catch(Exception e){}
			
			String path = normalizeURIPath(body.getTEMPLATE(),request);
			try{
				workbook = body.wrapSXSSF(new XSSFWorkbook(new File(path)));
			}catch(Exception e){
				body.setError(e,iStub.log_WARN);
			}
			if(workbook==null){
				try{
					workbook = body.wrapSXSSF(new XSSFWorkbook(new URL(path).openStream()));
				}catch(Exception e){
					body.setError(e,iStub.log_ERROR);
				}
			}
			

			
			if(workbook!=null){
//				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals("")){
//					if(!noGenerate.booleanValue()){
//						workbook = new XSSFWorkbook(new File(body.getSOURCE_DOCUMENT()));
//					}
//				}else if(iStreamWrapper!=null){
//					if(!noGenerate.booleanValue()){
//						if(settings==null) writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook);
//						else writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),workbook,settings);
//					}
//				}else{
//					if(!noGenerate.booleanValue()){
//						if(settings==null) writer = Workbook.createWorkbook(oStream,workbook); 
//						else writer = Workbook.createWorkbook(oStream,workbook,settings); 
//					}
//				}
				try{
					document = workbook.getSheetAt(0);
				}catch(Exception e){	
					document = workbook.createSheet("Sheet 0");
				}
			}else{
				workbook = body.wrapSXSSF(new XSSFWorkbook());
//				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals("")){
//					if(!noGenerate.booleanValue()){
//						if(settings==null) writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()));
//						else writer = Workbook.createWorkbook(new File(body.getSOURCE_DOCUMENT()),settings);
//					}	
//				}else if(iStreamWrapper!=null){
//					if(!noGenerate.booleanValue()){
//						if(settings==null) writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary));
//						else writer = Workbook.createWorkbook(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary),settings);
//					}	
//				}else{
//					if(!noGenerate.booleanValue()){
//						if(settings==null) writer = Workbook.createWorkbook(oStream);
//						else writer = Workbook.createWorkbook(oStream,settings); 
//					}
//				}
								 
				document = workbook.createSheet("Sheet 0");
			}
			bean _sysXlsT = new bean();
				_sysXlsT.setContent(new Boolean(true));
				_sysXlsT.setName("SYSTEM");
				_sysXlsT.setID(iConst.iHORT_SYSTEM_Excel_template_present);
				_beanLibrary.put(_sysXlsT.getName()+":"+_sysXlsT.getID(),_sysXlsT);
		}
		else{
			if(!noGenerate.booleanValue())
				workbook = body.wrapSXSSF(new XSSFWorkbook());
				
			
			document = workbook.createSheet("Sheet 0");
			
			if(body.getORIENTATION()!=null && body.getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
				document.getPrintSetup().setLandscape(true);
			else 
				document.getPrintSetup().setLandscape(false);
			
			
			
			
			try{
				java.util.StringTokenizer st = new java.util.StringTokenizer(body.getMARGINS(), ",");

				document.setMargin(Sheet.LeftMargin, Double.valueOf(st.nextToken()).doubleValue()/96);
				document.setMargin(Sheet.RightMargin, Double.valueOf(st.nextToken()).doubleValue()/96);
				document.setMargin(Sheet.TopMargin, Double.valueOf(st.nextToken()).doubleValue()/96);
				document.setMargin(Sheet.BottomMargin, Double.valueOf(st.nextToken()).doubleValue()/96);
				
	
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
			Workbook workbook = (Workbook)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Workbook)).getContent();
			if(workbook!=null){
				if (body.getTYPE_DOCUMENT()!=null && !body.getSOURCE_DOCUMENT().equals("")){
					if(!noGenerate.booleanValue()){
						OutputStream ostream = new FileOutputStream(new File(body.getSOURCE_DOCUMENT()));
						workbook.write(ostream);
						ostream.close();
					}
				}else if(iStreamWrapper!=null){
					if(!noGenerate.booleanValue()){
						workbook.write(iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary));
						
						HttpServletResponse response = null;
						try{
							response = (HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
							response.getOutputStream().write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
							iStreamWrapper.close();
							response.flushBuffer();

						}catch(Exception e){
						}
						
					}
				}else{
					if(!noGenerate.booleanValue()){
						HttpServletResponse response = null;
						try{
							response = (HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
						}catch(Exception e){
						}
						OutputStream oStream = null;
						if(response!=null){
							oStream = response.getOutputStream();
						}else{
							if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
								oStream = (OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent());
						}
						if(oStream!=null)
							workbook.write(oStream);
						oStream.close();
					}
				}
				workbook.close();
				workbook = null;
//				((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).setContent(workbook);
			}
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
				iStreamWrapper.close();
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
						Workbook workbook = (Workbook)((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Workbook)).getContent();
						if(workbook!=null){
							workbook.write(new FileOutputStream(new File(body.getSOURCE_ERROR_FIXED())));
							workbook.close();
							workbook = null;
							((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).setContent(workbook);
						}
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

package neohort.universal.output.lib_rtf.general_util;




import java.io.OutputStream;
import java.util.Hashtable;


import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_rtf.general;
import neohort.universal.output.util.OutputRunTime;
import neohort.util.util_file;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.rtf.RtfWriter2;



public class general_j2ee {

public general_j2ee() {
	super();
}
public static void executeFirst(general body, Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

		if(included!=null && included.booleanValue()==true){}
		else{
			if(body.getSOURCE_DOCUMENT()==null) body.setSOURCE_DOCUMENT("");
			javax.servlet.http.HttpServletResponse response = null;
			try{
				response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			}catch(Exception e){}
			if(body.getORIENTATION()!=null && body.getORIENTATION().trim().equalsIgnoreCase("LANDSCAPE"))
				body.setDocument(new Document(PageSize.A4.rotate()));
			else body.setDocument(new Document(PageSize.A4));
			try{
				int p1 = 30;
				int p2 = 30;
				int p3 = 30;
				int p4 = 30;
				java.util.StringTokenizer st = new java.util.StringTokenizer(body.getMARGINS(), ",");
					p1 = Integer.valueOf(st.nextToken()).intValue();
					p2 = Integer.valueOf(st.nextToken()).intValue();
					p3 = Integer.valueOf(st.nextToken()).intValue();
					p4 = Integer.valueOf(st.nextToken()).intValue();
				body.getDocument().setMargins(p1, p2, p3, p4);
			}catch(Exception e){
				body.getDocument().setMargins(30, 30, 30, 30);
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && !body.getSOURCE_DOCUMENT().equals("")){
				if(!noGenerate.booleanValue())
					body.setWriter(RtfWriter2.getInstance(body.getDocument(), new java.io.FileOutputStream(body.getSOURCE_DOCUMENT())));
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
			if (body.getTYPE_DOCUMENT()!=null && !body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && !body.getSOURCE_DOCUMENT().equals("")){
				if(!noGenerate.booleanValue())
					body.setWriter(RtfWriter2.getInstance(body.getDocument(), new java.io.FileOutputStream(body.getSOURCE_DOCUMENT())));
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
				if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT") &&  body.getSOURCE_DOCUMENT().equals("")){			
					response.setHeader("Content-Disposition","attachment; filename="+body.ID+".rtf");
					response.setHeader("Content-Transfer-Encoding","base64");
					response.setContentType("Application/pdf");
					if(!noGenerate.booleanValue()) body.setWriter(RtfWriter2.getInstance(body.getDocument(), response.getOutputStream()));
				}
				if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") && body.getSOURCE_DOCUMENT().equals("")){
					response.setHeader("Content-Type","Application/rtf");
					if(!noGenerate.booleanValue()) body.setWriter(RtfWriter2.getInstance(body.getDocument(), response.getOutputStream()));
				}
			}else{
				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
					if(!noGenerate.booleanValue()) body.setWriter(RtfWriter2.getInstance(body.getDocument(),(OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent())));
			}
			if(body.getWriter()!=null){
				body._beanLibrary = _beanLibrary;
			}
	
			bean _sysDocument = new bean();
					_sysDocument.setContent(body.getDocument());
					_sysDocument.setName("SYSTEM");
					_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
					_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);
	
			bean _sysRtfWriter2 = new bean();
					_sysRtfWriter2.setContent(body.getWriter());
					_sysRtfWriter2.setName("SYSTEM");
					_sysRtfWriter2.setID(iConst.iHORT_SYSTEM_Writer);
					_beanLibrary.put(_sysRtfWriter2.getName()+":"+_sysRtfWriter2.getID(),_sysRtfWriter2);
	
			bean _sysPdfPN = new bean();
					_sysPdfPN.setContent(new Integer("0"));
					_sysPdfPN.setName("SYSTEM");
					_sysPdfPN.setID(iConst.iHORT_SYSTEM_Document_PageNumber);
					_beanLibrary.put(_sysPdfPN.getName()+":"+_sysPdfPN.getID(),_sysPdfPN);
	
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
		if(!noGenerate.booleanValue()) ((RtfWriter2)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).flush();
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") && body.getSOURCE_DOCUMENT().equals("")){
			javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			for(int i=0;i<8000;i++)
				response.getOutputStream().print('\r');
		}
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
			if(!noGenerate.booleanValue()) ((RtfWriter2)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
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
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT") && !body.getSOURCE_DOCUMENT().equals("")){
			if(!noGenerate.booleanValue()) ((RtfWriter2)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
			javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			response.setHeader("Content-Disposition","attachment; filename="+body.ID+".pdf");
			response.setHeader("Content-Transfer-Encoding","base64");
			response.setContentType("Application/pdf");
			response.getOutputStream().write(util_file.getBytesFromFile(body.getSOURCE_DOCUMENT()));
			response.flushBuffer();
		}
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") && !body.getSOURCE_DOCUMENT().equals("")){
			if(!noGenerate.booleanValue()) ((RtfWriter2)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
			javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			response.setHeader("Content-Type","Application/pdf");
			response.getOutputStream().write(util_file.getBytesFromFile(body.getSOURCE_DOCUMENT()));
			response.flushBuffer();
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
						((RtfWriter2)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
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

}

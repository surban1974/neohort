package neohort.universal.output.lib_pdf.general_util;




import java.io.OutputStream;
import java.util.Hashtable;


import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.iHort;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_pdf.general;
import neohort.universal.output.util.I_StreamWrapper;
import neohort.universal.output.util.OutputRunTime;
import neohort.util.util_file;



import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class general_j2ee {

public general_j2ee() {
	super();
}
public static void executeFirst(general body, Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		Boolean noGenerate = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_NOGENERATE)).getContent());

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
			if(!body.getSOURCE_DOCUMENT().equals("")){
				if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") ){
					if(!noGenerate.booleanValue())
						body.setWriter(PdfWriter.getInstance(body.getDocument(), new java.io.FileOutputStream(body.getSOURCE_DOCUMENT())));
					if(!body.getSOURCE_BEFORE_FIXED().equals("")){
						try{
							javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
							if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
								((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_BEFORE_FIXED()).include(request,response);
						}catch(Exception e){
							body.setError(e);
	//						e.toString();
						}
					}	
				}
				if (body.getTYPE_DOCUMENT()!=null && !body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") ){
					if(!noGenerate.booleanValue())
						body.setWriter(PdfWriter.getInstance(body.getDocument(), new java.io.FileOutputStream(body.getSOURCE_DOCUMENT())));
					if(!body.getSOURCE_BEFORE_FIXED().equals("")){
						try{
							javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
							if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
								((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_BEFORE_FIXED()).include(request,response);
						}catch(Exception e){
							body.setError(e);
	//						e.toString();
						}
					}	
				}
			}else if(iStreamWrapper!=null){
				if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") ){
					if(!noGenerate.booleanValue())
						body.setWriter(PdfWriter.getInstance(body.getDocument(), iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary)));
					if(!body.getSOURCE_BEFORE_FIXED().equals("")){
						try{
							javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
							if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
								((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_BEFORE_FIXED()).include(request,response);
						}catch(Exception e){
							body.setError(e);
	//						e.toString();
						}
					}	
				}
				if (body.getTYPE_DOCUMENT()!=null && !body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") ){
					if(!noGenerate.booleanValue())
						body.setWriter(PdfWriter.getInstance(body.getDocument(), iStreamWrapper.createOutputStream(_tagLibrary, _beanLibrary)));
					if(!body.getSOURCE_BEFORE_FIXED().equals("")){
						try{
							javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
							if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
								((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_BEFORE_FIXED()).include(request,response);
						}catch(Exception e){
							body.setError(e);
	//						e.toString();
						}
					}	
				}
				
			}
			
			if (response!=null){
				if(body.getSOURCE_DOCUMENT().equals("") && iStreamWrapper==null){
					if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT")){			
						response.setHeader("Content-Disposition","attachment; filename="+body.ID+".pdf");
						response.setHeader("Content-Transfer-Encoding","base64");
						response.setContentType("Application/pdf");
						if(!noGenerate.booleanValue()) body.setWriter(PdfWriter.getInstance(body.getDocument(), response.getOutputStream()));
					}
					if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
						response.setHeader("Content-Type","Application/pdf");
						if(!noGenerate.booleanValue()) body.setWriter(PdfWriter.getInstance(body.getDocument(), response.getOutputStream()));
					}
				}
			}else{
				if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)!=null)
					if(!noGenerate.booleanValue()) body.setWriter(PdfWriter.getInstance(body.getDocument(),(OutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_INPUT_$external_output_stream)).getContent())));
			}
			if(body.getWriter()!=null){
				body._beanLibrary = _beanLibrary;
				general.PageEvent_0 pev = body.new PageEvent_0();
				body.getWriter().setPageEvent(pev);
				if(!body.getDIRECTION().equals("") && body.getDIRECTION().equalsIgnoreCase("RTL")){
					body.getWriter().setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
				}
			}
	
			bean _sysDocument = new bean();
					_sysDocument.setContent(body.getDocument());
					_sysDocument.setName("SYSTEM");
					_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
					_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);
	
			bean _sysPdfWriter = new bean();
					_sysPdfWriter.setContent(body.getWriter());
					_sysPdfWriter.setName("SYSTEM");
					_sysPdfWriter.setID(iConst.iHORT_SYSTEM_Writer);
					_beanLibrary.put(_sysPdfWriter.getName()+":"+_sysPdfWriter.getID(),_sysPdfWriter);
	
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
public static void executeLast(general body, Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
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

		
		if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).flush();
		
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") && body.getSOURCE_DOCUMENT().equals("") && iStreamWrapper==null){
			javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			for(int i=0;i<8000;i++)
				response.getOutputStream().print('\r');
		}
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
			if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
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
		if(!body.getSOURCE_DOCUMENT().equals("")){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT")){
				if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.setHeader("Content-Disposition","attachment; filename="+body.ID+".pdf");
				response.setHeader("Content-Transfer-Encoding","base64");
				response.setContentType("Application/pdf");
				response.getOutputStream().write(util_file.getBytesFromFile(body.getSOURCE_DOCUMENT()));
				response.flushBuffer();
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
				if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.setHeader("Content-Type","Application/pdf");
				response.getOutputStream().write(util_file.getBytesFromFile(body.getSOURCE_DOCUMENT()));
				response.flushBuffer();
			}		
		}else if(iStreamWrapper!=null){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT")){
				if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.setHeader("Content-Disposition","attachment; filename="+body.ID+".pdf");
				response.setHeader("Content-Transfer-Encoding","base64");
				response.setContentType("Application/pdf");
				response.getOutputStream().write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
				response.flushBuffer();
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
				if(!noGenerate.booleanValue()) ((PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				response.setHeader("Content-Type","Application/pdf");
				response.getOutputStream().write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
				response.flushBuffer();
			}		
		}
		_tagLibrary = new Hashtable<String, report_element_base>();
		_beanLibrary = new Hashtable<String, report_element_base>();

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
						((PdfWriter)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
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

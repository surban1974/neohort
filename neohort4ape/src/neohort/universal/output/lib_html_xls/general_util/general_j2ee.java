

package neohort.universal.output.lib_html_xls.general_util;

import java.io.DataOutputStream;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_html_xls.document;
import neohort.universal.output.lib_html_xls.general;
import neohort.universal.output.util.OutputRunTime;

public class general_j2ee{

public static void executeFirst(general body, Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{
		javax.servlet.http.HttpServletResponse response = null;
		try{
			response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
		}catch(Exception e){}

		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && body.getSOURCE_DOCUMENT()!=null){
//			body.writer = new java.io.DataOutputStream(new BufferedOutputStream(new FileOutputStream(body.getSOURCE_DOCUMENT(),false)));
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
			body.document = new document();
			if(body.getTYPE_RESPONSE().trim().equalsIgnoreCase("HTML")) body.setTYPE_DOCUMENT("STREAM");
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT") && body.getSOURCE_DOCUMENT()!=null){
				body.writer = new java.io.DataOutputStream(response.getOutputStream());
				response.setHeader("Content-Disposition","attachment; filename="+body.ID+".xls");
				response.setHeader("Content-Transfer-Encoding","base64");
				response.setContentType("Application/xls");
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") && body.getSOURCE_DOCUMENT()!=null){
				body.writer = new java.io.DataOutputStream(response.getOutputStream());
				if(body.getTYPE_RESPONSE().trim().equalsIgnoreCase("XLS"))
					response.setHeader("Content-Type","Application/xls");
			}
		}

		if(body.writer!=null){
			body._beanLibrary = _beanLibrary;
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

			body._header = "<HTML xmlns:o=\"urn:schemas-microsoft-com:office:office\""+body._separator()+
						"xmlns:x=\"urn:schemas-microsoft-com:office:excel\""+body._separator()+
						"xmlns=\"http://www.w3.org/TR/REC-html40\">"+body._separator();
			body._header+="<HEAD>"+body._separator();
			body._header+=body._meta();
			body._header+="</HEAD>"+body._separator()+"<BODY>"+body._separator();


		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(body._header);

		}
	}catch(Exception e){
		body.setError(e);
	}
}
public static void executeLast(general body,Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
		if(included!=null && included.booleanValue()==true){}
		else{
			body._footer = "</BODY>"+body._separator()+"</HTML>"+body._separator();
			body.document = (document)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
			body.prepareComment(_tagLibrary,_beanLibrary);
			if (body.getTYPE_DOCUMENT()!=null && (body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM") || body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("ATTACHMENT"))){
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				if (response!=null){
					((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(body._comment+body._content+body._footer);				}
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(body._comment+body._content+body._footer);
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
				if(!body.getSOURCE_AFTER_FIXED().equals("")){
					javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
					if(request!=null && request.getParameter("source")!=null){
						if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
							((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_AFTER_FIXED()).include(request,response);
					}
				}
				if (body.motore.getClass().getName().indexOf("XLSRunThread") > -1) {
					try{
						java.io.File fin = new java.io.File(body.getSOURCE_DOCUMENT()+"finished");
						fin.createNewFile();
					}catch(Exception e){}
				}
			}

		_tagLibrary = new Hashtable();
		_beanLibrary = new Hashtable();
		}

	}catch(Exception e){
		body.setError(e);
	}
}

public static void setError(general body,Exception e) {
	try {
		body.isError = true;
		if(e.toString().indexOf("java.io.IOException")>-1){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
				if(!body.getSOURCE_ERROR_FIXED().equals("")){
					try{
						((DataOutputStream)(((report_element_base)body._beanLibrary.get("SYSTEM:Writer")).getContent())).close();
					}catch (Exception ex) {}
					javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)body._beanLibrary.get("SYSTEM:Response")).getContent());
					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)body._beanLibrary.get("SYSTEM:Request")).getContent());
					if(request!=null && request.getParameter("source")!=null){
						if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
							((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_ERROR_FIXED()).include(request,response);
					}
				}
				if (body.motore.getClass().getName().indexOf("XLSRunThread") > -1) {
					try{
						java.io.File fin = new java.io.File(body.getSOURCE_DOCUMENT()+"error");
						fin.createNewFile();
					}catch(Exception ex){}
				}
			}

		}else{
			body.motore.setError(e,body.getName(),iStub.log_ERROR);
		}
	} catch (Exception ex) {
		ex.toString();
	}
}

}

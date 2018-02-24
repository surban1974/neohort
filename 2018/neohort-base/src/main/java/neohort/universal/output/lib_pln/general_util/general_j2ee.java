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

package neohort.universal.output.lib_pln.general_util;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib_pln.document;
import neohort.universal.output.lib_pln.general;
import neohort.universal.output.util.I_StreamWrapper;
import neohort.universal.output.util.OutputRunTime;

public class general_j2ee{


public static void executeFirst(general body,  Hashtable _tagLibrary, Hashtable _beanLibrary){
try{
	Boolean included = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
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

		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED") && body.getSOURCE_DOCUMENT()!=null){
			body.writer = new java.io.DataOutputStream(new BufferedOutputStream(new FileOutputStream(body.getSOURCE_DOCUMENT(),false)));
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
				body.writer = new java.io.DataOutputStream(response.getOutputStream());
				if(!body.getCONTENT_DISPOSITION().trim().equals("")) response.setHeader("Content-Disposition",body.getCONTENT_DISPOSITION());
				else response.setHeader("Content-Disposition","attachment; filename="+body.ID+".txt");
				if(!body.getCONTENT_TRANSFER_ENCODING().trim().equals("")) response.setHeader("Content-Transfer-Encoding",body.getCONTENT_TRANSFER_ENCODING());
				else response.setHeader("Content-Transfer-Encoding","base64");
				if(!body.getCONTENT_TYPE().trim().equals("")) response.setContentType(body.getCONTENT_TYPE());
				else response.setContentType("Application/txt");
			}
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){
				body.writer = new java.io.DataOutputStream(response.getOutputStream());
				if(!body.getCONTENT_TYPE().trim().equals("")) response.setHeader("Content-Type",body.getCONTENT_TYPE());
			}
		}

		if(body.writer!=null){
			body._beanLibrary = _beanLibrary;
		}


		bean _sysDocument = new bean();
				_sysDocument.setContent(new document());
				_sysDocument.setName("SYSTEM");
				_sysDocument.setID(iConst.iHORT_SYSTEM_Document);
				_beanLibrary.put(_sysDocument.getName()+":"+_sysDocument.getID(),_sysDocument);

		bean _sysPdfWriter = new bean();
				_sysPdfWriter.setContent(body.writer);
				_sysPdfWriter.setName("SYSTEM");
				_sysPdfWriter.setID(iConst.iHORT_SYSTEM_Writer);
				_beanLibrary.put(_sysPdfWriter.getName()+":"+_sysPdfWriter.getID(),_sysPdfWriter);

		bean _sysPdfPN = new bean();
				_sysPdfPN.setContent(new Integer("0"));
				_sysPdfPN.setName("SYSTEM");
				_sysPdfPN.setID(iConst.iHORT_SYSTEM_Document_PageNumber);
				_beanLibrary.put(_sysPdfPN.getName()+":"+_sysPdfPN.getID(),_sysPdfPN);

		if(iStreamWrapper==null)
			((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes("");
		else{
			iStreamWrapper.createOutputStream("".getBytes(), _tagLibrary, _beanLibrary);

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

		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("STREAM")){

			javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
			if (response!=null){
				if(iStreamWrapper==null)
					((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(body._content);
				else
					((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
				
			}
		_tagLibrary = new Hashtable();
		_beanLibrary = new Hashtable();
		}
		if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
			if(iStreamWrapper==null){
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(body._content);
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
			}else{
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).write(iStreamWrapper.getByteFromStream(_tagLibrary, _beanLibrary));
				((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
			}
			if(!body.getSOURCE_AFTER_FIXED().equals("")){
				javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
				javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
				if(request!=null && request.getParameter("source")!=null){
					if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
						((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_AFTER_FIXED()).include(request,response);
				}
			}
			if (body.motore.getClass().getName().indexOf("PLNRunThread") > -1) {
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

public static void setError(general body, Exception e) {
	try {
		body.isError = true;
		if(e.toString().indexOf("java.io.IOException")>-1){
			if (body.getTYPE_DOCUMENT()!=null && body.getTYPE_DOCUMENT().trim().equalsIgnoreCase("FIXED")){
				if(!body.getSOURCE_ERROR_FIXED().equals("")){
					try{
						((DataOutputStream)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).close();
					}catch (Exception ex) {}
					javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Response)).getContent());
					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)(((report_element_base)body._beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Request)).getContent());
					if(request!=null && request.getParameter("source")!=null){
						if(body.motore!=null && ((OutputRunTime)body.motore).getAnotherServletConfig()!=null)
							((OutputRunTime)body.motore).getAnotherServletConfig().getServletContext().getRequestDispatcher(body.getSOURCE_ERROR_FIXED()).include(request,response);
					}
				}
				if (body.motore.getClass().getName().indexOf("PLNRunThread") > -1) {
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

package neohort.service.filter;




import java.io.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neohort.universal.output.iConst;


public class filter_jsp implements Filter {
	public void destroy() {

	}
	public void doFilter(ServletRequest req,ServletResponse resp,FilterChain chain) 	throws ServletException, IOException {
		boolean elaborated=false;
		if(req instanceof HttpServletRequest){
			String url = ((HttpServletRequest)req).getQueryString();
			String uri = ((HttpServletRequest)req).getRequestURI();
			
			HttpServletRequest request = (HttpServletRequest)req; 
			HttpServletResponse response = (HttpServletResponse)resp; 
			String xmlSource=null;
			
			if(	url!=null){
				if(	uri.toUpperCase().lastIndexOf("/REPORT_CREATOR")==-1 &&
					(url.toUpperCase().indexOf("REPORTPROVIDER=NEOHORT")>-1 ||
					(request.getParameter("ReportProvider")!=null && request.getParameter("ReportProvider").equals("neoHort"))
					)
				){
					try{
						CharResponseWrapper responseWrapper =  new CharResponseWrapper((HttpServletResponse)resp);
						chain.doFilter(req, responseWrapper);
						xmlSource = responseWrapper.toString();	
						xmlSource = analizeXML(xmlSource);
						request.setAttribute(iConst.iHORT_INPUT_$source_stream,xmlSource);
						request.getSession().getServletContext().getRequestDispatcher("/report_creator").forward(request,(HttpServletResponse)resp);
						elaborated = true;
					}catch(Exception ex){					
					}
				}				
			}
			if(	!elaborated &&
				uri.toUpperCase().lastIndexOf("/REPORT_CREATOR")==uri.length()-15 &&
				request.getParameter(iConst.iHORT_INPUT_$source)!=null &&
				request.getParameter(iConst.iHORT_INPUT_$source).toUpperCase().lastIndexOf(".JSP")>-1
				){
					try{

						CharResponseWrapper responseWrapper =  new CharResponseWrapper((HttpServletResponse)resp);
						request.getSession().getServletContext().getRequestDispatcher(request.getParameter(iConst.iHORT_INPUT_$source)).forward(request,responseWrapper);
						xmlSource = responseWrapper.toString();	
							

						xmlSource = analizeXML(xmlSource);
						request.setAttribute(iConst.iHORT_INPUT_$source_stream,xmlSource);
						resp.flushBuffer();
//						resp.resetBuffer();
//						resp.reset();
						chain.doFilter(req, resp);
						elaborated = true;
							
					}catch(Exception ex){
						response.getWriter().write(ex.toString());					
					}catch(Throwable th){
						response.getWriter().write(th.toString());					
					}

				}				

/*			
			if(elaborated){
				try{
				
					request.setAttribute(iConst.iHORT_INPUT_$source_stream,xmlSource);
					
					request.getSession().getServletContext().getRequestDispatcher("/report_creator").forward(request,(HttpServletResponse)resp);
					elaborated = true;
					
				}catch(Exception ex){					
				}				
			}
*/			
		}

		if(!elaborated) chain.doFilter(req, resp);

	}
	public void init(FilterConfig config) throws ServletException {

	}
	public String analizeXML(String input){
		if(	input!=null && input.toUpperCase().indexOf("<?XML")!=0 && input.toUpperCase().indexOf("<?XML")>-1) 
			input = input.substring(input.toUpperCase().indexOf("<?XML"));
	return input;	
		
	}

}

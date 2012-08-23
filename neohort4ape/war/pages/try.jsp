<%@ page import="java.io.*" %>
<link href="light_menu.css" rel="stylesheet" type="text/css">
<script src="light_menu.js"></script> 


<%!


public static String replace (String target, String from, String to) {   
	int start = target.indexOf (from);
  	if (start==-1) return target;
  	int lf = from.length();
  	char [] targetChars = target.toCharArray();
  	StringBuffer buffer = new StringBuffer();
  	int copyFrom=0;
  	while (start != -1) {
    	buffer.append (targetChars, copyFrom, start-copyFrom);
    	buffer.append (to);
    	copyFrom=start+lf;
    	start = target.indexOf (from, copyFrom);
    }
  	buffer.append (targetChars, copyFrom, targetChars.length-copyFrom);
  	return buffer.toString();
}



public String prepareData(HttpServletRequest req){
	String file = "";
	try{
		DataInputStream in = null;
		String contentType = req.getContentType();
		if (contentType != null && contentType.indexOf("multipart/form-data") != -1) {
			in = new DataInputStream(req.getInputStream());
			int formDataLength = req.getContentLength();

			byte dataBytes[] = new byte[formDataLength];
			int bytesRead = 0;
			int totalBytesRead = 0;
			while (totalBytesRead < formDataLength) {
				bytesRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += bytesRead;
			}

			file = new String(dataBytes,0,dataBytes.length);
		
			dataBytes = null;
	
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1, contentType.length());
			int pos=0;			
			String separator = "\n";
			if(file.indexOf(separator)==-1) separator = "\r";
			pos = (file.indexOf("filename=\"")==-1)?pos:file.indexOf("filename=\"");
			pos = (file.indexOf(separator, pos)==-1)?pos:file.indexOf(separator, pos) + 1;
			pos = (file.indexOf(separator, pos)==-1)?pos:file.indexOf(separator, pos) + 1;
			pos = (file.indexOf(separator, pos)==-1)?pos:file.indexOf(separator, pos) + 1;
			int boundaryLocation = (file.indexOf(boundary, pos)==-1)?file.length():file.indexOf(boundary, pos) - 4;
			file =  file.substring(pos, boundaryLocation);
			in.close();
		}
		
	}catch(Exception e){
		file+="<span class='text_def'>ERRORE PARSING: "+e.toString()+"</span>";	
		return file;
	}
	return file;
}

%>

<%
	String xml_source = prepareData(request);
	if(xml_source!=null && !xml_source.equals("")){
		request.setAttribute("$source_stream",xml_source);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/report_creator");
		rd.forward(request,response); 
	}
	
%>

<br>
<br>
<br>
<form 	enctype="multipart/form-data" action="try.jsp" method="POST">
	

<table >
<tr> 
	<td width=200 class="block_background">
		<span class="page_section_text">
			Select new template
		</span>		
	</td>
	<td align="left" class="page_section">
		<input type="FILE" name="Filename" value="" maxlength=255 size=60>		
		<input type="submit" value="Load...">
	</td>
</tr>

</table>

</form> 

<jsp:include page="menu.jsp" flush="true"/>
<script>
document.getElementById("page_s_c_1").className = "page_section_active";
document.getElementById("button_s_c_15").className = "page_section_active";
</script>


<%
%>


	<link href="light_menu.css" rel="stylesheet" type="text/css">
<script src="light_menu.js"></script> 
<br>
<br>
<br>
<form>

<table >

<tr> 
	<td  align=left class="block_background" >
		<span class="page_section_text">
<font color="red"><b>NEW</b></font>. <br>
You can create new report from SIMPLE JSP file (this file must generate xml-template as result of its elaboration). Example of use: http://localhost/neoHort/log_view.jsp?<b>ReportProvider=neoHort</b>
The parameter ReportPrivider is obligated.
(In begin is necessity  to map into your WEB.XML the next filter <b>filter_jsp</b>)
		<br> <font color="red" size="1">
&lt;filter&gt;<br>
	&lt;filter-name&gt;filter_jsp&lt;/filter-name&gt;<br>
	&lt;display-name&gt;filter_jsp&lt;/display-name&gt;<br>
	&lt;description&gt;&lt;/description&gt;<br>
	&lt;filter-class&gt;neohort.service.filter.filter_jsp&lt;/filter-class&gt;<br>
&lt;/filter&gt;<br>
&lt;filter-mapping&gt;<br>
	&lt;filter-name&gt;filter_jsp&lt;/filter-name&gt;<br>
	&lt;url-pattern&gt;*.jsp&lt;/url-pattern&gt;<br>
&lt;/filter-mapping&gt;			
		)</font></span>
		<br>
		<a href="javascript:void(startURL('/neoHort/chart/jsp/log_view.jsp?ReportProvider=neoHort&$lib=pdf'));">
			<img src="../images/pdf_jsp.gif" border="0" alt="[from JSP - Pdf version]">			
		</a>
		<a href="javascript:void(startURL('/neoHort/chart/jsp/log_view.jsp?ReportProvider=neoHort&$lib=xls'));">
			<img src="../images/xls_jsp.gif" border="0" alt="[from JSP - Excel version]">
		</a>
		<a href="javascript:void(startURL('/neoHort//chart/jsp/log_view.jsp'));">
			<img src="../images/txt.gif" border="0" alt="[Source (compilated jsp)]">
		</a>	
		
		
	</td>	
	<td width=250 align=center class="page_section">
		<span class="page_section_text"><font color="red"><b>(In construction)</b></font> Prepare report in normal mode with $source=/*/*/file.jsp (this JSP must generate xml-template as result).</span>
		<br>
		<a href="javascript:void(startURL('/neoHort/report_creator?$source=/chart/jsp/log_view.jsp&$lib=pdf'));">
			<img src="../images/pdf.gif" border="0" alt="[Pdf version]">
		</a>
		<a href="javascript:void(startURL('/neoHort/report_creator?$source=/chart/jsp/log_view.jsp&$lib=xls'));">
			<img src="../images/xls.gif" border="0" alt="[html/xls version]">
		</a>
	</td>
	
</tr>	

</table>

</form> 

<!--  FUNZIONI  -->
<jsp:include page="menu.jsp" flush="true"/>
<script>
document.getElementById("page_s_c_1").className = "page_section_active";
document.getElementById("button_s_c_10").className = "page_section_active";
</script>

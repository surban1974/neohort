<link href="light_menu.css" rel="stylesheet" type="text/css">
<script src="light_menu.js"></script> 
<br>
<br>
<br>
<form>

<table>
<tr>
	<td width=250 align=center class="page_section">
	<span class="page_section_text">Table Example 1</span>
	<br>
	<a href="javascript:void(showAsIFrame('/neoHort/report_creator?$source=/neoHort/chart/table0.xml&$lib=pdf&$type=stream',900,500));">
		<img src="../images/pdf.gif" border="0" alt="[Pdf version]">
	</a>
	<a href="javascript:void(location.replace('/neoHort/report_creator?$source=/neoHort/chart/table0.xml&$lib=html_xls&$type=attachment'));">
		<img src="../images/xls_html.gif" border="0" alt="[xls/html]">
	</a>
	<a href="javascript:void(location.replace('/neoHort/report_creator?$source=/neoHort/chart/table0.xml&$lib=xls&$type=attachment'));">
		<img src="../images/xls.gif" border="0" alt="[Excel with tamplate]">
	</a>
	<a href="javascript:void(location.replace('/neoHort/report_creator?$source=/neoHort/chart/table01.xml&$lib=xls&$type=attachment'));">
		<img src="../images/xls.gif" border="0" alt="[Excel without tamplate]">
	</a>
	<a href="javascript:void(location.replace('/neoHort/report_creator?$source=/neoHort/chart/table01_rtf.xml&$lib=rtf&$type=attachment'));">
		<img src="../images/rtf.gif" border="0" alt="[RTF]">
	</a>	
	<a href="javascript:void(startURL('/neoHort/chart/table0.xml'));">
		<img src="../images/txt.gif" border="0" alt="[Source]">
	</a>
	</td>
	<td width=250 align=center class="block_background">
		<img src="../images/table0.gif">		
	</td>
</tr>
<tr>
	<td width=250 align=center class="block_background">
		<img src="../images/table1.gif">
	</td>

	<td width=250 align=center class="page_section">
	<span class="page_section_text">Table Example 2</span>
	<br>
	<a href="javascript:void(showAsIFrame('/neoHort/report_creator?$source=/neoHort/chart/table1.xml&$lib=pdf&$type=stream',900,500));">
		<img src="../images/pdf.gif" border="0" alt="[Pdf version]">
	</a>
	<a href="javascript:void(startURL('/neoHort/chart/table1.xml'));">
		<img src="../images/txt.gif" border="0" alt="[Source]">
	</a>
	</td>
</tr>
</table>
</form> 

<!--  FUNZIONI  -->
<jsp:include page="menu.jsp" flush="true"/>
<script>
document.getElementById("page_s_c_1").className = "page_section_active";
document.getElementById("button_s_c_11").className = "page_section_active";
</script>

<link href="light_menu.css" rel="stylesheet" type="text/css">
<script src="light_menu.js"></script> 
<br>
<br>
<br>
<form>

<table cellspacing="10" cellpadding="4">
<tr>
	<td width=250 align=center class="page_section">
		<span class="page_section_text">Chart Example 1</span>
		<br>
		<a href="javascript:void(showAsIFrame('/report_creator?$source=/chart/chart0.xml',900,500));">
			<img src="../images/pdf.gif" border="0" alt="[Pdf version]">			
		</a>	
		<a href="javascript:void(startURL('/chart/chart0.xml'));">		
			<img src="../images/txt.gif" border="0" alt="[Source]">
		</a>
	</td>
	<td width=250 align=center class="block_background">
		<img src="../images/chart0.gif">
	</td>
</tr>
<tr>
	<td width=250 align=center class="block_background">
		<img src="../images/chart1.gif">
	</td> 

	<td width=250 align=center class="page_section">
		<span class="page_section_text">Chart Example 2</span>
		<br>
		<a href="javascript:void(showAsIFrame('/report_creator?$source=/chart/chart1.xml',900,500));">
		<img src="../images/pdf.gif" border="0" alt="[Pdf version]">			
		</a>	
		
		<a href="javascript:void(startURL('/chart/chart1.xml'));">
			<img src="../images/txt.gif" border="0" alt="[Source]">
		</a>
	</td>
</tr>
<tr>
	<td width=250 align=center class="page_section">
		<span class="page_section_text">Chart Example 3</span>
		<br>
		<a href="javascript:void(showAsIFrame('/report_creator?$source=/chart/chart2.xml',900,500));">
			<img src="../images/pdf.gif" border="0" alt="[Pdf version]">			
		</a>		
		<a href="javascript:void(startURL('/chart/chart2.xml'));">
			<img src="../images/txt.gif" border="0" alt="[Source]">
		</a>
	</td>
	<td width=250 align=center class="block_background">
		<img src="../images/chart2.gif">
	</td>
</tr>
<tr>
	<td width=250 align=center class="block_background">
		<img src="../images/chart3.gif">
	</td>

	<td width=250 align=center class="page_section">
		<span class="page_section_text">Chart Example 4</span>
		<br>
		<a href="javascript:void(showAsIFrame('/report_creator?$source=/chart/chart3.xml',900,500));">
			<img src="../images/pdf.gif" border="0" alt="[Pdf version]">			
		</a>			
		
		<a href="javascript:void(startURL('/chart/chart3.xml'));">
			<img src="../images/txt.gif" border="0" alt="[Source]">
		</a>
	</td>
</tr>
<tr>
	<td width=250 align=center class="page_section">
		<span class="page_section_text">Chart Example 5</span>
		<br>
		<a href="javascript:void(showAsIFrame('/report_creator?$source=/chart/chart4.xml',900,500));">
			<img src="../images/pdf.gif" border="0" alt="[Pdf version]">			
		</a>	
		
		<a href="javascript:void(startURL('/chart/chart4.xml'));">
			<img src="../images/txt.gif" border="0" alt="[Source]">
		</a>
	</td>

	<td width=250 align=center class="block_background">
		<img src="../images/chart4.gif">
	</td>
</tr>
</table>

</form>

<!--  FUNCTIONS  -->
<jsp:include page="menu.jsp" flush="true"/>
<script>
document.getElementById("page_s_c_1").className = "page_section_active";
document.getElementById("button_s_c_12").className = "page_section_active";
</script>

<!--  FUNZIONI  -->
<jsp:include page="light_menu.jsp?PathImage=&PathJs=&PathMenu=&TH=td&TD=td" flush="true"/>


<div id="page1" name="page1" style="position:absolute;visibility: hidden">


<table cellspacing="0" border="0" class="block_background">
	<tr>
		<td><script>ObjectDraw("page1","button",13,"About/Documentation","actionAbout","page_section","","images/menu/","",true,24);</script></td>
		<td></td>	
		<td><script>ObjectDraw("page1","button",10,"First steps","actionGuide","page_section","","images/menu/","",true,24);</script></td>
		<td></td>		
		<td><script>ObjectDraw("page1","button",16,"<font color=red>NEW:jsp-generator</font>","actionFromJsp","page_section","","images/menu/","",true,24);</script></td>
		<td></td>		
		<td><script>ObjectDraw("page1","button",11,"Tables","actionTables","page_section","","images/menu/","",true,24);</script></td>
		<td></td>		
		<td><script>ObjectDraw("page1","button",12,"Charts","actionChart","page_section","","images/menu/","",true,24);</script></td>
		<td></td>	
		<td><script>ObjectDraw("page1","button",15,"Try Your report...","actionTry","page_section","","images/menu/","",true,24);</script></td>
    	</tr>   	
</table>
</div>

<script>
function actionChart(){
	location.replace("chart.jsp");
}
function actionGuide(){
	location.replace("content.jsp");
}
function actionFromJsp(){
	location.replace("fromjsp.jsp");
}
function actionTables(){
	location.replace("tables.jsp");
}
function actionAbout(){
	location.replace("about.jsp");
}
function actionTry(){
	location.replace("try.jsp");
}
function actionExit(){
	parent.window.close();
}

</script>
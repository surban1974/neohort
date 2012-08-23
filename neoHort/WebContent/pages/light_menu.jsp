<jsp:useBean id="Errori"
                            type="java.util.Vector"
                            class="java.util.Vector"
                            scope="session"/>

<%!
String prepError(String Error){
	String result="";
	String buf = Error;
	while(buf.length()>80){
		result+=buf.substring(0,80)+"<br>&nbsp;";
		buf=buf.substring(80);
	}
	result+=buf;
	return result;
}
%>
<%
String PathImage = ( request.getParameter("PathImage")==null ) ? "" : request.getParameter("PathImage");
String PathMenu = ( request.getParameter("PathMenu")==null ) ? "" : request.getParameter("PathMenu");

boolean checkError = true;
%>

<link href='light_menu.css' rel='stylesheet' type='text/css'>


<!-- BAR GENERAL -->

<script>
	if((IE4)||(NS6)) document.write("<div id=\"bar0\" name=\"content\" style=\"position:absolute;visibility: hidden\">");
	if(NS4) document.write("<layer name='bar0'  visibility='hide'><form>");
</script>


<table cellpadding="0" cellspacing="0" border="0" class="block_background">
	<tr>
		<td><script>ObjectDraw("bar0","page",1,"[actions]","f_azioni","page_section","","<%=PathImage%>");</script></td>
		<td><script>ObjectDraw("bar0","page",2,"[report of action]","f_errori","page_section","","<%=PathImage%>");</script></td>
		<td><script>ObjectDraw("bar0","page",3,"[find]","f_ricerca","page_section","","<%=PathImage%>");</script></td>		

    	</tr> 
</table>


<script>
if((IE4) || (NS6)) document.write("</div>");
if(NS4) document.write("</form></layer>");
</script>

<!-- PAGE ERRORS -->

<script>
if((IE4)||(NS6)) document.write("<div id=\"page2\" name=\"page2\" style=\"position:absolute;visibility: hidden\">");
if(NS4) document.write("<layer name='page2'  visibility='hide'><form>");
</script>
<table cellspacing="0" border="0"  class="block_background">
<%if(Errori.size()==0){
	checkError=false;
%>
	<tr>
		<td><script>ObjectDraw("page2","button",21,"messages/errors...","f_errori_mess","page_section","","<%=PathImage%>");</script></td>
	</tr>
<!--	
	<tr>
		<td colspan=2 align=right><script>ObjectDraw("page2","button",23,"aprire LOG file","f_errori_log","page_section","","<%=PathImage%>");</script></td>
    </tr>
-->    	
<%}else{%>
	<tr>
		<td ><script>ObjectDraw("page2","button",21,"messaggi/errori...","f_errori_mess","page_section","","<%=PathImage%>");</script></td>
	</tr>

<%	for(int i=0;i<Errori.size();i++){
		String mes = (String)Errori.get(i);
		String ERROR = mes.trim();
		String class_ERR = "error_section_errors";
		if(ERROR.indexOf("SYSI")==0){
			class_ERR = "error_section_warnings";
			ERROR = ERROR.substring(4);
		}
%>	
	<tr>	
		<td align=left class="<%=class_ERR%>"><nobr><B>&nbsp;&nbsp;[<%=prepError(ERROR)%>]&nbsp;&nbsp;</B></nobr></td>
	</tr>
<%

}
Errori.clear();%>	
<!--
	<tr>
		<td align=right><script>ObjectDraw("page2","button",23,"aprire LOG file","f_errori_log","page_section","","<%=PathImage%>");</script></td>
    	</tr>
-->    	
<%}%>
   	
</table>

<script>
if((IE4) || (NS6)) document.write("</div>");
if(NS4) document.write("</form></layer>");
</script>

<!-- PAGE FIND -->

<script>
if((IE4)||(NS6)) document.write("<div id=\"page3\" name=\"page3\" style=\"position:absolute;visibility: hidden\">");
if(NS4) document.write("<layer name='page3'  visibility='hide'><form>");
</script>
<table cellspacing="0" border="0" class="block_background">
	<tr>
		<td><input name="button32_input" id="button32_input" type="text"></td>
		<td><script>ObjectDraw("page3","button",31,"[find]","f_ricerca_ok","page_section","action_find.gif","<%=PathImage%>");</script></td>
    	</tr>
   	
</table>

<script>
if((IE4) || (NS6)) document.write("</div>");
if(NS4) document.write("</form></layer>");
</script>

<!-- PAGE SCORR -->

<script>
if((IE4)||(NS6)) document.write("<div id=\"p_scorr\" name=\"p_scorr\" style=\"position:absolute;visibility: hidden\">");
if(NS4) document.write("<layer name='p_scorr'  visibility='hide'><form>");
</script>

<table cellspacing="0" border="0" class="block_background">
	<tr>		
		<td align="center"><img name="page_right" id="page_right" src="../images/wait.gif"  border="0"></td>
    	</tr>
   	
</table>

<script>
if((IE4) || (NS6)) document.write("</div>");
if(NS4) document.write("</form></layer>");
</script>



<script>
if(IE4) document.all["bar0"].style.visibility="visible";
if(NS6) document.getElementById("bar0").style.visibility="visible";

variables[0]="bar0";
value_variables[0]=1;

variables[1]="page1";

variables[2]="page2";

<%if(!checkError){%>
	value_variables[1]=1;
	value_variables[2]=0;
	menuMouseOver(1,"page","");
<%}else{%>
	value_variables[1]=0;
	value_variables[2]=1;
	menuMouseOver(2,"page","");
<%}%>

variables[3]="page3";
value_variables[3]=0;
variables[4]="p_scorr";
value_variables[4]=0;

check("bar0",0,0);
check("page1",15,19);
check("page2",15,19);
check("page3",15,19);
check("p_scorr",15,19);
window.onscroll=beforeCheck;

</script>		

<!--Funzioni per menu-->

<script>
function f_azioni(){
	beforeAction(1,"<%=PathImage%>");
	if(getVar("page1")==0) setVar("page1",1);
	else  setVar("page1",0);
	beforeCheckNew("page1");	
}

function f_errori(){
	beforeAction(2,"<%=PathImage%>");
	if(getVar("page2")==0) setVar("page2",1);
	else  setVar("page2",0);
	beforeCheckNew("page2");	
}
function f_errori_log(){
	startURL('<%=PathMenu%>tool_log.jsp?funzRichiamata=1','LOG')
}
function f_errori_mess(){
	location.replace("log.jsp");
}
function f_errori_err(){
	
}

function f_ricerca(){
	beforeAction(3,"<%=PathImage%>");
	if(getVar("page3")==0) setVar("page3",1);
	else  setVar("page3",0);
	beforeCheckNew("page3");	
}
function f_ricerca_ok(){
	var for_find="";
	if(IE4){
		if(document.all["button32_input"])
			for_find = document.all["button32_input"].value;
	}	
	if(NS6){
		if(document.getElementById("button32_input"))
			for_find = document.getElementById("button32_input").value;
	}	
	if(NS4){
		if(document.layers["page3"].document.forms[0].button32_input)
			for_find = document.layers["page3"].document.forms[0].button32_input.value;
	}	

	findInPage(for_find);
}
function f_fly(){
	if(IE4){
		if(frame_dynamic) document.all["fly"].src = "qmftool_fly_no.gif";
		else document.all["fly"].src = "qmftool_fly_yes.gif";			
	}	
	if(NS6){
		if(frame_dynamic) document.getElementById("fly").src = "qmftool_fly_no.gif";			
		else document.getElementById("fly").src = "qmftool_fly_yes.gif";
	}		
	if(NS4){
	}		
	if(frame_dynamic) frame_dynamic=false;
	else{
		frame_dynamic=true;
		check("bar0",0,0);
		check("page1",15,19);
		check("page2",15,19);
		check("page3",15,19);
		check("p_scorr",15,19);
	}

}
function beforeClick(){
	setVar("page1",0);
	setVar("p_scorr",1);
	beforeCheckNew("p_scorr");
}
//window.setTimeout("frame_dynamic = false;",200);
</script>




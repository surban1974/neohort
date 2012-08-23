<jsp:useBean id="log"
                            type="java.util.Vector"
                            class="java.util.Vector"
                            scope="session"/>
<link href="light_menu.css" rel="stylesheet" type="text/css">
<script src="light_menu.js"></script> 
<br>
<br>
<br>
<%	for(int i=0;i<log.size();i++){
		String mes = (String)log.get(i);
%>	
<span class="page_section_text"><%=mes%></span><br>
<%	}%>
<!--  FUNZIONI  -->
<jsp:include page="menu.jsp" flush="true"/>
<script>
value_variables[1]=0;
value_variables[2]=1;
menuMouseOver(2,"page","");

document.getElementById("page_s_c_2").className = "page_section_active";
document.getElementById("button_s_c_21").className = "page_section_active";
</script>


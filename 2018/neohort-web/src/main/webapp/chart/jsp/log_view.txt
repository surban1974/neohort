
<jsp:useBean id="log"
                            type="java.util.Vector"
                            class="java.util.Vector"
                            scope="session"/>
<%!
	private String adapt(String input){
		String result="";
				for(int i=0;i<input.length();i++){
					if (input.charAt(i)=='\'') result+="&apos;";
					else if (input.charAt(i)=='>') result+="&gt;";
					else if (input.charAt(i)=='<') result+="&lt;";
					else if (input.charAt(i)=='"') result+="&quot;";
				
					else if (input.charAt(i)=='\n') result+=" ";
					else if (input.charAt(i)=='\r') result+=" ";
					else if (input.charAt(i)=='"') result+="\\\"";
					else if (input.charAt(i)=='\'') result+="\\'";
					else if (input.charAt(i)=='è') result+="e";
					else if (input.charAt(i)=='é') result+="e";
					else if (input.charAt(i)=='à') result+="a";
					else if (input.charAt(i)=='ò') result+="o";
					else if (input.charAt(i)=='ù') result+="u";
					else result+=input.charAt(i);
			
				}	
		return result;
	}
%>

<?xml version="1.0" encoding="UTF-8"?>
<!-- Created by  Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com-->

<GENERAL 
	ID="example.jsp.integrated"
	TYPE_DOCUMENT="<%=request.getParameter("$type")%>"
	ORIENTATION="PORTRAIT"
	LIB="<%=request.getParameter("$lib")%>"
	>
	<STYLE  ID="STYLE_FOOTER"
			FONT = "HELVETICA"
			FONT_SIZE = "15"
			FONT_TYPE = "BOLD"			
			FONT_COLOR_RGB = "200,200,150"				
			ALIGN = "LEFT"
	/>
	
	<PAGE_FOOTER PAGE_N="TRUE" STYLE_ID="STYLE_FOOTER">footer</PAGE_FOOTER>
	<INIT>
		<STYLE ID="STYLE_1"
				FONT = "HELVETICA"
				FONT_SIZE = "15"
				FONT_TYPE = "BOLD"			
				FONT_COLOR_RGB = "200,200,150"				
				ALIGN = "LEFT"
		/>
		<STYLE ID="STYLE_2"		
			FONT = "HELVETICA"
				FONT_SIZE = "15"
				BORDER = "15"
				BORDER_COLOR = "RED"
				ALIGN = "LEFT"
		/>
		<STYLE ID="STYLE_3"		
			FONT = "HELVETICA"
			FONT_SIZE = "15"
			FONT_TYPE = "ITALIC"
			FONT_STYLE = "UNDERLINE"
			FONT_COLOR = "RED"
			BACK_COLOR = "BLUE"
			
			BORDER = "7"
			BORDER_COLOR = "PINK"
			BORDER_WIDTH = "3"
			PADDING = "1"
			ALIGN = "CENTER"
		/>
		
		<STYLE ID="ST_CELL_H" 
			FONT="HELVETICA" 
			FONT_SIZE="10" 
			BORDER="15" 
			ALIGN="CENTER" 
			BORDER_COLOR_RGB="250,10,170"
			FONT_COLOR="RED"
			/>
		
	</INIT>
	<DOCUMENT>
		<PAGE_HEADER>
			<PARAGRAPH>
				<PHRASE STYLE_ID="STYLE_1">View LOG</PHRASE>	
			</PARAGRAPH>
		</PAGE_HEADER>
	
		<BLOCK 	STYLE_ID="STYLE_3">Step 6.1 - block</BLOCK>
		
		
		<TABLE COL="2" COLLS_DIMENTION="10,90">
<%	for(int i=0;i<log.size();i++){%>		
			<TABLE_ROW>
				<TABLE_CELL STYLE_ID="ST_CELL_H" BORDER="15"><%=i%></TABLE_CELL>
				<TABLE_CELL STYLE_ID="ST_CELL_H" BORDER="15" ALIGN="LEFT"><%=adapt(log.get(i).toString())%></TABLE_CELL>
			</TABLE_ROW>
<%	}%>			
		</TABLE>		
		

<IMAGE IMAGE_SOURCE="/neoHort/images/logo_small.gif" ABSOLUTE_X="20" ABSOLUTE_Y="20"/>				
	</DOCUMENT>
</GENERAL>

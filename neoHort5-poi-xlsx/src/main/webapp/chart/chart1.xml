<?xml version="1.0" encoding="UTF-8"?>
<!-- Created by  Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com-->

<GENERAL TYPE_DOCUMENT="STREAM" ORIENTATION="LANDSCAPE">
	<INIT>
		<BEAN ID="org_bci" TYPE="it.bci.sra.org.organigramma" CLASS="it.bci.sra.org.organigramma" SCOPE="SESSION"/>
		<BEAN ID="BU_FOUNDED" TYPE="it.bci.sra.elWST00001" CLASS="it.bci.sra.elWST00001" SCOPE="SESSION"/>
		<OBJECT_JAVA ID="list" TYPE="java.util.Vector" CLASS="java.util.Vector" SOURCE="org_bci" METHOD="_get_service_find(it.bci.sra.elWST00001:BU_FOUNDED)"/>
		<OBJECT_JAVA ID="data" TYPE="java.util.Date" CLASS="java.util.Date" SOURCE="" METHOD=""/>
		<STYLE ID="Style_current" FONT="HELVETICA" FONT_SIZE="10" FONT_TYPE="BOLD" PADDING="3" ALIGN="LEFT" FONT_COLOR="BLUE"/>
		<STYLE ID="Style_current_t" FONT="HELVETICA" FONT_SIZE="10" FONT_TYPE="NORMAL" PADDING="3" ALIGN="LEFT" FONT_COLOR="RED"/>		
		<STYLE ID="Style_current_in" FONT="HELVETICA" FONT_SIZE="10" FONT_TYPE="NORMAL" PADDING="3" ALIGN="RIGHT" FONT_COLOR="BLUE" BORDER="1"/>				
		<STYLE ID="Style_header" FONT="HELVETICA" FONT_SIZE="9" FONT_TYPE="BOLD" ALIGN="RIGHT" FONT_COLOR="white" BACK_COLOR="blue"/>
		<STYLE ID="Style_cell" FONT="HELVETICA" FONT_SIZE="9" FONT_TYPE="NORMAL" ALIGN="LEFT" FONT_COLOR="BLUE"/>
		<STYLE ID="Style_current_t1" FONT="HELVETICA" FONT_SIZE="10" FONT_TYPE="NORMAL" PADDING="3" ALIGN="LEFT" FONT_COLOR="RED" BORDER="0"/>
		<STYLE ID="Style_current_t2" FONT="HELVETICA" FONT_SIZE="7" FONT_TYPE="UNDERLINE" PADDING="3" ALIGN="RIGHT" BORDER="0"/>
		
		
	</INIT>
	
	<DOCUMENT>
		<BLOCK FONT="HELVETICA" FONT_SIZE="10" FONT_COLOR="BLUE" FONT_TYPE="NORMAL">%EXECUTEBEAN.data_string.toString%</BLOCK>
		<TABLE COL="1" COLLS_DIMENTION="100">
			<TABLE_ROW>
				<CHART 
					ID="First_Chart" 
					CHART_TYPE="CLASS:neohort.universal.output.lib.chart_advanced_pdf.chart_content_PIE_ADVANCED" 
					FORMAT_CHART_INPUT_DATA="DATE:yyyy-MM-dd;;;;"
					CHART_DATA_Y="DataTTTTTT #1;Data #2;DataTTTTT #3;Data #4;Data #5;Data #6;DataTTTTTTTTT #7;"
					CHART_DATA_X="2003-01-01;2004-02-01;2005-03-01;2006-04-01;2007-05-01;2008-06-01;2009-07-01;"
					CHART_DATA_Z="7;15;15;"
					FORMAT_SCALE_XYZ="DATE:dd/MM/yyyy;;;"
					SHOW_SCALE_XYZ="true;true;;"
					GR_SCALE_XYZ="0;;;"
					FONT_SCALE_XYZ="Helvetica;;;"
					FONT_SCALE_COLOR_XYZ="blue;;;"
					FONT_SCALE_SIZE_XYZ="7;;;"
					MAXELEMENT_LABEL_XYZ="7;;;"
					
					GR_LABEL_XYZ="90;;;"
					ALIGN_LABEL_XYZ="CENTER;;;"
					FONT_LABEL_XYZ="Helvetica-Oblique;;;"
					FONT_LABEL_COLOR_XYZ="RED;;;"
					FONT_LABEL_SIZE_XYZ="10;;;"

					LABEL_X=""
					LABEL_TOP="	E-TELECOM-E20;
							E-TELECOM-E30;
							E-TELECOM-E40;
							E-TELECOM-E30;
							E-TELECOM-E40;
							E-TELECOM-E30;
							E-TELECOM-E40;"

					FONT_LABEL_TOP="Helvetica"
					FONT_LABEL_COLOR_TOP="blue"
					FONT_LABEL_SIZE_TOP="8"
					ALIGN_LABEL_TOP="CENTER"
					
					ELEMENT_COLOR_3D="red;pink;black;"
					DIMENTION_H="540"
					DIMENTION_V="500"
					BORDER="0"
					ALIGN="CENTER"/>
			</TABLE_ROW>
			
		</TABLE>
	</DOCUMENT>
</GENERAL>

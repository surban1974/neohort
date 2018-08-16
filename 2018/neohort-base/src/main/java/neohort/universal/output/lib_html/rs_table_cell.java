/**
* Creation date: (14/12/2005)
* @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
*/

/********************************************************************************
*
*	Copyright (C) 2005  Svyatoslav Urbanovych
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.

* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*********************************************************************************/

package neohort.universal.output.lib_html;

import java.io.DataOutputStream;
import java.util.Date;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_format;


public class rs_table_cell extends element{

	private static final long serialVersionUID = 1L;
	private rs_table_row parentR = null;
	private java.lang.String RS_COLUMN;
	private java.lang.String BLANK_IF_EQUAL_LAST;
public rs_table_cell() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
try{
	this._header+=	"<TD ";
	this._footer=	"</TD>"+_separator()+this._footer;
	
	report_element_base parentC = (report_element_base)getParent();
	while (parentC!=null && !parentC.getName().equalsIgnoreCase("RS_TABLE_ROW"))
		parentC=(report_element_base)parentC.getParent();
	parentR = (rs_table_row)parentC;
	
	
	Object rs_content = null;
	Object rs_last_content = null;
	if(parentR!=null && getRS_COLUMN()!=null && !getRS_COLUMN().equals("")){
		try{
			rs_content = parentR.getResultSet().getObject(Integer.valueOf(getRS_COLUMN()));
			rs_last_content = parentR.getLastRow().get(getRS_COLUMN());
			parentR.getLastRow().put(getRS_COLUMN(),rs_content);
		}catch(Exception e){
			try{
				rs_content = parentR.getResultSet().getObject(getRS_COLUMN());
				rs_last_content = parentR.getLastRow().get(getRS_COLUMN());
				parentR.getLastRow().put(getRS_COLUMN(),rs_content);

			}catch(Exception ex){
				setError(e,iStub.log_WARN);
			}
		}

	}
	if(parentR!=null && parentR.getRow_style()!=null)
		forcedStyle(parentR.getRow_style());
	if(parentR!=null && parentR.getColumn_styles().get("column_style_id_"+getRS_COLUMN().toLowerCase())!=null)
		forcedStyle(parentR.getColumn_styles().get("column_style_id_"+getRS_COLUMN().toLowerCase()));

	
	if(this._content==null)
		this._content="";
	if(getBLANK_IF_EQUAL_LAST()!=null && (getBLANK_IF_EQUAL_LAST().equalsIgnoreCase("yes") || getBLANK_IF_EQUAL_LAST().equalsIgnoreCase("true"))){
		if(rs_content!=null && rs_last_content!=null && rs_content.toString().equals(rs_last_content.toString()))
			this._content+="";
		else
			this._content+= prepareContentString(rs_content, internal_style.getFORMAT());
	}else
		this._content+= prepareContentString(rs_content, internal_style.getFORMAT());

	
	table table = new table();
		try{
			report_element_base el = this;
			while(el.getParent()!=null && !el.getName().equalsIgnoreCase("TABLE"))
				el = (report_element_base)el.getParent();
			table = (table)el;
		}catch(Exception e){
			table = null;
		}

	if (table!=null && !table.getCOLLS_DIMENTION().equalsIgnoreCase("")){
		int colspn = 1;
		try{
			colspn = Integer.valueOf(internal_style.getCOL_SPAN()).intValue();
		}catch(Exception e){
			colspn = 1;
		}
		int WidthMax = 0;
		for(int i=0;i<colspn;i++){
			try{
				WidthMax+=Integer.valueOf(table.getCurrentWidth()).intValue();
			}catch(Exception e){}
		}
		this._header+="WIDTH=\""+WidthMax+((table.isPercent())?"%":"")+"\" ";
	}

	if(!internal_style.getALIGN().equals("")) this._header+="ALIGN=\""+internal_style.getALIGN()+"\" ";
	if(!internal_style.getCOL_SPAN().equals("")) this._header+="COLSPAN=\""+internal_style.getCOL_SPAN()+"\" ";
	if(!internal_style.getROW_SPAN().equals("")) this._header+="ROWSPAN=\""+internal_style.getROW_SPAN()+"\" ";

	if(	(!internal_style.getBORDER().equals("0") && internal_style.getBORDER().length()>0)||
		(!internal_style.getPADDING().equals("0") && internal_style.getPADDING().length()>0)||
		!internal_style.getBACK_COLOR().equals("")){
		this._header+="STYLE=\"";	
		if(!internal_style.getBORDER().trim().equals("")) this._header+=analiseBorder(internal_style.getBORDER());
		else this._header+=analiseBorder("15");
		if(!internal_style.getBORDER_WIDTH().equals("0") && internal_style.getBORDER_WIDTH().length()>0) this._header+="border-width:"+internal_style.getBORDER_WIDTH()+";";
		if(!internal_style.getBORDER_COLOR().equals("")){
			if(internal_style.getBORDER_COLOR().indexOf(",")>-1) this._header+="border-color:rgb("+internal_style.getBORDER_COLOR()+");"; 
			else this._header+="border-color:"+internal_style.getBORDER_COLOR()+";";
		}
		if(!internal_style.getBACK_COLOR().equals("")){
			if(internal_style.getBACK_COLOR().indexOf(",")>-1) this._header+="background-color:rgb("+internal_style.getBACK_COLOR()+");"; 
			else this._header+="background-color:"+internal_style.getBACK_COLOR()+";";
		}
			
		if(!internal_style.getPADDING().equals("0") && internal_style.getPADDING().length()>0) this._header+="padding: "+internal_style.getPADDING()+";";
		this._header+="\"";
	}


	this._header = this._header.trim();
	this._header+=">";

	if(	!internal_style.getFONT().equals("") ||
		!internal_style.getFONT_COLOR().equals("") ||
		!internal_style.getFONT_SIZE().equals("") ||
		!internal_style.getFONT_TYPE().equals("")){
		this._header+="<SPAN ";
		this._footer="</SPAN>"+this._footer;
	
		this._header+="style=\"";
		if(!internal_style.getFONT().trim().equals("")) this._header+="font-family:"+internal_style.getFONT()+";";
		if(!internal_style.getFONT_COLOR().trim().equals("")){
			if(internal_style.getFONT_COLOR().indexOf(",")>-1) this._header+="color:rgb("+internal_style.getFONT_COLOR()+");";
			else this._header+="color:"+internal_style.getFONT_COLOR()+";";
		}
		if(!internal_style.getFONT_SIZE().trim().equals("")) this._header+="font-size:"+getSize(internal_style.getFONT_SIZE())+"pt;";
		if(!internal_style.getFONT_TYPE().trim().equals("0") && internal_style.getFONT_TYPE().length()>0){
			if(internal_style.getFONT_TYPE().toUpperCase().equals("BOLD"))
				this._header+="font-style:normal;font-weight:bold;";
			if(internal_style.getFONT_TYPE().toUpperCase().equals("BOLDITALIC"))
				this._header+="font-style:italic;font-weight:bold;";
			if(internal_style.getFONT_TYPE().toUpperCase().equals("ITALIC"))
				this._header+="font-style:italic;";
		}
		this._header+="\">";
		this._header = this._header.trim();
	}
	
	((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._header);
}catch(Exception e){
	setError(e,iStub.log_WARN);
}
}
@SuppressWarnings("rawtypes")
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		((report_element_base)((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).lastElement()).add(this);
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

		((DataOutputStream)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())).writeBytes(this._content+this._comment+this._footer);
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("RS_TABLE_CELL");
	STYLE_ID = "";
	RS_COLUMN = "";
	BLANK_IF_EQUAL_LAST = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public java.lang.String getRS_COLUMN() {
	return RS_COLUMN;
}
public void setRS_COLUMN(java.lang.String rS_COLUMN) {
	RS_COLUMN = rS_COLUMN;
}
public java.lang.String getBLANK_IF_EQUAL_LAST() {
	return BLANK_IF_EQUAL_LAST;
}
public void setBLANK_IF_EQUAL_LAST(java.lang.String bLANK_IF_EQUAL_LAST) {
	BLANK_IF_EQUAL_LAST = bLANK_IF_EQUAL_LAST;
}

public String prepareContentString(Object obj, String formatSG) {
	if(obj==null)
		return "";
	String content = obj.toString();
	java.util.StringTokenizer st = new java.util.StringTokenizer(formatSG, "|");
	while (st.hasMoreTokens()){
		String formatS = st.nextToken();
		if(formatS.length()>0){
			boolean is=false;
			if (formatS.toUpperCase().indexOf("NUMBER:")==0){
				try{
					String format = formatS.substring(7);
					java.text.DecimalFormat df = new java.text.DecimalFormat(format);
					if(obj instanceof Number)
						content = df.format((Number)obj);
					else
						content = df.format(new java.math.BigDecimal(obj.toString().trim()).doubleValue());
				}catch(Exception e){}
				is=true;
			}
			if(!is){
				if (formatS.toUpperCase().indexOf("DATE:")==0){ 
					try{
						String format = formatS.substring(5);
						java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
						if(obj instanceof Date)
							content = df.format((Date)obj);
						else
							content = df.format(new java.util.Date(util_format.stringToData(obj.toString(),"yyyy-MM-dd").getTime()));
					}catch(Exception e){
						try{
							String format = formatS.substring(5);
							java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
							content = df.format(new java.util.Date(java.text.DateFormat.getDateInstance().parse(obj.toString()).getTime()));
						}catch(Exception ex){
						}
					}
					is=true;
				}
				if(!is){
					if (formatS.toUpperCase().indexOf("ISNULL:")==0){ 
						try{
							String format = formatS.substring(7);
							if (content.trim().equals("0")) content = format;
							else{
								if (new java.math.BigDecimal(content).doubleValue()==0) content = format;
							}									
						}catch(Exception e){}
						is=true;
					}
					if(!is){
						if (formatS.toUpperCase().indexOf("NOTNULL:")==0){ 
							try{
								String format = formatS.substring(8);
								if (content.trim().equals("")) content = format;
							}catch(Exception e){}
							is=true;
						}
						if(!is){
							if (formatS.toUpperCase().indexOf("TRIM:")>-1){
								try{
									content = content.trim();
								}catch(Exception e){}
								is=true;	
							}
							if(!is){
								if (formatS.toUpperCase().indexOf("UPPERCASE:")>-1){
									try{
										content = content.toUpperCase();
									}catch(Exception e){}
									is=true;	
								}
								if(!is){
									if (formatS.toUpperCase().indexOf("LOWERCASE:")>-1){
										try{
											content = content.toLowerCase();
										}catch(Exception e){}
										is=true;	
									}
									if(!is){
										if (formatS.toUpperCase().indexOf("SUBSTRING:")>-1){
											try{
												String format = formatS.substring(10+formatS.indexOf("SUBSTRING:"));					
												content = content.substring(0,Integer.valueOf(format).intValue());
											}catch(Exception e){}
											is=true;	
										}
										if(!is){
											if (formatS.toUpperCase().indexOf("REPLACE:")>-1){
												try{
													String format = formatS.substring(8);
													if(format.charAt(0)=='[' && format.charAt(format.length()-1)==']'){						
														java.util.StringTokenizer stf = new java.util.StringTokenizer(format, "--");
														String vFirst = stf.nextToken();
														String vSecond = stf.nextToken();
														content = content.replace(vFirst.charAt(1),vSecond.charAt(0));	
													}	
													content = content.substring(0,Integer.valueOf(format).intValue());
												}catch(Exception e){}
												is=true;	
											}
										}
									}
								}
							}
						}
					}
				}	
			}	
		}
	}		
	return content;		
}


public void forcedStyle(style style_new) {
	if(style_new==null) return;
	if(style_new.getTEXT_ALIGN_H()!=null && !style_new.getTEXT_ALIGN_H().equals("")) internal_style.setTEXT_ALIGN_H(style_new.getTEXT_ALIGN_H());
	if(style_new.getTEXT_ALIGN_V()!=null && !style_new.getTEXT_ALIGN_V().equals("")) internal_style.setTEXT_ALIGN_V(style_new.getTEXT_ALIGN_V());

	if(style_new.getFONT()!=null && !style_new.getFONT().equals("")) internal_style.setFONT(style_new.getFONT());
	if(style_new.getFONT_SIZE()!=null && !style_new.getFONT_SIZE().equals("")) internal_style.setFONT_SIZE(style_new.getFONT_SIZE());
	if(style_new.getFONT_TYPE()!=null && !style_new.getFONT_TYPE().equals("")) internal_style.setFONT_TYPE(style_new.getFONT_TYPE());
	if(style_new.getFONT_STYLE()!=null && !style_new.getFONT_STYLE().equals("")) internal_style.setFONT_STYLE(style_new.getFONT_STYLE());
	if(style_new.getFONT_ENCODED()!=null && !style_new.getFONT_ENCODED().equals("")) internal_style.setFONT_ENCODED(style_new.getFONT_ENCODED());
	if(style_new.getFONT_COLOR()!=null && !style_new.getFONT_COLOR().equals("")) internal_style.setFONT_COLOR(style_new.getFONT_COLOR());
	if(style_new.getFONT_COLOR_RGB()!=null && !style_new.getFONT_COLOR_RGB().equals("")) internal_style.setFONT_COLOR_RGB(style_new.getFONT_COLOR_RGB());

	if(style_new.getDIMENTION_H()!=null && !style_new.getDIMENTION_H().equals("")) internal_style.setDIMENTION_H(style_new.getDIMENTION_H());
	if(style_new.getDIMENTION_V()!=null && !style_new.getDIMENTION_V().equals("")) internal_style.setDIMENTION_V(style_new.getDIMENTION_V());
	if(style_new.getALIGN()!=null && !style_new.getALIGN().equals("")) internal_style.setALIGN(style_new.getALIGN());
	if(style_new.getPADDING()!=null && !style_new.getPADDING().equals("")) internal_style.setPADDING(style_new.getPADDING());
	if(style_new.getFORMAT()!=null && !style_new.getFORMAT().equals("")) internal_style.setFORMAT(style_new.getFORMAT());
	if(style_new.getWIDTH()!=null && !style_new.getWIDTH().equals("")) internal_style.setWIDTH(style_new.getWIDTH());
	if(style_new.getHEIGHT()!=null && !style_new.getHEIGHT().equals("")) internal_style.setHEIGHT(style_new.getHEIGHT());
	if(style_new.getCOL_SPAN()!=null && !style_new.getCOL_SPAN().equals("")) internal_style.setCOL_SPAN(style_new.getCOL_SPAN());
	if(style_new.getROW_SPAN()!=null && !style_new.getROW_SPAN().equals("")) internal_style.setROW_SPAN(style_new.getROW_SPAN());
	if(style_new.getABSOLUTE_X()!=null && !style_new.getABSOLUTE_X().equals("")) internal_style.setABSOLUTE_X(style_new.getABSOLUTE_X());
	if(style_new.getABSOLUTE_Y()!=null && !style_new.getABSOLUTE_Y().equals("")) internal_style.setABSOLUTE_Y(style_new.getABSOLUTE_Y());
	if(style_new.getTEXT_ROTATION_DEGREE()!=null && !style_new.getTEXT_ROTATION_DEGREE().equals("")) internal_style.setTEXT_ROTATION_DEGREE(style_new.getTEXT_ROTATION_DEGREE());
	
	if(style_new.getBACK_COLOR()!=null && !style_new.getBACK_COLOR().equals("")) internal_style.setBACK_COLOR(style_new.getBACK_COLOR());
	if(style_new.getBACK_COLOR_RGB()!=null && !style_new.getBACK_COLOR_RGB().equals("")) internal_style.setBACK_COLOR_RGB(style_new.getBACK_COLOR_RGB());
	if(style_new.getBAR_COLOR_RGB()!=null && !style_new.getBAR_COLOR_RGB().equals("")) internal_style.setBAR_COLOR_RGB(style_new.getBAR_COLOR_RGB());
	if(style_new.getBAR_COLOR()!=null && !style_new.getBAR_COLOR().equals("")) internal_style.setBAR_COLOR(style_new.getBAR_COLOR());

	if(style_new.getBORDER()!=null && !style_new.getBORDER().equals("")) internal_style.setBORDER(style_new.getBORDER());
	if(style_new.getBORDER_COLOR()!=null && !style_new.getBORDER_COLOR().equals("")) internal_style.setBORDER_COLOR(style_new.getBORDER_COLOR());
	if(style_new.getBORDER_COLOR_RGB()!=null && !style_new.getBORDER_COLOR_RGB().equals("")) internal_style.setBORDER_COLOR_RGB(style_new.getBORDER_COLOR_RGB());	
	if(style_new.getBORDER_COLOR_TOP()!=null && !style_new.getBORDER_COLOR_TOP().equals("")) internal_style.setBORDER_COLOR_TOP(style_new.getBORDER_COLOR_TOP());
	if(style_new.getBORDER_COLOR_TOP_RGB()!=null && !style_new.getBORDER_COLOR_TOP_RGB().equals("")) internal_style.setBORDER_COLOR_TOP_RGB(style_new.getBORDER_COLOR_TOP_RGB());	
	if(style_new.getBORDER_COLOR_BOTTOM()!=null && !style_new.getBORDER_COLOR_BOTTOM().equals("")) internal_style.setBORDER_COLOR_BOTTOM(style_new.getBORDER_COLOR_BOTTOM());
	if(style_new.getBORDER_COLOR_BOTTOM_RGB()!=null && !style_new.getBORDER_COLOR_BOTTOM_RGB().equals("")) internal_style.setBORDER_COLOR_BOTTOM_RGB(style_new.getBORDER_COLOR_BOTTOM_RGB());	
	if(style_new.getBORDER_COLOR_LEFT()!=null && !style_new.getBORDER_COLOR_LEFT().equals("")) internal_style.setBORDER_COLOR_LEFT(style_new.getBORDER_COLOR_LEFT());
	if(style_new.getBORDER_COLOR_LEFT_RGB()!=null && !style_new.getBORDER_COLOR_LEFT_RGB().equals("")) internal_style.setBORDER_COLOR_LEFT_RGB(style_new.getBORDER_COLOR_LEFT_RGB());	
	if(style_new.getBORDER_COLOR_RIGHT()!=null && !style_new.getBORDER_COLOR_RIGHT().equals("")) internal_style.setBORDER_COLOR_RIGHT(style_new.getBORDER_COLOR_RIGHT());
	if(style_new.getBORDER_COLOR_RIGHT_RGB()!=null && !style_new.getBORDER_COLOR_RIGHT_RGB().equals("")) internal_style.setBORDER_COLOR_RIGHT_RGB(style_new.getBORDER_COLOR_RIGHT_RGB());	

	if(style_new.getBORDER_WIDTH()!=null && !style_new.getBORDER_WIDTH().equals("")) internal_style.setBORDER_WIDTH(style_new.getBORDER_WIDTH());
	if(style_new.getBORDER_WIDTH_TOP()!=null && !style_new.getBORDER_WIDTH_TOP().equals("")) internal_style.setBORDER_WIDTH_TOP(style_new.getBORDER_WIDTH_TOP());
	if(style_new.getBORDER_WIDTH_BOTTOM()!=null && !style_new.getBORDER_WIDTH_BOTTOM().equals("")) internal_style.setBORDER_WIDTH_BOTTOM(style_new.getBORDER_WIDTH_BOTTOM());
	if(style_new.getBORDER_WIDTH_LEFT()!=null && !style_new.getBORDER_WIDTH_LEFT().equals("")) internal_style.setBORDER_WIDTH_LEFT(style_new.getBORDER_WIDTH_LEFT());
	if(style_new.getBORDER_WIDTH_RIGHT()!=null && !style_new.getBORDER_WIDTH_RIGHT().equals("")) internal_style.setBORDER_WIDTH_RIGHT(style_new.getBORDER_WIDTH_RIGHT());
	
	if(style_new.getEXTRA_FONT()!=null && !style_new.getEXTRA_FONT().equals("")) internal_style.setEXTRA_FONT(style_new.getEXTRA_FONT());
	if(style_new.getFONT_EMBEDDED()!=null && !style_new.getFONT_EMBEDDED().equals("")) internal_style.setFONT_EMBEDDED(style_new.getFONT_EMBEDDED());
	if(style_new.getDIRECTION()!=null && !style_new.getDIRECTION().equals("")) internal_style.setDIRECTION(style_new.getDIRECTION());

}

}

/**
* Creation date: (22/12/2005)
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

package neohort.universal.output.lib_xlsx;

import java.awt.Color;
import java.util.Date;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
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
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
@SuppressWarnings({ "rawtypes", "unchecked" })
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		
		
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
				if(rs_content!=null)
					parentR.getLastRow().put(getRS_COLUMN(),rs_content);
			}catch(Exception e){
				try{
					rs_content = parentR.getResultSet().getObject(getRS_COLUMN());
					rs_last_content = parentR.getLastRow().get(getRS_COLUMN());
					if(rs_content!=null)
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


		if(getBLANK_IF_EQUAL_LAST()!=null && (getBLANK_IF_EQUAL_LAST().equalsIgnoreCase("yes") || getBLANK_IF_EQUAL_LAST().equalsIgnoreCase("true"))){
			if(rs_content!=null && rs_last_content!=null && rs_content.toString().equals(rs_last_content.toString()))
				rs_content=null;
		}
		
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW); 
		
		int X = 0;
		int Y = 0;
		try{
			X = ((Integer)_sysPdfCC.getContent()).intValue();
		}catch(Exception e){
		}
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}
		
		Workbook workbook = (Workbook)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Workbook)).getContent();
		Sheet document = (Sheet)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());
		

		bean _defDatetimeformat = (bean)_beanLibrary.get("SYSTEM:DEFDATETIMEFORMAT");
		
		if(_defDatetimeformat!=null && _defDatetimeformat.getContent()!=null) setDefDATETIMEFORMAT((Short)_defDatetimeformat.getContent());
		else{
			_defDatetimeformat = new bean();
			_defDatetimeformat.setContent(workbook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy hh:mm"));
			_defDatetimeformat.setID("DEFDATETIMEFORMAT");
			_beanLibrary.put("SYSTEM:DEFDATETIMEFORMAT",_defDatetimeformat);
			setDefDATETIMEFORMAT((Short)_defDatetimeformat.getContent());
		}
		
		bean _defDateformat = (bean)_beanLibrary.get("SYSTEM:DEFDATEFORMAT");
		if(_defDateformat!=null && _defDateformat.getContent()!=null) setDefDATEFORMAT((Short)_defDateformat.getContent());
		else{
		
			_defDateformat = new bean();
			_defDateformat.setContent(workbook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy"));
			_defDatetimeformat.setID("DEFDATEFORMAT");
			_beanLibrary.put("SYSTEM:DEFDATEFORMAT",_defDateformat);
			setDefDATEFORMAT((Short)_defDateformat.getContent());
		}
		
		
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(
				this.getCellC(rs_content, X,Y, workbook, document)
			);
		
		

		try{
			if(!internal_style.getWIDTH().equals("")) 
				document.setColumnWidth(X,new Integer(internal_style.getWIDTH()).intValue()*256);
		}catch(Exception e){	
		}
		if(!internal_style.getHEIGHT().equals("") && parent!=null && parent instanceof table_row)
			((table_row)parent).setHEIGHT(internal_style.getHEIGHT());
		
		table parentTable = null;
		try{
			parentTable = (table)((table_row)this.getParent()).getParent();
		}catch(Exception e){
		}
			
		if(	parentTable!=null && 
			parentTable.getParentTable_Block()!=null &&
			parentTable.getParentTable_Block().getDeltaCol()==0)
			parentTable.getParentTable_Block().setDeltaCol(parentTable.getParentTable_Block().getDeltaCol()+1);
			
		X++;
		

		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR); 

		_sysPdfCC.setContent(new Integer(X));
		_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}

public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("RS_TABLE_CELL");
	RS_COLUMN = "";
	BLANK_IF_EQUAL_LAST = "";
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
public Cell getCellC(Object rs_content, int X,int Y, Workbook workbook, Sheet document) {

	Cell cell = null;
	
	CellReference cr = new CellReference(Y,X);
	Row row = document.getRow(cr.getRow());
	if(row==null)
		row = document.createRow(cr.getRow());
	Cell old = row.getCell(cr.getCol());
	if(old!=null)
		cell = old;
	else
		cell = row.createCell(cr.getCol());

	boolean isFormat=false;
//	String frase = "";

	CellStyle format = null;
	if(old!=null && old.getCellStyle()!=null)
		format = (XSSFCellStyle)old.getCellStyle();
	else
		format =  workbook.createCellStyle();
	


	
	Font font = null;
	try{
		if(internal_style.getFONT()!=null && !internal_style.getFONT().equals("")){
			font=workbook.createFont();
			font.setFontName(analizeFontName(internal_style.getFONT()));
			isFormat=true;
		}else{
			font=workbook.createFont();
		}
	}catch(Exception e){
	}

	if(internal_style.getFONT_SIZE()!=null && !internal_style.getFONT_SIZE().equals("")){
		int font_size = 10;
		try{
			font_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
			font.setFontHeightInPoints((short)font_size);
			isFormat=true;
		}catch(Exception e){}
	}
	if(	internal_style.getFONT_TYPE()!=null &&
		(internal_style.getFONT_TYPE().equalsIgnoreCase("ITALIC") || internal_style.getFONT_TYPE().equalsIgnoreCase("BOLDITALIC"))){
		try{
			font.setItalic(true);
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(	internal_style.getFONT_TYPE()!=null &&
		(internal_style.getFONT_TYPE().equalsIgnoreCase("BOLD") || internal_style.getFONT_TYPE().equalsIgnoreCase("BOLDITALIC"))){
		try{
			font.setBold(true);
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(	internal_style.getFONT_TYPE()!=null && internal_style.getFONT_TYPE().equalsIgnoreCase("UNDERLINE")){
		try{
			((XSSFFont)font).setUnderline(FontUnderline.SINGLE);
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(	internal_style.getFONT_STYLE()!=null && internal_style.getFONT_STYLE().equalsIgnoreCase("STRIKE")){
		try{
			font.setStrikeout(true);
			isFormat=true;
		}catch(Exception e){
		}
	}

	if(internal_style.getFONT_COLOR()!=null && !internal_style.getFONT_COLOR().equals("")){
		try{


			((XSSFFont)font).setColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getFONT_COLOR(),Color.black)));
			isFormat=true;
		}catch(Exception e){
		}
	}

	format.setFont(font);

	analiseBorder_Colour((XSSFCellStyle)format,internal_style);

	if(internal_style.getBACK_COLOR()!=null && !internal_style.getBACK_COLOR().equals("")){
		try{
			((XSSFCellStyle)format).setFillForegroundColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBACK_COLOR(),Color.white)));
			format.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(internal_style.getALIGN()!=null && !internal_style.getALIGN().equals("")){
		try{
			format.setAlignment(analiseHAlign(internal_style.getALIGN()));
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(internal_style.getTEXT_ALIGN_H()!=null && !internal_style.getTEXT_ALIGN_H().equals("")){
		try{
			format.setAlignment(analiseHAlign(internal_style.getTEXT_ALIGN_H()));
			isFormat=true;
		}catch(Exception e){
		}
	}
	if(internal_style.getTEXT_ALIGN_V()!=null && !internal_style.getTEXT_ALIGN_V().equals("")){
		try{
			format.setVerticalAlignment(analiseVAlign(internal_style.getTEXT_ALIGN_V()));
			isFormat=true;
		}catch(Exception e){
		}
	}

	float rotation = 0;
	try{
		rotation = new Float(getStyle().getTEXT_ROTATION_DEGREE()).floatValue();
	}catch(Exception e){
	}

	if(rotation!=0){
		try{
			format.setRotation((short)rotation);
			isFormat=true;
		}catch(Exception e){
		}
	}


	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_NUMBER)>-1){

			String formatNumber = getFormat(format_NUMBER, internal_style.getFORMAT());
			if(!formatNumber.equals("")){
				if(!isFormat)
					format =  workbook.createCellStyle();
				format.setDataFormat(workbook.createDataFormat().getFormat(formatNumber));
				isFormat=true;
			}
			
			if (isFormat) 
				cell.setCellStyle(format);
			
			if(rs_content!=null && rs_content instanceof Number)
				cell.setCellValue(((Number)rs_content).doubleValue());
			else if(rs_content!=null)
				cell.setCellValue(new Double(rs_content.toString()).doubleValue());
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}

	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_FORMULA)==0){
			if (isFormat) 
				cell.setCellStyle(format);
			if(rs_content!=null)
				cell.setCellFormula(rs_content.toString());
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}

	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)>-1){

			String formatDate = getFormat(format_DATETIME, internal_style.getFORMAT());
			if(!formatDate.equals("")){
				if(!isFormat)
					format =  workbook.createCellStyle();
				format.setDataFormat(workbook.createDataFormat().getFormat(formatDate));
				isFormat=true;
			}else{
				if(defDATETIMEFORMAT==0){
					CreationHelper createHelper = workbook.getCreationHelper();
					defDATETIMEFORMAT = createHelper.createDataFormat().getFormat("dd/MM/yyyy hh:mm");
				}
				format.setDataFormat(defDATETIMEFORMAT);
				isFormat=true;
			}
			
			Date ret = null;
			if(rs_content!=null){
				if(rs_content instanceof Date)
					ret = (Date)rs_content;
				else
					ret = getCallDate(rs_content.toString(),format_DATETIME,formatDate);

			}


			cell.setCellStyle(format);
			if(ret!=null)
				cell.setCellValue(ret);
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}
	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)>-1){

			String formatDate = getFormat(format_DATE, internal_style.getFORMAT());
			if(!formatDate.equals("")){
				if(!isFormat)
					format =  workbook.createCellStyle();
				format.setDataFormat(workbook.createDataFormat().getFormat(formatDate));
				isFormat=true;
			}else{
				if(defDATEFORMAT==0){
					CreationHelper createHelper = workbook.getCreationHelper();
					defDATEFORMAT = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
				}
				format.setDataFormat(defDATEFORMAT);
				isFormat=true;
			}
			
			Date ret = null;
			if(rs_content!=null){
				if(rs_content instanceof Date)
					ret = (Date)rs_content;
				else
					ret = getCallDate(rs_content.toString(),format_DATE,formatDate);
			}
			
			cell.setCellStyle(format);
			
			if(ret!=null)
				cell.setCellValue(ret);
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}

	String frase = null;
	if(rs_content!=null)
		frase = prepareContentString(rs_content, internal_style.getFORMAT());



	if(frase==null || frase.equals("")){
		if (isFormat) 
			cell.setCellStyle(format);
		cell.setCellValue("");
		if(getHyperlink()!=null)
			cell.setHyperlink(getHyperlink());
		cell=null;
		return cell;
	}
	else {
		if (isFormat) 
			cell.setCellStyle(format);
		cell.setCellValue(frase);
		if(getHyperlink()!=null)
			cell.setHyperlink(getHyperlink());
		cell=null;
		return cell;
	}


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
}

/**
* Creation date: (19/12/2005)
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
import java.util.HashMap;
import java.util.Hashtable;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;


import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_format;




public abstract class element extends report_element_base  implements report_element {

	private static final long serialVersionUID = 1L;
	private static HashMap colorsCache;
	private static HashMap fontNameCache;
	private static HashMap alignCache;
	private static HashMap vAlignCache;
	private static HashMap hAlignCache;

	private short defDATEFORMAT;
	private short defDATETIMEFORMAT;

	private static final String format_NUMBER="NUMBER";
	private static final String format_FORMULA="FORMULA";
	private static final String format_DATETIME="DATETIME";
	private static final String format_DATE="DATE";

	private Hyperlink hyperlink = null;


public element() {
	super();
	Parameters.addElement("ID");
	reimposta();
}
public void add(report_element_base child) {}

public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {}
public Object execute(Hashtable _beanLibrary) {
	return null;
}
public void reimposta(){
	name = "ELEMENT";
}

public Cell getCellC(int X,int Y, Workbook workbook, Sheet document) {

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
	String frase = "";
	if(getContent()!=null) 
		frase = (String)getContent();

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
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_NUMBER)==0){
			if (isFormat) 
				cell.setCellStyle(format);

			cell.setCellValue(new Double(frase).doubleValue());
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

			cell.setCellFormula(frase);
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}

	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)==0){
			Date ret = getCallDate(frase,internal_style.getFORMAT());
			if(defDATETIMEFORMAT==0){
				CreationHelper createHelper = workbook.getCreationHelper();
				defDATETIMEFORMAT = createHelper.createDataFormat().getFormat("dd/MM/yyyy hh:mm");
			}
			format.setDataFormat(defDATETIMEFORMAT);
			cell.setCellStyle(format);

			cell.setCellValue(ret);
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}
	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)==0){
			Date ret = getCallDate(frase,internal_style.getFORMAT());
			if(defDATEFORMAT==0){
				CreationHelper createHelper = workbook.getCreationHelper();
				defDATEFORMAT = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
			}
			format.setDataFormat(defDATEFORMAT);
			cell.setCellStyle(format);
			
			cell.setCellValue(ret);
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}


	frase = prepareContentString(internal_style.getFORMAT());



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

public void initCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		Boolean initProcess = new Boolean(false);
		try{
			initProcess = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).getContent());
		}catch(Exception e){
			bean _sysinitProcess = new bean();
			_sysinitProcess.setContent(initProcess);
			_sysinitProcess.setName("SYSTEM");
			_sysinitProcess.setID(iConst.iHORT_SYSTEM_initProcess);
			_beanLibrary.put(
				_sysinitProcess.getName() + ":" + _sysinitProcess.getID(),
				_sysinitProcess);

		}
		java.util.Vector canvas = ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent()));

		Object current_Element = canvas.lastElement();
			canvas.removeElement(canvas.lastElement());
		Object content_Element = canvas.lastElement();

		if(	initProcess.booleanValue() &&
			(	((bean)current_Element).getID().equals("PageFooter_") ||
				((bean)current_Element).getID().equals("PageHeader_"))
			){
			return;
		}
//		if(isError) return;


		if(content_Element instanceof bean){
			if(((bean)content_Element).getID().equals("PageFooter_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			if(((bean)content_Element).getID().equals("PageHeader_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			return;
		}

		if(content_Element instanceof Sheet){


//			if(current_Element instanceof WritableImage){
//				((XSSFSheet)content_Element).addImage((WritableImage)current_Element);
//				current_Element=null;
//				return;
//			}
//			if(current_Element instanceof Hyperlink){
//				((Sheet)content_Element).addHyperlink((Hyperlink)current_Element);
//				current_Element=null;
//				return;
//			}


			current_Element=null;
			return;
		}
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public boolean analiseItalicStyle(String type){
	if(type==null || type.equals("")) return false;
	if(type.equalsIgnoreCase("ITALIC") || type.equalsIgnoreCase("BOLDITALIC")) return true;
	return false;
}
public String analizeFontName(String name){
	if(fontNameCache==null) fontNameCache=new HashMap();
	String fontName = (String)fontNameCache.get(name);

	if(fontName==null){
		if(name==null || name.equals("")) fontName = "Calibri";
		else if(name.equalsIgnoreCase("HELVETICA")) fontName = "Arial";
		else if(name.equalsIgnoreCase("TIMES_NEW_ROMAN")) fontName = "Times Roman";
		else if(name.equalsIgnoreCase("TIMES_ROMAN")) fontName = "Times Roman";
		else if(name.equalsIgnoreCase("TAHOMA")) fontName = "Tahoma";
		else fontName = name;
		fontNameCache.put(name, fontName);
	}

	return fontName;
}

private static XSSFColor getNearestColour(Color awtColor){
	if(colorsCache==null) colorsCache=new HashMap();
	

	XSSFColor userColor = (XSSFColor) colorsCache.get(awtColor);

    if (userColor == null){
    	userColor = new XSSFColor(awtColor);
        colorsCache.put(awtColor, userColor);
    }

    return userColor;
}
 


public short analiseVAlign(String align){
	if(vAlignCache==null) vAlignCache=new HashMap();
	Short alignment = (Short)vAlignCache.get(align);

	if(alignment==null){
		if(align.equalsIgnoreCase("TOP"))  alignment =  CellStyle.VERTICAL_TOP;
		if(align.equalsIgnoreCase("CENTER"))  alignment =  CellStyle.VERTICAL_CENTER;
		if(align.equalsIgnoreCase("BOTTOM"))  alignment =  CellStyle.VERTICAL_BOTTOM;
		if(align.equalsIgnoreCase("JUSTIFY"))  alignment =  CellStyle.VERTICAL_JUSTIFY;
		if(alignment==null) alignment =  CellStyle.VERTICAL_JUSTIFY;
		vAlignCache.put(align, alignment);
	}
	return alignment;
}

public HorizontalAlignment analiseHAlign(String align){
	if(hAlignCache==null) hAlignCache=new HashMap();
	HorizontalAlignment alignment = (HorizontalAlignment)hAlignCache.get(align);

	if(alignment==null){
		if(align.equalsIgnoreCase("LEFT"))  alignment =  HorizontalAlignment.LEFT;
		if(align.equalsIgnoreCase("CENTER"))  alignment =  HorizontalAlignment.CENTER;
		if(align.equalsIgnoreCase("RIGHT"))  alignment =  HorizontalAlignment.RIGHT;
		if(alignment==null) alignment =  HorizontalAlignment.GENERAL;
		hAlignCache.put(align, alignment);
	}
	return alignment;
}

public void analiseBorder_Colour(XSSFCellStyle format, style internal_style){

	int border=0;
	try{
		border=new Integer(internal_style.getBORDER()).intValue();
	}catch(Exception e){
	}
//	if(border==0) borders.add(Border.NONE);
	if(border>0){
			if(border==1){
				format.setBorderTop(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				
			}
			if(border==2){
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==3){
				format.setBorderTop(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==4){
				format.setBorderLeft(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
			
			}
			if(border==5){
				format.setBorderTop(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				
			}
			if(border==6){
				format.setBorderLeft(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==7){
				format.setBorderTop(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==8){
				format.setBorderRight(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				
			}
			if(border==9){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderTop(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				
			}
			if(border==10){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==11){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderTop(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
				
			}
			if(border==12){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				
 			}
			if(border==13){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				format.setBorderTop(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				
			}
			if(border==14){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));

			}
			if(border==15){
				format.setBorderRight(BorderStyle.THIN);
				format.setBorderLeft(BorderStyle.THIN);
				format.setBorderTop(BorderStyle.THIN);
				format.setBorderBottom(BorderStyle.THIN);
				
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black)));
				format.setBottomBorderColor(getNearestColour(getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black)));
			}
	}

}



public String prepareContentString(String formatSG) {
	try{
		if(this.getContent()!=null){
			String content=(String)this.getContent();
			content=content.trim();
			this.setContent(content);
		}
	}catch(Exception e){
	}
	return super.prepareContentString(formatSG);
}

private java.util.Date getCallDate(String content, String formatS){
	if (formatS.toUpperCase().indexOf("DATETIME")==0){
		try{
			return new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd HH:mm").getTime());
		}catch(Exception e){
			try{
				return new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime());
			}catch(Exception ex){
			}
		}
	}

	if (formatS.toUpperCase().indexOf("DATE")==0){
		try{
			return new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd").getTime());
		}catch(Exception e){
			try{
				return new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime());
			}catch(Exception ex){
			}
		}
	}
	return null;
}


public short getDefDATEFORMAT() {
	return defDATEFORMAT;
}
public void setDefDATEFORMAT(short defDATEFORMAT) {
	this.defDATEFORMAT = defDATEFORMAT;
}
public short getDefDATETIMEFORMAT() {
	return defDATETIMEFORMAT;
}
public void setDefDATETIMEFORMAT(short defDATETIMEFORMAT) {
	this.defDATETIMEFORMAT = defDATETIMEFORMAT;
}
public Hyperlink getHyperlink() {
	return hyperlink;
}
public void setHyperlink(Hyperlink hyperlink) {
	this.hyperlink = hyperlink;
}
}
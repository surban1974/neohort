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
import java.util.List;
import java.util.StringTokenizer;

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
import org.apache.poi.ss.usermodel.VerticalAlignment;
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
	protected static HashMap<Color,XSSFColor> colorsCache;
	protected static HashMap<String,String> fontNameCache;
//	protected static HashMap<String,Short> alignCache;
	protected static HashMap<String,VerticalAlignment> vAlignCache;
	protected static HashMap<String,HorizontalAlignment> hAlignCache;

	protected short defDATEFORMAT;
	protected short defDATETIMEFORMAT;

	public static final String format_NUMBER="NUMBER";
	public static final String format_FORMULA="FORMULA";
	public static final String format_DATETIME="DATETIME";
	public static final String format_DATE="DATE";
	public static final String format_splitter="|";

	protected Hyperlink hyperlink = null;


public element() {
	super();
	Parameters.addElement("ID");
	reimposta();
}
public void add(report_element_base child) {}

public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {}
public Object execute(Hashtable<String, report_element_base> _beanLibrary) {
	return null;
}
public void reimposta(){
	name = "ELEMENT";
}

public Cell getCellC(int X,int Y, Workbook workbook, Sheet document, Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	
	Hashtable<String,CellStyle> wcfCash = null;
	if(_beanLibrary!=null){
		bean _sysWcfCash = (bean)_beanLibrary.get("SYSTEM:WritableCellFormatCash");
		if(_sysWcfCash!=null){
			wcfCash = (Hashtable<String,CellStyle>)_sysWcfCash.getContent();
			
			if(wcfCash==null)
				wcfCash = new Hashtable<String,CellStyle>();
		}else{
			wcfCash = new Hashtable<String,CellStyle>();
			_sysWcfCash = new bean();
			_sysWcfCash.setContent(wcfCash);
			_sysWcfCash.setName("SYSTEM");
			_sysWcfCash.setID("WritableCellFormatCash");
			_beanLibrary.put(_sysWcfCash.getName()+":"+_sysWcfCash.getID(),_sysWcfCash);
		}
	}else
		wcfCash = new Hashtable<String,CellStyle>();

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
	if(old!=null && old.getCellStyle()!=null){
		if(	internal_style.getFORMAT()!=null &&
				(
						internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)==-1 &&
						internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)==-1
				)
			)
		format = (XSSFCellStyle)old.getCellStyle();
	}
	else {
		if(internal_style!=null && wcfCash.get(internal_style.getCashKey()+"")!=null){
			try{
				String key = internal_style.getCashKey().replace("|", "");
				if(!key.equals(""))
					format = (CellStyle)wcfCash.get(internal_style.getCashKey()+"");
				if(format!=null)
					isFormat=true;
			}catch(Exception e){
				setError(e, iStub.log_ERROR);
			}
		}
	}

	if(format==null)
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

	if(internal_style.getWRAP()!=null && internal_style.getWRAP().equalsIgnoreCase("true")){
		try{
			format.setWrapText(true);
		}catch(Exception e){
		}
	}else if(internal_style.getWRAP()!=null && internal_style.getWRAP().equalsIgnoreCase("false")){
		try{
			format.setWrapText(false);
		}catch(Exception e){
		}
	}
	

	if(isFormat)
		wcfCash.put(internal_style.getCashKey()+"",format);

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

			Double dvalue = null;
			try{
				dvalue = new Double(frase.trim());
			}catch(Exception e){
				try{
					dvalue = new Double(frase.trim().replace(',', '.'));
				}catch(Exception ex){
				}
			}
			if(dvalue!=null)
				cell.setCellValue(dvalue.doubleValue());
			if(getHyperlink()!=null)
				cell.setHyperlink(getHyperlink());
			cell=null;
			return cell;
		}
	}catch(Exception e){
	}

	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_FORMULA)>-1){
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
				if(!isFormat)
					format =  workbook.createCellStyle();
				format.setDataFormat(defDATETIMEFORMAT);
				isFormat=true;
			}
			Date ret = getCallDate(frase,format_DATETIME,formatDate);
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
				if(!isFormat)
					format =  workbook.createCellStyle();
				format.setDataFormat(defDATEFORMAT);
				isFormat=true;
			}
			Date ret = getCallDate(frase,format_DATE,formatDate);
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

public void initCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
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
		List<Object> canvas = _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).getContentAsList();
		if(canvas.isEmpty()) return;
		Object current_Element = canvas.get(canvas.size()-1);
			canvas.remove(canvas.size()-1);
			if(canvas.isEmpty()) return;
		Object content_Element = canvas.get(canvas.size()-1);

		if(	initProcess.booleanValue() &&
			(	((bean)current_Element).getID().equals("PageFooter_") ||
				((bean)current_Element).getID().equals("PageHeader_"))
			){
			return;
		}
//		if(isError) return;


		if(content_Element instanceof bean){
			if(((bean)content_Element).getID().equals("PageFooter_"))
				((bean)content_Element).add2content(current_Element);
			if(((bean)content_Element).getID().equals("PageHeader_"))
				((bean)content_Element).add2content(current_Element);
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
	if(fontNameCache==null) fontNameCache=new HashMap<String,String>();
	String fontName = fontNameCache.get(name);

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

public static XSSFColor getNearestColour(Color awtColor){
	if(colorsCache==null) colorsCache=new HashMap<Color,XSSFColor>();


	XSSFColor userColor = colorsCache.get(awtColor);

    if (userColor == null){
    	userColor = new XSSFColor(awtColor);
        colorsCache.put(awtColor, userColor);
    }

    return userColor;
}



//@SuppressWarnings("deprecation")
//public short analiseVAlign(String align){
//	if(vAlignCache==null) vAlignCache=new HashMap<String,Short>();
//	Short alignment = vAlignCache.get(align);
//
//	if(alignment==null){
//		if(align.equalsIgnoreCase("TOP"))  alignment =  CellStyle.VERTICAL_TOP;
//		if(align.equalsIgnoreCase("CENTER"))  alignment =  CellStyle.VERTICAL_CENTER;
//		if(align.equalsIgnoreCase("BOTTOM"))  alignment =  CellStyle.VERTICAL_BOTTOM;
//		if(align.equalsIgnoreCase("JUSTIFY"))  alignment =  CellStyle.VERTICAL_JUSTIFY;
//		if(alignment==null) alignment =  CellStyle.VERTICAL_JUSTIFY;
//		vAlignCache.put(align, alignment);
//	}
//	return alignment;
//}

public VerticalAlignment analiseVAlign(String align){
	if(vAlignCache==null) vAlignCache=new HashMap<String,VerticalAlignment>();
	VerticalAlignment alignment = vAlignCache.get(align);

	if(alignment==null){
		if(align.equalsIgnoreCase("TOP"))  alignment =  VerticalAlignment.TOP;
		if(align.equalsIgnoreCase("CENTER"))  alignment =  VerticalAlignment.CENTER;
		if(align.equalsIgnoreCase("BOTTOM"))  alignment =  VerticalAlignment.BOTTOM;
		if(align.equalsIgnoreCase("JUSTIFY"))  alignment =  VerticalAlignment.JUSTIFY;
		if(alignment==null) alignment =  VerticalAlignment.JUSTIFY;
		vAlignCache.put(align, alignment);
	}
	return alignment;
}

public HorizontalAlignment analiseHAlign(String align){
	if(hAlignCache==null) hAlignCache=new HashMap<String,HorizontalAlignment>();
	HorizontalAlignment alignment = hAlignCache.get(align);

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

public java.util.Date getCallDate(String content, String format_id, String format){
	if (format_id.equalsIgnoreCase("DATETIME")){
		if(format!=null && !format.equals("")){
			try{
				return new java.util.Date(util_format.stringToData(content,format).getTime());
			}catch(Exception e){

			}
		}
		try{
			return new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd HH:mm").getTime());
		}catch(Exception e){
			try{
				return new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd HH:mm:ss.SSS").getTime());
			}catch(Exception ex){
				try{
					return new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime());
				}catch(Exception exe){
				}
			}
		}
	}

	if (format_id.equalsIgnoreCase("DATE")){
		if(format!=null && !format.equals("")){
			try{
				return new java.util.Date(util_format.stringToData(content,format).getTime());
			}catch(Exception e){

			}
		}
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
public static String getFormat(String format_id, String format_string){
	if(format_id==null || format_string==null || format_string.toUpperCase().indexOf(format_id.toUpperCase())==-1)
		return "";
	StringTokenizer st = new StringTokenizer(format_string, format_splitter);
	while(st.hasMoreTokens()){
		String mixed = st.nextToken();
		String firstpart = "";
		String secondpart = "";
		int sep = mixed.indexOf(":");
		if(sep>-1){
			firstpart = mixed.substring(0,sep);
			secondpart = mixed.substring(sep+1);
		}


		if(!firstpart.equals("") && firstpart.equalsIgnoreCase(format_id))
			return secondpart;
	}
	return "";
}
}
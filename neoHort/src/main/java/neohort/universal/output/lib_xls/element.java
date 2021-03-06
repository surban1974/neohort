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

package neohort.universal.output.lib_xls;





import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;


import jxl.Cell;
import jxl.Hyperlink;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_format;



public abstract class element extends report_element_base  implements report_element {

	private static final long serialVersionUID = 1L;
	protected static HashMap colorsCache;
	protected static HashMap fontNameCache;
	protected static HashMap alignCache;
	protected static HashMap vAlignCache;

	protected WritableCellFormat defDATEFORMAT;
	protected WritableCellFormat defDATETIMEFORMAT;

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

public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {}
public Object execute(Hashtable _beanLibrary) {
	return null;
}
public void reimposta(){
	name = "ELEMENT";
}

public Cell getCellC(Cell old,int X,int Y) {
	return getCellC(old, X, Y, null, null);
}

public Cell getCellC(Cell old,int X,int Y, Hashtable _tagLibrary, Hashtable _beanLibrary) {


	Hashtable wcfCash = null;
	if(_beanLibrary!=null){
		bean _sysWcfCash = (bean)_beanLibrary.get("SYSTEM:WritableCellFormatCash");
		if(_sysWcfCash!=null){
			wcfCash = (Hashtable)_sysWcfCash.getContent();
			if(wcfCash==null)
				wcfCash = new Hashtable();
		}else{
			wcfCash = new Hashtable();
			_sysWcfCash = new bean();
			_sysWcfCash.setContent(wcfCash);
			_sysWcfCash.setName("SYSTEM");
			_sysWcfCash.setID("WritableCellFormatCash");
			_beanLibrary.put(_sysWcfCash.getName()+":"+_sysWcfCash.getID(),_sysWcfCash);
		}
	}else
		wcfCash = new Hashtable();



	boolean isFormat=false;
	String frase = "";
	if(getContent()!=null) frase = (String)getContent();

	WritableCellFormat format = null;


	if(internal_style!=null && wcfCash.get(internal_style.getCashKey()+"")!=null){
		try{
			String key = internal_style.getCashKey().replace("|", "");
			if(!key.equals(""))
				format = (WritableCellFormat)wcfCash.get(internal_style.getCashKey()+"");
			if(format!=null)
				isFormat=true;
		}catch(Exception e){
			setError(e, iStub.log_ERROR);
		}
	}
	if(format==null){

		if(old!=null && old.getCellFormat()!=null){
			if(	internal_style.getFORMAT()!=null &&
					(
							internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)==-1 &&
							internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)==-1
					)
				)
			format = new WritableCellFormat (old.getCellFormat());

		}else{



			if(	internal_style.getFORMAT()!=null &&
				(
						internal_style.getFORMAT().toUpperCase().indexOf(format_NUMBER)>-1 ||
						internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)>-1 ||
						internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)>-1
				)
			){
				if(internal_style.getFORMAT().toUpperCase().indexOf(format_NUMBER)>-1){
					String formatNumber = getFormat(format_NUMBER, internal_style.getFORMAT());
					if(!formatNumber.equals("")){
						format = new WritableCellFormat(new NumberFormat(formatNumber));
						isFormat=true;
					}else
						format =  new WritableCellFormat();
				}
				boolean isDatatime = false;
				if(!isFormat && internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)>-1){
					String formatDate = getFormat(format_DATETIME, internal_style.getFORMAT());
					if(!formatDate.equals("")){
						format = new WritableCellFormat(new DateFormat(formatDate));
						isFormat=true;
					}else
						format =  new WritableCellFormat(new DateFormat("dd/MM/yyyy HH:mm"));
					isDatatime=true;
				}
				if(!isFormat && !isDatatime && internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)>-1){
					String formatDate = getFormat(format_DATE, internal_style.getFORMAT());
					if(!formatDate.equals("")){
						format = new WritableCellFormat(new DateFormat(formatDate));
						isFormat=true;
					}else
						format =  new WritableCellFormat(new DateFormat("dd/MM/yyyy"));
				}

			}else
				format =  new WritableCellFormat();
		}
		if(format==null)
			format =  new WritableCellFormat();

		WritableFont font = null;
		try{
			if(internal_style.getFONT()!=null && !internal_style.getFONT().equals("")){
				font = new WritableFont(analizeFontName(internal_style.getFONT()));
				isFormat=true;
			}else font=new WritableFont(format.getFont());
		}catch(Exception e){
		}

		if(internal_style.getFONT_SIZE()!=null && !internal_style.getFONT_SIZE().equals("")){
			int font_size = 10;
			try{
				font_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
				font.setPointSize(font_size);
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
				font.setBoldStyle(WritableFont.BOLD);
				isFormat=true;
			}catch(Exception e){
			}
		}
		if(	internal_style.getFONT_TYPE()!=null && internal_style.getFONT_TYPE().equalsIgnoreCase("UNDERLINE")){
			try{
				font.setUnderlineStyle(UnderlineStyle.SINGLE);
				isFormat=true;
			}catch(Exception e){
			}
		}
		if(	internal_style.getFONT_STYLE()!=null && internal_style.getFONT_STYLE().equalsIgnoreCase("STRIKE")){
			try{
				font.setStruckout(true);
				isFormat=true;
			}catch(Exception e){
			}
		}

		if(internal_style.getFONT_COLOR()!=null && !internal_style.getFONT_COLOR().equals("")){
			try{


				font.setColour(getNearestColour(getField_Color(internal_style.getFONT_COLOR(),Color.black)));
				isFormat=true;
			}catch(Exception e){
			}
		}

		format.setFont(font);

		Vector[] borders_colours = analiseBorder_Colour(internal_style);
		Vector borders = borders_colours[0];
		Vector colours = borders_colours[1];
		if (borders.size() >0) isFormat=true;
		for(int i=0;i<borders.size();i++){
			try{
				format.setBorder((Border)borders.get(i),BorderLineStyle.THIN,(Colour)colours.get(i));
			}catch(Exception e){
			}
		}

		if(internal_style.getBACK_COLOR()!=null && !internal_style.getBACK_COLOR().equals("")){
			try{
				format.setBackground(getNearestColour(getField_Color(internal_style.getBACK_COLOR(),Color.white)));
				isFormat=true;
			}catch(Exception e){
			}
		}
		if(internal_style.getALIGN()!=null && !internal_style.getALIGN().equals("")){
			try{
				format.setAlignment(analiseAlign(internal_style.getALIGN()));
				isFormat=true;
			}catch(Exception e){
			}
		}
		if(internal_style.getTEXT_ALIGN_H()!=null && !internal_style.getTEXT_ALIGN_H().equals("")){
			try{
				format.setAlignment(analiseAlign(internal_style.getTEXT_ALIGN_H()));
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
				format.setOrientation(analiseOrientation(rotation));
				isFormat=true;
			}catch(Exception e){
			}
		}

		if(internal_style.getWRAP()!=null && internal_style.getWRAP().equalsIgnoreCase("true")){
			try{
				format.setWrap(true);
			}catch(Exception e){
			}
		}else if(internal_style.getWRAP()!=null && internal_style.getWRAP().equalsIgnoreCase("false")){
			try{
				format.setWrap(false);
			}catch(Exception e){
			}
		}

		if(isFormat)
			wcfCash.put(internal_style.getCashKey()+"",format);

	}
	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_NUMBER)>-1){
			Double dvalue = null;
			try{
				dvalue = new Double(frase.trim());
			}catch(Exception e){
				try{
					dvalue = new Double(frase.trim().replace(',', '.'));
				}catch(Exception ex){
				}
			}
			if(dvalue!=null){
				if (isFormat)
					return new Number(X,Y,dvalue.doubleValue(),format);
				else
					return new Number(X,Y,dvalue.doubleValue());
			}
		}
	}catch(Exception e){
	}

	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_FORMULA)==0){
			return new Formula(X, Y, frase);
		}
	}catch(Exception e){
	}
	boolean isDatatime = false;
	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATETIME)>-1){
			String formatDate = getFormat(format_DATETIME, internal_style.getFORMAT());
			Date ret = getCallDate(frase,format_DATETIME,formatDate);
			if(ret!=null){
				if (isFormat)
					return new DateTime(X,Y,ret,format);
				else{
					if(defDATETIMEFORMAT==null) defDATETIMEFORMAT = new WritableCellFormat (new DateFormat("dd/MM/yyyy hh:mm"));
					return new DateTime(X,Y,ret,defDATETIMEFORMAT);
				}
			}
			isDatatime = true;
		}
	}catch(Exception e){
	}
	try{
		if(internal_style.getFORMAT()!=null & internal_style.getFORMAT().toUpperCase().indexOf(format_DATE)>-1 && !isDatatime){
			String formatDate = getFormat(format_DATE, internal_style.getFORMAT());
			Date ret = getCallDate(frase,format_DATE,formatDate);
			if(ret!=null){
				if (isFormat)
					return new DateTime(X,Y,ret,format);
				else{
					if(defDATEFORMAT==null) defDATEFORMAT = new WritableCellFormat (new DateFormat("dd/MM/yyyy"));
					return new DateTime(X,Y,ret,defDATEFORMAT);
				}
			}
		}
	}catch(Exception e){
	}


	frase = prepareContentString(internal_style.getFORMAT());


	Label cell = null;
	if(frase==null || frase.equals("")){
		if (isFormat) return new Blank(X,Y,format);
		else return new Blank(X,Y);
	}
	else {
		if (isFormat) cell = new Label(X,Y,frase,format);
		else cell = new Label(X,Y,frase);
	}
	frase = null;
	return cell;
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

		if(content_Element instanceof WritableSheet){

			if(current_Element instanceof Blank){
				((WritableSheet)content_Element).addCell((Blank)current_Element);
				current_Element=null;
				return;
			}

			if(current_Element instanceof Label){
				((WritableSheet)content_Element).addCell((Label)current_Element);
				current_Element=null;
				return;
			}
			if(current_Element instanceof Number){
				((WritableSheet)content_Element).addCell((Number)current_Element);
				current_Element=null;
				return;
			}
			if(current_Element instanceof Formula){
				((WritableSheet)content_Element).addCell((Formula)current_Element);
				current_Element=null;
				return;
			}
			if(current_Element instanceof DateTime){
				((WritableSheet)content_Element).addCell((DateTime)current_Element);
				current_Element=null;
				return;
			}
			if(current_Element instanceof jxl.write.Boolean){
				((WritableSheet)content_Element).addCell((jxl.write.Boolean)current_Element);
				current_Element=null;
				return;
			}

			if(current_Element instanceof WritableImage){
				((WritableSheet)content_Element).addImage((WritableImage)current_Element);
				current_Element=null;
				return;
			}
			if(current_Element instanceof WritableHyperlink){
				((WritableSheet)content_Element).addHyperlink((WritableHyperlink)current_Element);
				current_Element=null;
				return;
			}

			((WritableSheet)content_Element).addCell((Label)current_Element);
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
public WritableFont.FontName analizeFontName(String name){
	if(fontNameCache==null) fontNameCache=new HashMap();
	WritableFont.FontName fontName = (WritableFont.FontName)fontNameCache.get(name);

	if(fontName==null){
		if(name==null || name.equals("")) fontName = WritableFont.COURIER;
		if(name.equalsIgnoreCase("HELVETICA")) fontName = WritableFont.ARIAL;
		if(name.equalsIgnoreCase("TIMES_NEW_ROMAN")) fontName = WritableFont.TIMES;
		if(name.equalsIgnoreCase("TAHOMA")) fontName = WritableFont.TAHOMA;
		if(fontName==null) fontName = WritableFont.COURIER;
		fontNameCache.put(name, fontName);
	}

	return fontName;
}

public static Colour getNearestColour(Color awtColor){
	if(colorsCache==null) colorsCache=new HashMap();
    Colour color = (Colour) colorsCache.get(awtColor);

    if (color == null)
    {
        Colour[] colors = Colour.getAllColours();
        if ((colors != null) && (colors.length > 0))
        {
            Colour crtColor = null;
            int[] rgb = null;
            int diff = 0;
            int minDiff = 999;

            for (int i = 0; i < colors.length; i++)
            {
                crtColor = colors[i];
                rgb = new int[3];
                rgb[0] = crtColor.getDefaultRGB().getRed();
                rgb[1] = crtColor.getDefaultRGB().getGreen();
                rgb[2] = crtColor.getDefaultRGB().getBlue();

                diff = Math.abs(rgb[0] - awtColor.getRed()) + Math.abs(rgb[1] - awtColor.getGreen()) + Math.abs(rgb[2] - awtColor.getBlue());

                if (diff < minDiff)
                {
                    minDiff = diff;
                    color = crtColor;
                }
            }
        }

        colorsCache.put(awtColor, color);
    }

    return color;
}


public Alignment analiseAlign(String align){
	if(alignCache==null) alignCache=new HashMap();
	Alignment alignment = (Alignment)alignCache.get(align);

	if(alignment==null){

	if(align==null || align.equals("")) alignment= Alignment.GENERAL;
		if(align.equalsIgnoreCase("LEFT"))  alignment= Alignment.LEFT;
		if(align.equalsIgnoreCase("CENTER"))  alignment= Alignment.CENTRE;
		if(align.equalsIgnoreCase("RIGHT"))  alignment= Alignment.RIGHT;
		if(align.equalsIgnoreCase("FILL"))  alignment= Alignment.FILL;
		if(align.equalsIgnoreCase("JUSTIFY"))  alignment= Alignment.JUSTIFY;
		if(alignment==null) alignment= Alignment.GENERAL;
		alignCache.put(align, alignment);
	}
	return alignment;
}

public VerticalAlignment analiseVAlign(String align){
	if(vAlignCache==null) vAlignCache=new HashMap();
	VerticalAlignment alignment = (VerticalAlignment)vAlignCache.get(align);

	if(alignment==null){
		if(align.equalsIgnoreCase("TOP"))  alignment =  VerticalAlignment.TOP;
		if(align.equalsIgnoreCase("CENTER"))  alignment =  VerticalAlignment.CENTRE;
		if(align.equalsIgnoreCase("BOTTOM"))  alignment =  VerticalAlignment.BOTTOM;
		if(align.equalsIgnoreCase("JUSTIFY"))  alignment =  VerticalAlignment.JUSTIFY;
		if(alignment==null) alignment =  VerticalAlignment.JUSTIFY;
		vAlignCache.put(align, alignment);
	}
	return alignment;
}

public Vector[] analiseBorder_Colour(style internal_style){


	Vector[] result= new Vector[2];
	Vector borders = new Vector();
	Vector colours = new Vector();
	int border=0;
	try{
		border=new Integer(internal_style.getBORDER()).intValue();
	}catch(Exception e){
	}
//	if(border==0) borders.add(Border.NONE);
	if(border>0){
		if(border==1) borders.add(Border.TOP);
		if(border==2) borders.add(Border.BOTTOM);
			if(border==3){
				borders.add(Border.TOP);
				borders.add(Border.BOTTOM);
			}
		if(border==4) borders.add(Border.LEFT);
			if(border==5){
				borders.add(Border.TOP);
				borders.add(Border.LEFT);
			}
			if(border==6){
				borders.add(Border.LEFT);
				borders.add(Border.BOTTOM);
			}
			if(border==7){
				borders.add(Border.TOP);
				borders.add(Border.LEFT);
				borders.add(Border.BOTTOM);
			}
		if(border==8) borders.add(Border.RIGHT);
			if(border==9){
				borders.add(Border.RIGHT);
				borders.add(Border.TOP);
			}
			if(border==10){
				borders.add(Border.RIGHT);
				borders.add(Border.BOTTOM);
			}
			if(border==11){
				borders.add(Border.RIGHT);
				borders.add(Border.TOP);
				borders.add(Border.BOTTOM);
			}
			if(border==12){
				borders.add(Border.RIGHT);
				borders.add(Border.LEFT);
 			}
			if(border==13){
				borders.add(Border.RIGHT);
				borders.add(Border.LEFT);
				borders.add(Border.TOP);
			}
			if(border==14){
				borders.add(Border.RIGHT);
				borders.add(Border.LEFT);
				borders.add(Border.BOTTOM);
			}
			if(border==15){
				borders.add(Border.RIGHT);
				borders.add(Border.LEFT);
				borders.add(Border.TOP);
				borders.add(Border.BOTTOM);
//				result.add(Border.ALL);
			}
	}
	for(int i=0;i<borders.size();i++){
		Border local = (Border)borders.get(i);
		Colour local_colour = getNearestColour(Color.black);
		if(!internal_style.getBORDER_COLOR().equals(""))
			local_colour = getNearestColour(getField_Color(internal_style.getBORDER_COLOR(),Color.black));
		if(local.equals(Border.TOP)){
			if(!internal_style.getBORDER_COLOR_TOP().equals(""))
				local_colour = getNearestColour(getField_Color(internal_style.getBORDER_COLOR_TOP(),Color.black));
		}
		if(local.equals(Border.BOTTOM)){
			if(!internal_style.getBORDER_COLOR_BOTTOM().equals(""))
				local_colour = getNearestColour(getField_Color(internal_style.getBORDER_COLOR_BOTTOM(),Color.black));
		}
		if(local.equals(Border.LEFT)){
			if(!internal_style.getBORDER_COLOR_LEFT().equals(""))
				local_colour = getNearestColour(getField_Color(internal_style.getBORDER_COLOR_LEFT(),Color.black));
		}
		if(local.equals(Border.RIGHT)){
			if(!internal_style.getBORDER_COLOR_RIGHT().equals(""))
				local_colour = getNearestColour(getField_Color(internal_style.getBORDER_COLOR_RIGHT(),Color.black));

		}
		colours.add(local_colour);
	}
	result[0]=borders;
	result[1]=colours;
	return result;
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

public Orientation analiseOrientation(float rotation){
	if(rotation==0) return null;
	int ang = (int)rotation;
	if(ang>360){
		ang = ang % 360;
	}
	if(ang<0) ang = 360+ang;
	if(ang>340 && ang <=360) ang=0;
	if(ang>=0 && ang <=22) ang=0;
	if(ang>22 && ang <68) ang=45;
	if(ang>=68 && ang<=180) ang=90;
	if(ang>180 && ang<=292) ang=180;
	if(ang>292 && ang<=340) ang=135;
	return Orientation.getOrientation(ang);
/*
	if(rotation==45) return Orientation.PLUS_45;
	if(rotation==90) return Orientation.HORIZONTAL;
	if(rotation==135) return Orientation.MINUS_45;
	if(rotation==180) return Orientation.MINUS_90;
	if(rotation==255) return Orientation.MINUS_45;
	if(rotation==270) return Orientation.MINUS_90;
	return Orientation.HORIZONTAL;
*/
}
public WritableCellFormat getDefDATEFORMAT() {
	return defDATEFORMAT;
}
public void setDefDATEFORMAT(WritableCellFormat defDATEFORMAT) {
	this.defDATEFORMAT = defDATEFORMAT;
}
public WritableCellFormat getDefDATETIMEFORMAT() {
	return defDATETIMEFORMAT;
}
public void setDefDATETIMEFORMAT(WritableCellFormat defDATETIMEFORMAT) {
	this.defDATETIMEFORMAT = defDATETIMEFORMAT;
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
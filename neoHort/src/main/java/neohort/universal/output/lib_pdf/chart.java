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

package neohort.universal.output.lib_pdf;

import java.awt.Color;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.universal.output.lib.chart_pdf.I_chart_content;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;
import neohort.universal.output.lib.chart_pdf.chart_elementFactory;

import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class chart extends element{
	private static final long serialVersionUID = 4642174161706470914L;
	private java.lang.String CHART_TYPE;
	private java.lang.String CHART_DATA_X;
	private java.lang.String CHART_DATA_Y;
	private java.lang.String CHART_DATA_Z;
	private java.lang.String FORMAT_SCALE_XYZ;
	private java.lang.String SHOW_SCALE_XYZ;
	private java.lang.String FONT_SCALE_XYZ;
	private java.lang.String FONT_SCALE_SIZE_XYZ;
	private java.lang.String FONT_SCALE_COLOR_XYZ;
	private java.lang.String LABEL_X;
	private java.lang.String LABEL_Y;
	private java.lang.String LABEL_Z;
	private java.lang.String GR_SCALE_XYZ;
	private java.lang.String GR_LABEL_XYZ;
	private java.lang.String ALIGN_LABEL_XYZ;
	private java.lang.String MAXELEMENT_LABEL_XYZ;
	private java.lang.String FONT_LABEL_XYZ;
	private java.lang.String FONT_LABEL_SIZE_XYZ;
	private java.lang.String FONT_LABEL_COLOR_XYZ;
	private java.lang.String ELEMENT_COLOR_3D;
	private java.lang.String LABEL_TOP;
	private java.lang.String ALIGN_LABEL_TOP;
	private java.lang.String FONT_LABEL_TOP;
	private java.lang.String FONT_LABEL_SIZE_TOP;
	private java.lang.String FONT_LABEL_COLOR_TOP;
	private java.lang.String FORMAT_CHART_INPUT_DATA;
	private java.lang.String EXTERNAL;	
	private PdfPCell cell;			

	
public chart() {
	super();	
}
public Image chartFactory(PdfWriter pWriter) {
	try{
		PdfContentByte cb = pWriter.getDirectContent();
		return createImageWithBarcode(cb);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
	
	return null;
}
private Image chartFactory(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		PdfWriter pWriter = (PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
		PdfContentByte cb = pWriter.getDirectContent();

		return createImageWithBarcode(cb);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
	
	return null;
}
private Image createImageWithBarcode(PdfContentByte cb) {
	try {
		Image img = Image.getInstance(createTemplateWithBarcode(cb));

    	return img;
	}catch (Exception e) {
		throw new ExceptionConverter(e);
	}
}
/*
private void saveImage(Image img){
	try{
	Color foreground = Color.white;
	Color background = Color.white;
	int f = foreground.getRGB();
	int g = background.getRGB();	
	byte[] byte_img = img.rawData();
	Canvas canvas = new Canvas();
	boolean print = true;
	int ptr = 0;	
	int nn = 2;
	int width = (int)img.width();
	int height = (int)img.height();	
	int pix[] = new int[width * height];
	
	for (int k = 0; k < byte_img.length; ++k) {
		int w = (byte_img[k] == 0 ? 1 : nn);
		int c = g;
		if (print)
			c = f;
		print = !print;
		for (int j = 0; j < w; ++j)
			pix[ptr++] = c;
	}
	for (int k = width; k < pix.length; k += width) {
		System.arraycopy(pix, 0, pix, k, width); 
	}
	java.awt.Image result = canvas.createImage(new MemoryImageSource(width, height, pix, 0, width));	
	int test=0;
	}catch(Exception e){	
	}
}
*/
private PdfTemplate createTemplateWithBarcode(PdfContentByte cb) {
	PdfTemplate tp = cb.createTemplate(0, 0);
	
    Rectangle rect = placeBarcode(tp);
    tp.setBoundingBox(rect);
 
    return tp;
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		
			int _align = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
			int border = 0;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
			}catch(Exception e){}
			com.lowagie.text.Image chartIm = null;
			try{
				chartIm = chartFactory(_tagLibrary,_beanLibrary);
			}catch(Exception e){}
			
			if(chartIm!=null && !internal_style.getABSOLUTE_X().equals("") && !internal_style.getABSOLUTE_Y().equals("")){
				try{
					chartIm.setAbsolutePosition(new Float(internal_style.getABSOLUTE_X()).floatValue(),new Float(internal_style.getABSOLUTE_Y()).floatValue());
					((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);
					if(_tagLibrary.get(getName()+":"+getID())==null)
						_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
					else _tagLibrary.remove(getName()+":"+getID());		
					return;
				}catch(Exception e){
				}
			}
			
			
			if(chartIm==null) cell = new PdfPCell(new Phrase("ERRORE: It's impossible create the chart. Internal error."));	
			else cell = new PdfPCell(chartIm);
			cell.setHorizontalAlignment(_align);
			cell.setBorder(border);
			if(padding!=0) cell.setPadding(padding);

		
			
			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);

		
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());		

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public java.lang.String getALIGN_LABEL_TOP() {
	return ALIGN_LABEL_TOP;
}
public java.lang.String getALIGN_LABEL_XYZ() {
	return ALIGN_LABEL_XYZ;
}
private BaseFont getBaseFont(String name, BaseFont d_value) {
	BaseFont result = d_value;
	try{
		result = BaseFont.createFont(name, "winansi", false);
	}catch(Exception e){
		try{
			result = BaseFont.createFont(name.toLowerCase(), "winansi", false);
		}catch(Exception ex){
			try{
				result = BaseFont.createFont(name.toUpperCase(), "winansi", false);
			}catch(Exception exp){}
		}
	}
	return result;
}
private boolean getBoolean(String name, boolean d_value) {
	boolean result = d_value;
	try{
		result = Boolean.valueOf(name).booleanValue();
	}catch(Exception e){
	}
	return result;
}
public java.lang.String getCHART_DATA_X() {
	return CHART_DATA_X;
}
public java.lang.String getCHART_DATA_Y() {
	return CHART_DATA_Y;
}
public java.lang.String getCHART_DATA_Z() {
	return CHART_DATA_Z;
}
public java.lang.String getCHART_TYPE() {
	return CHART_TYPE;
}
private I_chart_dati getDatiXY(String chart_type) {
	try{
		String format_Scale_X="";
		String format_Scale_Y="";
		String format_Scale_Z="";
		java.util.StringTokenizer st = new java.util.StringTokenizer(getFORMAT_CHART_INPUT_DATA(), ";");
		if(st.hasMoreTokens()) format_Scale_X = st.nextToken();
		if(st.hasMoreTokens()) format_Scale_Y = st.nextToken();
		if(st.hasMoreTokens()) format_Scale_Z = st.nextToken();

		String format_X="";
		String format_Y="";
		java.util.StringTokenizer st_ = new java.util.StringTokenizer(getFORMAT_SCALE_XYZ(), ";");
		if(st_.hasMoreTokens()) format_X = st_.nextToken();
		if(st_.hasMoreTokens()) format_Y = st_.nextToken();

			
		I_chart_dati dXY = 
			new chart_elementFactory().chartFactory(chart_type,motore,isError).ChartDatiFactory();
		dXY.setFormat(0,format_X);
		dXY.setFormat(1,format_Y);

		
		java.util.StringTokenizer stDatiX = new java.util.StringTokenizer(getCHART_DATA_X(), ";");
		java.util.StringTokenizer stDatiY = new java.util.StringTokenizer(getCHART_DATA_Y(), ";");
		java.util.StringTokenizer stDatiZ = new java.util.StringTokenizer(getCHART_DATA_Z(), ";");
		while(stDatiX.hasMoreTokens())
			dXY.setDati(0,format_Scale_X,stDatiX.nextToken()); 
		while(stDatiY.hasMoreTokens())
			dXY.setDati(1,format_Scale_Y,stDatiY.nextToken());
		while(stDatiZ.hasMoreTokens())
			dXY.setDati(2,format_Scale_Z,stDatiZ.nextToken());
		
		dXY.setExternal_parameters(this.external_parameters);
		return dXY; 
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
		return null;
	}	
}
public java.lang.String getELEMENT_COLOR_3D() {
	return ELEMENT_COLOR_3D;
}
public java.lang.String getEXTERNAL() {
	return EXTERNAL;
}
public java.lang.String getFONT_LABEL_COLOR_TOP() {
	return FONT_LABEL_COLOR_TOP;
}
public java.lang.String getFONT_LABEL_COLOR_XYZ() {
	return FONT_LABEL_COLOR_XYZ;
}
public java.lang.String getFONT_LABEL_SIZE_TOP() {
	return FONT_LABEL_SIZE_TOP;
}
public java.lang.String getFONT_LABEL_SIZE_XYZ() {
	return FONT_LABEL_SIZE_XYZ;
}
public java.lang.String getFONT_LABEL_TOP() {
	return FONT_LABEL_TOP;
}
public java.lang.String getFONT_LABEL_XYZ() {
	return FONT_LABEL_XYZ;
}
public java.lang.String getFONT_SCALE_COLOR_XYZ() {
	return FONT_SCALE_COLOR_XYZ;
}
public java.lang.String getFONT_SCALE_SIZE_XYZ() {
	return FONT_SCALE_SIZE_XYZ;
}
public java.lang.String getFONT_SCALE_XYZ() {
	return FONT_SCALE_XYZ;
}
public java.lang.String getFORMAT_CHART_INPUT_DATA() {
	return FORMAT_CHART_INPUT_DATA;
}
public java.lang.String getFORMAT_SCALE_XYZ() {
	return FORMAT_SCALE_XYZ;
}
public java.lang.String getGR_LABEL_XYZ() {
	return GR_LABEL_XYZ;
}
public java.lang.String getGR_SCALE_XYZ() {
	return GR_SCALE_XYZ;
}
private int getInt(String name, int d_value) {
	int result = d_value;
	try{
		result = Integer.valueOf(name).intValue();
	}catch(Exception e){
	}
	return result;
}
public java.lang.String getLABEL_TOP() {
	return LABEL_TOP;
}
public java.lang.String getLABEL_X() {
	return LABEL_X;
}
public java.lang.String getLABEL_Y() {
	return LABEL_Y;
}
public java.lang.String getLABEL_Z() {
	return LABEL_Z;
}
public java.lang.String getMAXELEMENT_LABEL_XYZ() {
	return MAXELEMENT_LABEL_XYZ;
}
public java.lang.String getSHOW_SCALE_XYZ() {
	return SHOW_SCALE_XYZ;
}
private Rectangle placeBarcode(PdfContentByte cb) { 
	try{
		String chart_type = getCHART_TYPE();
		if(chart_type.toUpperCase().indexOf("CLASS:")==0)
			chart_type = chart_type.substring(6);
		else chart_type = "neohort.universal.output.lib.chart_pdf.chart_content_" +	chart_type;
	
			
		I_chart_dati dXY = getDatiXY(chart_type);
		if(dXY==null) return new Rectangle(0,0);
		
		Color font_label_color_top = getField_Color(new Color(0).getClass(),getFONT_LABEL_COLOR_TOP(),Color.black);
		BaseFont font_label_top = getBaseFont(getFONT_LABEL_TOP(),BaseFont.createFont("Courier", "winansi", false));
		int font_label_size_top = getInt(getFONT_LABEL_SIZE_TOP(),10);
	
		
	Color font_color_scale_X = Color.black;
	Color font_color_scale_Y = Color.black;
		java.util.StringTokenizer st_font_color_scale = new java.util.StringTokenizer(getFONT_SCALE_COLOR_XYZ(), ";");
		if(st_font_color_scale.hasMoreTokens()) font_color_scale_X = getField_Color(new Color(0).getClass(),st_font_color_scale.nextToken(),Color.black);
		if(st_font_color_scale.hasMoreTokens()) font_color_scale_Y = getField_Color(new Color(0).getClass(),st_font_color_scale.nextToken(),Color.black);
	
	Color font_color_label_X = Color.black;
	Color font_color_label_Y = Color.black;
		java.util.StringTokenizer st_font_color_label = new java.util.StringTokenizer(getFONT_LABEL_COLOR_XYZ(), ";");
		if(st_font_color_label.hasMoreTokens()) font_color_label_X = getField_Color(new Color(0).getClass(),st_font_color_label.nextToken(),Color.black);
		if(st_font_color_label.hasMoreTokens()) font_color_label_Y = getField_Color(new Color(0).getClass(),st_font_color_label.nextToken(),Color.black);
	
	Color color_background = getField_Color(new Color(0).getClass(),internal_style.getBACK_COLOR(),null);
	
	Color element_color_2d_bar = Color.blue;
	Color element_color_2d_right = Color.lightGray;
	Color element_color_2d_top = Color.black;
		java.util.StringTokenizer st_element_color_2d = new java.util.StringTokenizer(getELEMENT_COLOR_3D(), ";");
		if(st_element_color_2d.hasMoreTokens()) element_color_2d_bar = getField_Color(new Color(0).getClass(),st_element_color_2d.nextToken(),Color.blue);
		if(st_element_color_2d.hasMoreTokens()) element_color_2d_right = getField_Color(new Color(0).getClass(),st_element_color_2d.nextToken(),Color.lightGray);
		if(st_element_color_2d.hasMoreTokens()) element_color_2d_top = getField_Color(new Color(0).getClass(),st_element_color_2d.nextToken(),Color.black);
	
	
	BaseFont font_scale_X = BaseFont.createFont("Courier", "winansi", false);
	BaseFont font_scale_Y = BaseFont.createFont("Courier", "winansi", false);
		java.util.StringTokenizer st_font_scale = new java.util.StringTokenizer(getFONT_SCALE_XYZ(), ";");
		if(st_font_scale.hasMoreTokens()) font_scale_X = getBaseFont(st_font_scale.nextToken(),BaseFont.createFont("Courier", "winansi", false));
		if(st_font_scale.hasMoreTokens()) font_scale_Y = getBaseFont(st_font_scale.nextToken(),BaseFont.createFont("Courier", "winansi", false));
	
	int font_scale_size_X = 10;
	int font_scale_size_Y = 10;
		java.util.StringTokenizer st_font_scale_size = new java.util.StringTokenizer(getFONT_SCALE_SIZE_XYZ(), ";");
		if(st_font_scale_size.hasMoreTokens()) font_scale_size_X = getInt(st_font_scale_size.nextToken(),10);
		if(st_font_scale_size.hasMoreTokens()) font_scale_size_Y = getInt(st_font_scale_size.nextToken(),10);
	
	BaseFont font_label_X = BaseFont.createFont("Courier", "winansi", false);
	BaseFont font_label_Y = BaseFont.createFont("Courier", "winansi", false);
		java.util.StringTokenizer st_font_label = new java.util.StringTokenizer(getFONT_LABEL_XYZ(), ";");
		if(st_font_label.hasMoreTokens()) font_label_X = getBaseFont(st_font_label.nextToken(),BaseFont.createFont("Courier", "winansi", false));
		if(st_font_label.hasMoreTokens()) font_label_Y = getBaseFont(st_font_label.nextToken(),BaseFont.createFont("Courier", "winansi", false));
	
	int font_label_size_X = 10;
	int font_label_size_Y = 10;
		java.util.StringTokenizer st_font_label_size = new java.util.StringTokenizer(getFONT_LABEL_SIZE_XYZ(), ";");
		if(st_font_label_size.hasMoreTokens()) font_label_size_X = getInt(st_font_label_size.nextToken(),10);
		if(st_font_label_size.hasMoreTokens()) font_label_size_Y = getInt(st_font_label_size.nextToken(),10);
	
	boolean show_scale_X = false;
	boolean show_scale_Y = false;
		java.util.StringTokenizer st_show_scale = new java.util.StringTokenizer(getSHOW_SCALE_XYZ(), ";");
		if(st_show_scale.hasMoreTokens()) show_scale_X = getBoolean(st_show_scale.nextToken(),true);
		if(st_show_scale.hasMoreTokens()) show_scale_Y = getBoolean(st_show_scale.nextToken(),true);
	
	int gr_scale_X = 90;
	int gr_scale_Y = 0;
		java.util.StringTokenizer st_gr_scale = new java.util.StringTokenizer(getGR_SCALE_XYZ(), ";");
		if(st_gr_scale.hasMoreTokens()) gr_scale_X = getInt(st_gr_scale.nextToken(),90);
		if(st_gr_scale.hasMoreTokens()) gr_scale_Y = getInt(st_gr_scale.nextToken(),0);
	
	int gr_label_X = 0;
	int gr_label_Y = 90;
		java.util.StringTokenizer st_gr_label = new java.util.StringTokenizer(getGR_LABEL_XYZ(), ";");
		if(st_gr_label.hasMoreTokens()) gr_label_X = getInt(st_gr_label.nextToken(),0);
		if(st_gr_label.hasMoreTokens()) gr_label_Y = getInt(st_gr_label.nextToken(),90);
	
	int max_scale_X = 10;
	int max_scale_Y = 10;
		java.util.StringTokenizer st_max_scale = new java.util.StringTokenizer(getMAXELEMENT_LABEL_XYZ(), ";");
		if(st_max_scale.hasMoreTokens()) max_scale_X = getInt(st_max_scale.nextToken(),10);
		if(st_max_scale.hasMoreTokens()) max_scale_Y = getInt(st_max_scale.nextToken(),10);
	
	String align_label_top = getALIGN_LABEL_TOP();	
	String align_label_X = "CENTER";
	String align_label_Y = "CENTER";
		java.util.StringTokenizer st_align_label = new java.util.StringTokenizer(getALIGN_LABEL_XYZ(), ";");
		if(st_align_label.hasMoreTokens()) align_label_X = st_align_label.nextToken();
		if(st_align_label.hasMoreTokens()) align_label_Y = st_align_label.nextToken();
	
	String label_top = 	getLABEL_TOP();
	String label_X = 	getLABEL_X();
	String label_Y = 	getLABEL_Y();
	
	
		int _h_d = 0;
		int _w_d = 0;
		try{
			_w_d = Integer.valueOf(internal_style.getDIMENTION_H()).intValue();
			_h_d = Integer.valueOf(internal_style.getDIMENTION_V()).intValue();				
		}catch(Exception e){
			_h_d = 0;
			_w_d = 0;
		}
	
	
	
		if(color_background!=null){
			cb.setColorFill(color_background);
			cb.rectangle(0,0,_h_d,_w_d); 
			cb.fill();
		}	
			
		I_chart_content ch_contentT = new chart_elementFactory().chartFactory(chart_type,motore,isError);
			ch_contentT.setExternal(EXTERNAL);
			ch_contentT.setOrientation(I_chart_content.or_TOP);
			ch_contentT.setDati(dXY);
			ch_contentT.setX(0);
			ch_contentT.setY(0);
			ch_contentT.setWidth(_w_d);
			ch_contentT.setHeight(_h_d); 
	
			ch_contentT.setLabel(label_top);
			ch_contentT.setLabel_font(font_label_top);
			ch_contentT.setLabel_fontsize(font_label_size_top);
			ch_contentT.setLabel_color(font_label_color_top);
			ch_contentT.setLabel_gr(0);
			ch_contentT.setLabel_align(align_label_top);
	
			ch_contentT.setScale(dXY.getScale(0,max_scale_X));
			ch_contentT.setScale_font(font_scale_X);
			ch_contentT.setScale_fontsize(font_scale_size_X);
			ch_contentT.setScale_color(font_color_scale_X);
			ch_contentT.setScale_gr(gr_scale_X);
			ch_contentT.setScale_max(max_scale_X);
			ch_contentT.setShow_scale(show_scale_X);
			
			ch_contentT.setExternal_parameters(this.external_parameters);	
				
			cb = ch_contentT.placeBarcode(cb,false);
			
		I_chart_content ch_contentL = new chart_elementFactory().chartFactory(chart_type,motore,isError);
			ch_contentL.setExternal(EXTERNAL);
			ch_contentL.setOrientation(I_chart_content.or_LEFT);
			ch_contentL.setDati(dXY);
			ch_contentL.setX(0);
			ch_contentL.setY(0);
			ch_contentL.setWidth(_w_d);
			ch_contentL.setHeight(_h_d);
			
			ch_contentL.setLabel(label_Y);
			ch_contentL.setLabel_font(font_label_Y);
			ch_contentL.setLabel_fontsize(font_label_size_Y); 
			ch_contentL.setLabel_color(font_color_label_Y);
			ch_contentL.setLabel_gr(gr_label_Y);
			ch_contentL.setLabel_align(align_label_Y);
	
			ch_contentL.setScale(dXY.getScale(1,max_scale_Y));
			ch_contentL.setScale_font(font_scale_Y);
			ch_contentL.setScale_fontsize(font_scale_size_Y);
			ch_contentL.setScale_color(font_color_scale_Y);
			ch_contentL.setScale_gr(gr_scale_Y);
			ch_contentL.setScale_max(max_scale_Y);
			ch_contentL.setShow_scale(show_scale_Y);
			
			ch_contentL.setExternal_parameters(this.external_parameters);
			
			cb = ch_contentL.placeBarcode(cb,false);
	
		I_chart_content ch_contentR = new chart_elementFactory().chartFactory(chart_type,motore,isError);
			ch_contentR.setExternal(EXTERNAL);
			ch_contentR.setOrientation(I_chart_content.or_RIGHT);
			ch_contentR.setDati(dXY);
			ch_contentR.setX(0);
			ch_contentR.setY(0);
			ch_contentR.setWidth(_w_d);
			ch_contentR.setHeight(_h_d);
			
			ch_contentR.setLabel(label_Y);
			ch_contentR.setLabel_font(font_label_Y);
			ch_contentR.setLabel_fontsize(font_label_size_Y); 
			ch_contentR.setLabel_color(font_color_label_Y);
			ch_contentR.setLabel_gr(gr_label_Y);
			ch_contentR.setLabel_align(align_label_Y);
	
			ch_contentR.setScale(dXY.getScale(1,max_scale_Y));
			ch_contentR.setScale_font(font_scale_Y);
			ch_contentR.setScale_fontsize(font_scale_size_Y);
			ch_contentR.setScale_color(font_color_scale_Y);
			ch_contentR.setScale_gr(gr_scale_Y);
			ch_contentR.setScale_max(max_scale_Y);
			ch_contentR.setShow_scale(show_scale_Y);
			
			ch_contentR.setExternal_parameters(this.external_parameters);
			
			cb = ch_contentR.placeBarcode(cb,false);
				
			
		I_chart_content ch_contentB = new chart_elementFactory().chartFactory(chart_type,motore,isError);
			ch_contentB.setExternal(EXTERNAL);
			ch_contentB.setOrientation(I_chart_content.or_BOTTOM);
			ch_contentB.setDati(dXY);
			ch_contentB.setX(0);
			ch_contentB.setY(0);
			ch_contentB.setWidth(_w_d);
			ch_contentB.setHeight(_h_d);
			
			ch_contentB.setLabel(label_X);
			ch_contentB.setLabel_font(font_label_X);
			ch_contentB.setLabel_fontsize(font_label_size_X);
			ch_contentB.setLabel_color(font_color_label_X);
			ch_contentB.setLabel_gr(gr_label_X);
			ch_contentB.setLabel_align(align_label_X);
			
			ch_contentB.setScale(dXY.getScale(0,max_scale_X));
			ch_contentB.setScale_font(font_scale_X);
			ch_contentB.setScale_fontsize(font_scale_size_X);
			ch_contentB.setScale_color(font_color_scale_X);
			ch_contentB.setScale_gr(gr_scale_X);
			ch_contentB.setScale_max(max_scale_X);
			ch_contentB.setShow_scale(show_scale_X);
			
			ch_contentB.setExternal_parameters(this.external_parameters);
			
			cb = ch_contentB.placeBarcode(cb, false);
	
				
	//Draw
		I_chart_content ch_contentC = new chart_elementFactory().chartFactory(chart_type,motore,isError);
			ch_contentC.setExternal(EXTERNAL);
			ch_contentC.positionBody(cb,ch_contentT, ch_contentB, ch_contentL, ch_contentR,_h_d,_w_d);
			ch_contentC.setOrientation(I_chart_content.or_CENTER);
			ch_contentC.setDati(dXY);
			ch_contentC.setElement_color_1(element_color_2d_bar);
			ch_contentC.setElement_color_2(element_color_2d_right);
			ch_contentC.setElement_color_3(element_color_2d_top);
			
			ch_contentC.setExternal_parameters(this.external_parameters);
			
			cb = ch_contentC.placeBarcode(cb,true);
			ch_contentC.ActionAfter(cb,ch_contentT, ch_contentB, ch_contentL, ch_contentR,_h_d,_w_d);		
		return new Rectangle(_w_d,_h_d);
	}catch(Exception e){
		setError(e,iStub.log_FATAL);
	}	
	return null;
}
public void reimposta() {
	setName("CHART");
	CHART_TYPE = "";
	CHART_DATA_X = "";
	CHART_DATA_Y = "";
	CHART_DATA_Z = "";
	FORMAT_SCALE_XYZ = "";
	SHOW_SCALE_XYZ = "";
	ALIGN_LABEL_XYZ = "";
	FONT_SCALE_XYZ = "";
	FONT_SCALE_SIZE_XYZ = "";
	FONT_SCALE_COLOR_XYZ = "";
	LABEL_X = "";
	LABEL_Y = "";
	LABEL_Z = "";
	MAXELEMENT_LABEL_XYZ = "";
	FONT_LABEL_XYZ = "";
	FONT_LABEL_SIZE_XYZ = "";
	FONT_LABEL_COLOR_XYZ = "";
	ELEMENT_COLOR_3D = "";
	LABEL_TOP = "";
	ALIGN_LABEL_TOP = "";
	FONT_LABEL_TOP = "";
	FONT_LABEL_SIZE_TOP = "";
	FONT_LABEL_COLOR_TOP = "";
	GR_SCALE_XYZ = "";
	GR_LABEL_XYZ = "";
	FORMAT_CHART_INPUT_DATA = "";
	EXTERNAL = "";	
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void setALIGN_LABEL_TOP(java.lang.String newALIGN_LABEL_TOP) {
	ALIGN_LABEL_TOP = newALIGN_LABEL_TOP;
}
public void setALIGN_LABEL_XYZ(java.lang.String newALIGN_LABEL_XYZ) {
	ALIGN_LABEL_XYZ = newALIGN_LABEL_XYZ;
}
public void setCHART_DATA_X(java.lang.String newCHART_DATA_X) {
	CHART_DATA_X = newCHART_DATA_X;
}
public void setCHART_DATA_Y(java.lang.String newCHART_DATA_Y) {
	CHART_DATA_Y = newCHART_DATA_Y;
}
public void setCHART_DATA_Z(java.lang.String newCHART_DATA_Z) {
	CHART_DATA_Z = newCHART_DATA_Z;
}
public void setCHART_TYPE(java.lang.String newCHART_TYPE) {
	CHART_TYPE = newCHART_TYPE;
}
public void setELEMENT_COLOR_3D(java.lang.String newELEMENT_COLOR_3D) {
	ELEMENT_COLOR_3D = newELEMENT_COLOR_3D;
}
public void setEXTERNAL(java.lang.String newEXTERNAL) {
	EXTERNAL = newEXTERNAL;
}
public void setFONT_LABEL_COLOR_TOP(java.lang.String newFONT_LABEL_COLOR_TOP) {
	FONT_LABEL_COLOR_TOP = newFONT_LABEL_COLOR_TOP;
}
public void setFONT_LABEL_COLOR_XYZ(java.lang.String newFONT_LABEL_COLOR_XYZ) {
	FONT_LABEL_COLOR_XYZ = newFONT_LABEL_COLOR_XYZ;
}
public void setFONT_LABEL_SIZE_TOP(java.lang.String newFONT_LABEL_SIZE_TOP) {
	FONT_LABEL_SIZE_TOP = newFONT_LABEL_SIZE_TOP;
}
public void setFONT_LABEL_SIZE_XYZ(java.lang.String newFONT_LABEL_SIZE_XYZ) {
	FONT_LABEL_SIZE_XYZ = newFONT_LABEL_SIZE_XYZ;
}
public void setFONT_LABEL_TOP(java.lang.String newFONT_LABEL_TOP) {
	FONT_LABEL_TOP = newFONT_LABEL_TOP;
}
public void setFONT_LABEL_XYZ(java.lang.String newFONT_LABEL_XYZ) {
	FONT_LABEL_XYZ = newFONT_LABEL_XYZ;
}
public void setFONT_SCALE_COLOR_XYZ(java.lang.String newFONT_SCALE_COLOR_XYZ) {
	FONT_SCALE_COLOR_XYZ = newFONT_SCALE_COLOR_XYZ;
}
public void setFONT_SCALE_SIZE_XYZ(java.lang.String newFONT_SCALE_SIZE_XYZ) {
	FONT_SCALE_SIZE_XYZ = newFONT_SCALE_SIZE_XYZ;
}
public void setFONT_SCALE_XYZ(java.lang.String newFONT_SCALE_XYZ) {
	FONT_SCALE_XYZ = newFONT_SCALE_XYZ;
}
public void setFORMAT_CHART_INPUT_DATA(java.lang.String newFORMAT_CHART_INPUT_DATA) {
	FORMAT_CHART_INPUT_DATA = newFORMAT_CHART_INPUT_DATA;
}
public void setFORMAT_SCALE_XYZ(java.lang.String newFORMAT_SCALE_XYZ) {
	FORMAT_SCALE_XYZ = newFORMAT_SCALE_XYZ;
}
public void setGR_LABEL_XYZ(java.lang.String newGR_LABEL_XYZ) {
	GR_LABEL_XYZ = newGR_LABEL_XYZ;
}
public void setGR_SCALE_XYZ(java.lang.String newGR_SCALE_XYZ) {
	GR_SCALE_XYZ = newGR_SCALE_XYZ;
}
public void setLABEL_TOP(java.lang.String newLABEL_TOP) {
	LABEL_TOP = newLABEL_TOP;
}
public void setLABEL_X(java.lang.String newLABEL_X) {
	LABEL_X = newLABEL_X;
}
public void setLABEL_Y(java.lang.String newLABEL_Y) {
	LABEL_Y = newLABEL_Y;
}
public void setLABEL_Z(java.lang.String newLABEL_Z) {
	LABEL_Z = newLABEL_Z;
}
public void setMAXELEMENT_LABEL_XYZ(java.lang.String newMAXELEMENT_LABEL_XYZ) {
	MAXELEMENT_LABEL_XYZ = newMAXELEMENT_LABEL_XYZ;
}
public void setSHOW_SCALE_XYZ(java.lang.String newSHOW_SCALE_XYZ) {
	SHOW_SCALE_XYZ = newSHOW_SCALE_XYZ;
}
}

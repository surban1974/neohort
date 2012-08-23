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

package neohort.universal.output.lib.chart_pdf;


import java.util.HashMap;
import java.util.Vector;

import neohort.universal.output.util.I_OutputRunTime;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;

public abstract class A_chart_content implements I_chart_content, java.io.Serializable {
 	private static final long serialVersionUID = 1L;
	public float x;
    public float y;
    public float width;
    public float height;
    public BaseColor background = null;
    public int orientation;

    public String label;
    public float label_gr;
    public com.itextpdf.text.pdf.BaseFont label_font;
    public int label_fontsize;
    public BaseColor label_color = new BaseColor(BaseColor.BLACK.getRGB());

    public Vector scale = new Vector();
    public float scale_gr;
    public com.itextpdf.text.pdf.BaseFont scale_font;
    public int scale_fontsize;
    public BaseColor scale_color;
    public String scale_format;
    public int scale_max;


	public Vector valueXY = new Vector();

    public int or_CENTER = 0;
    public int or_BOTTOM = 1;
    public int or_TOP = 2;
    public int or_LEFT = 3;
    public int or_RIGHT = 4;

    public float space_0 = 2;

    public float x_Label = 0;
    public float y_Label = 0;
    public float width_Label = 0;
    public float height_Label = 0;
	public String label_align = "CENTER";
    public float x_Scale = 0;
    public float y_Scale = 0;
    public float width_Scale = 0;
    public float height_Scale = 0;
    public float delta_Scale = 0;
    public boolean show_scale = true;

    public float x_Line = 0;
    public float y_Line = 0;

	public I_chart_dati dati;

	public BaseColor element_color_1 = new BaseColor(BaseColor.BLUE.getRGB());
	public BaseColor element_color_2 = new BaseColor(BaseColor.LIGHT_GRAY.getRGB());
	public BaseColor element_color_3 = new BaseColor(BaseColor.BLACK.getRGB());

	public Vector colors = new Vector();
	public Vector colorsShadow = new Vector();

	public Vector scaleX = new Vector();
	public int scaleX_max = 0;
	private String External;

	private HashMap external_parameters;
	private I_OutputRunTime motore;
	private boolean isError=false;
	public String name = "CHART_ELEMENT_BASE";

public A_chart_content() {
	super();
	reimposta();
}


public void setMotore(I_OutputRunTime motore) {
	this.motore = motore;
}


public void setIsError(boolean isError) {
	this.isError = isError;
	if(isError) isError=isError;

}


public void setError(Exception e, String level) {
	try {
		isError = true;
		motore.setError(e,name,level);
	} catch (Exception ex) {
	}
}

public static Vector _createColors(int size) {
	Vector result = new Vector();
	int cycle = 1+(int)(size/7);
	int coef = 125/cycle;

		result.add(new BaseColor(
					154,
					154,
					255
					));
		result.add(	new BaseColor(
					154,
					51,
					101
					));
		result.add(	new BaseColor(
					255,
					255,
					204
					));
		result.add(	new BaseColor(
					204,
					255,
					255
					));
		result.add(	new BaseColor(
					101,
					0,
					101
					));
		result.add(	new BaseColor(
					255,
					128,
					128
					));
		result.add(	new BaseColor(
					0,
					101,
					204
					));


	for(int i=0;i< cycle-1;i++){
//blue
		result.add(	new BaseColor(
					i*coef,
					i*coef,
					230
					));
//red
		result.add(	new BaseColor(
					230,
					i*coef,
					i*coef
					));
//yellow
		result.add(	new BaseColor(
					230,
					230,
					i*coef
					));
//orange
		result.add(	new BaseColor(
					230,
					125,
					i*coef
					));
//green
		result.add(	new BaseColor(
					i*coef,
					230,
					i*coef
					));
//lightblue
		result.add(	new BaseColor(
					i*coef,
					230,
					230
					));
//violet
		result.add(	new BaseColor(
					230,
					i*coef,
					230
					));

	}
	Vector result_buf = new Vector();
	for(int i=0;i<size;i++)
		result_buf.add(result.get(i));
	return result_buf;
}
public static Vector _createColorsShadow(int size) {
	Vector result = _createColors(size);
	for(int i=0;i<result.size();i++){
		BaseColor curr = (BaseColor)result.get(i);
		result.set(i,new BaseColor(curr.getRed()/2,curr.getGreen()/2,curr.getBlue()/2));
	}

	Vector result_buf = new Vector();
	for(int i=0;i<size;i++)
		result_buf.add(result.get(i));
	return result_buf;
}
public BaseColor _prepareColor(int k, int size) {
	if(k>colors.size()-1){
		int coefColor = 254/(size);
		return new BaseColor(255-coefColor*k,coefColor*k,coefColor);
	}
	else return (BaseColor)colors.elementAt(k);
}
public void _prepareColori(int size) {
		try{
			if(colors.size()==0 && this.getClass().getName().indexOf("COLUMN_ADVANCED_XY")>-1){
				colors.add(new BaseColor(0,0,0));
			}
		}catch(Exception e){}
		int count_col = colors.size();
		Vector col_gen = _createColors(size-count_col);
		int i = 0;
		while(i<size-count_col){
			colors.add(col_gen.get(i));
			i++;
		}

}
public void _prepareColoriShadow(int size) {
		try{
			if(colorsShadow.size()==0 && this.getClass().getName().indexOf("COLUMN_ADVANCED_XY")>-1){
				colorsShadow.add(new BaseColor(0,0,0));
			}
		}catch(Exception e){}
		int count_col = colorsShadow.size();
		Vector col_gen = _createColorsShadow(size-count_col);
		int i = 0;
		while(i<size-count_col){
			colorsShadow.add(col_gen.get(i));
			i++;
		}

}
public BaseColor _prepareColorShadow(int k, int size) {
	if(k>colorsShadow.size()-1){
		int coefColor = 254/(size);
		return new BaseColor(255-coefColor*k,coefColor*k,coefColor);
	}
	else return (BaseColor)colorsShadow.elementAt(k);
}
public void _prepareline(PdfContentByte cb, float x0, float y0, float x1, float y1, float diap, BaseColor color){
}
public void _prepareline_dsh(PdfContentByte cb, float x0, float y0, float x1, float y1, float diap, BaseColor color) {
	double l = Math.sqrt((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0));
	if(l<diap) return;
	double alfa = Math.abs(Math.atan((y1-y0)/(x1-x0)));
       	if(x1-x0>=0 && y1-y0>=0) alfa=alfa+0;
       	if(x1-x0<0 && y1-y0>=0) alfa=(float)(Math.PI-alfa);
       	if(x1-x0<0 && y1-y0<0) alfa=(float)(Math.PI+alfa);
       	if(x1-x0>=0 && y1-y0<0) alfa=(float)(2*Math.PI-alfa);

	float xb = 0;
	float yb = 0;
	float xb0 = x0;
	float yb0 = y0;
	cb.setColorFill(color);
	boolean draw=true;
	while(l>0){
		xb = (float)Math.cos(alfa)*diap + xb0;
		yb = (float)Math.sin(alfa)*diap + yb0;
		if(draw){
			cb.moveTo(xb0,yb0);
			cb.lineTo(xb,yb);
			cb.fill();
		}
		draw=!draw;
		xb0=xb;
		yb0=yb;
		l-=diap;
	}
}
public void _prepareStyle(PdfContentByte cb, float x0, float y0, int k, BaseColor color, float h){
}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){
}
public I_chart_dati ChartDatiFactory(){
	return null;
}
public BaseColor getBackground() {
	return background;
}
public I_chart_dati getDati() {
	return dati;
}
public BaseColor getElement_color_1() {
	return element_color_1;
}
public BaseColor getElement_color_2() {
	return element_color_2;
}
public BaseColor getElement_color_3() {
	return element_color_3;
}
public java.lang.String getExternal() {
	return External;
}
public float getHeight() {
	return height;
}
public java.lang.String getLabel() {
	return label;
}
public java.lang.String getLabel_align() {
	return label_align;
}
public BaseColor getLabel_color() {
	return label_color;
}
public com.itextpdf.text.pdf.BaseFont getLabel_font() {
	return label_font;
}
public int getLabel_fontsize() {
	return label_fontsize;
}
public float getLabel_gr() {
	return label_gr;
}
public int getOrientation() {
	return orientation;
}
public java.util.Vector getScale() {
	return scale;
}
public BaseColor getScale_color() {
	return scale_color;
}
public com.itextpdf.text.pdf.BaseFont getScale_font() {
	return scale_font;
}
public int getScale_fontsize() {
	return scale_fontsize;
}
public java.lang.String getScale_format() {
	return scale_format;
}
public float getScale_gr() {
	return scale_gr;
}
public int getScale_max() {
	return scale_max;
}
public java.util.Vector getValueXY() {
	return valueXY;
}
public float getWidth() {
	return width;
}
public float getX() {
	return x;
}
public float getY() {
	return y;
}
public boolean isShow_scale() {
	return show_scale;
}
public abstract PdfContentByte placeBarcode(PdfContentByte cb, boolean paint);
public abstract void positionBody(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d);
public abstract void reimposta();
public void setBackground(BaseColor newBackground) {
	background = newBackground;
}
public void setDati(I_chart_dati newDati) {
	dati = newDati;
}
public void setElement_color_1(BaseColor newElement_color_1) {
	element_color_1 = newElement_color_1;
}
public void setElement_color_2(BaseColor newElement_color_2) {
	element_color_2 = newElement_color_2;
}
public void setElement_color_3(BaseColor newElement_color_3) {
	element_color_3 = newElement_color_3;
}
public void setExternal(java.lang.String newExternal) {
	External = newExternal;
}
public void setHeight(float newHeight) {
	height = newHeight;
}
public void setLabel(java.lang.String newLabel) {
	label = newLabel;
}
public void setLabel_align(java.lang.String newLabel_align) {
	label_align = newLabel_align;
}
public void setLabel_color(BaseColor newLabel_color) {
	label_color = newLabel_color;
}
public void setLabel_font(com.itextpdf.text.pdf.BaseFont newLabel_font) {
	label_font = newLabel_font;
}
public void setLabel_fontsize(int newLabel_fontsize) {
	label_fontsize = newLabel_fontsize;
}
public void setLabel_gr(float newLabel_gr) {
	label_gr = newLabel_gr;
}
public void setOrientation(int newOrientation) {
	orientation = newOrientation;
}
public void setScale(java.util.Vector newScale) {
	scale = newScale;
}
public void setScale_color(BaseColor newScale_color) {
	scale_color = newScale_color;
}
public void setScale_font(com.itextpdf.text.pdf.BaseFont newScale_font) {
	scale_font = newScale_font;
}
public void setScale_fontsize(int newScale_fontsize) {
	scale_fontsize = newScale_fontsize;
}
public void setScale_format(java.lang.String newScale_format) {
	scale_format = newScale_format;
}
public void setScale_gr(float newScale_gr) {
	scale_gr = newScale_gr;
}
public void setScale_max(int newScale_max) {
	scale_max = newScale_max;
}
public void setShow_scale(boolean newShow_scale) {
	show_scale = newShow_scale;
}
public void setValueXY(java.util.Vector newValueXY) {
	valueXY = newValueXY;
}
public void setWidth(float newWidth) {
	width = newWidth;
}
public void setX(float newX) {
	x = newX;
}
public void setY(float newY) {
	y = newY;
}
public HashMap getExternal_parameters() {
	return external_parameters;
}

public void setExternal_parameters(HashMap map) {
	external_parameters = map;
}

}

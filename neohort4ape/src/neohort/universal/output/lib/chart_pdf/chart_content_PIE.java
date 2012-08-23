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

import java.util.ArrayList;
import java.util.Vector;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

public class chart_content_PIE  extends A_chart_content implements I_chart_content, java.io.Serializable {
	private static final long serialVersionUID = -4829762592811608504L;
	private float x;
    private float y;
    private float width;
    private float height;
    private BaseColor background = null;
    private int orientation;

    private String label;
    private float label_gr;
    private com.itextpdf.text.pdf.BaseFont label_font;
    private int label_fontsize;
    private BaseColor label_color = new BaseColor(BaseColor.BLACK.getRGB());

    private Vector scale = new Vector();
    private float scale_gr;
    private com.itextpdf.text.pdf.BaseFont scale_font;
    private int scale_fontsize;
    private BaseColor scale_color;
    private java.lang.String scale_format;
    private int scale_max;

	private java.util.Vector valueXY = new Vector();

    static public int or_CENTER = 0;
    static public int or_BOTTOM = 1;
    static public int or_TOP = 2;
    static public int or_LEFT = 3;
    static public int or_RIGHT = 4;

    static private float space_0 = 2;

    private float x_Label = 0;
    private float y_Label = 0;
    private float width_Label = 0;
    private float height_Label = 0;
	private java.lang.String label_align = "CENTER";
    private float x_Scale = 0;
    private float width_Scale = 0;
    private float height_Scale = 0;
    private float delta_Scale = 0;
	private float prof = 7;
    private boolean show_scale = true;

    private float x_Line = 0;
 	private I_chart_dati dati;

	private BaseColor element_color_1 = new BaseColor(BaseColor.BLUE.getRGB());
	private BaseColor element_color_2 = new BaseColor(BaseColor.LIGHT_GRAY.getRGB());
	private BaseColor element_color_3 = new BaseColor(BaseColor.BLACK.getRGB());
	private java.lang.String External;
public chart_content_PIE() {
	super();
	reimposta();
}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){
}
public I_chart_dati ChartDatiFactory() {
	return new chart_datiPIE();
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
public PdfContentByte placeBarcode(PdfContentByte cb, boolean paint) {
float coefStr = 1/(float)(Math.sin(Math.PI/6));
float coef_h_pie = 16;
Vector dZ = dati.getDati(2,0);
if(dZ!=null && dZ.size()>1){
	try{
		float ang = new java.math.BigDecimal((String)dZ.elementAt(1)).floatValue();
		if(ang!=0) coefStr=1/(float)(Math.sin(Math.PI*ang/180));
	}catch(Exception e){}
}
if(dZ!=null && dZ.size()>2){
	try{
		float h_p = new java.math.BigDecimal((String)dZ.elementAt(2)).floatValue();
		if(h_p!=0) coef_h_pie = h_p;
	}catch(Exception e){}
}

    try {
        Vector scale_buf = new Vector();
        if (orientation == or_TOP) {
            if (paint) {
                if (background != null) {
                    cb.setColorFill(background);
                    cb.rectangle(x, y, height, width);
                    cb.fill();
                }
            }
            //Label-Top
            if (label != null && label.trim().length() > 0) {
                width_Label = label_font.getWidthPoint(label, label_fontsize);
                if (width_Label > width - x)
                    width_Label = width - x;
                height_Label =
                    label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
                x_Label = x + (width - x - width_Label) / 2;
                if (label_align.equalsIgnoreCase("CENTER"))
                    x_Label = x + (width - x - width_Label) / 2;
                if (label_align.equalsIgnoreCase("RIGHT"))
                    x_Label = (width + x - width_Label);
                if (label_align.equalsIgnoreCase("LEFT"))
                    x_Label = x;
                y_Label = y + (height - height_Label) / 2;

                if (paint) {
                    cb.setColorFill(label_color);
                    cb.beginText();
                    cb.setFontAndSize(label_font, label_fontsize);
                    cb.showTextAligned(PdfContentByte.ALIGN_LEFT, label, x_Label, y_Label, label_gr);
                    cb.endText();
                    cb.fill();
                }
            }
            if (!paint)
                height = height_Label + height_Label;
        }

        if (orientation == or_BOTTOM) {
			if(paint){
				if(background!=null){
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width);
					cb.fill();
				}
			}
//Label-Left
            if (label != null && label.trim().length() > 0) {
                y_Label = y;
                width_Label = label_font.getWidthPoint(label, label_fontsize);
                if(label_gr == 90){
	                if (width_Label > height-y)
 	                   width_Label = height-y;
                }
                height_Label =
                    label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
                x_Label = height_Label;
                y_Label = y+(height - width_Label-y) / 2;
                if(label_align.equalsIgnoreCase("CENTER")) y_Label = y+(height - width_Label) / 2;
                if(label_align.equalsIgnoreCase("RIGHT")) y_Label = (height +y - width_Label);
	            if(label_align.equalsIgnoreCase("LEFT")) y_Label = y;
				if(paint){
					cb.setColorFill(label_color);
					cb.beginText();
					cb.setFontAndSize(label_font, label_fontsize);
					cb.showTextAligned(PdfContentByte.ALIGN_LEFT, label, x_Label, y_Label, label_gr);
					cb.endText();
					cb.fill();
				}
           	}

//Scale-Left
            if (scale.size() > 0 && isShow_scale()) {
            	y_Scale = y;
            	if(label_gr==0) x_Scale = x+width_Label+space_0;
            	else x_Scale = x+height_Label+space_0;

            	x_Scale = x_Scale+8;

                for (int i = 0; i < scale.size(); i++) {
                   	String value = ((chart_datiPIE_container)scale.elementAt(i)).getLabel();
                    float sc_width_buf = scale_font.getWidthPoint(value+" (100.00%)", scale_fontsize);
                    if (width_Scale < sc_width_buf)
                      	width_Scale = sc_width_buf;
                    scale_buf.addElement(value+" ("+((chart_datiPIE_container)scale.elementAt(i)).getLabelPerc()+"%)");
                }
                height_Scale =
                    scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize);
                delta_Scale = (height-prof)/(scale.size()-1);
				if(paint){
  					double coefAlfa = 1;
 					if(scale_gr!=0)	coefAlfa = Math.abs(Math.cos(scale_gr*Math.PI/180));
 					float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();


                    for(int i=0;i<scale_buf.size();i++){

                        cb.setColorFill(((chart_datiPIE_container)scale.elementAt(i)).getColorTop());
                        	cb.moveTo(x_Scale-7, y+i*delta_Scale+space_0);
                        	cb.lineTo(x_Scale-2, y+i*delta_Scale+space_0);
                        	cb.lineTo(x_Scale-7, y+i*delta_Scale+space_0+height_Scale);
                        	cb.moveTo(x_Scale-2, y+i*delta_Scale+space_0+height_Scale);
                        	cb.lineTo(x_Scale-2, y+i*delta_Scale+space_0);
                        	cb.lineTo(x_Scale-7, y+i*delta_Scale+space_0+height_Scale);

                        cb.fill();

						cb.setColorFill(scale_color);
						cb.beginText();
						cb.setFontAndSize(scale_font, scale_fontsize);
						if(label_gr==90){
							if(scale_gr==90) cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+4*space_0+height_Scale, y+i*delta_Scale+space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+width_Scale*coefAlfaD, y+i*delta_Scale+space_0, scale_gr);
						}else{
							if(scale_gr==90) cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+4*space_0+height_Scale, y+i*delta_Scale+space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+width_Scale*coefAlfaD, y+i*delta_Scale+space_0, scale_gr);
						}
						cb.endText();
	                }
					cb.fill();
				}
            }

//Line-Left
  			double coefAlfa = 1;
 			if(scale_gr!=0)	coefAlfa = Math.abs(Math.cos(scale_gr*Math.PI/180));
 			float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();

			if(label_gr==90){
				if(scale_gr==90) x_Line = x+height_Label+8*space_0+height_Scale;
				else x_Line = x+height_Label+5*space_0+width_Scale*coefAlfaD;
			}else{
				if(scale_gr==90) x_Line = x+width_Label+8*space_0+height_Scale;
				else x_Line = x+width_Label+5*space_0+width_Scale*coefAlfaD;
			}

			y_Line = y;
			if(!paint){
				width = x_Line+space_0;
				height = height+y-delta_Scale;
			}
        }

        if (orientation == or_CENTER) {
            if (paint) {
                if (background != null) {
                    cb.setColorFill(background);
                    cb.rectangle(x, y, width, height);
                    cb.fill();
                }
            }

//Scale-Center
            if (dati != null) {
                float Radice = 0;

                if (width/2> height)
                    Radice = (height*2 - 2*prof) / 2;
                else
                    Radice = (width - 2*prof) / 2;

                float heightPie = Radice / coef_h_pie;

                Vector datiX = dati.getDati(0, Radice * 2);
                if (paint && datiX.size() > 0) {

                    float centerX = (width - prof) / 2;
                    float centerY = (height - prof) / 2;



                    for (int i = 0; i < datiX.size(); i++) {

						chart_datiPIE_container cdc = (chart_datiPIE_container)datiX.elementAt(i);
                        float x1 = cdc.getX0()+x+centerX+prof;
                        float y1 = cdc.getY0()/coefStr+y+centerY+prof;
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float x2 = cdc.getX1()+x+centerX+prof;
                        float y2 = cdc.getY1()/coefStr+y+centerY+prof;


                        cb.setColorFill(cdc.getColorSh());
	                        cb.moveTo(xd, yd - heightPie);
	                        cb.lineTo(x1, y1 - heightPie);
	                        cb.lineTo(xd, yd + heightPie);

	                        cb.moveTo(x1, y1 + heightPie);
	                        cb.lineTo(x1, y1 - heightPie);
	                        cb.lineTo(xd, yd + heightPie);
                        cb.fill();

                        cb.setColorFill(cdc.getColorSh());
	                        cb.moveTo(xd, yd - heightPie);
	                        cb.lineTo(x2, y2 - heightPie);
	                        cb.lineTo(xd, yd + heightPie);

	                        cb.moveTo(x2, y2 + heightPie);
	                        cb.lineTo(x2, y2 - heightPie);
	                        cb.lineTo(xd, yd + heightPie);
                        cb.fill();


						ArrayList ar = cdc.getPoints();
                        cb.setColorFill(cdc.getColorSh());

						cb.moveTo(x2, y2 - heightPie);
							for(int p=ar.size()-1;p>-1;p--){
	        					float pt[] = (float [])ar.get(p);
	        					cb.curveTo(	pt[4]+x+centerX+prof,
		        					pt[5]/coefStr+y+centerY+prof - heightPie,
		        					pt[2]+x+centerX+prof,
		        					pt[3]/coefStr+y+centerY+prof - heightPie,
		        					pt[0]+x+centerX+prof,
		        					pt[1]/coefStr+y+centerY+prof - heightPie);
							}
	                    cb.lineTo(x1, y1 + heightPie);
							for(int p=0;p<ar.size();p++){
	        					float pt[] = (float [])ar.get(p);
	        					cb.curveTo(	pt[2]+x+centerX+prof,
		        						pt[3]/coefStr+y+centerY+prof + heightPie,
		        						pt[4]+x+centerX+prof,
		        						pt[5]/coefStr+y+centerY+prof + heightPie,
		        						pt[6]+x+centerX+prof,
		        						pt[7]/coefStr+y+centerY+prof + heightPie);

							}
						cb.lineTo(x2, y2 - heightPie);


                        cb.fill();



                    }


                    for (int i = 0; i < datiX.size(); i++) {
						chart_datiPIE_container cdc = (chart_datiPIE_container)datiX.elementAt(i);
                        float x1 = cdc.getX0()+x+centerX+prof;
                        float y1 = cdc.getY0()/coefStr+y+centerY+prof;
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float x2 = cdc.getX1()+x+centerX+prof;
                        float y2 = cdc.getY1()/coefStr+y+centerY+prof;

						ArrayList ar = cdc.getPoints();

						cb.setColorFill(cdc.getColorTop());
                        cb.moveTo(xd, yd + heightPie);
                        cb.lineTo(x1, y1 + heightPie);


						for(int p=0;p<ar.size();p++){

	        				float pt[] = (float [])ar.get(p);
	        				cb.curveTo(	pt[2]+x+centerX+prof,
		        						pt[3]/coefStr+y+centerY+prof + heightPie,
		        						pt[4]+x+centerX+prof,
		        						pt[5]/coefStr+y+centerY+prof + heightPie,
		        						pt[6]+x+centerX+prof,
		        						pt[7]/coefStr+y+centerY+prof + heightPie);

						}

                       	cb.lineTo(x2, y2 + heightPie);
                        cb.fill();


                    }

                }
            }
        }

    } catch (Exception e) {
        e.toString();
    }
    return cb;
}
public void positionBody(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){

	float h_t = ch_contentT.getHeight();
	float w_l = ch_contentB.getWidth();
	float h_b = 0;

	if(h_t==0) h_t = space_0;

	ch_contentT.setX(w_l);
	ch_contentT.setY(_h_d-h_t-h_b);
	ch_contentT.setWidth(_w_d-w_l);
	cb = ch_contentT.placeBarcode(cb,true);

	ch_contentB.setX(0);
	ch_contentB.setY(0);
	ch_contentB.setHeight(_h_d-h_t-h_b);
	cb = ch_contentB.placeBarcode(cb,true);


	this.setX(w_l);
	this.setY(h_b);
	this.setWidth(_w_d-3*w_l/2);
	this.setHeight(_h_d-h_t-h_b);

}
public void reimposta() {
	try{
		label_font = BaseFont.createFont("Helvetica", "winansi", false);
		scale_font = BaseFont.createFont("Helvetica", "winansi", false);
	}catch(Exception e){}
}
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
}

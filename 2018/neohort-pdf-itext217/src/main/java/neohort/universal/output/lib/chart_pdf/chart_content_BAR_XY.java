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

import java.util.Vector;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class chart_content_BAR_XY extends A_chart_content implements I_chart_content, java.io.Serializable {
	private static final long serialVersionUID = 4106123103771486531L;
	private float x;
    private float y;
    private float width;
    private float height;
    private java.awt.Color background = null;
    private int orientation;

    private String label;
    private float label_gr;
    private com.lowagie.text.pdf.BaseFont label_font;
    private int label_fontsize;
    private java.awt.Color label_color = java.awt.Color.black;

    private Vector scale = new Vector();
    private float scale_gr;
    private com.lowagie.text.pdf.BaseFont scale_font;
    private int scale_fontsize;
    private java.awt.Color scale_color;
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
    private float y_Scale = 0;
    private float width_Scale = 0;
    private float height_Scale = 0;
    private float delta_Scale = 0;
    private boolean show_scale = true;

    private float x_Line = 0;
    private float y_Line = 0;

	private I_chart_dati dati;
	
	private java.awt.Color element_color_1 = java.awt.Color.blue;
	private java.awt.Color element_color_2 = java.awt.Color.lightGray;
	private java.awt.Color element_color_3 = java.awt.Color.black;	
	private String External;
public chart_content_BAR_XY() {
	super();
	reimposta();
}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){
}
public I_chart_dati ChartDatiFactory() {
	return new chart_datiXY();
}
public java.awt.Color getBackground() {
	return background;
}
public I_chart_dati getDati() {
	return dati;
}
public java.awt.Color getElement_color_1() {
	return element_color_1;
}
public java.awt.Color getElement_color_2() {
	return element_color_2;
}
public java.awt.Color getElement_color_3() {
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
public java.awt.Color getLabel_color() {
	return label_color;
}
public com.lowagie.text.pdf.BaseFont getLabel_font() {
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
public java.awt.Color getScale_color() {
	return scale_color;
}
public com.lowagie.text.pdf.BaseFont getScale_font() {
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
	float prof = 7;
    try {
Vector scale_buf = new Vector();
        if (orientation == or_TOP) {
			if(paint){	        
				if(background!=null){	        
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width); 
					cb.fill();
				}
			}
//Label-Top
            if (label != null && label.trim().length() > 0) {
                width_Label = label_font.getWidthPoint(label, label_fontsize);
                if (width_Label > width-x)
                    width_Label = width-x;
                height_Label =
                    label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
                x_Label = x + (width - x - width_Label) / 2;
                if(label_align.equalsIgnoreCase("CENTER")) x_Label = x + (width - x - width_Label) / 2;
                if(label_align.equalsIgnoreCase("RIGHT")) x_Label = (width + x - width_Label);    
	            if(label_align.equalsIgnoreCase("LEFT")) x_Label = x;    
                y_Label = y + (height - height_Label)/2;
                
				if(paint){	        
					cb.setColorFill(label_color);
					cb.beginText();
					cb.setFontAndSize(label_font, label_fontsize);
					cb.showTextAligned(PdfContentByte.ALIGN_LEFT, label, x_Label, y_Label, label_gr);
					cb.endText();
					cb.fill();
				}				
           	}
            if(!paint) height = height_Label + height_Label;
        }
        if (orientation == or_BOTTOM) {
			if(paint){	        
				if(background!=null){	        
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width); 
					cb.fill();
				}
			}
//Label-Bottom
            if (label != null && label.trim().length() > 0) {
                y_Label = y;
                width_Label = label_font.getWidthPoint(label, label_fontsize);
                if(label_gr==0){
	                if (width_Label > width-x)
 	                   width_Label = width-x;
                }       
                height_Label =
                    label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
                x_Label = x + (width - width_Label) / 2;
                if(label_align.equalsIgnoreCase("CENTER")) x_Label = x + (width - width_Label) / 2;
                if(label_align.equalsIgnoreCase("RIGHT")) x_Label = (width +x - width_Label);    
	            if(label_align.equalsIgnoreCase("LEFT")) x_Label = x;    
                
                y_Label = y+2*space_0;
				if(paint){	        
					cb.setColorFill(label_color);
					cb.beginText();
					cb.setFontAndSize(label_font, label_fontsize);
					cb.showTextAligned(PdfContentByte.ALIGN_LEFT, label, x_Label, y_Label, label_gr);
					cb.endText();
					cb.fill();
				}	

           	}
//Scale-Bottom
            if (scale.size() > 0 && isShow_scale()) {
            	if(label_gr==0) y_Scale = y+height_Label+space_0;
            	else y_Scale = y+width_Label+space_0;
                x_Scale = x;
                for (int i = 0; i < scale.size(); i++) {
                 	String value = (String) scale.elementAt(i);
                    float sc_width_buf = scale_font.getWidthPoint(value, scale_fontsize);
                    if (width_Scale < sc_width_buf)
                      	width_Scale = sc_width_buf;
                    scale_buf.addElement(value);
                }
                height_Scale =
                    scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize);
                
                delta_Scale = (width-prof)/(scale_max-1);
				if(paint){
					double coefAlfa = 1; 				
 					if(scale_gr!=0)	coefAlfa = Math.abs(Math.sin(scale_gr*Math.PI/180));
 					float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();
 
					cb.setColorFill(scale_color);                    
                    for(int i=0;i<scale_buf.size();i++){
						cb.beginText();
						cb.setFontAndSize(scale_font, scale_fontsize);
						if(label_gr==0){
							if(scale_gr==0)
								cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+4*space_0,y_Scale+height_Scale+2*space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+4*space_0 - scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize),y_Scale+width_Scale*coefAlfaD+2*space_0, scale_gr);
						}else{
							if(scale_gr==0)
								cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+4*space_0,y_Scale+height_Scale+2*space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+4*space_0 - scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize),y_Scale+width_Scale*coefAlfaD+2*space_0, scale_gr);
						}	
						cb.endText();
	                }
					cb.fill();
				}	
            }
                
//Line-Bottom
 			x_Line = x;
  			double coefAlfa = 1; 				
 			if(scale_gr!=0)	coefAlfa = Math.abs(Math.sin(scale_gr*Math.PI/180));
 			float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();
			if(label_gr==0){ 			
	 			if(scale_gr==0) y_Line = y+height_Label+8*space_0+height_Scale;
 				else y_Line = y+height_Label+5*space_0+width_Scale*coefAlfaD;
			}else{
	 			if(scale_gr==0) y_Line = y+width_Label+8*space_0+height_Scale;
 				else y_Line = y+width_Label+5*space_0+width_Scale*coefAlfaD;
 			}	
			if(paint){
				cb.setColorFill(java.awt.Color.lightGray);
					cb.moveTo(x+0.1f,y_Line+0.1f);
					cb.lineTo(x+0.1f+prof,y_Line+0.1f+prof);
					cb.lineTo(width+x-prof+0.1f,y_Line+0.1f);
					cb.moveTo(width+x-prof+0.1f+prof,y_Line+0.1f+prof);
					cb.lineTo(width+x-prof+0.1f,y_Line+0.1f);
					cb.lineTo(x+0.1f+prof,y_Line+0.1f+prof);
				cb.fill();
				
				cb.setColorFill(java.awt.Color.black);				
				cb.moveTo(x, y_Line);
				cb.lineTo(width+x-prof,y_Line);
				for(int i=1;i<scale_buf.size();i++){
					cb.moveTo(x+i*delta_Scale, y_Line-2);
					cb.lineTo(x+i*delta_Scale, y_Line);	
				}
				cb.moveTo(x,y_Line);cb.lineTo(x+prof,y_Line+prof);				
				cb.fill();
			}
//			if(!paint) height = y+height_Label+space_0+width_Scale+1;
			if(!paint){
				height = y_Line;
				width = width+x-delta_Scale;
			}	
        }
        
        if (orientation == or_LEFT) {
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
                for (int i = 0; i < scale.size(); i++) {
                   	String value = (String) scale.elementAt(i);
                    float sc_width_buf = scale_font.getWidthPoint(value, scale_fontsize);
                    if (width_Scale < sc_width_buf)
                      	width_Scale = sc_width_buf;
                    scale_buf.addElement(value);
                }
                height_Scale =
                    scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize);
                delta_Scale = (height-prof)/(scale_max-1);
				if(paint){	  
  					double coefAlfa = 1; 				
 					if(scale_gr!=0)	coefAlfa = Math.abs(Math.cos(scale_gr*Math.PI/180));
 					float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();
					 
					cb.setColorFill(scale_color);                    
                    for(int i=0;i<scale_buf.size();i++){
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
			if(paint){	        			
				cb.setColorFill(java.awt.Color.lightGray);
					cb.moveTo(x_Line+0.1f,y+0.1f);
					cb.lineTo(x_Line+0.1f+prof,y+0.1f+prof);
					cb.lineTo(x_Line+0.1f,height+y-prof+0.1f);
					cb.moveTo(x_Line+0.1f+prof,height+y+0.1f);
					cb.lineTo(x_Line+0.1f,height+y-prof+0.1f);
					cb.lineTo(x_Line+0.1f+prof,y+0.1f+prof);
				cb.fill();

				cb.setColorFill(java.awt.Color.black);				
				cb.moveTo(x_Line, y);
				cb.lineTo(x_Line, height+y-prof);
				for(int i=1;i<scale_buf.size();i++){
					cb.moveTo(x_Line-2, y+i*delta_Scale);
					cb.lineTo(x_Line, y+i*delta_Scale);
				}
				cb.moveTo(x_Line, y);cb.lineTo(x_Line+prof, y+prof);				
				cb.fill();
					
			}
//			if(!paint) width = x+height_Label+space_0+width_Scale+1;
			if(!paint){
				width = x_Line; 
				height = height+y-delta_Scale;
			}	
        }
        if (orientation == or_CENTER) {
			if(paint){	        
				if(background!=null){	        
					cb.setColorFill(background);
					cb.rectangle(x,y,width,height); 
					cb.fill();
				}
			}
		
//Scale-Center
            if (dati != null ) {
				Vector datiX = dati.getDati(0,width-prof);
				Vector datiY = dati.getDati(1,height-prof); 
				if(paint && datiX.size()>0 && datiY.size()>0){	
					float delta_minusX = Float.valueOf((String)datiX.elementAt(0)).floatValue();
					float delta_minusY = Float.valueOf((String)datiY.elementAt(0)).floatValue();
					for (int i = 1; i < datiX.size(); i++) {
						if(Float.valueOf((String)datiX.elementAt(i)).floatValue()<delta_minusX)
							delta_minusX = Float.valueOf((String)datiX.elementAt(i)).floatValue();
						if(Float.valueOf((String)datiY.elementAt(i)).floatValue()<delta_minusY)
							delta_minusY = Float.valueOf((String)datiY.elementAt(i)).floatValue();
					}	
                	for (int i = 0; i < datiY.size()-1; i++) {

	                	float x0 = x-delta_minusX;
	                	float y0 = y+Float.valueOf((String)datiY.elementAt(i)).floatValue()-delta_minusY;
	                	float x1 = x+Float.valueOf((String)datiX.elementAt(i)).floatValue()-delta_minusX;
	                	float y1 = y+Float.valueOf((String)datiY.elementAt(i+1)).floatValue()-delta_minusY;
	                	cb.setColorFill(element_color_1);
	                	cb.rectangle(x0,y0,x1-x0,y1-y0);
	                	cb.fill();
	                	
	                	cb.setColorFill(element_color_2);
	  						cb.moveTo(x0, y1);
							cb.lineTo(x1,y1);
							cb.lineTo(x0+prof,y1+prof);
						cb.fill();
						cb.setColorFill(element_color_2);
							cb.moveTo(x1+prof, y1+prof);
							cb.lineTo(x1,y1);
							cb.lineTo(x0+prof,y1+prof);
						cb.fill();
						if(x1>x0 ){
		                	cb.setColorFill(element_color_3);
 	  							cb.moveTo(x1, y0);
								cb.lineTo(x1,y1);
								cb.lineTo(x1+prof,y0+prof);
							cb.fill();
							cb.setColorFill(element_color_3);
								cb.moveTo(x1+prof, y1+prof);
								cb.lineTo(x1,y1);
								cb.lineTo(x1+prof,y0+prof);
							cb.fill();
							
		                	cb.setColorFill(java.awt.Color.black);
 	  							cb.moveTo(x1, y1);
  	 							cb.lineTo(x0,y1);
   							cb.fill();	
	   		             	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x1,y0);
   								cb.lineTo(x0,y0);
   							cb.fill();
	        	        	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x0,y1);
   								cb.lineTo(x0+prof,y1+prof);
   							cb.fill();
	     		           	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x0+prof,y1+prof);
   								cb.lineTo(x1+prof,y1+prof);
   							cb.fill();	
						}else{
		                	cb.setColorFill(element_color_3);
 	  							cb.moveTo(x0, y0);
								cb.lineTo(x0,y1);
								cb.lineTo(x0+prof,y0+prof);
							cb.fill();
							cb.setColorFill(element_color_3);
								cb.moveTo(x0+prof, y1+prof);
								cb.lineTo(x0,y1);
								cb.lineTo(x0+prof,y0+prof);
							cb.fill();
							
		                	cb.setColorFill(java.awt.Color.black);
 	  							cb.moveTo(x1, y1);
  	 							cb.lineTo(x0,y1);
   							cb.fill();	
	   		             	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x1,y0);
   								cb.lineTo(x0,y0);
   							cb.fill();
	        	        	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x1,y1);
   								cb.lineTo(x1+prof,y1+prof);
   							cb.fill();
	     		           	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x1,y1);
   								cb.lineTo(x1,y0);
   							cb.fill();
	     		           	cb.setColorFill(java.awt.Color.black);
   								cb.moveTo(x0+prof,y1+prof);
   								cb.lineTo(x1+prof,y1+prof);
   							cb.fill();	
   							
						}							
						
	                }
				}         
            }	
        } 
    } catch (Exception e) {}
    return cb;
}
public void positionBody(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){

	float h_t = ch_contentT.getHeight();
	float w_l = ch_contentL.getWidth();
	float h_l = ch_contentL.getHeight();
	float h_b = ch_contentB.getHeight();

	if(h_t==0) h_t = space_0;
	
	ch_contentL.setX(0);
	ch_contentL.setY(0+h_b);
	ch_contentL.setHeight(_h_d-h_t-h_b);
		h_l = _h_d-h_t-h_b;	
	cb = ch_contentL.placeBarcode(cb,true);

	ch_contentB.setX(w_l);
	ch_contentB.setY(0);
	ch_contentB.setWidth(_w_d-w_l);
	cb = ch_contentB.placeBarcode(cb,true);

	ch_contentT.setX(w_l);
	ch_contentT.setY(h_l+h_b);
	ch_contentT.setWidth(_w_d-w_l);
	cb = ch_contentT.placeBarcode(cb,true);

	this.setX(w_l);
	this.setY(h_b);
	this.setWidth(_w_d-w_l);
	this.setHeight(_h_d-h_t-h_b);
	
}
public void reimposta() {
	try{
		label_font = BaseFont.createFont("Helvetica", "winansi", false);
		scale_font = BaseFont.createFont("Helvetica", "winansi", false);
	}catch(Exception e){}
}
public void setBackground(java.awt.Color newBackground) {
	background = newBackground;
}
public void setDati(I_chart_dati newDati) {
	dati = newDati;
}
public void setElement_color_1(java.awt.Color newElement_color_1) {
	element_color_1 = newElement_color_1;
}
public void setElement_color_2(java.awt.Color newElement_color_2) {
	element_color_2 = newElement_color_2;
}
public void setElement_color_3(java.awt.Color newElement_color_3) {
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
public void setLabel_color(java.awt.Color newLabel_color) {
	label_color = newLabel_color;
}
public void setLabel_font(com.lowagie.text.pdf.BaseFont newLabel_font) {
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
public void setScale_color(java.awt.Color newScale_color) {
	scale_color = newScale_color;
}
public void setScale_font(com.lowagie.text.pdf.BaseFont newScale_font) {
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

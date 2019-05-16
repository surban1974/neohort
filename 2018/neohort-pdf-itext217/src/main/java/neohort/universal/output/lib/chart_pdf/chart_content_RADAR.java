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

import neohort.log.stubs.iStub;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class chart_content_RADAR  extends A_chart_content implements I_chart_content, java.io.Serializable {
 	private static final long serialVersionUID = -1L;
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

    private Vector<Object> scale = new Vector<Object>();
    private float scale_gr;
    private com.lowagie.text.pdf.BaseFont scale_font;
    private int scale_fontsize;
    private java.awt.Color scale_color;
    private java.lang.String scale_format;
    private int scale_max;

	private Vector<Object> valueXY = new Vector<Object>();
    
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
    private float y_Line = 0;

	private I_chart_dati dati;
	
	private java.awt.Color element_color_1 = java.awt.Color.blue;
	private java.awt.Color element_color_2 = java.awt.Color.lightGray;
	private java.awt.Color element_color_3 = java.awt.Color.black;		
	private java.lang.String External;
public chart_content_RADAR() {
	super();
	reimposta();
}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){

	float h_t = ch_contentT.getHeight();
	float h_b = 0;

	if(h_t==0) h_t = space_0;	
	
	ch_contentB.setHeight(_h_d-h_t-h_b);
	ch_contentB.setX(0);
	ch_contentB.setY(h_b);
	ch_contentB.setWidth(_w_d);
	
	cb = ch_contentB.placeBarcode(cb,true);
}
public I_chart_dati ChartDatiFactory() {
	return new chart_datiRADAR();
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
public Vector<Object> getScale() {
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
public Vector<Object> getValueXY() {
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
	if(deep!=-1)
		prof = deep;
    try {
    	Vector<Object> scale_buf = new Vector<Object>();
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
        }
        
        if (orientation == or_BOTTOM) {
			if(paint){	        
				if(background!=null){	        	        
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width); 
					cb.fill();
				}
			}
//Scale-Left
			float Radice = 0;
            if (scale.size() > 0 ) {
            	y_Scale = y+height/2;
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

            	double coefAlfa = 1;
 				if(scale_gr!=0)	coefAlfa = Math.abs(Math.cos(scale_gr*Math.PI/180));
 				float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();
				if(scale_gr==90) x_Line = x+height_Label+8*space_0+height_Scale;
				else x_Line = x+height_Label+5*space_0+width_Scale*coefAlfaD;
				y_Line = y+height/2;
       			
       			if(width-x_Line>height) Radice = (height-prof)/2; 
       			else Radice = (width-prof-x_Line)/2;
                    
                delta_Scale = (Radice)/(scale_max-1);
				if(paint){	  
					 
					cb.setColorFill(scale_color);                    
                    for(int i=0;i<scale_buf.size();i++){
						cb.beginText();
						cb.setFontAndSize(scale_font, scale_fontsize);
						if(label_gr==90){
							if(scale_gr==90) cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+4*space_0+height_Scale, y+height/2+i*delta_Scale+space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+width_Scale*coefAlfaD, y+height/2+i*delta_Scale+space_0, scale_gr);
						}else{			
							if(scale_gr==90) cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+4*space_0+height_Scale, y+height/2+i*delta_Scale+space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x_Scale+width_Scale*coefAlfaD, y+height/2+i*delta_Scale+space_0, scale_gr);
						}	
						cb.endText();
	                }
					cb.fill();
				}	
            }

			

			if(paint){
				cb.setColorFill(java.awt.Color.black);				
				cb.moveTo(x_Line, y_Line);
				cb.lineTo(x_Line, y_Line+Radice);
				for(int i=0;i<scale_buf.size();i++){
					cb.moveTo(x_Line-2, y_Line+i*delta_Scale);
					cb.lineTo(x_Line, y_Line+i*delta_Scale);
				}
				cb.fill();


					float centerX = (width-prof+x_Line)/2;
            		float centerY = (height-prof)/2;
				Vector<Object> datiX = dati.getDati(0,Radice*2);
            		float alfaDelta = 360/datiX.size();
				
            		float alfaStart = 90;
				if(isShow_scale()){
            		for (int i = 0; i < datiX.size(); i++){
	            		float Hx = new java.math.BigDecimal(Radice*Math.cos(Math.PI*alfaStart/180)).floatValue()+centerX+prof/2+x;
	            		float Hy = new java.math.BigDecimal(Radice*Math.sin(Math.PI*alfaStart/180)).floatValue()+centerY+prof/2+y;
	                	cb.setColorFill(java.awt.Color.black);
	                	cb.moveTo(x+centerX+prof/2,y+centerY+prof/2);
	                	cb.lineTo(Hx,Hy);
	                	cb.fill();

	                	float deltamax_scale = delta_Scale;
						for (int j=0;j<scale_max-1;j++){
		            		float Hx1 = new java.math.BigDecimal(deltamax_scale*Math.cos(Math.PI*alfaStart/180)).floatValue()+centerX+prof/2+x;
		            		float Hy1 = new java.math.BigDecimal(deltamax_scale*Math.sin(Math.PI*alfaStart/180)).floatValue()+centerY+prof/2+y;
		            		float Hx2 = new java.math.BigDecimal(deltamax_scale*Math.cos(Math.PI*(alfaStart-alfaDelta)/180)).floatValue()+centerX+prof/2+x;
		            		float Hy2 = new java.math.BigDecimal(deltamax_scale*Math.sin(Math.PI*(alfaStart-alfaDelta)/180)).floatValue()+centerY+prof/2+y;
		            		if((alfaStart-alfaDelta)<-265){
		            			Hx2 = new java.math.BigDecimal(deltamax_scale*Math.cos(Math.PI*(90)/180)).floatValue()+centerX+prof/2+x;
		            			Hy2 = new java.math.BigDecimal(deltamax_scale*Math.sin(Math.PI*(90)/180)).floatValue()+centerY+prof/2+y;
		            		}
	                		cb.setColorFill(java.awt.Color.black);
	                		cb.moveTo(Hx1,Hy1);
	                		cb.lineTo(Hx2,Hy2);
	                		cb.fill();

		            		deltamax_scale+= delta_Scale;
						}

               			alfaStart-=alfaDelta;
            		}
				}	
					
			}

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
            if (dati != null) {
        		float Radice = 0;
        		if(width>height) Radice = (height-prof)/2;
        		else Radice = (width-prof)/2;
	            
				Vector<Object> datiX = dati.getDati(0,Radice*2);
				Vector<Object> datiY = dati.getDati(1,Radice*2);
				if(paint && datiX.size()>0 && datiY.size()>0){

					float centerX = (width-prof)/2;
            		float centerY = (height-prof)/2;
            		
            		
            		for (int i = 0; i < datiX.size()-1; i++){
	            		float x1 = new java.math.BigDecimal((String)datiX.elementAt(i)).floatValue()+centerX+prof/2+x;
	            		float y1 = new java.math.BigDecimal((String)datiY.elementAt(i)).floatValue()+centerY+prof/2+y;
	            		float x2 = new java.math.BigDecimal((String)datiX.elementAt(i+1)).floatValue()+centerX+prof/2+x;
	            		float y2 = new java.math.BigDecimal((String)datiY.elementAt(i+1)).floatValue()+centerY+prof/2+y;

	            		cb.setColorFill(element_color_2);
						cb.moveTo(x+centerX+prof/2,y+centerY+prof/2);
						cb.lineTo(x1,y1);
						cb.lineTo(x2,y2);						
						cb.fill();

	                	cb.setColorFill(element_color_1);
	                	cb.moveTo(x+centerX+prof/2,y+centerY+prof/2);
	                	cb.lineTo(x1,y1);
	                	cb.fill();

	                	cb.setColorFill(element_color_1);
	                	cb.moveTo(x+centerX+prof/2,y+centerY+prof/2);
	                	cb.lineTo(x2,y2);
	                	cb.fill();


	                	cb.setColorFill(element_color_1);
	                	cb.moveTo(x1,y1);
	                	cb.lineTo(x2,y2);
	                	cb.fill();
            		}    
				}         
	        }	
        } 
        
    } catch (Exception e) {
    	setError(e,iStub.log_FATAL);
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
public void setScale(Vector<Object> newScale) {
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
public void setValueXY(Vector<Object> newValueXY) {
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

/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */

package neohort.universal.output.lib.chart_advanced_pdf;


import java.util.Vector;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

import neohort.universal.output.lib.chart_pdf.A_chart_content;
import neohort.universal.output.lib.chart_pdf.I_chart_content;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;



public class chart_content_LINE_ADVANCED_XY extends A_chart_content implements I_chart_content, java.io.Serializable {
	private static final long serialVersionUID = -6112979387451177728L;
	static private float space_0 = 2;
public chart_content_LINE_ADVANCED_XY() {
	super();
	reimposta();
}
public void _prepareline(PdfContentByte cb, float x0, float y0, float x1, float y1, float diap, BaseColor color) {
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
	while(l>0){
		xb = (float)Math.cos(alfa)*diap + xb0;
		yb = (float)Math.sin(alfa)*diap + yb0;
		cb.moveTo(xb,yb);
		cb.lineTo(xb,yb);
		cb.fill();
		xb0=xb;
		yb0=yb;
		l-=diap;
	}
}
public void _prepareStyle(PdfContentByte cb, float x0, float y0, int k, BaseColor color, float h) {
	int style = k;
	while(style>6) style-=7;
	switch(style){
		case 0:
	   		cb.setColorFill(color);
			cb.circle(x0,y0,2);
			cb.fill();
			break;
		case 1:
	   		cb.setColorFill(color);
           	cb.moveTo(x0-2,y0-2);
           	cb.lineTo(x0-2,y0+2);
           	cb.lineTo(x0+2,y0+2);
           	cb.lineTo(x0+2,y0-2);
			cb.fill();
			break;
		case 2:
	   		cb.setColorFill(color);
           	cb.moveTo(x0,y0-2);
           	cb.lineTo(x0-2,y0);
           	cb.lineTo(x0,y0+2);
           	cb.lineTo(x0+2,y0);
			cb.fill();
			break;
		case 3:
	   		cb.setColorFill(color);
           	cb.moveTo(x0,y0+2);
           	cb.lineTo(x0-2,y0-2);
           	cb.lineTo(x0+2,y0-2);
			cb.fill();
			break;
		case 4:
	   		cb.setColorFill(color);
           	cb.moveTo(x0,y0+2);
           	cb.lineTo(x0,y0-2);
           	cb.fill();
           	cb.moveTo(x0+2,y0);
           	cb.lineTo(x0-2,y0);
			cb.fill();
			break;
		case 5:
	   		cb.setColorFill(color);
           	cb.moveTo(x0+2,y0+2);
           	cb.lineTo(x0-2,y0-2);
           	cb.fill();
           	cb.moveTo(x0+2,y0-2);
           	cb.lineTo(x0-2,y0+2);
			cb.fill();
			break;
		case 6:
	   		cb.setColorFill(color);
           	cb.moveTo(x0+2,y0+2);
           	cb.lineTo(x0-2,y0-2);
           	cb.fill();
           	cb.moveTo(x0+2,y0-2);
           	cb.lineTo(x0-2,y0+2);
			cb.fill();
           	cb.moveTo(x0,y0+2);
           	cb.lineTo(x0,y0-2);
           	cb.fill();
			break;
	}
}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){
}
public I_chart_dati ChartDatiFactory() {
	return new chart_dati_LINE_ADVANCED_XY();
}
BaseColor getField_Color(Class cl, String name, BaseColor d_value) {
	BaseColor result = d_value;
	if(name.indexOf(",")>-1){
		try{
			int _r = -1;
			int _g = -1;
			int _b = -1;
			java.util.StringTokenizer st = new java.util.StringTokenizer(name, ",");
			if(st.hasMoreTokens()) _r = Integer.valueOf(st.nextToken()).intValue();
			if(st.hasMoreTokens()) _g = Integer.valueOf(st.nextToken()).intValue();
			if(st.hasMoreTokens()) _b = Integer.valueOf(st.nextToken()).intValue();
			if(	_r != -1 &&
				_g != -1 &&
				_b != -1)
				result = new BaseColor(_r,_g,_b);
		}catch(Exception e){}
	}else{
		try{
			result = (BaseColor)cl.getField(name).get(cl);
		}catch(Exception e){
			try{
				result = (BaseColor)cl.getField(name.toUpperCase()).get(cl);
			}catch(Exception ex){
				try{
					result = (BaseColor)cl.getField(name.toLowerCase()).get(cl);
				}catch(Exception exp){}
			}
		}
	}
	return new BaseColor(result.getRGB());
}
public PdfContentByte placeBarcode(PdfContentByte cb, boolean paint) {
	colors = new Vector();
	float prof = 7;
	boolean positionV = false;
    try {
Vector scale_buf = new Vector();

        if (orientation == super.or_TOP) {
			if(paint){
				if(background!=null){
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width);
					cb.fill();
				}
			}
//Label-Top
            if (label != null && label.trim().length() > 0) {
				Vector descr_label_top = new Vector();
				try{
					java.util.StringTokenizer st = new java.util.StringTokenizer(label, ";");
					while (st.hasMoreTokens()){
						String cur_el = st.nextElement().toString().trim();
						if(cur_el!=null && cur_el.indexOf("SYSTEM_COLOR[")==0)
							colors.add(getField_Color(BaseColor.class,
								cur_el.substring(13,cur_el.length()-1),
								null));
						else {
							if(cur_el.equals("POSITION_V"))
								positionV=true;
							if(cur_el.equals("POSITION_H"))
								positionV=false;
							if(!cur_el.equals("POSITION_V") && !cur_el.equals("POSITION_H"))
								descr_label_top.addElement(cur_el);
						}

					}

				}catch(Exception e){}

 	           height_Label =
  	               label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
   	 	        x_Label = x + 2;
    	        y_Label = height + y - height_Label;
				_prepareColori(((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size());

				if(positionV){
					for(int i=0;i<((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size();i++){
						if(paint){
							BaseColor curr_color = _prepareColor(i,((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size());
								cb.setColorFill(curr_color);
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=0){
									cb.moveTo(x_Label,y_Label - (i)*height_Label);
									cb.lineTo(x_Label+20,y_Label - (i)*height_Label);
									cb.fill();
								}
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=-1){
									_prepareStyle(cb,x_Label+10,y_Label - (i)*height_Label,i,curr_color,0);
								}

								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+22,y_Label - (i)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}
						}
					}
				}else{
					for(int i=0;i<((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size();i++){
						if(paint){
							BaseColor curr_color = _prepareColor(i,((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size());
							if(i%2==0){
								cb.setColorFill(curr_color);
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=0){
									cb.moveTo(x_Label,y_Label - (i/2)*height_Label);
									cb.lineTo(x_Label+20,y_Label - (i/2)*height_Label);
									cb.fill();
								}
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=-1){
									_prepareStyle(cb,x_Label+10,y_Label - (i/2)*height_Label,i,curr_color,0);
								}

								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+22,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}
							}else{
								cb.setColorFill(curr_color);
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=0){
									cb.moveTo(x_Label+(width-x)/2,y_Label - (i/2)*height_Label);
									cb.lineTo(x_Label+(width-x)/2+20,y_Label - (i/2)*height_Label);
									cb.fill();
								}
								if((new java.math.BigDecimal((String)dati.getDati(2,0).elementAt(i))).intValue()!=-1){
									_prepareStyle(cb,x_Label+(width-x)/2+10,y_Label - (i/2)*height_Label,i,curr_color,0);
								}

								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+(width-x)/2+22,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}
							}
						}
					}
				}
            }
            if(positionV){
	            if(!paint) height = ((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size()*(height_Label)+10;
            }else{
	            if(!paint) height = ((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size()*(height_Label/2)+10;
            }

        }


        if (orientation == super.or_BOTTOM) {
			if(paint){
				if(background!=null){
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width);
					cb.fill();
				}
			}
//Label-Bottom
			Vector descr_label_X = new Vector();
            if (label != null && label.trim().length() > 0) {
				boolean isLABEL = true;
				try{
					java.util.StringTokenizer st = new java.util.StringTokenizer(label, ";");
					while (st.hasMoreTokens())
						descr_label_X.addElement(st.nextElement().toString().trim());
				}catch(Exception e){}
	   			if(scale.size()==descr_label_X.size()) isLABEL=false;

				if(isLABEL){
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
				if(scale_buf.size() == descr_label_X.size()){
					for(int i=0;i<descr_label_X.size();i++) scale_buf.set(i,descr_label_X.elementAt(i));
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
				cb.setColorFill(new BaseColor(BaseColor.LIGHT_GRAY.getRGB()));
					cb.moveTo(x+0.1f,y_Line+0.1f);
					cb.lineTo(x+0.1f+prof,y_Line+0.1f+prof);
					cb.lineTo(width+x-prof+0.1f,y_Line+0.1f);
					cb.moveTo(width+x-prof+0.1f+prof,y_Line+0.1f+prof);
					cb.lineTo(width+x-prof+0.1f,y_Line+0.1f);
					cb.lineTo(x+0.1f+prof,y_Line+0.1f+prof);
				cb.fill();

				cb.setColorFill(new BaseColor(BaseColor.BLACK.getRGB()));
				cb.moveTo(x, y_Line);
				cb.lineTo(width+x-prof,y_Line);
				for(int i=1;i<scale_buf.size();i++){
					cb.moveTo(x+i*delta_Scale, y_Line-2);cb.lineTo(x+i*delta_Scale, y_Line);cb.fill();
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


        if (orientation == super.or_LEFT) {
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
				cb.setColorFill(new BaseColor(BaseColor.LIGHT_GRAY.getRGB()));
					cb.moveTo(x_Line+0.1f,y+0.1f);
					cb.lineTo(x_Line+0.1f+prof,y+0.1f+prof);
					cb.lineTo(x_Line+0.1f,height+y-prof+0.1f);
					cb.moveTo(x_Line+0.1f+prof,height+y+0.1f);
					cb.lineTo(x_Line+0.1f,height+y-prof+0.1f);
					cb.lineTo(x_Line+0.1f+prof,y+0.1f+prof);
				cb.fill();

				cb.setColorFill(new BaseColor(BaseColor.BLACK.getRGB()));
				cb.moveTo(x_Line, y);
				cb.lineTo(x_Line, height+y-prof);
				for(int i=1;i<scale_buf.size();i++){
					cb.moveTo(x_Line-2, y+i*delta_Scale);cb.lineTo(x_Line, y+i*delta_Scale);cb.fill();
					cb.moveTo(x_Line, y+i*delta_Scale);cb.lineTo(x_Line+prof, y+i*delta_Scale+prof);cb.fill();
				}
				cb.moveTo(x_Line, y);cb.lineTo(x_Line+prof, y+prof);
				cb.fill();

			}

			if(!paint){
				width = x_Line;
				height = height+y-delta_Scale;
			}
        }
        if (orientation == super.or_CENTER) {
			if(paint){
            	if (scale.size() > 0 ) {
	                delta_Scale = (height-prof)/(scale_max-1);
	                cb.setColorFill(new BaseColor(BaseColor.BLACK.getRGB()));
	                for(int i=0;i<scale.size();i++){
						cb.moveTo(x+prof, y+i*delta_Scale+prof);
						cb.lineTo(x+prof+width, y+i*delta_Scale+prof);
						cb.fill();
						_prepareline(cb,x,y+i*delta_Scale,x+width-prof,y+i*delta_Scale,5, new BaseColor(BaseColor.BLACK.getRGB()));
					}
            	}
			}

//Scale-Center
        if (dati != null) {
				try{
					java.util.StringTokenizer st = new java.util.StringTokenizer(label, ";");
					while (st.hasMoreTokens()){
						String cur_el = st.nextElement().toString().trim();
						if(cur_el!=null && cur_el.indexOf("SYSTEM_COLOR[")==0)
							colors.add(getField_Color(BaseColor.class,
								cur_el.substring(13,cur_el.length()-1),
								null));
					}
				}catch(Exception e){}

	        _prepareColori(((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size());
			Vector datiX = dati.getDati(0,width-prof,-1,0);
			Vector datiY = dati.getDati(1,height-prof,-1,0);
			Vector datiZ = dati.getDati(2,height-prof);
			Vector deltaXYZ = dati.getDati(0,width-prof,-2,0);

			float delta_minusX = Float.valueOf((String)deltaXYZ.elementAt(0)).floatValue();
			float delta_minusY = Float.valueOf((String)deltaXYZ.elementAt(1)).floatValue();

/*
				for (int i = 1; i < datiX.size(); i++) {
					if(Float.valueOf((String)datiX.elementAt(i)).floatValue()<delta_minusX)
						delta_minusX = Float.valueOf((String)datiX.elementAt(i)).floatValue();
				}
				for (int i = 1; i < datiY.size(); i++) {
					if(Float.valueOf((String)datiY.elementAt(i)).floatValue()<delta_minusY)
						delta_minusY = Float.valueOf((String)datiY.elementAt(i)).floatValue();
				}
*/


				if(delta_minusY<=0){
					cb.setColorFill(new BaseColor(BaseColor.RED.getRGB()));
						cb.moveTo(x,y-delta_minusY);
						cb.lineTo(x-3,y-delta_minusY+1);
						cb.lineTo(x-3,y-delta_minusY-1);
					cb.fill();
						cb.moveTo(x, y-delta_minusY);
						cb.lineTo(x+prof, y-delta_minusY+prof);
					cb.fill();
						cb.moveTo(x+prof, y-delta_minusY+prof);
						cb.lineTo(x+prof+width, y-delta_minusY+prof);
					cb.fill();
				}

	        int k=0;
			datiX = dati.getDati(0,width-prof,k,0);
			datiY = dati.getDati(1,height-prof,k,0);
			int type_d=1;
			try{
				type_d=new java.math.BigDecimal((String)datiZ.elementAt(k)).intValue();
			}catch(Exception e){
				type_d=1;
			}
			while(datiX!=null && datiY!=null){
				BaseColor curr_color = _prepareColor(k,((chart_dati_LINE_ADVANCED_XY)dati).datiX_st.size());
				if(paint && datiX.size()>0 && datiY.size()>0){

                	for (int i = 0; i < datiX.size()-1; i++) {

	                	float x0 = x+Float.valueOf((String)datiX.elementAt(i)).floatValue()-delta_minusX;
	                	float y0 = y+Float.valueOf((String)datiY.elementAt(i)).floatValue()-delta_minusY;
	                	float x1 = x+Float.valueOf((String)datiX.elementAt(i+1)).floatValue()-delta_minusX;
	                	float y1 = y+Float.valueOf((String)datiY.elementAt(i+1)).floatValue()-delta_minusY;
	                	if(type_d==1){
	                		cb.setColorFill(curr_color);
	                		cb.moveTo(x0,y0);
	                		cb.lineTo(x1,y1);
	                		cb.fill();
	                		_prepareStyle(cb,x0,y0,k, curr_color,0);
	                	}
	                	if(type_d==2){
	                		cb.setColorFill(curr_color);
	                		cb.moveTo(x0,y0);
		                		cb.lineTo(x0+prof,y0+prof);
		                		cb.lineTo(x1,y1);
	                		cb.moveTo(x1+prof,y1+prof);
		                		cb.lineTo(x0+prof,y0+prof);
		                		cb.lineTo(x1,y1);
	                		cb.fill();
/*
	                		cb.setColorFill(Color.black);
	                		cb.moveTo(x0,y0);
	                			cb.lineTo(x0+prof,y0+prof);
	                		cb.fill();
	                		_prepareStyle(cb,x0,y0,k, curr_color);
*/
	                	}
	                	if(type_d==0){
	                		cb.setColorFill(curr_color);
	                		_prepareStyle(cb,x0,y0,k, curr_color,0);
	                	}
	                	if(type_d==-1){
	                		cb.setColorFill(curr_color);
	                		cb.moveTo(x0,y0);
	                		cb.lineTo(x1,y1);
	                		cb.fill();
	                	}
					}
	               	float x0 = x+Float.valueOf((String)datiX.elementAt(datiX.size()-1)).floatValue()-delta_minusX;
	               	float y0 = y+Float.valueOf((String)datiY.elementAt(datiX.size()-1)).floatValue()-delta_minusY;
	     			if(type_d==0)
		               	_prepareStyle(cb,x0,y0,k, curr_color,0);
	     			if(type_d==1)
		               	_prepareStyle(cb,x0,y0,k, curr_color,0);

				}
				k++;
				datiX = dati.getDati(0,width-prof,k,0);
				datiY = dati.getDati(1,height-prof,k,0);
				try{
					type_d=new java.math.BigDecimal((String)datiZ.elementAt(k)).intValue();
				}catch(Exception e){
					type_d=1;
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
		this.setScale(ch_contentL.getScale());
		this.setScale_max(ch_contentL.getScale_max());

	this.label = ch_contentT.getLabel();
}
public void reimposta() {
	try{
		label_font = BaseFont.createFont("Helvetica", "winansi", false);
		scale_font = BaseFont.createFont("Helvetica", "winansi", false);
	}catch(Exception e){}
}
}

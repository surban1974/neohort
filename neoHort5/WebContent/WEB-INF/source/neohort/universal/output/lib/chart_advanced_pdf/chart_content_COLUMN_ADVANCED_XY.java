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


public class chart_content_COLUMN_ADVANCED_XY extends A_chart_content implements I_chart_content, java.io.Serializable {
	private static final long serialVersionUID = -205115486680893971L;
	public float space_0 = 2;
	public Double max_scale_fixed_Y;
	public Boolean show_under_columns;
public chart_content_COLUMN_ADVANCED_XY() {
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
	   		cb.setColorFill(color);
           	cb.moveTo(x0-4,y0+1);
           	cb.lineTo(x0-4,y0+h-1);
           	cb.lineTo(x0+4,y0+h-1);
           	cb.lineTo(x0+4,y0+1);
			cb.fill();

}
public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d){
}
public I_chart_dati ChartDatiFactory() {
	return new chart_dati_COLUMN_ADVANCED_XY();
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
	colorsShadow = new Vector();
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
						if(cur_el==null) cur_el="";
						boolean isLabel = true;
						if(cur_el.indexOf("SYSTEM_COLOR[")==0){
							colors.add(getField_Color(BaseColor.class,
								cur_el.substring(13,cur_el.length()-1),
								null));
							isLabel = false;
						}
						if(cur_el.indexOf("SYSTEM_SCALE_FIXED_Y[")==0){
							try{
								max_scale_fixed_Y = new Double(cur_el.substring(21,cur_el.length()-1));
							}catch(Exception e){}
							isLabel = false;
						}
						if(cur_el.equals("SYSTEM_SHOW_UNDER_COLUMNS")){
							show_under_columns = new Boolean(true);
							isLabel = false;
						}
						if(isLabel && cur_el.equals("POSITION_V")){
							positionV=true;
							isLabel = false;
						}
						if(isLabel && cur_el.equals("POSITION_H")){
							positionV=false;
							isLabel = false;
						}
						if(isLabel) descr_label_top.addElement(cur_el);
					}
				}catch(Exception e){}

 	           height_Label =
  	               label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
   	 	        x_Label = x + 20;
    	        y_Label = height + y - height_Label;
				_prepareColori(((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size());
				if(positionV){
					for(int i=0;i<((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size()-1;i++){
						int di=i+1;
						if(paint){
							BaseColor curr_color = _prepareColor(di,((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size());

							_prepareStyle(cb,x_Label+10,y_Label - (i)*height_Label,di,curr_color,height_Label);

							if(descr_label_top.size()>di){
								cb.setColorFill(label_color);
								cb.beginText();
								cb.setFontAndSize(label_font, label_fontsize);
								cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(di), x_Label+22,y_Label - (i)*height_Label, label_gr);
								cb.endText();
								cb.fill();
							}
						}
					}

				}else{
					for(int i=0;i<((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size()-1;i++){
						int di=i+1;
						if(paint){
							BaseColor curr_color = _prepareColor(di,((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size());
							if(i%2==0){
								_prepareStyle(cb,x_Label+10,y_Label - (i/2)*height_Label,di,curr_color,height_Label);

								if(descr_label_top.size()>di){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(di), x_Label+22,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}
							}else{
								_prepareStyle(cb,x_Label+(width-x)/2+10,y_Label - (i/2)*height_Label,di,curr_color,height_Label);
								if(descr_label_top.size()>di){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(di), x_Label+(width-x)/2+22,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}
							}
						}
					}
				}
            }
	            if(positionV){
					if(!paint) height = ((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size()*(height_Label)+10;
  	          	}else{
	  	          	if(!paint) height = ((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size()*(height_Label/2)+10;
    	        }

	        }


        if (orientation == super.or_BOTTOM) {
	        if(scale_max<0) scale_max = scale.size();

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
            if (scale.size() > 0 ) {
	            scale_max = scale.size();
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


                delta_Scale = (width-prof)/(scale_max);
				if(paint && isShow_scale()){
					double coefAlfa = 1;
 					if(scale_gr!=0)	coefAlfa = Math.abs(Math.sin(scale_gr*Math.PI/180));
 					float coefAlfaD = new java.math.BigDecimal(coefAlfa).floatValue();

					cb.setColorFill(scale_color);
                    for(int i=0;i<scale_buf.size();i++){
						cb.beginText();
						cb.setFontAndSize(scale_font, scale_fontsize);
						if(label_gr==0){
							if(scale_gr==0)
								cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+delta_Scale/2+4*space_0,y_Scale+height_Scale+2*space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+delta_Scale/2+4*space_0 - scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize),y_Scale+width_Scale*coefAlfaD+2*space_0, scale_gr);
						}else{
							if(scale_gr==0)
								cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+delta_Scale/2+4*space_0,y_Scale+height_Scale+2*space_0, scale_gr);
							else cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, (String)scale_buf.elementAt(i), x+i*delta_Scale+delta_Scale/2+4*space_0 - scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize),y_Scale+width_Scale*coefAlfaD+2*space_0, scale_gr);
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
 			if(!isShow_scale() && (label==null || label.trim().length()==0))
	 			y_Line = y+height_Label+height_Scale;
	 		else{
				if(label_gr==0){
		 			if(scale_gr==0) y_Line = y+height_Label+8*space_0+height_Scale;
 					else y_Line = y+height_Label+5*space_0+width_Scale*coefAlfaD;
				}else{
	 				if(scale_gr==0) y_Line = y+width_Label+8*space_0+height_Scale;
 					else y_Line = y+width_Label+5*space_0+width_Scale*coefAlfaD;
	 			}
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
				for(int i=0;i<scale_buf.size();i++){
					cb.moveTo(x+(i+1)*delta_Scale, y_Line-2);
					cb.lineTo(x+(i+1)*delta_Scale, y_Line);
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

	        if(max_scale_fixed_Y!=null){
		        scale = ((chart_dati_COLUMN_ADVANCED_XY)dati).getScaleY(max_scale_fixed_Y);
		        scale_max = scale.size();
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
            if (scale.size() > 0) {
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

		        if(max_scale_fixed_Y!=null){
			        delta_Scale = (float)((chart_dati_COLUMN_ADVANCED_XY)dati).getDeltaScaleY(max_scale_fixed_Y,height-prof);
			        if(delta_Scale==-1) delta_Scale = (height-prof)/(scale_max-1);
	  		    }else delta_Scale = (height-prof)/(scale_max-1);

				if(paint && isShow_scale()){
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
//			if(!paint) width = x+height_Label+space_0+width_Scale+1;
			if(!paint){
				width = x_Line;
				height = height+y-delta_Scale;
			}
        }
        if (orientation == super.or_CENTER) {

	        if(max_scale_fixed_Y!=null){
		        scale = ((chart_dati_COLUMN_ADVANCED_XY)dati).getScaleY(max_scale_fixed_Y);
		        scale_max = scale.size();
	        }

			if(paint){
				cb.setColorFill(new BaseColor(BaseColor.LIGHT_GRAY.getRGB()));
					cb.rectangle(x+0.2f+prof,y+0.2f,width,height);
				cb.fill();
	           	if (scale.size() > 0 ) {
			        if(max_scale_fixed_Y!=null){
				        delta_Scale = (float)((chart_dati_COLUMN_ADVANCED_XY)dati).getDeltaScaleY(max_scale_fixed_Y,height-prof);
				        if(delta_Scale==-1) delta_Scale = (height-prof)/(scale_max-1);
	  			    }else delta_Scale = (height-prof)/(scale_max-1);
	                cb.setColorFill(new BaseColor(BaseColor.BLACK.getRGB()));
	                for(int i=0;i<scale.size();i++){
						cb.moveTo(x+prof, y+i*delta_Scale+prof);
						cb.lineTo(x+prof+width, y+i*delta_Scale+prof);
						cb.fill();
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

	        _prepareColori(((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size());
	        _prepareColoriShadow(((chart_dati_COLUMN_ADVANCED_XY)dati).datiX_st.size());
			Vector datiX = dati.getDati(0,width-prof,-1,0);
			Vector datiY = dati.getDati(1,height-prof,-1,0);

float deltaGen = (width-prof)/datiY.size();

			float delta_minusX = Float.valueOf((String)datiX.elementAt(0)).floatValue();
			float delta_minusY = Float.valueOf((String)datiY.elementAt(0)).floatValue();
					for (int i = 1; i < datiX.size(); i++) {
						if(Float.valueOf((String)datiX.elementAt(i)).floatValue()<delta_minusX)
							delta_minusX = Float.valueOf((String)datiX.elementAt(i)).floatValue();
						if(Float.valueOf((String)datiY.elementAt(i)).floatValue()<delta_minusY)
							delta_minusY = Float.valueOf((String)datiY.elementAt(i)).floatValue();
					}


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
Vector datiYDes = new Vector();
Vector datiXGen = new Vector();
Vector datiYGen = new Vector();
int countR=0;
Vector datiydesloc = null;
			datiX = dati.getDati(0,width-prof,k,0);
			datiY = dati.getDati(1,height-prof,k,0);
			datiydesloc = dati.getDati(11,height-prof,k,0);
			if(datiydesloc!=null)
					datiYDes.add(datiydesloc);
			countR = datiX.size();
			while(datiX!=null && datiY!=null){
				datiXGen.add(datiX);
				datiYGen.add(datiY);
				k++;
				datiX = dati.getDati(0,width-prof,k,0);
				datiY = dati.getDati(1,height-prof,k,0);
				datiydesloc = dati.getDati(11,height-prof,k,0);
				if(datiydesloc!=null)
						datiYDes.add(datiydesloc);

			}

Vector datiXRes = new Vector();
Vector datiYRes = new Vector();
Vector datiYResDes = new Vector();
Vector datiColor = new Vector();
Vector datiColorShadow = new Vector();

			for(int i=0;i<countR;i++){
				for(int j=0;j<datiXGen.size();j++){
					if(j==0){
						datiXRes.add(null);
						datiYRes.add(null);
						datiYResDes.add(null);
						datiColor.add(colors.elementAt(j));
						datiColorShadow.add(colorsShadow.elementAt(j));
					}else{
						datiXRes.add(((Vector)datiXGen.elementAt(j)).elementAt(i));
						datiYRes.add(((Vector)datiYGen.elementAt(j)).elementAt(i));
						datiYResDes.add(((Vector)datiYDes.elementAt(j)).elementAt(i));
						datiColor.add(colors.elementAt(j));
						datiColorShadow.add(colorsShadow.elementAt(j));
					}
				}
			}

			float prof2=prof*(float)Math.cos(Math.PI/4);
			float prof3=0;
			if(datiXRes!=null && datiYRes!=null){
              	for (int i = 0; i < datiXRes.size(); i++) {
					if(datiXRes.elementAt(i)!=null){
	                	float x0 = x+Float.valueOf((String)datiXRes.elementAt(i)).floatValue()-delta_minusX;
	                	float y0 = y+Float.valueOf((String)datiYRes.elementAt(i)).floatValue()-delta_minusY;
	                	float x1 = x+Float.valueOf((String)datiXRes.elementAt(i)).floatValue()+deltaGen-delta_minusX;
	                	float y1 = y-delta_minusY;
	                	if(x1-x0<prof*Math.cos(Math.PI/4)) prof2=(x1-x0)*(float)Math.cos(Math.PI/4);
	                	if(prof2<0) prof2=1;
	                	prof3 = (prof-prof2)/2;


	                	x0+=prof3;
	                	x1+=prof3;
	                	y0+=prof3;
	                	y1+=prof3;

if(show_under_columns!=null && show_under_columns.booleanValue()){
	String valueDes = null;
	try{
		valueDes = (String)datiYResDes.get(i);
	}catch(Exception e){}
	if(valueDes!=null){
		cb.setColorFill(scale_color);
		cb.beginText();
		cb.setFontAndSize(scale_font, scale_fontsize);
		if(scale_gr==90) cb.showTextAligned(PdfContentByte.ALIGN_CENTER, valueDes, (x1+x0+prof2)/2, (float)(y0+1.2*prof2), scale_gr);
		else cb.showTextAligned(PdfContentByte.ALIGN_CENTER, valueDes, (x1+x0+prof2)/2, (float)(y0+1.2*prof2), scale_gr);
		cb.endText();
		cb.fill();
	}
}

	                	cb.setColorFill((BaseColor)datiColor.elementAt(i));
	                	cb.rectangle(x0,y0,x1-x0,y1-y0);
	                	cb.fill();

	                	cb.setColorFill((BaseColor)datiColorShadow.elementAt(i));
   						cb.moveTo(x1, y1);
							cb.lineTo(x1,y0);
							cb.lineTo(x1+prof2,y1+prof2);
						cb.moveTo(x1+prof2, y0+prof2);
							cb.lineTo(x1,y0);
							cb.lineTo(x1+prof2,y1+prof2);
						cb.fill();

						if(y0>y1 ){

		                	cb.setColorFill((BaseColor)datiColorShadow.elementAt(i));
 	  						cb.moveTo(x0, y0);
								cb.lineTo(x1,y0);
								cb.lineTo(x0+prof2,y0+prof2);
							cb.moveTo(x1+prof2, y0+prof2);
								cb.lineTo(x1,y0);
								cb.lineTo(x0+prof2,y0+prof2);
							cb.fill();
						}else{
		                	cb.setColorFill((BaseColor)datiColorShadow.elementAt(i));
 	  						cb.moveTo(x0, y1);
								cb.lineTo(x1,y1);
								cb.lineTo(x0+prof2,y1+prof2);
							cb.moveTo(x1+prof2, y1+prof2);
								cb.lineTo(x1,y1);
								cb.lineTo(x0+prof2,y1+prof2);
							cb.fill();
						}
	                }
               	}
				if(delta_minusY<=0){
					float delta_ScaleX = (width-prof)/scaleX_max;
					cb.setColorFill(new BaseColor(BaseColor.RED.getRGB()));
						cb.moveTo(x, y-delta_minusY);
						cb.lineTo(x+width-prof, y-delta_minusY);
					cb.fill();
					for(int i=0;i<scaleX.size();i++){
						cb.moveTo(x+(i+1)*delta_ScaleX, y-delta_minusY-2);
						cb.lineTo(x+(i+1)*delta_ScaleX, y-delta_minusY);
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

	((chart_content_COLUMN_ADVANCED_XY)ch_contentL).max_scale_fixed_Y =
		((chart_content_COLUMN_ADVANCED_XY)ch_contentT).max_scale_fixed_Y;

	cb = ch_contentL.placeBarcode(cb,true);

	ch_contentB.setX(w_l);
	ch_contentB.setY(0);
	ch_contentB.setWidth(_w_d-w_l);
	cb = ch_contentB.placeBarcode(cb,true);

	ch_contentT.setX(w_l);
	ch_contentT.setY(h_l+h_b);
	ch_contentT.setWidth(_w_d-w_l);
	cb = ch_contentT.placeBarcode(cb,true);

	this.max_scale_fixed_Y =
		((chart_content_COLUMN_ADVANCED_XY)ch_contentT).max_scale_fixed_Y;
	this.show_under_columns =
		((chart_content_COLUMN_ADVANCED_XY)ch_contentT).show_under_columns;

	this.setX(w_l);
	this.setY(h_b);
	this.setWidth(_w_d-w_l);
	this.setHeight(_h_d-h_t-h_b);
		this.setScale(ch_contentL.getScale());
		this.setScale_max(ch_contentL.getScale_max());

	this.scaleX = ch_contentB.getScale();
	this.scaleX_max = ch_contentB.getScale_max();
	this.label = ch_contentT.getLabel();

	this.setScale_color(ch_contentL.getScale_color());
	this.setScale_font(ch_contentL.getScale_font());
	this.setScale_fontsize(ch_contentL.getScale_fontsize());
	this.setScale_format(ch_contentL.getScale_format());
	this.setScale_gr(ch_contentL.getScale_gr());


}
public void reimposta() {
	try{
		label_font = BaseFont.createFont("Helvetica", "winansi", false);
		scale_font = BaseFont.createFont("Helvetica", "winansi", false);
	}catch(Exception e){}
}
}

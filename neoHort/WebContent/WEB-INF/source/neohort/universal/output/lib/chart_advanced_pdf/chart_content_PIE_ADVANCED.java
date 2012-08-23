/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */
package neohort.universal.output.lib.chart_advanced_pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

import neohort.universal.output.lib.chart_pdf.A_chart_content;
import neohort.universal.output.lib.chart_pdf.I_chart_content;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;



public class chart_content_PIE_ADVANCED extends A_chart_content implements I_chart_content, java.io.Serializable {
	private static final long serialVersionUID = -3688167212361469202L;
	static private float space_0 = 2;
    private float prof = 7; 
public chart_content_PIE_ADVANCED() {
	super();
	reimposta();
}
public void _prepareline(PdfContentByte cb, float x0, float y0, float x1, float y1, float diap, Color color) {	
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
public void _prepareStyle(PdfContentByte cb, float x0, float y0, int k, Color color, float h) {	
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
	return new chart_dati_PIE_ADVANCED();
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

        if (orientation == super.or_TOP) {
			if(paint){	        
				if(background!=null){	        
					cb.setColorFill(background);
					cb.rectangle(x,y,height,width); 
					cb.fill();
				}
			}
//Label-Top
boolean positionV = false;

			Vector datiXbuf = dati.getDati(0, 1*2);
            if (label != null && label.trim().length() > 0) {
				Vector descr_label_top = new Vector();
				try{
					java.util.StringTokenizer st = new java.util.StringTokenizer(label, ";"); 
					while (st.hasMoreTokens()){
						String curr_st = st.nextElement().toString().trim();
						if(curr_st.equals("POSITION_V"))
							positionV=true;
						if(curr_st.equals("POSITION_H"))
							positionV=false;
						if(!curr_st.equals("POSITION_V") && !curr_st.equals("POSITION_H"))	
							descr_label_top.addElement(curr_st);
					}	
				}catch(Exception e){}
				
				for(int i=0;i<datiXbuf.size();i++)
					descr_label_top.addElement("");
			
 	           height_Label =
  	               label_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, label_fontsize);
   	 	        x_Label = x + 20;
    	        y_Label = height + y - height_Label;
				if(positionV){
					for(int i=0;i<descr_label_top.size();i++){
						chart_dati_PIE_container_ADVANCED cdc = null;
						int k=0;
						while(k<datiXbuf.size() && i!=((chart_dati_PIE_container_ADVANCED)datiXbuf.elementAt(k)).getId())
							k++;
						if(k<datiXbuf.size())
							cdc = (chart_dati_PIE_container_ADVANCED)datiXbuf.elementAt(k);
						if(paint && cdc!=null){
							Color curr_color = cdc.getColorTop();
								_prepareStyle(cb,x_Label,y_Label - (i)*height_Label,i,curr_color,height_Label);

								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+12,y_Label - (i)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}	
						}	            
					}

				}else{
					for(int i=0;i<descr_label_top.size();i++){
						chart_dati_PIE_container_ADVANCED cdc = null;
						int k=0;
						while(k<datiXbuf.size() && i!=((chart_dati_PIE_container_ADVANCED)datiXbuf.elementAt(k)).getId())
							k++;
						if(k<datiXbuf.size())
							cdc = (chart_dati_PIE_container_ADVANCED)datiXbuf.elementAt(k);
						if(paint && cdc!=null){
							Color curr_color = cdc.getColorTop();
							if(i%2==0){
								_prepareStyle(cb,x_Label,y_Label - (i/2)*height_Label,i,curr_color,height_Label);

								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+12,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}	
							}else{
								_prepareStyle(cb,x_Label+(width-x)/2,y_Label - (i/2)*height_Label,i,curr_color,height_Label);
								if(descr_label_top.size()>i){
									cb.setColorFill(label_color);
									cb.beginText();
									cb.setFontAndSize(label_font, label_fontsize);
									cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)descr_label_top.elementAt(i), x_Label+(width-x)/2+12,y_Label - (i/2)*height_Label, label_gr);
									cb.endText();
									cb.fill();
								}	
							}	
						}	            
					}

				}	

            }
	            if(positionV){ 
					if(!paint) height = datiXbuf.size()*(height_Label)+20;
  	          	}else{
	  	          	if(!paint) height = datiXbuf.size()*(height_Label/2)+20;
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
        
        if (orientation == super.or_CENTER) {
            if (paint) {
                if (background != null) {
                    cb.setColorFill(background);
                    cb.rectangle(x, y, width, height);
                    cb.fill();
                }
            }

//Scale-Center
float minY = 0;
float maxY = height;
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

						chart_dati_PIE_container_ADVANCED cdc = (chart_dati_PIE_container_ADVANCED)datiX.elementAt(i);
                        float x1 = cdc.getX0()+x+centerX+prof;
                        float y1 = cdc.getY0()/coefStr+y+centerY+prof;
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float x2 = cdc.getX1()+x+centerX+prof;
                        float y2 = cdc.getY1()/coefStr+y+centerY+prof;
                        float xcl = cdc.getXcontrol()+x+centerX+prof;
                        float xclL = cdc.getXcontrolL()+x+centerX+prof;
                        float yclL = cdc.getYcontrolL()/coefStr+y+centerY+prof;
                        float xclR = cdc.getXcontrolR()+x+centerX+prof;
                        float yclR = cdc.getYcontrolR()/coefStr+y+centerY+prof;
                        

                        
                        

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

						
                        
						if( (cdc.getXcontrol()!=0 || cdc.getYcontrol()!=0) &&
							(
							(Math.abs(xcl-x1)>Math.abs(x1-x2)) ||
							(Math.abs(xcl-x2)>Math.abs(x1-x2))
							)
						){
 	 	                   	if(cdc.getXcontrolL()!=-1 && cdc.getYcontrolL()!=-1){
/*	 	 	                   	
  	                      		cb.setColorFill(cdc.getColorSh());							
	                        	cb.moveTo(xclL,yclL + heightPie);
		                        	cb.lineTo(x1,y1 + heightPie);
		                        	cb.lineTo(x1,y1 - heightPie);
 		                       		cb.lineTo(xclL,yclL - heightPie);
*/
  	                      		cb.setColorFill(cdc.getColorSh());							
	                        	cb.moveTo(xclL,yclL + heightPie);
		                        	cb.lineTo(xd,yd + heightPie);
		                        	cb.lineTo(xclL,yclL - heightPie);
		                        cb.moveTo(xd,yd - heightPie);
                       				cb.lineTo(xclL,yclL - heightPie);
                       				cb.lineTo(xd,yd + heightPie);
	 	 	                   cb.fill();
 	 	                   	} 
 	 	                   	 
							if(cdc.getXcontrolR()!=-1 && cdc.getYcontrolR()!=-1){
/*								
  	                      		cb.setColorFill(cdc.getColorSh());							
	                        	cb.moveTo(xclR,yclR + heightPie);
		                        	cb.lineTo(x2,y2 + heightPie);
		                        	cb.lineTo(x2,y2 - heightPie);
 		                       		cb.lineTo(xclR,yclR - heightPie);
 	 	                   		cb.fill();
*/
  	                      		cb.setColorFill(cdc.getColorSh());
	                        	cb.moveTo(xclR,yclR + heightPie);
		                        	cb.lineTo(xd,yd + heightPie);
		                        	cb.lineTo(xclR,yclR - heightPie);
		                        cb.moveTo(xd,yd - heightPie);
                       				cb.lineTo(xclR,yclR - heightPie);
                       				cb.lineTo(xd,yd + heightPie);
	 	 	                   cb.fill();
	
							}	
 	 	                    
 	                   
                		}   
                        
		
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
						chart_dati_PIE_container_ADVANCED cdc = (chart_dati_PIE_container_ADVANCED)datiX.elementAt(i);
                        float x1 = cdc.getX0()+x+centerX+prof;
                        float y1 = cdc.getY0()/coefStr+y+centerY+prof;
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float x2 = cdc.getX1()+x+centerX+prof;
                        float y2 = cdc.getY1()/coefStr+y+centerY+prof;

                        float yl = cdc.getYlabel()/coefStr+y+centerY+prof;

                        if(yl>maxY) maxY = yl;
                        if(yl<minY) minY = yl;
                        
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

Vector full_des_y = new Vector();
Vector full_des_x = new Vector();
Vector full_des_pair = new Vector();

float max_sc_width_buf = 0;
 
            if (scale.size() > 0 && isShow_scale()) {
            	for (int i = 0; i < datiX.size(); i++) {
					chart_dati_PIE_container_ADVANCED cdc = (chart_dati_PIE_container_ADVANCED)datiX.elementAt(i);
					String value = "";
					try{
                   		value = ((chart_dati_PIE_container_ADVANCED)scale.elementAt(cdc.getId())).getLabel();
					}catch(Exception e){}	
                    float sc_width_buf = scale_font.getWidthPoint(value+" (100.00%)", scale_fontsize);
                    if (width_Scale < sc_width_buf)
                      	width_Scale = sc_width_buf;
                    scale_buf.addElement(value+" ("+((chart_dati_PIE_container_ADVANCED)scale.elementAt(cdc.getId())).getLabelPerc()+"%) ");
                    if(max_sc_width_buf<scale_font.getWidthPoint((String)scale_buf.elementAt(i), scale_fontsize))
                    	max_sc_width_buf=scale_font.getWidthPoint((String)scale_buf.elementAt(i), scale_fontsize);
                }

                height_Scale =
                    scale_font.getFontDescriptor(BaseFont.AWT_MAXADVANCE, scale_fontsize) + (float)0.5;

				float yl_C=0;
				float xl_C=0;
                     
            	for (int i = 0; i < datiX.size(); i++) {
						chart_dati_PIE_container_ADVANCED cdc = (chart_dati_PIE_container_ADVANCED)datiX.elementAt(i);
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float xl = cdc.getXlabel()+x+centerX+prof;
                        float yl = cdc.getYlabel()/coefStr+y+centerY+prof;

						yl_C=0;
						xl_C=0;

                        float max_LOC = scale_font.getWidthPoint((String)scale_buf.elementAt(i), scale_fontsize);
                        	
						float delta = ((Radice/coefStr)-Math.abs(yd-yl)<0)?10:Math.abs(10-((Radice/coefStr)-Math.abs(yd-yl)));
                      	if(yd<yl)
	                      	yl_C = yd + (Radice/coefStr+3) + delta+5;
                        else
	                        yl_C = yd - (Radice/coefStr+3) - height_Scale-2 - delta-5;

						if(xd>xl) xl_C = (xl-max_sc_width_buf<0)?5:xl-max_sc_width_buf;
						else xl_C = xl;

						if(xd>xl) xl_C = xl-(max_LOC/2)*(xl/(width-width/10)/2);
						else xl_C = xl+(max_LOC/2)*(((width-width/2)-xl+xd)/(width-width/2)/2);
						
						boolean check_y = false;
						
						for(int ii=0;ii<full_des_x.size();ii++){
							if(yd<yl){
								if(((Float)full_des_pair.elementAt(ii)).floatValue()==0 && Math.abs(((Float)full_des_x.elementAt(ii)).floatValue()-xl_C)<max_sc_width_buf) check_y = true;
							}else{
								if(((Float)full_des_pair.elementAt(ii)).floatValue()==1 && Math.abs(((Float)full_des_x.elementAt(ii)).floatValue()-xl_C)<max_sc_width_buf) check_y = true;								
							}	
						}
						
						
						if(yd<yl) full_des_pair.add(new Float(0));
						else full_des_pair.add(new Float(1));
						
		                int operand = 1; 
		                float yl_C_p = yl_C;
		                int max_control = 0;
						if(check_y){
							int ii=0;
 	                      	while(ii<full_des_y.size() && max_control<full_des_y.size()*10){
	 	                       	float corr = ((Float)full_des_y.elementAt(ii)).floatValue();
	  	                      	if( Math.abs(corr-yl_C)<(height_Scale+1)){
		  	                      	if(yd<yl_C) yl_C+=(height_Scale+1)*operand;		   	                     		
 		                       		else yl_C-=(height_Scale+1)*operand;
 		                       		if(yl_C<(height_Scale+1) || yl_C>height-((height_Scale+1))){
	 		                       		operand*=-1;
	 		                       		ii=0;
	 		                       		yl_C = yl_C_p;
 		                       		}	
	                        	}
	                        	else ii++;
	                        	max_control++;
                        	}
						}  	
						full_des_y.add(new Float(yl_C));
                        cb.setColorFill(Color.gray);	


                        
//						if(xd>xl) xl_C = xl-(max_LOC/2)*(xl/(width-width/10)/2);
//						else xl_C = xl+(max_LOC/2)*(((width-width/2)-xl+xd)/(width-width/2)/2);


if(xd>xl)   						
	full_des_x.add(new Float(xl_C));
else 	
	full_des_x.add(new Float(xl_C-max_LOC));									
						cb.setColorFill(Color.gray);	                        
	                    if(yd>yl){
			                	cb.moveTo((float)(xl_C-0.2),(float)(yl_C));cb.lineTo(xl, yl-heightPie);cb.fill();
                        }else{
			                	cb.moveTo((float)(xl_C+0.2),(float)(yl_C+height_Scale));cb.lineTo(xl, yl+heightPie);cb.fill();
                        } 
									
                    }
            	for (int i = 0; i < datiX.size(); i++) {
						chart_dati_PIE_container_ADVANCED cdc = (chart_dati_PIE_container_ADVANCED)datiX.elementAt(i);
                        float xd = cdc.getXcenter()+x+centerX+prof;
                        float yd = cdc.getYcenter()/coefStr+y+centerY+prof;
                        float xl = cdc.getXlabel()+x+centerX+prof;
                        float yl = cdc.getYlabel()/coefStr+y+centerY+prof;

                        float max_LOC = scale_font.getWidthPoint((String)scale_buf.elementAt(i), scale_fontsize);

                        yl_C=((Float)full_des_y.elementAt(i)).floatValue();

						if(xd>xl) xl_C = xl-(max_LOC/2)*(xl/(width-width/10)/2);
						else xl_C = xl+(max_LOC/2)*(((width-width/2)-xl+xd)/(width-width/2)/2);
							

						if(background!=null) cb.setColorFill(background);
						else cb.setColorFill(Color.white);
						
							if(xd>xl){

									cb.moveTo(xl_C, yl_C);
										cb.lineTo(xl_C, yl_C+height_Scale);
										cb.lineTo(xl_C+max_LOC, yl_C);
									cb.moveTo(xl_C+max_LOC, yl_C+height_Scale);
										cb.lineTo(xl_C, yl_C+height_Scale);
										cb.lineTo(xl_C+max_LOC, yl_C);
									
							}else{
									cb.moveTo(xl_C, yl_C);
										cb.lineTo(xl_C-max_LOC, yl_C);
										cb.lineTo(xl_C, yl_C+height_Scale);
									cb.moveTo(xl_C-max_LOC, yl_C+height_Scale);
										cb.lineTo(xl_C-max_LOC, yl_C);
										cb.lineTo(xl_C, yl_C+height_Scale);
							}	
						cb.fill();
						
						
						cb.setColorFill(scale_color);
							cb.beginText();
							cb.setFontAndSize(scale_font, scale_fontsize);
							if(xd>xl)
								cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)scale_buf.elementAt(i), (float)(xl_C+0.2), (float)(yl_C+1.4), 0);
							else cb.showTextAligned(PdfContentByte.ALIGN_LEFT, (String)scale_buf.elementAt(i), (float)(xl_C-max_LOC+0.2), (float)(yl_C+1.4), 0);
							cb.endText();
						cb.fill();
						
							if(xd>xl){
								if(yd>yl){
									cb.moveTo((float)(xl_C-0.2), (float)(yl_C+height_Scale+0.2));cb.lineTo((float)(xl_C+max_LOC+0.2), (float)(yl_C+height_Scale+0.2));cb.fill();
								}else{
									cb.moveTo((float)(xl_C-0.2), (float)(yl_C-0.2));cb.lineTo((float)(xl_C+max_LOC+0.2), (float)(yl_C-0.2));cb.fill();									
								}	
							}else{
								if(yd>yl){								
									cb.moveTo((float)(xl_C-0.2), (float)(yl_C+height_Scale+0.2));cb.lineTo((float)(xl_C-0.2-max_LOC), (float)(yl_C+height_Scale+0.2));cb.fill();
								}else{
									cb.moveTo((float)(xl_C-0.2), (float)(yl_C-0.2));cb.lineTo((float)(xl_C-1.2-max_LOC), (float)(yl_C-0.2));cb.fill();									
								}		
							}
							
                        
                    }

            		
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

		this.setScale(ch_contentT.getScale());
		this.setScale_color(ch_contentT.getScale_color());
		this.setScale_font(ch_contentT.getScale_font());
		this.setScale_fontsize(ch_contentT.getScale_fontsize());
		this.setShow_scale(ch_contentT.isShow_scale());
		this.setScale_format(ch_contentT.getScale_format());
		this.setScale_gr(ch_contentT.getScale_gr());
		
	
		
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
}

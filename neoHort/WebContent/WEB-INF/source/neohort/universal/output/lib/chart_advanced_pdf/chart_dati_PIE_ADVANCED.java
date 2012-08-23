/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */
package neohort.universal.output.lib.chart_advanced_pdf;

import java.util.ArrayList;
import java.util.Vector;

import neohort.universal.output.lib.chart_pdf.A_chart_content;
import neohort.universal.output.lib.chart_pdf.A_chart_dati;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;
import neohort.util.util_format;
import neohort.util.util_sort;

public class chart_dati_PIE_ADVANCED extends A_chart_dati implements I_chart_dati{
	
chart_dati_PIE_ADVANCED() {
	super();
	reimposta();
}
public chart_dati_PIE_ADVANCED(java.lang.String newFormatX,java.lang.String newFormatY,java.lang.String newFormatZ) {
	super();
	reimposta();	
	if(newFormatX!=null) formatX = newFormatX;
	if(newFormatY!=null) formatY = newFormatY;
	if(newFormatZ!=null) formatZ = newFormatZ;	
}
    public  ArrayList bezierArc(float x1, float y1, float x2, float y2, float startAng, float extent, float xd, float yd) {
        float tmp;
        
        float alfa_tmp = (float)((startAng)*Math.PI/180);        
        extent = extent - startAng;
        startAng = 0;
        
        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y2 > y1) {
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        
        float fragAngle;
        int Nfrag;
        if (Math.abs(extent) <= 90f) {
            fragAngle = extent;
            Nfrag = 1;
        }
        else {
            Nfrag = (int)(Math.ceil(Math.abs(extent)/90f));
            fragAngle = extent / Nfrag;
        }
        float x_cen = (x1+x2)/2f;
        float y_cen = (y1+y2)/2f;
        float rx = (x2-x1)/2f;
        float ry = (y2-y1)/2f;
        float halfAng = (float)(fragAngle * Math.PI / 360.);
        float kappa = (float)(Math.abs(4. / 3. * (1. - Math.cos(halfAng)) / Math.sin(halfAng)));
        ArrayList pointList = new ArrayList(); 
        for (int i = 0; i < Nfrag; ++i) {
            float theta0 = (float)((startAng + i*fragAngle) * Math.PI / 180.);
            float theta1 = (float)((startAng + (i+1)*fragAngle) * Math.PI / 180.);
            float cos0 = (float)Math.cos(theta0);
            float cos1 = (float)Math.cos(theta1);
            float sin0 = (float)Math.sin(theta0);
            float sin1 = (float)Math.sin(theta1);

            float x_0;
            float y_0;
            float x_1;
            float y_1;
            float x_2;
            float y_2;
            float x_3;
            float y_3;

             
            if (fragAngle > 0f) {
	            x_0 = x_cen + rx * cos0;
                y_0 = y_cen - ry * sin0;
                x_1 = x_cen + rx * (cos0 - kappa * sin0);
                y_1 = y_cen - ry * (sin0 + kappa * cos0);
                x_2 = x_cen + rx * (cos1 + kappa * sin1);
                y_2 = y_cen - ry * (sin1 - kappa * cos1);
                x_3 = x_cen + rx * cos1;
                y_3 = y_cen - ry * sin1;
            }
            else {
	            x_0 = x_cen + rx * cos0;
                y_0 = y_cen - ry * sin0;
                x_1 = x_cen + rx * (cos0 + kappa * sin0);
                y_1 = y_cen - ry * (sin0 - kappa * cos0); 
                x_2 = x_cen + rx * (cos1 - kappa * sin1);
                y_2 = y_cen - ry * (sin1 + kappa * cos1); 
                x_3 = x_cen + rx * cos1;
                y_3 = y_cen - ry * sin1;
            }
            float R0 = (float)Math.sqrt((x_0-x_cen)*(x_0-x_cen)+(y_0-y_cen)*(y_0-y_cen));
            	float r0_alfa = (float)(Math.acos(Math.abs((x_0-x_cen)/R0)));
            	if(x_0>=0 && y_0>=0) r0_alfa+=alfa_tmp;
            	if(x_0<0 && y_0>=0) r0_alfa=(float)(Math.PI-r0_alfa+alfa_tmp);
            	if(x_0<0 && y_0<0) r0_alfa=(float)(Math.PI+r0_alfa+alfa_tmp);
            	if(x_0>=0 && y_0<0) r0_alfa=(float)(2*Math.PI-r0_alfa+alfa_tmp); 
            	
            	
            float R1 = (float)Math.sqrt((x_1-x_cen)*(x_1-x_cen)+(y_1-y_cen)*(y_1-y_cen));
            	float r1_alfa = (float)(Math.acos(Math.abs((x_1-x_cen)/R1)));
            	if(x_1>=0 && y_1>=0) r1_alfa+=alfa_tmp;
            	if(x_1<0 && y_1>=0) r1_alfa=(float)(Math.PI-r1_alfa+alfa_tmp); 
            	if(x_1<0 && y_1<0) r1_alfa=(float)(Math.PI+r1_alfa+alfa_tmp);
            	if(x_1>=0 && y_1<0) r1_alfa=(float)(2*Math.PI-r1_alfa+alfa_tmp); 

            float R2 = (float)Math.sqrt((x_2-x_cen)*(x_2-x_cen)+(y_2-y_cen)*(y_2-y_cen));
            	float r2_alfa = (float)(Math.acos(Math.abs((x_2-x_cen)/R2)));
            	if(x_2>=0 && y_2>=0) r2_alfa+=alfa_tmp;
            	if(x_2<0 && y_2>=0) r2_alfa=(float)(Math.PI-r2_alfa+alfa_tmp);
            	if(x_2<0 && y_2<0) r2_alfa=(float)(Math.PI+r2_alfa+alfa_tmp);
            	if(x_2>=0 && y_2<0) r2_alfa=(float)(2*Math.PI-r2_alfa+alfa_tmp); 

            float R3 = (float)Math.sqrt((x_3-x_cen)*(x_3-x_cen)+(y_3-y_cen)*(y_3-y_cen));
            	float r3_alfa = (float)(Math.acos(Math.abs((x_3-x_cen)/R3)));
            	if(x_3>=0 && y_3>=0) r3_alfa+=alfa_tmp;
            	if(x_3<0 && y_3>=0) r3_alfa=(float)(Math.PI-r3_alfa+alfa_tmp);
            	if(x_3<0 && y_3<0) r3_alfa=(float)(Math.PI+r3_alfa+alfa_tmp);
            	if(x_3>=0 && y_3<0) r3_alfa=(float)(2*Math.PI-r3_alfa+alfa_tmp); 

            
                pointList.add(new float[]{
	            (float)(Math.cos(r0_alfa)*R0+x_cen+xd),
                (float)(Math.sin(r0_alfa)*R0+y_cen+yd),
                (float)(Math.cos(r1_alfa)*R1+x_cen+xd),
                (float)(Math.sin(r1_alfa)*R1+y_cen+yd),
                (float)(Math.cos(r2_alfa)*R2+x_cen+xd),
                (float)(Math.sin(r2_alfa)*R2+y_cen+yd),
                (float)(Math.cos(r3_alfa)*R3+x_cen+xd),
                (float)(Math.sin(r3_alfa)*R3+y_cen+yd)
                });
            
        }
        return pointList;
    }
public Vector getDati(int type, float length) {
    Vector result = new Vector();
    switch(type){
	    case 0:
	    	return getDatiX(length,length);
	    case 1:
	    	return getDatiY(length,length);		    
	    case 2:
	    	return getDatiZ(length,length);
	    default:
	    	return result; 
    }
}
public Vector getDati(int type, float lengthX, float lengthY, float lengthZ) {
    Vector result = new Vector();
    switch(type){
	    case 0:
	    	return getDatiX(lengthX,lengthY);
	    case 1:
	    	return getDatiY(lengthX,lengthY);		    
	    case 2:
	    	return getDatiZ(lengthX,lengthY);
	    default:
	    	return result; 
    }
}
private Vector getDatiX(float lengthX, float lengthY) {
    Vector result = new Vector();
    Vector sort_result = new Vector();
	Vector sort_inv = new Vector();
	
    if (datiX == null || datiX.size() == 0) return result;

	        float Radice = 0;
	        if(lengthX>lengthY) Radice = lengthY/2;
	        else Radice = lengthX/2;
	        float dRadice = Radice/1000;

	        if(datiZ!=null && datiZ.size()!=0){
				try{
					float separ = new java.math.BigDecimal((String)datiZ.elementAt(0)).floatValue();
					if(separ!=0) dRadice = Radice/separ;
				}catch(Exception e){}	
			}
	
	        Radice = Radice - dRadice;
    
	Vector colorsSh = A_chart_content._createColorsShadow(datiX.size());     
	Vector colors = A_chart_content._createColors(datiX.size());

	try {
	    boolean gonext = false;
	    double Max100 = 0;
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
            
            for (int i = 0; i < datiX.size(); i++) {
	            Max100+=Math.abs(((java.math.BigDecimal) datiX.elementAt(i)).doubleValue());
            }
            gonext=true;
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
            
            for (int i = 0; i < datiX.size(); i++) {
	            Max100+=Math.abs(((java.sql.Date) datiX.elementAt(i)).getTime());
            }
            gonext=true;
        } 
        if(gonext) { 
            double alfaStart=2*Math.PI+Math.PI/2;
            for (int i = 0; i < datiX.size(); i++){
				chart_dati_PIE_container_ADVANCED cdc = new chart_dati_PIE_container_ADVANCED();
				double alfaDelta = 0;
				if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
					cdc.setLabel(((java.math.BigDecimal) datiX.elementAt(i)).toString());
	            	alfaDelta = Math.abs(((java.math.BigDecimal) datiX.elementAt(i)).doubleValue())*Math.PI*2/Max100;
				}	
	            if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
		            cdc.setLabel(((java.sql.Date) datiX.elementAt(i)).toString());
		            alfaDelta = Math.abs(((java.sql.Date) datiX.elementAt(i)).getTime())*Math.PI*2/Max100;
		            
	            }
//	            if(i==0 && alfaDelta!=0)  	alfaStart+=alfaDelta/2;
	            if(alfaDelta==0) alfaDelta = 0.00001;
	            
				double Xd = dRadice*Math.cos(alfaStart-alfaDelta/2);
				double Yd = dRadice*Math.sin(alfaStart-alfaDelta/2);

				cdc.setXlabel((float)((Radice+dRadice+5)*Math.cos(alfaStart-alfaDelta/2)));
				cdc.setYlabel((float)((Radice+dRadice+5)*Math.sin(alfaStart-alfaDelta/2)));

				cdc.setXcontrol(0);
				cdc.setYcontrol(0);

double alfaStartControl = alfaStart;
while(alfaStartControl-alfaDelta-2*Math.PI>=0)alfaStartControl-=2*Math.PI;
					
				if(alfaStartControl>Math.PI && alfaStartControl-alfaDelta<Math.PI){

					ArrayList ar = bezierArc(	(float)(-Radice),
												(float)(-Radice),
												(float)(Radice),
												(float)(Radice),
												(float)(Math.PI*180/Math.PI),
												(float)((alfaStartControl-alfaDelta)*180/Math.PI),
												(float)Xd,
												(float)Yd);
					float pt0[] = (float [])ar.get(0);
       			
					cdc.setXcontrol((float)pt0[0]);
					cdc.setYcontrol((float)pt0[1]);
					cdc.setXcontrolL((float)pt0[0]);
					cdc.setYcontrolL((float)pt0[1]);
				}	
				if(alfaStartControl>2*Math.PI && alfaStartControl-alfaDelta<2*Math.PI){
					ArrayList ar = bezierArc(	(float)(-Radice),
												(float)(-Radice),
												(float)(Radice),
												(float)(Radice),
												(float)(2*Math.PI*180/Math.PI),
												(float)((alfaStartControl-alfaDelta)*180/Math.PI),
												(float)Xd,
												(float)Yd);
					float pt0[] = (float [])ar.get(0);
      			
					cdc.setXcontrol((float)pt0[0]);
					cdc.setYcontrol((float)pt0[1]);
					cdc.setXcontrolR((float)pt0[0]);
					cdc.setYcontrolR((float)pt0[1]);
				}	
								
					
				cdc.setXcenter((float)Xd);
				cdc.setYcenter((float)Yd);

				ArrayList ar = bezierArc(	(float)(-Radice),
											(float)(-Radice),
											(float)(Radice),
											(float)(Radice),
											(float)(alfaStartControl*180/Math.PI),
											(float)((alfaStartControl-alfaDelta)*180/Math.PI),
											(float)Xd,
											(float)Yd);
				float pt0[] = (float [])ar.get(0);
				float pt1[] = (float [])ar.get(ar.size()-1);

       			cdc.setPoints(ar);
       			
       			cdc.setX0((float)pt0[0]);
       			cdc.setY0((float)pt0[1]);
       			cdc.setX1((float)pt1[6]);
       			cdc.setY1((float)pt1[7]);

				alfaStart-=alfaDelta;
       			
				cdc.setColorTop((java.awt.Color)colors.elementAt(i));
				cdc.setColorSh((java.awt.Color)colorsSh.elementAt(i));
				cdc.setId(i);
					float min_locale = cdc.getY0();
					if(min_locale>=cdc.getY1()) min_locale = cdc.getY1();
					if(min_locale>=cdc.getYcenter()) min_locale = cdc.getYcenter();
					if(min_locale>=cdc.getYlabel()) min_locale = cdc.getYlabel();
				cdc.setMIN_DRW(min_locale);
				result.add(cdc);
            } 

        
        }
        
    } catch (Exception e) {
	    e.toString();
	}
sort_result = new util_sort().sort(result,"MIN_DRW");
for(int i=sort_result.size()-1;i>-1;i--) sort_inv.add(sort_result.elementAt(i));

return sort_inv;
    
}
private Vector getDatiY(float lengthX, float lengthY) {
    return new Vector();
}
private Vector getDatiZ(float lengthX, float lengthY) {
    return datiZ;
}
public Vector getScale(int type, int max_scale) {
    Vector result = new Vector();
    switch(type){
	    case 0:
	    	return getScaleX(max_scale);
	    case 1:
	    	return getScaleY(max_scale);		    
	    case 2:
	    	return getScaleZ(max_scale);
	    default:
	    	return result; 
    }
}
private Vector getScaleX(int max_scale) {
	float lengthX = max_scale;
	float lengthY = max_scale;	
	Vector result = new Vector();

    if (datiX == null || datiX.size() == 0) return result;
    
    int d_y_size = datiY.size(); 
    for(int i=0;i<datiX.size()-d_y_size;i++) datiY.add("");

	        float Radice = 0;
	        if(lengthX>lengthY) Radice = lengthY/2;
	        else Radice = lengthX/2;
	        float dRadice = Radice/7;
	        Radice = Radice - dRadice;
    
	Vector colors = new Vector();
	
		colors.add(java.awt.Color.red);
		colors.add(java.awt.Color.green);
		colors.add(java.awt.Color.blue);
		int coefColor = (datiX.size()>2)?60/(datiX.size()/3):60;
		int coefColor1 = (datiX.size()>2)?240/(datiX.size()/3):240;
		int ic=3;
		int icC=1;
		while(ic<datiX.size()){
								colors.add(new java.awt.Color(255-coefColor*icC,coefColor1*icC,coefColor*icC));
								ic++;
			if(ic<datiX.size()){
								colors.add(new java.awt.Color(coefColor*icC,255-coefColor*icC,coefColor1*icC));
								ic++;
			}
			if(ic<datiX.size()){
								colors.add(new java.awt.Color(coefColor1*icC,coefColor*icC,255-coefColor*icC));
								ic++;
			}	
			icC++;
		}
    
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
	        
            double Max100 = 0;
            for (int i = 0; i < datiX.size(); i++) {
	            Max100+=Math.abs(((java.math.BigDecimal) datiX.elementAt(i)).doubleValue());
            }            
             for (int i = 0; i < datiX.size(); i++){
				chart_dati_PIE_container_ADVANCED cdc = new chart_dati_PIE_container_ADVANCED();
				cdc.setColorTop((java.awt.Color)colors.elementAt(i));
				cdc.setLabel((String)datiY.elementAt(i));
	
				cdc.setLabelPerc(
					prepareContentString(formatX,String.valueOf(Math.abs(((java.math.BigDecimal) datiX.elementAt(i)).doubleValue())*100/Max100))
					);
				
				result.add(cdc);
            } 

        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
            long min = ((java.sql.Date) datiX.elementAt(0)).getTime();
            long max = ((java.sql.Date) datiX.elementAt(0)).getTime();
            
            for (int i = 1; i < datiX.size(); i++) {
                if (((java.sql.Date) datiX.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiX.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiX.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiX.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();
	        
            double Max100 = 0;
            for (int i = 0; i < datiX.size(); i++) {
	            Max100+=Math.abs(((java.sql.Date) datiX.elementAt(i)).getTime());
            }
            Max100=Max100-min;
            
        
            for (int i = 0; i < datiX.size(); i++){
				chart_dati_PIE_container_ADVANCED cdc = new chart_dati_PIE_container_ADVANCED();
				cdc.setColorTop((java.awt.Color)colors.elementAt(i));
				cdc.setLabel((String)datiY.elementAt(i));
	
				cdc.setLabelPerc(
					prepareContentString("NUMBER:###,##0.0",String.valueOf(
						Math.abs(
							((java.sql.Date) datiX.elementAt(i)).getTime()
						)*100*2/Max100)
					)
					);
				result.add(cdc);
            } 

        }

    } catch (Exception e) {}
	
    return result;
}
private Vector getScaleY(int max_scale) {
    return new Vector();
}
private Vector getScaleZ(int max_scale) {
	return new Vector();
}
public String prepareContentString(String formatSG, String value) {
	String content=value;
	java.util.StringTokenizer st = new java.util.StringTokenizer(formatSG, "|");
	while (st.hasMoreTokens()){
		String formatS = st.nextToken();
		if(formatS.length()>0){
			if (formatS.indexOf("NUMBER:")==0){
				try{
					String format = formatS.substring(7);
					java.text.DecimalFormat df = new java.text.DecimalFormat(format);
					content = df.format(new java.math.BigDecimal(content.trim()).doubleValue());
				}catch(Exception e){}
			}
			if (formatS.indexOf("DATE:")==0){ 
				try{
					String format = formatS.substring(5);
					java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
					content = df.format(new java.util.Date(util_format.stringToData(content,"yyyy-MM-dd").getTime()));
				}catch(Exception e){
					try{
						String format = formatS.substring(5);
						java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
						content = df.format(new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime()));
					}catch(Exception ex){
					}
				}
			}
		}
	}		
	return content;		
}
public Object prepareDataValue(String formatSG, String value) {
	String content=value;
	java.util.StringTokenizer st = new java.util.StringTokenizer(formatSG, "|");
	while (st.hasMoreTokens()){
		String formatS = st.nextToken();
		if(formatS.length()>0){
			if (formatS.toUpperCase().indexOf("NUMBER")==0){
				try{
					return new java.math.BigDecimal(content.trim());
				}catch(Exception e){
					return null;
				}
			}
			if (formatS.indexOf("DATE")==0){ 
				try{
					String format = "";
					if(formatS.length()>4) format = formatS.substring(5);
					java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
					java.util.Date dataResult = df.parse(value);
					return new java.sql.Date(dataResult.getTime());					
				}catch(Exception e){
					return null;
				}
			}
		}
	}		
	return content;		
}
public void reimposta() {
	super.reimposta();
}
public void setDati(int type, Object D) {
	if(type>-1 && type<3 && D!=null){
		switch (type){
			case 0:
				datiX.addElement(D);
				break;
			case 1:
				datiY.addElement(D);
				break;
			case 2:
				datiZ.addElement(D);
				break;
		}
	}
}
public void setDati(int type,String format, String value) {
	Object D = prepareDataValue(format,value);
	if(type>-1 && type<3 && value!=null){
		switch (type){
			case 0:
				datiX.addElement(D);
				break;
			case 1:
				datiY.addElement(D);
				break;
			case 2:
				datiZ.addElement(D);
				break;
		}
	}
}
public void setDati(Object X, Object Y) {
	if(X!=null && Y!=null){
		datiX.addElement(X);
		datiY.addElement(Y);
	}
}
public void setDati(Object X, Object Y, Object Z) {
	if(X!=null && Y!=null && Z!=null){
		datiX.addElement(X);
		datiY.addElement(Y);
		datiZ.addElement(Z);
	}
}
public void setFormat(int type, String format) {
	if(type>-1 && type<3 && format!=null){
		switch (type){
			case 0:
				formatX = format;
				break;
			case 1:
				formatY = format;
				break;
			case 2:
				formatZ = format;
				break;
		}
	}
}
}

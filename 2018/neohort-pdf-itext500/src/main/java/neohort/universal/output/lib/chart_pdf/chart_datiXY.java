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

import neohort.util.util_format;

public class chart_datiXY extends A_chart_dati implements I_chart_dati{

chart_datiXY() {
	super();
	reimposta();
}
public chart_datiXY(java.lang.String newFormatX,java.lang.String newFormatY,java.lang.String newFormatZ) {
	super();
	reimposta();	
	if(newFormatX!=null) formatX = newFormatX;
	if(newFormatY!=null) formatY = newFormatY;
	if(newFormatZ!=null) formatZ = newFormatZ;	
}
public Vector getDati(int type, float length) {
    Vector result = new Vector();
    switch(type){
	    case 0:
	    	return getDatiX(length);
	    case 1:
	    	return getDatiY(length);		    
	    case 2:
	    	return getDatiZ(length);
	    default:
	    	return result; 
    }
}
public Vector getDati(int type, float lengthX, float lengthY, float lengthZ) {
    Vector result = new Vector();
   	return result; 
}
public Vector getDatiX(float length) {
    Vector result = new Vector(); 
    if (datiX == null || datiX.size() == 0) return result;
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
            float min = ((java.math.BigDecimal) datiX.elementAt(0)).floatValue();
            float max = ((java.math.BigDecimal) datiX.elementAt(0)).floatValue();
            for (int i = 1; i < datiX.size(); i++) {
                if (((java.math.BigDecimal) datiX.elementAt(i)).floatValue() < min)
                    min = ((java.math.BigDecimal) datiX.elementAt(i)).floatValue();
                else {
                    if (((java.math.BigDecimal) datiX.elementAt(i)).floatValue() > max)
                    	max = ((java.math.BigDecimal) datiX.elementAt(i)).floatValue();
                }
            }
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
            float delta = length/(max - min) ;
            for (int i = 0; i < datiX.size(); i++)
                result.add(String.valueOf(((java.math.BigDecimal) datiX.elementAt(i)).floatValue()*delta));
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
            float delta = length/(max - min);
            for (int i = 0; i < datiX.size(); i++)
                result.add(String.valueOf(((java.sql.Date) datiX.elementAt(i)).getTime()*delta - min*delta));
        }
        
    } catch (Exception e) {}
    return result;
}
public Vector getDatiY(float length) {
    Vector result = new Vector();
    if (datiY == null || datiY.size() == 0) return result;
    try {
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
            float min = ((java.math.BigDecimal) datiY.elementAt(0)).floatValue();
            float max = ((java.math.BigDecimal) datiY.elementAt(0)).floatValue();
            for (int i = 1; i < datiY.size(); i++) {
                if (((java.math.BigDecimal) datiY.elementAt(i)).floatValue() < min)
                    min = ((java.math.BigDecimal) datiY.elementAt(i)).floatValue();
                else {
                    if (((java.math.BigDecimal) datiY.elementAt(i)).floatValue() > max)
                        max = ((java.math.BigDecimal) datiY.elementAt(i)).floatValue();
                }
            }
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
            float delta = length/(max - min) ;
            for (int i = 0; i < datiY.size(); i++)
                result.add(String.valueOf(((java.math.BigDecimal) datiY.elementAt(i)).floatValue()*delta));
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
            long min = ((java.sql.Date) datiY.elementAt(0)).getTime();
            long max = ((java.sql.Date) datiY.elementAt(0)).getTime();
            for (int i = 1; i < datiY.size(); i++) {
                if (((java.sql.Date) datiY.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiY.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiY.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiY.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();
            float delta = length/(max - min);
            for (int i = 0; i < datiY.size(); i++)
                result.add(String.valueOf(((java.sql.Date) datiY.elementAt(i)).getTime()*delta - min*delta));
        }
        
    } catch (Exception e) {}
    return result;
}
public Vector getDatiZ(float length) {
    Vector result = new Vector();
    if (datiZ == null || datiZ.size() == 0) return result;
    try {
        if (formatZ.trim().toUpperCase().indexOf("NUMBER")==0) {
            float min = ((java.math.BigDecimal) datiZ.elementAt(0)).floatValue();
            float max = ((java.math.BigDecimal) datiZ.elementAt(0)).floatValue();
            for (int i = 1; i < datiZ.size(); i++) {
                if (((java.math.BigDecimal) datiZ.elementAt(i)).floatValue() < min)
                    min = ((java.math.BigDecimal) datiZ.elementAt(i)).floatValue();
                else {
                    if (((java.math.BigDecimal) datiZ.elementAt(i)).floatValue() > max)
                        max = ((java.math.BigDecimal) datiZ.elementAt(i)).floatValue();
                }
            }
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
            float delta = length/(max - min) ;
            for (int i = 0; i < datiZ.size(); i++)
                result.add(String.valueOf(((java.math.BigDecimal) datiZ.elementAt(i)).floatValue()*delta));
        }
        if (formatZ.trim().toUpperCase().indexOf("DATE")==0) { 
            long min = ((java.sql.Date) datiZ.elementAt(0)).getTime();
            long max =((java.sql.Date) datiZ.elementAt(0)).getTime();
            for (int i = 1; i < datiZ.size(); i++) {
                if (((java.sql.Date) datiZ.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiZ.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiZ.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiZ.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();            
            float delta = length/(max - min);
            for (int i = 0; i < datiZ.size(); i++)
                result.add(String.valueOf(((java.sql.Date) datiZ.elementAt(i)).getTime()*delta - ((java.sql.Date) datiZ.elementAt(0)).getTime()*delta-min*delta));
        }
        
    } catch (Exception e) {}
    return result;
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
    Vector result = new Vector();
    if (datiX == null || datiX.size() == 0) return result;
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
            float min = ((java.math.BigDecimal) datiX.elementAt(0)).floatValue();
            float max = ((java.math.BigDecimal) datiX.elementAt(0)).floatValue();
            for (int i = 1; i < datiX.size(); i++) {
                if (((java.math.BigDecimal) datiX.elementAt(i)).floatValue() < min)
                    min = ((java.math.BigDecimal) datiX.elementAt(i)).floatValue();
                else {
                    if (((java.math.BigDecimal) datiX.elementAt(i)).floatValue() > max)
                        max = ((java.math.BigDecimal) datiX.elementAt(i)).floatValue();
                }
            }
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
            float delta = (max - min) / (max_scale-1);
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatX,String.valueOf(min + i * delta)));
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
            long min;
            long max;
            min = max = ((java.sql.Date) datiX.elementAt(0)).getTime();
            for (int i = 1; i < datiX.size(); i++) {
                if (((java.sql.Date) datiX.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiX.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiX.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiX.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();            
            long delta = (max - min) / (max_scale-1);
            if(delta > delta_month) formatX="DATE:MMMMM-yyyyy"; 
            if(delta > delta_year) formatX="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatX,
	                new java.sql.Date(min + i * delta).toString()));
        }
        
    } catch (Exception e) {}
    return result;
}
private Vector getScaleY(int max_scale) {
	Vector result = new Vector();
	if(datiY==null || datiY.size()==0) return result;
	try{
		if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
			float min=((java.math.BigDecimal)datiY.elementAt(0)).floatValue();
			float max=((java.math.BigDecimal)datiY.elementAt(0)).floatValue();
			for(int i=1;i<datiY.size();i++){
				if(((java.math.BigDecimal)datiY.elementAt(i)).floatValue()<min)
					min = ((java.math.BigDecimal)datiY.elementAt(i)).floatValue();
				else{
					if(((java.math.BigDecimal)datiY.elementAt(i)).floatValue()>max)
						max = ((java.math.BigDecimal)datiY.elementAt(i)).floatValue();
				}	
			}
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
			float delta = (max-min)/(max_scale-1);
			float delta_ext = delta;
			if(delta>1){
				delta_ext = new java.math.BigDecimal(delta).divide(new java.math.BigDecimal("1"),0,java.math.BigDecimal.ROUND_UP).floatValue();
			}
			for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,String.valueOf(min + i * delta_ext)));
		}
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
            long min = ((java.sql.Date) datiY.elementAt(0)).getTime();
            long max = ((java.sql.Date) datiY.elementAt(0)).getTime();
            for (int i = 1; i < datiY.size(); i++) {
                if (((java.sql.Date) datiY.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiY.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiY.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiY.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();                        
            long delta = (max - min) / (max_scale-1);
            if(delta > delta_month) formatY="DATE:MMMMM-yyyyy"; 
            if(delta > delta_year) formatY="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,
	                new java.sql.Date(min + i * delta).toString()));
        }
	}catch(Exception e){}	
	return result;
}
private Vector getScaleZ(int max_scale) {
	Vector result = new Vector();
	if(datiZ==null || datiZ.size()==0) return result;
	try{
		if (formatZ.trim().toUpperCase().indexOf("NUMBER")==0) {
			float min = ((java.math.BigDecimal)datiZ.elementAt(0)).floatValue();
			float max = ((java.math.BigDecimal)datiZ.elementAt(0)).floatValue();
			for(int i=1;i<datiZ.size();i++){
				if(((java.math.BigDecimal)datiZ.elementAt(i)).floatValue()<min)
					min = ((java.math.BigDecimal)datiZ.elementAt(i)).floatValue();
				else{
					if(((java.math.BigDecimal)datiZ.elementAt(i)).floatValue()>max)
						max = ((java.math.BigDecimal)datiZ.elementAt(i)).floatValue();
				}	
			}
            if(max==min){
	            if(max>0) min = 0;
	            else max = 0;
            }
			float delta = (max-min)/(max_scale-1);
			float delta_ext = delta;
			if(delta>1){
				delta_ext = new java.math.BigDecimal(delta).divide(new java.math.BigDecimal("1"),0,java.math.BigDecimal.ROUND_UP).floatValue();
			}
			for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,String.valueOf(min + i * delta_ext)));
		}
        if (formatZ.trim().toUpperCase().indexOf("DATE")==0) {
            long min = ((java.sql.Date) datiZ.elementAt(0)).getTime();
            long max = ((java.sql.Date) datiZ.elementAt(0)).getTime();
            for (int i = 1; i < datiZ.size(); i++) {
                if (((java.sql.Date) datiZ.elementAt(i)).getTime() < min)
                    min = ((java.sql.Date) datiZ.elementAt(i)).getTime();
                else {
                    if (((java.sql.Date) datiZ.elementAt(i)).getTime() > max)
                        max = ((java.sql.Date) datiZ.elementAt(i)).getTime();
                }
            }
            if(max==min) min = new java.sql.Date(max - delta_day).getTime();                                    
            long delta = (max - min) / (max_scale-1);
            if(delta > delta_month) formatZ="DATE:MMMMM-yyyyy"; 
            if(delta > delta_year) formatZ="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatZ,
	                new java.sql.Date(min + i * delta).toString()));
        }
	}catch(Exception e){}	
	return result;
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

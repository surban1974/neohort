/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */
package neohort.universal.output.lib.chart_advanced_pdf;


import java.util.Vector;

import neohort.universal.output.lib.chart_pdf.A_chart_dati;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;
import neohort.util.util_format;


public class chart_dati_COLUMN_ADVANCED_XY extends A_chart_dati implements I_chart_dati{

    private Vector<Object> datiX_General = new Vector<Object>();
	private Vector<Object> datiY_General = new Vector<Object>();
    public Vector<Vector<Object>> datiX_st = new Vector<Vector<Object>>();
	public Vector<Vector<Object>> datiY_st = new Vector<Vector<Object>>();


	private float minY = 0;
	private float deltaMinY = 0;
    private float maxY = 0;
    private float deltaMaxY = 0;
    private float deltaY = 0;

    private int max_min_inputX=0;
    private int max_min_inputY=0;  
    
    private int columnCurr = 0;
	
	
chart_dati_COLUMN_ADVANCED_XY() {
	super();
	reimposta();
}
public chart_dati_COLUMN_ADVANCED_XY(java.lang.String newFormatX,java.lang.String newFormatY,java.lang.String newFormatZ) {
	super();
	reimposta();	
	if(newFormatX!=null) formatX = newFormatX;
	if(newFormatY!=null) formatY = newFormatY;
	if(newFormatZ!=null) formatZ = newFormatZ;	
}
public Vector<Object> getDati(int type, float length) {
    Vector<Object> result = new Vector<Object>();
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
public Vector<Object> getDati(int type, float length, float count, float lengthZ) {
    Vector<Object> result = new Vector<Object>();
    
    try{
    	int i = new java.math.BigDecimal(count).intValue();
    	if(i>-1){
    		datiX = datiX_st.elementAt(i);
    		datiY = datiY_st.elementAt(i);
    		columnCurr = i;
    	}else{
	    	datiX = datiX_General;
	    	datiY = datiY_General;
	    	columnCurr = 0;
	    }	
    }catch(Exception e){
	    return null;
    }	
    
	switch(type){
	    case 0:
	    	return getDatiX(length);
	    case 1:
	    	return getDatiY(length);		    
	    case 2:
	    	return getDatiZ(length);
	    case 10:
	    	return getDatiX_St();
	    case 11:
	    	return getDatiY_St();		    
	    case 12:
	    	return getDatiZ_St();
	    case 20:
	    	if(deltaMinY!=0) {
	    		Vector<Object> res = new Vector<Object>();
	    		res.add(deltaMinY*deltaY);
	    		return res;
	    	}
	    	return null;	
	    	
	    default:
	    	return result; 
    }

}
public Vector<Object> getDatiX(float length) {
    Vector<Object> result = new Vector<Object>(); 
    if (datiX_General == null || datiX_General.size() == 0) return result;
    try {
	    float delta = length/datiX_General.size();
            for (int i = 0; i < datiX.size(); i++)
                result.add(String.valueOf(columnCurr*delta+i*(datiX_General.size()/datiX.size())*delta));

    } catch (Exception e) {}
    return result;
}
private Vector<Object> getDatiX_St() {
    Vector<Object> result = new Vector<Object>(); 
    if (datiX_General == null || datiX_General.size() == 0 || datiX == null || datiX.size()==0) return result;
    
    try {
            for (int i = 0; i < datiX.size(); i++)
                result.add(
	                prepareContentString(formatX,(String)datiX.elementAt(i)));

    } catch (Exception e) {}
    return result;
}
public Vector<Object> getDatiY(float length) {	
    Vector<Object> result = new Vector<Object>();
     
    if (datiY_General == null || datiY_General.size() == 0) return result;
    try {
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minY==maxY && minY==0 && maxY==0){	        
            	minY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	maxY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
                    	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	else {
                    	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
                    	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}   
          		deltaY = length/(maxY - minY);
				maxY = (long)(minY + length * deltaY);            	
            	deltaY = length/(maxY - minY);
          		
	        }else deltaY = length/(maxY - minY) ;
            for (int i = 0; i < datiY.size(); i++)
                result.add(String.valueOf(((java.math.BigDecimal) datiY.elementAt(i)).floatValue()*deltaY));
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
                    	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	else {
                    	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
                        	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();
            	deltaY = length/(maxY - minY);
            	maxY = (long)(minY + length * deltaY);
            	deltaY = length/(maxY - minY);
	        }else deltaY = length/(maxY - minY);
	        
            for (int i = 0; i < datiY.size(); i++)
                result.add(String.valueOf(((java.sql.Date) datiY.elementAt(i)).getTime()*deltaY - minY*deltaY));
        }
        
    } catch (Exception e) {}
    
    return result;
}
private Vector<Object> getDatiY_St() {
    Vector<Object> result = new Vector<Object>(); 
    if (datiY_General == null || datiY_General.size() == 0 || datiY == null || datiY.size()==0) return result;
    
    try {
            for (int i = 0; i < datiY.size(); i++)
                result.add(
	                prepareContentString(formatY,((java.math.BigDecimal)datiY.elementAt(i)).toString()));

    } catch (Exception e) {}
    return result;
}
public Vector<Object> getDatiZ(float length) {
    Vector<Object> result = new Vector<Object>();
    return result;
}
private Vector<Object> getDatiZ_St() {
    Vector<Object> result = new Vector<Object>(); 
    return result;
}
public double getDeltaScaleY(Double max_scale_fixed_Y, float h) {
    if(datiY_General.size()==0){
	    for(int i=0;i<datiY_st.size();i++){
		    for(int j=0;j<(datiY_st.elementAt(i)).size();j++) datiY_General.add((datiY_st.elementAt(i)).elementAt(j));
		}
    }    
    if (datiY_General == null || datiY_General.size() == 0) return -1;
    try {
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minY==maxY && minY==0 && maxY==0){	        
            	minY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	maxY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
                    	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	else {
                    	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
                    	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}            	
	        }
	        if(Math.abs(maxY-minY)!=0){
		        return (max_scale_fixed_Y.doubleValue()*h)/Math.abs(maxY-minY);
	        }else return -1;		        
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
                    	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	else {
                    	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
                        	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();                        	
	        }

	        if(Math.abs(maxY-minY)!=0){
		        return (max_scale_fixed_Y.doubleValue()*h)/Math.abs(maxY-minY);
	        }else return -1;		        
	         
        }
        
    } catch (Exception e) {
		return -1;	   
	}
	return -1;
}
public Vector<Object> getScale(int type, int max_scale) {
    Vector<Object> result = new Vector<Object>();
    switch(type){
	    case 0:
	    	return getScaleX(max_scale);
	    case 1:
	    	return getScaleY(max_scale,null,null,null);		    
	    case 2:
	    	return getScaleZ(max_scale);
	    default:
	    	return result; 
    }
}
public Vector<Object> getScale(int type, int max_scale, Float ScaleStep, Float Max, Float Min) {
    Vector<Object> result = new Vector<Object>();
    switch(type){
	    case 0:
	    	return getScaleX(max_scale);
	    case 1:
	    	return getScaleY(max_scale,ScaleStep,Max,Min);		    
	    case 2:
	    	return getScaleZ(max_scale);
	    default:
	    	return result; 
    }
}
private Vector<Object> getScaleX(int max_scale) {
    Vector<Object> result = new Vector<Object>();
    if(datiX_General.size()==0){
	    for(int i=0;i<datiX_st.size();i++){
		    for(int j=0;j<(datiX_st.elementAt(i)).size();j++) datiX_General.add((datiX_st.elementAt(i)).elementAt(j));
		}
    }    	
    if (datiX_General == null || datiX_General.size() == 0) return result;
    max_scale = (datiX_st.elementAt(0)).size();
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
            for (int i = 0; i < (datiX_st.elementAt(0)).size(); i++)
                result.add(prepareContentString(formatX,((java.math.BigDecimal) (datiX_st.elementAt(0)).elementAt(i)).toString()));
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
            for (int i = 0; i < (datiX_st.elementAt(0)).size(); i++)
                result.add(prepareContentString(formatX,
	               ((java.sql.Date) (datiX_st.elementAt(0)).elementAt(i)).toString()));
        }
        
    } catch (Exception e) {}
    return result;
}
private Vector<Object> getScaleY(int max_scale, Float ScaleStep, Float MaxY, Float MinY) {
    Vector<Object> result = new Vector<Object>();
    if(datiY_General.size()==0){
	    for(int i=0;i<datiY_st.size();i++){
		    for(int j=0;j<(datiY_st.elementAt(i)).size();j++) datiY_General.add((datiY_st.elementAt(i)).elementAt(j));
		}
    }    
    if (datiY_General == null || datiY_General.size() == 0) return result;
    try {
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minY==maxY && minY==0 && maxY==0){	        
            	minY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	maxY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
                    	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	else {
                    	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
                    	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}
            	
            	if(MinY!=null && MinY<minY) {
            		deltaMinY=Math.abs(minY-MinY);
            		minY=MinY;            		
            	}
            	if(MaxY!=null && MaxY>maxY) {
            		deltaMaxY=Math.abs(MaxY-maxY);
            		maxY=MaxY;            		
            	}
            	if(ScaleStep!=null) {
                	deltaY = ScaleStep;
                	float remainder = minY % deltaY;   
                	if(remainder!=0) {
                		float nminY=minY-remainder-deltaY;
                		deltaMinY=Math.abs(nminY-minY);
                		minY=nminY;
                	}
                	remainder = maxY % deltaY;
                	if(remainder!=0) {
                		float nmaxY=maxY-remainder+deltaY;
                		deltaMaxY=Math.abs(nmaxY-maxY);
                		maxY=nmaxY;
                	}
                	
                	max_scale = (int)((maxY - minY) / deltaY)+1;           		
            	}else {
                	deltaY = (maxY - minY) / (max_scale-1);
//               	maxY = minY + max_scale * deltaY;
                	maxY = minY + (max_scale-1) * deltaY;
                	deltaY = (maxY - minY) / (max_scale-1);           		
            	}

	        }else { 
	        	if(ScaleStep!=null)
	        		deltaY = ScaleStep;
	        	else
	        		deltaY = (maxY - minY) / (max_scale-1);
	        }

	            
            for (int i = 0; i < max_scale; i++)
               result.add(prepareContentString(formatY,String.valueOf(minY + i * deltaY)));
 	            
 	            

        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
                    	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	else {
                    	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
                        	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();

            	deltaY = (maxY - minY) / (max_scale-1);
//				maxY = (long)(minY + max_scale * deltaY); 
				maxY = (long)(minY + (max_scale-1) * deltaY);
            	deltaY = (maxY - minY) / (max_scale-1);
	        }else deltaY = (maxY - minY) / (max_scale-1);
	        
            if(deltaY > delta_month) formatY="DATE:MMMMM-yyyyy"; 
            if(deltaY > delta_year) formatY="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,
	                new java.sql.Date((long)(minY + i * deltaY)).toString()));

            
                
        }
        
    } catch (Exception e) {}
    return result;
}
public Vector<Object> getScaleY(Double max_scale_fixed_Y) { 
    Vector<Object> result = new Vector<Object>();
    if(datiY_General.size()==0){
	    for(int i=0;i<datiY_st.size();i++){
		    for(int j=0;j<(datiY_st.elementAt(i)).size();j++) datiY_General.add((datiY_st.elementAt(i)).elementAt(j));
		}
    }    
    if (datiY_General == null || datiY_General.size() == 0) return result;
    try {
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minY==maxY && minY==0 && maxY==0){	        
            	minY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	maxY = ((java.math.BigDecimal) datiY_General.elementAt(0)).floatValue();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
                    	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	else {
                    	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
                    	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}            	
	        }
		        double st = minY;
	            while(st<maxY){
 	               result.add(prepareContentString(formatY,String.valueOf(st)));
 	               st+=max_scale_fixed_Y.doubleValue();
	            }
		        
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
                    	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	else {
                    	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
                        	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();                        	
	        }

		        double st = minY;
	            while(st<maxY){
 	               result.add(prepareContentString(formatY,new java.sql.Date((long)st).toString()));
 	               st+=max_scale_fixed_Y.doubleValue();
	            }
	         
        }
        
    } catch (Exception e) {}
    return result;
}
private Vector<Object> getScaleZ(int max_scale) {
	Vector<Object> result = new Vector<Object>();
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
}
public void setDati(int type,String format, String value) {
	if(value.trim().equals("|")){
		if(type>-1 && type<3){
			switch (type){
				case 0:
					if(datiX.size()>0){
						Vector<Object> buf = new Vector<Object>();
						for(int i=0;i<datiX.size();i++) buf.add(datiX.elementAt(i));
						datiX_st.add(buf);
					}	
					datiX = new Vector<Object>();
					break;
				case 1:
					if(datiY.size()>0){
						Vector<Object> buf = new Vector<Object>();
						for(int i=0;i<datiY.size();i++) buf.add(datiY.elementAt(i));
						datiY_st.add(buf);
					}	
					datiY = new Vector<Object>();
				case 2:
					break;
			}
		}
		
	}else{				
		if(type>-1 && type<3 && value!=null){
			switch (type){
				case 0:
					if(	max_min_inputX == 0 || max_min_inputX == 1){
						setMaxMinX(value,max_min_inputX);
						max_min_inputX++;
					}
					else{
						datiX.addElement(prepareDataValue(format,value));
					}	
					break;
				case 1:
					if(	max_min_inputY == 0 || max_min_inputY == 1){
						setMaxMinY(value,max_min_inputY);
						max_min_inputY++;
					}
					else{
						datiY.addElement(prepareDataValue(format,value));
					}	
					break;
				case 2:
					datiZ.addElement(prepareDataValue(format,value));
					break;
			}
		}
	}	
}
public void setDati(Object X, Object Y) {
}
public void setDati(Object X, Object Y, Object Z) {
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
public void setMaxMinX(String value,int max_min_input) {
	if(value.trim().equals("-")){
	    return;
     }
}
public void setMaxMinY(String value,int max_min_input) {
	if(value.trim().equals("-")){
		switch(max_min_input){
			case 0:		        	
		  		minY = 0;	
	 	 	   	break;
	  	   	case 1:
	      		maxY = 0;	
	       		break;	
       	}
	    return;
     }
	
        if (formatY.trim().toUpperCase().indexOf("NUMBER")==0) {
	        Object D = prepareDataValue(formatY,value);
	        switch(max_min_input){
		        case 0:		        	
		        	minY = ((java.math.BigDecimal) D).floatValue();	
		        	break;
		        case 1:
		        	maxY = ((java.math.BigDecimal) D).floatValue();	
		        	break;	
	        }
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        Object D = prepareDataValue(formatY,value);
	        switch(max_min_input){
		        case 0:		        	
		        	minY = ((java.sql.Date)D).getTime();
		        	break;
		        case 1:
		        	maxY = ((java.sql.Date)D).getTime();
		        	break;	
	        }
        }
}
public void setMaxMinZ(String value,int max_min_input) {
}
}

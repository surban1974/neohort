/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */
package neohort.universal.output.lib.chart_advanced_pdf;



import java.util.Vector;

import neohort.universal.output.lib.chart_pdf.A_chart_dati;
import neohort.universal.output.lib.chart_pdf.I_chart_dati;
import neohort.util.util_format;




public class chart_dati_LINE_ADVANCED_XY extends A_chart_dati implements I_chart_dati{

    private Vector<Object> datiX_General = new Vector<Object>();
	private Vector<Object> datiY_General = new Vector<Object>();
    public Vector<Vector<Object>> datiX_st = new Vector<Vector<Object>>();
	public Vector<Vector<Object>> datiY_st = new Vector<Vector<Object>>();

	private float minX = 0;
    private float maxX = 0;
    private float deltaX = 0;

	private float minY = 0;
    private float maxY = 0;
    private float deltaY = 0;

    private int max_min_inputX=0;
    private int max_min_inputY=0;    

 
    private Vector<Object> deltaXYZ; 
    	
	
chart_dati_LINE_ADVANCED_XY() {
	super();
	reimposta();
}
public chart_dati_LINE_ADVANCED_XY(java.lang.String newFormatX,java.lang.String newFormatY,java.lang.String newFormatZ) {
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
    	}else{
	    	if(i==-2){
		    	return deltaXYZ;
	    	}else{
		    	datiX = datiX_General;
		    	datiY = datiY_General;
	    	}	
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
	    default:
	    	return result; 
    }

}
public Vector<Object> getDatiX(float length) {
    Vector<Object> result = new Vector<Object>();  
    if (datiX_General == null || datiX_General.size() == 0) return result;
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minX==maxX && minX==0 && maxX==0){
	            minX = ((java.math.BigDecimal) datiX_General.elementAt(0)).floatValue();
 	           	maxX = ((java.math.BigDecimal) datiX_General.elementAt(0)).floatValue();
  	          	for (int i = 1; i < datiX_General.size(); i++) {
					if(	datiX_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX_General.elementAt(i)).equals("#")){
			            }else{        	
	 	  	             	if (((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue() < minX)
 	 		  	            	minX = ((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue();
     			           	else {
      			   		        if (((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue() > maxX)
       	 			            	maxX = ((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue();
     			           	}        	
       	        	}
            	}
            	if(maxX==minX){
	            	if(maxX>0) minX = 0;
	            	else maxX = 0;
            	}            	
	        }
	        deltaX = length/(maxX - minX) ;
	        float min_with_0 = minX*deltaX;
	        float max_with_0 = maxX*deltaX;	        
            for (int i = 0; i < datiX.size(); i++){
	            try{
		            float d_curr = ((java.math.BigDecimal) datiX.elementAt(i)).floatValue()*deltaX;
//		            if (d_curr<min_with_0) min_with_0 = d_curr;
					if (d_curr<min_with_0 || d_curr>max_with_0) result.add("#"); 
	  	            else result.add(String.valueOf(d_curr));
	            }catch(Exception e){
		            if(	datiX.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX.elementAt(i)).equals("#"))
		            result.add("#"); 
		        }      
            }
            deltaXYZ.set(0, new java.math.BigDecimal(min_with_0).toString());
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
   	        if(minX==maxX && minX==0 && maxX==0){
            	minX = ((java.sql.Date) datiX_General.elementAt(0)).getTime();
            	maxX = ((java.sql.Date) datiX_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiX_General.size(); i++) {
					if(	datiX_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX_General.elementAt(i)).equals("#")){
			            }else{ 	            	
                			if (((java.sql.Date) datiX_General.elementAt(i)).getTime() < minX)
                    			minX = ((java.sql.Date) datiX_General.elementAt(i)).getTime();
                			else {
                    			if (((java.sql.Date) datiX_General.elementAt(i)).getTime() > maxX)
                        			maxX = ((java.sql.Date) datiX_General.elementAt(i)).getTime();
                			}
			            }		
            	}
            	if(maxX==minX) minX = new java.sql.Date((long)maxX - delta_day).getTime();                        	
   	        }
   	        deltaX = length/(maxX - minX);
	        float min_with_0 = 0;
	        float max_with_0 = maxX*deltaX;	        
            for (int i = 0; i < datiX.size(); i++){
	            try{
		            float d_curr = (((java.sql.Date) datiX.elementAt(i)).getTime() -minX) *deltaX;		            
//		            if (d_curr<min_with_0) min_with_0 = d_curr;
					if (d_curr<min_with_0 || d_curr>max_with_0) result.add("#"); 
  	              	else result.add(String.valueOf(d_curr));
	            }catch(Exception e){
		            if(	datiX.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX.elementAt(i)).equals("#")) 
		            result.add("#"); 
		        }      
                
            }
            deltaXYZ.set(0, new java.math.BigDecimal(min_with_0).toString());
        }
        
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
					if(	datiY_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY_General.elementAt(i)).equals("#")){
			            }else{	            	
		                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
  			                  	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
     			           	else {
        		            	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
          				          	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
     			           	}      	
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}            	
	        }
	        deltaY = length/(maxY - minY) ;
	        float min_with_0 = minY*deltaY;	  
	        float max_with_0 = maxY*deltaY; 
            for (int i = 0; i < datiY.size(); i++){
	            try{
		            float d_curr = ((java.math.BigDecimal) datiY.elementAt(i)).floatValue()*deltaY;
//		            if (d_curr<min_with_0) min_with_0 = d_curr;
					if (d_curr<min_with_0 || d_curr>max_with_0) result.add("#"); 
  	              	else result.add(String.valueOf(d_curr));
	            }catch(Exception e){
		            if(	datiY.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY.elementAt(i)).equals("#"))
		            result.add("#"); 
		        }      
                
            }
            deltaXYZ.set(1, new java.math.BigDecimal(min_with_0).toString());
	        
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
					if(	datiY_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY_General.elementAt(i)).equals("#")){
			            }else{	            	
		                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
  			                  	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
     			           	else {
        		            	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
          			              	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
     			           	}      	
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();                        	
	        }
	        deltaY = length/(maxY - minY);
	        float max_with_0 = maxY*deltaY;
	        float min_with_0 = 0;	        
            for (int i = 0; i < datiY.size(); i++){
	            try{
		            float d_curr = ((java.sql.Date) datiY.elementAt(i)).getTime()*deltaY - minY*deltaY;
//		            if (d_curr<min_with_0) min_with_0 = d_curr;
					if (d_curr<min_with_0 || d_curr>max_with_0) result.add("#");  
  	              	else result.add(String.valueOf(d_curr));
	            }catch(Exception e){
		            if(	datiY.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY.elementAt(i)).equals("#"))
		            result.add("#"); 
		        }      
                
            }
            deltaXYZ.set(1, new java.math.BigDecimal(min_with_0).toString());
        }
        
    } catch (Exception e) {}
    return result;
}
public Vector<Object> getDatiZ(float length) {
    Vector<Object> result = new Vector<Object>();
    try{
        for (int i = 0; i < datiZ.size(); i++)
        	result.add(String.valueOf(((java.math.BigDecimal) datiZ.elementAt(i)).floatValue()));
    }catch(Exception e){}    	
    return result;
}
public Vector<Object> getScale(int type, int max_scale) {
    Vector<Object> result = new Vector<Object>();
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
private Vector<Object> getScaleX(int max_scale) {
    Vector<Object> result = new Vector<Object>();
    if(datiX_General.size()==0){
	    for(int i=0;i<datiX_st.size();i++){
		    for(int j=0;j<(datiX_st.elementAt(i)).size();j++) datiX_General.add((datiX_st.elementAt(i)).elementAt(j));
		}
    }    	
    if (datiX_General == null || datiX_General.size() == 0) return result;
    try {
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
	        if(minX==maxX && minX==0 && maxX==0){
	            minX = ((java.math.BigDecimal) datiX_General.elementAt(0)).floatValue();
 	           	maxX = ((java.math.BigDecimal) datiX_General.elementAt(0)).floatValue();
  	          	for (int i = 1; i < datiX_General.size(); i++) {
					if(	datiX_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX_General.elementAt(i)).equals("#")){
			            }else{	            	
		   	             	if (((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue() < minX)
  			  	            	minX = ((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue();
     			           	else {
      			   		        if (((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue() > maxX)
       	 			            	maxX = ((java.math.BigDecimal) datiX_General.elementAt(i)).floatValue();
     			           	}    	
       	        	}
            	}
            	if(maxX==minX){
	            	if(maxX>0) minX = 0;
	            	else maxX = 0;
            	}            	
	        }
	        deltaX = (maxX - minX) / (max_scale-1) ;
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatX,String.valueOf(minX + i * deltaX)));
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
   	        if(minX==maxX && minX==0 && maxX==0){
            	minX = ((java.sql.Date) datiX_General.elementAt(0)).getTime();
            	maxX = ((java.sql.Date) datiX_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiX_General.size(); i++) {
					if(	datiX_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiX_General.elementAt(i)).equals("#")){
			            }else{	            	
		                	if (((java.sql.Date) datiX_General.elementAt(i)).getTime() < minX)
  			                  	minX = ((java.sql.Date) datiX_General.elementAt(i)).getTime();
     			           	else {
        		            	if (((java.sql.Date) datiX_General.elementAt(i)).getTime() > maxX)
          			              	maxX = ((java.sql.Date) datiX_General.elementAt(i)).getTime();
     			           	}      	
                	}
            	}
            	if(maxX==minX) minX = new java.sql.Date((long)maxX - delta_day).getTime();                        	
   	        }
            deltaX = (maxX - minX) / (max_scale-1);
//            if(deltaX > delta_month) formatX="DATE:MMMMM-yyyyy"; 
//            if(deltaX > delta_year) formatX="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatX,
	                new java.sql.Date((long)(minX + i * deltaX)).toString()));
        }
        
    } catch (Exception e) {}
    return result;
}
private Vector<Object> getScaleY(int max_scale) {
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
					if(	datiY_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY_General.elementAt(i)).equals("#")){
			            }else{	            	
		                	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() < minY)
  			                  	minY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
     			           	else {
        		            	if (((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue() > maxY)
          				          	maxY = ((java.math.BigDecimal) datiY_General.elementAt(i)).floatValue();
     			           	}          	
                	}
            	}
            	if(maxY==minY){
	            	if(maxY>0) minY = 0;
	            	else maxY = 0;
            	}            	
	        }
            deltaY = (maxY - minY) / (max_scale-1);
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,String.valueOf(minY + i * deltaY)));
        }
        if (formatY.trim().toUpperCase().indexOf("DATE")==0) {
	        if(minY==maxY && minY==0 && maxY==0){
            	minY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	maxY = ((java.sql.Date) datiY_General.elementAt(0)).getTime();
            	for (int i = 1; i < datiY_General.size(); i++) {
					if(	datiY_General.elementAt(i) instanceof java.lang.String &&
			            ((String)datiY_General.elementAt(i)).equals("#")){
			            }else{	            	
		                	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() < minY)
  			                  	minY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
     			           	else {
        		            	if (((java.sql.Date) datiY_General.elementAt(i)).getTime() > maxY)
          			              	maxY = ((java.sql.Date) datiY_General.elementAt(i)).getTime();
     			           	}      	
                	}
            	}
            	if(maxY==minY) minY = new java.sql.Date((long)maxY - delta_day).getTime();                        	
	        }
            deltaY = (maxY - minY) / (max_scale-1);
//            if(deltaY > delta_month) formatY="DATE:MMMMM-yyyyy"; 
//            if(deltaY > delta_year) formatY="DATE:yyyyy";
            for (int i = 0; i < max_scale; i++)
                result.add(prepareContentString(formatY,
	                new java.sql.Date((long)(minY + i * deltaY)).toString()));
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
	if(value!=null && value.length()==1 && value.equals("#")) return content;
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
	deltaXYZ = new Vector<Object>();
	deltaXYZ.add("0");
	deltaXYZ.add("0");
	deltaXYZ.add("0");
	
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
		switch(max_min_input){
			case 0:		        	
		  		minX = 0;
	 	 	   	break;
	  	   	case 1:
	      		maxX = 0;	
	       		break;	
       	}
	    return;
     }
	
        if (formatX.trim().toUpperCase().indexOf("NUMBER")==0) {
	        Object D = prepareDataValue(formatX,value);
	        switch(max_min_input){
		        case 0:		        	
		        	minX = ((java.math.BigDecimal) D).floatValue();	
		        	break;
		        case 1:
		        	maxX = ((java.math.BigDecimal) D).floatValue();	
		        	break;	
	        }
        }
        if (formatX.trim().toUpperCase().indexOf("DATE")==0) {
	        Object D = prepareDataValue(formatX,value);
	        switch(max_min_input){
		        case 0:		        	
		        	minX = ((java.sql.Date)D).getTime();
		        	break;
		        case 1:
		        	maxX = ((java.sql.Date)D).getTime();
		        	break;	
	        }
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

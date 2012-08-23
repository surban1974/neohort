package neohort.util;

import java.util.*; 
import java.sql.Timestamp;
import java.text.*;
public class util_format {
	final static public Locale ITALIAN = new Locale("it","IT");
	final static public Locale ENGLISH = new Locale("en","EN");
	final static public Locale JAPANESE = new Locale("ja","JA");
	final static public int EUROPEAN = 0;
	final static public int ANGLOAMERICAN = 1;
	final static String[] Cifra = {"","","uno","due","tre","quattro","cinque","sei","sette","otto","nove","dieci","undici","dodici","tredici","quattordici","quindici","sedici","diciassette","diciotto","diciannove","venti","trenta","quaranta","cinquanta","sessanta","settanta","ottanta","novanta"};

	private static int anno;
	private static int mese;
	private static int giorno;
public util_format() {
	super();
	Calendar c = Calendar.getInstance();
	Date d = new Date();
	c.setTime(d);
	anno = c.get(Calendar.YEAR);
	mese = c.get(Calendar.MONTH);
	giorno = c.get(Calendar.DAY_OF_MONTH);
}
public util_format(Date data) {
	super();
	Calendar c = Calendar.getInstance();
	c.setTime(data);
	anno = c.get(Calendar.YEAR);
	mese = c.get(Calendar.MONTH);
	giorno = c.get(Calendar.DAY_OF_MONTH);
}

public util_format(java.sql.Date data) {
	super();
	Calendar c = Calendar.getInstance();
	c.setTime(data);
	anno = c.get(Calendar.YEAR);
	mese = c.get(Calendar.MONTH);
	giorno = c.get(Calendar.DAY_OF_MONTH);
}


public  static  String dataToString( Object data, String formato) {
	if(data==null || formato==null) return "";
	if(data instanceof java.util.Date) return dataToString( (java.util.Date)data, formato);
	if(data instanceof java.sql.Date) return dataToString( (java.sql.Date)data, formato);
	return "";
}

public  static  String dataToString( java.util.Date data, String formato) {
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formato,ITALIAN);
	String result=null;
	result = sdf.format(data);
//	if(result.equals("0001-01-01")) result="1-01-01";
	return  result;
}
public  static  String dataToString( java.util.Date data, String formato, Locale loc) {
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formato,loc);
	String result=null;
	result = sdf.format(data);
	return  result;
}



public  static  String dataToString( java.sql.Date data, String formato) {
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formato,ITALIAN);
	String result=null;
	result = sdf.format(data);
//	if(result.equals("0001-01-01")) result="1-01-01";
	return  result;
}
public  static  String dataToString( java.sql.Date data, String formato, Locale loc) {
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formato,loc);
	String result=null;
	result = sdf.format(data);
	return  result;
}

public static int getAnno() {
	return anno;
}
public static int getGiorno() {
	return giorno;
}
public static int getMese() {
	return mese;
}
public static int getUltimoGiorno(int mese, int anno) {
	int ultimo = 28;
	java.util.Calendar data = Calendar.getInstance();
	mese--; // in java.util.Calendar i mesi sono calcolati da 0 a 11
	data.set(anno,mese,ultimo);
	while (data.get(Calendar.MONTH) == mese) { // il passaggio al mese successivo indica che ho superato l'ultimo giorno del mese
		ultimo++;
		data.add(Calendar.DAY_OF_MONTH,+1);
	}
	return --ultimo;
}
private static void preParser(String data, int style) throws ParseException {

	int dd = 0;// giorno
	int mm = 0;// mese
	int yy = 0;// anno
	int index = 0;// parse position
	try {

		data = data.trim();
		while (!Character.isDigit(data.charAt(index)) ) index++;// toglie i caratteri che non sono numeri

		if ( data.length()<3 ) throw new ParseException("Data errata",data.length());

		try {
			if ( data.length()==3) {
				dd = Integer.parseInt( data.substring(0,1) );//giorno
				mm = Integer.parseInt( data.substring(1,2) );//mese
				yy = Integer.parseInt( data.substring(2,3) );//anno
			}
			if ( data.length()==4) {
				dd = Integer.parseInt( data.substring(0,1) );//giorno
				mm = Integer.parseInt( data.substring(1,2) );//mese
				yy = Integer.parseInt( data.substring(2,4) );//anno
			}
		} catch (NumberFormatException ex) { throw new ParseException("Data errata",0); }

		if ( data.length()>4) {
			// giorno
			try {
				dd = Integer.parseInt( data.substring(index,index+2) );
				index +=2;
			} catch (NumberFormatException e) {
				try {
					dd = Integer.parseInt( data.substring(index,index+1) );
					index +=1;
				} catch (NumberFormatException ex) { throw new ParseException("Giorno errato",index); }
			}// fine giorno

			while (!Character.isDigit(data.charAt(index)) ) index++;// toglie i caratteri che non sono numeri

			// mese
			try {
				mm = Integer.parseInt( data.substring(index,index+2) );
				index +=2;
			} catch (NumberFormatException e) {
				try {
					mm = Integer.parseInt( data.substring(index,index+1) );
					index +=1;
				} catch (NumberFormatException ex) { throw new ParseException("Mese errato",index); }
			}// fine mese

			while ( !Character.isDigit(data.charAt(index)) ) index++;// toglie i caratteri che non sono numeri

			//anno
			for (int count=4;count>=1;count--) {
				try {
					yy = Integer.parseInt( data.substring(index,index+count) );
					break;
				} catch (NumberFormatException e) { continue;
				} catch (StringIndexOutOfBoundsException e) { continue; }
			}
			if (yy==0) 	throw new ParseException("Anno errato",index);
			// fine anno

		} // fine (data.length()>4)

	} catch(StringIndexOutOfBoundsException e) { throw new ParseException("Data errata",index); }

	// validazione
	try {
		switch (style) {
			case 0:
				setAnno(yy);
				setMese(mm);
				setGiorno(dd);
				break;
			case 1:
				setAnno(yy);
				setMese(dd);
				setGiorno(mm);
				break;
		}
	} catch (Exception e) { throw new ParseException("Data errata",0); }
	if ( !validate() ) throw new ParseException("Data errata",0);
}
public static void setAnno(int newValue) throws Exception {
	if ( newValue<1 || newValue>3000 ) throw new Exception();
	if (newValue<100 ) newValue += (newValue<50) ? 2000 : 1900;
	if (newValue>=100 && newValue<1000 ) newValue += 1000;
	anno = newValue;
}
public static void setGiorno(int newValue) throws Exception {
	if ( newValue<1 || newValue>31 ) throw new Exception();
	giorno = newValue;
}
public static void setMese(int newValue) throws Exception {
	if ( newValue<1 || newValue>12 ) throw new Exception();
	mese = newValue;
}
public static java.util.Date stringToData(String data, int style) throws Exception {
	if ( style<0 || style >1 || data==null|| data.trim().equals("") ) throw new Exception();
	try { preParser(data,style); } catch (ParseException e) { throw new Exception(); }
	data = ""+getGiorno()+" "+getMese()+" "+getAnno();
	return stringToData(data,"dd MM yyyy");

}
public static java.util.Date stringToData ( String data, String formato) throws Exception {
	SimpleDateFormat sdf = new SimpleDateFormat(formato,ITALIAN);
	ParsePosition pos = new ParsePosition(0);
	Date dataResult = null;
	try {
		dataResult = sdf.parse(data,pos);
	} catch(Exception e) {
		throw new Exception();
	}
	if ( pos.getIndex()==0 || dataResult==null ) throw new Exception();
	return dataResult;
}
public static java.sql.Date stringToSqlData ( String data, String formato, Locale loc) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat(formato,loc);
	ParsePosition pos = new ParsePosition(0);
	Date dataResult = null;
	try {
		dataResult = sdf.parse(data,pos);
	} catch(Exception e) {
		throw new ParseException("Errore in stringToData.",0);
	}
	if ( pos.getIndex()==0 || dataResult==null ) throw new ParseException("Errore in stringToData.",0);

	return new java.sql.Date(dataResult.getTime());
}

public static java.sql.Date stringToSqlData ( String data, String formato) throws Exception {
	SimpleDateFormat sdf = new SimpleDateFormat(formato,ITALIAN);
	ParsePosition pos = new ParsePosition(0);
	Date dataResult = null;
	try {
		dataResult = sdf.parse(data,pos);
	} catch(Exception e) {
		throw new Exception();
	}
	if ( pos.getIndex()==0 || dataResult==null ) throw new Exception();
	return new java.sql.Date(dataResult.getTime());
}

public String toString() {
	// Insert code to print the receiver here.
	// This implementation forwards the message to super. You may replace or supplement this.
	return ""+giorno+" "+mese+" "+anno;
}
public static boolean validate() {
	Calendar c = Calendar.getInstance();
	Date d = new Date();
	int a,m,g;

	c.set(anno,mese-1,giorno);
	d = c.getTime();
	c.setTime(d);
	a = c.get(Calendar.YEAR);
	m = c.get(Calendar.MONTH);
	g = c.get(Calendar.DAY_OF_MONTH);

	if ( a==anno && m==mese-1 && g==giorno ) return true;
	return false;
}
public static String convertAp(String value){
	String result="";
	if(value==null) return result;
	for(int i=0;i<value.length();i++){
		if(value.charAt(i)=='\'') result+="''";
		else result+=value.charAt(i);
	}
	return result;
}

public static Timestamp stringToTimestamp(String timestamp) {	
	
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new java.util.Date());
	if (timestamp==null || timestamp.length()==0) return new Timestamp(calendar.getTime().getTime()); 

	
	if(timestamp.length()<26){
		int length = timestamp.length();
		for(int i=0;i<26-length;i++) timestamp+="0";
	}
	try{
		calendar.set(Calendar.YEAR,Integer.valueOf(timestamp.substring(0,4)).intValue()-1900);
	}catch(Exception e){}
	try{	
		calendar.set(Calendar.MONTH,Integer.valueOf(timestamp.substring(5,7)).intValue()-1);
	}catch(Exception e){}
	try{	
		calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf(timestamp.substring(8,10)).intValue());
	}catch(Exception e){}
	try{	
		calendar.set(Calendar.HOUR_OF_DAY,Integer.valueOf(timestamp.substring(11,13)).intValue());
	}catch(Exception e){}
	try{	
		calendar.set(Calendar.MINUTE,Integer.valueOf(timestamp.substring(14,16)).intValue());
	}catch(Exception e){}
	try{	
		calendar.set(Calendar.SECOND,Integer.valueOf(timestamp.substring(17,19)).intValue());
	}catch(Exception e){}
	
	Timestamp result = new Timestamp(calendar.getTime().getTime());
	
	try{	
		result.setNanos(Integer.valueOf(timestamp.substring(20)).intValue());
	}catch(Exception e){}
	return result;
}
	
public static String timestampToString(java.sql.Timestamp tmp) {
	if(tmp==null) return "9999-12-31-00.00.00.000000";
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(tmp.getTime());
		String 	res = "";
		if(calendar.get(Calendar.YEAR)>999)						
			res+=String.valueOf(calendar.get(Calendar.YEAR))+"-";
		else{
			boolean end = false;
			if(calendar.get(Calendar.YEAR)<10){
				res+="000"+String.valueOf(calendar.get(Calendar.YEAR))+"-";
				end=true;
			}
			if(!end && calendar.get(Calendar.YEAR)<100){
				res+="00"+String.valueOf(calendar.get(Calendar.YEAR))+"-";
				end=true;
			}
			if(!end && calendar.get(Calendar.YEAR)<1000){
				res+="0"+String.valueOf(calendar.get(Calendar.YEAR))+"-";
				end=true;
			}
		}	
	calendar.get(Calendar.YEAR);
		
		res+=((calendar.get(Calendar.MONTH)<9)?"0"+String.valueOf(calendar.get(Calendar.MONTH)+1):String.valueOf(calendar.get(Calendar.MONTH)+1))+"-";
		res+=((calendar.get(Calendar.DAY_OF_MONTH)<10)?"0"+String.valueOf(calendar.get(Calendar.DATE)):String.valueOf(calendar.get(Calendar.DATE)))+"-";
		res+=((calendar.get(Calendar.HOUR_OF_DAY)<10)?"0"+String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)):String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)))+".";
		res+=((calendar.get(Calendar.MINUTE)<10)?"0"+String.valueOf(calendar.get(Calendar.MINUTE)):String.valueOf(calendar.get(Calendar.MINUTE)))+".";
		res+=((calendar.get(Calendar.SECOND)<10)?"0"+String.valueOf(calendar.get(Calendar.SECOND)):String.valueOf(calendar.get(Calendar.SECOND)))+".";
		try{
			res+=String.valueOf(tmp.getNanos()).substring(0,6);
		}catch(Exception e){
			res+="000000";
		}
	return res;
}


public static String prepareContentString(String formatSG, String content) {
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
					content = df.format(new java.util.Date(stringToData(content,"yyyy-MM-dd").getTime()));
				}catch(Exception e){
					try{
						String format = formatS.substring(5);
						java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
						content = df.format(new java.util.Date(java.text.DateFormat.getDateInstance().parse(content).getTime()));
					}catch(Exception ex){
					}
				}
			}
			if (formatS.indexOf("ISNULL:")==0){ 
				try{
					String format = formatS.substring(7);
					if (content.trim().equals("0")) content = format;
					else{
						if (new java.math.BigDecimal(content).doubleValue()==0) content = format;
					}									
				}catch(Exception e){}
			}
			if (formatS.indexOf("NOTNULL:")==0){ 
				try{
					String format = formatS.substring(8);
					if (content.trim().equals("")) content = format;
				}catch(Exception e){}
			}
			if (formatS.indexOf("TRIM:")>-1){
				try{
					content = content.trim();
				}catch(Exception e){}	
			}
			if (formatS.indexOf("UPPERCASE:")>-1){
				try{
					content = content.toUpperCase();
				}catch(Exception e){}	
			}
			if (formatS.indexOf("LOWERCASE:")>-1){
				try{
					content = content.toLowerCase();
				}catch(Exception e){}	
			}
			if (formatS.indexOf("SUBSTRING:")>-1){
				try{
					String format = formatS.substring(10+formatS.indexOf("SUBSTRING:"));					
					content = content.substring(0,Integer.valueOf(format).intValue());
				}catch(Exception e){}	
			}
			if (formatS.indexOf("REPLACE:")>-1){
				try{
					String format = formatS.substring(8);
					if(format.charAt(0)=='[' && format.charAt(format.length()-1)==']'){						
						java.util.StringTokenizer stf = new java.util.StringTokenizer(format, "--");
						String vFirst = stf.nextToken();
						String vSecond = stf.nextToken();
						content = replace(content, vFirst,vSecond);	
					}	
					content = content.substring(0,Integer.valueOf(format).intValue());
				}catch(Exception e){}	
			}
			
		}
	}		
	return content;		
}

public static String makeFormatedString(String format, Object ref) throws Exception{
	if(format==null) return ref.toString();	
	if(ref.getClass().isPrimitive()) return ref.toString();
	if(	!(ref instanceof java.sql.Date) &&
		!(ref instanceof java.util.Date) &&
		!(ref instanceof java.sql.Timestamp) &&
		!(ref instanceof String)
	){
		try{
			try{
//				return new DecimalFormat(format).parse(ref.toString().trim()).toString();
				return new DecimalFormat(format).format(new java.math.BigDecimal(ref.toString()).doubleValue());
			}catch(Exception e){}	
		}catch(Exception e){
			return ref.toString();
		}	
	}	
	if(ref instanceof java.sql.Date){
		try{
			return dataToString((java.sql.Date)ref, format);
		}catch(Exception e){}
	}	
	if(ref instanceof Timestamp){
		try{
			return dataToString(new java.sql.Date(((Timestamp)ref).getTime()), format);
		}catch(Exception e){}
	}
	if(ref instanceof java.util.Date){
		try{
			return util_format.dataToString((java.util.Date)ref, format);
		}catch(Exception e){}
	}
	if(ref instanceof String){
		try{
			try{
				return new DecimalFormat(format).format(new java.math.BigDecimal(ref.toString()).doubleValue());
			}catch(Exception e){}	
		}catch(Exception e){
			return ref.toString();
		}			
	}
	return ref.toString();		
}


public static String replace (String target, String from, String to) {   
	int start = target.indexOf (from);
  	if (start==-1) return target;
  	int lf = from.length();
  	char [] targetChars = target.toCharArray();
  	StringBuffer buffer = new StringBuffer();
  	int copyFrom=0;
  	while (start != -1) {
		buffer.append (targetChars, copyFrom, start-copyFrom);
		buffer.append (to);
		copyFrom=start+lf;
		start = target.indexOf (from, copyFrom);
	}
  	buffer.append (targetChars, copyFrom, targetChars.length-copyFrom);
  	return buffer.toString();
}

public static String normaliseXMLText(String input) {
	String result="";
	if(	input.indexOf("&")>-1 ||
			input.indexOf("\\")>-1 ||
			input.indexOf(">")>-1 ||
			input.indexOf("<")>-1 ||
			input.indexOf("\"")>-1){ 
		
			for(int i=0;i<input.length();i++){
				if (input.charAt(i)=='&') result+="&amp;";
				else if (input.charAt(i)=='\'') result+="&apos;";
				else if (input.charAt(i)=='>') result+="&gt;";
				else if (input.charAt(i)=='<') result+="&lt;";
				else if (input.charAt(i)=='"') result+="&quot;";
				else result+=input.charAt(i);
			
			}
			return result;
	}else return input;	
/*	
	if (input==null ) return input;
	if(	input.indexOf("&")>-1 ||
		input.indexOf("\\")>-1 ||
		input.indexOf(">")>-1 ||
		input.indexOf("<")>-1 ||
		input.indexOf("\"")>-1){
		 
		try{
			return util_xml_ibm.normalFE(input,"");
		}catch(Exception e){
			return input;
		}
	}else return input;
*/	
}
public static String formatNumber(String value, int cInt, int cDec, String type){
	// type = 0 Italiano 1.000.000,77		
	// type = 1 Inglese  1,000,000.77
	try{
		if(value==null || value.trim().equals(""))	return value;
	
		String control="0"; 
		if(new java.text.DecimalFormat("#,###,##0.#").format(1111111.1).equals("1,111,111.1")) control="1";
		
		String format = "######0";			
		if(cDec>0){
			format+=".";
			for(int i=0;i<cDec;i++) format+="0";
		}
		if(cDec<0) format = "######0.######";
	
		if(value.indexOf(".")>-1 && value.indexOf(",")>-1){
			if(control.equals("0")) value= replace(replace(value,".",""),",",".");
			if(control.equals("1")) value= replace(value,",","");
		}
		if(value.indexOf(",")>-1 && value.indexOf(".")==-1){
			value= replace(value,",",".");
		}
	
		String buf = new java.text.DecimalFormat(format).format(new java.math.BigDecimal(value.trim()).doubleValue());
		

		if(type==null || type.trim().equals("")) type="0";
		if(type.equals("0") && control.equals("1")){
			buf = buf.replace('.','^').replace(',','.').replace('^',',');
		}
		if(type.equals("1") && control.equals("0")){
			buf = buf.replace(',','^').replace('.',',').replace('^','.');
		}
		return buf;
	}catch(Exception e){
		return value;	
	}
}


public static String formatStringFromDB (String target) {
 return (target!=null) ? target.trim() : "";
}


public static String formatForJS(String target){
	String newTarget="";
	if(target==null) return null;
	else if(!target.equals("")) {
		newTarget=new String(target);
		newTarget=replace(newTarget," ","&#32;");
		newTarget=replace(newTarget,"!","&#33;");
		newTarget=replace(newTarget,"\"","\\\"");
		newTarget=replace(newTarget,"$","&#36;");
		newTarget=replace(newTarget,"%","&#37;");
		newTarget=replace(newTarget,"\'","\\\'");
		newTarget=replace(newTarget,"/","&#47;");
		newTarget=replace(newTarget,"<","&#60;");
		newTarget=replace(newTarget,">","&#62;");
		newTarget=replace(newTarget,"?","&#63;");
		newTarget=replace(newTarget,"@","&#64;");
		newTarget=replace(newTarget,"\\","&#92;");
		newTarget=replace(newTarget,"^","&#94;");
		newTarget=replace(newTarget,"`","&#96;");
		newTarget=replace(newTarget,"~","&#126;");
		newTarget=replace(newTarget,"à","&agrave;");
	    newTarget=replace(newTarget,"è","&egrave;");
		newTarget=replace(newTarget,"é","&eacute;");
		newTarget=replace(newTarget,"í","&iacute;");
		newTarget=replace(newTarget,"ì","&igrave;");
		newTarget=replace(newTarget,"ò","&ograve;");			 
		newTarget=replace(newTarget,"ó","&oacute;");
		newTarget=replace(newTarget,"ù","&ugrave;");
		newTarget=replace(newTarget,"ú","&uacute;");
	}
	return newTarget;	
}

public static String NumeroInLettere(double Numero){
	if(Numero == 0) return "";
	int Frazione1, Frazione2, Frazione3;
	String InLettere = "";
	double Temp;
	
	for(int Esp=3;Esp>-1;Esp--){ 
//		Temp = Numero / 1000 ^ Esp;
		Temp = Numero/Exp(1000, Esp);
		Frazione1 = (int)Temp;
		if(Frazione1 > 0){
			Frazione2 = Frazione1;
			if(Frazione2 > 99){
				Temp = Frazione2 / 100;
				Frazione3 = (int)Temp;
				Frazione2 = Frazione2 - Frazione3 * 100;
				if(Frazione3 == 1) InLettere = InLettere + "cento";
				else InLettere = InLettere + Cifra[Frazione3+1] + "cento";
			}
			if(Frazione2 <= 20)	InLettere = InLettere + Cifra[Frazione2+1];
			else{
				Temp = Frazione2 / 10;
				Frazione3 = (int)Temp;
				InLettere = InLettere + Cifra[Frazione3+19];
				Frazione2 = Frazione2 - Frazione3 * 10;
				if(Frazione2 == 1 || Frazione2 == 8){
					InLettere = InLettere.substring(0, InLettere.length() - 1);
				}
				InLettere = InLettere + Cifra[Frazione2+1];
			}
			switch (Esp){
				case 1:
					if(Frazione1 == 1) InLettere = InLettere.substring(0, InLettere.length() - 3) + "mille";
					else InLettere = InLettere + "mila";
					break;
				case 2:
					if(Frazione1 == 1) InLettere = InLettere.substring(0, InLettere.length() - 3) + "unmilione";
					else InLettere = InLettere + "milioni";
					break;
				case 3:
					if(Frazione1 == 1) InLettere = "unmiliardo";
					else InLettere = InLettere + "miliardi";
					break;
			}
			Numero = Numero - Frazione1 * Exp(1000,Esp);			
		}
	}
	
	return InLettere;
}

private static double Exp(double value, int esp){
	double result = 1;
	if(esp==0) return result;
	for(int i=0;i<esp;i++) result*=value;
	return result;
}


}
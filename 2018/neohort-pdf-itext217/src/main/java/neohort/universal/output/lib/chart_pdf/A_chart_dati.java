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

import java.util.HashMap;
import java.util.Vector;


public abstract class A_chart_dati implements I_chart_dati{	
	public Vector<Object> datiX = new Vector<Object>();
	public Vector<Object> datiY = new Vector<Object>();
	public Vector<Object> datiZ = new Vector<Object>();	
	public java.lang.String formatX = "";
	public java.lang.String formatY = "";
	public java.lang.String formatZ = "";

	public long delta_day = 86400;
	public long delta_month = 2678400;
	public long delta_year = 31536000;
	
	private HashMap<String,Object> container_tmp;
	private HashMap<String,Object> external_parameters;
	


public A_chart_dati() {
	super();
	reimposta();
}

public abstract Vector<Object> getDati(int type, float length);
public abstract Vector<Object> getDati(int type, float length, float count, float lengthZ);
public Vector<Object> getDatiX(float length){
	return null;
}
public Vector<Object> getDatiY(float length){
	return null;
}
public Vector<Object> getDatiZ(float length){
	return null;
}
public abstract Vector<Object> getScale(int type, int max_scale);
public Vector<Object> getScale(int type, int max_scale, Float ScaleStep, Float Max, Float Min){
	return getScale(type, max_scale);
}
public abstract String prepareContentString(String formatSG, String value);
public abstract Object prepareDataValue(String formatSG, String value);
public void reimposta() {
/*	
	java.sql.Date dtest = new java.sql.Date(0);
	Calendar calendar = Calendar.getInstance();
		calendar.setTime(dtest); 
		
	delta_day = new java.sql.Date(calendar.YEAR,calendar.MONTH,calendar.DAY_OF_MONTH+1).getTime();
	delta_month = new java.sql.Date(calendar.YEAR,calendar.MONTH+1,calendar.DAY_OF_MONTH).getTime();
	delta_year = new java.sql.Date(calendar.YEAR+1,calendar.MONTH,calendar.DAY_OF_MONTH).getTime();
*/	
}
public abstract void setDati(int type, Object D);
public abstract void setDati(int type,String format, String value);
public abstract void setDati(Object X, Object Y);
public abstract void setDati(Object X, Object Y, Object Z);
public abstract void setFormat(int type, String format);
public HashMap<String,Object> getContainer_tmp() {
	if(container_tmp==null) container_tmp=new HashMap<String,Object>();
	return container_tmp;
}
public HashMap<String,Object> getExternal_parameters() {
	if(external_parameters==null) external_parameters=new HashMap<String,Object>();
	return external_parameters;
}
public void setExternal_parameters(HashMap<String,Object> map) {
	external_parameters = map;
}

}

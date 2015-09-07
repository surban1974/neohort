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
	public java.util.Vector datiX = new Vector();
	public java.util.Vector datiY = new Vector();
	public java.util.Vector datiZ = new Vector();	
	public java.lang.String formatX = "";
	public java.lang.String formatY = "";
	public java.lang.String formatZ = "";

	public long delta_day = 86400;
	public long delta_month = 2678400;
	public long delta_year = 31536000;
	
	private HashMap container_tmp;
	private HashMap external_parameters;
	


public A_chart_dati() {
	super();
	reimposta();
}

public abstract Vector getDati(int type, float length);
public abstract Vector getDati(int type, float length, float count, float lengthZ);
public Vector getDatiX(float length){
	return null;
}
public Vector getDatiY(float length){
	return null;
}
public Vector getDatiZ(float length){
	return null;
}
public abstract Vector getScale(int type, int max_scale);
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
public HashMap getContainer_tmp() {
	if(container_tmp==null) container_tmp=new HashMap();
	return container_tmp;
}
public HashMap getExternal_parameters() {
	if(external_parameters==null) external_parameters=new HashMap();
	return external_parameters;
}
public void setExternal_parameters(HashMap map) {
	external_parameters = map;
}

}

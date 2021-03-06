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

package neohort.universal.output.lib;


import java.awt.Color;



public abstract class report_element_baseawt extends report_element_base implements report_element {
	private static final long serialVersionUID = 1L;
	
		
public Color getField_Color( String name, Color d_value) {
	Color result = d_value;
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
				result = new Color(_r,_g,_b);
		}catch(Exception e){}	
	}else{	
		try{
			result = (Color)Color.class.getField(name).get(Color.class);
		}catch(Exception e){
			try{
				result = (Color)Color.class.getField(name.toUpperCase()).get(Color.class);
			}catch(Exception ex){
				try{
					result = (Color)Color.class.getField(name.toLowerCase()).get(Color.class);
				}catch(Exception exp){}
			}
		}
	}	
	if(result==null) return Color.WHITE;
	else return new Color(result.getRGB());
}
public Color getField_ColorAsColor(String name, Color d_value) {
	Color result = d_value;
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
				result = new Color(_r,_g,_b);
		}catch(Exception e){}	
	}else{	
		try{
			result = (Color)Color.class.getField(name).get(Color.class);
		}catch(Exception e){
			try{
				result = (Color)Color.class.getField(name.toUpperCase()).get(Color.class);
			}catch(Exception ex){
				try{
					result = (Color)Color.class.getField(name.toLowerCase()).get(Color.class);
				}catch(Exception exp){}
			}
		}
	}	
	return result;
}

}

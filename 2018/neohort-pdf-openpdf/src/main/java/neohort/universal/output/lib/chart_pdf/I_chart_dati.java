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

public interface I_chart_dati {
public Vector<Object> getDati(int type, float length);
public Vector<Object> getDati(int type, float lengthX, float lengthY, float lengthZ);
public Vector<Object> getScale(int type, int max_scale);
public Vector<Object> getScale(int type, int max_scale, Float ScaleStepY, Float Max, Float Min);
public void setDati(int type, Object D);
public void setDati(int type,String format, String value);
public void setFormat(int type, String format);
public HashMap<String,Object> getExternal_parameters();
public void setExternal_parameters(HashMap<String,Object> map);

}

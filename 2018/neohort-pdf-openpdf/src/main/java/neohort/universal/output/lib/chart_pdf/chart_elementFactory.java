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

import neohort.universal.output.util.I_OutputRunTime;


public class chart_elementFactory {
public chart_elementFactory() {
	super();
}
public I_chart_content chartFactory(String className, I_OutputRunTime motore, boolean isError) throws Exception{
	I_chart_content element = null;
		if (!className.equals("")) {
			element = (I_chart_content)Class.forName(className).newInstance();
			element.setMotore(motore);
			element.setIsError(isError);
		}
	return element;
}
}

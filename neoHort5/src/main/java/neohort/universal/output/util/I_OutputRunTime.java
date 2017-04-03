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

package neohort.universal.output.util;


import org.w3c.dom.Node;
import neohort.universal.output.lib.report_element;

public interface I_OutputRunTime{	
	public void _notify();
	public void _wait();
	//public ServletConfig getAnotherServletConfig();
	public String getID();
	public String getSaveAs();
	public int getStatus();
	public void recompileProfile();
	public void recompileProfile(report_element children, report_element parent);
	public void recompileProfile(Node node, report_element parent);
	public void reimposta();
	public void setError(Exception e, String des, String level);
	public void setLIB(String lib); 
	public String getNamePac();
	public void clear();
}

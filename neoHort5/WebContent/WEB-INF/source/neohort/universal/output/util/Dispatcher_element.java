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

import java.io.Serializable;
import java.util.Vector;

public class Dispatcher_element implements Serializable{
	private static final long serialVersionUID = 8267215984535990451L;
	private int status = -1;
	private Vector listamessaggi = new Vector();
public Dispatcher_element() {
	super();
}
public Vector getListamessaggi() {
	return listamessaggi;
}
public int getStatus() {
	return status;
}
public void setStatus(int newStatus) {
	status = newStatus;
}
}

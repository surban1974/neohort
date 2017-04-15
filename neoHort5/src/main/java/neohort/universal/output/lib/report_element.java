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

import java.io.Serializable;
import java.util.Hashtable;

public interface report_element extends Serializable{
	public String SYMBOL_0 =	"_$00$_";	//	.
	public String SYMBOL_1 =	"_$01$_";	//	:
	public String SYMBOL_2 =	"_$02$_";	//	;
	public String SYMBOL_3 =	"_$03$_";	//	[
	public String SYMBOL_4 =	"_$04$_";	//	]
	public String SYMBOL_5	=	"_$05$_";	//	{
	public String SYMBOL_6	=	"_$06$_";	//	}
	public String SYMBOL_7	=	"_$07$_";	//	(
	public String SYMBOL_8	=	"_$08$_";	//	)
	public String SYMBOL_9	=	"_$09$_";	//	\
	public String SYMBOL_10	=	"_$10$_";	//	"
	public String SYMBOL_11	=	"_$11$_";	//	'
	public String SYMBOL_12	=	"_$12$_";	//	,
		
void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary);
void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary);
void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary);
void reimposta();
Object getContent();
String getID();
String getName();
}

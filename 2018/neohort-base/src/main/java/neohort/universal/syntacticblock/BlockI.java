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

package neohort.universal.syntacticblock;

import java.io.Serializable;

public interface BlockI extends Serializable{
	final char CH_BrL = '(';
	final char CH_BrR = ')';		
	final char CH_LS = '<';
	final char CH_MR = '>';
	final char CH_EQ = '=';
	final char CH_NE = '!';
	final char CH_ML = '*';
	final char CH_DV = '/';
	final char CH_PL = '+';
	final char CH_MN = '-';
	final char CH_AND = '&';
	final char CH_OR = '|';
	final char CH_UBr = '\'';
	final char[] CH_Specification = {'(',')','<','>','=','!','*','/','+','-','&','|',' '};	
public int CheckSeparator(String Value);
public String GetResultAction();
}

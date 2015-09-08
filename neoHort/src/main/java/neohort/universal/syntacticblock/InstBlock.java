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

public final class InstBlock{
	String nameOfType =null;
	String InputValue=null;
	final String DigitArray = "0123456789.E<>=!*/+-()|& ";
	final String OnlyDigit = "0123456789";	
public InstBlock() {
}
public InstBlock(String value) {
	this.InputValue=value;
	this.nameOfType = CheckTypeOfBlock();
}

private boolean Check_D() {
	int i=0;
	boolean EndCycle = false;
	while (i< InputValue.length() && !EndCycle) {
		if (!CheckSymbol(InputValue.charAt(i))) EndCycle=true;			
		i++;
	}
	if (!EndCycle) return true;
	else return false;
}
private boolean Check_S() {
	if (InputValue.indexOf("'")>-1){
		return true;
	}
	else return false;
}
private boolean CheckSymbol(char Symbol) {
	if (DigitArray.indexOf(Symbol)>-1) return true;
	else return false;
}
private String CheckTypeOfBlock() {
	String prefName = this.getClass().getName();
	if(prefName.indexOf(".")>-1)
		prefName=prefName.substring(0,prefName.lastIndexOf("."));
//	if (Check_B()) return prefName+".BlockB";
	if (Check_S()) return prefName+".BlockS";
	if (Check_D()) return prefName+".BlockD";
	InputValue = "'"+InputValue+"'";
	return prefName+".BlockS";
}
public Block GetBlock() {
	if (this.nameOfType!=null){
		try {
			Block CurrentBlock = (Block)Class.forName(this.nameOfType).newInstance();
			CurrentBlock.setIni(this.InputValue);
			return CurrentBlock;
		} catch (Exception e) {return null;}			
	}	
	return null;
}
}

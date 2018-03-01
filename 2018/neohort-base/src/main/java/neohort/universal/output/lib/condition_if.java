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

import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.syntacticblock.BlockI;
import neohort.universal.syntacticblock.InstBlock;

public class condition_if extends report_element_base{
	private static final long serialVersionUID = -1L;
	private java.lang.String CONDITION;
	private java.lang.String cond_locale = "false";
public condition_if() {
	super();	
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		cond_locale	= getCONDITION();
		if (cond_locale.trim().equalsIgnoreCase("TRUE") || cond_locale.trim().equals("1")){
		}else{
			try {
				InstBlock IBlock = new InstBlock(cond_locale);
				BlockI BufBlock=IBlock.GetBlock();
				cond_locale=BufBlock.GetResultAction(); 
			}
			catch(Exception e){
				cond_locale = "0";
			}
		}
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public java.lang.String getCONDITION() {
	return CONDITION;
}
public boolean isActive() {
	if (cond_locale.trim().equalsIgnoreCase("TRUE") || cond_locale.trim().equals("1")){
		cond_locale = "0";
		return true;
	}	
	return false;
}
public boolean isActiveCycle() {
	if (cond_locale.trim().equalsIgnoreCase("TRUE") || cond_locale.trim().equals("1")){
		cond_locale = "0";
		return true;
	}	
	return false;
}
public boolean isPreActive() {
	if (cond_locale.trim().equalsIgnoreCase("TRUE") || cond_locale.trim().equals("1")){
		return true;
	}	
	return false;
}
public boolean isPreActiveCycle() {
	if (cond_locale.trim().equalsIgnoreCase("TRUE") || cond_locale.trim().equals("1")){
		return true;
	}	
	return false;
}
public void reimposta() {
	setName("CONDITION_IF");
	CONDITION = "";
}
public String RETURN(String nome){
	return cond_locale;
}
public void setCONDITION(java.lang.String newC) {
	CONDITION = newC;
}
}

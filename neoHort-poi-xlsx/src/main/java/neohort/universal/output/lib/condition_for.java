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

public class condition_for extends report_element_base{
	private static final long serialVersionUID = 7475732844248230642L;
	private java.lang.String FOR_FIRSTCOUNTER;
	private java.lang.String FOR_LASTCOUNTER;
	private java.lang.String FOR_STEPCOUNTER;
	int for_firstcounter = 0;
	int for_lastcounter = 0;
	int for_stepcounter = 1;		
public condition_for() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		for_firstcounter = Integer.valueOf(getFOR_FIRSTCOUNTER()).intValue();
		for_lastcounter = Integer.valueOf(getFOR_LASTCOUNTER()).intValue();
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		for_firstcounter = 0;
		for_lastcounter = 0;
	}
	try{
		for_stepcounter = Integer.valueOf(getFOR_STEPCOUNTER()).intValue();				
	}catch(Exception e){
		for_stepcounter = 1;		
	}
	
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		for_firstcounter = Integer.valueOf(getFOR_FIRSTCOUNTER()).intValue();
		for_lastcounter = Integer.valueOf(getFOR_LASTCOUNTER()).intValue();
		for_stepcounter = Integer.valueOf(getFOR_STEPCOUNTER()).intValue();				
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		for_firstcounter = 0;
		for_lastcounter = 0;
		for_stepcounter = 0;		
	}
}
public java.lang.String getFOR_FIRSTCOUNTER() {
	return FOR_FIRSTCOUNTER;
}
public java.lang.String getFOR_LASTCOUNTER() {
	return FOR_LASTCOUNTER;
}
public java.lang.String getFOR_STEPCOUNTER() {
	return FOR_STEPCOUNTER;
}
public boolean isActiveCycle() {
	if (for_firstcounter<for_lastcounter){
		for_firstcounter+=for_stepcounter;
		return true;
	}	
	return false;
}
public boolean isPreActiveCycle() {
	if (for_firstcounter<for_lastcounter){
		return true;
	}	
	return false;
}
public void reimposta() {
	setName("CONDITION_FOR");
	FOR_FIRSTCOUNTER = "";
	FOR_LASTCOUNTER = "";
	FOR_STEPCOUNTER = "";	
}
public String RETURN(String nome){
	if(nome.trim().toUpperCase().indexOf("SYSTEM:")==0){
		String diret = nome.trim().toUpperCase().substring(7);
		if(diret.equals("SIZE")) return String.valueOf(FOR_LASTCOUNTER);
		if(diret.equals("INDEX")) return String.valueOf(for_firstcounter);
	}
	return String.valueOf(for_firstcounter);
}
public void setFOR_FIRSTCOUNTER(java.lang.String newFOR_FIRSTCOUNTER) {
	FOR_FIRSTCOUNTER = newFOR_FIRSTCOUNTER;
}
public void setFOR_LASTCOUNTER(java.lang.String newFOR_LASTCOUNTER) {
	FOR_LASTCOUNTER = newFOR_LASTCOUNTER;
}
public void setFOR_STEPCOUNTER(java.lang.String newFOR_STEPCOUNTER) {
	FOR_STEPCOUNTER = newFOR_STEPCOUNTER;
}
}

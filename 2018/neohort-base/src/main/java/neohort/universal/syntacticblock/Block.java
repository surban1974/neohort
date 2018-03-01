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

import java.util.Vector;


public abstract class Block implements BlockI{
	private static final long serialVersionUID = -1L;
	String Data = null;
	char Action;
	boolean CheckBrackets=false;
	Vector<SymbolContainer> PreparedData = new Vector<SymbolContainer>();
	BlockI LeftB = null;
	BlockI RightB = null;
	
	
	private class SymbolContainer{
		private String Value = null;
		private String Ident = null;

//		SymbolContainer(){
//			super();			
//		}
		SymbolContainer(String Value, int Ident){
			super();
			this.Value = Value;
			this.Ident = "_"+String.valueOf(Ident).trim();			
		}
		public String getSymbol(){return Value;}
		public String getIdent(){return Ident;}
		
//		public boolean isIdent(int Ident){
//			if (this.Ident.equalsIgnoreCase("_"+String.valueOf(Ident).trim())) return true;
//			else return false;
//		}
		public String toString(){return Value+"{"+Ident+"}";}
		
	}
		
public Block() {
	super();
}
public Block(String Value) {
	super();
	setIni(setPreparedData(Value));	
}
public final int CheckRightBracket(int PositionLeftBracket, String Value) {
	int Double=1;
	int CurrentPosition=PositionLeftBracket+1;
	while (Double!=0 && CurrentPosition<Value.length()){
		if(Value.charAt(CurrentPosition)==CH_BrL) Double++;
		if(Value.charAt(CurrentPosition)==CH_BrR) Double--;
		CurrentPosition++;
	}
	return CurrentPosition--;
}
public int CheckSeparator(String Value) {
	if (Value.indexOf(CH_BrL)>-1){
		int PositionLeftBracket=Value.indexOf(CH_BrL);
		int PositionRightBracket=CheckRightBracket(PositionLeftBracket,Value);
		if (PositionLeftBracket==0) {
			CheckBrackets=true;
			return PositionRightBracket;
		}
		else return PositionLeftBracket-1;
	}
	if (Value.indexOf(CH_LS)>-1) return Value.indexOf(CH_LS);// <
	if (Value.indexOf(CH_MR)>-1) return Value.indexOf(CH_MR);// >
	if (Value.indexOf(CH_EQ)>-1) return Value.indexOf(CH_EQ);// =
	if (Value.indexOf(CH_NE)>-1) return Value.indexOf(CH_NE);// !
	if (Value.indexOf(CH_ML)>-1) return Value.indexOf(CH_ML);// *
	if (Value.indexOf(CH_DV)>-1) return Value.indexOf(CH_DV);// /
	if (Value.indexOf(CH_PL)>-1) return Value.indexOf(CH_PL);// +
	if (Value.indexOf(CH_MN)>-1) return Value.indexOf(CH_MN);// -
	return -1;
}
public String getPreparedData(String Value){
	String Result="";
	int i=0;
	while (i<Value.length()){
		if (isActionSpec(Value.charAt(i))){
			Result+=Value.charAt(i);
			i++;
		}
		else{
			if (Value.charAt(i) == '_'){
				int k = i+1;
				while (k<Value.length() && !isActionSpec(Value.charAt(k))) k++;
				try {
					Result+=((SymbolContainer)PreparedData.elementAt(Integer.valueOf(Value.substring(i+1,k)).intValue())).getSymbol();
					i=k;					
				}
				catch (Exception e){Result+=Value.charAt(i);}	
			}
			else {
				Result+=Value.charAt(i);
				i++;
			}	
		}
	}
	return Result;
}
public abstract String GetResultAction();
public abstract String getValue(String Value,int FirstSymbol);
public boolean isActionSpec(char Value) {
	for(int i=0;i<CH_Specification.length;i++){
		if (Value == CH_Specification[i]) return true;
	}
	return false;
}
public String PrepareFirst(String Value, int Position) {	
	return Value.substring(0,Position);
}
public String PrepareSecond(String Value, int Position) {
	return 	Value.substring(Position+1);
}
public abstract void setIni(String Value);
public String setPreparedData(String Value){
	String Result="";
	if (Value.equalsIgnoreCase("ERROR")) {
			SymbolContainer SCont = new SymbolContainer(Value,PreparedData.size());
			PreparedData.addElement(SCont);
			Result+=SCont.getIdent();
			return Result;
	}
	int i=0;
	while (i<Value.length()){
		if (isActionSpec(Value.charAt(i))){
			Result+=Value.charAt(i);
			i++;
		}
		else{
			SymbolContainer SCont = new SymbolContainer(getValue(Value,i),PreparedData.size());
			PreparedData.addElement(SCont);
			i+=SCont.getSymbol().length();
			Result+=SCont.getIdent();
		}
	}
	return Result;
}
public String spTrim(String InpValue) {
	String Result="";
	boolean forbid=false;
	if (InpValue==null) return "";
	for(int i=0;i<InpValue.length();i++){
		if (!forbid){
			if (InpValue.charAt(i)=='\'') forbid=true;
		}
		else{
			if (InpValue.charAt(i)=='\'') forbid=false;
		}
		if (forbid) Result+=InpValue.charAt(i);
		else{
			if (InpValue.charAt(i)!=' ') Result+=InpValue.charAt(i);
		}	
	}
	return Result;
}
public String toString() {
	String Result="";
	String LeftT="Left block undefined";
	String RightT="Right block undefined";
	if (Data!=null) Result="["+Data+"]";
	else {
		if (LeftB!=null) LeftT=LeftB.toString();
		if (RightB!=null) RightT=RightB.toString();
		Result="("+LeftT+")"+
		Action+
		"("+RightT+")";
	}
	return Result;
}
}

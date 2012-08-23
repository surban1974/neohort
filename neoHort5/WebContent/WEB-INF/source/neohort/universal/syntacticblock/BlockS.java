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

public class BlockS extends Block implements BlockI{

	private static final long serialVersionUID = -7328555101320945762L;
public BlockS() {
	super();
}
public BlockS(String Value) {
	super(Value);
}
private int EqualData(String Left, String Right) {
	int Result=0;
	int Size = (Left.length()>Right.length())?Right.length():Left.length();
	boolean StopAction=false;
	int i=0;
	while (!StopAction && i<Size){
		if (Left.charAt(i)>Right.charAt(i)){
			Result=1;
			StopAction=true;
		}
		if (Left.charAt(i)<Right.charAt(i)){
			Result=2;
			StopAction=true;
		}
		i++;
	}
	return Result;
}
public String GetResultAction() {
	String Result = null;
	if (Data!=null) Result = Data;
	else {
		switch (Action) {
			case CH_PL: // +
				if (LeftB!=null && RightB!=null) Result = String.valueOf(PrepareDtoS(RightB.GetResultAction())+PrepareDtoS(LeftB.GetResultAction()));
				if (LeftB==null) Result = RightB.GetResultAction();
				if (RightB==null) Result = LeftB.GetResultAction();
				if (LeftB==null && RightB==null) Result = "0";
				break;
			case CH_LS: // < 
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (EqualData(PrepareDtoS(RightB.GetResultAction()),PrepareDtoS(LeftB.GetResultAction()))==2) Result ="true";
					else Result = "false";
				}
				break;
			case CH_MR: // >
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (EqualData(PrepareDtoS(RightB.GetResultAction()),PrepareDtoS(LeftB.GetResultAction()))==1) Result ="true";
					else Result = "false";
				}
				break;						
			case CH_NE: // !
				if (LeftB==null || RightB==null) Result = "true";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase(PrepareDtoS(LeftB.GetResultAction()))) Result ="false";
					else Result = "true";
				}
				break;
			case CH_EQ: // =
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase(PrepareDtoS(LeftB.GetResultAction()))) Result ="true";
					else Result = "false";
				}
				break;
//This is addititional features for the emulation a work with the boolean"s variables
			case CH_AND: // &
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase("true") && PrepareDtoS(LeftB.GetResultAction()).equalsIgnoreCase("true")) Result ="true";				
					else Result = "false";
				}
				break;
			case CH_OR: // |
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase("true") || PrepareDtoS(LeftB.GetResultAction()).equalsIgnoreCase("true")) Result ="true";
					else Result = "false";
				}
				break;				
		}
	}
	LeftB = null;
	RightB = null;
	Data = null;
	return Result;
}
public String getValue(String Value, int FirstSymbol){
	if (Value.charAt(FirstSymbol)=='\''){
		if (Value.indexOf("'", FirstSymbol+1)>-1) return Value.substring(FirstSymbol,Value.indexOf("'", FirstSymbol+1)+1);
		else return "";
	}
	else return new BlockD().getValue(Value, FirstSymbol);
}
private String PrepareDtoS(String Value) {
	return Value;
}
private String PreparetoString(String Value) {
	String BetwResult="";
	for(int i=0;i<Value.length();i++){
		if (Value.charAt(i)!='\'') BetwResult+=Value.charAt(i);
	}
	return BetwResult;
}
public void setIni(String Value){
	if (PreparedData.size()==0) Value = setPreparedData(Value);
	Value = spTrim(Value);
	int Position=CheckSeparator(Value);	
	if(Value.length()==0) Data = getPreparedData(PreparetoString(Value));
	if(Position==-1) Data = getPreparedData(PreparetoString(Value));
	else {
		if(CheckBrackets){
			Value=Value.substring(1,Position-1)+Value.substring(Position);
			Position=Position-2;
			if (Value.length()==Position){
				CheckBrackets=false;
				setIni(Value);
				return;
			}
		}
		LeftB = new InstBlock(getPreparedData(PrepareFirst(Value,Position))).GetBlock();
		RightB = new InstBlock(getPreparedData(PrepareSecond(Value,Position))).GetBlock();
		Action = Value.charAt(Position);
	}
}
}

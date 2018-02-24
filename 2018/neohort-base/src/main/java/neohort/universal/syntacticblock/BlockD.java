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

public class BlockD extends Block implements BlockI{
	private static final long serialVersionUID = 3590924502643225735L;

public BlockD() {
	super();
}
public BlockD(String Value) {
	super(Value);
}
public String GetResultAction() {
	String Result = null;
	if (Data!=null) Result = Data; 
	else {
		switch (Action) {
			case CH_ML: // *
				if (LeftB!=null && RightB!=null) Result = String.valueOf(PrepareDtoS(RightB.GetResultAction()).doubleValue()*PrepareDtoS(LeftB.GetResultAction()).doubleValue());
				if (LeftB==null) Result = RightB.GetResultAction();
				if (RightB==null) Result = LeftB.GetResultAction();
				if (LeftB==null && RightB==null) Result = "0";
				break;
			case CH_DV: // /
				if (LeftB!=null && RightB!=null) Result = String.valueOf(PrepareDtoS(LeftB.GetResultAction()).doubleValue()/PrepareDtoS(RightB.GetResultAction()).doubleValue());
				if (LeftB==null) Result = RightB.GetResultAction();
				if (RightB==null) Result = LeftB.GetResultAction();
				if (LeftB==null && RightB==null) Result = "0";
				break;
			case CH_PL: // +
				if (LeftB!=null && RightB!=null) Result = String.valueOf(PrepareDtoS(RightB.GetResultAction()).doubleValue()+PrepareDtoS(LeftB.GetResultAction()).doubleValue());
				if (LeftB==null) Result = RightB.GetResultAction();
				if (RightB==null) Result = LeftB.GetResultAction();
				if (LeftB==null && RightB==null) Result = "0";
				break;
			case CH_MN: // -
				if (LeftB!=null && RightB!=null) Result = String.valueOf(PrepareDtoS(LeftB.GetResultAction()).doubleValue()-PrepareDtoS(RightB.GetResultAction()).doubleValue());
				if (LeftB==null) Result = RightB.GetResultAction();
				if (RightB==null) Result = LeftB.GetResultAction();
				if (LeftB==null && RightB==null) Result = "0";
				break;
			case CH_LS: // < 
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).doubleValue()>PrepareDtoS(LeftB.GetResultAction()).doubleValue()) Result ="true";
					else Result = "false";
				}
				break;
			case CH_MR: // >
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).doubleValue()<PrepareDtoS(LeftB.GetResultAction()).doubleValue()) Result ="true";
					else Result = "false";
				}
				break;
			case CH_NE: // !
				if (LeftB==null || RightB==null) Result = "true";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).doubleValue()==PrepareDtoS(LeftB.GetResultAction()).doubleValue()) Result ="false";
					else Result = "true";
				}
				break;
			case CH_EQ: // =
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (PrepareDtoS(RightB.GetResultAction()).doubleValue()==PrepareDtoS(LeftB.GetResultAction()).doubleValue()) Result ="true";
					else Result = "false";
				}
				break;
//This is addititional features for the emulation a work with the boolean"s variables
			case CH_AND: // &
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (_PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase("true") && _PrepareDtoS(LeftB.GetResultAction()).equalsIgnoreCase("true")) Result ="true";				
					else Result = "false";
				}
				break;
			case CH_OR: // |
				if (LeftB==null || RightB==null) Result = "false";
				else{
					if (_PrepareDtoS(RightB.GetResultAction()).equalsIgnoreCase("true") || _PrepareDtoS(LeftB.GetResultAction()).equalsIgnoreCase("true")) Result ="true";
					else Result = "false";
				}
				break;				
				
		}
	}
	LeftB = null;
	RightB = null;
	Data = null;
	Result = PrepareResult(Result);
	return Result;
}
public String getValue(String Value,int FirstSymbol){
	int i=FirstSymbol;
	boolean Stop = false;
	while (i< Value.length() && !Stop){
		if (isActionSpec(Value.charAt(i))) Stop=true;
		else i++;
	}	
	return Value.substring(FirstSymbol,i);
}
private Double PrepareDtoS(String Value) {
	Double Result = new Double(0.0);
	try {
		Result = Double.valueOf(Value);
	}
	catch(Exception e){}
	return Result;
}
private String PrepareResult(String Value) {
	if (Value.indexOf(".")>-1){
		if(Double.valueOf(Value.substring(Value.indexOf(".")+1)).doubleValue()==0) return Value.substring(0,Value.indexOf("."));
		else return Value;
	}
	else return Value;
}
public void setIni(String Value){
	if (PreparedData.size()==0) Value = setPreparedData(Value);	
	Value = spTrim(Value);	
	int Position=CheckSeparator(Value);	
	if(Value.length()==0) Data = getPreparedData(Value);
	if(Position==-1) Data = getPreparedData(Value);
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

private String _PrepareDtoS(String Value) {
	return Value;
}
}

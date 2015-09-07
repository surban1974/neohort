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

public class BlockB extends Block implements BlockI {
	private static final long serialVersionUID = 8701350119618081529L;
public BlockB() {
	super();
}
public BlockB(String Value) {
	super(Value);
}
public String GetResultAction() {
	String Result = null;
	if (Data!=null) Result = Data;
	else {
		switch (Action) {
			case CH_AND: // &
				if (LeftB==null || RightB==null) Result = "false";
				else {
					try{
						Result = new Boolean(Boolean.getBoolean(LeftB.GetResultAction()) && Boolean.getBoolean(RightB.GetResultAction())).toString();
					}
					catch(Exception e){
						Result="false";
					}
				}
				break;
			case CH_OR: // |
				if (LeftB==null || RightB==null) Result = "false";
				else {
					try{
						Result = new Boolean(Boolean.getBoolean(LeftB.GetResultAction()) || Boolean.getBoolean(RightB.GetResultAction())).toString();
					}
					catch(Exception e){
						Result="false";
					}
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
	return null;
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
}

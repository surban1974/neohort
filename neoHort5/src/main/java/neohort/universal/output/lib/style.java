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

public class style extends report_element_base{
	private static final long serialVersionUID = 4061897228931015406L;
	private String TEXT_ALIGN_H;
	private String TEXT_ALIGN_V;
	private String FONT;
	private String FONT_SIZE;
	private String FONT_TYPE;
	private String FONT_ENCODED;
	private String FONT_EMBEDDED;
	
	private String FONT_COLOR;
	private String FONT_COLOR_RGB;
	
	private String DIMENTION_H;
	private String DIMENTION_V;
	private String ALIGN;
	
	private String BACK_COLOR;
	private String BACK_COLOR_RGB;
	
	private String BORDER_COLOR;
	private String BORDER_COLOR_RGB;
	
	
	private String BORDER;
	private String BORDER_WIDTH;
	
	private String PADDING;
	
	private String FORMAT;
	private String FONT_STYLE;
	private String COL_SPAN;
	private String ROW_SPAN;	
	
	private String BAR_COLOR_RGB;
	private String BAR_COLOR;
	
	private String ABSOLUTE_X;
	private String ABSOLUTE_Y;
	private String TEXT_ROTATION_DEGREE;
	
	private String WIDTH;
	private String HEIGHT;
	
	private String BORDER_COLOR_TOP;
	private String BORDER_COLOR_TOP_RGB;
	private String BORDER_COLOR_BOTTOM;
	private String BORDER_COLOR_BOTTOM_RGB;
	private String BORDER_COLOR_LEFT;
	private String BORDER_COLOR_LEFT_RGB;
	private String BORDER_COLOR_RIGHT;
	private String BORDER_COLOR_RIGHT_RGB;
	
	private String BORDER_WIDTH_TOP;
	private String BORDER_WIDTH_BOTTOM;
	private String BORDER_WIDTH_LEFT;
	private String BORDER_WIDTH_RIGHT;
	
	private String LEADING;
	
	private String EXTRA_FONT;
	private String DIRECTION;
	
	
	
	public static String PROPERTYS = ";TEXT_ALIGN_H;TEXT_ALIGN_V;FONT;FONT_SIZE;FONT_TYPE;FONT_ENCODED;FONT_COLOR;FONT_COLOR_RGB;DIMENTION_H;DIMENTION_V;ALIGN;BACK_COLOR;BACK_COLOR_RGB;BORDER_COLOR;BORDER_COLOR_RGB;BORDER;BORDER_WIDTH;PADDING;FORMAT;FONT_STYLE;COL_SPAN;ROW_SPAN;BAR_COLOR_RGB;BAR_COLOR;ABSOLUTE_X;ABSOLUTE_Y;WIDTH;HEIGHT;BORDER_COLOR_TOP;BORDER_COLOR_TOP_RGB;BORDER_COLOR_BOTTOM;BORDER_COLOR_BOTTOM_RGB;BORDER_COLOR_LEFT;BORDER_COLOR_LEFT_RGB;BORDER_COLOR_RIGHT;BORDER_COLOR_RIGHT_RGB;BORDER_WIDTH_TOP;BORDER_WIDTH_BOTTOM;BORDER_WIDTH_LEFT;BORDER_WIDTH_RIGHT;TEXT_ROTATION_DEGREE;LEADING;";
	
public style() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
}


public String getCashKey(){
	return
			TEXT_ALIGN_H+"|"+
			TEXT_ALIGN_V+"|"+
			FONT+"|"+
			FONT_SIZE+"|"+
			FONT_TYPE+"|"+
			FONT_ENCODED+"|"+
			FONT_EMBEDDED+"|"+
			
			FONT_COLOR+"|"+
			FONT_COLOR_RGB+"|"+
			
			DIMENTION_H+"|"+
			DIMENTION_V+"|"+
			ALIGN+"|"+
			
			BACK_COLOR+"|"+
			BACK_COLOR_RGB+"|"+
			
			BORDER_COLOR+"|"+
			BORDER_COLOR_RGB+"|"+
			
			
			BORDER+"|"+
			BORDER_WIDTH+"|"+
			
			PADDING+"|"+
			
			FORMAT+"|"+
			FONT_STYLE+"|"+
			COL_SPAN+"|"+
			ROW_SPAN+"|"+	
			
			BAR_COLOR_RGB+"|"+
			BAR_COLOR+"|"+
			
			ABSOLUTE_X+"|"+
			ABSOLUTE_Y+"|"+
			TEXT_ROTATION_DEGREE+"|"+
			
			WIDTH+"|"+
			HEIGHT+"|"+
			
			BORDER_COLOR_TOP+"|"+
			BORDER_COLOR_TOP_RGB+"|"+
			BORDER_COLOR_BOTTOM+"|"+
			BORDER_COLOR_BOTTOM_RGB+"|"+
			BORDER_COLOR_LEFT+"|"+
			BORDER_COLOR_LEFT_RGB+"|"+
			BORDER_COLOR_RIGHT+"|"+
			BORDER_COLOR_RIGHT_RGB+"|"+
			
			BORDER_WIDTH_TOP+"|"+
			BORDER_WIDTH_BOTTOM+"|"+
			BORDER_WIDTH_LEFT+"|"+
			BORDER_WIDTH_RIGHT+"|"+
			
			LEADING+"|"+
			
			EXTRA_FONT+"|"+
			DIRECTION+"|";
	}
public String getALIGN() {
	return ALIGN;
}
public String getBACK_COLOR() {
	return (BACK_COLOR.trim().equals(""))?BACK_COLOR_RGB:BACK_COLOR;
}
public String getBORDER() {
	return BORDER;
}
public String getDIMENTION_H() {
	return DIMENTION_H;
}
public String getDIMENTION_V() {
	return DIMENTION_V;
}
public String getFONT() {
	return FONT;
}
public String getFONT_COLOR() {
	return (FONT_COLOR.trim().equals(""))?FONT_COLOR_RGB:FONT_COLOR;
}
public String getFONT_SIZE() {
	return FONT_SIZE;
}
public String getFONT_STYLE() {
	return FONT_STYLE;
}
public String getFONT_TYPE() {
	return FONT_TYPE;
}
public String getFORMAT() {
	return FORMAT;
}
public String getPADDING() {
	return PADDING;
}

public void reimposta() {
	setName("STYLE");
	TEXT_ALIGN_H="";
	TEXT_ALIGN_V="";
	
	FONT="";
	FONT_SIZE="";
	FONT_STYLE="";
	FONT_TYPE="";
	FONT_ENCODED="";
	FONT_COLOR="";
	FONT_COLOR_RGB="";
	
	DIMENTION_H="";
	DIMENTION_V="";
	ALIGN="";
	PADDING="";
	FORMAT="";
	WIDTH="";
	HEIGHT="";
	COL_SPAN="";
	ROW_SPAN="";
	ABSOLUTE_X="";
	ABSOLUTE_Y="";
	TEXT_ROTATION_DEGREE="";
	
	BACK_COLOR="";
	BACK_COLOR_RGB="";
	BAR_COLOR_RGB="";
	BAR_COLOR="";
	
	BORDER="";
	BORDER_COLOR="";
	BORDER_COLOR_RGB="";
	BORDER_COLOR_TOP="";
	BORDER_COLOR_TOP_RGB="";
	BORDER_COLOR_BOTTOM="";
	BORDER_COLOR_BOTTOM_RGB="";
	BORDER_COLOR_LEFT="";
	BORDER_COLOR_LEFT_RGB="";
	BORDER_COLOR_RIGHT="";
	BORDER_COLOR_RIGHT_RGB="";

	BORDER_WIDTH="";	
	BORDER_WIDTH_TOP="";
	BORDER_WIDTH_BOTTOM="";
	BORDER_WIDTH_LEFT="";
	BORDER_WIDTH_RIGHT="";
	
	LEADING="";
	
	EXTRA_FONT="";
	FONT_EMBEDDED="";
	DIRECTION="";
}
public void reStyle(style style_new) {
	if(style_new==null) return;
	if(style_new.getTEXT_ALIGN_H()!=null && this.getTEXT_ALIGN_H().trim().equals("")) this.setTEXT_ALIGN_H(style_new.getTEXT_ALIGN_H());
	if(style_new.getTEXT_ALIGN_V()!=null && this.getTEXT_ALIGN_V().trim().equals("")) this.setTEXT_ALIGN_V(style_new.getTEXT_ALIGN_V());

	if(style_new.getFONT()!=null && this.getFONT().trim().equals("")) this.setFONT(style_new.getFONT());
	if(style_new.getFONT_SIZE()!=null && this.getFONT_SIZE().trim().equals("")) this.setFONT_SIZE(style_new.getFONT_SIZE());
	if(style_new.getFONT_TYPE()!=null && this.getFONT_TYPE().trim().equals("")) this.setFONT_TYPE(style_new.getFONT_TYPE());
	if(style_new.getFONT_STYLE()!=null && this.getFONT_STYLE().trim().equals("")) this.setFONT_STYLE(style_new.getFONT_STYLE());
	if(style_new.getFONT_ENCODED()!=null && this.getFONT_ENCODED().trim().equals("")) this.setFONT_ENCODED(style_new.getFONT_ENCODED());
	if(style_new.getFONT_COLOR()!=null && this.getFONT_COLOR().trim().equals("")) this.setFONT_COLOR(style_new.getFONT_COLOR());
	if(style_new.getFONT_COLOR_RGB()!=null && this.getFONT_COLOR_RGB().trim().equals("")) this.setFONT_COLOR_RGB(style_new.getFONT_COLOR_RGB());

	if(style_new.getDIMENTION_H()!=null && this.getDIMENTION_H().trim().equals("")) this.setDIMENTION_H(style_new.getDIMENTION_H());
	if(style_new.getDIMENTION_V()!=null && this.getDIMENTION_V().trim().equals("")) this.setDIMENTION_V(style_new.getDIMENTION_V());
	if(style_new.getALIGN()!=null && this.getALIGN().trim().equals("")) this.setALIGN(style_new.getALIGN());
	if(style_new.getPADDING()!=null && this.getPADDING().trim().equals("")) this.setPADDING(style_new.getPADDING());
	if(style_new.getFORMAT()!=null && this.getFORMAT().trim().equals("")) this.setFORMAT(style_new.getFORMAT());
	if(style_new.getWIDTH()!=null && this.getWIDTH().trim().equals("")) this.setWIDTH(style_new.getWIDTH());
	if(style_new.getHEIGHT()!=null && this.getHEIGHT().trim().equals("")) this.setHEIGHT(style_new.getHEIGHT());
	if(style_new.getCOL_SPAN()!=null && this.getCOL_SPAN().trim().equals("")) this.setCOL_SPAN(style_new.getCOL_SPAN());
	if(style_new.getROW_SPAN()!=null && this.getROW_SPAN().trim().equals("")) this.setROW_SPAN(style_new.getROW_SPAN());
	if(style_new.getABSOLUTE_X()!=null && this.getABSOLUTE_X().trim().equals("")) this.setABSOLUTE_X(style_new.getABSOLUTE_X());
	if(style_new.getABSOLUTE_Y()!=null && this.getABSOLUTE_Y().trim().equals("")) this.setABSOLUTE_Y(style_new.getABSOLUTE_Y());
	if(style_new.getTEXT_ROTATION_DEGREE()!=null && this.getTEXT_ROTATION_DEGREE().trim().equals("")) this.setTEXT_ROTATION_DEGREE(style_new.getTEXT_ROTATION_DEGREE());
	
	if(style_new.getBACK_COLOR()!=null && this.getBACK_COLOR().trim().equals("")) this.setBACK_COLOR(style_new.getBACK_COLOR());
	if(style_new.getBACK_COLOR_RGB()!=null && this.getBACK_COLOR_RGB().trim().equals("")) this.setBACK_COLOR_RGB(style_new.getBACK_COLOR_RGB());
	if(style_new.getBAR_COLOR_RGB()!=null && this.getBAR_COLOR_RGB().trim().equals("")) this.setBAR_COLOR_RGB(style_new.getBAR_COLOR_RGB());
	if(style_new.getBAR_COLOR()!=null && this.getBAR_COLOR().trim().equals("")) this.setBAR_COLOR(style_new.getBAR_COLOR());

	if(style_new.getBORDER()!=null && this.getBORDER().trim().equals("")) this.setBORDER(style_new.getBORDER());
	if(style_new.getBORDER_COLOR()!=null && this.getBORDER_COLOR().trim().equals("")) this.setBORDER_COLOR(style_new.getBORDER_COLOR());
	if(style_new.getBORDER_COLOR_RGB()!=null && this.getBORDER_COLOR_RGB().trim().equals("")) this.setBORDER_COLOR_RGB(style_new.getBORDER_COLOR_RGB());	
	if(style_new.getBORDER_COLOR_TOP()!=null && this.getBORDER_COLOR_TOP().trim().equals("")) this.setBORDER_COLOR_TOP(style_new.getBORDER_COLOR_TOP());
	if(style_new.getBORDER_COLOR_TOP_RGB()!=null && this.getBORDER_COLOR_TOP_RGB().trim().equals("")) this.setBORDER_COLOR_TOP_RGB(style_new.getBORDER_COLOR_TOP_RGB());	
	if(style_new.getBORDER_COLOR_BOTTOM()!=null && this.getBORDER_COLOR_BOTTOM().trim().equals("")) this.setBORDER_COLOR_BOTTOM(style_new.getBORDER_COLOR_BOTTOM());
	if(style_new.getBORDER_COLOR_BOTTOM_RGB()!=null && this.getBORDER_COLOR_BOTTOM_RGB().trim().equals("")) this.setBORDER_COLOR_BOTTOM_RGB(style_new.getBORDER_COLOR_BOTTOM_RGB());	
	if(style_new.getBORDER_COLOR_LEFT()!=null && this.getBORDER_COLOR_LEFT().trim().equals("")) this.setBORDER_COLOR_LEFT(style_new.getBORDER_COLOR_LEFT());
	if(style_new.getBORDER_COLOR_LEFT_RGB()!=null && this.getBORDER_COLOR_LEFT_RGB().trim().equals("")) this.setBORDER_COLOR_LEFT_RGB(style_new.getBORDER_COLOR_LEFT_RGB());	
	if(style_new.getBORDER_COLOR_RIGHT()!=null && this.getBORDER_COLOR_RIGHT().trim().equals("")) this.setBORDER_COLOR_RIGHT(style_new.getBORDER_COLOR_RIGHT());
	if(style_new.getBORDER_COLOR_RIGHT_RGB()!=null && this.getBORDER_COLOR_RIGHT_RGB().trim().equals("")) this.setBORDER_COLOR_RIGHT_RGB(style_new.getBORDER_COLOR_RIGHT_RGB());	

	if(style_new.getBORDER_WIDTH()!=null && this.getBORDER_WIDTH().trim().equals("")) this.setBORDER_WIDTH(style_new.getBORDER_WIDTH());
	if(style_new.getBORDER_WIDTH_TOP()!=null && this.getBORDER_WIDTH_TOP().trim().equals("")) this.setBORDER_WIDTH_TOP(style_new.getBORDER_WIDTH_TOP());
	if(style_new.getBORDER_WIDTH_BOTTOM()!=null && this.getBORDER_WIDTH_BOTTOM().trim().equals("")) this.setBORDER_WIDTH_BOTTOM(style_new.getBORDER_WIDTH_BOTTOM());
	if(style_new.getBORDER_WIDTH_LEFT()!=null && this.getBORDER_WIDTH_LEFT().trim().equals("")) this.setBORDER_WIDTH_LEFT(style_new.getBORDER_WIDTH_LEFT());
	if(style_new.getBORDER_WIDTH_RIGHT()!=null && this.getBORDER_WIDTH_RIGHT().trim().equals("")) this.setBORDER_WIDTH_RIGHT(style_new.getBORDER_WIDTH_RIGHT());
	
	if(style_new.getEXTRA_FONT()!=null && this.getEXTRA_FONT().trim().equals("")) this.setEXTRA_FONT(style_new.getEXTRA_FONT());
	if(style_new.getFONT_EMBEDDED()!=null && this.getFONT_EMBEDDED().trim().equals("")) this.setFONT_EMBEDDED(style_new.getFONT_EMBEDDED());
	if(style_new.getDIRECTION()!=null && this.getDIRECTION().trim().equals("")) this.setDIRECTION(style_new.getDIRECTION());

}

public void setALIGN(String newALIGN) {
	if(newALIGN!=null)
		ALIGN = newALIGN.trim().toUpperCase();
}
public void setBACK_COLOR(String newBACK_COLOR) {
	if(newBACK_COLOR!=null)
		BACK_COLOR = newBACK_COLOR.trim().toLowerCase();
}
public void setBORDER(String newBORDER) {
	BORDER = newBORDER;
}
public void setDIMENTION_H(String newDIMENTION_H) {
	DIMENTION_H = newDIMENTION_H;
}
public void setDIMENTION_V(String newDIMENTION_V) {
	DIMENTION_V = newDIMENTION_V;
}
public void setFONT(String newFONT) {
	if(newFONT!=null)
		FONT = newFONT.trim();
}
public void setFONT_COLOR(String newFONT_COLOR) {
	if(newFONT_COLOR!=null)
		FONT_COLOR = newFONT_COLOR.trim().toLowerCase();
}
public void setFONT_SIZE(String newFONT_SIZE) {
	FONT_SIZE = newFONT_SIZE;
}
public void setFONT_STYLE(String newFONT_STYLE) {
	FONT_STYLE = newFONT_STYLE;
}
public void setFONT_TYPE(String newFONT_TYPE) {
	if(newFONT_TYPE!=null)
		FONT_TYPE = newFONT_TYPE.trim();
}
public void setFORMAT(String newFORMAT) {
	FORMAT = newFORMAT;
}
public void setPADDING(String newPADDING) {
	PADDING = newPADDING;
}
public String getCOL_SPAN() {
	return COL_SPAN;
}
public void setCOL_SPAN(String col_span) {
	COL_SPAN = col_span;
}
public String getROW_SPAN() {
	return ROW_SPAN;
}
public void setROW_SPAN(String row_span) {
	ROW_SPAN = row_span;
}
public String getBACK_COLOR_RGB() {
	return BACK_COLOR_RGB;
}
public String getBAR_COLOR_RGB() {
	return BAR_COLOR_RGB;
}
public String getFONT_COLOR_RGB() {
	return FONT_COLOR_RGB;
}
public void setBACK_COLOR_RGB(String string) {
	BACK_COLOR_RGB = string;
}
public void setBAR_COLOR_RGB(String string) {
	BAR_COLOR_RGB = string;
}
public void setFONT_COLOR_RGB(String string) {
	FONT_COLOR_RGB = string;
}
public String getTEXT_ALIGN_H() {
	return TEXT_ALIGN_H;
}
public String getTEXT_ALIGN_V() {
	return TEXT_ALIGN_V;
}
public void setTEXT_ALIGN_H(String string) {
	TEXT_ALIGN_H = string;
}
public void setTEXT_ALIGN_V(String string) {
	TEXT_ALIGN_V = string;
}
public String getBAR_COLOR() {
	return (BAR_COLOR.trim().equals(""))?BAR_COLOR_RGB:BAR_COLOR;	
}
public void setBAR_COLOR(String newBAR_COLOR) {
	if(newBAR_COLOR!=null)
		BAR_COLOR = newBAR_COLOR.trim().toLowerCase();	
}
public String getBORDER_COLOR() {
	return (BORDER_COLOR.trim().equals(""))?BORDER_COLOR_RGB:BORDER_COLOR;
}
public void setBORDER_COLOR(String newBORDER_COLOR) {
	if(newBORDER_COLOR!=null)
	BORDER_COLOR = newBORDER_COLOR.trim().toLowerCase();
}
public String getBORDER_COLOR_RGB() {
	return BORDER_COLOR_RGB;
}
public void setBORDER_COLOR_RGB(String string) {
	BORDER_COLOR_RGB = string;
}
public String getBORDER_WIDTH() {
	return BORDER_WIDTH;
}
public void setBORDER_WIDTH(String string) {
	BORDER_WIDTH = string;
}
public String getABSOLUTE_X() {
	return ABSOLUTE_X;
}

public String getABSOLUTE_Y() {
	return ABSOLUTE_Y;
}

public void setABSOLUTE_X(String string) {
	ABSOLUTE_X = string;
}

public void setABSOLUTE_Y(String string) {
	ABSOLUTE_Y = string;
}

public String getFONT_ENCODED() {
	return FONT_ENCODED;
}

public void setFONT_ENCODED(String string) {
	FONT_ENCODED = string;
}

public String getHEIGHT() {
	return HEIGHT;
}

public String getWIDTH() {
	return WIDTH;
}

public void setHEIGHT(String string) {
	HEIGHT = string;
}

public void setWIDTH(String string) {
	WIDTH = string;
}

	public String getBORDER_COLOR_BOTTOM() {
		return (BORDER_COLOR_BOTTOM.trim().equals(""))?BORDER_COLOR_BOTTOM_RGB:BORDER_COLOR_BOTTOM;
	}

	public String getBORDER_COLOR_BOTTOM_RGB() {
		return BORDER_COLOR_BOTTOM_RGB;
	}

	public String getBORDER_COLOR_LEFT() {
		return (BORDER_COLOR_LEFT.trim().equals(""))?BORDER_COLOR_LEFT_RGB:BORDER_COLOR_LEFT;
	}

	public String getBORDER_COLOR_LEFT_RGB() {
		return BORDER_COLOR_LEFT_RGB;
	}

	public String getBORDER_COLOR_RIGHT() {
		return (BORDER_COLOR_RIGHT.trim().equals(""))?BORDER_COLOR_RIGHT_RGB:BORDER_COLOR_RIGHT;
	}

	public String getBORDER_COLOR_RIGHT_RGB() {
		return BORDER_COLOR_RIGHT_RGB;
	}

	public String getBORDER_COLOR_TOP() {
		return (BORDER_COLOR_TOP.trim().equals(""))?BORDER_COLOR_TOP_RGB:BORDER_COLOR_TOP;
	}

	public String getBORDER_COLOR_TOP_RGB() {
		return BORDER_COLOR_TOP_RGB;
	}

	public String getBORDER_WIDTH_BOTTOM() {
		return BORDER_WIDTH_BOTTOM;
	}

	public String getBORDER_WIDTH_LEFT() {
		return BORDER_WIDTH_LEFT;
	}

	public String getBORDER_WIDTH_RIGHT() {
		return BORDER_WIDTH_RIGHT;
	}

	public String getBORDER_WIDTH_TOP() {
		return BORDER_WIDTH_TOP;
	}

	public void setBORDER_COLOR_BOTTOM(String string) {
		BORDER_COLOR_BOTTOM = string;
	}

	public void setBORDER_COLOR_BOTTOM_RGB(String string) {
		BORDER_COLOR_BOTTOM_RGB = string;
	}

	public void setBORDER_COLOR_LEFT(String string) {
		BORDER_COLOR_LEFT = string;
	}

	public void setBORDER_COLOR_LEFT_RGB(String string) {
		BORDER_COLOR_LEFT_RGB = string;
	}

	public void setBORDER_COLOR_RIGHT(String string) {
		BORDER_COLOR_RIGHT = string;
	}

	public void setBORDER_COLOR_RIGHT_RGB(String string) {
		BORDER_COLOR_RIGHT_RGB = string;
	}

	public void setBORDER_COLOR_TOP(String string) {
		BORDER_COLOR_TOP = string;
	}

	public void setBORDER_COLOR_TOP_RGB(String string) {
		BORDER_COLOR_TOP_RGB = string;
	}

	public void setBORDER_WIDTH_BOTTOM(String string) {
		BORDER_WIDTH_BOTTOM = string;
	}

	public void setBORDER_WIDTH_LEFT(String string) {
		BORDER_WIDTH_LEFT = string;
	}

	public void setBORDER_WIDTH_RIGHT(String string) {
		BORDER_WIDTH_RIGHT = string;
	}

	public void setBORDER_WIDTH_TOP(String string) {
		BORDER_WIDTH_TOP = string;
	}

	public String getTEXT_ROTATION_DEGREE() {
		return TEXT_ROTATION_DEGREE;
	}

	public void setTEXT_ROTATION_DEGREE(String string) {
		TEXT_ROTATION_DEGREE = string;
	}
	public String getLEADING() {
		return LEADING;
	}
	public void setLEADING(String leading) {
		LEADING = leading;
	}

	public String getEXTRA_FONT() {
		return EXTRA_FONT;
	}
	public void setEXTRA_FONT(String eXTRAFONT) {
		EXTRA_FONT = eXTRAFONT;
	}
	public String getFONT_EMBEDDED() {
		return FONT_EMBEDDED;
	}
	public void setFONT_EMBEDDED(String fONTEMBEDDED) {
		FONT_EMBEDDED = fONTEMBEDDED;
	}
	public String getDIRECTION() {
		return DIRECTION;
	}
	public void setDIRECTION(String dIRECTION) {
		DIRECTION = dIRECTION;
	}

}
